class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0, tmp; i < nums.length; ++i) {
            tmp = 1;
            for (int idx = i; nums[idx] != idx; ++tmp, swap(nums, idx, nums[idx]))
                ;
            res = Math.max(res, tmp);
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}