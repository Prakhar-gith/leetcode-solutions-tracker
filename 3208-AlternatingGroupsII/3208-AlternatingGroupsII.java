// Algorithm:
// 1. Pehle, ek diff array banate hain of length n (n = colors.length) where each diff[i] is 1 
//    if colors[i] and colors[(i+1) mod n] are different, otherwise 0. 
//    Ye diff array batata hai ki adjacent pair alternating hai ya nahi.
// 2. Since the colors array is circular, diff array is also circular.
// 3. To easily handle the wrap-around for groups of k contiguous tiles, hum diff array ka ek extended version banayenge
//    by concatenating diff with itself (i.e., extended array of length 2*n).
// 4. Ab, ek sliding window of length (k - 1) chalayein over the extended diff array. 
//    Kyunki ek group of k tiles mein (k - 1) adjacent pairs honge.
// 5. For each starting index i in [0, n-1]:
//      - Agar sliding window sum (i.e., total number of alternating adjacent pairs in that window) equals (k - 1),
//        iska matlab group starting at i is fully alternating.
//      - Count such groups.
// 6. Return the count as the number of alternating groups.

// Pseudo Code:
// -------------
// function countAlternatingGroups(colors, k):
//     n = length(colors)
//     diff = new array of length n
//     for i from 0 to n-1:
//         diff[i] = 1 if colors[i] != colors[(i+1)%n] else 0
//     
//     extended = new array of length 2*n
//     for i from 0 to 2*n-1:
//         extended[i] = diff[i % n]
//
//     windowLength = k - 1
//     sum = sum of extended[0...windowLength-1]
//     count = 0
//     if sum == windowLength:
//         count++
//     
//     for i from 1 to n-1:
//         sum = sum - extended[i-1] + extended[i+windowLength-1]
//         if sum == windowLength:
//             count++
//     
//     return count

// Visualization Example:
// ------------------------
// Consider colors = [0,1,0,1,0] and k = 3, n = 5.
// diff array (comparing each element with next, circularly):
//   diff[0] = (0 != 1) = 1
//   diff[1] = (1 != 0) = 1
//   diff[2] = (0 != 1) = 1
//   diff[3] = (1 != 0) = 1
//   diff[4] = (0 != 0) = 0   // since colors[4]=0 and colors[0]=0 (wrap-around)
// extended array = [1,1,1,1,0, 1,1,1,1,0]
// For k = 3, window length = 2.
// Slide the window over extended indices [0..4]:
//   Window starting at 0: [1,1] -> sum=2 -> valid (2==k-1)
//   Window starting at 1: [1,1] -> sum=2 -> valid
//   Window starting at 2: [1,1] -> sum=2 -> valid
//   Window starting at 3: [1,0] -> sum=1 -> not valid
//   Window starting at 4: [0,1] -> sum=1 -> not valid
// Total valid groups = 3.

// Code:
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;  // Total number of tiles
        // diff[i] will be 1 if the tile at i and tile at (i+1) mod n have different colors, else 0.
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = (colors[i] != colors[(i + 1) % n]) ? 1 : 0;
        }
        
        // Create an extended array of diff to handle circular wrap-around easily.
        int[] extended = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            extended[i] = diff[i % n];
        }
        
        int windowLength = k - 1; // In a group of k tiles, there are (k-1) adjacent pairs.
        int windowSum = 0;
        
        // Calculate the sum of the first window (from index 0 to windowLength-1 in the extended array)
        for (int i = 0; i < windowLength; i++) {
            windowSum += extended[i];
        }
        
        int count = 0; // Count of valid alternating groups
        
        // Agar first window sum equals (k-1), then group starting at index 0 is alternating.
        if (windowSum == windowLength) {
            count++;
        }
        
        // Slide the window from index 1 to index n-1 (these correspond to starting positions in the circle)
        for (int i = 1; i < n; i++) {
            // Update the window sum: remove the element exiting the window and add the new element entering the window.
            windowSum = windowSum - extended[i - 1] + extended[i + windowLength - 1];
            if (windowSum == windowLength) { // Agar window sum equals (k-1), group is alternating.
                count++;
            }
        }
        
        // Return the total count of alternating groups.
        return count;
    }
}
