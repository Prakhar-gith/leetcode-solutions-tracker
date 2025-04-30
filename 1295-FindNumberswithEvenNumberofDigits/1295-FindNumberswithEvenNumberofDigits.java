// Last updated: 4/30/2025, 9:10:51 PM
class Solution {
    public int findNumbers(int[] nums) {
        int countOfEvenDigitNumbers = 0;
        for (int num : nums) {
            int digitCount = 0;
            if (num == 0) {
                digitCount = 1;
            } else {
                int temp = num;
                while (temp > 0) {
                    temp /= 10;
                    digitCount++;
                }
            }

            if (digitCount % 2 == 0) {
                countOfEvenDigitNumbers++;
            }
        }
        return countOfEvenDigitNumbers;
    }
}