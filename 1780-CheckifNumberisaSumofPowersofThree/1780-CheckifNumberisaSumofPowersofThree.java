// Algorithm:
// 1. Convert the number 'n' into its base-3 representation by repeatedly taking modulo 3 and dividing by 3.
// 2. Agar kisi bhi step mein remainder 2 milta hai, iska matlab hai ki ek power of three do baar use ho raha hai,
//    which violates the "distinct powers" condition.
// 3. Agar pure conversion ke dauran kabhi bhi remainder 2 na aaye, then n can be represented as the sum of distinct powers of three.
// 4. Return true agar valid, else false.
//
// Pseudo Code:
// -------------
// function checkIfSumOfPowers(n):
//     while n > 0:
//         remainder = n % 3
//         if remainder == 2:
//             return false
//         n = n / 3
//     return true
//
// Visualization Example for n = 12:
// ------------------------------------------------
// 12 % 3 = 0      -> n becomes 4
// 4 % 3  = 1      -> n becomes 1
// 1 % 3  = 1      -> n becomes 0
// Base-3 representation = "110" (only 1s and 0s) -> valid sum of distinct powers of three
//
// Visualization Example for n = 21:
// ------------------------------------------------
// 21 % 3 = 0      -> n becomes 7
// 7 % 3  = 1      -> n becomes 2
// 2 % 3  = 2      -> n becomes 0
// Base-3 representation = "210" (contains a 2) -> not a valid sum of distinct powers of three

class Solution {
    public boolean checkPowersOfThree(int n) {
        // Iterate until n becomes 0
        while (n > 0) {
            int remainder = n % 3; // n ko base 3 mein convert karte hue current digit nikal rahe hain
            // Agar remainder 2 hai, toh matlab yeh power of three repeat ho raha hai (duplicate)
            if (remainder == 2) {
                return false; // Condition violate ho gayi, so return false
            }
            n = n / 3; // n ko update karo by dividing by 3 to check next digit
        }
        // Agar loop complete ho jaye bina kisi remainder 2 ke, then n is a valid sum of distinct powers of three
        return true;
    }
}
