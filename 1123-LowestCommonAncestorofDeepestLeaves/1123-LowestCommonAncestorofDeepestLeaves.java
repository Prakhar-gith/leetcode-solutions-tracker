// Last updated: 4/4/2025, 11:41:06 PM
// Algorithm:
// 1. Use a DFS (Depth-First Search) to traverse the tree and compute for every subtree:
//      - The deepest depth reachable from that node.
//      - The lowest common ancestor (LCA) of all deepest leaves in that subtree.
// 2. For each node, recursively compute results for left and right children.
// 3. Compare the deepest depths from left and right subtrees:
//      - If they are equal, then the current node is the LCA of the deepest leaves in its subtree.
//      - If left subtree is deeper, then the LCA is the one from the left subtree.
//      - If right subtree is deeper, then the LCA is the one from the right subtree.
// 4. The recursion returns a pair (node, depth) for each subtree, where node is the LCA for that subtree's deepest leaves and depth is the maximum depth in that subtree (with current node counted as depth 1).
// 5. Finally, return the LCA computed from the DFS starting at the root.
//
// Pseudo Code:
// -------------
// function dfs(node):
//     if node is null: return (null, 0)
//     leftPair = dfs(node.left)
//     rightPair = dfs(node.right)
//     if leftPair.depth == rightPair.depth:
//         return (node, leftPair.depth + 1)
//     else if leftPair.depth > rightPair.depth:
//         return (leftPair.node, leftPair.depth + 1)
//     else:
//         return (rightPair.node, rightPair.depth + 1)
//
// function lcaDeepestLeaves(root):
//     return dfs(root).node
//
// Visualization Example:
// ----------------------
// Consider the tree: [3,5,1,6,2,0,8,null,null,7,4]
// - Deepest leaves: 7 and 4 at depth 4.
// - DFS from node 2 (parent of 7 and 4): left depth = 1 (from 7), right depth = 1 (from 4)
//   -> They are equal, so LCA at node 2 is returned with depth = 2.
// - Backtracking, the final LCA of deepest leaves becomes node 2.
// This satisfies the condition that node 2 is the lowest common ancestor of leaves 7 and 4.


// Code Implementation:
public class Solution {
    
    // Helper class to store pair of (node, depth)
    private class Pair {
        TreeNode node;  // LCA for the subtree
        int depth;      // Maximum depth of the subtree (with current node counted as 1)
        Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    // DFS function that returns a Pair for the given subtree rooted at 'root'
    private Pair dfs(TreeNode root) {
        // Base case: if the node is null, no LCA and depth is 0.
        if (root == null) return new Pair(null, 0);
        
        // Recursively get results from left and right subtrees.
        Pair leftPair = dfs(root.left);
        Pair rightPair = dfs(root.right);
        
        // Compare the maximum depths from left and right:
        if (leftPair.depth == rightPair.depth) {
            // Agar dono side same depth ke hain, current node is the LCA of deepest leaves.
            return new Pair(root, leftPair.depth + 1);
        } else if (leftPair.depth > rightPair.depth) {
            // Left subtree is deeper: pass up the left LCA.
            return new Pair(leftPair.node, leftPair.depth + 1);
        } else {
            // Right subtree is deeper: pass up the right LCA.
            return new Pair(rightPair.node, rightPair.depth + 1);
        }
    }
    
    // Main function to return the lowest common ancestor of deepest leaves.
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node; // DFS returns a pair, we return the LCA node from it.
    }
}
