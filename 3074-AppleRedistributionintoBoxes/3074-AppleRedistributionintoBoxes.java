// Last updated: 12/24/2025, 4:13:31 PM
1class Solution {
2    public int minimumBoxes(int[] apple, int[] capacity) {
3        int s=0;
4        for(int a:apple) s+= a;
5        Arrays.sort(capacity);
6        int c= 0;
7        int i= capacity.length- 1;
8        while(s > 0){
9            s-= capacity[i--];
10            c++;
11        }
12        return c;
13    }
14}