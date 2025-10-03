// Last updated: 10/3/2025, 10:45:49 PM
import java.util.PriorityQueue;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }

        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int totalWater = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int row = cell[0];
            int col = cell[1];
            int height = cell[2];

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow > 0 && newRow < m - 1 && newCol > 0 && newCol < n - 1 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    
                    totalWater += Math.max(0, height - heightMap[newRow][newCol]);
                    
                    pq.offer(new int[]{newRow, newCol, Math.max(height, heightMap[newRow][newCol])});
                }
            }
        }

        return totalWater;
    }
}