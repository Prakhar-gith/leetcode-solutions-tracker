// Algorithm:
// 1. Initialize two pointers i and j to iterate over nums1 and nums2 respectively.
// 2. Create an ArrayList to store the merged result.
// 3. Traverse both arrays:
//    - If the current ids (nums1[i][0] and nums2[j][0]) are equal, add a new entry 
//      with id and the sum of their values, then increment both pointers.
//    - If nums1[i][0] is less than nums2[j][0], add the current nums1 element to the result and increment i.
//    - Otherwise, add the current nums2 element to the result and increment j.
// 4. After the loop, add any remaining elements from nums1 or nums2.
// 5. Convert the ArrayList into a 2D array and return it.
//
// Pseudo Code:
// -------------
// function mergeArrays(nums1, nums2):
//     i = 0, j = 0
//     result = new list
//     while i < length(nums1) and j < length(nums2):
//         if nums1[i][0] == nums2[j][0]:
//             result.add([nums1[i][0], nums1[i][1] + nums2[j][1]])
//             i++, j++
//         else if nums1[i][0] < nums2[j][0]:
//             result.add(nums1[i])
//             i++
//         else:
//             result.add(nums2[j])
//             j++
//     while i < length(nums1):
//         result.add(nums1[i])
//         i++
//     while j < length(nums2):
//         result.add(nums2[j])
//         j++
//     return result converted to int[][]
//
// Visualization Example:
// ------------------------
// Let nums1 = [[1,2],[2,3],[4,5]] and nums2 = [[1,4],[3,2],[4,1]].
// - Start with pointers i = 0, j = 0.
// - Compare nums1[0] = [1,2] with nums2[0] = [1,4]:
//      IDs are equal (1), so add [1, 6] to result and move both pointers.
// - Now, i = 1 and j = 1:
//      Compare nums1[1] = [2,3] with nums2[1] = [3,2]:
//      Since 2 < 3, add [2,3] from nums1 and increment i.
// - Now, i = 2 and j = 1:
//      Compare nums1[2] = [4,5] with nums2[1] = [3,2]:
//      Since 4 > 3, add [3,2] from nums2 and increment j.
// - Now, i = 2 and j = 2:
//      Compare nums1[2] = [4,5] with nums2[2] = [4,1]:
//      IDs are equal (4), so add [4,6] (5+1) and increment both pointers.
// - Both arrays are fully traversed, resulting in [[1,6],[2,3],[3,2],[4,6]].

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        // Initialize two pointers for both arrays
        int i = 0, j = 0;
        // ArrayList to store the merged results
        List<int[]> resultList = new ArrayList<>();
        
        // Traverse both arrays using two pointers
        while (i < nums1.length && j < nums2.length) {
            // Check if the ids are equal
            if (nums1[i][0] == nums2[j][0]) {
                // Agar ids same hain, unke values ka sum karo and add the merged pair
                resultList.add(new int[]{nums1[i][0], nums1[i][1] + nums2[j][1]});
                i++; // Move pointer for nums1
                j++; // Move pointer for nums2
            }
            // If the id in nums1 is smaller, add nums1's element to result
            else if (nums1[i][0] < nums2[j][0]) {
                resultList.add(new int[]{nums1[i][0], nums1[i][1]});
                i++; // Move pointer for nums1
            }
            // Otherwise, the id in nums2 is smaller, so add nums2's element to result
            else {
                resultList.add(new int[]{nums2[j][0], nums2[j][1]});
                j++; // Move pointer for nums2
            }
        }
        
        // If any elements are left in nums1, add them to the result
        while (i < nums1.length) {
            resultList.add(new int[]{nums1[i][0], nums1[i][1]});
            i++;
        }
        
        // If any elements are left in nums2, add them to the result
        while (j < nums2.length) {
            resultList.add(new int[]{nums2[j][0], nums2[j][1]});
            j++;
        }
        
        // Convert the ArrayList into a 2D array to return
        int[][] result = new int[resultList.size()][2];
        for (int k = 0; k < resultList.size(); k++) {
            result[k] = resultList.get(k);
        }
        
        return result;
    }
}
