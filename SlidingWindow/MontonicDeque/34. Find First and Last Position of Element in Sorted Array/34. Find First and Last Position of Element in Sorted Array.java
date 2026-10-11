1class Solution {
2    public int[] searchRange(int[] nums, int target) {
3        int result[] = new int[2];
4        int x = 0, y =0, n = nums.length;
5        x = firstTrue(nums, target);
6        y = lastTrue(nums, target);
7       // System.out.println(x+ +y);
8        result[0] = (x < n && nums[x]==target) ? x : -1;
9        result[1] = (y < n && nums[y]==target) ? y : -1;
10
11        return result;
12    }
13
14    public int firstTrue(int arr[], int k) {
15       int start = 0, end = arr.length-1, mid =0;
16
17       while(start < end) {
18          mid = start+(end-start)/2;
19          if((arr[mid]==k)) {
20            end = mid;
21          }
22          else if (arr[mid] < k) {
23             start = mid+1;
24          }
25          else {
26           end = mid-1;
27          }
28       }
29       return start;
30    }
31
32     public int lastTrue(int arr[], int k) {
33       int start = 0, end = arr.length-1, mid =0;
34
35       while(start < end) {
36          mid = start+(end-start+1)/2;
37          if((arr[mid]==k)) {
38            start = mid;
39          }
40          else if (arr[mid] < k) {
41             start = mid+1;
42          }
43          else {
44            end = mid-1;
45          }
46       }
47       return start;
48    }
49}