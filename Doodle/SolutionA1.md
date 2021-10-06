
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