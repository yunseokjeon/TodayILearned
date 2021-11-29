1 https://leetcode.com/problems/campus-bikes-ii/ 
```Java
/*
1066. Campus Bikes II

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation: 
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
*/

class Solution {
    // Maximum number of bikes is 10
    boolean visited [] = new boolean[10];
    int smallestDistanceSum = Integer.MAX_VALUE;

    // Manhattan distance
    private int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    private void minimumDistanceSum(int[][] workers, int workerIndex, int[][] bikes, int currDistanceSum) {
        if (workerIndex >= workers.length) {
            smallestDistanceSum = Math.min(smallestDistanceSum, currDistanceSum);
            return;
        }
        // If the current distance sum is greater than the smallest result
        // found then stop exploring this combination of workers and bikes
        if (currDistanceSum >= smallestDistanceSum) {
            return;
        }
        for (int bikeIndex = 0; bikeIndex < bikes.length; bikeIndex++) {
            // If bike is available
            if (!visited[bikeIndex]) {
                visited[bikeIndex] = true;
                minimumDistanceSum(workers, workerIndex + 1, bikes,
                        currDistanceSum + findDistance(workers[workerIndex], bikes[bikeIndex]));
                visited[bikeIndex] = false;
            }
        }
    }

    public int assignBikes(int[][] workers, int[][] bikes) {
        minimumDistanceSum(workers, 0, bikes, 0);
        return smallestDistanceSum;
    }
}
```
2 

3 

4 

5 

