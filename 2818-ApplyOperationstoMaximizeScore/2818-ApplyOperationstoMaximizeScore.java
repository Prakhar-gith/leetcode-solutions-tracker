// Last updated: 3/29/2025, 9:36:23 AM
// Algorithm:
// 1. Precompute the prime scores for each number in nums. 
//    The prime score of a number is the number of distinct prime factors.
//    We can use a sieve to precompute smallest prime factors (spf) for numbers up to max(nums).
//
// 2. For each element nums[i] with prime score ps[i], count the number of subarrays in which it is the chosen multiplier.
//    An element nums[i] is chosen if:
//      - It is the leftmost among those with the highest prime score in that subarray.
//      - This can be computed by finding the "span" of subarrays where nums[i] remains dominant.
//    Let:
//      left = i - prev, where prev is the last index j < i with ps[j] >= ps[i] (if none, use -1)
//      right = next - i, where next is the first index j > i with ps[j] > ps[i] (if none, use n)
//    Then, count[i] = left * right.
//
// 3. Each subarray contributes an operation multiplying the current score by nums[i].
//    We now have pairs (x, count) where x = nums[i] and count = count[i] is the maximum number of times we can choose x.
//    To maximize the product, we want to use larger x as many times as possible.
//    
// 4. Sort these pairs in descending order by x, then greedily use as many operations as possible from the available count of that x.
//    Multiply the score by (x^taken) modulo mod, where taken = min(count, remaining k).
//
// 5. Return the final score modulo 10^9 + 7.
//
// Pseudo Code:
// -------------
// precompute spf[] up to max(nums)
// for i in 0...n-1:
//     ps[i] = computePrimeScore(nums[i])
// 
// Use monotonic stack to compute for each i:
//     prev[i] = last index j < i with ps[j] >= ps[i] (or -1 if none)
//     next[i] = first index j > i with ps[j] > ps[i] (or n if none)
//     count[i] = (i - prev[i]) * (next[i] - i)
// 
// Create list of pairs (nums[i], count[i]) and sort descending by nums[i].
// score = 1
// for each pair (x, cnt) in sorted list:
//     taken = min(cnt, k)
//     score = score * modPow(x, taken, mod) mod mod
//     k -= taken
//     if (k == 0) break
// return score
//
// Visualization Example:
// ----------------------
// Let nums = [8,3,9,3,8] and k = 2.
// Suppose prime scores: ps[0]=1 (8=2^3), ps[1]=1 (3), ps[2]=1 (9=3^2), ps[3]=1 (3), ps[4]=1 (8)
// For equal prime scores, the leftmost wins, so we need to compute spans using:
//   For index 2 (value 9): 
//     prev = index 1 (because ps[1] = 1 >= 1), left = 2-1 = 1
//     next = n (since no index j>2 with ps[j] >1), right = 5-2 = 3
//     count = 1*3 = 3.
// Similarly, others will have counts computed.
// Sorting pairs by value descending, we get (9,3), (8,?), (3,?)...
// We choose k=2 operations from the highest multiplier 9, so answer = 9^2 mod mod = 81.
//
// Implementation below:
 
class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int maximumScore(java.util.List<Integer> numsList, int k) {
        int n = numsList.size();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = numsList.get(i);
        }
        
        // Step 1: Precompute prime scores.
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }
        int[] spf = sieve(maxVal);
        int[] ps = new int[n];
        for (int i = 0; i < n; i++) {
            ps[i] = primeScore(nums[i], spf);
        }
        
        // Step 2: Compute spans using monotonic stack.
        int[] prev = new int[n];
        int[] next = new int[n];
        
        // For prev: last index j < i with ps[j] >= ps[i]
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && ps[stack.peek()] < ps[i]) {
                stack.pop();
            }
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        // For next: first index j > i with ps[j] > ps[i]
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && ps[stack.peek()] <= ps[i]) {
                stack.pop();
            }
            next[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        // Compute count for each index i.
        // count[i] = (i - prev[i]) * (next[i] - i)
        long[] count = new long[n];
        for (int i = 0; i < n; i++) {
            long left = i - (prev[i] + 1) + 1; // equals i - prev[i]
            long right = next[i] - i;
            count[i] = left * right;
        }
        
        // Step 3: Create pairs (nums[i], count[i]) and sort in descending order by nums[i]
        java.util.List<Pair> pairs = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new Pair(nums[i], count[i]));
        }
        pairs.sort((a, b) -> Integer.compare(b.value, a.value));
        
        // Step 4: Greedily choose operations
        long score = 1;
        for (Pair p : pairs) {
            if (k <= 0) break;
            long take = Math.min(p.count, (long) k);
            score = (score * modPow(p.value, take, MOD)) % MOD;
            k -= take;
        }
        
        return (int) score;
    }
    
    // Helper class to hold (value, count)
    private static class Pair {
        int value;
        long count;
        Pair(int value, long count) {
            this.value = value;
            this.count = count;
        }
    }
    
    // Sieve of Eratosthenes to compute smallest prime factor (spf) for numbers up to max
    private int[] sieve(int max) {
        int[] spf = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= max; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= max; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        return spf;
    }
    
    // Compute prime score: number of distinct prime factors of x using precomputed spf.
    private int primeScore(int x, int[] spf) {
        int count = 0;
        int prev = -1;
        while (x > 1) {
            int factor = spf[x];
            if (factor != prev) {
                count++;
                prev = factor;
            }
            x /= factor;
        }
        return count;
    }
    
    // Fast modular exponentiation: computes (base^exp) % mod
    private long modPow(long base, long exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
