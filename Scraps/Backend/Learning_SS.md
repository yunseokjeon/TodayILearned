Spring Security 책 써주세요. 그전까지 읽을 Spring Security 책 추천해 주세요.

fsoftwareengineer
2022년 08월 19일 (금)

저는 공식문서를 읽었는데.. 스프링 문서 꽤 괜찮다고 생각해요!

https://ask.fm/fsoftwareengineer9/answers/172261815685

Spring Security 공식 문서에는 예제가 거의 없는데, 이 문제는 어떻게 해결하셨나요?

fsoftwareengineer
2022년 08월 20일 (토)

다른 오픈소스나 블로그를 보면서(spring security tutorial 검색하면 여러개 나와요) 해결하거나 예제가 없는경우 또는 안돌아가는 경우 직접 테스팅+스프링시큐리티 코드를 디버깅 해서 배웠습니다..

https://ask.fm/fsoftwareengineer9/answers/172287225989

"직접 테스팅+스프링시큐리티 코드를 디버깅 해서 배웠습니다" 이에 대해 조금만 더 자세히 말씀해 주세요.

fsoftwareengineer
2022년 08월 20일 (토)

제가 공부한 범위는 대부분 WebSecurityConfigurerAdapter 를 구현하고 http.method(myImplementation) 처럼 적절한 빈을 설정하는게 대부분이거든요. (타래로)

https://ask.fm/fsoftwareengineer9/answers/172287773829

이 경우 제가 가장 처음 알고싶은건 이 빈이 언제 실행되는지나 빈에 넘어오는 매개변수의 값이 무엇인지입니다. 
그래서 보통 설정하고자하는 메서드 말고도 다른 설정을 여러개 추가해보고 예) authenticationEntrypoint추가 successhandler추가 후 간단히 로그와 넘어온 파라미터 출력

오후 1:31 · 2022년 8월 20일·Twitter for iPhone

https://twitter.com/fsoftwareengin1/status/1560846945232465920

해서 실행순서/조건을 확인해요. 예를들어authenticationEntrypoint가 실행될줄 알았는데 안됐다, 그럼 공식문서나 예제를 다시 읽어보고 빠진부분을 하나씩 추가하면서 빠진게 뭔지 찾아요.. 문서나 예제는 여러 설정이 섞여있어 정확히 뭐가 필요한지 찾기 힘들거든요. (좀 미련하죠😅)

오후 1:40 · 2022년 8월 20일·Twitter for iPhone

https://twitter.com/fsoftwareengin1/status/1560849151927021568

또 다른 방법은 다시 예를들어 authenticationEntrypoint 이라 치면, 이 추상클래스를 코드 리포에서 찾아봐요. Caller가 누구인지 찾아보는거죠. 그걸 보면서 공식문서를 같이 읽거나 블로그의 플로우차트를 비교해서 공부해요.

오후 1:40 · 2022년 8월 20일·Twitter for iPhone

https://twitter.com/fsoftwareengin1/status/1560849153923526656

또는 각 추상클래스마다 Default로 구현된 클래스들이 있거든요, 그 클래스들은 어떻게 구현했는지 보기도 합니다.

오후 1:40 · 2022년 8월 20일·Twitter for iPhone

https://twitter.com/fsoftwareengin1/status/1560849155764789249

당연한 이야기지만 한번 읽는다고 이해되는건 아니고 헤매면서 몇번 읽으면 이해해요 :)

오후 2:26 · 2022년 8월 20일·Twitter for iPhone

https://twitter.com/fsoftwareengin1/status/1560860919197868033

