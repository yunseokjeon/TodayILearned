```Java
class MyHome{
    void mySimpleRoom(int n){...}
    void mySimpleRoom(int n1, int n2){...}
    void mySimpleRoom(double d1, double d2){...} // 메소드 오버로딩
}
```
```Java 
// 키워드 this를 이용한 다른 생성자의 호출
class Person{
    private int regiNum;
    private int passNum;

    Person(int rnum, int pnum){
        regiNum = rnum;
        passNum = pnum;
    }

    Person(int rnum){
        this(rnum, 0); // this
    }

    void showPersonalInfo(){...}
}
```
```Java 
String st1 = "Coffee";
String st2 = "Bread";
String st3 = st1.concat(st2); // CoffeeBread

String str = "abcdefg";
str.substring(2,4); // cd

// str1.equals(str2) 같으면 true 반환
// cmp = str1.compareTo(str2) 같으면 0 반환, str1이 사전에서 앞에 위차하면 -1 반환
// str1.compareToIgnoreCase(str2)  대소문자 비교는 안함

double e = 2.71;
String se = String.valueOf(e); // "2.71"
```
