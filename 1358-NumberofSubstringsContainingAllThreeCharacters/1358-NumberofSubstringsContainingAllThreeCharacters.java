// Algorithm:
// 1. Use a sliding window (two pointers) to traverse the string.
// 2. Maintain a frequency count for characters 'a', 'b', and 'c' in the current window.
// 3. For each starting index i (left pointer), expand the right pointer j until the window [i, j] 
//    contains at least one occurrence of 'a', 'b' and 'c'.
// 4. Once the window is valid, every substring starting at i and ending at any index from j to n-1 
//    will also be valid. So, add (n - j + 1) to the answer.
// 5. Then, remove the character at index i from the window (shrink the window from the left) 
//    and continue for the next starting index.
// 6. Return the total count of valid substrings.
//
// Pseudo Code:
// -------------
// count = 0
// i = 0, j = 0
// freq = [0, 0, 0]  // for 'a', 'b', 'c'
// while i < n:
//     while j < n and (freq['a'] == 0 or freq['b'] == 0 or freq['c'] == 0):
//         freq[s[j]]++
//         j++
//     if (freq['a'] > 0 and freq['b'] > 0 and freq['c'] > 0):
//         count += (n - j + 1)
//     freq[s[i]]--
//     i++
// return count
//
// Visualization Example:
// ------------------------
// Consider s = "abcabc" (n = 6):
//  - For i = 0: Expand j until window "abc" is formed (j becomes 3). Valid substrings starting at 0:
//       "abc", "abca", "abcab", "abcabc"  => (6 - 3 + 1) = 4 substrings.
//  - For i = 1: Window becomes "bca" with j already at 3, but then j must be advanced if needed.
//       Valid substrings starting at 1: "bca", "bcab", "bcabc"  => 3 substrings.
//  - Similarly for i = 2 and i = 3, we get 2 and 1 substrings respectively.
//  Total count = 4 + 3 + 2 + 1 = 10.
//

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        // Frequency array for vowels: index 0->'a', 1->'b', 2->'c'
        int[] freq = new int[3];
        int count = 0;
        int j = 0; // Right pointer for the sliding window
        
        // Traverse each possible starting index using left pointer i
        for (int i = 0; i < n; i++) {
            // Expand the window until it contains at least one 'a', 'b', and 'c'
            while (j < n && (freq[0] == 0 || freq[1] == 0 || freq[2] == 0)) {
                char ch = s.charAt(j);
                // Increase frequency count; 'a' -> index 0, 'b' -> index 1, 'c' -> index 2
                freq[ch - 'a']++;
                j++;
            }
            // Agar window valid hai (contains all three characters)
            if (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                // Substrings starting at i and ending at any index from j-1 to n-1 are valid
                count += (n - j + 1);
            }
            // Ab window se s.charAt(i) remove karo, as we will move left pointer ahead
            char leftChar = s.charAt(i);
            freq[leftChar - 'a']--;
        }
        
        // Return the total count of valid substrings
        return count;
    }
}
