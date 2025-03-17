// Algorithm:
// 1. We use binary search on the "capability" value, which is defined as the maximum money among the houses robbed.
//    Our search range is from the minimum value in nums (low) to the maximum value in nums (high).
// 2. For a given candidate capability 'cap', we simulate the robbery using a greedy approach:
//    - Iterate through the houses (nums) from left to right.
//    - If a house's money is less than or equal to 'cap', we rob it and skip the next house (because adjacent houses cannot be robbed).
//    - Count the number of houses robbed.
//    - If the count is at least k, then it is possible to rob k houses with maximum money <= cap.
// 3. Based on the check, adjust the binary search range: if possible, try to lower the capability; otherwise, increase it.
// 4. Continue until low equals high, which gives the minimum capability needed.
//
// Pseudo Code:
// -------------
// function canRob(cap):
//     count = 0, i = 0
//     while i < n:
//         if nums[i] <= cap:
//             count++
//             i += 2    // skip adjacent house
//         else:
//             i++
//     return count >= k
//
// main:
//     low = min(nums), high = max(nums)
//     while low < high:
//         mid = (low + high) / 2
//         if canRob(mid):
//             high = mid
//         else:
//             low = mid + 1
//     return low
//
// Visualization Example (nums = [2,3,5,9], k = 2):
// ------------------------------------------------------------------
// low = 2, high = 9
// mid = (2+9)/2 = 5
// Check candidate = 5:
//   - index0: 2 <= 5, rob it (count = 1), skip index1.
//   - index2: 5 <= 5, rob it (count = 2), skip index3.
//   -> count = 2 which is >= k, so candidate 5 works. Now high = 5.
// Next, low = 2, high = 5, mid = 3
// Check candidate = 3:
//   - index0: 2 <= 3, rob it (count = 1), skip index1.
//   - index2: 5 > 3, skip index2.
//   - index3: 9 > 3, skip index3.
//   -> count = 1 which is < k, so candidate 3 doesn't work. Set low = 4.
// Then, low = 4, high = 5, mid = 4
// Check candidate = 4:
//   - index0: 2 <= 4, rob it (count = 1), skip index1.
//   - index2: 5 > 4, skip.
//   - index3: 9 > 4, skip.
//   -> count = 1 which is < k, so candidate 4 doesn't work. Set low = 5.
// Now low = 5, high = 5, loop ends, answer = 5.
//
// Code:
class Solution {
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        // Compute the minimum and maximum values in nums for binary search range
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int num : nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }
        
        // Binary search for the minimum capability that allows robbing at least k houses
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canRob(nums, mid, k)) {
                // Agar mid candidate se k houses rob kar sakte hain, try lowering capability
                high = mid;
            } else {
                // Otherwise, increase the candidate capability
                low = mid + 1;
            }
        }
        return low;
    }
    
    // Helper function to check if it's possible to rob at least k houses with candidate capability 'cap'
    private boolean canRob(int[] nums, int cap, int k) {
        int count = 0;
        int i = 0;
        while (i < nums.length) {
            // Agar current house ka money <= cap, then we can rob this house
            if (nums[i] <= cap) {
                count++;       // House robbed
                i += 2;        // Skip next house (adjacent) as per rule
            } else {
                i++;           // Otherwise, move to next house
            }
            if (count >= k) return true;
        }
        return false;
    }
}
