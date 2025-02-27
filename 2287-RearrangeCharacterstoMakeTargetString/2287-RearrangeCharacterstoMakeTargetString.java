// Algorithm:
// 1. Count the frequency of each character in string s and in target.
// 2. For every character present in target, calculate how many times it can be formed from s by dividing its frequency in s by its frequency in target.
// 3. The maximum number of target copies is the minimum of these ratios across all characters in target.
//
// Pseudo Code:
// -------------
// function rearrangeCharacters(s, target):
//     freqS = new array of size 26 initialized to 0
//     freqT = new array of size 26 initialized to 0
//     
//     for each character c in s:
//         freqS[c - 'a']++
//     
//     for each character c in target:
//         freqT[c - 'a']++
//     
//     answer = infinity
//     for i from 0 to 25:
//         if freqT[i] > 0:
//             answer = min(answer, freqS[i] / freqT[i])
//     
//     return answer
//
// Visualization:
// --------------
// Example: s = "ilovecodingonleetcode", target = "code"
//   - freqS for letters 'c','o','d','e' are counted from s.
//   - freqT for "code" is { c:1, o:1, d:1, e:1 }.
//   - The number of times each letter in target can be formed is calculated,
//     and the answer is the minimum of these counts.
//
// Code with detailed "hinglish" comments:

class Solution {
    public int rearrangeCharacters(String s, String target) {
        // Create frequency arrays for s and target, size 26 for each lowercase letter
        int[] freqS = new int[26]; // yahan s ke har letter ki frequency count karenge
        int[] freqT = new int[26]; // yahan target ke har letter ki frequency count karenge

        // Count frequency for string s
        for (char c : s.toCharArray()) {
            freqS[c - 'a']++; // 'a' ko base maan ke index calculate karte hain aur frequency increment karte hain
        }
        
        // Count frequency for target string
        for (char c : target.toCharArray()) {
            freqT[c - 'a']++; // target ke letters ke frequencies count kar lo
        }
        
        // Initialize answer to a large number, so that minimum can be found easily
        int answer = Integer.MAX_VALUE;
        
        // For each letter from 'a' to 'z'
        for (int i = 0; i < 26; i++) {
            // Sirf un letters ko consider karo jo target mein present hain (frequency > 0)
            if (freqT[i] > 0) {
                // Calculate how many times target can use letter i from s
                // Agar freqT[i] se divide karke minimum copies milte hain, wahi answer hoga
                answer = Math.min(answer, freqS[i] / freqT[i]);
            }
        }
        
        // Return the maximum copies of target that can be formed
        return answer;
    }
}
