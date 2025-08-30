// Last updated: 8/30/2025, 6:56:40 PM
class Solution {
    public char kthCharacter(long k, int[] operations) {
        // current_k is 1-indexed, so we can directly use it.
        // current_offset tracks the character shift from 'a'
        long current_k = k;
        int current_offset = 0;

        // lengths[i] will store the length of the string *before* operations[i] is applied.
        // lengths[0] is the initial length of "a", which is 1.
        // lengths[operations.length] will be the total length of the string after all operations.
        long[] lengths = new long[operations.length + 1];
        lengths[0] = 1; // Initial length "a"

        for (int i = 0; i < operations.length; i++) {
            // The length before the next operation is double the length before the current operation.
            // This is because each operation doubles the string length.
            // lengths[i+1] = lengths[i] * 2;
            // However, we need to be careful about overflow if length becomes too large.
            // Given k <= 10^14, we only care about lengths up to ~2 * 10^14.
            // If lengths[i] * 2 exceeds 2 * 10^14 (or a sufficiently large number greater than max_k),
            // we can cap it, because k will always fall within the first half anyway.
            // A safer approach for lengths[i+1] is to cap it if it exceeds k or a slightly larger value.
            // Let's use a very large number that's greater than 10^14 to cap it, e.g., 2 * 10^14 + 7
            // Or, simply use Long.MAX_VALUE and rely on k being within bounds.
            // Since k is at most 10^14, 2^50 is approx 10^15. So 2^50 is sufficient.
            // Lengths can grow up to 2^100, which exceeds Long.MAX_VALUE.
            // The crucial observation is that if length_before_this_op * 2 already exceeds K,
            // then current_k will always be less than or equal to length_before_this_op.
            // We just need to know if current_k is in the first or second half.
            // So if lengths[i] is already large enough such that current_k will always be in the first half of a future string,
            // we can just keep it at a capped value like Long.MAX_VALUE.
            
            // To simplify, we calculate the lengths normally.
            // If lengths[i] * 2 overflows, it will wrap around or become negative.
            // This needs to be handled.
            // Let's cap lengths[i+1] at a value that's guaranteed to be greater than k,
            // for example, k + 1, or just Long.MAX_VALUE / 2 (to prevent overflow on * 2).
            // A more robust approach: if length_before_this_op (lengths[i]) is already >= k,
            // then any future operation will result in a length that is also >= k.
            // So we can cap the length at k + 1 to avoid overflow for subsequent calculations.
            
            if (lengths[i] > k) { // If the current segment length is already more than k, we don't need to calculate exact larger lengths.
                                  // This is an optimization.
                lengths[i+1] = lengths[i]; // Keep it capped.
            } else {
                // Ensure no overflow for lengths[i] * 2.
                // If lengths[i] > Long.MAX_VALUE / 2, then lengths[i] * 2 will overflow.
                // In such a case, we can simply cap it at Long.MAX_VALUE.
                if (lengths[i] > Long.MAX_VALUE / 2) {
                    lengths[i+1] = Long.MAX_VALUE; // Cap at max long value if overflow imminent
                } else {
                    lengths[i+1] = lengths[i] * 2;
                }
            }
        }

        // Iterate backward through the operations
        for (int i = operations.length - 1; i >= 0; i--) {
            // length_before_this_op is the length of the string before operations[i] was applied.
            // This is stored in lengths[i].
            long length_before_this_op = lengths[i];

            // If current_k falls into the second half of the string
            if (current_k > length_before_this_op) {
                current_k -= length_before_this_op; // Adjust k to find its position in the second half
                
                // If the operation was type 1, the second half characters are shifted
                if (operations[i] == 1) {
                    current_offset = (current_offset + 1) % 26;
                }
            }
            // Else (current_k <= length_before_this_op), it falls into the first half.
            // No change to current_k or current_offset, as the first half is the original string.
        }

        // After processing all operations, current_offset holds the total shift from 'a'
        return (char)('a' + current_offset);
    }
}