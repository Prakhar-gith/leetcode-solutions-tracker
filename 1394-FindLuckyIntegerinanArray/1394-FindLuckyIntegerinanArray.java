// Last updated: 7/5/2025, 10:11:31 PM
class Solution {
    public int findLucky(int[] arr) {
        // Using an array as a frequency map due to constraints (1 <= arr[i] <= 500)
        int[] freq = new int[501]; // Indices 0 to 500

        // Step 1: Count Frequencies
        for (int num : arr) {
            freq[num]++;
        }

        // Step 2 & 3: Check for Lucky Integers from largest to smallest
        for (int i = 500; i >= 1; i--) {
            if (freq[i] == i) {
                return i; // Found the largest lucky integer
            }
        }

        // Step 4: No lucky integer found
        return -1;
    }
}