// Last updated: 1/6/2026, 4:54:13 PM
1/**
2 * Definition for a binary tree node.
3 * public class TreeNode {
4 *     int val;
5 *     TreeNode left;
6 *     TreeNode right;
7 *     TreeNode() {}
8 *     TreeNode(int val) { this.val = val; }
9 *     TreeNode(int val, TreeNode left, TreeNode right) {
10 *         this.val = val;
11 *         this.left = left;
12 *         this.right = right;
13 *     }
14 * }
15 */
16class Solution {
17    public int maxLevelSum(TreeNode root) {
18        if(root== null) return 0;
19        int maxS= Integer.MIN_VALUE; int ans= 1; int lvl= 1;
20        Queue<TreeNode> q= new LinkedList<>();
21        q.add(root);
22        while(!q.isEmpty()){
23            int sz= q.size(); int sum=0;
24            for(int i=0; i< sz; i++){
25                TreeNode n= q.poll();
26                sum+= n.val;
27                if(n.left!= null) q.add(n.left);
28                if(n.right!= null) q.add(n.right);
29            }
30            if(sum > maxS){
31                maxS= sum; ans= lvl;
32            }
33            lvl++;
34        }
35        return ans;
36    }
37}