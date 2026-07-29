1class Solution {
2    public int maxNumOfMarkedIndices(int[] nums) {
3        Arrays.sort(nums);
4        int start = 0, end = nums.length/2, mid =0;
5        while(start < end) {
6            mid = start + (end-start+1)/2;
7            if(solve(mid, nums)){
8                start = mid;
9            } else {
10                end = mid-1;
11            }
12        }
13        return 2*start;
14    }
15
16    public boolean solve(int k, int nums[]) {
17        int count =0, n= nums.length;
18        for(int i =0; i<k ; i++) {
19            int pro = 2*nums[i];
20            if(pro<=nums[n-k+i]){
21                count++;
22            }
23            if(count>=k) return true;
24        }
25      return false;
26    }
27}
28
29/*
30**Understanding the hints one by one:**
31
32**Hint 1 — Think about checking if k operations is possible**
33
34Instead of directly finding the maximum, flip the question. Ask: can I perform exactly k operations? If I can do k operations, I mark 2k indices. So find the maximum k where this is possible.
35
36This is binary search on the answer — exactly your LAST TRUE pattern. k=1 is trivially possible (if any valid pair exists). As k increases, it gets harder. Pattern T T T F F F → LAST TRUE → maximize k.
37
38---
39
40**Hint 2 — To perform k operations use smallest k and largest k elements**
41
42Sort the array. You need k pairs. Each pair needs one small number (the i) and one large number (the j) where 2×small <= large.
43
44To maximize pairs, use the k smallest numbers as the small side and k largest numbers as the large side. Why? Because you want the small side to be as small as possible (easier to satisfy 2×small) and the large side to be as large as possible. Using any other combination would only make the condition harder to satisfy.
45
46---
47
48**Hint 3 — Match ith smallest with ith largest**
49
50After sorting, say n=8 and k=3:
51
52```
53sorted = [a, b, c, d, e, f, g, h]
54small side = [a, b, c]  (first k elements)
55large side = [f, g, h]  (last k elements)
56```
57
58Match them in order: a with f, b with g, c with h.
59
60Why this matching and not a with h, b with g, c with f? Because if a pairs with h (the biggest), you waste h on the easiest pairing. g and f might not satisfy b and c. Matching in order is optimal — smallest small with smallest large, so you do not waste your biggest numbers on your smallest numbers.
61
62---
63
64**Hint 4 — Binary search on k**
65
66Now you have a way to CHECK if k operations is possible:
67
68Sort array. Take first k elements as small side, last k elements as large side. Check if nums[i] × 2 <= nums[n-k+i] for all i from 0 to k-1. If all pairs satisfy → k is possible.
69
70Binary search on k from 0 to n/2. Find the largest k where this check passes. Answer = 2k.
71
72
73**Why n/2?**
74
75You need pairs. Each pair uses 2 elements. If array has n elements, maximum possible pairs = n/2. You cannot have more than n/2 pairs no matter what. So binary search range is 0 to n/2.
76
77---
78
79**Why n-k+i and not k-i+1?**
80
81The hint says match ith smallest with k-i+1 largest which is 1-indexed language. Your code uses 0-indexed arrays. Let me show both side by side.
82
83Say n=8, k=3, array after sorting:
84
85```
86index:  0    1    2    3    4    5    6    7
87nums: [ 2,   3,   4,   5,   6,   7,   8,   9 ]
88
89Small side = first k = indices 0, 1, 2  (elements 2, 3, 4)
90Large side = last k  = indices 5, 6, 7  (elements 6, 7, 8, 9 ... last 3)
91```
92
93Where does the large side START? At index `n-k = 8-3 = 5`. Correct.
94
95Now match them in order:
96
97```
98i=0: small = nums[0],     large = nums[n-k+0] = nums[5]
99i=1: small = nums[1],     large = nums[n-k+1] = nums[6]
100i=2: small = nums[2],     large = nums[n-k+2] = nums[7]
101```
102
103So `n-k+i` simply means: start from the beginning of the large side (index n-k) and move forward by i. That is it.
104
105The hint's k-i+1 largest is just 1-indexed counting from the right. Your formula `n-k+i` is the same thing expressed as a 0-indexed array position.
106
107
108 */