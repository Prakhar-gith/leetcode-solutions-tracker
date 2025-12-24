// Last updated: 12/24/2025, 6:49:01 PM
1class Solution {
2    public boolean containsNearbyDuplicate(int[] nums, int k) {
3        Set<Integer> s= new HashSet<>();
4        for(int i=0; i< nums.length; i++){
5            if(!s.add(nums[i])) return true;
6            if(s.size()> k) s.remove(nums[i- k]);
7        }
8        return false;
9    }
10}