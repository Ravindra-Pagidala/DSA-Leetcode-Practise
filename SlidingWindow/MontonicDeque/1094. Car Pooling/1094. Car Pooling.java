1class Solution {
2    public boolean carPooling(int[][] trips, int capacity) {
3      int arr[] = new int[1001];
4       int passengers =0, from =0, to =0;
5      for(int trip[] : trips) {
6         passengers = trip[0];
7         from = trip[1];
8         to = trip[2];
9         arr[from]+=passengers;
10         arr[to]-=passengers;
11      }
12      int current = 0;
13      for(int val: arr) {
14         current+=val;
15         if(current>capacity) return false;
16      }
17
18      return true;
19} }
20
21/*
22**General explanation — think of it like a timeline.**
23
24Imagine a horizontal line representing the road from location 0 to location 1000. Your car starts at 0 and drives east.
25
26Each trip says: between location X and location Y, there are N passengers sitting in the car.
27
28You want to know: at any single point on this road, did the car ever carry more than capacity passengers?
29
30---
31
32**The difference array idea:**
33
34Instead of saying at locations 1,2,3,4 there are 2 passengers — which requires updating 4 cells — you just say:
35
36```
37At location 1: +2 passengers enter
38At location 5: -2 passengers leave
39```
40
41Two operations instead of four. When you later scan from left to right and keep a running total, you automatically know how many passengers are in the car at every location.
42
43---
44
45**Why subtract at `to` and not `to-1`:**
46
47Passengers are IN the car from location `from` up to but not including `to`. At `to` they step out. So you subtract at `to` itself. When the prefix sum reaches `to`, it subtracts those passengers — meaning from that location onwards they are gone. Exactly correct.
48
49---
50
51
52`int[] diff = new int[1001]`
53Create a timeline of 1001 locations all set to zero. Each cell says net passenger change at this location. Nothing has happened yet so everything is zero.
54
55`diff[trip[1]] += trip[0]`
56At the pickup location, note that these passengers are getting in. Increase that cell by the number of passengers.
57
58`diff[trip[2]] -= trip[0]`
59At the dropoff location, note that these passengers are leaving. Decrease that cell by the number of passengers.
60
61`current += diff[i]`
62Drive through each location one by one. Add whatever net change happened at this location to your running passenger count. This running count IS the number of people currently sitting in the car.
63
64`if (current > capacity) return false`
65At every single location, immediately check if the car is overfull. The moment it is, stop and return false. No need to check further.
66
67`return true`
68If you drove through all locations and never exceeded capacity, the answer is true.
69
70---
71
72**Dry run — trips=[[2,1,5],[3,3,7]], capacity=4:**
73
74**Step 1 — build the timeline:**
75
76```
77Trip [2, 1, 5]: 2 passengers board at 1, leave at 5
78  diff[1] += 2
79  diff[5] -= 2
80
81Trip [3, 3, 7]: 3 passengers board at 3, leave at 7
82  diff[3] += 3
83  diff[7] -= 3
84```
85
86Timeline after both trips:
87
88```
89location: 0  1  2  3  4   5  6   7  8 ...
90diff:     0  2  0  3  0  -2  0  -3  0 ...
91```
92
93Read this as: at location 1 net +2 people, at location 3 net +3 people, at location 5 net -2 people, at location 7 net -3 people. Everywhere else nothing changes.
94
95**Step 2 — drive through and check:**
96
97```
98location 0: current = 0+0 = 0.  Car has 0.  0 <= 4. Fine.
99location 1: current = 0+2 = 2.  Car has 2.  2 <= 4. Fine.
100location 2: current = 2+0 = 2.  Car has 2.  2 <= 4. Fine.
101location 3: current = 2+3 = 5.  Car has 5.  5 > 4.  STOP. Return false.
102```
103
104At location 3, trip 1 passengers (2 people) are still in the car AND trip 2 passengers (3 people) just boarded. Total = 5. Exceeds capacity 4. Answer is **false**.
105
106---
107
108**Dry run — trips=[[2,1,5],[3,5,7]], capacity=3:**
109
110**Step 1 — build the timeline:**
111
112```
113Trip [2, 1, 5]: diff[1] += 2,  diff[5] -= 2
114Trip [3, 5, 7]: diff[5] += 3,  diff[7] -= 3
115```
116
117At location 5 both changes land on the same cell:
118
119```
120diff[5] = -2 + 3 = 1
121```
122
123Timeline:
124
125```
126location: 0  1  2  3  4  5  6   7  8 ...
127diff:     0  2  0  0  0  1  0  -3  0 ...
128```
129
130**Step 2 — drive through and check:**
131
132```
133location 0: current = 0+0 = 0.  Car has 0.  Fine.
134location 1: current = 0+2 = 2.  Car has 2.  2 <= 3. Fine.
135location 2: current = 2+0 = 2.  Car has 2.  Fine.
136location 3: current = 2+0 = 2.  Car has 2.  Fine.
137location 4: current = 2+0 = 2.  Car has 2.  Fine.
138location 5: current = 2+1 = 3.  Car has 3.  3 <= 3. Fine.
139location 6: current = 3+0 = 3.  Car has 3.  Fine.
140location 7: current = 3-3 = 0.  Car has 0.  Fine.
141```
142
143Never exceeded. Answer is **true**.
144
145---
146
147**Why location 5 works cleanly:**
148
149At location 5, trip 1 passengers leave (-2) and trip 2 passengers board (+3). Net change = +1. The difference array combines both into one cell automatically. Running total goes from 2 to 3. Car has exactly 3 people — within capacity. The difference array does not care about order of events at same location. It just adds all net changes together. That is the beauty of it.
150 */