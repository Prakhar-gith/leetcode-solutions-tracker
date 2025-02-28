// Algorithm:
// 1. For each digit position (ones, tens, hundreds, etc.), calculate how many times '1' appears at that digit.
// 2. For a given digit position i (i = 1, 10, 100, ...):
//    - divider = i * 10
//    - (n / divider) * i gives the count of full cycles where '1' appears.
//    - For the remaining part (n % divider), we add extra ones:
//         - If the current digit (n / i % 10) is 1, then add (n % i + 1).
//         - If it is greater than 1, then add i.
//         - If it is 0, then add 0.
//    - We combine the above using: 
//         count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i)
//
// Pseudo Code:
// -------------
// count = 0
// for i = 1; i <= n; i *= 10:
//     divider = i * 10
//     count += (n / divider) * i + min( max(n % divider - i + 1, 0), i )
// return count
//
// Visualization Example for n = 13:
// -----------------------------------
// i = 1 (ones place):
//   divider = 10
//   left part = 13 / 10 = 1         -> contributes 1 * 1 = 1
//   remainder part = 13 % 10 = 3      -> extra ones = min(max(3 - 1 + 1, 0), 1) = min(3, 1) = 1
//   Total ones from ones place = 1 + 1 = 2
//
// i = 10 (tens place):
//   divider = 100
//   left part = 13 / 100 = 0         -> contributes 0 * 10 = 0
//   remainder part = 13 % 100 = 13    -> extra ones = min(max(13 - 10 + 1, 0), 10) = min(4, 10) = 4
//   Total ones from tens place = 0 + 4 = 4
//
// Total count = 2 (ones place) + 4 (tens place) = 6
//

class Solution {
    public int countDigitOne(int n) {
        // 'count' will hold the total count of digit '1'
        long count = 0; // Using long to avoid overflow during calculations
        
        // Loop for each digit position: 1, 10, 100, ... up to n
        for (long i = 1; i <= n; i *= 10) {
            // 'divider' represents the next digit's place (i.e., i * 10)
            long divider = i * 10;
            
            // (n / divider) * i gives the contribution from complete cycles
            // Math.min(Math.max(n % divider - i + 1, 0), i) gives the contribution from the partial cycle
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i);
            
            // Detailed Explanation:
            // - (n / divider): Number of complete groups of 'divider' numbers.
            // - Each complete group contributes 'i' number of '1's at the current digit position.
            // - n % divider: The remaining part after the complete groups.
            // - (n % divider - i + 1): The number of extra '1's contributed by the current incomplete group,
            //   but it should not exceed 'i', hence the Math.min with i.
        }
        
        // Casting the count back to int, as the result fits within int range.
        return (int) count;
    }
}
