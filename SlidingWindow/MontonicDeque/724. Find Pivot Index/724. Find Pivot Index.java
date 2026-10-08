1class Solution {
2    public int pivotIndex(int[] nums) {
3        int n = nums.length;
4        int ls[] = new int[n+1];
5        int rs[] = new int[n+1];
6        ls[0]= 0; rs[n]=0;
7        for(int i =1 ; i< ls.length; i++){
8              ls[i]= ls[i-1]+nums[i-1];
9        }
10
11        for(int i = rs.length-2;i>=0; i--) {
12            rs[i] = rs[i+1]+nums[i];
13
14        }
15       int x = 1, y =0;
16
17       while(x < ls.length && y <= rs.length-2) {
18             if(ls[x]==rs[y]) {
19                return y;
20             }
21             x++; y++;
22       }
23
24        return -1;
25    }
26}