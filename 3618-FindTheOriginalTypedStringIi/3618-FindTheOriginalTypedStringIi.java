// Last updated: 8/30/2025, 6:56:39 PM
class Solution {

    private final int MOD = 1_000_000_007;

    public int possibleStringCount(String word, int k) {
        int n = word.length();
        if (n == 0) {
            return 0;
        }

        // 1. Parse word into groups and get m and counts of group lengths
        Map<Integer, Integer> counts = new HashMap<>();
        int m = 0;
        if (n > 0) {
            m = 1;
            int currentLen = 1;
            for (int i = 1; i < n; i++) {
                if (word.charAt(i) == word.charAt(i - 1)) {
                    currentLen++;
                } else {
                    counts.put(currentLen, counts.getOrDefault(currentLen, 0) + 1);
                    m++;
                    currentLen = 1;
                }
            }
            counts.put(currentLen, counts.getOrDefault(currentLen, 0) + 1);
        }

        // 2. Calculate total number of possible original strings
        long total = 1;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            long l = entry.getKey();
            long count = entry.getValue();
            total = (total * power(l, count)) % MOD;
        }

        if (k == 1) {
            return (int) total;
        }

        // 3. Compute coefficients of P(x) = product( (1-x^l)^count[l] ) mod x^k
        long[] pCoeffs = new long[k];
        pCoeffs[0] = 1;

        long[] inv = new long[k];
        if (k > 1) {
            inv[1] = 1;
            for (int i = 2; i < k; i++) {
                inv[i] = MOD - (long) (MOD / i) * inv[MOD % i] % MOD;
            }
        }
        
        List<Integer> distinctLengths = new ArrayList<>(counts.keySet());
        Collections.sort(distinctLengths);

        for (int l : distinctLengths) {
            if (l >= k) {
                continue;
            }
            int count = counts.get(l);
            
            // Get sparse representation of (1-x^l)^count
            List<long[]> termPoly = new ArrayList<>();
            termPoly.add(new long[]{0, 1}); // power, coeff for term 1
            long c_nk = 1;
            for (int j = 1; j * l < k; j++) {
                c_nk = c_nk * (count - j + 1) % MOD * inv[j] % MOD;
                long coeff = (j % 2 == 1) ? (MOD - c_nk) : c_nk;
                termPoly.add(new long[]{(long) j * l, coeff});
            }

            // Multiply pCoeffs by termPoly
            long[] newPCoeffs = new long[k];
            for (int i = 0; i < k; i++) {
                if (pCoeffs[i] == 0) continue;
                for (long[] term : termPoly) {
                    int power = (int) term[0];
                    long coeff = term[1];
                    if (i + power < k) {
                        newPCoeffs[i + power] = (newPCoeffs[i + power] + pCoeffs[i] * coeff) % MOD;
                    }
                }
            }
            pCoeffs = newPCoeffs;
        }

        // 4. Compute coefficients of A(x) = (1-x)^(-m) mod x^k
        long[] aCoeffs = new long[k];
        aCoeffs[0] = 1;
        long c_nk_a = 1;
        for (int j = 1; j < k; j++) {
            c_nk_a = c_nk_a * (m + j - 1) % MOD * inv[j] % MOD;
            aCoeffs[j] = c_nk_a;
        }

        // 5. Compute C(x) = A(x) * P(x) mod x^k
        long[] cCoeffs = new long[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= i; j++) {
                cCoeffs[i] = (cCoeffs[i] + aCoeffs[j] * pCoeffs[i - j]) % MOD;
            }
        }

        // 6. Calculate sum of invalid counts (length < k)
        long sInvalid = 0;
        // g_coeffs[j] = c_coeffs[j-m]
        for (int j = 0; j < k; j++) {
            if (j >= m) {
                sInvalid = (sInvalid + cCoeffs[j - m]) % MOD;
            }
        }
        
        long result = (total - sInvalid + MOD) % MOD;
        return (int) result;
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}