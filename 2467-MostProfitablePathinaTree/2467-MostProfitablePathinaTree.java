import java.util.*;

class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        Map<Integer, Integer> bobTime = new HashMap<>();
        findBobPath(tree, bob, -1, 0, bobTime);
        
        return dfs(tree, amount, 0, -1, 0, bobTime);
    }

    private boolean findBobPath(List<Integer>[] tree, int node, int parent, int time, Map<Integer, Integer> bobTime) {
        bobTime.put(node, time);
        if (node == 0) return true;
        
        for (int neighbor : tree[node]) {
            if (neighbor == parent) continue;
            if (findBobPath(tree, neighbor, node, time + 1, bobTime)) {
                return true;
            }
        }
        
        bobTime.remove(node);
        return false;
    }

    private int dfs(List<Integer>[] tree, int[] amount, int node, int parent, int time, Map<Integer, Integer> bobTime) {
        int profit = amount[node];

        if (bobTime.containsKey(node)) {
            int bobArrives = bobTime.get(node);
            if (bobArrives == time) { 
                profit /= 2;
            } else if (bobArrives < time) { 
                profit = 0;
            }
        }

        int maxProfit = Integer.MIN_VALUE;
        boolean isLeaf = true;

        for (int neighbor : tree[node]) {
            if (neighbor == parent) continue;
            isLeaf = false;
            maxProfit = Math.max(maxProfit, dfs(tree, amount, neighbor, node, time + 1, bobTime));
        }

        return profit + (isLeaf ? 0 : maxProfit);
    }
}
