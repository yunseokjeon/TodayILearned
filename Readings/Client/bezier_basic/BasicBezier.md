# Linear Bezier Curve

L0(t) = (1-t)P0 + tP1 <br>
t : varies from 0 to 1 <br>
L0 : linear interpolation <br>
P0 : start point <br>
p1 : end point <br>

<img src="https://github.com/yunseokjeon/TodayILearned/blob/main/Readings/Client/bezier_basic/g1.png?raw=true"/>

# Quadratic Bezier Curve

L0(t) = (1-t)P0 + tP1 <br>
L1(t) = (1-t)P1 + tP2 <br>
Q0(t) = (1-t)L0(t) + tL1(t) <br>

<img src="https://github.com/yunseokjeon/TodayILearned/blob/main/Readings/Client/bezier_basic/g2.png?raw=true"/>

# Cubic Bezier Curve

L0(t) = (1-t)P0 + tP1 <br>
L1(t) = (1-t)P1 + tP2 <br>
L2(t) = (1-t)P2 + tP3 <br>
Q0(t) = (1-t)L0(t) + tL1(t) <br>
Q1(t) = (1-t)L1(t) + tL2(t) <br>
C0(t) = (1-t)Q0(t) + tQ1(t) <br>

<img src="https://github.com/yunseokjeon/TodayILearned/blob/main/Readings/Client/bezier_basic/g3.png?raw=true"/>


<br><br><br>

[Drawing Bezier curves and Splines with CustomPaint flutter](https://jasper-dev.hashnode.dev/drawing-bezier-curves-and-splines-with-custompaint-flutter)
