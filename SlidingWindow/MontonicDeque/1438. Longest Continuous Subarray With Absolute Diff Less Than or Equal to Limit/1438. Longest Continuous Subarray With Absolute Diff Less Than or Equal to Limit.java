1class Solution {
2    public int longestSubarray(int[] nums, int limit) {
3    TreeMap<Integer, Integer> map = new TreeMap<>();
4      int max = 0, i =0, j =0, len = nums.length;
5    while(j < len) {
6        int end = nums[j];
7      map.put(end, map.getOrDefault(end, 0)+1);
8      if(map.lastKey()-map.firstKey()<=limit) {
9        max = Math.max(max, j-i+1);
10        j++;
11      }
12      else {
13        while(i<=j && map.lastKey()-map.firstKey()>limit) {
14            int start = nums[i];
15            map.put(start, map.get(start)-1);
16            if(map.get(start)==0){
17                map.remove(start);
18            }
19            i++;
20        }
21        j++;
22      }
23    }
24    return max;
25    }
26}