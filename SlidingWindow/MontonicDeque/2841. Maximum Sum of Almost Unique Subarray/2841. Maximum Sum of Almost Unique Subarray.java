1class Solution {
2    public long maxSum(List<Integer> nums, int m, int k) {
3        Map<Long, Long> map = new HashMap<>();
4        int i=0, j=0, len = nums.size();
5        long sum =0, max= -1;
6        if(k>len) return 0;
7        while(j < len) {
8            long end = nums.get(j);
9            sum+=end;
10            map.put(end, map.getOrDefault(end, 0L)+1);
11            if(j-i+1<k) j++;
12            else if(j-i+1==k) {
13                if(map.size()>=m){
14                    max = Math.max(max, sum);
15                }
16                long start = nums.get(i);
17                sum-=start;
18                map.put(start, map.get(start)-1);
19                if(map.get(start)==0){
20                    map.remove(start);
21                }
22                i++;
23                j++;
24            }
25        }
26        return max==-1?0:max;
27    }
28}