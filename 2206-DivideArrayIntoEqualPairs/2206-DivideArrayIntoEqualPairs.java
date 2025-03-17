// Algorithm:
// 1. Create a frequency array (or HashMap) to count occurrences of each number in the input array.
// 2. Iterate over the frequency array and check if every count is even.
//    - If any count is odd, return false (kyunki pair banane ke liye frequency even honi chahiye).
// 3. If all counts are even, return true.
//
// Pseudo Code:
// -------------
// function divideArray(nums):
//     freq = new array of size MAX+1 (initialize to 0)
//     for each num in nums:
//         freq[num]++
//     for each count in freq:
//         if (count % 2 != 0):
//             return false
//     return true
//
// Visualization Example:
// ------------------------
// Let nums = [3,2,3,2,2,2]
// Frequency:
//   2 appears 4 times (even)
//   3 appears 2 times (even)
// Since all frequencies are even, we can pair them as:
//   Pairs: (2,2), (2,2), (3,3)
// Hence, return true.

class Solution {
    public boolean divideArray(int[] nums) {
        // Maximum possible value is 500 as per constraints, so we need an array of size 501.
        int[] freq = new int[501]; // Frequency array to count occurrences of each number
        
        // Count the frequency of each number in nums.
        for (int num : nums) {
            freq[num]++;
        }
        
        // Check if every number's frequency is even.
        for (int count : freq) {
            // Agar count odd hai, toh pair banana possible nahi hai.
            if (count % 2 != 0) {
                return false;
            }
        }
        
        // Agar sab counts even hain, toh array ko equal pairs mein divide kar sakte hain.
        return true;
    }
}
