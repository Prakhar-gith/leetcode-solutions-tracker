// Last updated: 11/4/2025, 2:49:30 PM
import java.util.*;

class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n= nums.length ;
        int[] answer = new int[ n- k + 1 ] ;

        for ( int i = 0 ; i<= n- k ; i ++ ) {
            int[] freq= new int[ 51 ] ;
            
            for ( int j = i; j < i + k; j ++ ) {
                freq[ nums[ j ] ] ++ ;
            }

            List< int[] > counts = new ArrayList< >() ;
            for ( int val = 1 ; val <= 50 ; val ++ ) {
                if ( freq[ val ] > 0 ) {
                    counts.add( new int[]{ val, freq[ val ] } ) ;
                }
            }

            Collections.sort( counts, (a, b) -> {
                if ( a[ 1 ] != b[ 1 ] ) {
                    return b[ 1 ] - a[ 1 ] ;
                } else {
                    return b[ 0 ] - a[ 0 ] ;
                }
            } ) ;

            int currentXSum= 0 ;
            int limit = Math.min( x, counts.size() ) ;
            for ( int j = 0 ; j < limit ; j ++ ) {
                currentXSum += counts.get( j )[ 0 ] * counts.get( j )[ 1 ] ;
            }
            answer[ i ] = currentXSum ;
        }
        
        return answer ;
    }
}