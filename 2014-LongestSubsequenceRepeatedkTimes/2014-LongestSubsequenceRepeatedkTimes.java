// Last updated: 6/28/2025, 1:22:21 AM

class Solution {
    String s;
    int k;
    char[] candidateChars;
    String result = "";
    int[][] nextCharIndices; // nextCharIndices[i][char_code] stores the index of the next occurrence of char_code at or after index i.

    public String longestSubsequenceRepeatedK(String s, int k) {
        this.s = s;
        this.k = k;

        // Step 1: Count character frequencies and filter candidate characters
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder sbCandidates = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            // Only characters appearing at least k times can be part of seq
            // This is crucial for correctness based on examples 2 and 3.
            if (freq[i] >= k) {
                sbCandidates.append((char) ('a' + i));
            }
        }
        this.candidateChars = sbCandidates.toString().toCharArray();

        // Precompute next character indices
        nextCharIndices = new int[s.length() + 1][26];
        for (int i = 0; i < 26; i++) {
            nextCharIndices[s.length()][i] = -1; // Sentinel value indicating no more occurrences
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                nextCharIndices[i][j] = nextCharIndices[i + 1][j]; // Inherit from next position
            }
            nextCharIndices[i][s.charAt(i) - 'a'] = i; // Update current char position
        }

        // Determine maximum possible length of seq
        // Constraints: 2 <= n < k * 8 implies n/k < 8. Thus L < 8. Max L is 7.
        int maxPossibleLen = s.length() / k;
        if (maxPossibleLen > 7) {
            maxPossibleLen = 7;
        }

        // Iterate possible lengths from max down to 0
        for (int L = maxPossibleLen; L >= 0; L--) {
            if (L == 0) {
                // If no valid non-empty sequence was found, return empty string.
                // Otherwise, the `result` would have been set by a longer sequence already.
                return result;
            }
            // Start backtracking for current length L
            char[] currentSeq = new char[L];
            // If we find a valid sequence for the current length L, it's the longest.
            // Since we iterate candidateChars from 'z' down to 'a', it will be lexicographically largest.
            if (findLongestLexicographical(0, currentSeq, L)) {
                return result;
            }
        }
        return ""; // Should not be reached if maxPossibleLen >= 0
    }

    // Backtracking function to find the lexicographically largest subsequence of length `targetLen`
    // `idx`: current index in `currentSeq` to fill
    // `currentSeq`: the subsequence being built
    // `targetLen`: the desired length of `currentSeq`
    private boolean findLongestLexicographical(int idx, char[] currentSeq, int targetLen) {
        // Pruning step: If the current prefix (currentSeq up to idx-1) cannot be repeated k times,
        // then no longer sequence starting with this prefix can either.
        // We only check if idx > 0, as an empty string (idx=0) is always a valid prefix to start building from.
        if (idx > 0) {
            String currentPrefix = new String(currentSeq, 0, idx);
            if (!canForm(currentPrefix)) {
                return false; // Prune this branch
            }
        }

        if (idx == targetLen) {
            // At this point, we know canForm(new String(currentSeq)) is true due to the pruning above.
            // This is the first candidate of this length and lexicographical order, so it's the answer.
            result = new String(currentSeq);
            return true;
        }

        // Iterate candidate characters from 'z' down to 'a' to ensure lexicographical largest
        for (int i = candidateChars.length - 1; i >= 0; i--) {
            currentSeq[idx] = candidateChars[i];
            if (findLongestLexicographical(idx + 1, currentSeq, targetLen)) {
                return true; // Propagate success upwards
            }
        }
        return false;
    }

    // Checks if `seq * k` is a subsequence of `s` using precomputed nextCharIndices
    private boolean canForm(String seq) {
        int currentSIdx = 0; // Current starting index in s for subsequence search
        for (int repeat = 0; repeat < k; repeat++) {
            int tempSIdx = currentSIdx; // Temporary index for this repeat's search starting point
            for (char c : seq.toCharArray()) {
                int charCode = c - 'a';
                if (tempSIdx >= s.length() || nextCharIndices[tempSIdx][charCode] == -1) {
                    return false; // Character not found for current repeat, or ran out of s
                }
                tempSIdx = nextCharIndices[tempSIdx][charCode] + 1; // Move to the next position after finding 'c'
            }
            currentSIdx = tempSIdx; // Update starting index for the next repeat to be after the just-found sequence
        }
        return true;
    }
}