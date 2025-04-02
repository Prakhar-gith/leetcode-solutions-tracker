// Last updated: 4/2/2025, 3:06:42 PM
// Algorithm:
// 1. Build a graph from the roads list where each node holds its adjacent nodes along with the travel time.
// 2. Use Dijkstra's algorithm to compute the shortest distance from intersection 0 to every other intersection.
// 3. While running Dijkstra, maintain a ways[] array which stores the number of shortest paths to each node.
//    - Initialize ways[0] = 1 since there is exactly one way to start at intersection 0.
//    - For every relaxation step, if we find a shorter distance for a neighbor, update its ways count equal to the current node's ways.
//    - If we find an equal distance (another shortest path), add the current node's ways to the neighbor's ways.
// 4. Return ways[n-1] modulo 10^9+7 which represents the number of shortest paths to the destination.
//
// Pseudo Code:
// -------------
// function numberOfWays(n, roads):
//     graph = buildGraph(roads)
//     dist[0...n-1] = INF, ways[0...n-1] = 0
//     dist[0] = 0, ways[0] = 1
//     pq = new PriorityQueue (node, distance)
//     pq.add( (0, 0) )
//     while pq not empty:
//         (d, u) = pq.poll()
//         if d > dist[u]: continue
//         for each neighbor v of u with weight w:
//             if d + w < dist[v]:
//                 dist[v] = d + w
//                 ways[v] = ways[u]
//                 pq.add( (dist[v], v) )
//             else if d + w == dist[v]:
//                 ways[v] += ways[u] (mod MOD)
//     return ways[n-1]
//
// Visualization Example:
// ----------------------
// For n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],
//                     [3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
// - Starting at 0 with distance 0 and ways = 1.
// - From 0, we can go to 6 (cost 7), 1 (cost 2), and 4 (cost 5).
// - As we relax the distances, we'll update ways for each node accordingly.
// - Finally, ways[6] (destination) becomes 4, meaning there are 4 shortest paths to reach node 6.
//
// Code with detailed comments in Hinglish:
class Solution {
    // MOD constant for modulo operations
    private static final int MOD = 1_000_000_007;
    
    public int countPaths(int n, int[][] roads) {
        // Build the graph: For each node, store a list of {neighbor, travelTime}
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        // Add roads to the graph (bidirectional)
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            graph[u].add(new int[]{v, time});
            graph[v].add(new int[]{u, time});
        }
        
        // dist[i] stores the shortest distance from node 0 to node i.
        long[] dist = new long[n];
        // ways[i] stores the number of ways to reach node i with the shortest distance.
        long[] ways = new long[n];
        
        // Initialize distances to infinity and ways to 0.
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;
        
        // Priority queue for Dijkstra: stores {currentDistance, node}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0}); // Starting at node 0 with distance 0
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currDist = curr[0];
            int u = (int) curr[1];
            
            // Agar current distance greater than stored distance hai, then skip karo (outdated entry)
            if (currDist > dist[u]) continue;
            
            // Traverse all neighbors of node u
            for (int[] edge : graph[u]) {
                int v = edge[0];
                int time = edge[1];
                // Check if we found a shorter path to node v through u
                if (currDist + time < dist[v]) {
                    // Update shortest distance to v
                    dist[v] = currDist + time;
                    // Update number of ways to reach v = ways to reach u (naya best path)
                    ways[v] = ways[u];
                    pq.offer(new long[]{dist[v], v});
                } else if (currDist + time == dist[v]) {
                    // Found an alternate path with the same shortest distance,
                    // So add the ways from u to v.
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }
        
        // Return the number of ways to reach destination (node n - 1) modulo MOD
        return (int) ways[n - 1];
    }
}
