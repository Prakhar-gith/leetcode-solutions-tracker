// Last updated: 6/24/2025, 11:30:59 PM
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        boolean[] seen = new boolean[n];

        for (int j = 0; j < n; j++) {
            if (nums[j] == key) {
                int start = Math.max(0, j - k);
                int end = Math.min(n - 1, j + k);
                for (int i = start; i <= end; i++) {
                    seen[i] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (seen[i]) result.add(i);
        }

        return result;
    }
}
