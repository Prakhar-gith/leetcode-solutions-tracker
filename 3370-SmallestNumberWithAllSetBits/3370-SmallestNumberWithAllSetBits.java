// Last updated: 10/30/2025, 12:36:34 AM
class Solution {
    public int smallestNumber(int n) {
        int x = 1 ;
        
        while ( x < n ) {
            x = ( x << 1 ) | 1 ;
        }
        
        return x ;
    }
}