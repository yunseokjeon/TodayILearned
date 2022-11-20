## 기본 타입

```TypeScript
// 튜플 (Tuple)

let b:[string, number];
b = ['z',1]
b[0].toLowerCase()

// void, never

function sayHello():void {
    console.log('hello');
}

function showError():never {
    throw new Error();
}

function infLoop():never {
    while(true){

    }
}

// enum

enum Os {
    Windows,
    Ios,
    Android
}

let myOs:Os;
myOs = Os.Windows;

// null, undefined

let a:null = null;
let b:undefined = undefined;

```

## 인터페이스

```TypeScript
type Score = 'A' | 'B' | 'C' | 'F';

interface User {
    name: string;
    age: number;
    gender? : string;
    readonly birthYear : number;
    [grade : number] : Score;
}

let user: User = {
    name: 'me',
    age: 30,
    birthYear: 2000,
    1: 'A',
    2: 'B'
}

interface Add {
    (num1: number, num2: number): number;
}

const add: Add = function(x, y) {
    return x + y;
}

interface IsAdult {
    (age: number): boolean;
}

const a:IsAdult = (age) => {
    return age > 19;
}

// implements

interface Car {
    color: string;
    wheels: number;
    start(): void;
}

interface Benz extends Car {
    door: number;
    stop(): void;
}

interface Toy {
    name: string;
}

interface ToyCar extends Car, Toy {
    price: number;
}


class Bmw implements Car {
    color;
    wheels = 4;

    constructor(c: string){
        this.color = c;
    }

    start(){
        console.log('go');
    }
}

const b = new Bmw('green');

```

## 함수

```TypeScript
function hello(name?: string) {
    return `Hello, ${name || "world"}`;
}

const result = hello();

function hello2(name = "world") {
    return `Hello, ${name}`;
}

function add(...nums: number[]) {
    return nums.reduce((result, num) => result + num, 0);
}





interface User {
    name: string;
}

const Sam: User = {name: 'Sam'}

function showName(this: User, age: number, gender: 'm'|'f') {
    console.log(this.name)
}

const a = showName.bind(Sam);
a(30, 'm') 





interface User {
    name: string;
    age: number;
}

function join(name: string, age: string): string;
function join(name: string, age: number): User;
function join(name: string, age: number | string): User | string{
    if(typeof age === "number"){
        return{
            name,
            age,
        };
    }else{
        return "나이는 숫자로 입력해주세요.";
    }
}

const sam: User = join("Sam", 30);
const jame: string = join("Jane", "30");
```

## 리터럴, 유니온 / 교차 타입

```TypeScript
// Literal Types

const userName1 = "Bob";
let userName2: string | number = "Tom";
userName2 = 3;




type Job = "police" | "developer" | "teacher";

interface User {
    name: string;
    job: Job;
}

const user: User = {
    name: "Bob",
    job: "developer",
}



// Union Types

interface Car {
    name: "car";
    color: string;
    start(): void;
}

interface Mobile {
    name: "mobile";
    color: string;
    call(): void;
}

function getGift(gift: Car | Mobile) {
    console.log(gift.color);
    if(gift.name === "car") {
        gift.start();
    } else {
        gift.call();
    }
}



// Intersection Types

interface Car {
    name: string;
    start(): void;
}

interface Toy {
    name: string;
    color: string;
    price: number;
}

const toyCar: Toy & Car = {
    name: "타요",
    start() {},
    color: "blue",
    price: 1000,
}
```

## 클래스

```TypeScript
class Car {
    // color: string;

    constructor(public color: string) {
        this.color = color;
    }
    start() {
        console.log("start");
    }
}

const bmw = new Car("red");


// 접근 제한자(Access modifier) - public, private, protected
/*
public - 자식 클래스, 클래스 인스턴스 모두 접근 가능
protected - 자식 클래스에서 접근 가능
private - 헤당 클래스 내부에서만 접근 가능
*/
class Car {
    readonly name: string = "car"; 
    color: string;
    static wheels = 4;

    constructor(color: string, name) {
        this.color = color;
        this.name = name;
    }
    start() {
        console.log("start");
        console.log(this.name);
        console.log(Car.wheels);
    }
}

class Bmw extends Car {
    constructor(color: string, name) {
        super(color, name);
    }
    showName() {
        console.log(this.name);
    }
}


// 추상 클래스

abstract class Car {
    color: string;

    constructor(color: string) {
        this.color = color;
    }

    start() {
        console.log("start");
    }

    abstract doSomething(): void;
}

class Bmw extends Car {

    constructor(color: string) {
        super(color);
    }

    doSomething() {
        alert(3);
    }
}
```

## 제네릭 Generics

```TypeScript
// Generic

function getSize<T>(arr: T[]): number {
    return arr.length;
}

const arr1 = [1, 2, 3];
getSize<number>(arr1);






interface Mobile<T> {
    name: string;
    price: number;
    option: T;
}

const m1: Mobile<{ color: string; coupon: boolean }> = {
    name: "s21",
    price: 1000,
    option: {
        color: "red",
        coupon: false,
    },
}

const m2: Mobile<string>  = {
    name: "s20",
    price: 900,
    option: "good",
}






interface User {
    name: string;
    age: number;
}

interface Car {
    name: string;
    color: string;
}

interface Book {
    price: number;
}

const user: User = {name: "a", age: 10};
const car: Car = {name: "bmw", color: "red"};
const book: Book = {price: 3000};

function showName<T extends { name: string }>(data: T): string {
    return data.name;
}
```

## 유틸리티 타입 Utility Types

```TypeScript
// keyof

interface User {
    id: number;
    name: string;
    age: number;
    gender: "m" | "f";
}

type UserKey = keyof User; // 'id' | 'name' | 'age' | 'gender'

const uk: UserKey = 'age'


// Partial<T>

interface User {
    id: number;
    name: string;
    age: number;
    gender: "m" | "f";
}

let admin: Partial<User> = {
    id: 1,
    name: "Bob",
}


// Required<T>

interface User {
    id: number;
    name: string;
    age?: number;
}

let admin: Required<User> = {
    id: 1,
    name: "Bob",
    age: 30,
}


// Readonly<T>

interface User {
    id: number;
    name: string;
    age?: number;
}

let admin: Readonly<User> = {
    id: 1,
    name: "Bob",
}

admin.id = 4; // 에러 발생 


// Record<K, T>

type Grade = "1" | "2" | "3" | "4";
type Score = "A" | "B" | "C" | "D" | "F";

const score: Record<Grade, Score> = {
    1: "A",
    2: "C",
    3: "B",
    4: "D",
}

interface User {
    id: number;
    name: string;
    age: number;
}

function isValid(user: User) {
    const result: Record<keyof User, boolean> = {
        id: user.id > 0,
        name: user.name !== "",
        age: user.age > 0,
    };

    return result;
}


// Pick<T, K>

interface User {
    id: number;
    name: string;
    age: number;
    gender: "M" | "W";
}

const admin: Pick<User, "id" | "name"> = {
    id: 0,
    name: "Bob",
}


// Omit<T, K>

interface User {
    id: number;
    name: string;
    age: number;
    gender: "M" | "W";
}

const admin: Omit<User, "age" | "gender"> = {
    id: 0,
    name: "Bob",
}


// Exclude<T1, T2>

type T1 = string | number | boolean;
type T2 = Exclude<T1, number | string>;


// NonNullable<Type>

type T1 = string | null | undefined | void;
type T2 = NonNullable<T1>; // string과 void


```