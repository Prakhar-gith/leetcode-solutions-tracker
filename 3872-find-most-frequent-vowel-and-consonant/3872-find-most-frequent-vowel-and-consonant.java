class Solution {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        for (char c = 'a'; c <= 'z'; c++) {
            int currentFreq = freq[c - 'a'];
            
            if (vowels.contains(c)) {
                if (currentFreq > maxVowelFreq) {
                    maxVowelFreq = currentFreq;
                }
            } else {
                if (currentFreq > maxConsonantFreq) {
                    maxConsonantFreq = currentFreq;
                }
            }
        }
        
        return maxVowelFreq + maxConsonantFreq;
    }
}