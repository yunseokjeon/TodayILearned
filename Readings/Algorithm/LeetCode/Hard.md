[1] 42. Trapping Rain Water

https://leetcode.com/problems/trapping-rain-water/

```Java
class Solution {
    /*
    height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
    leftMax = [0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3]
    rightMax = [3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1, 0]
    현재 지점 i의 수위는 왼쪽 최대와 오른쪽 최대의 차이이다.
     */
    public int trap(int[] height) {
        int N = height.length;
        int[] leftMax = new int[N], rightMax = new int[N];

        for (int i = 1; i < N; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
        for (int i = N - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int result=0;
        for (int i = 0; i < N; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            if (waterLevel > height[i]) {
                result += waterLevel - height[i];
            }
        }
        return result;
    }
}
```

[2]

[3]

[4]

