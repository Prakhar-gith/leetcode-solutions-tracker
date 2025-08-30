// Last updated: 8/30/2025, 6:55:56 PM
class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for(int i = 0; i < s.length(); i++){
            int reverse = ('z' - s.charAt(i)) + 1;
             sum += reverse * (i + 1);
        }
        return sum;
    }
}