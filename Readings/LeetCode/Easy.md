[1] 1. Two Sum

https://leetcode.com/problems/two-sum/

```Java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
```

```C++
class Solution {
public:
    vector<int> twoSum(vector<int> &nums, int target) {
        unordered_map<int, int> hash;
        vector<int> result;

        for (int i = 0; i < nums.size(); i++) {
            int numberToFind = target - nums[i];
            if (hash.find(numberToFind) != hash.end()) {
                result.push_back(hash[numberToFind]);
                result.push_back(i);
                return result;
            }
            hash[nums[i]] = i;
        }
        return result;
    }
};
```

```Python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        hashmap = {}
        for i in range(len(nums)):
            complement = target - nums[i]
            if complement in hashmap:
                return [i, hashmap[complement]]
            hashmap[nums[i]] = i
```

[2] 270. Closest Binary Search Tree Value

https://leetcode.com/problems/closest-binary-search-tree-value/

```Java
class Solution {
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (Objects.isNull(kid)) {
            return a;
        }
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}
```

```C++
class Solution {
public:
    int closestValue(TreeNode *root, double target) {
        int a = root->val;
        auto kid = target < a ? root->left : root->right;
        if (!kid) {
            return a;
        }
        int b = closestValue(kid, target);
        return abs(a - target) < abs(b - target) ? a : b;
    }
};
```

```Python
class Solution:
    def closestValue(self, root, target):
        a = root.val
        kid = root.left if target < a else root.right
        if not kid: return a
        b = self.closestValue(kid, target)
        return min((b, a), key=lambda x: abs(target - x))

```

[3] 9. Palindrome Number

https://leetcode.com/problems/palindrome-number/

```Java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        // 12 and 1 >> 1 == 12 / 10
        return x == reverse || x == reverse / 10;
    }
}
```

[4] 202. Happy Number

https://leetcode.com/problems/happy-number/

```Java
class Solution {
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n /= 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}
```

[5] 13. Roman to Integer

https://leetcode.com/problems/roman-to-integer/


```Java
class Solution {

    static Map<String, Integer> values = new HashMap<>();

    static {
        values.put("M", 1000);
        values.put("D", 500);
        values.put("C", 100);
        values.put("L", 50);
        values.put("X", 10);
        values.put("V", 5);
        values.put("I", 1);
    }

    public int romanToInt(String s) {
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            String current = s.substring(i, i + 1);
            int currentNumber = values.get(current);
            int nextNumber = 0;
            if (i + 1 < s.length()) {
                String next = s.substring(i + 1, i + 2);
                nextNumber = values.get(next);
            }

            if (currentNumber < nextNumber) {
                sum += (nextNumber - currentNumber);
                i += 2;
            }else{
                sum += currentNumber;
                i++;
            }
        }
        return sum;
    }
}
```

[6] 1694. Reformat Phone Number

https://leetcode.com/problems/reformat-phone-number/

```Java
class Solution {
    public String reformatNumber(String number) {
        Queue<Character> queue = new LinkedList<>();
        for (Character now : number.toCharArray()) {
            if (Character.isDigit(now)) {
                queue.offer(now);
            }
        }

        StringBuilder result = new StringBuilder();
        while (queue.size() > 4) {
            result.append(queue.poll()).append(queue.poll()).append(queue.poll()).append('-');
        }

        if (queue.size() == 4) {
            result.append(queue.poll()).append(queue.poll()).append('-');
        }

        while (!queue.isEmpty()) {
            result.append(queue.poll());
        }

        return result.toString();
    }
}
```

[7] 2315. Count Asterisks

https://leetcode.com/problems/count-asterisks/

```Java
// s = "l|*e*et|c**o|*de|"
class Solution {
    public int countAsterisks(String s) {
        int result = 0, bars = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*' && bars % 2 == 0) {
                result++;
            }
            if (s.charAt(i) == '|') {
                bars++;
            }
        }
        return result;
    }
}
```

[8] 1137. N-th Tribonacci Number

https://leetcode.com/problems/n-th-tribonacci-number/

```Java
class Solution {
    public int tribonacci(int n) {
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }
        int temporary, x = 0, y = 1, z = 1;
        for (int i = 3; i <= n; i++) {
            temporary = x + y + z;
            x = y;
            y = z;
            z = temporary;
        }
        return z;
    }
}
```

[9] 2210. Count Hills and Valleys in an Array

https://leetcode.com/problems/count-hills-and-valleys-in-an-array/

```Java
class Solution {
    public int countHillValley(int[] nums) {
        int result = 0, left = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (left < nums[i] && nums[i] > nums[i + 1] || left > nums[i] && nums[i] < nums[i + 1]) {
                result++;
                left = nums[i];
            }
        }
        return result;
    }
}
```

[10] 594. Longest Harmonious Subsequence

https://leetcode.com/problems/longest-harmonious-subsequence/

```Java
class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (Integer key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                result = Math.max(result, map.get(key) + map.get(key + 1));
            }
        }
        return result;
    }
}
```

[11]