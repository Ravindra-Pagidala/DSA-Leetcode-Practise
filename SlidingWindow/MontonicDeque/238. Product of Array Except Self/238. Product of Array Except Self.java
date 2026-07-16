1class Solution {
2    public int[] productExceptSelf(int[] nums) {
3
4        //use test case nums[] = {1,2,3,4}
5        int len = nums.length;
6        int result[] = new int[len];
7
8        int pro = 1;
9        int left = 0;
10
11        // calculate the product of all elements to the left of each index, excluding the element itself
12
13        // fill result[] with left side products for every element
14        // result[] becomes {1,1,2,6} for input {1,2,3,4}
15
16        for (int i = 0; i < len; i++) {
17            left = i - 1;
18            // index 0 has no elements to its left, so its left product is 1 
19            if (left >= 0) {
20                pro *= nums[left];
21            }
22            result[i] = pro;
23        }
24
25        pro = 1;
26        int right = 0;
27
28        // calculate the product of all elements to the right of each index, excluding the element itself
29        // multiply this right product into result[i] which already holds the left product, giving the final answer
30
31        for (int i = len - 1; i >= 0; i--) {
32            right = i + 1;
33            // the last element has no elements to its right, so right product is 1, and answer is just the left product
34
35            if (right < len) {
36                pro *= nums[right];
37            }
38            // result[i] already has left subarray product, now multiply by right subarray product to get the final answer
39            result[i] *= pro;
40        }
41
42        //result  [24,12,8,6]
43        return result;
44    }
45}