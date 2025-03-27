// Last updated: 3/27/2025, 11:10:01 PM
// Algorithm:
// 1. Sabse pehle overall dominant element (candidate) find karo from the entire list.
//    Since the array has exactly one dominant element, we can simply count frequencies.
// 2. Let totalCandidate be the total frequency of the candidate in the entire array.
// 3. Iterate over possible split indices i from 0 to n-2 (because right part must be non-empty).
//    - Maintain a running count (leftCandidate) of candidate occurrences in the left subarray (nums[0...i]).
//    - For each index i, left subarray length = i + 1 and right subarray length = n - i - 1.
//    - Check if candidate is dominant in left subarray: leftCandidate > (i + 1) / 2.
//    - Check if candidate is dominant in right subarray: (totalCandidate - leftCandidate) > (n - i - 1) / 2.
// 4. Return the first index i satisfying both conditions, else return -1 if no valid split exists.
//
// Pseudo Code:
// -------------
// candidate = findDominant(nums)          // the one dominant element in nums
// totalCandidate = count(candidate in nums)
// leftCandidate = 0
// for i from 0 to n-2:
//     if (nums[i] == candidate):
//         leftCandidate++
//     if (leftCandidate * 2 > (i + 1)) and ((totalCandidate - leftCandidate) * 2 > (n - i - 1)):
//         return i
// return -1
//
// Visualization Example:
// ------------------------
// Example: nums = [1,2,2,2]
// - Candidate (dominant) = 2, totalCandidate = 3
// i = 0: left = [1] -> leftCandidate = 0, leftLen = 1, condition fails (0*2 <= 1).
// i = 1: left = [1,2] -> leftCandidate = 1, leftLen = 2, condition fails (1*2 == 2, not > 2).
// i = 2: left = [1,2,2] -> leftCandidate = 2, leftLen = 3 (2*2 = 4 > 3),
//        right = [2] -> rightCandidate = 1, rightLen = 1 (1*2 = 2 > 1).
// Valid split at i = 2. Thus, answer = 2.

class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        
        // Frequency map to count each element in nums
        // Dominant element exists uniquely by problem statement.
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // Find the dominant candidate: element whose frequency is > n/2
        int candidate = -1;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() * 2 > n) {
                candidate = entry.getKey();
                break;
            }
        }
        
        // Total occurrences of the candidate in the entire list
        int totalCandidate = freq.get(candidate);
        int leftCandidate = 0;  // Count of candidate in left subarray
        
        // Iterate over possible split indices from 0 to n-2
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) == candidate) {
                leftCandidate++;
            }
            // Check if candidate is dominant in left part:
            // candidate count in left > half of left subarray length.
            if (leftCandidate * 2 > (i + 1)) {
                int rightCount = totalCandidate - leftCandidate; // candidate count in right part
                int rightLen = n - i - 1;
                // Check if candidate is dominant in right part:
                if (rightCount * 2 > rightLen) {
                    return i; // Found the minimum valid split index
                }
            }
        }
        
        // Agar koi valid split nahi mila, return -1.
        return -1;
    }
}
