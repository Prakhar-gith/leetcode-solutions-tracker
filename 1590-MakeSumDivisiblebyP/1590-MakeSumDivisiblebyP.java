// Last updated: 11/30/2025, 8:10:29 PM
1class Solution {
2    public int minSubarray(int[] nums, int p) {
3        long sum= 0;
4        for(int x : nums) sum+= x;
5        int rem= (int)(sum % p);
6        if(rem == 0) return 0;
7        
8        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
9        map.put(0, -1);
10        int cur = 0, minLen= nums.length;
11        
12        for(int i=0; i< nums.length; ++i) {
13            cur= (cur + nums[i]) % p;
14            int target = (cur - rem + p) % p;
15            if(map.containsKey(target)) {
16                minLen= Math.min(minLen, i- map.get(target));
17            }
18            map.put(cur, i);
19        }
20        return minLen == nums.length ? -1 : minLen;
21    }
22}