// Algorithm:
// 1. Use Brian Kernighan's algorithm to count set bits.
// 2. The idea is that for any number n, performing n & (n - 1) removes the lowest set bit from n.
// 3. Initialize a counter to 0, then iterate while n is not zero.
//    - In each iteration, update n = n & (n - 1) and increment the counter.
// 4. Once n becomes 0, the counter contains the total number of set bits (1s) in the binary representation of the original n.

// Pseudo Code:
// -------------
// function hammingWeight(n):
//     count = 0
//     while n != 0:
//         n = n & (n - 1)   // Remove the lowest set bit
//         count++           // Increment counter
//     return count

// Visualization Example for n = 11 (binary 1011):
// -------------------------------------------------
// Initial n = 1011 (binary), count = 0
// Iteration 1: n = 1011 & 1010 = 1010, count = 1
// Iteration 2: n = 1010 & 1001 = 1000, count = 2
// Iteration 3: n = 1000 & 0111 = 0000, count = 3
// Final count = 3, which is the number of 1s in binary 1011.

class Solution {
    // Function to return the number of 1 bits (set bits) in the binary representation of n
    public int hammingWeight(int n) {
        int count = 0;  // Yeh counter hai jo count karega total set bits
        
        // Loop jab tak n 0 nahi ho jata, matlab jab tak set bits remaining hain
        while (n != 0) {
            // n & (n-1) removes the lowest set bit from n
            n = n & (n - 1); // Yeh operation n me se lowest 1 bit hata deta hai
            count++;         // Ek set bit remove hua, isliye count badha do
        }
        
        // Return the final count of set bits
        return count;
    }
}
