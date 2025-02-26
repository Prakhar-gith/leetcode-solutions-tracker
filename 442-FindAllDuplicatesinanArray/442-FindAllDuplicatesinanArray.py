# Algorithm:
# 1. Traverse through the nums list.
# 2. For each element, determine its corresponding index by taking the absolute value and subtracting 1.
# 3. If the element at that index is already negative, it means the number has been seen before, so add the absolute value of the current number to the result list.
# 4. Otherwise, mark the element at that index as negative to indicate that the number has been seen.
# 5. Return the result list containing all duplicates.
#
# Pseudo Code:
# -----------
# function findDuplicates(nums):
#     result = empty list
#     for i from 0 to length(nums) - 1:
#         index = abs(nums[i]) - 1
#         if nums[index] < 0:
#             add abs(nums[i]) to result
#         else:
#             nums[index] = -nums[index]
#     return result
#
# Visualization:
# --------------
# Consider nums = [4, 3, 2, 7, 8, 2, 3, 1]
# 
# Initial state:
#   nums = [4, 3, 2, 7, 8, 2, 3, 1]
#   result = []
#
# Iteration 1:
#   i = 0, current number = 4, index = 3 (4-1)
#   nums[3] = 7 (positive), so mark it negative: nums becomes [4, 3, 2, -7, 8, 2, 3, 1]
#
# Iteration 2:
#   i = 1, current number = 3, index = 2 (3-1)
#   nums[2] = 2 (positive), so mark it negative: nums becomes [4, 3, -2, -7, 8, 2, 3, 1]
#
# Iteration 3:
#   i = 2, current number = -2, absolute = 2, index = 1 (2-1)
#   nums[1] = 3 (positive), so mark it negative: nums becomes [4, -3, -2, -7, 8, 2, 3, 1]
#
# Iteration 4:
#   i = 3, current number = -7, absolute = 7, index = 6 (7-1)
#   nums[6] = 3 (positive), so mark it negative: nums becomes [4, -3, -2, -7, 8, 2, -3, 1]
#
# Iteration 5:
#   i = 4, current number = 8, index = 7 (8-1)
#   nums[7] = 1 (positive), so mark it negative: nums becomes [4, -3, -2, -7, 8, 2, -3, -1]
#
# Iteration 6:
#   i = 5, current number = 2, index = 1 (2-1)
#   nums[1] = -3 (negative), duplicate found: add 2 to result
#
# Iteration 7:
#   i = 6, current number = -3, absolute = 3, index = 2 (3-1)
#   nums[2] = -2 (negative), duplicate found: add 3 to result
#
# Iteration 8:
#   i = 7, current number = -1, absolute = 1, index = 0 (1-1)
#   nums[0] = 4 (positive), so mark it negative: nums becomes [-4, -3, -2, -7, 8, 2, -3, -1]
#
# Final result:
#   result = [2, 3]

class Solution:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        # Initialize a list to store the duplicate numbers found in the array.
        result = []
        
        # Traverse each number in the array
        for i in range(len(nums)):
            # Calculate the index corresponding to the absolute value of the current number
            index = abs(nums[i]) - 1  # subtracting 1 because list indices start from 0
            
            # If the element at the calculated index is negative,
            # it means the number (index+1) has been seen before, hence it's a duplicate.
            if nums[index] < 0:
                result.append(abs(nums[i]))  # add the duplicate number to the result list
            else:
                # Mark the element at the calculated index as negative to indicate its presence.
                nums[index] = -nums[index]
        
        # Return the list containing all the duplicates found.
        return result
