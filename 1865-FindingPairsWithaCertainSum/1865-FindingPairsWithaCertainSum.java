// Last updated: 7/6/2025, 8:12:03 PM
class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> nums2Frequency; // Stores frequency of elements in nums2

    /**
     * Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
     */
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.nums2Frequency = new HashMap<>();
        // Populate the frequency map for nums2
        for (int num : nums2) {
            nums2Frequency.put(num, nums2Frequency.getOrDefault(num, 0) + 1);
        }
    }

    /**
     * Adds val to nums2[index], i.e., apply nums2[index] += val.
     * Updates the frequency map accordingly.
     */
    public void add(int index, int val) {
        // Decrement the count of the old value in nums2Frequency
        nums2Frequency.put(nums2[index], nums2Frequency.get(nums2[index]) - 1);
        
        // Update the value in nums2
        nums2[index] += val;
        
        // Increment the count of the new value in nums2Frequency
        nums2Frequency.put(nums2[index], nums2Frequency.getOrDefault(nums2[index], 0) + 1);
    }

    /**
     * Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
     */
    public int count(int tot) {
        int pairCount = 0;
        // Iterate through each element in nums1
        for (int num1 : nums1) {
            // Calculate the required complement from nums2
            int complement = tot - num1;
            // If the complement exists in nums2Frequency, add its count to pairCount
            if (nums2Frequency.containsKey(complement)) {
                pairCount += nums2Frequency.get(complement);
            }
        }
        return pairCount;
    }
}