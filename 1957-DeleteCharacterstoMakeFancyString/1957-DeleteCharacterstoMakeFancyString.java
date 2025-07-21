// Last updated: 7/22/2025, 1:01:58 AM
class Solution {
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int n = sb.length();
            // check if the last two characters in sb are both equal to current character
            if (n >= 2 && sb.charAt(n - 1) == s.charAt(i) && sb.charAt(n - 2) == s.charAt(i)) {
                continue; // skip adding to avoid 3 consecutive equal characters
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
