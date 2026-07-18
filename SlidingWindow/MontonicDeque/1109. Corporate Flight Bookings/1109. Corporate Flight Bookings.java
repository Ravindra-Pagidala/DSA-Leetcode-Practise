1class Solution {
2    public int[] corpFlightBookings(int[][] bookings, int n) {
3        int diff[] = new int[n+1];
4        System.out.println(diff.length);
5        int result[] = new int[n];
6        for(int arr[] : bookings) {
7            int left = arr[0]-1;
8            int right = arr[1]-1;
9            int v = arr[2];
10            update(diff, left, right, v);
11        }
12        int sum =0;
13      for(int i =0; i<n; i++) {
14         sum+=diff[i];
15         result[i]=sum;
16      }
17      return result;
18    }
19
20    public void update(int diff[], int left, int right, int v){
21      diff[left]+=v;
22      diff[right+1]-=v;
23    }
24}
25
26//FollowTemplate 3 of prefixSum , problem is 1 index based , that's why do -1