1class Solution {
2    public int maximumCandies(int[] candies, long k) {
3       int start = 0, end =0, mid =0;
4       for(int x : candies) {
5          end = Math.max(x, end);
6       }
7
8       while(start < end) {
9            mid = start+(end-start+1)/2;
10            if(solve(mid, candies, k)) {
11                start = mid;
12            } else {
13                end = mid-1;
14            }
15       }
16       return start;
17} 
18  public boolean solve(int mid, int arr[], long k) {
19        long count =0;
20      for(int x: arr) {
21         if(x>=mid){
22            count+=x/mid;
23         }
24         if(count>=k) return true;
25      }
26      return false;
27  }
28
29}