// Last updated: 8/30/2025, 6:56:41 PM
class Solution {
    public int possibleStringCount(String word) {
        // Initialize count to 1, representing the case where no key was pressed for too long.
        int count = 1;

        // Iterate from the second character of the word.
        // We compare each character with its preceding character.
        for (int i = 1; i < word.length(); i++) {
            // If the current character is the same as the previous character,
            // it means the current character could potentially be an extra character
            // typed due to a key being pressed for too long.
            // Since this can happen at most once in the entire string,
            // each such occurrence represents a new possible original string
            // (where this specific repeated character is considered the 'extra' one).
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            }
        }

        // The total count represents the sum of:
        // 1. The original string itself (no long press).
        // 2. Each scenario where a specific character in a consecutive pair
        //    is considered the result of a single long press.
        return count;
    }
}