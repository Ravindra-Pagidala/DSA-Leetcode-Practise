1class Solution {
2    public int maxSatisfied(int[] customers, int[] grumpy, int k) {
3        int i = 0, j = 0, len = customers.length;
4        int baseSum = 0, maxSumForOnes = -1;
5
6        // Base: count all customers where owner is already not grumpy (always satisfied)
7        for (int t = 0; t < len; t++) {
8            if (grumpy[t] == 0) baseSum += customers[t];
9        }
10
11        // Sliding window of size k: find window with max grumpy customers (extra gain)
12        int ss = 0;
13        while (j < len) {
14            if (grumpy[j] == 1) ss += customers[j]; // only grumpy ones contribute extra
15
16            if (j - i + 1 < k) {
17                j++;
18            } else if (j - i + 1 == k) {
19                maxSumForOnes = Math.max(maxSumForOnes, ss); // track best window
20
21                if (grumpy[i] == 1) ss -= customers[i]; // remove left if grumpy
22                i++;
23                j++;
24            }
25        }
26
27        // base (always satisfied) + best extra gain from chosen window
28        return baseSum + maxSumForOnes;
29    }
30}