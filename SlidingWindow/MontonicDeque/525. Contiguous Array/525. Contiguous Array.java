1class Solution {
2    public int findMaxLength(int[] nums) {
3        
4        Map<Integer, Integer> map = new HashMap<>();
5        int ps =0, max = 0;
6        map.put(0,-1);
7        for(int j =0; j<nums.length; j++) {
8            ps+= nums[j]==0?-1:1;
9           if(map.containsKey(ps)) {
10            max = Math.max(max, j-map.get(ps));
11            //cacl max only when there's ps
12           }
13
14           else{
15            map.put(ps, j);
16           }
17        }
18        return max;
19    }
20}
21
22/*
23This looks completely different from sum problems. But there is a beautiful trick. Replace every 0 in the array with
24-1. Now equal 0s and 1s means the sum of the subarray is 0. Problem converts to: LONGEST SUBARRAY WITH
25SUM = 0.
26 */