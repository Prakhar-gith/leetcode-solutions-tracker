class Solution {
    private static final Set<Character> VOWELS = 
        new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));


    public String sortVowels(String s) {
        List<Character> vowelsList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (VOWELS.contains(c)) {
                vowelsList.add(c);
            }
        }
        
        Collections.sort(vowelsList);
        
        char[] resultChars = s.toCharArray();
        int vowelPointer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (VOWELS.contains(s.charAt(i))) {
                resultChars[i] = vowelsList.get(vowelPointer);
                vowelPointer++;
            }
        }
        
        return new String(resultChars);
    }
}