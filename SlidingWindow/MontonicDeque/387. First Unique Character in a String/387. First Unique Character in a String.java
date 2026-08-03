1class Solution {
2    public int firstUniqChar(String s) {
3        Map<Character, Integer> map = new LinkedHashMap<>();
4        int arr[] = new int[26];
5        for(int i =0 ; i< s.length(); i++) {
6            char c = s.charAt(i);
7            arr[c-'a']++;
8        }
9
10           for(int i =0 ; i<s.length(); i++) {
11             
12       
13             if(arr[s.charAt(i)-'a']==1)
14             {
15                return i;
16             }
17              
18        }
19
20        
21        return -1;
22    
23    }
24}