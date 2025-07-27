// Last updated: 7/27/2025, 10:59:55 PM
class Solution {
    public int countHillValley(int[] nums) {
        
        // Algorithm:
        // The problem is to count "hills" and "valleys", with a special rule for consecutive
        // equal numbers (plateaus). The rule says that a plateau like `[4,4,4]` should be treated
        // as a single hill or valley. This gives us a key insight: we only need to care about
        // the sequence of *distinct adjacent* numbers.
        //
        // So, the algorithm is a two-step process:
        // 1. Simplify the Array: We first create a new list from the input `nums` by removing
        //    any consecutive duplicates. For example, `[2,4,1,1,6,5]` becomes `[2,4,1,6,5]`.
        //    This new list represents the effective sequence of rises and falls.
        //
        // 2. Count Hills and Valleys: We then iterate through this simplified list from the second
        //    element to the second-to-last. The first and last elements can't be hills or valleys
        //    as they don't have neighbors on both sides. For each element `mid`, we compare it to its
        //    immediate `left` and `right` neighbors to see if it forms a hill (`mid > left && mid > right`)
        //    or a valley (`mid < left && mid < right`).

        // Pseudo Code:
        // 1. simplified_nums = new ArrayList()
        // 2. // Step 1: Remove consecutive duplicates from nums.
        // 3. simplified_nums.add(nums[0])
        // 4. for i from 1 to nums.length - 1:
        // 5.   if nums[i] != nums[i-1]:
        // 6.     simplified_nums.add(nums[i])
        //
        // 7. count = 0
        // 8. // Step 2: Iterate through the simplified list to find hills and valleys.
        // 9. // We skip the first and last elements.
        // 10. for i from 1 to simplified_nums.size() - 2:
        // 11.   left = simplified_nums[i-1]
        // 12.   mid = simplified_nums[i]
        // 13.   right = simplified_nums[i+1]
        // 14.   if (mid > left AND mid > right) OR (mid < left AND mid < right):
        // 15.     count++
        //
        // 16. return count

        // Step 1: Simplify the array by removing consecutive duplicates.
        // Ek nayi list banayenge jisme consecutive duplicates nahi honge.
        List<Integer> simplifiedNums = new ArrayList<>();
        if (nums.length > 0) {
            simplifiedNums.add(nums[0]);
        }
        for (int i = 1; i < nums.length; i++) {
            // Only add the current number if it's different from the previous one.
            // Sirf alag numbers ko add karo.
            if (nums[i] != nums[i - 1]) {
                simplifiedNums.add(nums[i]);
            }
        }

        int count = 0;
        // Step 2: Iterate through the simplified list to count hills and valleys.
        // We need at least 3 elements to form a hill or valley.
        if (simplifiedNums.size() < 3) {
            return 0;
        }

        // We check every element except the first and the last.
        // Pehla aur aakhri element ko chhod kar sabko check karenge.
        for (int i = 1; i < simplifiedNums.size() - 1; i++) {
            int left = simplifiedNums.get(i - 1);
            int mid = simplifiedNums.get(i);
            int right = simplifiedNums.get(i + 1);

            // Check if the middle element is a hill (peak).
            // Check karo ki element hill (pahaad) hai ya nahi.
            boolean isHill = mid > left && mid > right;

            // Check if the middle element is a valley (trough).
            // Check karo ki element valley (ghaati) hai ya nahi.
            boolean isValley = mid < left && mid < right;

            if (isHill || isValley) {
                count++;
            }
        }

        return count;

        /*
         * Visualization of the process:
         *
         * Input:          nums = [2, 4, 1, 1, 6, 5]
         *
         * Step 1: Simplification
         * The `[1, 1]` is a plateau, so we treat it as one.
         * The `simplifiedNums` list becomes: [2, 4, 1, 6, 5]
         *
         * Step 2: Counting on the Simplified List
         * We check the triplets [left, mid, right] from the new list.
         *
         * - i = 1: The triplet is [2, 4, 1].
         * - `4 > 2` and `4 > 1` -> This is a Hill. `count = 1`.
         *
         * - i = 2: The triplet is [4, 1, 6].
         * - `1 < 4` and `1 < 6` -> This is a Valley. `count = 2`.
         *
         * - i = 3: The triplet is [1, 6, 5].
         * - `6 > 1` and `6 > 5` -> This is a Hill. `count = 3`.
         *
         * The loop ends. The final count is 3.
        */
    }
}