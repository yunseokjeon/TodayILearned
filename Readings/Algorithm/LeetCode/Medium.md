[1] 2. Add Two Numbers

https://leetcode.com/problems/add-two-numbers/

```Java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null | carry != 0) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return dummy.next;
    }
}
```

[2] 3. Longest Substring Without Repeating Characters

https://leetcode.com/problems/longest-substring-without-repeating-characters/

```Java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        if (s.length() == 0) {
            return result;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
                map.put(s.charAt(i), i);
            }
            map.put(s.charAt(i), i);
            result = Math.max(result, i - j + 1);
        }
        return result;
    }
}
```

[3] 5. Longest Palindromic Substring

https://leetcode.com/problems/longest-palindromic-substring/

```Java
class Solution {
    int left = 0;
    int maxLen = 0;

    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(i, i, s);
            extendPalindrome(i, i + 1, s);
        }
        return s.substring(this.left, this.left + this.maxLen);
    }

    public void extendPalindrome(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (this.maxLen < right - left - 1) {
            this.left = left + 1;
            this.maxLen = right - left - 1;
        }
    }
}
```

[4] 6. Zigzag Conversion

https://leetcode.com/problems/zigzag-conversion/

```Java
class Solution {
    public String convert(String s, int numRows) {
        if (s.length() < numRows || numRows < 2) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int index = 0;
        int direction = 1;
        for (Character now : s.toCharArray()) {
            if (index == 0) {
                direction = 1;
            } else if (index == numRows - 1) {
                direction = -1;
            }
            rows[index].append(now);
            index += direction;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row.toString());
        }
        return result.toString();
    }
}
```

[5] 7. Reverse Integer

https://leetcode.com/problems/reverse-integer/

```Java
class Solution {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }
        return result;
    }
}
```

[6] 11. Container With Most Water

https://leetcode.com/problems/container-with-most-water/

```Java
class Solution {
    public int maxArea(int[] height) {
        int result = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int now = (right - left) * Math.min(height[left], height[right]);
            result = Math.max(result, now);
            if (height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return result;
    }
}
```

[7] 16. 3Sum Closest

https://leetcode.com/problems/3sum-closest/

```Java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int N = nums.length;
        int result = nums[0] + nums[1] + nums[N - 1];
        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            int left = i + 1, right = N - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum < target) {
                    left++;
                }else{
                    right--;
                }
            }
        }
        return result;
    }
}
```

[8] 19. Remove Nth Node From End of List

https://leetcode.com/problems/remove-nth-node-from-end-of-list/

```Java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

[9] 22. Generate Parentheses

https://leetcode.com/problems/generate-parentheses/

```Java
class Solution {

    List<String> result = new ArrayList<>();
    
    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, "", n);
        return result;
    }

    public void backtrack(int left, int right, String candidate, int max) {
        if (candidate.length() == max * 2) {
            result.add(candidate);
            return;
        }
        if (left < max) {
            backtrack(left + 1, right, candidate + "(", max);
        }
        if (right < left) {
            backtrack(left, right + 1, candidate + ")", max);
        }
    }
}
```

[10] 24. Swap Nodes in Pairs

https://leetcode.com/problems/swap-nodes-in-pairs/

```Java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        first.next = swapPairs(second.next);
        second.next = first;
        return second;
    }
}
```

[11] 36. Valid Sudoku

https://leetcode.com/problems/valid-sudoku/

```Java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int N = 9;
        Set<Character>[] rows = new Set[N];
        Set<Character>[] cols = new Set[N];
        Set<Character>[] boxes = new Set[N];

        for (int i = 0; i < N; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Character now = board[r][c];
                if (now == '.') {
                    continue;
                }
                if (rows[r].contains(now)) {
                    return false;
                }
                rows[r].add(now);
                if (cols[c].contains(now)) {
                    return false;
                }
                cols[c].add(now);
                int index = (r / 3) * 3 + (c / 3);
                if (boxes[index].contains(now)) {
                    return false;
                }
                boxes[index].add(now);
            }
        }
        return true;
    }
}
```

[12] 39. Combination Sum

https://leetcode.com/problems/combination-sum/

```Java
class Solution {
    public void backtrack(List<List<Integer>> results, LinkedList<Integer> combination,
                          int start, int remain, int[] candidates) {
        if (remain == 0) {
            results.add(new ArrayList<>(combination));
            return;
        } else if (remain < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            combination.add(candidates[i]);
            backtrack(results, combination, i, remain - candidates[i], candidates);
            combination.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(results, new LinkedList<>(), 0, target, candidates);
        return results;
    }
}
```

[13] 116. Populating Next Right Pointers in Each Node

https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

```Java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node now = queue.poll();
                if (i < size - 1) {
                    now.next = queue.peek();
                }
                if (now.left != null) {
                    queue.add(now.left);
                }
                if (now.right != null) {
                    queue.add(now.right);
                }
            }
        }
        return root;
    }
}
```

[14] 146. LRU Cache

https://leetcode.com/problems/lru-cache/

```Java
class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
```

[15] 159. Longest Substring with At Most Two Distinct Characters

https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

```Java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int N = s.length();
        if (N < 3) {
            return N;
        }
        int left = 0, right = 0, result = 2;
        Map<Character, Integer> map = new HashMap<>();
        while (right < N) {
            map.put(s.charAt(right), right++);
            if (map.size() == 3) {
                int deleteIndex = Collections.min(map.values());
                map.remove(s.charAt(deleteIndex));
                left = deleteIndex + 1;
            }
            result = Math.max(result, right - left);
        }
        return result;
    }
}
```

[16] 333. Largest BST Subtree

https://leetcode.com/problems/largest-bst-subtree/

```Java
class NodeValue {
    int minNode, maxNode, maxSize;

    public NodeValue(int minNode, int maxNode, int maxSize) {
        this.minNode = minNode;
        this.maxNode = maxNode;
        this.maxSize = maxSize;
    }
}

class Solution {
    public NodeValue helper(TreeNode root) {
        if (root == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        NodeValue left = helper(root.left);
        NodeValue right = helper(root.right);
        if (left.maxNode < root.val && root.val < right.minNode) {
            return new NodeValue(Math.min(left.minNode, root.val),
                    Math.max(root.val, right.maxNode), left.maxSize + right.maxSize + 1);
        }
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
    }

    public int largestBSTSubtree(TreeNode root) {
        return helper(root).maxSize;
    }
}
```

[17] 334. Increasing Triplet Subsequence

https://leetcode.com/problems/increasing-triplet-subsequence/

```Java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        Integer first = Integer.MAX_VALUE;
        Integer second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            }else{
                return true;
            }
        }
        return false;
    }
}
```

[18] 386. Lexicographical Numbers

https://leetcode.com/problems/lexicographical-numbers/

```Java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(i, n, results);
        }
        return results;
    }

    public void dfs(int current, int n, List<Integer> results) {
        if (current > n) {
            return;
        } else {
            results.add(current);
            for (int i = 0; i < 10; i++) {
                if (current * 10 + i <= n) {
                    dfs(current * 10 + i, n, results);
                }
            }
        }
    }
}
```

[19] 646. Maximum Length of Pair Chain

https://leetcode.com/problems/maximum-length-of-pair-chain/

```Java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int result = 0, current = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (current < pair[0]) {
                current = pair[1];
                result++;
            }
        }
        return result;
    }
}
```

[20] 659. Split Array into Consecutive Subsequences

https://leetcode.com/problems/split-array-into-consecutive-subsequences/

```Java
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), append = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            } else if (append.getOrDefault(num, 0) > 0) {
                append.put(num, append.get(num) - 1);
                append.put(num + 1, append.getOrDefault(num + 1, 0) + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                append.put(num + 3, append.getOrDefault(num + 3, 0) + 1);
            }else{
                return false;
            }
            freq.put(num, freq.get(num) - 1);
        }
        return true;
    }
}
```

[21] 776. Split BST

https://leetcode.com/problems/split-bst/

```Java
class Solution {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }
        else if (root.val <= V) {
            TreeNode[] result = splitBST(root.right, V);
            root.right = result[0];
            result[0] = root;
            return result;
        } else {
            TreeNode[] result = splitBST(root.left, V);
            root.left = result[1];
            result[1] = root;
            return result;
        }
    }
}
```

[22] 946. Validate Stack Sequences

https://leetcode.com/problems/validate-stack-sequences/

```Java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && index < N && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return index == N;
    }
}
```

[23] 1197. Minimum Knight Moves

https://leetcode.com/problems/minimum-knight-moves/

```Java
class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int dfs(int x, int y) {
        String key = x + "." + y;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (x + y == 0) {
            return 0;
        } else if (x + y == 2) {
            return 2;
        } else {
            int result = Math.min(dfs(Math.abs(x - 2), Math.abs(y - 1)), dfs(Math.abs(x - 1), Math.abs(y - 2))) + 1;
            memo.put(key, result);
            return result;
        }
    }

    public int minKnightMoves(int x, int y) {
        return this.dfs(Math.abs(x), Math.abs(y));
    }
}
```

[24] 1219. Path with Maximum Gold

https://leetcode.com/problems/path-with-maximum-gold/

```Java
class Solution {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int result = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                result = Math.max(result, findMaxGold(grid, m, n, r, c));
            }
        }
        return result;
    }

    int[] DIR = new int[]{0, 1, 0, -1, 0};

    int findMaxGold(int[][] grid, int m, int n, int r, int c) {
        if (r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0) {
            return 0;
        }
        int origin = grid[r][c];
        grid[r][c] = 0;
        int maxGold = 0;
        for (int i = 0; i < 4; i++) {
            maxGold = Math.max(maxGold, findMaxGold(grid, m, n, r + DIR[i], c + DIR[i + 1]));
        }
        grid[r][c] = origin;
        return origin + maxGold;
    }
}
```

[25] 1325. Delete Leaves With a Given Value

https://leetcode.com/problems/delete-leaves-with-a-given-value/

```Java
class Solution {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root.left != null) {
            root.left = removeLeafNodes(root.left, target);
        }
        if (root.right != null) {
            root.right = removeLeafNodes(root.right, target);
        }
        return (root.left == root.right && root.val == target) ? null : root;
    }
}
```

[26] 1404. Number of Steps to Reduce a Number in Binary Representation to One

https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/

```Java
class Solution {
    public int numSteps(String s) {
        int N = s.length();
        int result = 0, carry = 0;
        for (int i = N - 1; i >= 1; i--) {
            if (s.charAt(i) - '0' + carry == 1) {
                result += 2;
                carry = 1;
            } else {
                result++;
            }
        }
        return result + carry;
    }
}
```

[27] 1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree

https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/

```Java
class Solution {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    public boolean dfs(TreeNode node, int[] arr, int depth) {
        if (node == null || depth >= arr.length || arr[depth] != node.val) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return depth + 1 == arr.length;
        }
        return dfs(node.left, arr, depth + 1) || dfs(node.right, arr, depth + 1);
    }
}
```

[28] 1730. Shortest Path to Get Food

https://leetcode.com/problems/shortest-path-to-get-food/

```Java
class Solution {
    public int getFood(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        Queue<int[]> queue = new LinkedList<>();
        Integer[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int rowLength = grid.length;
        int colLength = grid[0].length;
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < colLength; c++) {
                if (grid[r][c] == '*') {
                    queue.add(new int[]{r, c, 0});
                    break;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int step = current[2];
            for (Integer[] direction : directions) {
                int row = current[0] + direction[0];
                int col = current[1] + direction[1];
                if (row < 0 || row >= rowLength || col < 0 || col >= colLength || grid[row][col] == 'X') {
                    continue;
                }
                if (grid[row][col] == '#') {
                    return step + 1;
                }
                grid[row][col] = 'X';
                queue.add(new int[]{row, col, step + 1});
            }
        }
        return -1;
    }
}
```

[29] 1885. Count Pairs in Two Arrays

https://leetcode.com/problems/count-pairs-in-two-arrays/

```Java
class Solution {
    public long countPairs(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = nums1[i] - nums2[i];
        }
        int left = 0, right = N - 1;
        long result = 0;
        Arrays.sort(diff);
        while (left < right) {
            if (diff[left] + diff[right] > 0) {
                result += (right - left);
                right--;
            } else {
                left++;
            }
        }
        return result;
    }
}
```

[30] 2054. Two Best Non-Overlapping Events

https://leetcode.com/problems/two-best-non-overlapping-events/

```Java
class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int result = 0, maxVal = 0;

        for (int[] event : events) {
            int start = event[0];
            while (!queue.isEmpty()) {
                if (queue.peek()[0] >= start) {
                    break;
                }
                int[] eve = queue.remove();
                maxVal = Math.max(maxVal, eve[1]);
            }
            result = Math.max(result, event[2] + maxVal);
            queue.add(new int[]{event[1], event[2]});
        }

        return result;
    }
}
```

[31] 2326. Spiral Matrix IV

https://leetcode.com/problems/spiral-matrix-iv/

```Java
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] result = new int[m][n];
        for (int[] item : result) {
            Arrays.fill(item, -1);
        }

        int y = 0, x = 0;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int curDir = 0;
        int count = 0;

        while (head != null) {
            result[y][x] = head.val;

            if (++count == m * n) {
                return result;
            }

            int[] dir = dirs[curDir];
            int nextY = y + dir[0];
            int nextX = x + dir[1];

            if (nextY < 0 || nextY >= m || nextX < 0 || nextX >= n || result[nextY][nextX] != -1) {
                curDir = (curDir + 1) % 4;
                nextY = y + dirs[curDir][0];
                nextX = x + dirs[curDir][1];
            }

            y = nextY;
            x = nextX;
            head = head.next;
        }

        return result;
    }
}
```

[32] 18. 4Sum

https://leetcode.com/problems/4sum/

```Java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return result;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        long average_value = target / k;

        // We cannot obtain a sum of target if the smallest value
        // in nums is greater than target / k or if the largest
        // value in nums is smaller than target / k.
        if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return result;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i])));
                    result.get(result.size() - 1).addAll(subset);
                }
            }
        }

        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Long> set = new HashSet<>();

        for (int i = start; i < nums.length; ++i) {
            if (result.isEmpty() || result.get(result.size() - 1).get(1) != nums[i]) {
                if (set.contains(target - nums[i])) {
                    result.add(Arrays.asList((int)target - nums[i], nums[i]));
                }
            }
            set.add((long)nums[i]);
        }

        return result;
    }
}
```

[33] 71. Simplify Path

https://leetcode.com/problems/simplify-path/

```Java
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        for (String component : components) {
            if (component.equals(".") || component.isEmpty()) {
                continue;
            } else if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }else{
                stack.push(component);
            }
        }
        StringBuilder result = new StringBuilder();
        for (String s : stack) {
            result.append("/");
            result.append(s);
        }
        return result.length() > 1 ? result.toString() : "/";
    }
}
```

[34] 1144. Decrease Elements To Make Array Zigzag

https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/

```Java
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int[] result = new int[2];
        int N = nums.length, left, right;
        for (int i = 0; i < N; i++) {
            left = 0 < i ? nums[i - 1] : 1001;
            right = i + 1 < N ? nums[i + 1] : 1001;
            result[i % 2] += Math.max(0, nums[i] - Math.min(left, right) + 1);
        }
        return Math.min(result[0], result[1]);
    }
}
```

[35] 456. 132 Pattern

https://leetcode.com/problems/132-pattern/

```Java
class Solution {
    public boolean find132pattern(int[] nums) {
        int N = nums.length;
        if (N < 3) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int[] min = new int[N];
        min[0] = nums[0];
        for (int i = 1; i < N; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        for (int j = N - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }

        return false;
    }

    /*
    nums 3 1 4 2
    min  3 1 1 1
    stack 2
    i 2 >> 2 < 4 >> return true
     */
}
```

[36] 633. Sum of Square Numbers

https://leetcode.com/problems/sum-of-square-numbers/

```Java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }
}
```

[37] 755. Pour Water

https://leetcode.com/problems/pour-water/

```Java
class Solution {
    public int[] pourWater(int[] heights, int volume, int k) {
        for (int i = 0; i < volume; i++) {
            int current = k;
            while (current > 0 && heights[current - 1] <= heights[current]) {
                current--;
            }
            while (current < heights.length - 1 && heights[current] >= heights[current + 1]) {
                current++;
            }
            while (k < current && heights[current - 1] == heights[current]) {
                current--;
            }
            heights[current]++;
        }
        return heights;
    }
}
```

[38] 1999. Smallest Greater Multiple Made of Two Digits

https://leetcode.com/problems/smallest-greater-multiple-made-of-two-digits/

```Java
class Solution {
    public int findInteger(int k, int digit1, int digit2) {
        return explore(k, digit1, digit2, 0L);
    }

    public int explore(int k, int digit1, int digit2, long x) {
        if (x > Integer.MAX_VALUE) {
            return -1;
        }
        if (x > k && x % k == 0) {
            return (int) x;
        }

        int d1 = (x + digit1 == 0) ? -1 : explore(k, digit1, digit2, 10 * x + digit1);
        int d2 = (x + digit2 == 0) ? -1 : explore(k, digit1, digit2, 10 * x + digit2);
        return d1 > 1 && d2 > 1 ? Math.min(d1, d2) : Math.max(d1, d2);
    }
}
```

[39] 2385. Amount of Time for Binary Tree to Be Infected

https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/

```Java
class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        createGraph(root, graph);
        return maxDistance(graph, new HashSet<>(), start, 0, 0);
    }

    public void createGraph(TreeNode root, Map<Integer, List<Integer>> graph) {
        List<Integer> adjacent = graph.computeIfAbsent(root.val, param -> new ArrayList<>());

        if (root.left != null) {
            graph.computeIfAbsent(root.left.val, param -> new ArrayList<>()).add(root.val);
            adjacent.add(root.left.val);
            createGraph(root.left, graph);
        }

        if (root.right != null) {
            graph.computeIfAbsent(root.right.val, param -> new ArrayList<>()).add(root.val);
            adjacent.add(root.right.val);
            createGraph(root.right, graph);
        }
    }

    public int maxDistance(Map<Integer, List<Integer>> graph, Set<Integer> visited,
                           int currentNode, int maxDistance, int currentDistance) {
        if (!visited.contains(currentNode)) {
            visited.add(currentNode);
            maxDistance = Math.max(maxDistance, currentDistance);

            for (int neighbor : graph.get(currentNode)) {
                maxDistance = Math.max(maxDistance, maxDistance(graph, visited, neighbor, maxDistance, currentDistance + 1));
            }
        }
        return maxDistance;
    }
}
```

[40] 951. Flip Equivalent Binary Trees

https://leetcode.com/problems/flip-equivalent-binary-trees/

```Java
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
    }
}
```

[41] 1423. Maximum Points You Can Obtain from Cards

https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/

```Java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] frontSetOfCards = new int[k + 1];
        int[] rearSetOfCards = new int[k + 1];

        for (int i = 0; i < k; i++) {
            frontSetOfCards[i + 1] = frontSetOfCards[i] + cardPoints[i];
            rearSetOfCards[i + 1] = rearSetOfCards[i] + cardPoints[n - i - 1];
        }

        int maxScore = 0;
        for (int i = 0; i <= k; i++) {
            int currentScore = frontSetOfCards[i] + rearSetOfCards[k - i];
            maxScore = Math.max(currentScore, maxScore);
        }
        return maxScore;
    }
}
```

[42] 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts

https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/

```Java
class Solution {
    // We will use long instead of int to prevent overflow
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // Start by sorting the inputs
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int n = horizontalCuts.length;
        int m = verticalCuts.length;

        // Consider the edges first
        long maxHeight = Math.max(horizontalCuts[0], h - horizontalCuts[n - 1]);
        for (int i = 1; i < n; i++) {
            // horizontalCuts[i] - horizontalCuts[i - 1] represents the distance between
            // two adjacent edges, and thus a possible height
            maxHeight = Math.max(maxHeight, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        // Consider the edges first
        long maxWidth = Math.max(verticalCuts[0], w - verticalCuts[m - 1]);
        for (int i = 1; i < m; i++){
            // verticalCuts[i] - verticalCuts[i - 1] represents the distance between
            // two adjacent edges, and thus a possible width
            maxWidth = Math.max(maxWidth, verticalCuts[i] - verticalCuts[i - 1]);
        }

        // Be careful of overflow, and don't forget the modulo!
        return (int) ((maxWidth * maxHeight) % (1000000007));
    }
}
```

[43] 621. Task Scheduler

https://leetcode.com/problems/task-scheduler/

```Java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        Arrays.sort(frequencies);
        int fMax = frequencies[25];
        int idleTime = (fMax - 1) * n;

        for (int i = frequencies.length - 2; i >= 0 && idleTime > 0; i--) {
            idleTime -= Math.min(fMax - 1, frequencies[i]);
        }
        idleTime = Math.max(0, idleTime);
        return idleTime + tasks.length;
    }
}
```

[44] 1698. Number of Distinct Substrings in a String

https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/

```Java
class Node {
    Node[] children = new Node[26];
}

class Solution {
    // s = "aabbaba"
    public int countDistinct(String s) {
        Node root = new Node();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Node current = root;
            for (int j = i; j < s.length(); j++) {
                if (current.children[s.charAt(j) - 'a'] == null) {
                    current.children[s.charAt(j) - 'a'] = new Node();
                    result++;
                }
                current = current.children[s.charAt(j) - 'a'];
            }
        }
        return result;
    }
}
```

[45] 1836. Remove Duplicates From an Unsorted Linked List

https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/

```Java
class Solution {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> repeated = new HashMap<>();
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode current = dummy.next;

        while (current != null) {
            repeated.put(current.val, repeated.getOrDefault(current.val, 0) + 1);
            current = current.next;
        }

        ListNode previous = dummy;
        current = dummy.next;

        while (current != null) {
            if (repeated.get(current.val) > 1) {
                previous.next = current.next;
                current.next = null;
                current = previous;
            }
            previous = current;
            current = current.next;
        }
        return dummy.next;
    }
}
```

[46] 991. Broken Calculator

https://leetcode.com/problems/broken-calculator/

```Java
class Solution {
    /*
    startValue = 2, target = 3
    2 >> 4 >> 3, 2
     */
    public int brokenCalc(int startValue, int target) {
        if (startValue >= target) {
            return startValue - target;
        }
        if (target % 2 == 0) {
            return 1 + brokenCalc(startValue, target / 2);
        }else{
            return 1 + brokenCalc(startValue, target + 1);
        }
    }
}
```

[47] 339. Nested List Weight Sum

https://leetcode.com/problems/nested-list-weight-sum/

```Java
// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


class Solution {

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int total = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                total += nested.getInteger() * depth;
            } else {
                total += dfs(nested.getList(), depth + 1);
            }
        }
        return total;
    }
}
```

[48] 2080. Range Frequency Queries

https://leetcode.com/problems/range-frequency-queries/

```Java
/*
[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]
33 -> TreeMap 1 : 0
33 -> TreeMap 7 : 1

[0, 11, 33]
33 -> TreeMap 1:0, 7:1

TreeMap.ceilingKey()  : The least key greater than or equal to the key passed as an argument or null
TreeMap.floorKey() :  The greatest key less than or equal to key, or null
 */
class RangeFreqQuery {
    Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new TreeMap<>());
            map.get(arr[i]).put(i, map.get(arr[i]).size());
        }
    }

    public int query(int left, int right, int value) {
        if (!map.containsKey(value)) {
            return 0;
        }
        TreeMap<Integer, Integer> treeMap = map.get(value);
        Integer first = treeMap.ceilingKey(left);
        Integer second = treeMap.floorKey(right);
        if (first == null || second == null) {
            return 0;
        }
        return treeMap.get(second) - treeMap.get(first) + 1;
    }
}
```

[49] 1447. Simplified Fractions

https://leetcode.com/problems/simplified-fractions/

```Java
/*
n = 3
0 1 1 1
0 1 1 2
0 1 1 3
1 3 1 2
1 2 1 1 -> 2 / 3
1 2 2 3
2 3 1 1
 */

class Solution {
    public List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        dfs(0, 1, 1, 1, n, result);
        return result;

    }

    public void dfs(int leftNumerator, int leftDenominator, int rightNumerator, int rightDenominator, int n, List<String> result) {
        if (leftDenominator + rightDenominator > n) {
            return;
        }
        int numerator = leftNumerator + rightNumerator;
        int denominator = leftDenominator + rightDenominator;
        result.add(numerator + "/" + denominator);
        dfs(leftNumerator, leftDenominator, numerator, denominator, n, result);
        dfs(numerator, denominator, rightNumerator, rightDenominator, n, result);
    }
}
```

[50] 939. Minimum Area Rectangle

https://leetcode.com/problems/minimum-area-rectangle/

```Java
/*
points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
map : 1 -> 1, 3
      2 -> 2
      3 -> 1, 3

x1 = 1, y1 = 1, x2 = 3, y2 = 3
x1 = 1, y1 = 3, x2 = 3, y2 = 1
x1 = 3, y1 = 1, x2 = 1, y2 = 3
x1 = 3, y1 = 3, x2 = 1, y2 = 1

 */
class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }

        int minimumArea = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 != x2 && y1 != y2) {
                    if (map.get(x1).contains(y2) && map.get(x2).contains(y1)) {
                        minimumArea = Math.min(minimumArea, Math.abs(x1 - x2) * Math.abs(y1 - y2));
                    }
                }
            }
        }
        return minimumArea != Integer.MAX_VALUE ? minimumArea : 0;
    }
}
```

[51] 1535. Find the Winner of an Array Game

https://leetcode.com/problems/find-the-winner-of-an-array-game/

```Java
class Solution {
    public int getWinner(int[] arr, int k) {
        int current = arr[0], count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (current < arr[i]) {
                current = arr[i];
                count = 0;
            }
            if (++count == k) {
                break;
            }
        }
        return current;
    }
}
```

[52] 2428. Maximum Sum of an Hourglass

https://leetcode.com/problems/maximum-sum-of-an-hourglass/

```Java
class Solution {
    public int maxSum(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[0].length - 2; j++) {
                int sum = grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i + 1][j + 1]
                        + grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2];
                result = Math.max(result, sum);
            }
        }
        return result;
    }
}
```

[53] 723. Candy Crush

https://leetcode.com/problems/candy-crush/

```Java
class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] board = new int[][]{
                {110, 5, 112, 113, 114},
                {210, 211, 5, 213, 214},
                {310, 311, 3, 313, 314},
                {410, 411, 412, 5, 414},
                {5, 1, 512, 3, 3},
                {610, 4, 1, 613, 614},
                {710, 1, 2, 713, 714},
                {810, 1, 2, 1, 1},
                {1, 1, 2, 2, 2},
                {4, 1, 4, 4, 1014}
        };
        s.candyCrush(board);
    }

    public int[][] candyCrush(int[][] board) {
        int R = board.length, C = board[0].length;
        boolean todo = false;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c + 2 < C; c++) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r][c + 1]) && v == Math.abs(board[r][c + 2])) {
                    board[r][c] = board[r][c + 1] = board[r][c + 2] = -v;
                    todo = true;
                }
            }
        }

        for (int r = 0; r + 2 < R; r++) {
            for (int c = 0; c < C; c++) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r + 1][c]) && v == Math.abs(board[r + 2][c])){
                    board[r][c] = board[r + 1][c] = board[r + 2][c] = -v;
                    todo = true;
                }
            }
        }

        for (int c = 0; c < C; c++) {
            int wr = R - 1;
            for (int r = R - 1; r >= 0; r--) {
                if (board[r][c] > 0) {
                    board[wr--][c] = board[r][c];
                }
            }
            while (wr >= 0) {
                board[wr--][c] = 0;
            }
        }

        return todo ? candyCrush(board) : board;
    }
}
```

[54]

[55]