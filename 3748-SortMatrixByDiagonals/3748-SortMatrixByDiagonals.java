// Last updated: 8/30/2025, 6:56:12 PM
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagonals = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                diagonals.putIfAbsent(key, new ArrayList<>());
                diagonals.get(key).add(grid[i][j]);
            }
        }

        for (Integer key : diagonals.keySet()) {
            List<Integer> diagonal = diagonals.get(key);
            if (key >= 0) {
                Collections.sort(diagonal, Collections.reverseOrder());
            } else {
                Collections.sort(diagonal);
            }
        }

        Map<Integer, Integer> pointers = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                int index = pointers.getOrDefault(key, 0);
                grid[i][j] = diagonals.get(key).get(index);
                pointers.put(key, index + 1);
            }
        }

        return grid;
    }
}