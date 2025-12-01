// Last updated: 12/1/2025, 4:54:15 PM
1class Solution {
2    public long maxRunTime(int n, int[] batteries) {
3        java.util.Arrays.sort(batteries);
4        long sum= 0;
5        for(int b : batteries) sum+= b;
6        int i= batteries.length - 1;
7        while(i >= 0 && batteries[i] > sum/ n){
8            sum -= batteries[i];
9            n--;
10            i--;
11        }
12        return sum / n;
13    }
14}