// Algorithm:
// 1. Convert the number to a char array to work with individual digits.
// 2. Find the first digit from right (pivot) which is smaller than its next digit.
//    - Agar aisa digit mil jaye, toh woh pivot hoga jahan se hum swap start karenge.
// 3. If no such pivot exists, then the number is in descending order and no greater permutation exists; return -1.
// 4. Find the smallest digit to the right of the pivot that is greater than the pivot digit.
// 5. Swap the pivot with that digit.
// 6. Sort the subarray to the right of the pivot in ascending order to get the smallest possible number greater than the original.
// 7. Convert the resulting char array back to a number. If it exceeds Integer.MAX_VALUE, return -1; otherwise, return the result.

/*
   Pseudo Code:
   -------------
   function nextGreaterElement(n):
       number = convert n to char array
       idx = -1
       // Step 2: Find pivot index
       for i from number.length - 2 down to 0:
           if number[i] < number[i + 1]:
               idx = i
               break
       if idx == -1:
           return -1  // No pivot found, number is in descending order

       // Step 4: Find smallest digit greater than pivot
       val = number[idx]
       smallidx = idx + 1
       for i from idx + 1 to number.length - 1:
           if number[i] > val and number[i] <= number[smallidx]:
               smallidx = i

       // Step 5: Swap pivot with smallest greater digit
       swap(number, idx, smallidx)

       // Step 6: Sort the subarray from idx + 1 to end in ascending order
       sort(number, idx + 1, number.length)

       ans = convert number back to long
       if ans > Integer.MAX_VALUE:
           return -1
       else:
           return ans as int

   Visualization Example for n = 12:
   ----------------------------------
   n = 12 -> number = ['1', '2']
   - Traverse from right: compare '1' and '2' => '1' < '2', so pivot at index 0.
   - Find smallest digit > '1' in the right part: that's '2' at index 1.
   - Swap: number becomes ['2', '1']
   - Sort subarray after index 0 (already sorted here): ['1']
   - Resulting number = 21, which is the answer.
*/

class Solution {
    public int nextGreaterElement(int n) {
        // Convert integer n to a char array representing its digits
        char[] number = (n + "").toCharArray();
        int idx = -1;  // This will store the pivot index

        // Find the pivot: first index from right where the digit is less than its next digit
        // yahan hum right se traverse kar rahe hain to find the first decrease
        for (int i = number.length - 2; i >= 0; i--) {
            if (number[i] < number[i + 1]) {
                idx = i; // Pivot index found
                break;
            }
        }

        // If no pivot found, then the digits are in descending order, so no greater permutation exists
        if (idx == -1) {
            return -1;
        }

        // Store the pivot digit value
        char val = number[idx];
        int smallidx = idx + 1;  // Initialize smallidx to the next position

        // Find the smallest digit greater than pivot on the right side
        // yahan, hume pivot se bada lekin sabse chota digit chahiye to swap ke liye
        for (int i = idx + 1; i < number.length; i++) {
            if (number[i] > val && number[i] <= number[smallidx]) {
                smallidx = i;
            }
        }

        // Swap the pivot digit with the found smallest greater digit
        char temp = number[idx];
        number[idx] = number[smallidx];
        number[smallidx] = temp;

        // Sort the subarray from index idx+1 to the end to get the smallest arrangement after the pivot
        // Arrays.sort with fromIndex (inclusive) and toIndex (exclusive)
        Arrays.sort(number, idx + 1, number.length);

        // Convert the char array back to a number (using long to check for overflow)
        long ans = Long.parseLong(new String(number));
        // If the answer does not fit in a 32-bit integer, return -1
        if (ans > Integer.MAX_VALUE) {
            return -1;
        } else {
            return (int) ans;
        }
    }
}
