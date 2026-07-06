1class Solution {
2    public int shortestSubarray(int[] nums, int k) {
3        int min = Integer.MAX_VALUE, len = nums.length, sum =0;
4        int[] prefix = new int[len+1];
5        for(int i =0; i<len ; i++){
6            prefix[i+1] = prefix[i]+nums[i];
7        }
8
9        Deque<Integer> deque = new ArrayDeque<>();
10        for(int i =0; i<=len ; i++) {
11
12            while(!deque.isEmpty() && prefix[deque.peekLast()]>=prefix[i]){
13                deque.pollLast();
14            }
15           while(!deque.isEmpty() && prefix[i]-prefix[deque.peekFirst()]>=k){
16               min = Math.min(min, i-deque.pollFirst());
17            }
18
19
20            deque.offerLast(i);
21        }
22        return min==Integer.MAX_VALUE?-1:min;
23    }
24}
25
26/*
27
28**Why prefix array has size n+1 instead of n:**
29
30You have `nums = [2, -1, 2, 3, -5, 4]`. 6 elements. n=6.
31
32---
33
34**Think of prefix[i] as: sum of everything BEFORE index i.**
35
36```
37prefix[0] = 0        (nothing before index 0. Empty sum.)
38prefix[1] = 2        (sum of nums[0..0] = 2)
39prefix[2] = 1        (sum of nums[0..1] = 2+(-1) = 1)
40prefix[3] = 3        (sum of nums[0..2] = 2+(-1)+2 = 3)
41prefix[4] = 6        (sum of nums[0..3] = 2+(-1)+2+3 = 6)
42prefix[5] = 1        (sum of nums[0..4] = 2+(-1)+2+3+(-5) = 1)
43prefix[6] = 5        (sum of nums[0..5] = 2+(-1)+2+3+(-5)+4 = 5)
44```
45
466 elements in nums. But 7 values in prefix. That extra slot at the front (prefix[0] = 0) is what makes n+1 size necessary.
47
48---
49
50**Why that extra prefix[0] = 0 is critical:**
51
52Suppose you want the sum of the ENTIRE array from index 0 to index 5.
53
54Without prefix[0]:
55
56```
57prefix = [2, 1, 3, 6, 1, 5]
58         [0  1  2  3  4  5]
59
60Sum from 0 to 5 = prefix[5] - prefix[-1] ??
61You need index -1. That does not exist.
62```
63
64With prefix[0] = 0:
65
66```
67prefix = [0, 2, 1, 3, 6, 1, 5]
68         [0  1  2  3  4  5  6]
69
70Sum from 0 to 5 = prefix[6] - prefix[0] = 5 - 0 = 5. Correct.
71```
72
73prefix[0] = 0 represents the empty state — before you have seen any element. It is like a starting point of 0.
74
75---
76
77**The formula — subarray sum from index a to b in nums:**
78
79```
80prefix[b+1] - prefix[a]
81```
82
83Let me show exactly why with examples:
84
85```
86nums =   [2,  -1,   2,   3,  -5,   4]
87index =   0    1    2    3    4    5
88
89Subarray [1..3] means nums[1], nums[2], nums[3] = -1+2+3 = 4
90
91Using formula: prefix[3+1] - prefix[1] = prefix[4] - prefix[1] = 6 - 2 = 4. Correct.
92
93Subarray [0..5] means entire array = 2+(-1)+2+3+(-5)+4 = 5
94
95Using formula: prefix[5+1] - prefix[0] = prefix[6] - prefix[0] = 5 - 0 = 5. Correct.
96
97Subarray [0..0] means just nums[0] = 2
98
99Using formula: prefix[0+1] - prefix[0] = prefix[1] - prefix[0] = 2 - 0 = 2. Correct.
100```
101
102The `b+1` is because prefix[i] stores sum of everything BEFORE index i, so to include index b you need prefix[b+1].
103
104The `prefix[0] = 0` handles the edge case where your subarray starts from index 0 — there is nothing before it, so the sum of everything before is zero.
105
106---
107
108**If you only made array of size n:**
109
110```
111prefix = [2, 1, 3, 6, 1, 5]  (size 6, no leading 0)
112```
113
114You CANNOT represent a subarray starting at index 0 without a special case. You CANNOT represent the sum of the entire array without going out of bounds. The formula breaks. You would need ugly if-else checks everywhere.
115
116With size n+1 and prefix[0]=0, the formula `prefix[b+1] - prefix[a]` works cleanly for every possible subarray including those starting at index 0. No special cases needed anywhere.
117
118
119**Let me explain each part like I'm sitting next to you.**
120
121---
122
123**Step 2 — Why do we want the RIGHTMOST j?**
124
125The subarray is from position `j` to position `i`. Its length is `i - j`.
126
127You want the SHORTEST subarray. So you want `i - j` to be as SMALL as possible. Since `i` is fixed (you are standing at `i`), the only way to make `i - j` smaller is to make `j` BIGGER.
128
129Concrete example:
130
131```
132prefix = [0, 2, 1, 3, 6, ...]
133i = 4 (prefix = 6), k = 5
134
135j=0: 6-0=6 >= 5. Valid. Length = 4-0 = 4.
136j=2: 6-1=5 >= 5. Valid. Length = 4-2 = 2.
137j=3: 6-3=3 < 5.  Not valid.
138```
139
140Both j=0 and j=2 give valid subarrays. But j=2 is further RIGHT, so the subarray is SHORTER. You want j=2, not j=0. So among all valid j values, you always want the rightmost one.
141
142---
143
144**Step 3 — Why do we throw away index from back when prefix[back] >= prefix[i]?**
145
146Forget the code for a second. Think about two people standing in a queue — person A (older, standing at position `a`) and person B (newer, standing at position `b`). You just arrived at position `i`.
147
148Person A has a prefix sum of 5. Person B has a prefix sum of 3. Person B arrived AFTER person A.
149
150Now for any future ending position, say `i' = 10` with prefix sum 8:
151
152```
153Using person A as start: 8 - 5 = 3. Subarray length = 10 - a.
154Using person B as start: 8 - 3 = 5. Subarray length = 10 - b.
155```
156
157Person B gives you a BIGGER difference (5 > 3) — which means more likely to satisfy sum >= k.
158Person B also gives you a SHORTER subarray (b > a, so 10-b < 10-a).
159
160Person B beats person A on BOTH things simultaneously — bigger difference AND shorter length.
161
162So person A is completely useless. Person A can never give you a better answer than person B for any future ending position. Throw person A away.
163
164This happens when `prefix[a] >= prefix[b]`. The newer index b with smaller prefix sum always wins.
165
166So when you arrive at index `i`, you look at the back of the deque. If the back has a prefix sum that is BIGGER THAN OR EQUAL TO your current prefix sum — throw it away. Because you (the current index `i`) are newer AND have smaller prefix sum. You beat that back element for every future ending.
167
168---
169
170**Step 4 — Why do we throw away index from front after finding a valid subarray?**
171
172You are at position `i`. The front of the deque is index `j`. You check: `prefix[i] - prefix[j] >= k`. It is valid. You record the length `i - j`.
173
174Now the front `j` is done. Should we keep it for the future?
175
176Think about what happens if we keep it. The next position is `i+1`, then `i+2`, and so on. If we use `j` as the start again:
177
178```
179With future ending i+1: length = (i+1) - j = (i-j) + 1. Longer than what we already found.
180With future ending i+2: length = (i+2) - j = (i-j) + 2. Even longer.
181```
182
183Every future ending gives a LONGER subarray starting from `j` compared to what we just found at `i`. The answer can only get worse. Front `j` is dead.
184
185Concrete example:
186
187```
188prefix = [0, 2, 1, 3, 6, 1, 5]
189k = 5
190
191At i=4 (prefix=6): front is j=0 (prefix=0).
192  6 - 0 = 6 >= 5. Length = 4 - 0 = 4. Answer = 4. Remove front 0.
193
194If we had kept front 0 for future:
195  At i=5 (prefix=1): 1 - 0 = 1 < 5. Not even valid.
196  At i=6 (prefix=5): 5 - 0 = 5 >= 5. Length = 6 - 0 = 6. But we already found 4!
197```
198
199Keeping front 0 only gave us length 6 later, which is worse than the 4 we already found. Useless. Remove it the moment it gives a valid answer so it cannot pollute future checks.
200
201---
202
203**One line summary of each:**
204
205Remove from **back**: because a newer index with smaller prefix sum beats an older index with bigger prefix sum — for every future ending position simultaneously.
206
207Remove from **front**: because once a front index gives a valid answer, it can only give LONGER answers for all future endings. It served its purpose. Kick it out.
208
209Great question. Let me build this from scratch.
210
211---
212
213**What are we actually trying to find?**
214
215A subarray with sum >= k. The subarray starts somewhere and ends somewhere.
216
217In prefix sum language:
218
219```
220subarray sum from start to end = prefix[end] - prefix[start]
221```
222
223We want this to be >= k. So:
224
225```
226prefix[end] - prefix[start] >= k
227```
228
229---
230
231**The deque stores all POSSIBLE starting points.**
232
233As you iterate with `i` as the ending position, the deque holds all indices that could be the starting point of a valid subarray ending at `i`.
234
235So when you are at position `i`, you are asking:
236
237> Among all the starting points stored in my deque, is there one where `prefix[i] - prefix[start] >= k`?
238
239That is literally the condition `prefix[i] - prefix[dq.peekFirst()] >= k`.
240
241`i` is your current ending position. `dq.peekFirst()` is the best possible starting point (smallest prefix sum, giving largest difference).
242
243---
244
245**Why check only the FRONT and not all elements in the deque?**
246
247Because the deque is in INCREASING order of prefix sums. Front has the SMALLEST prefix sum.
248
249```
250deque (prefix values): [0, 1, 3]
251                        ↑ front (smallest)
252```
253
254`prefix[i] - prefix[front]` is the LARGEST possible difference you can get from any element in the deque.
255
256If this largest difference is still less than k, then every other element in the deque (which has bigger prefix sums) will give an even smaller difference. All of them fail. No point checking.
257
258If the front satisfies the condition, great — record the answer and remove it. Then check the new front. Keep checking until the front fails.
259
260---
261
262**Concrete example:**
263
264```
265prefix = [0, 2, 1, 3, 6, 1, 5], k = 5
266At i=4, prefix[4]=6
267Deque holds indices: [0, 2, 3]
268Prefix values:        0  1  3
269```
270
271Check front = index 0, prefix=0:
272
273```
2746 - 0 = 6 >= 5. Valid. Length = 4-0 = 4. Remove front.
275```
276
277Check new front = index 2, prefix=1:
278
279```
2806 - 1 = 5 >= 5. Valid. Length = 4-2 = 2. Remove front.
281```
282
283Check new front = index 3, prefix=3:
284
285```
2866 - 3 = 3 < 5. Not valid. STOP.
287```
288
289If index 3 with prefix=3 fails, index 4 (if it were there) with prefix=6 would give `6-6=0`. Even worse. No point looking further.
290
291---
292
293**In one line:**
294
295You check `prefix[i] - prefix[dq.peekFirst()] >= k` because `i` is your ending position and `dq.peekFirst()` is the best starting point available. If even the best starting point cannot satisfy the condition, no other starting point can. If it does satisfy, record the answer and check the next best starting point (new front) — because that might give a shorter subarray.
296
297
298 */