// Last updated: 12/24/2025, 7:10:45 PM
1class Solution {
2    public int maxProfit(int[] prices) {
3        int b1= Integer.MAX_VALUE;
4        int p1= 0;
5        int b2= Integer.MAX_VALUE;
6        int p2= 0;
7        for(int p : prices){
8            b1= Math.min(b1, p);
9            p1= Math.max(p1, p- b1);
10            b2= Math.min(b2, p- p1);
11            p2= Math.max(p2, p- b2);
12        }
13        return p2;
14    }
15}