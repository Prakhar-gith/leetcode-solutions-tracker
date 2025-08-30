// Last updated: 8/30/2025, 6:56:21 PM
class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        // if(n < k) return false;
        
        // for (int i = 0; i <= n - k; i++) {
        //     String substr = s.substring(i, i + k);
        //     boolean same = true;
            
        //     for (int j = 1; j < substr.length(); j++) {
        //         if (substr.charAt(j) != substr.charAt(0)) {
        //             same = false;
        //             break;
        //         }
        //     }
        //     if (!same) continue; 
            
        //     boolean leftBoundary = (i == 0) || (s.charAt(i - 1) != substr.charAt(0));
        //     boolean rightBoundary = (i + k == n) || (s.charAt(i + k) != substr.charAt(0));
            
        //     if (leftBoundary && rightBoundary) {
        //         return true;
        //     }
        // }
        // return false;

        int count = 1;
        for(int i = 1; i < n; i++){
            char curr = s.charAt(i);
            char prev = s.charAt(i-1);
            if(curr == prev) count++;
            else{
                if(count == k) return true;
                count = 1;
            }
        }
        if(count== k) return true;
        return false;
    }
}
