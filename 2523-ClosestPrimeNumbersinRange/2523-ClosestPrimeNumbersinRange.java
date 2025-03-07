// Algorithm:
// 1. Pehle Sieve of Eratosthenes use karke [2, right] tak ke saare prime numbers generate karo.
// 2. Sieve ke through, ek boolean array isPrime[] create karo jisme true ka matlab hai ki number prime hai.
// 3. Uske baad, left se right tak iterate karo aur saare prime numbers ko collect karo.
// 4. Agar collected primes ki list mein 2 se kam elements hain, toh return [-1, -1] (kyunki pair nahi ban sakta).
// 5. Warna, list mein adjacent primes ke beech ka difference calculate karo aur minimum gap waala pair select karo.
// 6. Agar multiple pairs same gap ke hain, toh naturally pehle aane waala (chhota num1) choose hoga.
// 7. Return the answer as an array [num1, num2].

/*
   Pseudo Code:
   -------------
   function closestPrimes(left, right):
       Create boolean array isPrime of size right+1 and initialize all to true (for indices >=2)
       for i from 2 to sqrt(right):
           if isPrime[i] is true:
               for j from i*i to right, step i:
                   isPrime[j] = false
       primes = empty list
       for i from left to right:
           if i>=2 and isPrime[i] is true:
               add i to primes
       if primes.size < 2:
           return [-1, -1]
       bestDiff = infinity, answer = [-1, -1]
       for i from 1 to primes.size - 1:
           diff = primes[i] - primes[i-1]
           if diff < bestDiff:
               bestDiff = diff
               answer = [primes[i-1], primes[i]]
       return answer

   Visualization Example:
   ----------------------
   For left = 10, right = 19:
   - Sieve generate primes: [11, 13, 17, 19]
   - Differences: 13-11=2, 17-13=4, 19-17=2
   - Minimum difference = 2; Pairs: [11,13] and [17,19]
   - Since 11 < 17, answer is [11,13]
*/

class Solution {
    public int[] closestPrimes(int left, int right) {
        // Sieve of Eratosthenes: Create boolean array to mark primes up to 'right'
        boolean[] isPrime = new boolean[right + 1];
        // Initially mark all numbers >=2 as prime
        for (int i = 2; i <= right; i++) {
            isPrime[i] = true;
        }
        
        // Mark non-prime numbers using Sieve method
        for (int i = 2; i * i <= right; i++) {
            if (isPrime[i]) {
                // Mark all multiples of i starting from i*i as not prime
                for (int j = i * i; j <= right; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // List to store primes within the given range [left, right]
        List<Integer> primes = new ArrayList<>();
        // Iterate over the range and collect primes
        for (int i = Math.max(left, 2); i <= right; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        // Agar collected primes 2 ya usse kam hain, return [-1, -1]
        if (primes.size() < 2) {
            return new int[]{-1, -1};
        }
        
        // Initialize variables for best (minimum) difference and answer pair
        int bestDiff = Integer.MAX_VALUE;
        int[] ans = new int[]{-1, -1};
        
        // Iterate over adjacent primes to find the pair with minimum difference
        for (int i = 1; i < primes.size(); i++) {
            int diff = primes.get(i) - primes.get(i - 1);
            // Agar found diff chhota hai bestDiff se, update bestDiff and answer pair
            if (diff < bestDiff) {
                bestDiff = diff;
                ans[0] = primes.get(i - 1);
                ans[1] = primes.get(i);
            }
        }
        
        // Return the answer pair with the smallest gap (and smallest num1 if equal gap)
        return ans;
    }
}
