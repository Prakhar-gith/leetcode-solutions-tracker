// Algorithm:
// 1. Start with initial state '0' because s is initially all zeros.
// 2. Initialize a flips counter to 0.
// 3. Iterate through each character in target:
//      - Agar current character (c) previous state se alag hai, iska matlab flip chahiye.
//      - Increment flips and update previous state to current character.
// 4. Return the flips count as the minimum number of operations needed.
//
// Pseudo Code:
// -------------
// function minFlips(target):
//     flips = 0
//     prev = '0'
//     for each char c in target:
//         if c != prev:
//             flips++
//             prev = c
//     return flips
//
// Visualization:
// --------------
// Target: "10111", Initial state: prev = '0'
// Index 0: '1' != '0' -> flip required, flips = 1, update prev = '1'
// Index 1: '0' != '1' -> flip required, flips = 2, update prev = '0'
// Index 2: '1' != '0' -> flip required, flips = 3, update prev = '1'
// Index 3: '1' == '1' -> no flip
// Index 4: '1' == '1' -> no flip
// Final flips = 3

class Solution {
    public int minFlips(String target) {
        // s initially is all zeros, isliye starting state '0'
        char prev = '0';
        int flips = 0; // Yeh counter rakhega kitni flip operations chahiye
        
        // Loop target ke har character pe
        for (char c : target.toCharArray()) {
            // Agar current bit aur previous bit alag hain, toh flip karna padega
            if (c != prev) {
                flips++;      // Flip operation count badhao
                prev = c;     // Update previous state to current bit, flip ke baad state change ho jata hai
            }
        }
        
        // Total flips return karo jo minimum operations required hain target achieve karne ke liye
        return flips;
    }
}
