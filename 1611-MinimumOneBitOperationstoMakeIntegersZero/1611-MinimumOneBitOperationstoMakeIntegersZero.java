// Last updated: 11/8/2025, 7:36:28 PM
class Solution {
    public int minimumOneBitOperations(int n) {
        int res = 0;
        while( n > 0 ){
            res ^=n;
            n >>= 1;
        }
        return res ;
    }
}