# [Nuxt RealWorld](https://github.com/pocojang/nuxt-realworld) 부검

## 설정

```json
// tsconfig.json

{
  "compilerOptions": {
    "target": "ES2018", // 사용할 특정 ECMAScript 버전 설정
    "module": "ESNext", // require가 아닌 import 문법 사용
    "moduleResolution": "Node", // 모듈 해석 방법 설정: 'node' (Node.js) 혹은 'classic'
    "lib": ["ESNext", "ESNext.AsyncIterable", "DOM"], // 컴파일에 포함될 라이브러리 파일 목록
    "esModuleInterop": true,  // 모든 imports에 대한 namespace 생성을 통해 CommonJS와 ES Modules 간의 상호 운용성이 생기게할 지 여부
    "allowJs": true,   //자바스크립트 파일 컴파일 허용 여부
    "sourceMap": true,  // '.map' 파일 생성 여부.
    "strict": true, // 모든 엄격한 타입-체킹 옵션 활성화 여부
    "noEmit": true, // 결과 파일 내보낼지 여부
    "experimentalDecorators": true, // ES7의 decorators에 대한 실험적 지원 여부
    "baseUrl": ".", // 변환된 자바스크립트 파일을 저장하는 디렉토리를 설정
    "paths": {
      "@/*": ["./*"]
    },  // 'baseUrl'를 기준으로 불러올 모듈의 위치를 재지정하는 엔트리 시리즈
    "types": ["@types/node", "@nuxt/types", "@nuxtjs/axios"]  // 컴파일중 포함될 타입 정의 파일 목록
  },
  "exclude": ["node_modules", ".nuxt", "dist"]
}

// https://geonlee.tistory.com/214
```

