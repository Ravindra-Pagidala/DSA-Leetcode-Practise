1class Solution {
2    public int minimizeArrayValue(int[] nums) {
3        long sum =0, max =-1;
4        for(int i =0; i<nums.length; i++) {
5            sum+=nums[i];
6            max = Math.max(max, (sum+i)/(i+1));
7        }
8        return (int)max;
9    }
10}
11
12/*
13
14BinarySearch is not efficient algo for this problem , dont get confused with minMax
15
16**What are we trying to find?**
17
18You want to find the smallest possible value of the largest bucket after any number of operations.
19
20---
21
22**Let us forget the code completely. Just think physically.**
23
24```
25nums = [3, 7, 1, 6]
26```
27
28You have 4 buckets with 3, 7, 1, 6 units of water. You can pour water from any bucket into the bucket immediately to its LEFT. As many times as you want.
29
30---
31
32**Why can the answer not be 4?**
33
34Look at just the first two buckets: bucket 0 has 3, bucket 1 has 7.
35
36Combined water = 3 + 7 = 10.
37
38Now ask yourself: is there ANY way to distribute 10 units of water across 2 buckets such that BOTH buckets have at most 4?
39
40Maximum you can hold in 2 buckets if each holds at most 4 = 4 + 4 = 8.
41
42But you have 10 units. 10 > 8. Impossible.
43
44**No matter what you do, at least one of the first two buckets will always have more than 4.** The water cannot disappear. It cannot flow rightward. The total in buckets 0 and 1 is stuck at 10 forever.
45
46So the answer cannot be 4. Or 3. Or anything less than 5.
47
48---
49
50**Why is 5 achievable?**
51
52If max = 5, the first two buckets can each hold at most 5. Together they can hold 5 + 5 = 10. You have exactly 10 units. It fits perfectly.
53
54Now show that you can actually rearrange to achieve this:
55
56```
57Start:  [3, 7, 1, 6]
58
59Pour from bucket 1 to bucket 0 twice:
60After:  [5, 5, 1, 6]
61
62Now pour from bucket 3 to bucket 2 once:
63After:  [5, 5, 2, 5]
64
65Maximum = 5. Done.
66```
67
68Every bucket is at most 5. It works.
69
70---
71
72**So why exactly 5 and not something between 4 and 5?**
73
74Because we are dealing with integers. The answer must be a whole number. 4 is impossible (proved above). 5 is achievable (proved above). So 5 is the answer.
75
76---
77
78**The general principle — every prefix tells you a floor:**
79
80The key rule is: water can only flow LEFT. It can NEVER flow right. So the total water in any prefix (first i+1 buckets) can only stay the same or increase over time — water flows IN from the right but never OUT to the right.
81
82This means:
83
84For the first 1 bucket: total = 3. You have 1 bucket. Minimum possible max = ceil(3/1) = 3.
85
86For the first 2 buckets: total = 10. You have 2 buckets. Minimum possible max = ceil(10/2) = 5.
87
88For the first 3 buckets: total = 11. You have 3 buckets. Minimum possible max = ceil(11/3) = 4.
89
90For the first 4 buckets: total = 17. You have 4 buckets. Minimum possible max = ceil(17/4) = 5.
91
92Each of these is a HARD LOWER BOUND. The answer must be at least this value for each prefix. You cannot beat it no matter how clever you are.
93
94The final answer must satisfy ALL of these constraints simultaneously. So you take the maximum across all of them.
95
96```
97max(3, 5, 4, 5) = 5
98```
99
100Answer = 5.
101
102---
103
104**Why ceil and not floor?**
105
106If you have 10 units across 2 buckets and you want them equal: 10/2 = 5 exactly. Fine.
107
108If you have 11 units across 3 buckets: 11/3 = 3.67. You cannot have 3.67 in a bucket. Each bucket must have a whole number. The best you can do is some have 3 and some have 4. The maximum will be 4 = ceil(11/3).
109
110You always round UP because you cannot split a unit and the total must fit.
111
112---
113
114**Now the code makes complete sense:**
115
116```java
117public int minimizeArrayValue(int[] nums) {
118    long sum = 0;
119    long answer = 0;
120
121    for (int i = 0; i < nums.length; i++) {
122        sum += nums[i];               // running prefix sum up to index i
123        long avg = (sum + i) / (i + 1);  // ceil(sum / (i+1))
124        answer = Math.max(answer, avg);   // take the maximum constraint
125    }
126    return (int) answer;
127}
128```
129
130`sum` at each step = total water in the first i+1 buckets.
131
132`(sum + i) / (i+1)` = ceiling division of sum by (i+1). This is the minimum possible maximum for the first i+1 buckets.
133
134`Math.max(answer, avg)` = keep the tightest constraint seen so far. The tightest constraint is the largest floor value across all prefixes.
135
136---
137
138**Dry run:**
139
140```
141i=0: sum=3,  ceil(3/1)=3,  answer=max(0,3)=3
142i=1: sum=10, ceil(10/2)=5, answer=max(3,5)=5
143i=2: sum=11, ceil(11/3)=4, answer=max(5,4)=5
144i=3: sum=17, ceil(17/4)=5, answer=max(5,5)=5
145
146Return 5.
147```
148
149The constraint from the first 2 buckets (5) was the tightest. It determined the final answer.
150 */