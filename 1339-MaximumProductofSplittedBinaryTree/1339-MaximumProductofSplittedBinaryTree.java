// Last updated: 1/7/2026, 9:08:36 PM
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
17    List<Long> list= new ArrayList<>();
18    public int maxProduct(TreeNode root) {
19        long total= g(root);
20        long ans= 0;
21        for(long s: list){
22            long p= s*(total- s);
23            if(p > ans) ans= p;
24        }
25        return (int)(ans % 1000000007);
26    }
27    long g(TreeNode r){
28        if(r ==null) return 0;
29        long s= r.val + g(r.left) + g(r.right);
30        list.add(s);
31        return s;
32    }
33}