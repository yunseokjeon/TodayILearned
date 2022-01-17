# HandlerInterceptors vs. Filters in Spring MVC

## Filters

Filter는 스프링 프레임워크가 아닌 웹서버의 일부. Filter를 통해 request와 response를 차단하거나 조작할 수 있다. Spring Security는 인증과 인가를 위해 filter를 사용하는 좋은 예이다. 

## Filter 만들기

```Java
@Component
public class LogFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Hello from: " + request.getLocalAddr());
        chain.doFilter(request, response);
    }

}
```

## HandlerInterceptors

HandlerInterceptor는 스프링 프레임워크의 일부로, DispatcherServlet과 Controller 사이에 위치함.


## HandlerInterceptor 만들기

- preHandle() – Executed before the target handler is called
- postHandle() – Executed after the target handler but before the DispatcherServlet renders the view
- afterCompletion() – Callback after completion of request processing and view rendering

```Java
public class LogInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
    }

}
```

```Java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }

}
```

https://www.baeldung.com/spring-mvc-handlerinterceptor-vs-filter 


https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-mvc-3/src/main/java/com/baeldung/filtersinterceptors




