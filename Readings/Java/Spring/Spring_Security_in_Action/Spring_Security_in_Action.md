# 1. 오늘날의 보안

스프링 시큐리티 : 스프링 애플리케이션에 보안을 적용하는 과정을 크게 간소화하는 프레임워크

인증 Authentication : 애플리케이션이 사용자를 식별하는 방법

권한 부여 Authorization : 사용자를 식별하는 목적은 나중에 그들이 무엇을 하도록 허용해야 하는지 결정하기 위한 것

# 2. 안녕! 스프링 시큐리티

## 기본 구성

스프링 시큐리티의 인증 프로세스에 포함된 주 구성 요소와 이들 간의 관계.

1. 인증 필터가 요청을 가로챈다.
2. 인증 책임이 인증 관리자에 위임된다.
3. 인증 관리자는 인증 논리를 구현하는 인증 공급자를 이용한다.
4. 인증 공급자는 사용자 세부 정보 서비스로 사용자를 찾고 암호 인코더로 암호를 검증한다.
5. 인증 결과가 필터에 반환된다.
6. 인증된 엔티티에 관한 세부 정보가 보안 컨텍스트에 저장된다.

## ssia-ch2-ex2

```Java
// ProjectConfig.java

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        // 주어진 사용자 이름, 암호, 권한 목록으로 사용자 생성.
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        // UserDetailsService에서 관리하도록 사용자 추가.
        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated(); // 모든 요청에 인증이 필요하다.
    }
}
```

## ssia-ch2-ex3

```Java
// ProjectConfig.java

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        // 이제 configure() 메서드에서 UserDetailsService와 PasswordEncoder가 설정됨.
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
```

## ssia-ch2-ex4

```Java
// ProjectConfig.java

/*
애플리케이션의 책임을 분리해서 작성하는 것이 좋기 때문에 이 방법은 사용하지 말 것.
*/
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("john")
                .password("12345")
                .authorities("read")
        .and()
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
```

## ssia-ch2-ex5

```Java
// ProjectConfig.java

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}
```

```Java
// CustomAuthenticationProvider.java

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if ("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
    }

    // 5장의 내용
    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }
}
```

## ssia-ch2-ex6

항상 한 클래스가 하나의 책임을 맡도록 하는 것이 바람직하다.

```Java
// UserManagementConfig.java

@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
```

```Java
// WebAuthorizationConfig.java

@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
```

# 3. 사용자 관리

스프링 시큐리티가 사용자를 이해할 수 있도록 애플리케이션의 사용자를 기술하는 방법을 배우는데, 프레임워크가 사용자를 인식할 수 있게 사용자를 나타내는 방법을 배우는 것은 인증 흐름을 구축하기 위한 필수 단계다.

## UserDetailsService 계약의 이해

AuthenticationPovider는 인증 논리에서 UserDetailsService를 이용해 사용자 세부 정보를 로드한다. 데이터베이스 등에서 사용자를 로드하도록 UserDetailsService를 구현할 수 있다.

AuthenticationPrivider는 인증 논리를 구현하고 UserDetailsService를 이용해 사용자 세부 정보를 로드하는 구성 요소이며 사용자 이름으로 사용자를 찾기 위해 loadUserByUsername(String username) 메서드를 호출한다.

## ssia-ch3-ex1

```Java
// InMemoryUserDetailsService.java

public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    /*
    users가 일종의 데이터베이스 역할을 하고 있고, 이 저장소에서 사용자를 조회하는 메서드를 오버라이딩하고 있다. 
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
```

```Java
// User.java

// UserDetails를 구현하는 저장소에 저장될 클래스

public class User implements UserDetails {

    private final String username;
    private final String password;
    private final String authority;

    public User(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

```Java
// ProjectConfig.java

@Configuration
public class ProjectConfig {

    // UserDetails 객체 하나를 담고 있는, UserDetailsService 빈 생성.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails u = new User("john", "12345", "read");
        List<UserDetails> users = List.of(u);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

```

## UserDetailsManager 계약 구현

스프링 시큐리티가 인증을 수행하려면 UserDetailsService 계약이 필요한데, 일반적으로 애플리케이션에는 사용자를 관리하는 기능이 필요하고 대부분의 앱은 새 사용자를 추가하거나 기존 사용자를 삭제할 수 있어야 한다. 이때는 스프링 시큐리티에 정의된 더 구체적인 인테페이스인 UserDetailsManager를 구현한다.


# 4. 암호 처리

PassswordEncoder는 인증 프로세스에서 암호가 유효한지를 확인한다. 모든 시스템은 어떤 방식으로든 인코딩된 암호를 저장하며 아무도 암호를 읽을 수 없게 해시를 저장하는 것이 좋다. PasswordEoncoder도 암호를 인코딩할 수 있다. 계약에 선언된 encode() 및 matches() 메서드는 사실상 계약의 책임을 정의한다. 

## DelegatingPasswordEncoder를 이용한 여러 인코딩 전략

현재 사용되는 알고리즘에서 취약성이 발견되어 신규 등록 사용자의 자격 증명을 변경하고 싶지만, 기존 자격 증명을 변경하기가 쉽지 않다고 생각해 보자. 이때는 여러 종류의 해시를 지원해야 하는데, 이 상황을 어떻게 해결해야 할까? DelegatingPasswordEncoder 객체가 하나의 솔루션이 될 수 있다.

## 인코딩, 암호화, 해싱

인코딩(Encoding)은 주어집 입력에 대한 모든 변환을 의미한다. 

암호화(Encryption)는 출력을 얻기 위해 입력값과 키를 모두 지정하는 특정한 유형의 인코딩이다. 

(x, k) -> y, x는 입력이고, k는 키며, y는 암호화 결과다.

(y, k) -> x, 이를 역함수 복호화(Reverse Function Decryption)라고 하고, 암호화에 쓰는 키와 복호화에 쓰는 키가 같으면, 일반적으로 이를 대칭키라고 한다.

해싱(Hashing)은 함수가 한 방향으로만 작동하는 특정한 유형의 인코딩이다. 따라서 해싱 함수의 출력 y에서 입력 x를 얻을 수 없다. 때때로 해싱 함수는 입력에 임의의 값을 추가할 수도 있으며, 이 값을 솔트(salt)라고 한다. 

## 스프링 시큐리티에서 인증 흐름을 위한 주 계약을 나타내는 인터페이스

UserDetails - 스프링 시큐리티가 관리하는 사용자를 나타낸다.

GrantedAuthority - 애플리케이션의 목적 내에서 사용자에게 허용되는 작업을 정의한다. (읽기, 쓰기, 삭제 등)

UserDataisService - 사용자 이름으로 사용자 세부 정보를 검색하는 객체를 나타낸다.

UserDetailsManager - UserDetailsService의 더 구체적인 계약이다. 사용자 이름으로 사용자를 검색하는 것 외에도 사용자 컬렉션이나 특정 사용자를 변경할 수도 있다.

PasswordEncoder - 암호를 암호화 또는 해시하는 방법과 주어진 인코딩 된 문자열을 일반 텍스트 암호화 비교하는 방법을 지정한다.

## 키 생성기 && 암호기

키 생성기 - 해싱 및 암호화 알고리즘을 위한 키를 생성하는 객체

암호기 - 데이터를 암호화 및 복호화하는 객체

