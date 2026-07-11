1class NumArray {
2   
3    int nums[];
4    int prefixSum[];
5
6    public NumArray(int[] nums) {
7        this.nums = nums;
8        prefixSum = new int[nums.length+1];
9        prefixSum[0] = 0;
10
11       for(int i =1; i<prefixSum.length; i++) {
12            prefixSum[i]= prefixSum[i-1]+nums[i-1];
13        }
14    }
15    
16    public int sumRange(int left, int right) {
17       return prefixSum[right+1]-prefixSum[left];
18    }
19}
20
21/**
22 * Your NumArray object will be instantiated and called as such:
23 * NumArray obj = new NumArray(nums);
24 * int param_1 = obj.sumRange(left,right);
25 */