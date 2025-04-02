// Last updated: 4/2/2025, 3:08:18 PM
// Algorithm:
// 1. Build the undirected graph using an adjacency list (each vertex stores its neighbors in a set).
// 2. Use a boolean array 'visited' to keep track of visited vertices.
// 3. For every unvisited vertex, perform a DFS (or BFS) to get all vertices in the connected component.
// 4. For the current connected component, let m be the number of vertices.
//    - A complete graph (or clique) with m vertices should have exactly m*(m-1)/2 edges.
//    - While traversing the component, compute the sum of degrees (i.e., the number of neighbors) for each vertex.
//    - Since each edge is counted twice (once for each endpoint), the actual number of edges is sumDegrees/2.
// 5. If the actual number of edges equals m*(m-1)/2, then the component is complete.
// 6. Count such complete components and return the count.
//
// Pseudo Code:
// -------------
// function countCompleteComponents(n, edges):
//     graph = build adjacency list from edges
//     visited = array of false of size n
//     count = 0
//     for vertex in 0 to n-1:
//         if not visited[vertex]:
//             component = DFS(vertex)  // gather all vertices in this component
//             m = size(component)
//             sumDegrees = 0
//             for each vertex u in component:
//                 sumDegrees += degree(u)  // number of neighbors from graph
//             actualEdges = sumDegrees / 2
//             if actualEdges == m*(m-1)/2:
//                 count++
//     return count
//
// Visualization Example:
// ----------------------
// For n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
// Graph:
//   0: {1,2}
//   1: {0,2}
//   2: {0,1}
//   3: {4}
//   4: {3}
//   5: {}
// Connected components:
//   - Component 1: {0,1,2} -> m = 3, expected edges = 3, actual edges = (2+2+2)/2 = 3, complete.
//   - Component 2: {3,4}   -> m = 2, expected edges = 1, actual edges = (1+1)/2 = 1, complete.
//   - Component 3: {5}     -> m = 1, expected edges = 0, actual edges = 0, complete.
// Total complete components = 3.

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build the graph as an adjacency list where each vertex stores a set of its neighbors.
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        // Fill the graph with the edges.
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        boolean[] visited = new boolean[n]; // visited array to mark processed vertices.
        int completeCount = 0; // Count of complete components
        
        // Iterate over each vertex to process its connected component.
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Get all vertices in the current connected component via DFS.
                List<Integer> component = new ArrayList<>();
                dfs(i, graph, visited, component);
                
                int m = component.size(); // Number of vertices in the component.
                long sumDegrees = 0; // Sum of degrees for vertices in this component.
                for (int vertex : component) {
                    sumDegrees += graph.get(vertex).size();
                }
                // Actual number of edges = sumDegrees / 2 (since each edge counted twice).
                long actualEdges = sumDegrees / 2;
                // In a complete graph, number of edges should be m*(m-1)/2.
                long expectedEdges = (long)m * (m - 1) / 2;
                
                // Agar actual edges match expected edges, then component is complete.
                if (actualEdges == expectedEdges) {
                    completeCount++;
                }
            }
        }
        
        return completeCount;
    }
    
    // DFS helper method to traverse the component and add vertices to the 'component' list.
    private void dfs(int u, List<Set<Integer>> graph, boolean[] visited, List<Integer> component) {
        visited[u] = true;
        component.add(u); // Add current vertex to component list.
        // Traverse all neighbors of u.
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                dfs(v, graph, visited, component);
            }
        }
    }
}
