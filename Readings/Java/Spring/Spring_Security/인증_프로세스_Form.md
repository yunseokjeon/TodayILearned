## 정적 자원 관리 - WebIgnore 설정

스프링 시큐리티가 CSS와 같은 정적 자원 관리에도 관여하게 되는데, 이런 자원에 대한 보안을 해제할 필요가 있으므로, 이를 설정해야 한다.

```Java
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        http.authorizeRequests()
            .antMatchers("/","/users").permitAll()
            .antMatchers("/mypage").hasRole("USER")
            .antMatchers("/messages").hasRole("MANAGER")
            .antMatchers("/config").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and().formLogin();
    }
}
```

## DB 연동 인증 처리(1) : CustomUserDetailsService

```Java
@Service("userDetailsService")
public class CustomerUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Account account = userRepository.findByUsername(username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        AccountContext accountContext = new AccountContext(account, roles);
        return accountContext;
    }
}
```

