// Last updated: 9/15/2025, 7:28:36 PM
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int count = words.length;
        
        for (String word : words) {
            for (char broken : brokenLetters.toCharArray()) {
                if (word.indexOf(broken) != -1) {
                    count--;
                    break; // next word
                }
            }
        }
        
        return count;
    }
}