// Last updated: 6/25/2025, 8:44:15 PM
class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = -10_000_000_001L; // Minimum possible product (-10^5 * 10^5 = -10^10)
        long right = 10_000_000_001L;  // Maximum possible product (10^5 * 10^5 = 10^10)
        long ans = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countLessEqual(nums1, nums2, mid) >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private long countLessEqual(int[] nums1, int[] nums2, long val) {
        long count = 0;
        for (int num1 : nums1) {
            if (num1 == 0) {
                if (val >= 0) {
                    count += nums2.length;
                }
            } else if (num1 > 0) {
                // Find count of nums2[j] such that num1 * nums2[j] <= val
                // Since num1 > 0, this is equivalent to nums2[j] <= val / num1
                // We need to find the rightmost index (upper bound)
                int low = 0, high = nums2.length - 1;
                int idx = -1; // Stores the rightmost index such that nums2[idx] * num1 <= val

                while (low <= high) {
                    int midIdx = low + (high - low) / 2;
                    if ((long) num1 * nums2[midIdx] <= val) {
                        idx = midIdx;
                        low = midIdx + 1;
                    } else {
                        high = midIdx - 1;
                    }
                }
                count += (idx + 1); // Add the count (idx is 0-indexed, so idx+1 elements)
            } else { // num1 < 0
                // Find count of nums2[j] such that num1 * nums2[j] <= val
                // Since num1 < 0, this is equivalent to nums2[j] >= val / num1
                // We need to find the leftmost index (lower bound)
                int low = 0, high = nums2.length - 1;
                int idx = nums2.length; // Stores the leftmost index such that nums2[idx] * num1 <= val

                while (low <= high) {
                    int midIdx = low + (high - low) / 2;
                    if ((long) num1 * nums2[midIdx] <= val) {
                        idx = midIdx; // This midIdx is a candidate for our left-most bound.
                        high = midIdx - 1; // Try to find an even smaller index.
                    } else {
                        low = midIdx + 1;
                    }
                }
                count += (nums2.length - idx); // Number of elements from idx to end of array
            }
        }
        return count;
    }
}