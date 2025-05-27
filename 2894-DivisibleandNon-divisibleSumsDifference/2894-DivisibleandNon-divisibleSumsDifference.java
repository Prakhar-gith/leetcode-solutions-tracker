// Last updated: 5/27/2025, 8:59:05 PM
class Solution {

    // Algorithm:
    // 1. Compute total sum T of integers from 1 to n: T = n*(n+1)/2.
    // 2. Compute how many are divisible by m: k = n / m.
    // 3. Sum of multiples of m from m to k*m is sumDiv = m * (1 + 2 + ... + k) = m * k*(k+1)/2.
    // 4. num1 (non-divisible sum) = T - sumDiv.
    //    num2 (divisible sum)     = sumDiv.
    // 5. Return num1 - num2 = (T - sumDiv) - sumDiv = T - 2*sumDiv.

    /*
    Pseudocode:

    function differenceOfSums(n, m):
        T = n*(n+1)/2
        k = n / m
        sumDiv = m * k*(k+1)/2
        return T - 2*sumDiv
    */

    /*
    Visualization:

      Example n=10, m=3:
        T = 10*11/2 = 55
        k = 10/3 = 3
        sumDiv = 3*(3*4/2) = 3*6 = 18
        result = 55 - 2*18 = 55 - 36 = 19
    */

    public int differenceOfSums(int n, int m) {
        // total sum 1..n
        long T = (long) n * (n + 1) / 2;
        // count of multiples of m
        long k = n / m;
        // sum of multiples of m = m * (1 + 2 + ... + k)
        long sumDiv = m * (k * (k + 1) / 2);
        // difference = total minus twice divisible sum
        long result = T - 2 * sumDiv;
        return (int) result;
    }
}
