// Last updated: 5/4/2025, 11:14:39 PM
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        // Algorithm:
        // The problem is to count pairs of equivalent dominoes (dominoes[i] and dominoes[j] where i < j).
        // Two dominoes [a, b] and [c, d] are equivalent if (a == c and b == d) or (a == d and b == c). This means rotation is allowed.
        // We can simplify the equivalence check by representing each domino in a canonical form. A simple canonical form is to always store the smaller number first, followed by the larger number. So, [a, b] and [b, a] both become [min(a, b), max(a, b)].
        // After converting all dominoes to their canonical form, the problem reduces to counting pairs of identical dominoes in the list of canonical forms.
        // We can do this efficiently by using a frequency map (or an array since the values are in a small range) to store the counts of each unique canonical form.
        // For a canonical domino [v1, v2] (where v1 <= v2), we can use an encoding like v1 * 10 + v2 as a key in our frequency counter. Since v1 and v2 are between 1 and 9, the encoded key will be between 1*10+1=11 and 9*10+9=99. An array of size 100 is sufficient.
        // We iterate through the original list of dominoes. For each domino [a, b], we find its canonical form [min(a, b), max(a, b)] and encode it into a key.
        // Before incrementing the frequency count for this key, the current count in the frequency array represents the number of equivalent dominoes encountered *before* the current one. Each of these previously seen dominoes forms a pair with the current domino. We add this count to our total number of equivalent pairs.
        // Then, we increment the frequency count for the current key.
        // After processing all dominoes, the total count accumulated is the answer.

        // Pseudocode:
        // function numEquivDominoPairs(dominoes):
        //   // Canonical forms ka count store karne ke liye frequency array.
        //   // Canonical form [a, b] jahan a <= b hai, use a * 10 + b se encode kiya jata hai.
        //   // Values 1 se 9 hain, toh encoded values 1*10+1=11 se 9*10+9=99 tak hongi.
        //   // 100 size ka array kafi hai (indices 0-99).
        //   // Frequency array to store counts of canonical domino forms (encoded as integers 11-99).
        //   // Canonical form [a, b] where a <= b is encoded as a * 10 + b.
        //   // Values are 1 to 9, so encoded values range from 1*10+1=11 to 9*10+9=99.
        //   // An array of size 100 is sufficient (indices 0-99).
        //   counts = integer array of size 100, initialized to 0
        //
        //   // Equivalent pairs ki total sankhya.
        //   // Total number of equivalent pairs.
        //   total_pairs = 0
        //
        //   // Input list ke har domino se iterate karte hain.
        //   // Iterate through each domino [a, b] in the input list.
        //   for each domino [a, b] in dominoes:
        //     // Canonical form determine karte hain: pehle chhota number, phir bada number.
        //     // Determine the canonical form: smaller number first, larger number second.
        //     v1 = min(a, b)
        //     v2 = max(a, b)
        //
        //     // Canonical form ko single integer key mein encode karte hain.
        //     // Encode the canonical form into a single integer key.
        //     key = v1 * 10 + v2
        //
        //     // 'counts' array mein is key ka current count pehle dekhe gaye dominoes ki sankhya hai jo current domino ke equivalent hain.
        //     // Is count ko total pairs mein add karte hain.
        //     // The current count for this key in the 'counts' array is the number of previously seen
        //     // dominoes that are equivalent to the current one.
        //     // Add this count to the total number of equivalent pairs.
        //     total_pairs += counts[key]
        //
        //     // Is canonical form key ka count increment karte hain.
        //     // Increment the count for this canonical form key.
        //     counts[key]++
        //
        //   // Saare dominoes process karne ke baad, total_pairs mein result hai.
        //   // After processing all dominoes, total_pairs holds the result.
        //   return total_pairs

        // Visualization:
        // Imagine har domino ko aise standardize kar rahe hain ki unka chhota number hamesha pehle aaye.
        // [1, 2] aur [2, 1] dono [1, 2] ban jaayenge. [3, 4] [3, 4] hi rahega. [1, 1] [1, 1] hi rahega.
        // Ab, hamein is standardized list mein identical dominoes ke pairs count karne hain.
        // Jaise [[1, 2], [1, 2], [1, 1], [1, 2], [2, 2]] ban jaayega [[1, 2], [1, 2], [1, 1], [1, 2], [2, 2]].
        // Hamare paas teen [1, 2] hain. Pehla [1, 2] doosre [1, 2] ke saath pair banata hai (1 pair).
        // Pehla [1, 2] teesre [1, 2] ke saath pair banata hai (1 pair).
        // Doosra [1, 2] teesre [1, 2] ke saath pair banata hai (1 pair).
        // Total pairs sirf [1, 2] group se = 1 + 1 + 1 = 3.
        // Agar kisi canonical domino ki frequency k hai, toh us group se pairs ki sankhya k * (k - 1) / 2 hoti hai (Combinations formula: k C 2).
        // Hamara algorithm yeh combination formula implicitly apply karta hai. Jab hum k-th equivalent domino dekhte hain, toh us waqt us canonical form ka count k-1 hota hai. Hum yeh k-1 count total pairs mein add kar dete hain, jo ki pehle ke k-1 equivalent dominoes se pair banane ki sankhya hai. Jab saare dominoes process ho jaate hain, total count saare groups ke pairs ka sum hota hai.

        // Implementation:
        // Frequency array to count occurrences of each canonical domino form.
        // The key is formed by encoding min(a,b) and max(a,b) into a single integer (min*10 + max).
        // Since 1 <= a, b <= 9, the encoded keys range from 1*10+1=11 to 9*10+9=99.
        // An array of size 100 (indices 0 to 99) is sufficient.
        // Har canonical domino form ke counts ke liye frequency array.
        // Key min(a,b) aur max(a,b) ko ek single integer mein encode karke banta hai (min*10 + max).
        // Kyunki 1 <= a, b <= 9, encoded keys 1*10+1=11 se 9*10+9=99 tak honge.
        // 100 size ka array (indices 0 se 99) kafi hai.
        int[] counts = new int[100];

        // Variable to store the total number of equivalent pairs found.
        // Equivalent pairs ki total sankhya store karne ke liye variable.
        int totalPairs = 0;

        // Iterate through each domino in the input list.
        // Input list ke har domino se iterate karte hain.
        for (int[] domino : dominoes) {
            // Get the two numbers on the current domino.
            // Current domino par dono numbers nikal lete hain.
            int a = domino[0];
            int b = domino[1];

            // Determine the canonical form by ensuring the smaller number is first.
            // Canonical form determine karte hain yeh ensure karke ki chhota number pehle ho.
            int v1 = Math.min(a, b);
            int v2 = Math.max(a, b);

            // Encode the canonical form into a single integer key.
            // For [v1, v2] where v1 <= v2, the key is v1 * 10 + v2.
            // Canonical form ko single integer key mein encode karte hain.
            // [v1, v2] ke liye jahan v1 <= v2 hai, key v1 * 10 + v2 hai.
            int key = v1 * 10 + v2;

            // Before incrementing the count for the current key, the value at counts[key]
            // is the number of times this canonical form has been seen before.
            // Each of these previous occurrences forms an equivalent pair with the current domino.
            // So, we add the current count for this key to our total pairs.
            // Current key ke count ko increment karne se pehle, counts[key] par jo value hai
            // woh is canonical form ko pehle kitni baar dekha gaya hai, uski sankhya hai.
            // Har previous occurrence current domino ke saath ek equivalent pair banati hai.
            // Isliye, hum is key ke current count ko total pairs mein add karte hain.
            totalPairs += counts[key];

            // Increment the count for the current canonical form key in the counts array.
            // counts array mein current canonical form key ka count increment karte hain.
            counts[key]++;
        }

        // After iterating through all the dominoes, totalPairs holds the final count
        // of all equivalent domino pairs (i, j) with i < j.
        // Saare dominoes ko iterate karne ke baad, totalPairs mein final count hai
        // saare equivalent domino pairs (i, j) ka jahan i < j hai.
        return totalPairs;
    }
}