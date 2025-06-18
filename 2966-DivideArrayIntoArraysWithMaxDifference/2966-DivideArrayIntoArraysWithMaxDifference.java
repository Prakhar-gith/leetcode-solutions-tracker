// Last updated: 6/18/2025, 10:13:12 AM
import java.util.Arrays;

class Solution {
    public int[][] divideArray(int[] nums, int k) {
        // Sort the array to easily pick elements that are close to each other
        Arrays.sort(nums);

        int n = nums.length;
        // The number of triplets will be n / 3
        int[][] result = new int[n / 3][3];

        // Iterate through the sorted array in steps of 3
        for (int i = 0; i < n; i += 3) {
            // For each triplet, check if the difference between the largest and smallest element
            // is less than or equal to k. Since the array is sorted, this is nums[i+2] - nums[i].
            if (nums[i + 2] - nums[i] > k) {
                // If the condition is not met, it's impossible to satisfy the conditions
                // for the entire array, so return an empty array.
                return new int[0][0];
            } else {
                // If the condition is met, add the triplet to the result array
                result[i / 3][0] = nums[i];
                result[i / 3][1] = nums[i + 1];
                result[i / 3][2] = nums[i + 2];
            }
        }

        // If the loop completes, it means all triplets were successfully formed
        return result;
    }
}