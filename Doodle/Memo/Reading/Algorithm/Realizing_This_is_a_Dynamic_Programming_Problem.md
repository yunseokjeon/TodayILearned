## Realizing This is a Dynamic Programming Problem

이 문제가 DP로 풀 수 있음을 알려주는 두 부분이 있음.

첫째, "number of ways"에 대해 묻고 있음

둘째, 이전에 한 결정에 기반해서 현재의 결정을 해야 함. 이 문제에서 주어진 기둥에 어떤 색을 칠해야 할지를 결정해야 함. 이는 이전의 결정에 따라 달라짐. 예를 들어서 처음 두 개의 기둥에 같은 색을 칠했다면, 세 번째 기둥에 같은 색이 허용되지 않음.

이 두 가지가 DP 문제의 특징이다.

## A Framework to Solve Dynamic Programming Problems

DP는 전형적인 3개의 component를 가짐. 대부분의 DP 문제는 이 방식으로 풀 수 있기 때문에, 이들 컴퍼넌트를 배우는 것은 매우 중요함.

첫째, 주어진 상태에서 문제에 대한 답을 표현하는 함수나 배열이 필요함. 이 문제에서는 totalWays라는 함수가 있는 데, 이 함수는 i개의 기둥을 색칠하는 방법의 수를 반환함. 이는 1차원 DP 문제.

두번째, totalWays(3)과 totalWays(4)와 같이, 상태 사이를 전이하는 방식이 방법이 필요함. recurrence relation라고 불리며, DP로 문제를 해결할 때 가장 어려운 부분임.

세번째, base case를 설정해야 함. 만일 한 개의 기둥이 있다면, k개의 색칠하는 방법이 있음. 두 개의 기둥이 있다면 k * k개의 색칠하는 방법이 있음.(왜냐하면 인접한 두 개의 기둥은 같은 색으로 칠할 수 있으므로.) 따라서 totalWays(1) = k, totalWays(2) = k * k

## Finding The Recurrence Relation

totalWays(1)과 totalWays(2)의 값은 알기에, 이제 totalWays(i)를 위한 공식이 필요함. i번째 기둥을 색칠할 방법이 얼마나 많은지 생각해 보자. 두 가지 옵션이 있음.

1. 이전 기둥과 다른 색깔 사용하기. 만일 다른 색을 사용한다면, 사용할 수 있는 색은 k-1개임. (i-1) 번째 기둥과 다른 색으로 i 번째 기둥을 색칠할 수 있는 방법은 (k - 1) * totalWays(i - 1) 개 임.
2. 이전 기둥과 같은 색깔 사용하기. 오직 한 가지 색만을 사용할 수 있으며, 따라서 (i - 1)번째 기둥과 같은 색으로 i 번째 기둥을 색칠할 수 있는 방법은 1 * totalWays(i - 1) 개 임. 그러나 연속해서 세 개의 기둥을 같은 색으로 칠할 수 없다는 제약이 있음. 따라서 (i - 1)번째 기둥이 (i - 2)번째 기둥과 다른 경우에만, i 번째 기둥을 (i - 1)번째 기둥과 같은 색으로 칠할 수 있음.

그래서 (i - 2)번째 기둥과 다른 색으로 (i - 1)번째 기둥을 색칠할 수 있는 방법은 몇 가지일까? totalWays(i) = (k - 1) * ( totalWays(i - 1) + totalWays(i - 2) )


https://leetcode.com/problems/paint-fence/solution/ 