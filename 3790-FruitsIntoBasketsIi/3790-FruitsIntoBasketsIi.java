// Last updated: 8/30/2025, 6:56:01 PM
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        boolean[] basketUsed = new boolean[baskets.length];
        int unplacedFruitsCount = 0;

        for (int fruit : fruits) {
            boolean isPlaced = false;
            for (int j = 0; j < baskets.length; j++) {
                if (!basketUsed[j] && baskets[j] >= fruit) {
                    basketUsed[j] = true;
                    isPlaced = true;
                    break;
                }
            }
            if (!isPlaced) {
                unplacedFruitsCount++;
            }
        }

        return unplacedFruitsCount;
    }
}