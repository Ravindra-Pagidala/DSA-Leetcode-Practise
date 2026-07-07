1class Solution {
2    public int minOperations(int[] nums, int x) {
3        int total=0, max=-1, len = nums.length;
4        for(int c:nums){
5            total+=c;
6        }
7        if(total==x) return len;
8        if(x>total) return -1;
9        int target = total-x;
10        int i =0, windowSum=0;
11        for(int j =0; j< len ; j++) {
12            windowSum+=nums[j];
13            while(i<=j && windowSum>target){
14                windowSum-=nums[i];
15                i++;
16            }
17            if(windowSum==target)
18            max=Math.max(max, j-i+1);
19        }
20       return max==-1?max:len-max;
21    }
22}