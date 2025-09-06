// Last updated: 9/7/2025, 3:44:02 AM
class Solution {
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries)
            ans += ((getOperations(query[1]) - getOperations(query[0] - 1) + 1) / 2);
        return ans;
    }

    private long getOperations(int n) {
        long res = 0;
        int ops = 0;
        for (long p = 1; p <= n; p *= 4) {
            int l = (int)p;
            int r = Math.min(n, (int)(p * 4 - 1));
            res += (long) (r - l + 1) * ++ops;
        }
        return res;
    }
}
