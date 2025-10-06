// Last updated: 10/7/2025, 1:56:37 AM
import java.util.PriorityQueue;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[n][n];

        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int r = current[1];
            int c = current[2];

            if (r == n - 1 && c == n - 1) {
                return time;
            }

            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visited[newR][newC]) {
                    visited[newR][newC] = true;
                    int newTime = Math.max(time, grid[newR][newC]);
                    pq.offer(new int[]{newTime, newR, newC});
                }
            }
        }
        
        return -1;
    }
}