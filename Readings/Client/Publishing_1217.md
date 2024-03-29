# <꾸미는> CSS

## [Lesson 1. CSS 적용방법(inline, internal, linked)과 선택자들](https://www.yalco.kr/@html-css/2-1/)

```CSS
/* 모든 요소 선택 */
* {
    font-weight: bold;
    color: darkorange;
}

/* 같은 선택자의 경우 뒤에 오는 것이 우선순위 높음 */
* {
    color: plum;
}

/* 태그 선택자 */
p {
    color: olivedrab;
}

/* class 선택자 */
/* 태그보다 우선순위 높음 */
/* 페이지상의 여러 요소가 같은 class를 가질 수 있음 */
.blue {
    color: lightblue;
}

/* 다른 선택자에 이어붙일 수 있음(태그, 클래스 등...) */
/* 선택자는 구체적일수록 우선순위 높음 */
p.blue {
    color: slateblue;
}

.blue.dark {
    color: mediumblue;
}

p.blue.dark {
    color: darkblue;
}

/* id 선택자 */
/* class보다 우선순위 높음 */
/* id는 페이지상에서 요소마다 고유해야 함 */
#red {
    color: tomato;
}

/* 그룹 선택자 */
span, .dark, #red {
    text-decoration: underline;
}
```

```CSS
/* 자손 결합자 */
/*outer 클래스 내부의 모든 li*/
.outer li {
  color: olivedrab;
}

/* 자식(1촌 자손) 결합자 */
.outer > li {
  color: dodgerblue;
}

/*outer의 자식(1촌 자손) li의 자손 li*/
.outer > li li {
  text-decoration: underline;
}

/* 뒤따르는 모든 동생들 결합자 
    <li class="starter"> </li> 아래의 li들 
*/
.starter ~ li {
  font-style: italic;
}

/* 뒤따르는 바로 다음 동생 결합자 */
.starter + li {
  font-weight: bold;
}

/* 첫 번째, 마지막 요소 가상 클래스 */
ol li:first-child,
ol li:last-child {
  color: yellowgreen;
}

/* ~가 아닌 요소 가상 클래스 *
    outer의 자식 중에 마지막 요소가 아닌 것
    <ul class="outer"> </ul>
 */
.outer > li:not(:last-child) {
  text-decoration: line-through;
}

ul:not(.outer) li {
  font-weight: bold;
}

/* ~번째 요소 가상 클래스 */
/* #, #n, #n+#, odd, even 등 시도해보기 */
ol li:nth-child(3) {
  font-weight: bold;
  color: deeppink;
}

/* 마우스오버 가상 클래스 */
li:hover {
  font-weight: bold;
  color: blue;
}
```
