
// Algorithm:
// 1. Use a stack to simulate an in-order traversal of the BST.
// 2. In the constructor, initialize the stack and push all left children from the root onto the stack.
// 3. The next() method pops the top node from the stack, which is the next smallest element.
//    - After popping, if the node has a right child, push all its left children onto the stack.
// 4. The hasNext() method simply checks if the stack is not empty, meaning there are more nodes to process.

/*
   Pseudo Code:
   -------------
   function BSTIterator(root):
       stack = new Stack
       pushLeft(root)
   
   function pushLeft(node):
       while node != null:
           stack.push(node)
           node = node.left
   
   function next():
       node = stack.pop()
       if node.right exists:
           pushLeft(node.right)
       return node.val
   
   function hasNext():
       return (stack is not empty)
       
   Visualization Example:
   ----------------------
   Consider BST:
            7
           / \
          3   15
             /  \
            9    20

   Initial Stack (after pushLeft(root)):
       pushLeft(7): stack = [7, 3]  (top is 3)
   
   Calling next():
       - Pop 3 from stack -> returns 3, stack becomes [7].
   
   Calling next() again:
       - Pop 7 from stack -> returns 7.
       - Since 7 has a right child (15), pushLeft(15):
            push 15, then pushLeft(15.left=9) -> push 9.
       - Updated stack = [15, 9] (top is 9).
   
   This process continues to yield the in-order sequence.
*/
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

class BSTIterator {
    // Stack to hold the nodes during in-order traversal
    private Stack<TreeNode> stack;
    
    // Constructor: Initialize the iterator with the root of the BST
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        // Push all left children from root onto the stack
        pushAllLeft(root);
    }
    
    // Helper function: Push all left nodes from the given node to the bottom of the stack
    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);  // Push the current node
            node = node.left;  // Move to left child
        }
    }
    
    // Returns the next smallest number in the BST
    public int next() {
        // Pop the top node from the stack; this is the next in-order node
        TreeNode currentNode = stack.pop();
        
        // If the current node has a right child, push all its left children
        if (currentNode.right != null) {
            pushAllLeft(currentNode.right);
        }
        
        // Return the value of the current node
        return currentNode.val;
    }
    
    // Returns true if there are still nodes to process in the BST
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */