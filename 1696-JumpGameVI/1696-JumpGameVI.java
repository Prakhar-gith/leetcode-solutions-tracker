// Algorithm:
// 1. Define dp[i] as the maximum score to reach index i.
// 2. Initialize dp[0] = nums[0] because starting point score is the first element.
// 3. Use a deque (double-ended queue) to maintain indices of dp in decreasing order of dp values.
//    - The front of the deque will always be the index with the maximum dp value within the current window.
// 4. For each index i from 1 to n-1:
//    a. Remove indices from the front of the deque if they are out of the window (i - k).
//    b. Set dp[i] = nums[i] + dp[deque.peek()], because the best jump comes from the maximum dp in the window.
//    c. Remove indices from the back of the deque while dp[i] is greater than or equal to dp[deque.peekLast()].
//    d. Add index i to the deque.
// 5. Return dp[n-1] which represents the maximum score to reach the last index.

/*
   Pseudo Code:
   -------------
   function maxResult(nums, k):
       n = length(nums)
       dp[0] = nums[0]
       deque = new empty deque
       deque.push(0)
       for i = 1 to n - 1:
           while deque is not empty and deque.front() < i - k:
               deque.pop_front()
           dp[i] = nums[i] + dp[deque.front()]
           while deque is not empty and dp[i] >= dp[deque.back()]:
               deque.pop_back()
           deque.push_back(i)
       return dp[n-1]
*/

/*
   Visualization Example for nums = [1, -1, -2, 4, -7, 3] and k = 2:

   Index:    0    1    2    3    4    5
   nums:    [1,  -1,  -2,   4,  -7,   3]
   dp:       1
   deque:   [0]
   
   i = 1:
   - Window: indices [max(0, 1-2), 1] -> [0,1]
   - dp[1] = nums[1] + dp[0] = -1 + 1 = 0
   - deque update: remove indices from back if dp[1] >= dp[last] -> dp[1]=0, dp[0]=1, so no removal.
   - deque becomes [0, 1]

   i = 2:
   - Window: indices [0,2]
   - dp[2] = nums[2] + dp[0] = -2 + 1 = -1
   - deque update: dp[2] = -1, no removal needed.
   - deque becomes [0, 1, 2]

   i = 3:
   - Window: indices [1,3] because index 0 is out of window (3-2 = 1)
   - Remove index 0 from deque.
   - dp[3] = nums[3] + dp[1] = 4 + 0 = 4 (dp[1] is max in current window)
   - deque update: remove from back indices with smaller dp values.
     * dp[3] = 4 >= dp[2] = -1 -> remove index 2.
     * dp[3] = 4 >= dp[1] = 0  -> remove index 1.
   - deque becomes [3]

   Continue similarly for remaining indices.
*/

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length; // Total number of elements in nums
        int[] dp = new int[n]; // dp[i] will store the maximum score to reach index i
        dp[0] = nums[0]; // Starting at index 0, score is just nums[0]
        
        // Deque to store indices of dp in descending order (max at the front)
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0); // Start with index 0 in the deque
        
        // Traverse the array from index 1 to n - 1
        for (int i = 1; i < n; i++) {
            // Remove indices from deque which are out of the window (i - k)
            // yahan hum ensure kar rahe hain ki deque ke front ka index valid window ke andar ho
            while (!deque.isEmpty() && deque.peek() < i - k) {
                deque.poll(); // Remove index if it's out of the allowed jump range
            }
            
            // Calculate dp[i] as current nums[i] + maximum score in the window (dp[deque.peek()])
            dp[i] = nums[i] + dp[deque.peek()];
            
            // Maintain the deque in decreasing order of dp values
            // Remove indices from the back that have a lower dp value than current dp[i]
            // taki future steps mein dp[i] easily maximum ban jaye
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            
            // Add current index i to the deque
            deque.offer(i);
        }
        
        // dp[n-1] contains the maximum score to reach the last index
        return dp[n - 1];
    }
}
