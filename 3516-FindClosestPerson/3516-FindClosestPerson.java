// Last updated: 9/4/2025, 5:34:44 PM
class Solution {
    public int findClosest(int x, int y, int z) {
        int distance1 = Math.abs(x - z);
        int distance2 = Math.abs(y - z);

        if (distance1 < distance2) {
            return 1;
        } else if (distance2 < distance1) {
            return 2;
        } else {
            return 0;
        }
    }
}