// Last updated: 7/3/2025, 9:36:04 PM
class Solution {
    /**
     * Calculates the sum of the n smallest k-mirror numbers.
     *
     * A k-mirror number is a positive integer that is a palindrome in both base-10
     * and base-k. This method generates base-10 palindromes in increasing order,
     * checks if they are also palindromic in base-k, and sums the first n such numbers.
     *
     * @param k The base for the mirror check (2 <= k <= 9).
     * @param n The number of k-mirror numbers to find (1 <= n <= 30).
     * @return The sum of the n smallest k-mirror numbers.
     */
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;

        // Loop through the possible lengths of the base-10 palindromes.
        for (int len = 1; count < n; len++) {
            // Calculate the range for the first half of the number.
            // A number with 'len' digits has a first half of '(len + 1) / 2' digits.
            long start = (long) Math.pow(10, (len - 1) / 2);
            long end = (long) Math.pow(10, (len + 1) / 2);

            // Iterate through all possible "first half" numbers to build palindromes.
            for (long i = start; i < end; i++) {
                String firstHalf = Long.toString(i);
                String secondHalf = new StringBuilder(firstHalf).reverse().toString();

                long palindrome;
                // Construct the full palindrome from its halves.
                if (len % 2 == 1) {
                    // For odd length, the middle character is not repeated.
                    // e.g., i=123 -> "123" + "21" -> 12321
                    palindrome = Long.parseLong(firstHalf + secondHalf.substring(1));
                } else {
                    // For even length, the second half is a full reversal.
                    // e.g., i=12 -> "12" + "21" -> 1221
                    palindrome = Long.parseLong(firstHalf + secondHalf);
                }

                // Check if the generated base-10 palindrome is also a palindrome in base-k.
                if (isBaseKPalindrome(palindrome, k)) {
                    sum += palindrome;
                    count++;
                    if (count == n) {
                        // If we have found n numbers, return the sum immediately.
                        return sum;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * Checks if a number's representation in a given base is a palindrome.
     *
     * @param num The number to check.
     * @param k   The base.
     * @return True if the base-k representation is a palindrome, otherwise false.
     */
    private boolean isBaseKPalindrome(long num, int k) {
        // Convert the number to its string representation in the specified base.
        String baseKStr = Long.toString(num, k);
        int left = 0;
        int right = baseKStr.length() - 1;

        // Check if the string is a palindrome.
        while (left < right) {
            if (baseKStr.charAt(left) != baseKStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}