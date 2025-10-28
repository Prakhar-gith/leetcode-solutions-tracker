// Last updated: 10/29/2025, 12:32:18 AM
import java.util.Arrays;

class Solution {
    public int countValidSelections(int[] nums) {
        int validCount = 0;
        int n = nums.length ;
        
        for ( int i = 0 ; i < n ; i ++ ) {
            if ( nums[ i ] == 0 ) {
                
                if ( check( nums, i, -1, n ) ) {
                    validCount ++ ;
                }
                
                if ( check( nums, i, 1, n ) ) {
                    validCount ++ ;
                }
            }
        }
        return validCount ;
    }
    
    private boolean check( int[] nums, int startCurr, int startDir, int n ) {
        int[] simNums = Arrays.copyOf( nums, n );
        int curr = startCurr ;
        int dir = startDir ;
        
        while ( curr >= 0 && curr < n ) {
            if ( simNums[ curr ] == 0 ) {
                curr += dir ;
            } else {
                simNums[ curr ] -- ;
                dir = -dir ;
                curr += dir ;
            }
        }
        
        for ( int val : simNums ) {
            if ( val != 0 ) {
                return false ;
            }
        }
        
        return true ;
    }
}