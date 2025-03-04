// Algorithm:
// 1. Start with a required removal amount 'm' = 10 for Alice's first turn.
// 2. Alternate turns between Alice and Bob.
// 3. On each turn, if the remaining stones (n) is less than the required removal 'm', then the current player loses.
// 4. Otherwise, subtract 'm' from n and then decrement 'm' by 1 (since next move requires 1 fewer stone).
// 5. Continue until a player cannot remove the required stones.
// 6. Return true if Alice wins, false otherwise (i.e., if Bob wins).
//
// Pseudo Code:
// -------------
// function stoneRemovalGame(n):
//     m = 10
//     aliceTurn = true
//     while (true):
//         if n < m:
//             return !aliceTurn   // current player loses, so winner is the opposite
//         n = n - m
//         m = m - 1
//         aliceTurn = !aliceTurn
//
// Visualization Example for n = 12:
// ------------------------------------------------
// Initial n = 12, m = 10, turn = Alice
//   - Alice: 12 >= 10, so remove 10 stones, n becomes 2, m becomes 9, turn switches to Bob.
//   - Bob: Now n = 2, but required m = 9. Since 2 < 9, Bob cannot move and loses.
//   Result: Alice wins (true)

class Solution {
    public boolean canAliceWin(int n) {
        int currentMove = 10; // Alice starts with removing 10 stones
        boolean aliceTurn = true; // Alice's turn initially
        
        // Loop until one player cannot make the required move
        while (true) {
            // Agar remaining stones n, current required move se kam hain, then current player loses.
            if (n < currentMove) {
                // Current player loses; winner is the opposite.
                return !aliceTurn; // Agar alice ka turn hai and she can't move, then bob wins (false) and vice versa.
            }
            
            // Otherwise, subtract the current move from n
            n -= currentMove;
            // Decrement the required move by 1 for the next turn
            currentMove--;
            // Switch turns between Alice and Bob
            aliceTurn = !aliceTurn;
        }
    }
}
