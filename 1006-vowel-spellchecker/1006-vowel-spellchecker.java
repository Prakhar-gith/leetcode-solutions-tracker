class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>();
        Map<String, String> caseInsensitiveWords = new HashMap<>();
        Map<String, String> vowelErrorWords = new HashMap<>();

        for (String word : wordlist) {
            exactWords.add(word);
            String lower = word.toLowerCase();
            caseInsensitiveWords.putIfAbsent(lower, word);
            
            String devoweled = devowel(lower);
            vowelErrorWords.putIfAbsent(devoweled, word);
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = solve(queries[i], exactWords, caseInsensitiveWords, vowelErrorWords);
        }
        return result;
    }

    private String solve(String query, Set<String> exactWords, Map<String, String> caseInsensitiveWords, Map<String, String> vowelErrorWords) {
        if (exactWords.contains(query)) {
            return query;
        }
        String lowerQuery = query.toLowerCase();
        if (caseInsensitiveWords.containsKey(lowerQuery)) {
            return caseInsensitiveWords.get(lowerQuery);
        }

        String devoweledQuery = devowel(lowerQuery);
        if (vowelErrorWords.containsKey(devoweledQuery)) {
            return vowelErrorWords.get(devoweledQuery);
        }

        return "";
    }

    private String devowel(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(isVowel(c) ? '*' : c);
        }
        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}