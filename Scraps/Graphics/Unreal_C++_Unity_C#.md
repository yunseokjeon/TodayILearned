리눅스 커널에서 Rust를 도입한다는 것을 보면 시스템 프로그래밍 영역에서 C 언어가 Rust로 넘어갈 것으로 예상되는데요. C++이 문제군요. 아직까지 이렇다할 대체재가 나오지 않아서 당분간은 혼란스러울 것 같습니다. 이럴 때는 MS가 나서줘야 하는데 아무 소식이 없군요.

오후 7:21 · 2022년 9월 22일
·Twitter Web App

https://twitter.com/pyrasis/status/1572893725197733889

언리얼 같은 게임 엔진에서 Rust를 채택해주면 급격히 확산될 것 같은데, 아직 큰 움직임은 보이지 않는군요. 게임 업계에서는 유니티로 인해 C# 사용자가 대폭 늘었거든요.

오후 7:37 · 2022년 9월 22일
·Twitter for iPhone

https://twitter.com/pyrasis/status/1572897873943207937

언리얼이 C++을 사용하고 있지만 가비지 컬렉터를 내장하고 있어서 사실상 C++의 슈퍼셋 내지는 변종에 가까운 형태를 띄고 있습니다.

오후 7:40 · 2022년 9월 22일
·Twitter for iPhone

https://twitter.com/pyrasis/status/1572898516833538048

유니티는 정말 특이한 것이 닷넷 프레임워크에서 돌아가는 VM 언어인 C#을 쓰고 있는데요. 여기서 유니티 자체적으로 IL2CPP라는 것을 만들어서 C# IL을 C++ 소스코드로 변환한 뒤에 이걸 컴파일해서 네이티브 바이너리를 만들어냅니다.

오후 7:48 · 2022년 9월 22일
·Twitter for iPhone

https://twitter.com/pyrasis/status/1572900633694568453

“유니티는 정말 특이한 것이 닷넷 프레임워크에서 돌아가는 VM 언어인 C#을 쓰고 있는데요. 여기서 유니티 자체적으로 IL2CPP라는 것을 만들어서 C# IL을 C++ 소스코드로 변환한 뒤에 이걸 컴파일해서 네이티브 바이너리를 만들어냅니다.” 선생님. 이런 정보는 어디서 알 수 있는지요?

오후 8:20 · 2022년 9월 22일
·Twitter for iPhone

https://twitter.com/digit0xff/status/1572908610694574080

아. IL2CPP는 유니티 공식 문서에 나와있습니다. 유니티로 프로젝트할 때 IL2CPP를 직접 사용하기도 했고요.

https://docs.unity3d.com/kr/2021.3/Manual/IL2CPP.html

오후 8:22 · 2022년 9월 22일
·Twitter Web App

https://twitter.com/pyrasis/status/1572909146776961024

