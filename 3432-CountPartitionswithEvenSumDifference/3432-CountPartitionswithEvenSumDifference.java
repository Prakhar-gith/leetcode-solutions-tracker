// Last updated: 12/5/2025, 2:21:59 PM
1class Solution {
2    public int countPartitions(int[] nums) {
3        int sum= 0;
4        for(int n : nums) sum+= n;
5        if(sum % 2 == 0) return nums.length- 1;
6        return 0;
7    }
8}