## 실행 컨텍스트
실행할 코드에 제공할 환경 정보들을 모아놓은 객체

```JavaScript
var a = 1;

function outer(){
    function inner(){
        console.log(a); // undefined
        var a = 3;
    }
    inner();
    console.log(a); // 1
}

outer();
console.log(a); // 1

```

```JavaScript
function a(x){
    console.log(x); // 1
    var x;
    console.log(x); // 1
    var x = 2;
    console.log(x); // 2
}

a(1)
```

```JavaScript
function a(){
    console.log(b); // f b(){}
    var b = 'bbb';
    console.log(b); // bbb
    function b(){}
    console.log(b); // bbb
}

a();
```

```JavaScript
function a(){ } // 함수 선언문. 함수명 a가 곧 변수명.
a(); // OK

var b = function() { }  // (익명) 함수 표현식. 변수명 b가 곧 함수명.
b(); // OK

var c = function d(){ } // 기명 함수 표현식. 변수명은 c, 함수명은 d.
c(); // OK
d(); // Error
```


```JavaScript
var a = 1;

var outer = function(){
    var inner = function(){
        console.log(a); // undefiend
        var a = 3;
    };

    inner();
    console.log(a); // 1
};

outer();
console.log(a); // 1
```


