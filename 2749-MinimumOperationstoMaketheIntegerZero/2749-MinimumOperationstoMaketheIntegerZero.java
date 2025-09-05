// Last updated: 9/5/2025, 11:09:51 PM
class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 65; k++) {
            long x = (long) num1 - (long) k * num2;
            if (x >= k && Long.bitCount(x) <= k) {
                return k;
            }
        }
        return -1;
    }
}