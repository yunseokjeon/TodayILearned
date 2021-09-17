# 자바 가상머신의 메모리 모델
- 메소드 영역 Method Area - 메소드의 바이트 코드, static 변수
- 스택 영역 Stack Area - 지역변수, 매개변수
- 힙 영역 Heap Area - 인스턴스

```Java
// 메소드 영역
class Boy{
    static int average = 0;
    public void Run(){....}
}

class MyMain{
    public static void main(String[] args){
        Boy b = new Boy(); // 인스턴스 생성
        Boy.average += 5; // 클래스 변수 접근

        // 메소드 영역, 바이트코드와 static 변수가 할당되는 메모리 공간, 이 영역에 저장된 내용은 프로그램 종료 시 소멸된다.
    }
}
```
```Java
// 스택 영역

public static void main(String[] args){
    int num1 = 10;
    int num2 = 20;
    adder(num1, num2);
}

public static void adder(int n1, int n2){
    int result = n1 + n2;
    return result;
}

// 스택 영역, 지역변수 매개변수 할당되는 영역

```
```Java
// 힙 영역
public static void main(String[] args){
    String str1 = new String("My String");
    String str2 = new String("Your String");
    
    // 힙 영역, 인스턴스가 저장되는 영역, 가비지 컬렉션의 대상이 되는 영역.
}
```
```Java
// clone 메소드 호출의 예
class Point implements Cloneable{
    private int xPos;
    private int yPos;

    public Point(int x, int y){
        xPos = x;
        yPos = y;
    }

    public void showPosition(){
        System.out.printf("[%d, %d]", xPos, yPos);
        System.out.println();
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone(); // Object 클래스의 clone 메소드 호출
    }
    // 접근 수준 지시자를 protected에서 public으로 바꾸기 위한 오버라이딩
}

class InstanceCloning{
    public static void main(String[] args){
        Point org = new Point(3, 5);
        Point cpy;

        try{
            cpy = (Point)org.clone();
            org.showPosition();
            cpy.showPosition();
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
```

