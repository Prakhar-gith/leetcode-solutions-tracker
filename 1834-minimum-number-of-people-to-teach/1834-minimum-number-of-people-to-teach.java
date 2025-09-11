class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        Set<Integer>[] langSets = new HashSet[m];
        for (int i = 0; i < m; i++) {
            langSets[i] = new HashSet<>();
            for (int lang : languages[i]) {
                langSets[i].add(lang);
            }
        }

        Set<Integer> nonCommunicatingUsers = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1;
            int v = friendship[1] - 1;
            if (!canCommunicate(langSets[u], langSets[v])) {
                nonCommunicatingUsers.add(u);
                nonCommunicatingUsers.add(v);
            }
        }

        if (nonCommunicatingUsers.isEmpty()) {
            return 0;
        }

        int minTeachings = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int currentTeachings = 0;
            for (int user : nonCommunicatingUsers) {
                if (!langSets[user].contains(i)) {
                    currentTeachings++;
                }
            }
            minTeachings = Math.min(minTeachings, currentTeachings);
        }

        return minTeachings;
    }

    private boolean canCommunicate(Set<Integer> lang1, Set<Integer> lang2) {
        for (int lang : lang1) {
            if (lang2.contains(lang)) {
                return true;
            }
        }
        return false;
    }
}