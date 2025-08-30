// Last updated: 8/30/2025, 6:56:38 PM
// Algorithm:
// 1. Pehle check karo ki sabhi elements nums mein k se bade ya barabar hain, because operation ke through
//    hum values ko sirf kam kar sakte hain. Agar koi value k se choti hai, toh answer impossible => return -1.
// 2. Ab, array ke distinct values nikaalo (set S). In har distinct value ko ek "step" ke roop mein soch sakte hain.
//    Matlab, agar array ke distinct values sorted descending ho: d1 > d2 > ... > d_m, toh ek operation se d1
//    ko d2 tak reduce kiya ja sakta hai (choose h = d2, valid h ho jaata hai kyunki sabhi d1 ek jaise hain).
// 3. Ab, goal hai sabhi elements ko k banana.
//    - Agar k exactly S ke minimum (d_m) ke barabar hai, toh humein m-1 operations ki zaroorat hogi.
//    - Agar k d_m se chota hai, toh humein pehle m-1 operations se array ko d_m pe lana hai aur ek extra operation
//      se d_m ko k banana hai. Total operations = m.
// 4. Return the computed answer.
//
// Pseudo Code:
// -------------
// if (any num in nums < k) then return -1
// S = set of distinct values in nums
// m = size(S)
// minVal = minimum value in S
// if (k == minVal) return m - 1, else return m
//
// Visualization Example:
// ----------------------
// Example 1: nums = [5,2,5,4,5], k = 2
//   Distinct S = {5,4,2} -> sorted descending: [5,4,2] (m = 3, min = 2)
//   Since k (2) equals min, operations = 3 - 1 = 2.
//   Explanation: Operation 1: choose h = 4 (reduce 5's to 4), Operation 2: choose h = 2 (reduce 4's to 2).
//
// Example 3: nums = [9,7,5,3], k = 1
//   Distinct S = {9,7,5,3} -> sorted descending: [9,7,5,3] (m = 4, min = 3)
//   k (1) is less than min (3), operations = 4.
//   Explanation: Operation 1: h = 7, Operation 2: h = 5, Operation 3: h = 3, Operation 4: h = 1.
//
// Code:
class Solution {
    public int minOperations(int[] weights, int k) {
        // Check if every element is >= k, because we can only reduce values, not increase.
        for (int weight : weights) {
            if (weight < k) {
                return -1; // K is greater than some element, so it's impossible.
            }
        }
        
        // Create a set to store distinct values in the array.
        Set<Integer> set = new HashSet<>();
        for (int weight : weights) {
            set.add(weight);
        }
        
        // m = number of distinct values.
        int m = set.size();
        // Find the minimum value among the distinct values.
        int minVal = Integer.MAX_VALUE;
        for (int val : set) {
            minVal = Math.min(minVal, val);
        }
        
        // If k equals the minimum distinct value, then we require (m - 1) operations.
        // Otherwise, if k is less than the minimum, we need one extra operation to reduce min to k.
        return (k == minVal) ? (m - 1) : m;
    }
}
