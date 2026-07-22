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
12      int ps[] = new int[len+1];
13      ps[0] = df[0];
14
15      for(int i =1; i<ps.length; i++){
16         ps[i]=ps[i-1]+df[i];
17        // System.out.print(ps[i]+ );
18      }
19
20      StringBuilder sb = new StringBuilder();
21      for(int i=0; i<ps.length-1; i++) {
22         char c = s.charAt(i);
23        // System.out.println(c);
24       c=(char)(((c - 'a') + (ps[i] % 26) + 26) % 26 + 'a');
25         sb.append(c);
26      }
27
28     return sb.toString();
29    }
30
31    public void update(int df[], int left, int right, int val) {
32        df[left]+=val;
33        df[right+1]-=val;
34    }
35}