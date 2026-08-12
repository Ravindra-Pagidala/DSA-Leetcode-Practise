1class Solution {
2    public int maximumPopulation(int[][] logs) {
3        // Years range 1950-2050, offset by 1950 to use array index 0-100
4        int[] arr = new int[101];
5
6        for (int[] log : logs) {
7            int x = log[0] - 1950;
8            int y = log[1] - 1950;
9            arr[x] += 1;   // person enters at birth year
10            arr[y] -= 1;   // person exits at death year (not counted that year)
11        }
12
13        // Prefix sum gives actual population at each year
14        int maxPopulation = Integer.MIN_VALUE, maxYear = 0, population = 0;
15        for (int i = 0; i < arr.length; i++) {
16            population += arr[i];
17            // strict > ensures earliest year wins when tie
18            if (population > maxPopulation) {
19                maxPopulation = population;
20                maxYear = 1950 + i;
21            }
22        }
23        return maxYear;
24    }
25}