// Last updated: 11/16/2025, 10:47:34 PM
class Solution {
    public int numSub(String s) {
        
        long ans = 0;
        long current_len = 0;
        int MOD = 1_000_000_007;
        
        for( int i= 0; i< s.length(); i ++ ){
            
            if( s.charAt(i) == '1' ){
                current_len++;
            } else {
                
                ans = (ans + (current_len * (current_len + 1) / 2) % MOD) % MOD;
                current_len = 0;
            }
        }
        
        ans = (ans + (current_len * (current_len + 1) / 2) % MOD) % MOD;
        
        return (int) ans;
    }
}