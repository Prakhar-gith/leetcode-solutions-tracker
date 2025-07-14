// Last updated: 7/14/2025, 8:38:12 PM
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val, this.next = next; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        /*
         * =================================================================
         * EXPLANATION
         * =================================================================
         * Hum linked list ko traverse karenge from head to tail. Har node pe, hum apne
         * current decimal result ko 2 se multiply karenge (ya mathematically, ek bit left shift karenge)
         * aur phir current node ki value (jo ya toh 0 hai ya 1) usme add kar denge.
         * Yeh process binary se decimal conversion ke standard tareeke ko simulate karta hai jab aap
         * bits ko left-to-right (most significant to least significant) process karte hain.
         *
         * =================================================================
         * ALGORITHM (English)
         * =================================================================
         * 1. Initialize an integer variable 'decimalValue' to 0. This will store the final result.
         * 2. Start traversing the linked list from the 'head' node.
         * 3. Continue to loop as long as the current node is not null.
         * 4. Inside the loop, for each node:
         * a. Update 'decimalValue' by shifting its bits one position to the left ('decimalValue' << 1).
         * This is equivalent to multiplying 'decimalValue' by 2, making space for the next bit.
         * b. Perform a bitwise OR of the updated 'decimalValue' with the current node's value ('head.val').
         * This effectively adds the current bit to our number.
         * c. Move to the next node in the list.
         * 5. Once the loop finishes (after the last node), return the final 'decimalValue'.
         *
         * =================================================================
         * PSEUDOCODE
         * =================================================================
         * function getDecimalValue(head):
         * decimalValue = 0
         * currentNode = head
         * while currentNode is not NULL:
         * // Shift left by 1 and add the new bit
         * decimalValue = (decimalValue * 2) + currentNode.val
         * // Or using bitwise operators:
         * // decimalValue = (decimalValue << 1) | currentNode.val
         * currentNode = currentNode.next
         * return decimalValue
         *
         * =================================================================
         * VISUALIZATION
         * =================================================================
         * Input: head = [1,0,1]
         *
         * Initial state:
         * decimalValue = 0
         * currentNode --> [1] -> [0] -> [1] -> null
         *
         * --- Iteration 1 ---
         * currentNode.val is 1.
         * decimalValue = (decimalValue * 2) + currentNode.val
         * decimalValue = (0 * 2) + 1  => 1
         * Move to next node.
         *
         * --- Iteration 2 ---
         * currentNode.val is 0.
         * decimalValue = (decimalValue * 2) + currentNode.val
         * decimalValue = (1 * 2) + 0  => 2
         * Move to next node.
         *
         * --- Iteration 3 ---
         * currentNode.val is 1.
         * decimalValue = (decimalValue * 2) + currentNode.val
         * decimalValue = (2 * 2) + 1  => 5
         * Move to next node (which is null).
         *
         * --- End of Loop ---
         * Loop terminates as currentNode is null.
         * Return final decimalValue: 5
         * =================================================================
        */

        // Initialize num to 0. Yeh hamara final decimal number store karega.
        int num = 0;

        // Start traversing the list from the head node.
        // Jab tak hum list ke end tak nahi pahunch jate (head null nahi ho jata), tab tak loop chalega.
        while (head != null) {
            // Left shift the current number by 1 bit.
            // Iska matlab hai hum number ko 2 se multiply kar rahe hain.
            // For example, agar num 2 (binary 10) hai, to left shift ke baad 4 (binary 100) ho jayega.
            // Aisa karke hum next bit ke liye jagah bana rahe hain.
            // Then, perform a bitwise OR with the current node's value (0 or 1).
            // Yeh step `num = num * 2 + head.val;` ke barabar hai.
            num = (num << 1) | head.val;

            // Move to the next node in the list.
            // Agle node pe jao.
            head = head.next;
        }

        // After the loop, 'num' will hold the complete decimal value.
        // Loop ke baad, 'num' me final decimal value hogi, usko return kar do.
        return num;
    }
}