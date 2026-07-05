1class Solution {
2    public int kthSmallest(int[][] matrix, int k) {
3        int n = matrix.length;
4        int start = matrix[0][0], end = matrix[n-1][n-1], mid =0;
5        while(start < end) {
6            mid = start + (end-start)/2;
7            if(count(matrix, mid, n)>=k){
8                end = mid;
9            }
10            else{
11                start = mid+1;
12            }
13        }
14        return start;
15    }
16
17    public int count(int matrix[][], int mid, int n) {
18        int row = 0, col = n-1, count =0;
19        while(row<n && col>=0){
20            if(matrix[row][col]<=mid){
21                count+=col+1;
22                row++;
23            }
24            else{
25                col--;
26            }
27        }
28        return count;
29    }
30}
31
32/* refer notes */