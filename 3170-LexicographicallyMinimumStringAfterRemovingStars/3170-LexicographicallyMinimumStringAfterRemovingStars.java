// Last updated: 6/11/2025, 3:54:20 AM
class Solution {
    public String clearStars(String s) {
        int n = s.length();
        // Deques to store indices of each letter
        @SuppressWarnings("unchecked")
        Deque<Integer>[] pos = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayDeque<>();
        }
        boolean[] deleted = new boolean[n];
        
        // First pass: record letter positions, and process stars
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                // Find smallest letter available to the left
                for (int ch = 0; ch < 26; ch++) {
                    if (!pos[ch].isEmpty()) {
                        // Remove the rightmost occurrence to minimize lex order
                        int delIdx = pos[ch].removeLast();
                        deleted[delIdx] = true;
                        break;
                    }
                }
            } else {
                // Record this letter's index
                pos[c - 'a'].addLast(i);
            }
        }
        
        // Build the answer: skip '*' and deleted letters
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '*' && !deleted[i]) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
