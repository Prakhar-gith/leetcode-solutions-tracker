// Algorithm:
// 1. Treat the Excel column title as a base-26 number.
// 2. Initialize a variable 'result' to 0.
// 3. For each character in the string, convert it to its corresponding number (A -> 1, B -> 2, ..., Z -> 26).
// 4. Multiply 'result' by 26 and add the current character's value.
// 5. Return the final 'result' which is the column number.

/*
   Pseudo Code:
   -------------
   function titleToNumber(columnTitle):
       result = 0
       for each character c in columnTitle:
           value = (c - 'A' + 1)
           result = result * 26 + value
       return result
       
   Visualization Example for "AB":
   ---------------------------------
   - Start with result = 0.
   - For first character 'A':
         value = (A - 'A' + 1) = 1
         result = 0 * 26 + 1 = 1
   - For second character 'B':
         value = (B - 'A' + 1) = 2
         result = 1 * 26 + 2 = 28
   Final result: 28 (which corresponds to "AB")
*/

class Solution {
    public int titleToNumber(String columnTitle) {
        // Initialize result to store the cumulative column number
        int result = 0;
        
        // Iterate through each character in the string
        for (int i = 0; i < columnTitle.length(); i++) {
            // Convert character to its numerical value: 'A' -> 1, 'B' -> 2, ..., 'Z' -> 26
            int value = columnTitle.charAt(i) - 'A' + 1;
            
            // Multiply result by 26 (base conversion) and add the current character's value
            result = result * 26 + value;
            // Explanation: Har iteration mein hum previous result ko 26 se multiply karte hain aur current character ka value add kar dete hain,
            // taki hum base-26 system ka use kar sakein.
        }
        
        // Return the computed column number
        return result;
    }
}
