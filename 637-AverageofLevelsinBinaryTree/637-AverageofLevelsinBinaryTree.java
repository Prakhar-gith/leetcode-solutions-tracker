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
// 1. Use Breadth-First Search (BFS) to traverse the tree level by level.
// 2. For each level, calculate the sum of node values and count the nodes.
// 3. Compute the average as (sum / number of nodes) and add it to the result list.
// 4. Continue until all levels are processed.
// 5. Return the list of averages.
//
// Pseudo Code:
// -------------
// function averageOfLevels(root):
//     if root is null: return empty list
//     initialize queue with root
//     initialize result list
//     while queue is not empty:
//         levelSize = queue.size()
//         levelSum = 0
//         for i = 0 to levelSize - 1:
//             node = queue.poll()
//             levelSum += node.val
//             if node.left != null: queue.offer(node.left)
//             if node.right != null: queue.offer(node.right)
//         average = levelSum / levelSize
//         add average to result
//     return result
//
// Visualization Example:
// ----------------------
// For tree: [3,9,20,null,null,15,7]
// Level 0: [3] => average = 3.0
// Level 1: [9,20] => average = (9+20)/2 = 14.5
// Level 2: [15,7] => average = (15+7)/2 = 11.0

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        // Result list to store average of each level
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        
        // Queue for BFS traversal, starting with root
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // Process each level until queue is empty
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes in current level
            double levelSum = 0;          // Sum of node values at current level
            
            // Traverse all nodes of current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val; // Add current node's value to sum
                
                // Agar left child exists, add to queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                // Agar right child exists, add to queue
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // Calculate average for this level and add to result
            result.add(levelSum / levelSize);
        }
        
        return result;
    }
}