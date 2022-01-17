자바스크립트의 모든 변수는 실은 특정 객체의 프로퍼티로서 동작. 사용자가 var 연산자를 이용해 변수를 선언하더라도 실제 자바스크립트 엔진은 어떤 특정 객체의 프로퍼티로 인식하는 것. 특정 객체란 바로 실행 컨텍스트의 LexicalEnvironment. 실행 컨텍스트는 변수를 수집해서 LE의 프로퍼티로 저장. 이후 어떤 변수를 호출하면 LE를 조회해서 일치하는 프로퍼티가 있을 경우 그 값을 반환. 전역 컨텍스트의 경우 LE는 전역객체를 그대로 참조.

```JavaScript
var a = 1;
window.b = 2;
console.log(a, window.a, this.a);   // 1 1 1
console.log(b, window.b, this.b);   // 2 2 2

window.a = 3;
b = 4;
console.log(a, window.a, this.a);   // 3 3 3 
console.log(b, window.b, this.b);   // 4 4 4 

```

## 함수 내부에서의 this
어떤 함수를 함수로서 호출할 경우에는 this가 지정되지 않는다. this에는 호출한 주체에 대한 정보가 담기는데, 함수로서 호출하는 것은 호출 주체를 명시하지 않고 개발자가 코드에 직접 관여해서 실행한 것이기 때문에 호출 주체의 정보를 알 수 없는 것. 실행 컨텍스트를 활성화할 당시에 this가 기정되지 않은 경우 this는 전역 객체를 바라보는데, 따라서 함수에서의 this는 전역 객체를 가리킨다.

## 메서드의 내부함수에서의 this
```JavaScript
var obj1 = {
    outer: function(){
        console.log(this);  // obj1

        var innerFunc = function(){
            console.log(this);  // 전역객체(Window) and obj2
        }
        innerFunc();

        var obj2 = {
            innerMedhod: innerFunc
        };

        obj2.innerMethod();
    }
}
```

## 내부함수에서의 this를 우회하는 방법
```JavaScript
var obj = {
    outer: function(){
        console.log(this); // {outer: f}
        var innerFunc1 = function(){
            console.log(this);  // Window
        };
        innerFunc1();

        var self = this;
        var innerFunc2 = function(){
            console.log(self);  // {outer: f}
        };
        innerFunc2();

    }; 
};

obj.outer();
```

## this를 바인딩하지 않는 함수
```JavaScript
var obj = {
    outer: function(){
        console.log(this);  // {outer: f}
        var innerFunc = () => {
            console.log(this);  // {outer: f}
        }
        innerFunc();
    }
};

obj.outer();
```

## 콜백 함수 this
함수 A의 제어권을 다른 함수(또는 메서드) B에게 넘겨주는 경우 함수 A를 콜백 함수라 한다.

```JavaScript
setTimeout(function(){ console.log(this); }, 300);  //전역객체

[1, 2, 3, 4, 5].forEach(function(x){
    console.log(this);  //전역객체
});

document.body.innerHTML += '<button id="a">클릭</button>';
document.body.querySelector('#a')
    .addEventListener('click', function(e){
        console.log(this, e);   //addEventListener 메서드는 콜백 함수를 호출할 때 자신의 this를 상속하도록 정의돼 있다. 메서드명의 점 앞부분이 곧 this가 된다.
    });

// 콜백 함수에서의 this는 무엇이라고 단언할 수 없으며, 콜백 함수의 제어권을 가지는 함수(메서드)가 콜백 함수에서의 this를 무엇으로 할지를 결정하며, 특별히 정의하지 않은 경우에는 기본적으로 함수와 마찬가지로 전역객체를 바라본다.
```

## 생성자 함수 내부에서의 this

자바스크립트는 함수에 생성자로서의 역할을 함께 부여했다. new 명령어와 함께 함수를 호출하면 해당 함수가 생성자로서 동작하게 된다. 그리고 어떤 함수가 생성자 함수로서 호출된 경우 내부에서의 this는 곧 새로 만들 구체적인 인스턴스 자신이 된다.


```JavaScript
var Cat = function(name, age){
    this.bark = '야옹';
    this.name = name;
    this.age = age;
};

var choco = new Cat('초코', 7);
var nabi = new Cat('나비', 5);
console.log(choco, nabi);

/*
Cat {bark: 야옹, name: 초코, age: 7}
Cat {bark: 야옹, name: 나비, age: 5}
*/
```

## 명시적으로 this를 바인딩하는 방법 - call 메서드

```JavaScript
var func = function(a, b, c){
    console.log(this, a, b, c);
};

func(1, 2, 3);  // Window
func.call({x: 1}, 4, 5, 6); // {x: 1} 4 5 6
```

```JavaScript
var obj = {
    a: 1,
    method: function(x, y){
        console.log(this.a, x, y);
    }
};

obj.method(2, 3);   // 1 2 3
obj.method.call({a: 4}, 5, 6);  // 4 5 6
```

## 명시적으로 this를 바인딩하는 방법  - apply 메서드

```JavaScript
var func = function(a, b, c){
    console.log(this, a, b, c);
};
func.apply({x: 1}, [4, 5, 6]);  // {x: 1} 4 5 6

var obj = {
    a: 1,
    method: function(x, y){
        console.log(this.a, x, y);
    }
};
obj.medthod.apply({a: 4}, [5, 6]);  // 4 5 6
```

## call / apply 메서드의 활용

```JavaScript
var obj = {
    0: 'a',
    1: 'b',
    2: 'c',
    length: 3
};
Array.prototype.push.call(obj, 'd');
console.log(obj);   // {0: 'a', 1: 'b', 2: 'c', 3: 'd', length: 4}

var arr = Array.prototype.slice.call(obj);
console.log(arr);   ['a', 'b', 'c', 'd']
```

## 생성자 내부에서 다른 생성자를 호출

```JavaScript
function Person(name, gender){
    this.name = name;
    this.gender = gender;
}

function Student(name, gender, school){
    Person.call(this, name, gender);
    this.school = school;
}

function Employee(name, gender, company){
    Person.apply(this, [name, gender]);
    this.company = company;
}

var by = new Student('보영', 'female', '단국대');
var jn = new Employee('재난', 'male', '구골');
```

call / apply 메서드는 명시적으로 별도의 this를 바인딩하면서 함수 또는 메서드를 실행하는 훌륭한 방법이지만 오히려 이로 인해 this를 예측하기 어렵게 만들어 코드 해석을 방해한다는 단점이 있다.

## bind

```JavaScript
var func = function(a, b, c, d){
    console.log(this, a, b, c, d);
};
func(1, 2, 3, 4);   // Window 1 2 3 4

var bindFunc1 = func.bind({x: 1});
bindFunc1(5, 6, 7, 8);  // {x: 1} 5 6 7 8

var bindFunc2 = fund.bind({x: 1}, 4, 5);
bindFunc2(6, 7);    // {x: 1} 4 5 6 7
bindFunc2(8, 9);    // {x: 1} 4 5 8 9
```

## name 프로퍼티

```JavaScript
var func = function(a, b, c, d){
    console.log(this, a, b, c, d);
};
var bindFunc = func.bind({x: 1}, 4, 5);
console.log(func.name); // func
console.log(bindFunc);  // bound func
```
