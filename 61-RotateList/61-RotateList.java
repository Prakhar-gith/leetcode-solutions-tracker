/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// Algorithm:
// 1. Calculate the length of the linked list and identify its tail.
// 2. Compute effective rotations using k % length (because rotating by the list's length results in the same list).
// 3. If k becomes 0 after modulo, return the original head.
// 4. Find the new tail node by moving (length - k - 1) steps from the head.
// 5. The node next to new tail becomes the new head.
// 6. Connect the original tail to the original head to form a circular linked list.
// 7. Break the circle by setting newTail.next to null.
// 8. Return the new head of the rotated list.

/*
   Pseudo Code:
   -------------
   function rotateRight(head, k):
       if head is null or head.next is null:
           return head
       length = 1
       tail = head
       while tail.next != null:
           tail = tail.next
           length++
       k = k mod length
       if k == 0:
           return head
       stepsToNewTail = length - k - 1
       newTail = head
       for i from 0 to stepsToNewTail:
           newTail = newTail.next
       newHead = newTail.next
       tail.next = head
       newTail.next = null
       return newHead

   Visualization:
   --------------
   For list: 1 -> 2 -> 3 -> 4 -> 5 and k = 2:
   - Calculate length = 5, tail = node 5.
   - k = 2 % 5 = 2.
   - New tail position = 5 - 2 - 1 = 2 (0-indexed; node with value 3).
   - New head = node with value 4.
   - Link tail (5) to head (1) forming a circle.
   - Break the circle by setting node 3's next to null.
   - Final rotated list: 4 -> 5 -> 1 -> 2 -> 3.
*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Agar list empty ya sirf ek node hai, to directly head return karo
        if (head == null || head.next == null) return head;
        
        // Calculate karte hain length of list aur find the tail node
        int length = 1; // head already count mein hai
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // k ko reduce karo effective rotations ke liye (k mod length)
        k = k % length;
        // Agar k 0 hai, list same hi rahegi, so return original head
        if (k == 0) return head;
        
        // Find the new tail: list ko rotate karne ke liye newTail should be at (length - k - 1)th position
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;
        // Move newTail required steps
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        
        // New head will be newTail.next
        ListNode newHead = newTail.next;
        
        // Form a circular list by linking tail to the head
        tail.next = head;
        
        // Break the circle by setting newTail.next to null
        newTail.next = null;
        
        // Return new head which points to the rotated list
        return newHead;
    }
}
