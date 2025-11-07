// Last updated: 11/7/2025, 8:55:23 PM
class Solution {
    
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        
        long[] prefixSum = new long[n + 1];
        long totalStations = 0L;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stations[i];
            totalStations += stations[i];
        }
        
        long[] initial_power = new long[n];
        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n - 1, i + r);
            initial_power[i] = prefixSum[right + 1] - prefixSum[left];
        }
        
        long low = 0L;
        long high = totalStations + k; 
        long ans = 0L;
        
        while (low <= high) {
            long mid_target = low + (high - low) / 2;
            if (isPossible(mid_target, n, r, k, initial_power)) {
                ans = mid_target;
                low = mid_target + 1;
            } else {
                high = mid_target - 1;
            }
        }
        return ans;
    }
    
    private boolean isPossible(long target, int n, int r, long k, long[] initial_power) {
        
        long[] additions = new long[n];
        long used_k = 0L;
        long current_added_power = 0L; 
        
        for (int i = 0; i < n; i++) {
            
            if (i > 0) {
                current_added_power += (i + r < n) ? additions[i + r] : 0;
                current_added_power -= (i - r - 1 >= 0) ? additions[i - r - 1] : 0;
            } else {
                 for (int j = 0; j <= r && j < n; j++) {
                    current_added_power += additions[j];
                }
            }
            
            long power_at_i = initial_power[i] + current_added_power;
            
            if (power_at_i < target) {
                
                long needed = target - power_at_i;
                used_k += needed;
                if (used_k > k) {
                    return false;
                }
                
                int placement_idx = Math.min(n - 1, i + r);
                additions[placement_idx] += needed;
                
                current_added_power += needed;
            }
        }
        
        return true;
    }
}