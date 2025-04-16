// Last updated: 4/16/2025, 7:09:11 PM
// Algorithm:
// 1. Use a sliding window (two pointers) to count the number of subarrays that satisfy the "good" condition.
// 2. We maintain a frequency map (an array of size sufficiently large, or a HashMap) 
//    that tracks the occurrence of each element in the current window.
// 3. We also maintain a variable "pairs" that stores the number of pairs in the current window.
//    When we add a new element, say x at index r, the number of new pairs formed is equal to
//    the frequency of x before adding (because each occurrence pairs up with the new one).
// 4. For each right pointer r (expanding the window), update the frequency of nums[r] and 
//    add the new pairs. Then, while the number of pairs in the current window is at least k,
//    all subarrays that extend from the current left pointer to any index from r to n-1 will be "good".
//    Thus, add (n - r) to the answer, and shrink the window from the left by removing elements 
//    and updating the "pairs" count accordingly.
// 5. Continue until r reaches the end.
// 6. Return the final count of good subarrays.
//
// Pseudo Code:
// -------------
// ans = 0, pairs = 0, left = 0
// freq = new frequency array or map
// for r from 0 to n-1:
//     pairs += freq[nums[r]]   // new pairs formed by adding nums[r]
//     freq[nums[r]]++
//     while (pairs >= k):
//         ans += (n - r)
//         freq[nums[left]]--
//         pairs -= freq[nums[left]]   // because freq[nums[left]] now represents pairs contributed by removing one occurrence
//         left++
// return ans
//
// Visualization Example (Hinglish):
// ----------------------
// Maan lo nums = [3,1,4,3,2,2,4] and k = 2.
// - Start with left=0, pairs=0.
// - Expand window, update frequency and pairs as you add each element.
// - Jab window [left, r] mein pairs >= 2 ho jata hai, 
//   then every subarray starting at left and ending at any index from current r to end is "good", 
//   so add (n - r) to answer, then remove nums[left] and move left pointer to try to get a smaller valid window.
// - Continue similarly for all r.
//  
// Code:

class Solution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long pairs = 0;
        int left = 0;
        // Since nums[i] <= 1e9 and array length up to 1e5,
        // it is better to use a HashMap for frequency.
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Iterate with right pointer over the array.
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            // Get current frequency of x
            int count = freq.getOrDefault(x, 0);
            // New pairs formed = count (each of previous occurrences pairs with new one)
            pairs += count;
            // Update frequency for x.
            freq.put(x, count + 1);
            
            // While current window [left, r] has pairs >= k,
            // every subarray starting at left and ending from r to n-1 is good.
            while (left <= r && pairs >= k) {
                ans += (n - r);
                // Now shrink window from left
                int y = nums[left];
                int f = freq.get(y);
                // Removing one occurrence of y reduces pairs by (f - 1)
                // Because the removed y formed pairs with (f - 1) others in the window.
                pairs -= (f - 1);
                freq.put(y, f - 1);
                left++;
            }
        }
        
        return ans;
    }
}
