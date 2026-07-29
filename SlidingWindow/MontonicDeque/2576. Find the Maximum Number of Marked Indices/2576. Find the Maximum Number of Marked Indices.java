1class Solution {
2    public int maxNumOfMarkedIndices(int[] nums) {
3        Arrays.sort(nums);
4        int start = 0, end = nums.length/2, mid =0;
5        while(start < end) {
6            mid = start + (end-start+1)/2;
7            if(solve(mid, nums)){
8                start = mid;
9            } else {
10                end = mid-1;
11            }
12        }
13        return 2*start;
14    }
15
16    public boolean solve(int k, int nums[]) {
17        int count =0, n= nums.length;
18        for(int i =0; i<k ; i++) {
19            int pro = 2*nums[i];
20            if(pro<=nums[n-k+i]){
21                count++;
22            }
23            if(count>=k) return true;
24        }
25      return false;
26    }
27}