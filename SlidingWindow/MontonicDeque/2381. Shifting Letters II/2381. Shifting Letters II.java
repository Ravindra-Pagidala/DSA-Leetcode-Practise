1class Solution {
2    public String shiftingLetters(String s, int[][] shifts) {
3        int len = s.length();
4       int df[] = new int[len+1];
5
6       for(int[] shift: shifts) {
7         int left = shift[0];
8         int right = shift[1];
9         int val = shift[2]==0?-1:1;
10         update(df, left, right, val);
11       }
12
13      for(int i =1; i<df.length; i++){
14         df[i]+=df[i-1];
15        // System.out.print(ps[i]+ );
16      }
17
18      StringBuilder sb = new StringBuilder();
19      for(int i=0; i<df.length-1; i++) {
20         char c = s.charAt(i);
21        // System.out.println(c);
22       c=(char)(((c - 'a') + (df[i] % 26) + 26) % 26 + 'a');
23         sb.append(c);
24      }
25
26     return sb.toString();
27    }
28
29    public void update(int df[], int left, int right, int val) {
30        df[left]+=val;
31        df[right+1]-=val;
32    }
33}