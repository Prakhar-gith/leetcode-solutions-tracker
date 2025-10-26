// Last updated: 10/26/2025, 8:39:14 PM
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort( nums );
        int n = nums.length;
        
        Set< Long > candidates = new HashSet<>() ;
        for (int num : nums) {
            candidates.add( (long) num ) ;
            candidates.add( (long) num - k );
            candidates.add( (long) num + k );
        }
        
        int maxFreq = 0;
        
        for (long T : candidates) {
            long lower = T - k ;
            long upper = T+ k ;
            
            int idxStart = lowerBound( nums, lower );
            int idxEnd = upperBound( nums, upper );
            int countAB = idxEnd- idxStart; 
            
            int idxStartT = lowerBound( nums, T );
            int idxEndT= upperBound( nums, T );
            int countA = idxEndT - idxStartT;
            
            int countB= countAB - countA;
            
            int currentFreq = countA + Math.min( countB, numOperations );
            maxFreq = Math.max( maxFreq, currentFreq );
        }
        
        return maxFreq;
    }
    
    private int lowerBound( int[] arr, long target) {
        int l =0, r= arr.length;
        while( l < r ) {
            int mid = l+ (r - l) / 2;
            if ( arr[mid] < target) {
                l = mid+ 1;
            } else {
                r= mid;
            }
        }
        return l ;
    }
    
    private int upperBound( int[] arr, long target) {
        int l= 0, r= arr.length;
        while (l < r) {
            int mid =l+ (r - l) / 2;
            if ( arr[mid] <= target) {
                l = mid + 1 ;
            } else {
                r = mid;
            }
        }
        return l ;
    }
}