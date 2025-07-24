// Last updated: 7/24/2025, 11:19:05 PM
class Solution {

    // Global timer for DFS in/out times.
    int time = 0;
    
    public int minimumScore(int[] nums, int[][] edges) {
        
        // Algorithm:
        // The previous solution was correct in its high-level approach (checking O(N^2) edge pairs)
        // but was too slow because the ancestor check inside the loop was inefficient, leading to an
        // O(N^3) overall complexity and a Time Limit Exceeded (TLE) error.
        //
        // Optimization:
        // We can optimize the ancestor check to O(1) using a standard graph algorithm technique:
        // DFS in-time and out-time.
        // 1. We perform a single, comprehensive DFS traversal from the root (node 0).
        // 2. During this DFS, we use a global timer. `in[u]` is the time when we first visit node `u`.
        //    `out[u]` is the time after we have visited all of `u`'s children.
        // 3. With this pre-computation, node `u` is an ancestor of node `v` if and only if
        //    `in[u] < in[v]` AND `out[u] > out[v]`. This check is O(1).
        // 4. This single DFS can also compute the `subtreeXor` for all nodes simultaneously.
        // 5. With this optimization, the main O(N^2) loop now performs O(1) work inside, making the
        //    total complexity O(N^2), which is efficient enough to pass.

        // Pseudo Code:
        // 1. n = nums.length; adj = buildAdjacencyList(edges)
        // 2. subtreeXor = new int[n], in = new int[n], out = new int[n], time = 0
        // 3. // Single DFS to pre-compute everything
        // 4. dfs(0, -1, adj, nums, subtreeXor, in, out)
        // 5. totalXor = subtreeXor[0]
        // 6. minScore = infinity
        // 7. // Iterate over all pairs of nodes (i, j) to define edges to cut.
        // 8. For i from 1 to n-1:
        // 9.   For j from i+1 to n-1:
        // 10.      is_i_ancestor_j = (in[i] < in[j] && out[i] > out[j])
        // 11.      is_j_ancestor_i = (in[j] < in[i] && out[j] > out[i])
        // 12.      // Calculate component XORs a,b,c based on the ancestor relationship.
        // 13.      // Update minScore.
        // 14. return minScore

        int n = nums.length;
        // Step 1: Adjacency list representation of the tree.
        // Tree ko represent karne ke liye adjacency list banayenge.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Arrays to store pre-computed values from our DFS.
        int[] subtreeXor = new int[n];
        int[] inTime = new int[n];
        int[] outTime = new int[n];
        
        // Step 2: Ek hi DFS se saari zaroori information pre-compute kar lenge.
        dfs(0, -1, adj, nums, subtreeXor, inTime, outTime);
        
        int minScore = Integer.MAX_VALUE;
        int totalXor = subtreeXor[0];

        // Step 3: Iterate through all O(N^2) pairs of nodes to simulate cutting the edges above them.
        // Root (node 0) ka koi parent nahi hota, isliye loop 1 se start hota hai.
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                int xorA, xorB, xorC;
                
                // O(1) ancestor check using in/out times.
                // Check karte hain ki `i` node `j` ka ancestor hai ya nahi.
                boolean iIsAncestorOfJ = inTime[i] < inTime[j] && outTime[i] > outTime[j];
                // Check karte hain ki `j` node `i` ka ancestor hai ya nahi.
                boolean jIsAncestorOfI = inTime[j] < inTime[i] && outTime[j] > outTime[i];

                if (iIsAncestorOfJ) {
                    // Case 2: Nested. `i` is an ancestor of `j`.
                    // Edge (parent_i, i) is "above" edge (parent_j, j).
                    xorA = subtreeXor[j];
                    xorB = subtreeXor[i] ^ subtreeXor[j];
                    xorC = totalXor ^ subtreeXor[i];
                } else if (jIsAncestorOfI) {
                    // Case 2: Nested. `j` is an ancestor of `i`.
                    xorA = subtreeXor[i];
                    xorB = subtreeXor[j] ^ subtreeXor[i];
                    xorC = totalXor ^ subtreeXor[j];
                } else {
                    // Case 1: Disjoint. The subtrees of `i` and `j` don't overlap.
                    xorA = subtreeXor[i];
                    xorB = subtreeXor[j];
                    xorC = totalXor ^ xorA ^ xorB;
                }

                // Calculate the score for this pair of removals and update the minimum.
                int maxVal = Math.max(xorA, Math.max(xorB, xorC));
                int minVal = Math.min(xorA, Math.min(xorB, xorC));
                minScore = Math.min(minScore, maxVal - minVal);
            }
        }

        return minScore;
    }

    // Single DFS to calculate subtreeXor, in-time, and out-time for every node.
    private int dfs(int u, int p, List<List<Integer>> adj, int[] nums, int[] subtreeXor, int[] inTime, int[] outTime) {
        // Mark entry time for node u.
        inTime[u] = time++;
        
        int currentXor = nums[u];
        for (int v : adj.get(u)) {
            if (v == p) continue;
            currentXor ^= dfs(v, u, adj, nums, subtreeXor, inTime, outTime);
        }
        
        subtreeXor[u] = currentXor;

        // Mark exit time for node u.
        outTime[u] = time++;
        return currentXor;
    }
    
    /*
     * Visualization of the logic remains the same, but the implementation is faster.
     *
     * Assume we have a tree rooted at 0.
     * We have pre-calculated `subtreeXor[i]`, `in[i]`, and `out[i]` for all nodes `i`.
     *
     * To check if `u` is an ancestor of `v`:
     * if (in[u] < in[v] && out[u] > out[v]) { // it is! }
     *
     * This O(1) check allows the O(N^2) iteration over edge pairs to complete in time.
     *
     * Case 1: Disjoint Subtrees (u is not ancestor of v, v is not ancestor of u)
     * The three components have XOR sums:
     * 1. subtreeXor[u]
     * 2. subtreeXor[v]
     * 3. totalXor ^ subtreeXor[u] ^ subtreeXor[v]
     *
     * Case 2: Nested Subtrees (u is an ancestor of v)
     * The three components have XOR sums:
     * 1. subtreeXor[v]
     * 2. subtreeXor[u] ^ subtreeXor[v]
     * 3. totalXor ^ subtreeXor[u]
     */
}