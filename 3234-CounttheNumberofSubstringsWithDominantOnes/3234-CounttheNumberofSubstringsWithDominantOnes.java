// Last updated: 11/16/2025, 2:50:26 AM

class Solution {
    public int numberOfSubstrings(String s) {
        
        int n = s.length();
        int ans = 0;
        int max_z = (int) Math.sqrt(n);
        
        ArrayList<Integer> zero_idx_list = new ArrayList<>();
        zero_idx_list.add(-1);
        for( int i= 0; i< n; i ++){
            if( s.charAt(i) == '0' ){
                zero_idx_list.add( i );
            }
        }
        zero_idx_list.add( n );
        
        int m = zero_idx_list.size();
        int p = 0; 
        
        for( int i= 0; i< n; i ++){
            
            boolean is_zero = ( s.charAt(i) == '0' );
            
            if( is_zero ){
                p++;
            }
            
            int z_start = is_zero ? 1 : 0;
            
            for( int z = z_start; z <= max_z; z ++){
                
                int next_zero_idx_pos;
                
                if( is_zero ){
                    next_zero_idx_pos = p + z;
                } else {
                    next_zero_idx_pos = p + z + 1;
                }
                
                if( next_zero_idx_pos >= m ){
                    break;
                }
                
                long start_j;
                
                if( z == 0 ){
                    start_j = i;
                } else {
                    start_j = zero_idx_list.get( next_zero_idx_pos - 1 );
                }
                
                long end_j = zero_idx_list.get( next_zero_idx_pos ) - 1;
                long j_needed = (long)i + (long)z * z + (long)z - 1;
                long j_start = Math.max( start_j, j_needed );
                
                if( j_start <= end_j ){
                    ans += ( end_j - j_start + 1 );
                }
            }
        }
        
        return ans;
    }
}