AOP에 걸린 Method의 Parameter 이름 가져오기 https://alwayspr.tistory.com/34

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
</dependency>
```

## annotation 만들기

```Java
package com.ms.blogaopparametername.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccountValidator {
}
```
## Aspect 만들기

```Java
package com.ms.blogaopparametername.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.ms.blogaopparametername.User;

@Aspect
@Component
public class ValidatorAspect {
    
    @Pointcut("@annotation(com.ms.blogaopparametername.aop.AccountValidator)")
    public void accountValidator() {}

    /*@Before("accountValidator() && args(id,token,..)")
    public void validateAccount(Long id, String token) {
        System.out.println(id + " : " + token);
    }*/

    /**
     * joinPoint 적용
     * (maven 빌드에 추가되어 있긴 하지만 null이 반환 되면
     * javac -parameters 옵션 추가 후 Project rebuild 필요)
     */
    /*@Before("accountValidator()")
    public void validateAccount(JoinPoint joinPoint) {
        Long id = null;
        String token = null;
        String parameterName;
        Object[] parameterValues = joinPoint.getArgs();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        for (int i = 0; i < method.getParameters().length; i++) {
            parameterName = method.getParameters()[i].getName();
            if (parameterName.equals("id"))
                id = (Long) parameterValues[i];
            if (parameterName.equals("token"))
                token = (String) parameterValues[i];
        }

        System.out.println(id + " : " + token);
    }*/

    @Before("accountValidator()")
    public void validateAccount(JoinPoint joinPoint) {
        User user = Arrays.stream(joinPoint.getArgs())
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));
                .orElseGet(() -> {
                    System.out.println("User를 찾을 수 없습니다.");
                    return new User();
                });

        System.out.println(user.getId() + " : " + user.getToken());
    }
}

```

## User

```Java
package com.ms.blogaopparametername;

public class User {
    private Long id;
    private String token;

    public User() {
    }

    public User(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

```

## Service

```Java
package com.ms.blogaopparametername;

import org.springframework.stereotype.Service;

import com.ms.blogaopparametername.aop.AccountValidator;

@Service
public class BusinessService {
    @AccountValidator
    public void logic(Long id, String token) {
        System.out.println("Hello World\n");
    }

    @AccountValidator
    public void logic2(String name, Long id, String token) {
        System.out.println("Goodbye World\n");
    }

    @AccountValidator
    public void logic3(Long businessId, String token, Long id) {
        System.out.println("Good Morning World\n");
    }

    @AccountValidator
    public void logic4(User user) {
        System.out.println("Good Evening\n");
    }
}

```

## Main

```Java
package com.ms.blogaopparametername;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAopParameterNameApplication {
    private final BusinessService businessService;

    public BlogAopParameterNameApplication(BusinessService businessService) {
        this.businessService = businessService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogAopParameterNameApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (run) -> {
            this.businessService.logic(1L, "token");
            this.businessService.logic2("김민수", 1L, "token");
            this.businessService.logic3(999L, "token", 1L);
            this.businessService.logic4(new User(1L, "token"));
        };
    }
}

```

## 결과

```
User를 찾을 수 없습니다.
null : null
Hello World

User를 찾을 수 없습니다.
null : null
Goodbye World

User를 찾을 수 없습니다.
null : null
Good Morning World

1 : token
Good Evening

```
