// Last updated: 7/24/2025, 1:54:10 AM
class Solution {
    public int maximumGain(String s, int x, int y) {
        
        // Algorithm:
        // The problem asks for the maximum score by removing "ab" and "ba" substrings.
        // A greedy approach works best here. The idea is to always remove the substring
        // that gives more points first. For example, if 'x' (for "ab") is greater than 'y' (for "ba"),
        // we should remove all possible "ab"s from the string first.
        //
        // Why is this greedy approach optimal?
        // Removing a higher-scoring pair first ensures we lock in the best possible points at each step.
        // This removal might create new opportunities for the lower-scoring pair, which is good.
        // Or it might destroy a potential lower-scoring pair, but since we gained more points by removing
        // the higher-scoring one, it's a net positive outcome.
        //
        // The implementation will be a two-pass process:
        // 1. First Pass: Remove all occurrences of the higher-scoring pair.
        // 2. Second Pass: On the string that remains after the first pass, remove all occurrences
        //    of the lower-scoring pair.
        // We can use a stack-like mechanism to efficiently remove pairs. As we iterate through the
        // string, we use a temporary string (or a stack) to build the result. If the current character
        // forms a pair with the last character of our temporary string, we pop the last character and add
        // points. Otherwise, we append the current character.

        // Pseudo Code:
        // 1. totalScore = 0
        // 2. if x > y:
        // 3.   // High score pair is "ab", low score is "ba"
        // 4.   (points1, remainingString1) = removePairs(s, 'a', 'b', x)
        // 5.   totalScore += points1
        // 6.   (points2, remainingString2) = removePairs(remainingString1, 'b', 'a', y)
        // 7.   totalScore += points2
        // 8. else:
        // 9.   // High score pair is "ba", low score is "ab"
        // 10.  (points1, remainingString1) = removePairs(s, 'b', 'a', y)
        // 11.  totalScore += points1
        // 12.  (points2, remainingString2) = removePairs(remainingString1, 'a', 'b', x)
        // 13.  totalScore += points2
        // 14. return totalScore

        // `removePairs` function logic:
        //   newString = StringBuilder()
        //   points = 0
        //   for each char `c` in inputString:
        //     if newString is not empty and last char of newString is `first` and `c` is `second`:
        //        remove last char from newString
        //        points += scoreForPair
        //     else:
        //        append `c` to newString
        //   return (points, newString)

        if (x > y) {
            // Prioritize removing "ab" because x > y.
            return calculateScore(s, 'a', 'b', x, y);
        } else {
            // Prioritize removing "ba" because y >= x.
            return calculateScore(s, 'b', 'a', y, x);
        }
    }

    // Helper function to perform the two-pass calculation.
    // Yeh function poora calculation karega, pehle high-score wale pair ko, fir low-score wale ko.
    private int calculateScore(String s, char highFirst, char highSecond, int highPoints, int lowPoints) {
        
        // Pass 1: Remove the higher-scoring pairs.
        // Hum ek StringBuilder use karenge jo stack ki tarah kaam karega.
        StringBuilder sb = new StringBuilder();
        int totalScore = 0;

        for (char c : s.toCharArray()) {
            // Check if the current character `c` forms a high-score pair with the last character in our StringBuilder.
            // Agar `highFirst` aur `highSecond` ka pair banta hai, to use remove kardo.
            if (c == highSecond && sb.length() > 0 && sb.charAt(sb.length() - 1) == highFirst) {
                sb.deleteCharAt(sb.length() - 1); // This is like popping from the stack.
                totalScore += highPoints;
            } else {
                sb.append(c); // This is like pushing to the stack.
            }
        }

        // The string remaining after the first pass.
        // Pehle pass ke baad jo string bachi hai.
        String remainingString = sb.toString();
        sb.setLength(0); // Clear the StringBuilder for the second pass.

        // Pass 2: On the remaining string, remove the lower-scoring pairs.
        // Ab bachi hui string par low-score wala pair remove karenge.
        char lowFirst = highSecond;
        char lowSecond = highFirst;

        for (char c : remainingString.toCharArray()) {
            if (c == lowSecond && sb.length() > 0 && sb.charAt(sb.length() - 1) == lowFirst) {
                sb.deleteCharAt(sb.length() - 1);
                totalScore += lowPoints;
            } else {
                sb.append(c);
            }
        }
        
        return totalScore;
    }
    
    /*
     * Visualization of the Stack-like process (using StringBuilder):
     *
     * s = "cdbcbbaaabab", x = 4, y = 5.
     * Since y > x, high-score pair is "ba", low-score is "ab".
     *
     * Pass 1: Remove "ba" (score 5)
     * Char | StringBuilder `sb` (Stack) | Action
     * -----------------------------------------------------------------
     * 'c'  | c                          | Push 'c'
     * 'd'  | cd                         | Push 'd'
     * 'b'  | cdb                        | Push 'b'
     * 'c'  | cdbc                       | Push 'c'
     * 'b'  | cdbcb                      | Push 'b'
     * 'b'  | cdbcbb                     | Push 'b'
     * 'a'  | cdbcb                      | Current 'a' + Top 'b' = "ba". Pop 'b'. Score += 5.
     * 'a'  | cdbc                       | Current 'a' + Top 'b' = "ba". Pop 'b'. Score += 5.
     * 'a'  | cdbca                      | Push 'a'
     * 'b'  | cdbcab                     | Push 'b'
     * 'a'  | cdbca                      | Current 'a' + Top 'b' = "ba". Pop 'b'. Score += 5.
     * 'b'  | cdbcab                     | Push 'b'
     *
     * End of Pass 1: Total Score = 15. `remainingString` = "cdbcab".
     *
     * Pass 2: Remove "ab" (score 4) from "cdbcab"
     * Char | StringBuilder `sb` (Stack) | Action
     * -----------------------------------------------------------------
     * 'c'  | c                          | Push 'c'
     * 'd'  | cd                         | Push 'd'
     * 'b'  | cdb                        | Push 'b'
     * 'c'  | cdbc                       | Push 'c'
     * 'a'  | cdbca                      | Push 'a'
     * 'b'  | cdbc                       | Current 'b' + Top 'a' = "ab". Pop 'a'. Score += 4.
     *
     * End of Pass 2: Total Score = 15 + 4 = 19. Final remaining string is "cdbc".
    */
}