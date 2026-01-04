// Last updated: 1/4/2026, 8:56:37 PM
1class Solution {
2    public int minDeletionSize(String[] strs) {
3        int n = strs.length;
4        int m = strs[0].length();
5        // sorted[i] is true if strs[i] < strs[i+1] is already guaranteed by previous columns
6        boolean[] sorted = new boolean[n - 1];
7        int deletions = 0;
8
9        for (int j = 0; j < m; j++) {
10            boolean delete = false;
11            // Check if column j breaks the sorted order for any pair not yet sorted
12            for (int i = 0; i < n - 1; i++) {
13                if (!sorted[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
14                    delete = true;
15                    break;
16                }
17            }
18
19            if (delete) {
20                // If we must delete this column, increment count
21                deletions++;
22            } else {
23                // If we keep this column, update the 'sorted' array
24                // If strs[i] < strs[i+1] at this column, they are now strictly sorted
25                for (int i = 0; i < n - 1; i++) {
26                    if (strs[i].charAt(j) < strs[i + 1].charAt(j)) {
27                        sorted[i] = true;
28                    }
29                }
30            }
31        }
32        return deletions;
33    }
34}