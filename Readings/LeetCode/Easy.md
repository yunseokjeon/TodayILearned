[1] 1. Two Sum

https://leetcode.com/problems/two-sum/

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

[3]