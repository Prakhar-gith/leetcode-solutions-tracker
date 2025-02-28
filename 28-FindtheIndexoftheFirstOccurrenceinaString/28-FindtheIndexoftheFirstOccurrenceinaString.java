// Algorithm:
// 1. Agar needle empty ho toh return 0 (kyunki empty string ka occurrence index 0 hota hai).
// 2. Otherwise, calculate lengths of haystack (n) and needle (m).
// 3. Loop over haystack from index 0 se leke n-m tak:
//    a. For each index i, check if the substring starting at i with length m matches needle.
//    b. Agar mismatch milta hai, break and move to next index.
//    c. Agar complete match ho jaye (i.e., all m characters match), then return index i.
// 4. Agar loop complete hone ke baad koi match nahi milta, return -1.
//
// Pseudo Code:
// -------------
// function strStr(haystack, needle):
//     if needle is empty:
//         return 0
//     n = length(haystack)
//     m = length(needle)
//     for i from 0 to n - m:
//         j = 0
//         while j < m and haystack[i+j] == needle[j]:
//             j++
//         if j == m:
//             return i
//     return -1
//
// Visualization Example:
// ------------------------
// haystack = "sadbutsad", needle = "sad"
// Iteration 1: i = 0
//   Compare haystack[0..2] ("sad") with needle ("sad")
//   -> Match found, hence return 0
//
// If no match is found in entire haystack, we return -1.

class Solution {
    public int strStr(String haystack, String needle) {
        // Agar needle empty hai, toh according to problem statement return 0
        if (needle.length() == 0) {
            return 0;
        }
        
        int n = haystack.length();  // Total length of haystack
        int m = needle.length();    // Total length of needle
        
        // Loop from index 0 to n-m, taki needle ke liye enough characters ho haystack mein
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            // Compare characters of needle with substring starting at index i in haystack
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;  // Characters match, move to next character in needle
            }
            // Agar poore needle ke characters match ho gaye, then we found the starting index
            if (j == m) {
                return i; // Return the index where needle starts in haystack
            }
        }
        
        // Agar koi matching substring nahi mila, then return -1
        return -1;
    }
}
