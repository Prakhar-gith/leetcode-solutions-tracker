// Last updated: 12/6/2025, 1:44:30 PM
1class Solution {
2    public int countPartitions(int[] nums, int k) {
3        int n= nums.length;
4        int mod= 1000000007;
5        int[] dp= new int[n+ 1];
6        int[] p= new int[n+ 2];
7        
8        dp[0]= 1;
9        p[1]= 1;
10        
11        java.util.Deque<Integer> min= new java.util.ArrayDeque<>();
12        java.util.Deque<Integer> max= new java.util.ArrayDeque<>();
13        int l= 0;
14        
15        for(int i= 0; i< n; i++){
16            while(!min.isEmpty() && nums[min.peekLast()] >= nums[i]) min.pollLast();
17            min.addLast(i);
18            while(!max.isEmpty() && nums[max.peekLast()] <= nums[i]) max.pollLast();
19            max.addLast(i);
20            
21            while(nums[max.peekFirst()] - nums[min.peekFirst()] > k){
22                l++;
23                if(min.peekFirst() < l) min.pollFirst();
24                if(max.peekFirst() < l) max.pollFirst();
25            }
26            
27            int cur= (p[i+ 1] - p[l] + mod) % mod;
28            dp[i+ 1]= cur;
29            p[i+ 2]= (p[i+ 1] + cur) % mod;
30        }
31        return dp[n];
32    }
33}