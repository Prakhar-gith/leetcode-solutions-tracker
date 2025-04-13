// Last updated: 4/13/2025, 9:21:04 AM
// Algorithm:
// 1. We are given that a digit string of length n is "good" if:
//      - The digits at even indices (0-indexed) are even. There are 5 choices: {0,2,4,6,8}.
//      - The digits at odd indices are prime. The only prime digits allowed are: {2,3,5,7} (4 choices).
// 2. Let evenCount be the number of even-indexed positions and oddCount be the number of odd-indexed positions.
//      - For an n-length string, evenCount = (n + 1) / 2  (because index 0 is even, and so on)
//      - And oddCount = n / 2.
// 3. Since leading zeros are allowed, each good digit string is formed independently at each position.
//      - Total good numbers = 5^(evenCount) * 4^(oddCount).
// 4. Because n can be as large as 10^15, we must compute the exponentiation using fast modular exponentiation.
// 5. The answer should be returned modulo 10^9 + 7.
//
// Pseudo Code:
// -------------
// evenCount = (n + 1) / 2
// oddCount = n / 2
// result = modPow(5, evenCount, mod) * modPow(4, oddCount, mod) mod mod
// return result
//
// Visualization Example:
// ----------------------
// Example 1: n = 1:
//   evenCount = (1+1)/2 = 1, oddCount = 0
//   result = 5^1 * 4^0 = 5 * 1 = 5
//
// Example 2: n = 4:
//   evenCount = (4+1)/2 = 2, oddCount = 4/2 = 2
//   result = 5^2 * 4^2 = 25 * 16 = 400

class Solution {
    static final long MOD = 1_000_000_007;
    
    public int countGoodNumbers(long n) {
        // Compute number of positions at even indices and odd indices.
        long evenCount = (n + 1) / 2; // (n+1)/2 even positions (0-indexed)
        long oddCount = n / 2;        // n/2 odd positions
        
        // Compute 5^(evenCount) mod MOD and 4^(oddCount) mod MOD using fast modular exponentiation.
        long evenWays = modPow(5, evenCount, MOD);
        long oddWays = modPow(4, oddCount, MOD);
        
        // Multiply the two counts modulo MOD and return result.
        return (int)((evenWays * oddWays) % MOD);
    }
    
    // Fast modular exponentiation function: Computes (base^exp) mod mod efficiently.
    private long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {  // If exp is odd, multiply result by base.
                result = (result * base) % mod;
            }
            base = (base * base) % mod; // Square the base.
            exp >>= 1;                // Divide exp by 2.
        }
        return result;
    }
}
