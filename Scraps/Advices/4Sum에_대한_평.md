https://leetcode.com/problems/4sum/ 에 대한 평.

HSM : 음.. 많이 알면 좋긴 한데, 이 정도는 좀 제끼고 보는 것 같아. 그냥 안 나오길 바라는 그런 배짱? 시험 전에 벼락치기로 보는 리스트에 등록해 두고 그 리스트 훑을 때 암기하는 것 같아.

```Java
class Solution {
    int len;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        this.len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    public List<List<Integer>> kSum(int[] nums, int tarket, int k, int start) {

        List<List<Integer>> results = new ArrayList<>();
        if (start >= len) {
            return results;
        }
        if (k == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < tarket) {
                    left++;
                } else if (sum > tarket) {
                    right--;
                }
                if (sum == tarket) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    results.add(new ArrayList<>(temp));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        } else {
            for (int i = start; i < this.len; i++) {

                for (List<Integer> candidate : kSum(nums, tarket - nums[i], k - 1, i + 1)) {
                    candidate.add(nums[i]);
                    results.add(new ArrayList<>(candidate));
                }
                while (i < len - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }

            }
        }
        return results;
    }
}

/*
엣지 케이스 : 

[1000000000,1000000000,1000000000,1000000000]
-294967296

HSM : 
앗 요런 경우는 나같으면 그냥 int 안쓰고 long으로 해버려..
sum에 관련된 애들만 long으로 바꿔버리면 뚝딱 해결됩니다

타입을 하나 상위타입으로 바꾸는게 나쁘지 않아
알고리즘에 문제는 없지만 타입 자체의 성능에 제한이 걸린거니까 그냥 그 제한을 푸는 방향이니깐
만약 실제 면접에선 int로 짜도 99%의 경우 상관없을텐데, 면접관이 이 엣지케이스를 신경쓰는 경우가 있는 경우 직접 대놓고 물어볼거야. 예를들면 “아주 큰 수가 들어오는 경우에도 동작할까요?” 라는 식으로. 그러면 입력이 int라고 고정되었으니, sum변수만 long으로 타입을 바꿔주면 어지간한 케이스에는 동작할거라 생각합니다 라고 하면 뚝딱!

문제 내에서 궁금한 거나 아님 다른 물어보고싶은 거 있으시면 언제든 알려줘~~~~
*/
```