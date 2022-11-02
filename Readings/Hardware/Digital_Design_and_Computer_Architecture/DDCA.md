# [Digital Design and Computer Architecture](https://www.amazon.com/-/ko/dp/0128200642/ref=sr_1_3?crid=I28YY3UQUY68&keywords=Digital+Design+and+Computer+Architecture&qid=1667398835&qu=eyJxc2MiOiIxLjk1IiwicXNhIjoiMS42NCIsInFzcCI6IjEuNjgifQ%3D%3D&sprefix=digital+design+and+computer+architecture%2Caps%2C264&sr=8-3)

# Combinational Logic Design

## TIMING - Propagation and Contamination Delay

Combinational logic에는 두 가지 delay가 존재한다.

propagation delay t_pd : 인풋이 변하기 시작해서 아웃풋이 마지막 값에 도달할 때까지의 최대 시간

contamination delay t_cd : 인풋이 변하기 시작해서 아웃풋의 값이 변화하기 시작할 때까지의 최소 시간

<img src="https://github.com/yunseokjeon/TodayILearned/blob/main/Readings/Hardware/Digital_Design_and_Computer_Architecture/images/2/2_67.png?raw=true">

<img src="https://github.com/yunseokjeon/TodayILearned/blob/main/Readings/Hardware/Digital_Design_and_Computer_Architecture/images/2/2_68.png?raw=true">

combinational circuit의 propagation delay는 critical path 위에 놓인 요소들의 propagation delay의 합이다.

contamination delay는 short path 위에 놓인 요소들의 contamination delay의 합이다.

## TIMING - Glitches

한개의 input transition이 multiple output transitions를 만들 수 있는 데, 이를 glitches라고 한다.

<img src="https://github.com/yunseokjeon/TodayILearned/blob/main/Readings/Hardware/Digital_Design_and_Computer_Architecture/images/2/2_76.png?raw=true">


# Sequential Logic Design

