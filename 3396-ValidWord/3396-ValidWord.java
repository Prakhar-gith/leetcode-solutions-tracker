// Last updated: 8/30/2025, 6:56:52 PM
class Solution {
    public boolean isValid(String word) {
        // Algorithm (Tarika/Vidhi):
        // 1. Sabse pehle, check karo ki word ki length 3 se kam hai ya nahi. Agar hai, to word invalid hai, so return false.
        //    (First, check if the length of the word is less than 3. If it is, the word is invalid, so return false.)
        // 2. Do boolean flags, `hasVowel` aur `hasConsonant`, ko `false` se initialize karo. Yeh flags track karenge ki hume kam se kam ek vowel aur ek consonant mila hai ya nahi.
        //    (Initialize two boolean flags, `hasVowel` and `hasConsonant`, to `false`. These will track if we've found at least one vowel and one consonant.)
        // 3. Word ke har character par iterate karo (loop chalao).
        //    (Iterate through each character of the word.)
        // 4. Har character ke liye, pehle check karo ki woh ek valid character hai ya nahi (matlab, English letter ya digit). Agar nahi hai (jaise '@', '#', '$'), to word invalid hai, so turant `false` return kardo.
        //    (For each character, first check if it's a valid character (i.e., an English letter or a digit). If not (like '@', '#', '$'), the word is invalid, so return `false` immediately.)
        // 5. Agar character ek letter hai, to check karo ki woh vowel hai ya consonant.
        //    (If the character is a letter, check if it's a vowel or a consonant.)
        //    - Agar vowel hai, to `hasVowel` flag ko `true` set kardo.
        //      (If it is a vowel, set the `hasVowel` flag to `true`.)
        //    - Agar letter hai par vowel nahi, to woh consonant hoga. `hasConsonant` flag ko `true` set kardo.
        //      (If it is a letter but not a vowel, it must be a consonant. Set the `hasConsonant` flag to `true`.)
        // 6. Jab loop sabhi characters ko check kar chuka ho, to word tabhi valid hoga jab saare characters alphanumeric the AUR hume kam se kam ek vowel AUR ek consonant mila ho.
        //    (After the loop has finished checking all characters, the word is valid only if all characters were alphanumeric AND we have found at least one vowel AND at least one consonant.)
        // 7. `hasVowel && hasConsonant` ka result return kardo.
        //    (Return the result of `hasVowel AND hasConsonant`.)

        // Pseudo Code:
        // FUNCTION isValid(word):
        //   // Shart 1: Lambai check (Condition 1: Length check)
        //   IF length of word < 3 THEN
        //     RETURN false
        //   END IF
        //
        //   // Flags ko shuru karo (Initialize flags)
        //   hasVowel = false
        //   hasConsonant = false
        //
        //   // Har character ke liye loop (Loop for each character)
        //   FOR EACH character c in word:
        //     // Shart 2: Sirf letters ya digits hone chahiye (Condition 2: Only letters or digits allowed)
        //     IF c is NOT a letter AND c is NOT a digit THEN
        //       RETURN false  // Agar koi special symbol mila to invalid (If any special symbol is found, it's invalid)
        //     END IF
        //
        //     // Agar character ek letter hai to check karo (If character is a letter, then check)
        //     IF c is a letter THEN
        //       // Shart 3: Vowel check
        //       IF c is a vowel (case-insensitive) THEN
        //         hasVowel = true
        //       ELSE
        //         // Shart 4: Consonant check
        //         hasConsonant = true
        //       END IF
        //     END IF
        //   END FOR
        //
        //   // Aakhri check: Vowel aur consonant dono hone chahiye
        //   // (Final check: Both vowel and consonant must be present)
        //   RETURN hasVowel AND hasConsonant
        // END FUNCTION
        
        // --- Visualization (Udhaaran/Example) ---
        // Chalo word = "Usa@1" lete hain
        // (Let's take word = "Usa@1")
        // 1. Length Check: length = 5 jo ki >= 3 hai. Theek hai. (length = 5 which is >= 3. OK.)
        // 2. Initialize: hasVowel = false, hasConsonant = false.
        // 3. Loop chalu: (Loop starts)
        //    - char = 'U': Ye letter hai. Vowel hai? Haan. `hasVowel` = true.
        //      (It's a letter. Is it a vowel? Yes. `hasVowel` = true.)
        //    - char = 's': Ye letter hai. Vowel hai? Nahi. Ye consonant hai. `hasConsonant` = true.
        //      (It's a letter. Is it a vowel? No. It's a consonant. `hasConsonant` = true.)
        //    - char = 'a': Ye letter hai. Vowel hai? Haan. `hasVowel` pehle se hi true hai.
        //      (It's a letter. Is it a vowel? Yes. `hasVowel` is already true.)
        //    - char = '@': Ye na toh letter hai na hi digit. Invalid character!
        //      (This is neither a letter nor a digit. Invalid character!)
        // 4. Result: Turant `false` return karo kyunki '@' mil gaya. (Immediately return `false` because '@' was found.)
        // 
        // Ab word = "12ab" lete hain
        // (Now let's take word = "12ab")
        // 1. Length Check: length = 4 jo ki >= 3 hai. OK.
        // 2. Initialize: hasVowel = false, hasConsonant = false.
        // 3. Loop chalu:
        //    - char = '1': Ye digit hai. Theek hai. (It's a digit. OK.)
        //    - char = '2': Ye digit hai. Theek hai. (It's a digit. OK.)
        //    - char = 'a': Ye letter hai. Vowel hai? Haan. `hasVowel` = true.
        //    - char = 'b': Ye letter hai. Vowel hai? Nahi. Ye consonant hai. `hasConsonant` = true.
        // 4. Loop khatam. (Loop ends.)
        // 5. Final Check: `hasVowel` (true) && `hasConsonant` (true) -> true.
        // 6. Result: `true` return karo. (Return `true`.)


        // Condition 1: Word me kam se kam 3 characters hone chahiye.
        // (The word must contain a minimum of 3 characters.)
        if (word.length() < 3) {
            return false; // Agar length 3 se kam hai, to seedha false return karo. (If length is less than 3, directly return false.)
        }

        // Vowels aur consonants milne par track karne ke liye flags.
        // (Flags to track if vowels and consonants are found.)
        boolean hasVowel = false;
        boolean hasConsonant = false;
        
        // Vowels ko check karne ke liye ek reference string. Isse check karna aasan ho jaata hai.
        // (A reference string to check for vowels. This makes checking easier.)
        String vowels = "aeiouAEIOU";

        // Word ke har character ko check karne ke liye loop.
        // (Loop to check each character of the word.)
        for (char c : word.toCharArray()) {
            // Condition 2: Sirf digits (0-9) aur English letters (a-z, A-Z) allowed hain.
            // (Only digits (0-9) and English letters (a-z, A-Z) are allowed.)
            if (!Character.isLetterOrDigit(c)) {
                // Agar koi special character ('@', '#', '$') milta hai, to word invalid hai.
                // (If any special character ('@', '#', '$') is found, the word is invalid.)
                return false; 
            }

            // Agar character ek letter hai, to check karo ki wo vowel hai ya consonant.
            // (If the character is a letter, check if it is a vowel or a consonant.)
            if (Character.isLetter(c)) {
                // `vowels.indexOf(c) != -1` ka matlab hai ki character 'c' vowels string me maujood hai.
                // (`vowels.indexOf(c) != -1` means the character 'c' is present in the vowels string.)
                if (vowels.indexOf(c) != -1) {
                    // Condition 3: Kam se kam ek vowel hona chahiye. Flag ko true set kardo.
                    // (Condition 3: There must be at least one vowel. Set the flag to true.)
                    hasVowel = true;
                } else {
                    // Agar letter hai par vowel nahi, to wo consonant hai.
                    // (If it's a letter but not a vowel, then it's a consonant.)
                    // Condition 4: Kam se kam ek consonant hona chahiye. Flag ko true set kardo.
                    // (Condition 4: There must be at least one consonant. Set the flag to true.)
                    hasConsonant = true;
                }
            }
        }

        // Loop ke baad, check karo ki dono flags true hain ya nahi.
        // (After the loop, check if both flags are true.)
        // Agar word me vowel aur consonant dono hain, tabhi word valid hoga.
        // (The word will be valid only if it contains both a vowel and a consonant.)
        return hasVowel && hasConsonant;
    }
}