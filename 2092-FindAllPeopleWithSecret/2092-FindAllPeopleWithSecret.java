// Last updated: 1/4/2026, 8:58:49 PM
1import java.util.*;
2
3class Solution {
4    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
5        // Sort meetings by time
6        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));
7        
8        int[] parent = new int[n];
9        for (int i = 0; i < n; i++) parent[i] = i;
10        
11        boolean[] known = new boolean[n];
12        known[0] = true;
13        known[firstPerson] = true;
14        
15        int m = meetings.length;
16        for (int i = 0; i < m; ) {
17            int j = i;
18            // Identify the range of meetings happening at the same time
19            while (j < m && meetings[j][2] == meetings[i][2]) {
20                j++;
21            }
22            
23            // Phase 1: Union all people meeting at this time
24            for (int k = i; k < j; k++) {
25                union(parent, meetings[k][0], meetings[k][1]);
26            }
27            
28            // Phase 2: Identify which groups have at least one person who ALREADY knows the secret
29            Set<Integer> secretRoots = new HashSet<>();
30            for (int k = i; k < j; k++) {
31                int u = meetings[k][0];
32                int v = meetings[k][1];
33                if (known[u]) secretRoots.add(find(parent, u));
34                if (known[v]) secretRoots.add(find(parent, v));
35            }
36            
37            // Phase 3: Propagate the secret to everyone in those connected groups
38            for (int k = i; k < j; k++) {
39                int u = meetings[k][0];
40                int v = meetings[k][1];
41                int root = find(parent, u); // u and v are already connected, so find(u) == find(v)
42                if (secretRoots.contains(root)) {
43                    known[u] = true;
44                    known[v] = true;
45                }
46            }
47            
48            // Phase 4: Reset the union-find structure for the next time batch
49            // Only reset the people involved in the current batch
50            for (int k = i; k < j; k++) {
51                int u = meetings[k][0];
52                int v = meetings[k][1];
53                parent[u] = u;
54                parent[v] = v;
55            }
56            
57            // Move to the next batch
58            i = j;
59        }
60        
61        // Collect results
62        List<Integer> result = new ArrayList<>();
63        for (int k = 0; k < n; k++) {
64            if (known[k]) {
65                result.add(k);
66            }
67        }
68        return result;
69    }
70    
71    // Standard Find with Path Compression
72    private int find(int[] parent, int x) {
73        if (parent[x] != x) {
74            parent[x] = find(parent, parent[x]);
75        }
76        return parent[x];
77    }
78    
79    // Standard Union
80    private void union(int[] parent, int x, int y) {
81        int rootX = find(parent, x);
82        int rootY = find(parent, y);
83        if (rootX != rootY) {
84            parent[rootX] = rootY;
85        }
86    }
87}