1class Solution {
2    public int longestSubarray(int[] nums, int limit) {
3    Deque<Integer> maxDeque = new ArrayDeque<>();
4    Deque<Integer> minDeque = new ArrayDeque<>();
5    int i =0, max = 0;
6    for(int j =0; j <nums.length;j++) {
7        
8        //remove indices whose values are smaller than current, we need maxEle
9        while(!maxDeque.isEmpty() && nums[maxDeque.peekLast()]<=nums[j]){
10            maxDeque.pollLast();
11        }
12        //add current at last , front will have currentMax
13         maxDeque.offerLast(j);
14
15        //remove indices whose values are greater than current, we need minEle
16        while(!minDeque.isEmpty() && nums[minDeque.peekLast()]>=nums[j]){
17            minDeque.pollLast();
18        }
19         //add currentMax at Last,front will have currentMin
20        minDeque.offerLast(j);
21
22        //while max-min>limit
23        while(nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] >limit)
24         {
25            //remove data related to i and slide window
26            if(maxDeque.peekFirst()==i){
27                maxDeque.pollFirst();
28            }
29            if(minDeque.peekFirst()==i){
30                minDeque.pollFirst();
31            }
32              //move towards right until condition is satisfied
33            i++;
34        }
35       max = Math.max(max, j-i+1);
36    }
37    return max;
38  }
39 }
40
41 // refer sliding window maximum problem