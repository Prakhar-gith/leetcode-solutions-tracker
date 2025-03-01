// Algorithm:
// 1. Iterate through all strings in the array.
// 2. Use DFS to mark all strings that are connected (i.e., similar) to the current string.
// 3. Two strings are considered similar if they are identical or if they differ in exactly two positions.
// 4. Each DFS call marks one complete group of similar strings.
// 5. Count the number of DFS calls (groups) and return that count.
//
// Pseudo Code:
// -------------
// function numSimilarGroups(strs):
//     n = length(strs)
//     visited = boolean array of size n (all false)
//     groups = 0
//     for i from 0 to n-1:
//         if not visited[i]:
//             dfs(strs, visited, i)
//             groups++
//     return groups
//
// function dfs(strs, visited, index):
//     mark visited[index] as true
//     for j from 0 to n-1:
//         if not visited[j] and areSimilar(strs[index], strs[j]):
//             dfs(strs, visited, j)
//
// function areSimilar(s1, s2):
//     diff = 0
//     for i from 0 to length(s1)-1:
//         if s1[i] != s2[i]:
//             diff++
//         if diff > 2: return false
//     return (diff == 0 or diff == 2)
//
// Visualization Example:
// --------------------------
// Consider strs = ["tars", "rats", "arts", "star"]
// - Start with "tars": DFS visits "rats" (similar by swapping letters) and then "arts" (similar to "rats").
//   So, group 1: {"tars", "rats", "arts"}.
// - "star" is not visited in this DFS and is not similar to any in group 1, so it forms group 2.
// - Total groups = 2.
//

class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length; // Total strings in the input array
        boolean[] visited = new boolean[n]; // Array to keep track of visited strings during DFS
        int groups = 0; // Counter for the number of similar string groups
        
        // Loop through each string in the array
        for (int i = 0; i < n; i++) {
            // Agar current string pehle se visited hai, toh usse skip karo
            if (!visited[i]) {
                // Naya group mil gaya, DFS se sab connected (similar) strings ko mark karo
                dfs(strs, visited, i);
                groups++; // Group count increase karo
            }
        }
        
        // Return total groups found
        return groups;
    }
    
    // DFS function to traverse all strings similar to the current string at index 'index'
    private void dfs(String[] strs, boolean[] visited, int index) {
        visited[index] = true; // Mark current string as visited
        int n = strs.length; // Total number of strings
        
        // Iterate over all strings to check similarity with current string
        for (int j = 0; j < n; j++) {
            // Agar string j not visited hai aur current string similar hai string j se, DFS call karo
            if (!visited[j] && areSimilar(strs[index], strs[j])) {
                dfs(strs, visited, j);
            }
        }
    }
    
    // Helper function to check if two strings s1 and s2 are similar
    // Two strings are similar if they are identical or differ in exactly two positions
    private boolean areSimilar(String s1, String s2) {
        int diff = 0; // Counter for differences between s1 and s2
        int length = s1.length(); // Both strings have same length
        
        // Compare characters of both strings at each position
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++; // Increment difference counter
                if (diff > 2) {
                    // Agar differences 2 se zyada ho, toh strings similar nahi ho sakti
                    return false;
                }
            }
        }
        // Return true if strings are identical (diff==0) or have exactly two differences (diff==2)
        return diff == 0 || diff == 2;
    }
}
