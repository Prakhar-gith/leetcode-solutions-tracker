// Last updated: 12/4/2025, 7:27:06 PM
1class Solution {
2    public int minOperations(int[] nums, int k) {
3        int sum= 0;
4        for(int x : nums) sum+= x;
5        return sum% k;
6    }
7}