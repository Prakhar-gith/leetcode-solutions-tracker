// Last updated: 6/17/2025, 10:16:48 AM
class Solution {
    static final int MOD = 1_000_000_007;
    long[] fact;
    long[] invFact;

    // Modular exponentiation: (base^exp) % MOD
    long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    // Precompute factorials and inverse factorials
    void precomputeFactorials(int maxVal) {
        fact = new long[maxVal + 1];
        invFact = new long[maxVal + 1];
        fact[0] = 1;
        invFact[0] = 1;
        for (int i = 1; i <= maxVal; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
            invFact[i] = power(fact[i], MOD - 2); // Modular inverse using Fermat's Little Theorem
        }
    }

    // Combinations nCk = n! / (k! * (n-k)!) % MOD
    long nCk(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        return (((fact[n] * invFact[k]) % MOD) * invFact[n - k]) % MOD;
    }

    public int countGoodArrays(int n, int m, int k) {
        // Constraints: 1 <= n <= 10^5, 1 <= m <= 10^5, 0 <= k <= n - 1

        // Maximum value for factorials needed is n-1
        precomputeFactorials(n - 1);

        long combinations = nCk(n - 1, k);

        long term_m = m;
        long term_m_minus_1_power;

        if (n - k - 1 < 0) { // This case should not happen given k <= n-1
            // If n-k-1 is negative, it implies n-1 < k, which contradicts k <= n-1
            // However, it's good to be robust. If n-k-1 were negative,
            // power(m-1, negative) is undefined in modular arithmetic context
            // or means inverse, but here it indicates invalid scenario or 0.
            // But problem states k <= n-1, so n-k-1 >= 0.
            term_m_minus_1_power = 0; // Or handle as an error if it could happen.
        } else {
            term_m_minus_1_power = power(m - 1, n - k - 1);
        }
        

        long ans = (combinations * term_m) % MOD;
        ans = (ans * term_m_minus_1_power) % MOD;

        return (int) ans;
    }
}