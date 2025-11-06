// Last updated: 11/6/2025, 9:39:48 PM
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

class Solution {

    private class DSU {
        int[] parent;
        TreeSet<Integer>[] onlineNodes;

        DSU(int c) {
            parent = new int[c + 1];
            onlineNodes = new TreeSet[c + 1];
            for (int i = 1; i <= c; i++) {
                parent[i] = i;
                onlineNodes[i] = new TreeSet<>();
                onlineNodes[i].add(i);
            }
        }

        int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            
            if (rootI != rootJ) {
                if (onlineNodes[rootI].size() < onlineNodes[rootJ].size()) {
                    int temp = rootI;
                    rootI = rootJ;
                    rootJ = temp;
                }
                
                onlineNodes[rootI].addAll(onlineNodes[rootJ]);
                parent[rootJ] = rootI;
                onlineNodes[rootJ].clear();
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU( c );
        boolean[] isOnline = new boolean[ c + 1 ];
        Arrays.fill( isOnline, true );

        for (int[] conn : connections) {
            dsu.union(conn[0], conn[1]);
        }

        List<Integer> results = new ArrayList<>();
        
        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];

            if (type == 1) {
                if (isOnline[x]) {
                    results.add(x);
                } else {
                    int root = dsu.find(x);
                    TreeSet<Integer> gridNodes = dsu.onlineNodes[root];
                    if (gridNodes.isEmpty()) {
                        results.add(-1);
                    } else {
                        results.add(gridNodes.first());
                    }
                }
            } else { 
                if (isOnline[x]) {
                    isOnline[x] = false;
                    int root = dsu.find( x );
                    dsu.onlineNodes[root].remove(x);
                }
            }
        }

        int[] ans = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            ans[ i ] = results.get(i);
        }
        
        return ans;
    }
}