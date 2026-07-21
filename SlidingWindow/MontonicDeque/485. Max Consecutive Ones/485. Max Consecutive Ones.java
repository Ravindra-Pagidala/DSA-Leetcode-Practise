class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int i, count = 0;
        for (i = 0; i < nums.length; i++)
        {
            int c = 0;
            while (nums[i] == 1)
            {
                ++c;
                ++i;
                if (i == nums.length)
                    break;
            }
            if (c > count)
                count = c;
        }
        return count;
    }
}