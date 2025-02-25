// Algorithm:
// 1. Pehle input array ko sort karo taaki duplicates ek saath aa jayein.
// 2. Backtracking use karke har permutation generate karo.
// 3. Ek boolean array 'used' maintain karo jo bataye ke koi element current permutation mein use ho chuka hai ya nahi.
// 4. Recursively iterate through the array:
//    - Agar current element already used hai, toh skip karo.
//    - Agar current element duplicate hai (i.e., nums[i] == nums[i-1]) aur previous element used nahi hua hai, toh bhi skip karo, 
//      taki duplicate permutation na bane.
// 5. Jab current permutation ki length nums.length ke barabar ho jaye, us permutation ko result list mein add kar do.
// 6. Finally, return the result list containing all unique permutations.

/*
   Pseudo Code:
   -----------
   function permuteUnique(nums):
       sort(nums)
       result = new List of Lists
       used = new boolean array of length nums.length (initialized to false)
       current = empty list
       backtrack(nums, used, current, result)
       return result

   function backtrack(nums, used, current, result):
       if current.size == nums.length:
           add a copy of current to result
           return
       for i from 0 to nums.length - 1:
           if used[i] is true:
               continue
           if i > 0 and nums[i] == nums[i-1] and used[i-1] is false:
               continue
           mark used[i] as true
           add nums[i] to current
           backtrack(nums, used, current, result)
           remove last element from current
           mark used[i] as false
*/

/*
   Visualization Example for nums = [1,1,2]:
   
   Sorted nums = [1,1,2]

           []
         /  |  \
       1    1    2
       |    |    |
     [1]  [1]   [2]
    /   \   \   /
  1      2   2  (skip duplicate branch)
  |      | 
 [1,1] [1,2]
   |      |
   2      1
   |      |
[1,1,2] [1,2,1]

   Final unique permutations:
   [1,1,2], [1,2,1], [2,1,1]
*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Sort the array to group duplicates together for easier duplicate handling
        Arrays.sort(nums);
        
        // 'result' will hold all the unique permutations generated
        List<List<Integer>> result = new ArrayList<>();
        // 'current' will store the current permutation sequence during backtracking
        List<Integer> current = new ArrayList<>();
        // 'used' array to mark elements that are already included in 'current'
        boolean[] used = new boolean[nums.length];
        
        // Start backtracking to generate permutations
        backtrack(nums, used, current, result);
        return result; // Return the final list of unique permutations
    }
    
    // Helper function for backtracking
    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // Base condition: agar current permutation complete ho gayi (size equals nums.length), then add a copy of it to result
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // Deep copy is essential
            return;
        }
        
        // Iterate through each element in nums
        for (int i = 0; i < nums.length; i++) {
            // Agar current element already used hai, toh skip karo
            if (used[i]) continue;
            
            // Duplicate check:
            // Agar i > 0, current element same as previous and previous element not used, then skip to avoid duplicate permutation
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            
            // Mark current element as used
            used[i] = true;
            // Add the current element to the permutation list
            current.add(nums[i]);
            
            // Recursively call backtracking to fill the next positions
            backtrack(nums, used, current, result);
            
            // Backtracking step:
            // Remove the last element added and mark it as unused for the next iterations
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
