// Algorithm:
// 1. Hum result list initialize karenge jisme saare subsets store honge.
// 2. Backtracking function create karo jo current subset build kare.
// 3. Har recursive call mein, current subset ka ek copy result list mein add kar do.
// 4. For-loop ke through array ke elements iterate karo starting from a given index:
//    - Current element ko current subset mein add karo.
//    - Recursively backtrack call karo with next index (i+1).
//    - Backtrack karte waqt, last added element ko remove karo to explore next possibility.
// 5. Return the final result list containing all subsets.

/*
   Pseudo Code:
   -------------
   function subsets(nums):
       result = new list
       current = empty list
       backtrack(0, nums, current, result)
       return result

   function backtrack(start, nums, current, result):
       add copy of current to result
       for i from start to nums.length - 1:
           add nums[i] to current
           backtrack(i + 1, nums, current, result)
           remove last element from current
*/

/*
   Visualization for nums = [1,2,3]:
   
   Start: current = []
       |__ Add [] to result

       i = 0, add 1 -> current = [1]
              |__ Add [1] to result
              i = 1, add 2 -> current = [1,2]
                      |__ Add [1,2] to result
                      i = 2, add 3 -> current = [1,2,3]
                              |__ Add [1,2,3] to result
                      Backtrack: remove 3 -> current = [1,2]
              Backtrack: remove 2 -> current = [1]
              i = 2, add 3 -> current = [1,3]
                      |__ Add [1,3] to result
              Backtrack: remove 3 -> current = [1]
       Backtrack: remove 1 -> current = []
       i = 1, add 2 -> current = [2]
              |__ Add [2] to result
              i = 2, add 3 -> current = [2,3]
                      |__ Add [2,3] to result
              Backtrack: remove 3 -> current = [2]
       Backtrack: remove 2 -> current = []
       i = 2, add 3 -> current = [3]
              |__ Add [3] to result
       Backtrack: remove 3 -> current = []

   Final result contains: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // Create a list to store all subsets (result)
        List<List<Integer>> result = new ArrayList<>();
        // Temporary list to store current subset during recursion
        List<Integer> current = new ArrayList<>();
        // Start backtracking from index 0
        backtrack(0, nums, current, result);
        return result; // Return all generated subsets
    }
    
    // Backtracking helper function to generate subsets
    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add a deep copy of the current subset to the result list
        result.add(new ArrayList<>(current));
        
        // Iterate over the array starting from 'start' index
        for (int i = start; i < nums.length; i++) {
            // Add current element to the subset
            current.add(nums[i]);
            // Recursively generate further subsets including this element
            backtrack(i + 1, nums, current, result);
            // Backtracking step: remove the last element added to try next possibility
            current.remove(current.size() - 1);
        }
    }
}
