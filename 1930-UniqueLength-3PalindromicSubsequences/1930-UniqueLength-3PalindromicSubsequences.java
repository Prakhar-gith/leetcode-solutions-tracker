// Last updated: 11/21/2025, 3:04:48 PM
class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans= 0;
        for(char c='a'; c<= 'z'; c++) {
            int l= s.indexOf(c);
            int r = s.lastIndexOf(c);
            
            if(l!= -1 && r > l) {
                java.util.Set<Character> set= new java.util.HashSet<>();
                for(int i= l+ 1; i< r; i++){
                    set.add(s.charAt(i));
                }
                ans+= set.size();
            }
        }
        return ans;
    }
}