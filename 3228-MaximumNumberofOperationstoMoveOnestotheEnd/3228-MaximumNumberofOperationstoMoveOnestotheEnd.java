// Last updated: 11/13/2025, 4:08:09 PM
class Solution {
    public int maxOperations(String s) {
        
        int n = s.length();
        int operations = 0;
        int ones_count = 0;
        
        for( int i = 0; i < n; i++ ){
            
            if( s.charAt(i) == '1' ){
                ones_count += 1;
            } else { 
                
                if( i > 0 && s.charAt(i - 1) == '1' ){
                    
                    operations += ones_count;
                }
            }
        }
        
        return operations;
    }
}