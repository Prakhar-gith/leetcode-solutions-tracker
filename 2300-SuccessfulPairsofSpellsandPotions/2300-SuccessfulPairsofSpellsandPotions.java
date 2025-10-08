// Last updated: 10/8/2025, 6:36:31 PM
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        
        Arrays.sort(potions);
        
        for (int i = 0; i < n; i++) {
            long spell = spells[i];
            long target = (success + spell - 1) / spell;
            
            int left = 0;
            int right = m;
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (potions[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            
            pairs[i] = m - left;
        }
        
        return pairs;
    }
}