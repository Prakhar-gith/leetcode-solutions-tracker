// Last updated: 8/30/2025, 6:56:54 PM
class Solution {

    /**
     * DSU (Disjoint Set Union) data structure enhanced to maintain the bitwise AND 
     * of all edge weights within each component.
     */
    static class DSU {
        private final int[] parent;
        private final int[] componentAnd;

        /**
         * Initializes the DSU structure for n vertices.
         */
        public DSU(int n) {
            parent = new int[n];
            componentAnd = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                // Initialize with -1 (all 1s in binary), the identity for bitwise AND.
                componentAnd[i] = -1;
            }
        }

        /**
         * Finds the representative of the set containing element i, with path compression.
         */
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]); // Path compression
        }

        /**
         * Merges the sets containing elements i and j. The edge weight is used 
         * to update the component's aggregate AND value.
         */
        public void union(int i, int j, int weight) {
            int rootI = find(i);
            int rootJ = find(j);

            if (rootI != rootJ) {
                // Union the two components. A more optimized DSU could use union by size/rank.
                if (rootI < rootJ) { // Merge smaller index root into larger one
                    parent[rootJ] = rootI;
                    componentAnd[rootI] &= componentAnd[rootJ] & weight;
                } else {
                    parent[rootI] = rootJ;
                    componentAnd[rootJ] &= componentAnd[rootI] & weight;
                }
            } else {
                // If i and j are already in the same component, this edge forms a cycle.
                // We only need to AND its weight with the component's current value.
                componentAnd[rootI] &= weight;
            }
        }
    }

    /**
     * Calculates the minimum cost for each query walk using a DSU data structure.
     */
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        DSU dsu = new DSU(n);

        // Process all edges to build the components and their aggregate AND sums.
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1], edge[2]);
        }

        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0];
            int t = query[i][1];
            
            // If s and t are in the same component, a walk exists.
            if (dsu.find(s) == dsu.find(t)) {
                // The minimum cost is the pre-calculated AND value of the entire component.
                result[i] = dsu.componentAnd[dsu.find(s)];
            } else {
                // If they are in different components, no walk is possible.
                result[i] = -1;
            }
        }

        return result;
    }
}