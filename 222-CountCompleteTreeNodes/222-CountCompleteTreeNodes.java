/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// Algorithm:
// 1. Agar tree null hai, toh 0 return karo.
// 2. Calculate karo leftDepth (leftmost path depth) aur rightDepth (rightmost path depth) using helper functions.
// 3. Agar leftDepth equals rightDepth hai, iska matlab tree ek perfect binary tree hai, toh total nodes = (2^depth) - 1.
// 4. Agar depths equal nahi hai, toh tree perfect nahi hai. Isliye, recursively count karo left aur right subtrees ke nodes aur add 1 for the root.
// 5. Return the computed total count.

// Pseudo Code:
// -------------
// function countNodes(root):
//     if root == null:
//         return 0
//     leftDepth = getLeftDepth(root)
//     rightDepth = getRightDepth(root)
//     if leftDepth == rightDepth:
//         return (2^(leftDepth)) - 1
//     else:
//         return 1 + countNodes(root.left) + countNodes(root.right)
//
// function getLeftDepth(node):
//     depth = 0
//     while node is not null:
//         depth++
//         node = node.left
//     return depth
//
// function getRightDepth(node):
//     depth = 0
//     while node is not null:
//         depth++
//         node = node.right
//     return depth

/*
   Visualization Example:

             1
           /   \
          2     3
         / \   /
        4   5 6

   - leftDepth from root: 1 -> 2 -> 4, so leftDepth = 3.
   - rightDepth from root: 1 -> 3, so rightDepth = 2.
   
   Since leftDepth != rightDepth, tree is not perfect.
   Thus, we count nodes recursively:
       countNodes(root) = 1 (root) + countNodes(2's subtree) + countNodes(3's subtree)
   For any perfect subtree, the formula (2^depth - 1) applies.
*/

class Solution {
    public int countNodes(TreeNode root) {
        // Base case: agar tree empty hai, return 0
        if (root == null) {
            return 0;
        }
        
        // Calculate the depth of the leftmost path (max depth)
        int leftDepth = getLeftDepth(root);
        // Calculate the depth of the rightmost path
        int rightDepth = getRightDepth(root);
        
        // Agar dono depths same hain, toh tree perfect hai
        if (leftDepth == rightDepth) {
            // Perfect binary tree ke liye formula: (2^depth) - 1
            return (1 << leftDepth) - 1; // (1 << leftDepth) means 2^leftDepth
        } else {
            // Agar tree perfect nahi hai, toh recursively count left and right subtrees, and add 1 for the root
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    
    // Helper function to compute depth by traversing left children
    private int getLeftDepth(TreeNode node) {
        int depth = 0;
        // Traverse the leftmost branch
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }
    
    // Helper function to compute depth by traversing right children
    private int getRightDepth(TreeNode node) {
        int depth = 0;
        // Traverse the rightmost branch
        while (node != null) {
            depth++;
            node = node.right;
        }
        return depth;
    }
}
