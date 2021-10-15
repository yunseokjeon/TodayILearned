
```Java
/*
https://leetcode.com/problems/add-two-numbers/

1. Add Two Numbers

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
*/

// 문제
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while ( _____ || _____ ) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = x + y + carry;
            _____ = sum / 10;
            _____ = new ListNode(sum % 10);
            curr = curr.next;
            if (_____) p = p.next;
            if (_____) q = q.next;
        }
        if (carry > 0) {
            _____ = new ListNode(carry);
        }

        return dummyHead.next;
    }

}

// 솔루션
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

}

```

```Java
/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/

3. Longest Substring Without Repeating Characters

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
*/

// 문제
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            if (_____) {
                set.add(_____);
                max = _____
            } else {
                _____
            }
        }
        return max;
    }
}

// 솔루션
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }
}
```

```Java
/*
https://leetcode.com/problems/longest-palindromic-substring/

5. Longest Palindromic Substring

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.

*/

// 문제
class Solution {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (_____)
            return s;
        for (int i = 0; i < len; i++) {
            _____
            _____
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (_____) {
            j--;
            k++;
        }
        if (_____) {
            lo = j + 1;
            maxLen = _____
        }
    }

}

// 솔루션
class Solution {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        for (int i = 0; i < len; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

}
```

```Java
/*
https://leetcode.com/problems/zigzag-conversion/

6. ZigZag Conversion

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
*/

// 문제
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < _____ ; i++){
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingdown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (_____) {
                goingdown = !goingdown;
            }
            curRow += _____
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows)
            ret.append(row);
        return ret.toString();
    }
}

// 솔루션
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++){
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingdown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingdown = !goingdown;
            }
            curRow += goingdown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows)
            ret.append(row);
        return ret.toString();
    }
}
```

```Java
/*
https://leetcode.com/problems/string-to-integer-atoi/

8. String to Integer (atoi)

Input: s = "4193 with words"
Output: 4193
Explanation:
Step 1: "4193 with words" (no characters read because there is no leading whitespace)
         ^
Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
             ^
The parsed integer is 4193.
Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
*/
// 문제
class Solution {
    public int myAtoi(String str) {
        int sign = 1, i = 0, r = 0;
        str = _____
        if (str.isEmpty()) 
            return 0;
        else if (_____) {
            _____
            i++;
        } else if (_____) {
            i++;
        }
        while (i < str.length() && _____ ) {
            int d = ______
            if (_____)
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            _______
            i++;
        }
        return ______
    }
}

// 솔루션
class Solution {
    public int myAtoi(String str) {
        int sign = 1, i = 0, r = 0;
        str = str.trim();
        if (str.isEmpty()) return 0;
        else if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int d = str.charAt(i) - '0';
            if (r > (Integer.MAX_VALUE - d) / 10)
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            r = r * 10 + d;
            i++;
        }
        return r * sign;
    }
}
```

```Java
/*
https://leetcode.com/problems/container-with-most-water/

11. Container With Most Water

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
*/

// 문제
class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = _____
            if (______)
                l++;
            else
                r--;
        }
        return maxarea;
    }
}

// 솔루션
class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
```

```Java
/*
https://leetcode.com/problems/3sum/

15. 3Sum

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
*/

// 문제
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && _____; i++) {
            if (_____)
                twoSum(nums, i, res);
        }
        return res;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<>();
        for (int j = _____; j < nums.length; j++) {
            int complement = _____
            if (_____) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (_____)
                    j++;
            }
            _____
        }
    }
}

// 솔루션
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i])
                twoSum(nums, i, res);
        }
        return res;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<>();
        for (int j = i + 1; j < nums.length; j++) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    j++;
            }
            seen.add(nums[j]);
        }
    }
}
```

```Java
/*
https://leetcode.com/problems/3sum-closest/

16. 3Sum Closest

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
// 문제
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = _____
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && _____; i++) {
            int lo = _____
            int hi = _____
            while (lo < hi) {
                int sum = ______
                if (______) {
                    diff = _____
                }
                if (_____) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return _______
    }
}

// 솔루션
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; i++) {
            int lo = i + 1;
            int hi = sz - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return target - diff;
    }
}
```

```Java
/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/

17. Letter Combinations of a Phone Number

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
*/

// 문제
class Solution {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return combinations;
        phoneDigits = digits;
        _____
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (_____) {
            combinations.add(path.toString());
            return;
        }
        String possibleLetters = letters.get(_____);
        for (char letter : possibleLetters.toCharArray()) {
            ______
            ______
            ______
        }
    }
}

// 솔루션
class Solution {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return combinations;
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return;
        }
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            path.append(letter);
            backtrack(index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
```

```Java
/*
https://leetcode.com/problems/4sum/

18. 4Sum

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
*/

// 문제
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (_____)
            return res;

        if (k == 2)
            return twoSum(nums, target, start);

        for (int i = start; i < nums.length; i++) {
            if (_____) {
                for (_____) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1)._____;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (res.isEmpty() || _____ != nums[i]) {
                if (_____) {
                    res.add(Arrays.asList(target - nums[i], nums[i]));
                }
            }
            s.add(nums[i]);
        }
        return res;
    }
}

// 솔루션
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || target < nums[start] * k || target > nums[nums.length - 1] * k)
            return res;

        if (k == 2)
            return twoSum(nums, target, start);

        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) {
                if (s.contains(target - nums[i])) {
                    res.add(Arrays.asList(target - nums[i], nums[i]));
                }
            }
            s.add(nums[i]);
        }
        return res;
    }
}
```

```Java
/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/

19. Remove Nth Node From End of List

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// 문제
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (_____) {
            _____
            first = first.next;
        }
        _____
        first = dummy;
        while (_____) {
            _____
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}

// 솔루션
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}

```

```Java
/*
https://leetcode.com/problems/generate-parentheses/

22. Generate Parentheses

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
*/

// 문제
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        _____
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (_____) {
            ans.add(cur.toString());
            return;
        }
        if (_____) {
            cur.append("(");
            _____
            cur.deleteCharAt(cur.length() - 1);
        }
        if (_____) {
            cur.append(")");
            _____
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}

// 솔루션
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
```
```Java
/*
https://leetcode.com/problems/swap-nodes-in-pairs/

24. Swap Nodes in Pairs

Input: head = [1,2,3,4]
Output: [2,1,4,3]
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// 문제
class Solution {
    public ListNode swapPairs(ListNode head) {

        if ( _____ ) {
            return head;
        }

        ListNode firstNode = _____
        ListNode secondNode = _____

        _____
        _____

        return secondNode;
    }
}

// 솔루션
class Solution {
    public ListNode swapPairs(ListNode head) {

        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }
}
```

```Java
/*
https://leetcode.com/problems/next-permutation/

31. Next Permutation

Input: nums = [1,2,3]
Output: [1,3,2]
*/

// 문제
class Solution {
    public void nextPermutation(int[] nums) {
        int i = _____
        while (_____ && _____) {
            i--;
        }
        if (_____) {
            int j = _____
            while (_____) {
                j--;
            }
            _____
        }
        _____
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 솔루션
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

```Java
/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

34. Find First and Last Position of Element in Sorted Array

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
*/

// 문제
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = _____
        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }
        int lastOccurrence = _____
        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (_____) {
            int mid = (begin + end) / 2;
            if (_____) {
                if (_____) {
                    
                    if (_____ || _____) {
                        return mid;
                    }
                    
                    end = mid - 1;
                } else {
                    
                    if (_____ || _____) {
                        return mid;
                    }
                    
                    begin = mid + 1;
                }
            } else if (_____) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return _____
    }
}

// 솔루션
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = this.findBound(nums, target, true);
        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }
        int lastOccurrence = this.findBound(nums, target, false);
        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                if (isFirst) {
                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }
                    // Search on the left side for the bound.
                    end = mid - 1;
                } else {
                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }
                    // Search on the right side for the bound.
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }
}
```

```Java
/*
https://leetcode.com/problems/valid-sudoku/

36. Valid Sudoku

Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
*/

// 문제
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        HashSet<Character>[] rows = _____
        HashSet<Character>[] cols = _____
        HashSet<Character>[] boxes = _____
        for (int r = 0; _____; r++) {
            rows[r] = _____
            cols[r] = _____
            boxes[r] = _____
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];

                if (_____) {
                    continue;
                }

                if (_____) {
                    return false;
                }
                _____

                if (_____) {
                    return false;
                }
                _____

                int idx = _____
                if (_____) {
                    return false;
                }
                _____
            }
        }
        return true;
    }
}

// 솔루션
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        // Use hash set to record the status
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];

                // Check if the position is filled with number
                if (val == '.') {
                    continue;
                }

                // Check the row
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);

                // Check the column
                if (cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }
        return true;
    }
}
```

```Java
/*
https://leetcode.com/problems/count-and-say/

38. Count and Say

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"

https://leetcode.com/problems/count-and-say/discuss/15995/Examples-of-nth-sequence

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221 
 6.     312211
 7.     13112221
 8.     1113213211
 9.     31131211131221
 10.   13211311123113112211
*/

// 문제 
class Solution {
    public String countAndSay(int n) {
        String s = _____
        for (int i = _____; _____; i++) {
            s = _____
        }
        return s;
    }

    public String countIdx(String s) {
        StringBuilder sb = new StringBuilder();
        char c = _____
        int count = _____
        for (int i = _____; _____; i++) {
            if (_____) {
                count++;
            } else {
                sb.append(_____);
                sb.append(_____);
                c = _____
                count = _____
            }
        }
        sb.append(_____);
        sb.append(_____);
        return sb.toString();
    }
}

// 솔루션
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            s = countIdx(s);
        }
        return s;
    }

    public String countIdx(String s) {
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}
```

```Java
/*
https://leetcode.com/problems/combination-sum/

39. Combination Sum

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
*/

// 문제
class Solution {

    protected void backtrack(
            int remain,
            LinkedList<Integer> comb,
            int start, int[] candidates,
            List<List<Integer>> results) {

        if (_____) {
            results.add(_____);
            return;
        } else if (_____) {
            return;
        }

        for (int i = _____; i < candidates.length; i++) {
            comb.add(_____);
            this.backtrack(_____, comb, i, candidates, results);
            comb._____
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        this.backtrack(target, comb, _____, candidates, results);
        return results;
    }
}

// 솔루션
class Solution {

    protected void backtrack(
            int remain,
            LinkedList<Integer> comb,
            int start, int[] candidates,
            List<List<Integer>> results) {

        if (remain == 0) {
            // make a deep copy of the current combination
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (remain < 0) {
            // exceed the scope, stop exploration.
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // add the number into the combination
            comb.add(candidates[i]);
            this.backtrack(remain - candidates[i], comb, i, candidates, results);
            // backtrack, remove the number from the combination
            comb.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        this.backtrack(target, comb, 0, candidates, results);
        return results;
    }
}
```

```Java
/*
https://leetcode.com/problems/combination-sum-ii/

40. Combination Sum II

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
*/

// 문제
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (int candidate : candidates) {
            if (counter._____)
                counter.put(candidate, _____);
            else
                counter.put(candidate, _____);
        }

        List<int[]> counterList = new ArrayList<>();
        counter._____((key, value) -> {
            counterList.add(new int[]{key, value});
        });

        backtrack(comb, target, _____, counterList, results);
        return results;
    }

    private void backtrack(LinkedList<Integer> comb,
                           int remain, int curr,
                           List<int[]> counter,
                           List<List<Integer>> results) {

        if (_____) {
            if (_____) {
                results.add(new ArrayList<Integer>(comb));
            }
            return;
        }

        for (int nextCurr = _____; nextCurr < counter.size(); ++nextCurr) {
            int[] entry = counter.get(_____);
            Integer candidate = _____, freq = _____

            if (_____)
                continue;

            comb.addLast(_____);
            counter.set(_____, new int[]{_____, _____});

            backtrack(comb, _____, nextCurr, counter, results);

            counter.set(_____, new int[]{_____, _____});
            comb._____
        }
    }
}


// 솔루션
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // container to hold the final combinations
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            if (counter.containsKey(candidate))
                counter.put(candidate, counter.get(candidate) + 1);
            else
                counter.put(candidate, 1);
        }

        // convert the counter table to a list of (num, count) tuples
        List<int[]> counterList = new ArrayList<>();
        counter.forEach((key, value) -> {
            counterList.add(new int[]{key, value});
        });

        backtrack(comb, target, 0, counterList, results);
        return results;
    }

    private void backtrack(LinkedList<Integer> comb,
                           int remain, int curr,
                           List<int[]> counter,
                           List<List<Integer>> results) {

        if (remain <= 0) {
            if (remain == 0) {
                // make a deep copy of the current combination.
                results.add(new ArrayList<Integer>(comb));
            }
            return;
        }

        for (int nextCurr = curr; nextCurr < counter.size(); ++nextCurr) {
            int[] entry = counter.get(nextCurr);
            Integer candidate = entry[0], freq = entry[1];

            if (freq <= 0)
                continue;

            // add a new element to the current combination
            comb.addLast(candidate);
            counter.set(nextCurr, new int[]{candidate, freq - 1});

            // continue the exploration with the updated combination
            backtrack(comb, remain - candidate, nextCurr, counter, results);

            // backtrack the changes, so that we can try another candidate
            counter.set(nextCurr, new int[]{candidate, freq});
            comb.removeLast();
        }
    }
}
```
```Java
/*
https://leetcode.com/problems/jump-game-ii/

45. Jump Game II

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
*/

// 문제
class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < _____; i++) {     
            farthest = Math.max(farthest, _____);
            if (i == currentJumpEnd) {
                _____
                currentJumpEnd = _____
            }
        }
        return jumps;
    }
}

// 솔루션
class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }
}
```

```Java
/*
https://leetcode.com/problems/permutations/

46. Permutations

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*/

// 문제
class Solution {
    public void backtrack(int n,
                          ArrayList<Integer> nums,
                          List<List<Integer>> output,
                          int first) {
        
        if (_____)
            output.add(new ArrayList<Integer>(nums));
        for (int i = _____; i < n; i++) {
            Collections.swap(nums, _____, _____);
            backtrack(n, nums, output, _____);
            Collections.swap(nums, _____, _____);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new LinkedList();
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, _____);
        return output;
    }
}


// 솔루션
class Solution {
    public void backtrack(int n,
                          ArrayList<Integer> nums,
                          List<List<Integer>> output,
                          int first) {
        // if all integers are used up
        if (first == n)
            output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }
}

```

```Java
/*
https://leetcode.com/problems/permutations-ii/

47. Permutations II

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
*/

// 문제
class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (int num : nums) {
            if (!counter._____)
                counter.put(num, _____);
            counter.put(num, _____);
        }

        LinkedList<Integer> comb = new LinkedList<>();
        this.backtrack(comb, nums.length, counter, results);
        return results;
    }

    protected void backtrack(
            LinkedList<Integer> comb,
            Integer N,
            HashMap<Integer, Integer> counter,
            List<List<Integer>> results) {

        if (_____) {
            results.add(_____(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry._____
            Integer count = entry._____
            if (_____)
                continue;

            comb.addLast(_____);
            counter.put(num, _____);

            backtrack(comb, N, counter, results);

            comb._____
            counter._____
        }
    }
}


// 솔루션
class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        // count the occurrence of each number
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num))
                counter.put(num, 0);
            counter.put(num, counter.get(num) + 1);
        }

        LinkedList<Integer> comb = new LinkedList<>();
        this.backtrack(comb, nums.length, counter, results);
        return results;
    }

    protected void backtrack(
            LinkedList<Integer> comb,
            Integer N,
            HashMap<Integer, Integer> counter,
            List<List<Integer>> results) {

        if (comb.size() == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0)
                continue;
            // add this number into the current combination
            comb.addLast(num);
            counter.put(num, count - 1);

            // continue the exploration
            backtrack(comb, N, counter, results);

            // revert the choice for the next exploration
            comb.removeLast();
            counter.put(num, count);
        }
    }
}

```

```Java
/*
https://leetcode.com/problems/rotate-image/

48. Rotate Image

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
*/

// 문제
class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < _____; i++) {
            for (int j = _____; j < _____; j++) {
                int tmp = matrix[_][_];
                matrix[_][_] = matrix[_][_];
                matrix[_][_] = tmp;
            }
        }
    }

    public void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < _____; i++) {
            for (int j = 0; j < _____; j++) {
                int tmp = matrix[_][_];
                matrix[_][_] = matrix[_][_];
                matrix[_][_] = tmp;
            }
        }
    }
}

// 솔루션
class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    public void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
}
```

```Java
/*
https://leetcode.com/problems/group-anagrams/

49. Group Anagrams

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/

// 문제
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (_____)
            return new ArrayList();
        Map<String, List> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s._____
            Arrays._____
            String key = String._____
            if (!ans._____)
                ans.put(_____, new ArrayList());
            ans.get(_____).add(_____);
        }
        return new ArrayList(ans._____);
    }
}

// 솔루션
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0)
            return new ArrayList();
        Map<String, List> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
```

```Java
/*
https://leetcode.com/problems/powx-n/

50. Pow(x, n)

Input: x = 2.00000, n = 10
Output: 1024.00000
*/

// 문제
class Solution {
    private double fastPow(double x, long n) {
        if (_____) {
            return 1.0;
        }
        double half = fastPow(x, _____);
        if (_____) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = _____
            N = _____
        }
        return fastPow(x, N);
    }
}

// 솔루션
class Solution {
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }
}
```

```Java
/*
https://leetcode.com/problems/spiral-matrix/

54. Spiral Matrix

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
*/

// 문제
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int up = _____
        int left = _____
        int right = _____
        int down = _____

        while (result.size() < _____) {
           
            for (int col = _____; col _____ right; col++) {
                result.add(matrix[up][col]);
            }
            
            for (int row = _____; row _____ down; row++) {
                result.add(matrix[row][right]);
            }
            
            if (up _____ down) {
                
                for (int col = _____; col _____ left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            
            if (left _____ right) {
                
                for (int row = _____; row _____ up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }
}


// 솔루션
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = columns - 1;
        int down = rows - 1;

        while (result.size() < rows * columns) {
            // Traverse from left to right.
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
            // Traverse downwards.
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
            // Make sure we are now on a different row.
            if (up != down) {
                // Traverse from right to left.
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            // Make sure we are now on a different column.
            if (left != right) {
                // Traverse upwards.
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }
}

```

```Java
/*
https://leetcode.com/problems/jump-game/

55. Jump Game

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
*/

// 문제
class Solution {
    public boolean canJump(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (_____) {
                return false;
            }
            max = Math.max(_____, max);
        }
        return true;
    }
}

// 솔루션
class Solution {
    public boolean canJump(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(A[i] + i, max);
        }
        return true;
    }
}
```

```Java
/*

*/

// 문제

// 솔루션
```

```Java
/*

*/

// 문제

// 솔루션
```

```Java
/*

*/

// 문제

// 솔루션
```

```Java
/*

*/

// 문제

// 솔루션
```


