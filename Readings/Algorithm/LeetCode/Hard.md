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

[3] 52. N-Queens II

https://leetcode.com/problems/n-queens-ii/

```Java
class Solution {
    private int size;

    public int totalNQueens(int n) {
        size = n;
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols) {
        if (row == size) {
            return 1;
        }
        int result = 0;
        for (int col = 0; col < size; col++) {
            int currentDiagonal = row - col;
            int currentAntiDiagonal = row + col;

            if (cols.contains(col) || diagonals.contains(currentDiagonal) || antiDiagonals.contains(currentAntiDiagonal)) {
                continue;
            }

            cols.add(col);
            diagonals.add(currentDiagonal);
            antiDiagonals.add(currentAntiDiagonal);

            result += backtrack(row + 1, diagonals, antiDiagonals, cols);

            cols.remove(col);
            diagonals.remove(currentDiagonal);
            antiDiagonals.remove(currentAntiDiagonal);
        }
        return result;
    }
}
```

[4] 499. The Maze III

https://leetcode.com/problems/the-maze-iii/

```Java 
class Solution {
    class Point implements Comparable<Point> {
        int x, y, l;
        String s;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.l = Integer.MAX_VALUE;
            this.s = "";
        }

        public Point(int x, int y, int l, String s) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.s = s;
        }

        public int compareTo(Point p) {
            return l == p.l ? s.compareTo(p.s) : l - p.l;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        Point[][] points = new Point[m][n];

        for (int i = 0; i < m * n; i++)
            points[i / n][i % n] = new Point(i / n, i % n);

        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        String[] ds = new String[]{"u", "r", "d", "l"};
        PriorityQueue<Point> list = new PriorityQueue<>(); // using priority queue
        list.offer(new Point(ball[0], ball[1], 0, ""));

        while (!list.isEmpty()) {
            Point p = list.poll();
            if (points[p.x][p.y].compareTo(p) <= 0)
                continue; // if we have already found a route shorter
            points[p.x][p.y] = p;

            for (int i = 0; i < 4; i++) {
                int xx = p.x, yy = p.y, l = p.l;
                while (xx >= 0 && xx < m && yy >= 0 && yy < n && maze[xx][yy] == 0 && (xx != hole[0] || yy != hole[1])) {
                    xx += dir[i][0];
                    yy += dir[i][1];
                    l++;
                }
                if (xx != hole[0] || yy != hole[1]) { // check the hole
                    xx -= dir[i][0];
                    yy -= dir[i][1];
                    l--;
                }
                list.offer(new Point(xx, yy, l, p.s + ds[i]));
            }
        }
        return points[hole[0]][hole[1]].l == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].s;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] maze = {{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}};
        int[] ball = {4, 3};
        int[] hole = {0, 1};
        s.findShortestWay(maze, ball, hole);
    }
}
```

[5]

[6]

[7]

[8]

[9]

[10]


