// Last updated: 11/1/2025, 11:35:21 PM
import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        
        Set<Integer> nodesToDelete = new HashSet<>();
        for (int num : nums) {
            nodesToDelete.add(num);
        }
        
        while (head != null && nodesToDelete.contains(head.val)) {
            head = head.next;
        }
        
        if (head == null) {
            return null;
        }
        ListNode current = head;
        
        while (current.next != null) {
            if (nodesToDelete.contains(current.next.val)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return head;
    }
}