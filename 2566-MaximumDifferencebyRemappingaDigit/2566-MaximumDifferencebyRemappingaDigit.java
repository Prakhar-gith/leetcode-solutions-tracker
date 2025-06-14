// Last updated: 6/15/2025, 12:55:41 AM
class Solution {
    public int minMaxDifference(int num) {
        String s = String.valueOf(num);

        // Calculate max_num
        char digitToReplaceForMax = ' ';
        // Find the first digit that is not '9'
        for (char c : s.toCharArray()) {
            if (c != '9') {
                digitToReplaceForMax = c;
                break;
            }
        }
        String maxStr;
        if (digitToReplaceForMax == ' ') { // All digits were '9'
            maxStr = s;
        } else {
            maxStr = s.replace(digitToReplaceForMax, '9');
        }
        int maxNum = Integer.parseInt(maxStr);

        // Calculate min_num
        char digitToReplaceForMin = s.charAt(0); // Always target the first digit
        String minStr = s.replace(digitToReplaceForMin, '0');
        int minNum = Integer.parseInt(minStr);
        
        return maxNum - minNum;
    }
}