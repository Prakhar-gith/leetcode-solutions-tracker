// Last updated: 8/30/2025, 6:56:18 PM
class Solution {
    public String findValidPair(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s.toCharArray()){
            freq.put(c, freq.getOrDefault(c,0) +1);
        }

        for(int i = 0; i < s.length() - 1; i++){
            char firstC = s.charAt(i);
            char secondC = s.charAt(i + 1);

            if(firstC == secondC){
                continue;
            }

            int firstD = firstC - '0';
            int secondD = secondC - '0';

            if(freq.get(firstC) == firstD && freq.get(secondC) == secondD){
                return s.substring(i, i + 2);
            }
        }
        return "";
    }
}