// Algorithm:
// 1. Check if the array is empty or has only one element. If so, return 0 or that element respectively.
// 2. Create a DP array 'dp' of the same length as nums.
// 3. Set dp[0] = nums[0] (only one house to rob).
// 4. Set dp[1] = max(nums[0], nums[1]) (choose the better of the first two houses).
// 5. For each subsequent house (index i from 2 to n-1):
//    - Decide whether to rob the current house plus dp[i-2] (skip adjacent house) or skip current house and take dp[i-1].
//    - dp[i] = max(dp[i-1], dp[i-2] + nums[i])
// 6. Return dp[n-1] as the maximum money that can be robbed without alerting the police.

/*
   Pseudo Code:
   -------------
   function rob(nums):
       if nums is empty:
           return 0
       if length(nums) == 1:
           return nums[0]
       n = length(nums)
       dp = new array of size n
       dp[0] = nums[0]
       dp[1] = max(nums[0], nums[1])
       for i from 2 to n - 1:
           dp[i] = max(dp[i-1], dp[i-2] + nums[i])
       return dp[n-1]
*/

/*
   Visualization Example for nums = [2,7,9,3,1]:
   
       Index:     0   1   2   3   4
       House:     2   7   9   3   1
       
       dp[0] = 2                // Only one house, rob it.
       dp[1] = max(2,7) = 7       // Choose max between house0 and house1.
       dp[2] = max(dp[1], dp[0] + 9) = max(7, 2+9) = 11
       dp[3] = max(dp[2], dp[1] + 3) = max(11, 7+3) = 11
       dp[4] = max(dp[3], dp[2] + 1) = max(11, 11+1) = 12
       
       Final maximum amount = dp[4] = 12
*/

class Solution {
    public int rob(int[] nums) {
        // Edge case: agar array khali hai, toh kuch bhi nahi chori kar sakte
        if (nums == null || nums.length == 0) return 0;
        // Agar sirf ek house hai, bas wahi chori karo
        if (nums.length == 1) return nums[0];
        
        int n = nums.length; // Total number of houses
        int[] dp = new int[n]; // dp array to store maximum money robable till each house
        
        // Pehla house: bas usi ka paisa
        dp[0] = nums[0];
        // Dusra house: choose the maximum of first or second house's money
        dp[1] = Math.max(nums[0], nums[1]);
        
        // Iterate over the rest of the houses starting from index 2
        for (int i = 2; i < n; i++) {
            // Decision: current house ke sath pichle non-adjacent house ka paisa (dp[i-2] + nums[i])
            // ya bina current house ke pichle house tak ka maximum (dp[i-1])
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            // yahan dp[i] holds the maximum money that can be robbed till house i
        }
        
        // dp[n-1] contains the maximum money that can be robbed without alerting the police
        return dp[n - 1];
    }
}
