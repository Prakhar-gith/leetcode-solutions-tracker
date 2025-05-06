// Last updated: 5/7/2025, 12:49:17 AM
class Solution {
    public int[] buildArray(int[] nums) {
        // Algorithm:
        // The problem requires us to construct a new array 'ans' of the same length as the input permutation array 'nums'.
        // The rule for building 'ans' is explicitly provided: for every index 'i' from 0 to nums.length - 1, the value at ans[i] must be equal to the value in 'nums' found at the index specified by the value of nums[i]. That is, ans[i] = nums[nums[i]].
        // Since the constraints on the array length (up to 1000) allow for using extra space proportional to the input size (O(n)), the most straightforward and easy-to-understand approach is to create a new result array of the same size and fill it directly according to the given formula.
        // We will iterate through each index 'i' of the input array 'nums' from 0 up to n-1. For each index 'i', we compute the value nums[nums[i]] and store it in the corresponding index 'i' of the newly created 'ans' array.

        // Pseudocode:
        // function buildArray(nums):
        //   // Input array 'nums' ki length nikal lete hain aur use 'n' variable mein store karte hain.
        //   // Get the length of the input array 'nums' and store it in a variable 'n'.
        //   n = length of nums
        //
        //   // Ek naya integer array 'ans' banate hain jiska size 'n' (input array ke barabar) hoga.
        //   // Create a new integer array 'ans' with the same length as the input array 'nums'.
        //   ans = create new integer array of size n
        //
        //   // Input array 'nums' ke har valid index 'i' (0 se n-1 tak) se iterate karte hain.
        //   // Iterate through each valid index 'i' of the input array 'nums' (from 0 to n-1).
        //   for i from 0 to n - 1:
        //     // Problem ke rule ke mutabik, 'ans' array ke current index 'i' par value
        //     // original array 'nums' ke 'nums[i]'th index par jo value hai, woh honi chahiye.
        //     // According to the problem's rule, the value at the current index 'i' of the 'ans' array
        //     // should be the value found in the original array 'nums' at the index specified by nums[i].
        //     // This means ans[i] = nums[nums[i]].
        //     // Pehle nums[i] ki value nikalte hain. Phir is value ko index maan kar nums mein se final value nikalte hain
        //     // aur use ans[i] mein store karte hain.
        //     // First, get the value at nums[i], and then use this value as an index to access nums again.
        //     // The value at ans[i] is the value in nums at the index nums[i].
        //     ans[i] = nums[nums[i]]
        //
        //   // Poora 'ans' array fill karne ke baad, usse return karte hain.
        //   // After filling the entire 'ans' array, return it.
        //   return ans

        // Visualization:
        // Imagine aapke paas ek original array 'nums' hai. Aapko ek naya array 'ans' banana hai bilkul uske jaisa size ka.
        // Aap naye array 'ans' ke har khali slot ko bharne ke liye 'nums' array ko reference karenge.
        // Har index 'i' ke liye naye array 'ans' mein value daalne ke liye:
        // 1. Original array 'nums' mein index 'i' par jo value hai, woh dekho (ye hai `nums[i]`).
        // 2. Ab, jo value aapne step 1 mein dekhi (yani `nums[i]`), usse ek index ki tarah use karo. Original array 'nums' mein us index par jo value hai, woh dekho (ye hai `nums[nums[i]]`).
        // 3. Yeh jo `nums[nums[i]]` value aapne step 2 mein dekhi, isse naye array 'ans' ke index 'i' par daal do (`ans[i] = nums[nums[i]]`).
        // Yeh process saare indices (0 se shuru karke array ke end tak) ke liye repeat karo.
        //
        // Example: nums = [0, 2, 1, 5, 3, 4]
        // Humein ek naya array 'ans' banana hai jiska size nums jitna ho (yani 6).
        // ans = [?, ?, ?, ?, ?, ?]
        //
        // Index i = 0:
        // nums[0] dekho: value hai 0.
        // Ab, 0 ko index use karo, nums[0] dekho: value hai 0.
        // ans[0] = 0.
        // ans = [0, ?, ?, ?, ?, ?]
        //
        // Index i = 1:
        // nums[1] dekho: value hai 2.
        // Ab, 2 ko index use karo, nums[2] dekho: value hai 1.
        // ans[1] = 1.
        // ans = [0, 1, ?, ?, ?, ?]
        //
        // Index i = 2:
        // nums[2] dekho: value hai 1.
        // Ab, 1 ko index use karo, nums[1] dekho: value hai 2.
        // ans[2] = 2.
        // ans = [0, 1, 2, ?, ?, ?]
        //
        // ... aur aise hi baki indices ke liye bhi.
        // Is tarah se, aap naye 'ans' array ke har element ko original 'nums' array se do baar lookup karke fill karte hain.

        // Implementation:
        // Get the length of the input array nums.
        // Input array nums ki length nikal lete hain.
        int n = nums.length;

        // Create a new integer array ans with the same length as nums.
        // This array will store the result.
        // Ek naya integer array ans banate hain jiska size nums ke barabar hai.
        // Yeh array result store karega.
        int[] ans = new int[n];

        // Iterate through each index i from 0 up to n-1.
        // For each index i, we calculate the value that should be at ans[i].
        // Har index i (0 se n-1 tak) se iterate karte hain.
        // Har index i ke liye, woh value calculate karte hain jo ans[i] par honi chahiye.
        for (int i = 0; i < n; i++) {
            // According to the problem's rule, ans[i] = nums[nums[i]].
            // First, we get the value at nums[i]. This value is guaranteed to be a valid index (0 to n-1) because nums is a permutation.
            // Then, we use this value as an index to access the nums array again and get the final value for ans[i].
            // Problem ke rule ke mutabik, ans[i] = nums[nums[i]].
            // Pehle, nums[i] ki value nikalte hain. Yeh value ek valid index (0 se n-1 tak) hone ki guarantee hai kyunki nums ek permutation hai.
            // Phir, is value ko index ki tarah use karke nums array ko dobara access karte hain aur ans[i] ke liye final value nikalte hain.
            int indexToAccess = nums[i];
            
            // The value at ans[i] is the value stored in nums at the index 'indexToAccess'.
            // ans[i] par jo value aayegi woh nums mein 'indexToAccess' index par stored value hai.
            ans[i] = nums[indexToAccess];
            
            // This is equivalent to the more compact form: ans[i] = nums[nums[i]];
            // Yeh zyada compact form ke barabar hai: ans[i] = nums[nums[i]];
        }

        // Return the newly constructed array ans, which contains the required elements.
        // Naye banaye gaye array ans ko return karte hain, jismein chahiye elements hain.
        return ans;
    }
}