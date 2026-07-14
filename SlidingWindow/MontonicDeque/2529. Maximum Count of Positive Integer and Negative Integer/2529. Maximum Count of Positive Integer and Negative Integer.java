1class Solution {
2    public int maximumCount(int[] nums) {
3    if(nums.length==1 && nums[0]!=0) return 1;
4
5        int len = nums.length;
6       int positive = len-minBs(nums); 
7       int negative = maxBs(nums)!=0? maxBs(nums)+1:0;
8       int max = Math.max(positive, negative);
9       return max;
10    }
11
12    public int minBs(int nums[]){
13
14    int start = 0, end = nums.length,mid =0;
15     while(start < end) {
16        mid = start+(end-start)/2;
17        if(nums[mid]>=1){
18            end = mid;
19        }
20        else {
21            start = mid+1;
22        }
23     } 
24      return start;
25    }
26
27    public int maxBs(int nums[]) {
28    int start = 0, end = nums.length-1,mid =0;
29     while(start < end) {
30        mid = start+(end-start+1)/2;
31        if(nums[mid]<=-1){
32            start = mid;
33        }
34        else {
35         end = mid-1;
36        }
37     } 
38      return start;
39
40    }
41}