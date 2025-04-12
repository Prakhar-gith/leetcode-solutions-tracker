// Last updated: 4/13/2025, 1:32:39 AM
class Solution {
    public long countGoodIntegers(int n, int k) {
        long[] fac = new long[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            fac[i] = fac[i - 1] * i;
        }

        long ans = 0;
        Set<String> seen = new HashSet<>();
        int halfLength = (n + 1) / 2;
        int base = (int) Math.pow(10, halfLength - 1);
        int limit = (int) Math.pow(10, halfLength);

        for (int i = base; i < limit; i++) {
            String firstHalf = String.valueOf(i);
            String secondHalf = new StringBuilder(firstHalf).reverse().toString();
            String palindrome = firstHalf + secondHalf.substring(n % 2);
            if (Long.parseLong(palindrome) % k != 0) continue;

            char[] sortedDigits = palindrome.toCharArray();
            Arrays.sort(sortedDigits);
            String signature = new String(sortedDigits);
            if (seen.contains(signature)) continue;
            seen.add(signature);

            int[] freq = new int[10];
            for (char c : palindrome.toCharArray()) {
                freq[c - '0']++;
            }
            // First digit cannot be zero.
            int firstDigitChoices = n - freq[0];
            long count = firstDigitChoices * fac[n - 1];
            for (int f : freq) {
                count /= fac[f];
            }
            ans += count;
        }
        return ans;
    }
}
