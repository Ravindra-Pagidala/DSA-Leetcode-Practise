1class Solution {
2    public int maximumPopulation(int[][] logs) {
3       int diff[] = new int[101];
4       for(int log[] : logs) {
5             int start = log[0]-1950;
6             int end = log[1]-1950;
7             diff[start]+=1;
8             diff[end]-=1;
9       }
10       int max = Integer.MIN_VALUE, year = 0, sum = 0;
11       for(int i =0; i< 101; i++) {
12            sum+=diff[i];
13           if(sum > max) {
14              max = sum;
15              year = i+1950;
16            //  System.out.println(max + +year);
17           }
18       }
19
20       return year; 
21    }
22}