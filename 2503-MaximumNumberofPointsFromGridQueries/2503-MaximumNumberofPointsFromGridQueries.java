// Last updated: 3/28/2025, 11:57:50 AM
// Algorithm:
// 1. Create an array "cells" of all grid cells, each represented as a triple (value, i, j).
// 2. Sort the "cells" array by cell value in ascending order.
// 3. Pair each query with its original index and sort the queries by their value in ascending order.
// 4. Use a Disjoint Set Union (DSU) structure to manage connected components of activated cells.
// 5. Initialize a boolean array "activated" of size m*n to keep track of which cells are activated.
// 6. For each query in sorted order, while the next cell in "cells" has a value less than the query value:
//    - Mark the cell as activated.
//    - Union the cell with its adjacent (up, down, left, right) neighbors if they are activated.
// 7. After processing the necessary cells for the query, if the top-left cell (0,0) is activated,
//    the answer for that query is the size of the component containing (0,0). Otherwise, the answer is 0.
// 8. Map the answers back to the original query order and return the resulting array.
//
// Pseudo Code:
// -------------
// cells = list of (grid[i][j], i, j) for each cell in grid
// sort(cells) by value ascending
// queriesWithIndex = list of (query, index)
// sort(queriesWithIndex) by query ascending
// DSU dsu = new DSU(m*n)
// activated = boolean array of size m*n initialized to false
// ans = array of size queries.length
// pointer = 0
// for each (q, idx) in queriesWithIndex:
//     while pointer < cells.length and cells[pointer].value < q:
//         (val, i, j) = cells[pointer]
//         pos = i * n + j
//         activated[pos] = true
//         for each neighbor (ni, nj) of (i,j):
//             if neighbor is in bounds and activated[ni * n + nj] is true:
//                 dsu.union(pos, ni * n + nj)
//         pointer++
//     if activated[0] is true:
//         ans[idx] = dsu.size(find(0))
//     else:
//         ans[idx] = 0
// return ans
//
// Visualization Example:
// ----------------------
// Consider grid = [[1,2,3],[2,5,7],[3,5,1]] and query = 5.
// Sorted cells (with indices): e.g., (1,0,0), (1,2,2), (2,0,1), (2,1,0), (3,0,2), (3,2,0), (5,1,1), (5,2,1), (7,1,2)
// For query 5, process cells with value < 5, i.e. cells with values 1,1,2,2,3,3.
// Activate these cells and union adjacent ones.
// Then, if top-left cell is activated, answer = size of its component.
// Repeat for each query.
//
// Code:
class Solution {
    
    // DSU (Disjoint Set Union) class to manage union-find operations.
    class DSU {
        int[] parent;
        int[] size;
        
        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        // Find operation with path compression.
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        // Union operation to merge two sets.
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            // Merge smaller component into larger one for efficiency.
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
        
        // Return the size of the component which x belongs to.
        public int getSize(int x) {
            return size[find(x)];
        }
    }
    
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int totalCells = m * n;
        
        // Step 1: Create an array of all cells (value, i, j)
        int[][] cells = new int[totalCells][3]; // Each cell: [value, i, j]
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[index][0] = grid[i][j];
                cells[index][1] = i;
                cells[index][2] = j;
                index++;
            }
        }
        
        // Step 2: Sort cells based on their grid value (ascending order)
        Arrays.sort(cells, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Step 3: Pair each query with its original index and sort queries by value.
        int qLen = queries.length;
        int[][] queryPairs = new int[qLen][2]; // [query value, original index]
        for (int i = 0; i < qLen; i++) {
            queryPairs[i][0] = queries[i];
            queryPairs[i][1] = i;
        }
        Arrays.sort(queryPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // DSU structure for union-find, and an activated boolean array to mark processed cells.
        DSU dsu = new DSU(totalCells);
        boolean[] activated = new boolean[totalCells];
        
        // Result array for answers
        int[] ans = new int[qLen];
        int pointer = 0; // Pointer for cells
        
        // Directions for 4 adjacent moves: up, down, left, right.
        int[] dirX = { -1, 1, 0, 0 };
        int[] dirY = { 0, 0, -1, 1 };
        
        // Process each query in increasing order.
        for (int i = 0; i < qLen; i++) {
            int queryVal = queryPairs[i][0];
            int originalIndex = queryPairs[i][1];
            
            // Activate all cells with grid value < queryVal.
            while (pointer < totalCells && cells[pointer][0] < queryVal) {
                int cellVal = cells[pointer][0];
                int r = cells[pointer][1];
                int c = cells[pointer][2];
                int pos = r * n + c;
                activated[pos] = true; // Mark current cell as activated
                
                // For each neighbor, if activated, union the current cell with neighbor.
                for (int d = 0; d < 4; d++) {
                    int nr = r + dirX[d];
                    int nc = c + dirY[d];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        int nPos = nr * n + nc;
                        if (activated[nPos]) {
                            dsu.union(pos, nPos);
                        }
                    }
                }
                pointer++;
            }
            
            // After activating necessary cells, check if the starting cell (0,0) is activated.
            if (activated[0]) {
                // The number of points is the size of the connected component containing (0,0).
                ans[originalIndex] = dsu.getSize(0);
            } else {
                ans[originalIndex] = 0; // (0,0) not activated, so no points.
            }
        }
        
        return ans;
    }
}
