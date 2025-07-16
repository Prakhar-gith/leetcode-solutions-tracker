// Last updated: 7/16/2025, 6:31:19 PM
class Solution {
    public int maximumLength(int[] nums) {
        /*
         * ALGORITHM:
         *
         * 1. Problem Ko Samajhna (Understanding the Problem):
         * Humko ek integer array `nums` diya gaya hai. Humein isme se sabse lambi "valid" subsequence ki length batani hai.
         * Ek subsequence "valid" tab hoti hai jab uske consecutive elements ke sum ka parity (even ya odd) hamesha same rahe.
         * Matlab, `(sub[0] + sub[1]) % 2` ki value `(sub[1] + sub[2]) % 2`, etc. ke barabar honi chahiye.
         *
         * 2. Parity Ka Khel (The Game of Parity):
         * Do numbers ka sum even hota hai agar dono numbers ya to even hon ya dono odd hon.
         * `even + even = even` => `(sum) % 2 = 0`
         * `odd + odd = even`   => `(sum) % 2 = 0`
         *
         * Do numbers ka sum odd hota hai agar ek even ho aur doosra odd.
         * `even + odd = odd` => `(sum) % 2 = 1`
         * `odd + even = odd` => `(sum) % 2 = 1`
         *
         * 3. Possible Valid Patterns:
         * Isse humein 2 main cases milte hain:
         * Case A: Jab consecutive sum hamesha EVEN ho (`(sum) % 2 == 0`).
         * - Iske liye subsequence ke saare elements ya to EVEN hone chahiye (e.g., [2, 4, 8]).
         * - Ya saare elements ODD hone chahiye (e.g., [1, 3, 7, 5]).
         * Case B: Jab consecutive sum hamesha ODD ho (`(sum) % 2 == 1`).
         * - Iske liye subsequence ke elements ki parity alternate karni chahiye.
         * - Ya to `[even, odd, even, odd, ...]`
         * - Ya `[odd, even, odd, even, ...]`
         *
         * 4. Chaar Candidates (Four Candidates for the Longest Subsequence):
         * Toh humein 4 tarah ki subsequences ki maximum possible length nikalni hai aur unme se jo sabse badi ho, woh hamara answer hoga.
         * 1. Sirf Even numbers ki subsequence: Iski length array mein total even numbers ke count ke barabar hogi.
         * 2. Sirf Odd numbers ki subsequence: Iski length array mein total odd numbers ke count ke barabar hogi.
         * 3. Alternating `Even-Odd-Even...` subsequence: Hum array ko scan karke greedily isko banayenge. Pehle even dhoondenge, fir odd, fir even, and so on.
         * 4. Alternating `Odd-Even-Odd...` subsequence: Same as above, but pehle odd se start karenge.
         *
         * 5. Implementation Steps:
         * a. Array mein total even aur odd numbers count kar lo. Ye humein candidate 1 aur 2 ki length de dega.
         * b. Ek function/loop likho jo alternating sequence ki length nikalega. Isko do baar call karenge:
         * - Ek baar `Even` (parity 0) se start karke.
         * - Ek baar `Odd` (parity 1) se start karke.
         * c. In chaaron lengths (all_even, all_odd, alt_even_start, alt_odd_start) mein se maximum value return kar do.
         *
         *
         * PSEUDOCODE:
         *
         * function maximumLength(nums):
         * // Step 1: Count even and odd numbers
         * even_count = 0
         * odd_count = 0
         * for each num in nums:
         * if num % 2 == 0:
         * even_count++
         * else:
         * odd_count++
         *
         * // Candidate 1: All even numbers
         * max_len = even_count
         *
         * // Candidate 2: All odd numbers
         * max_len = max(max_len, odd_count)
         *
         * // Candidate 3: Alternating starting with even (parity 0)
         * len_alt_even = 0
         * expected_parity = 0
         * for each num in nums:
         * if num % 2 == expected_parity:
         * len_alt_even++
         * expected_parity = 1 - expected_parity // Flip expected parity (0 -> 1, 1 -> 0)
         *
         * max_len = max(max_len, len_alt_even)
         *
         * // Candidate 4: Alternating starting with odd (parity 1)
         * len_alt_odd = 0
         * expected_parity = 1
         * for each num in nums:
         * if num % 2 == expected_parity:
         * len_alt_odd++
         * expected_parity = 1 - expected_parity // Flip expected parity
         *
         * max_len = max(max_len, len_alt_odd)
         *
         * return max_len
         *
         *
         * VISUALIZATION:
         *
         * Let's take nums = [1, 2, 1, 1, 2, 1, 2]
         * Parities:      [O, E, O, O, E, O, E] (O=Odd, E=Even)
         *
         * Candidate 1: All Even
         * - Count of Evens = 2 (numbers are 2, 2)
         * - Length = 2. Subsequence: [2, 2]
         *
         * Candidate 2: All Odd
         * - Count of Odds = 5 (numbers are 1, 1, 1, 1, 1)
         * - Length = 5. Subsequence: [1, 1, 1, 1, 1]
         *
         * Candidate 3: Alternating, start with Even (E, O, E, O...)
         * - Scan nums: [1, 2, 1, 1, 2, 1, 2]
         * - Need E: skip 1, find 2. Sub: [2]. (len=1)
         * - Need O: find 1. Sub: [2, 1]. (len=2)
         * - Need E: skip 1, find 2. Sub: [2, 1, 2]. (len=3)
         * - Need O: find 1. Sub: [2, 1, 2, 1]. (len=4)
         * - Need E: find 2. Sub: [2, 1, 2, 1, 2]. (len=5)
         * - Length = 5.
         *
         * Candidate 4: Alternating, start with Odd (O, E, O, E...)
         * - Scan nums: [1, 2, 1, 1, 2, 1, 2]
         * - Need O: find 1. Sub: [1]. (len=1)
         * - Need E: find 2. Sub: [1, 2]. (len=2)
         * - Need O: find 1. Sub: [1, 2, 1]. (len=3)
         * - Need E: skip 1, find 2. Sub: [1, 2, 1, 2]. (len=4)
         * - Need O: find 1. Sub: [1, 2, 1, 2, 1]. (len=5)
         * - Need E: find 2. Sub: [1, 2, 1, 2, 1, 2]. (len=6)
         * - Length = 6.
         *
         * Final Result: max(2, 5, 5, 6) = 6.
         */

        // First, count even and odd numbers. Pehle even aur odd numbers gin lo.
        int oddCount = 0;
        int evenCount = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        // Candidate 1: Subsequence with only even numbers. Length is the total count of evens.
        // Aisa subsequence jisme sirf even numbers ho. Uski length total even numbers ke barabar hogi.
        // (even + even) % 2 = 0, so this is a valid subsequence.
        int maxLen = evenCount;

        // Candidate 2: Subsequence with only odd numbers. Length is the total count of odds.
        // Aisa subsequence jisme sirf odd numbers ho. Uski length total odd numbers ke barabar hogi.
        // (odd + odd) % 2 = 0, so this is also a valid subsequence.
        // Ab tak ki max length se compare karke update karo.
        maxLen = Math.max(maxLen, oddCount);

        // Candidate 3: Alternating sequence starting with an even number (E, O, E, O...).
        // Ek alternating sequence jo even number se shuru ho.
        // (even + odd) % 2 = 1, so this is a valid subsequence.
        int lenAltEvenStart = 0;
        int expectedParityEven = 0; // 0 for even
        for (int num : nums) {
            // Agar current number ki parity wahi hai jo hum dhoondh rahe hain...
            if (num % 2 == expectedParityEven) {
                lenAltEvenStart++; // ...toh length badhao...
                expectedParityEven = 1 - expectedParityEven; // ...aur agli expected parity ko flip kar do (even se odd).
            }
        }
        maxLen = Math.max(maxLen, lenAltEvenStart);

        // Candidate 4: Alternating sequence starting with an odd number (O, E, O, E...).
        // Ek alternating sequence jo odd number se shuru ho.
        // (odd + even) % 2 = 1, so this is a valid subsequence.
        int lenAltOddStart = 0;
        int expectedParityOdd = 1; // 1 for odd
        for (int num : nums) {
            // Agar current number ki parity wahi hai jo hum dhoondh rahe hain...
            if (num % 2 == expectedParityOdd) {
                lenAltOddStart++; // ...toh length badhao...
                expectedParityOdd = 1 - expectedParityOdd; // ...aur agli expected parity ko flip kar do (odd se even).
            }
        }
        maxLen = Math.max(maxLen, lenAltOddStart);

        // Saare 4 cases me se jo maximum length aayi, wahi final answer hai.
        return maxLen;
    }
}