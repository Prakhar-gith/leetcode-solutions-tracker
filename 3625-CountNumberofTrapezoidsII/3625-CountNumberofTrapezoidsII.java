// Last updated: 12/4/2025, 1:53:37 AM
1import java.util.*;
2
3class Solution {
4    public int countTrapezoids(int[][] points) {
5        HashMap<Integer, HashMap<Integer, Integer>> slopes = new HashMap<>();
6        HashMap<Integer, HashMap<Integer, Integer>> lines = new HashMap<>();
7
8        int n = points.length;
9
10        for (int i = 0; i < n; i++) {
11            for (int j = i + 1; j < n; j++) {
12
13                int dx = points[j][0] - points[i][0];
14                int dy = points[j][1] - points[i][1];
15
16                if (dx < 0 || (dx == 0 && dy < 0)) {
17                    dx = -dx;
18                    dy = -dy;
19                }
20
21                int g = gcd(dx, Math.abs(dy));
22                int sx = dx / g;
23                int sy = dy / g;
24
25                int des = sx * points[i][1] - sy * points[i][0];
26
27                int key1 = (sx << 12) | (sy + 2000);
28                int key2 = (dx << 12) | (dy + 2000);
29
30                slopes.computeIfAbsent(key1, k -> new HashMap<>()).merge(des, 1, Integer::sum);
31                lines.computeIfAbsent(key2, k -> new HashMap<>()).merge(des, 1, Integer::sum);
32            }
33        }
34
35        return count(slopes) - count(lines) / 2;
36    }
37
38    private int count(HashMap<Integer, HashMap<Integer, Integer>> map) {
39        long ans = 0;
40
41        for (HashMap<Integer, Integer> inner : map.values()) {
42            long sum = 0;
43
44            for (int val : inner.values()) 
45                sum += val;
46
47            for (int val : inner.values()) {
48                sum -= val;
49                ans += (long) val * sum;
50            }
51        }
52
53        return (int) ans;
54    }
55
56    private int gcd(int a, int b) {
57        while (b != 0) {
58            int t = a % b;
59            a = b;
60            b = t;
61        }
62        return Math.abs(a);
63    }
64}