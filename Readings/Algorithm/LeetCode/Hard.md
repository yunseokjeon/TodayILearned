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

[2] 51. N-Queens

https://leetcode.com/problems/n-queens/

```Java
class Solution {

    private int size;
    private List<List<String>> solutions = new ArrayList<List<String>>();

    public List<List<String>> solveNQueens(int n) {
        size = n;
        char[][] emptyBoard = new char[size][size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                emptyBoard[i][j] = '.';
            }
        }
        backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), emptyBoard);
        return solutions;
    }

    private List<String> createBoard(char[][] state) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            String currentRow = new String(state[row]);
            board.add(currentRow);
        }
        return board;
    }

    private void backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols, char[][] state) {
        if (row == size) {
            solutions.add(createBoard(state));
            return;
        }

        for (int col = 0; col < size; col++) {
            int currentDiagonal = row - col;
            int currentAntiDiagonal = row + col;

            if (cols.contains(col) || diagonals.contains(currentDiagonal) || antiDiagonals.contains(currentAntiDiagonal)) {
                continue;
            }

            cols.add(col);
            diagonals.add(currentDiagonal);
            antiDiagonals.add(currentAntiDiagonal);
            state[row][col] = 'Q';

            backtrack(row + 1, diagonals, antiDiagonals, cols, state);

            cols.remove(col);
            diagonals.remove(currentDiagonal);
            antiDiagonals.remove(currentAntiDiagonal);
            state[row][col] = '.';
        }
    }
}
```

[3]

[4]

