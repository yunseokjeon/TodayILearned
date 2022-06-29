안녕하세요, 트위터 친구 여러분. 혹시 아래의 질문에 현답을 주실 분들이 계실까요?

오후 9:21 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/digit0xff/status/1542121174171516930

1) http://nivo.rocks/line/ 여기 링크의 예제 코드를 보면, <ResponsiveLine/>가 놓인 부분에 차트가 그려질텐데, 라이브러리 내부적으로는 canvas 태그가 사용되고 있는 것일까요?

오후 9:22 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/digit0xff/status/1542121201090560000

2) http://github.com/plouc/nivo 저장소의 주소인데, 라인 차트 부분의 코드를 찾아보고 싶은데, 어떻게 찾아야 할까요? 오픈소스 코드를 보는 일반적인 방법에 대한 질문이겠네요.

오후 9:22 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/digit0xff/status/1542121281742831617

직접 구현하시려는 것인지 사용하시려는 것인지요. 두번째 트윗의 페이지에 보니까 SVG 버전도 있고 canvas 버전도 있고 소스코드도 예제샘플로 다 있던데용.

오후 9:48 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/nicehide/status/1542127811124637696


세상에나. 사용하려는 것은 아니고, 어떻게 화면을 그리나 하는 궁금증이 있었습니다. 관심 감사드려요!

오후 9:59 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/digit0xff/status/1542130575464878080

보니까 여기가 라인차트 패키지구요

https://github.com/plouc/nivo/tree/master/packages/line

오후 10:08 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/nicehide/status/1542132849431629824

이게 svg 
https://github.com/plouc/nivo/blob/master/packages/line/src/ResponsiveLine.js

오후 10:10 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/nicehide/status/1542133351544279040

이게 canvas 네용.

https://github.com/plouc/nivo/blob/master/packages/line/src/ResponsiveLineCanvas.js

오후 10:10 · 2022년 6월 29일·Twitter for iPhone

실제 그리는 구현부분은 각각 Line.js, LineCanvas.js 인 듯 합니다.

오후 10:12 · 2022년 6월 29일·Twitter for iPhone

https://twitter.com/nicehide/status/1542133812649287680


