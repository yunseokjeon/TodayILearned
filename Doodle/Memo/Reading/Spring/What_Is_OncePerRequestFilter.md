# What Is OncePerRequestFilter?

Filter는 servlet 실행 전후에 호출될 수 있다. request가 servlet에 전달될 때, RequestDispatcher가 이를 다른 servlet에 전달할 수도 있다. 서로 다른 servlet이 같은 filter를 갖을 수도 있는 데, 이 경우 같은 filter가 여러 번 실행된다. 하지만 특정한 filter가 request 당 한 번만 실행되길 원할 수 있다. 이런 상황에서 OncePerRequestFilter가 사용될 수 있다. Spring은 request 한 번에 OncePerRequestFilter가 오직 한 번만 실행되는 것을 보장한다.

## Using OncePerRequestFilter for Synchronous Requests

```Java
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws
            ServletException, IOException {
        String usrName = request.getHeader("userName");
        logger.info("Successfully authenticated user  " +
                usrName);
        filterChain.doFilter(request, response);
    }
}
```

## Using OncePerRequestFilter for Asynchronous Requests

shouldNotFilterAsyncDispatch() 메소드가 true를 반환하면, subsequent async dispatch에서는 filter가 호출되지 않는다. 하지만 false를 반환하면, thread 당 한 번씩, each async dispatch에서 실행된다.

```Java
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws
            ServletException, IOException {
        String usrName = request.getHeader("userName");
        logger.info("Successfully authenticated user  " +
                usrName);
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }
    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}
```

https://www.baeldung.com/spring-onceperrequestfilter

https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-basic-customization-2/src/main/java/com/baeldung/onceperrequestfilter

