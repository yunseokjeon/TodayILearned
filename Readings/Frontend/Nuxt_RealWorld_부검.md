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

```TypeScript
// nuxt.config.ts

const config: NuxtConfig = {
  // ...
  plugins: ['@/plugins/repository'],  // plugin 을 통해서 사용자는 사용하고자 하는 함수 혹은 instance 를 vue root application 혹은 context 에 binding

  components: true,
  // 사용하는 컴포넌트의 auto-import 가능하게 설정

}
```

## types

```TypeScript
// types/index.ts

export type ResponseType<K extends string, V> = Promise<{ [P in K]: V }>
```

[P in K]: V 는 K에 있는 항목 하나씩에 V를 매핑함을 의미. 아래 예제를 참조해서 이해.

```TypeScript
type Record<K extends keyof any, T> = {
    [P in K]: T;
};

export interface Car {
    name: string,
    price: number
}

const productList: Record<"SONATA" | "AVANTE", Car> = {
    SONATA: {name: "SONATA", price: 10000},
    AVANTE: {name: "SONATA", price: 10000}
}
```

실제 사용 예.

```TypeScript
export interface Article {
  slug: string
  title: string
  description: string
  body: string
  tagList: Tag[]
  createdAt: string
  updatedAt: string
  favorited: boolean
  favoritesCount: number
  author: Author
}

type ArticleResponse = ResponseType<'article', Article>
```

```TypeScript
// types/index.ts

export type OptionalPick<T, K extends keyof T> = Pick<Partial<T>, K>
```
하나씩 분해해서 보자.

K extends keyof T : T를 이루고 있는 키를 포함한 타입 K

Partial<T> : T의 일부 요소만 가지는 타입

Pick<Partial<T>, K> : Partial<T> 중에서 K 만을 선택

실제 사용 예.

```TypeScript
export interface Article {
  slug: string
  title: string
  description: string
  body: string
  tagList: Tag[]
  createdAt: string
  updatedAt: string
  favorited: boolean
  favoritesCount: number
  author: Author
}

export type CreateArticleRequest = Pick<
  Article,
  'title' | 'description' | 'body'
> &
  OptionalPick<Article, 'tagList'>
```

```TypeScript
// types/index.ts

export type Optional<T, K extends keyof T> = OptionalPick<T, K> & Omit<T, K>
```

Omit<T, K> : T에서 K는 제외.

```TypeScript
// types/plugin-types.d.ts

import { Repository } from '@/api'

declare module 'vue/types/vue' {
  interface Vue {
    $repository: Repository
  }
}

declare module '@nuxt/types' {
  interface NuxtAppOptions {
    $repository: Repository
  }
  interface Context {
    $repository: Repository
  }
}
```

declare module 'vue/types/vue'를 사용하면, Vue.$repository 가 타입 체크에서 걸리지 않고 사용할 수 있게 된다.


