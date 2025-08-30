// Last updated: 8/30/2025, 6:56:03 PM
class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        int m = conflictingPairs.length;

        // 1) Preprocess pairs into (A_i,B_i) with A_i< B_i,
        //    and record which conflicts end at each B_i.
        int[] A = new int[m], B = new int[m];
        @SuppressWarnings("unchecked")
        List<Integer>[] endsAt = new List[n+1];
        for (int i = 1; i <= n; i++) endsAt[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = conflictingPairs[i][0];
            int b = conflictingPairs[i][1];
            if (a > b) { int t = a; a = b; b = t; }
            A[i] = a;
            B[i] = b;
            endsAt[b].add(i);
        }

        // 2) TreeMap of active "a+1" keys -> list of conflict indices
        TreeMap<Integer, List<Integer>> T = new TreeMap<>();
        long[] delta = new long[m];
        long base = 0;

        // sliding window l is implicitly the current maximum key (or 1 if none)
        for (int r = 1; r <= n; r++) {
            // insert all conflicts that end at r
            for (int idx : endsAt[r]) {
                int key = A[idx] + 1;
                T.computeIfAbsent(key, k -> new ArrayList<>()).add(idx);
            }

            // figure out the top two keys in T
            int v1, v2, idx1;
            if (T.isEmpty()) {
                v1 = 1;
                v2 = 1;
                idx1 = -1;
            } else {
                v1 = T.lastKey();
                List<Integer> topList = T.get(v1);
                idx1 = topList.get(0); // attribute to any one conflict
                if (topList.size() >= 2) {
                    // tie for top key, removing one still leaves the same key
                    v2 = v1;
                } else {
                    // only one conflict at v1, next best is lowerKey or 1
                    Integer lo = T.lowerKey(v1);
                    v2 = (lo == null ? 1 : lo);
                }
            }

            // that v1 is exactly the l we use for the sliding window
            int l = v1;
            base += (long)(r - l + 1);

            // record how much this conflict "idx1" uniquely contributed
            if (idx1 >= 0) {
                delta[idx1] += (long)(v1 - v2);
            }
        }

        // 3) answer = base + max_i delta[i]
        long bestDelta = 0;
        for (long d : delta) if (d > bestDelta) bestDelta = d;
        return base + bestDelta;
    }
}
