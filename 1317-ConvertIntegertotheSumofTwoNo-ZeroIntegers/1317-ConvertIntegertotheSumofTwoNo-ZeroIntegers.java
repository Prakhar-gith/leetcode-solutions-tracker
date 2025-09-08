// Last updated: 9/8/2025, 11:25:51 PM
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a <= n / 2; a++) {
            int b = n - a;
            if (!containsZero(a) && !containsZero(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{};
    }

    private boolean containsZero(int num) {
        return String.valueOf(num).contains("0");
    }
}