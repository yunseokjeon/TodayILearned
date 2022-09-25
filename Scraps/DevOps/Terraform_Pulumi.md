pulumi가 사업을 진행하는 걸 보면 정말 특이하다는 생각이 든다. 유료 서비스이자 오픈소스인 terraform의 코드를 그대로 가져와서 Python,C#,Java,Go,TypeScript로 래핑한 오픈소스를 내놓음. 비즈니스 모델도 terraform과 동일하게 state를 클라우드에 저장해주는 서비스

오후 2:12 · 2022년 9월 22일
·Twitter Web App

https://twitter.com/pyrasis/status/1572816030271180801

핵심은 전부 terraform이 구현했는데, 래핑한 기술만 가지고 비즈니스가 된다는 것이 신기함.

오후 2:13 · 2022년 9월 22일
·Twitter Web App

https://twitter.com/pyrasis/status/1572816249264164866

terraform은 HCL이 가장 큰 약점이라 생각해서 pulumi는 이 부분을 해결하려고 한 것 같음.

오후 2:14 · 2022년 9월 22일
·Twitter Web App

https://twitter.com/pyrasis/status/1572816506853138432

AWS의 CloudFormation도 처음에는 JSON, YAML만 지원하다가, 이제는 AWS CDK라고 해서 TypeScript, JavaScript, Python, Java, C#을 지원하는 래퍼를 내놓음.

오후 2:16 · 2022년 9월 22일
·Twitter Web App

https://twitter.com/pyrasis/status/1572816964506222592

