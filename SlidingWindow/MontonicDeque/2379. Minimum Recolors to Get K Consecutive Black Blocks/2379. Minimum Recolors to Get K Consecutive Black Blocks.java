1class Solution {
2    public int minimumRecolors(String s, int k) {
3        int[]arr= new int[2];
4        int i =0, j =0, len = s.length(), min =Integer.MAX_VALUE;
5        while(j < len) {
6            char end = s.charAt(j);
7            if(end=='W') {
8                arr[0]+=1;
9            } else {
10                arr[1]+=1;
11            }
12            if(j-i+1<k) j++;
13            else if(j-i+1==k) {
14                min = Math.min(min, arr[0]);
15                char start = s.charAt(i);
16                if(start=='W') {
17                    arr[0]-=1;
18                } else{
19                    arr[1]-=1;
20                }
21                i++;
22                j++;
23            }  
24        }
25        return min;
26    }
27}