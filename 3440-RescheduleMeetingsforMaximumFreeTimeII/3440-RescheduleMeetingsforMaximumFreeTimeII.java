// Last updated: 7/11/2025, 12:37:50 AM
class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        // ### HINGLISH EXPLANATION ###
        // Humein maximum continuous free time nikalna hai, at most ek meeting ko reschedule karke.
        // Problem ka main idea yeh hai ki maximum free time ya toh original gaps mein se ek hoga,
        // ya phir ek naya bada gap banega jab hum ek meeting ko uski jagah se hatate hain.
        //
        // Jab hum koi meeting M_i ko hatate hain, toh M_i ki jagah, uske pehle ka free gap, aur uske baad ka free gap
        // milkar ek bada sa free slot banate hain. Let's call this merged_gap.
        //
        // Ab humein M_i (duration d_i) ko kahin aur place karna hai. Hum isko bache hue free time mein kahin bhi
        // rakh sakte hain. To get the best result (sabse bada final free time), hum M_i ko place karte waqt
        // koshish karenge ki jo sabse bada free slot hai, woh intact rahe.
        //
        // So, har meeting M_i ke liye, hum yeh scenario consider karte hain:
        // 1. M_i ko hatane se naye gaps ka set banta hai. Is set mein sabse bada gap (h0) aur dusra sabse bada (h1) nikalte hain.
        // 2. Agar M_i ka duration (d_i) h1 se chota ya barabar hai, toh hum M_i ko h1 (ya usse chote slot) mein daal denge. Isse h0 intact rahega, aur answer h0 hoga.
        // 3. Agar d_i > h1, toh majboori mein M_i ko h0 mein daalna padega. Tab answer h0 - d_i hoga.
        //
        // Is approach ko fast banane ke liye (O(n) mein), hum prefix aur suffix arrays ka use karte hain
        // taaki har step mein 'largest' aur 'second largest' gap O(1) time mein mil jaye.

        /*
         * ### ALGORITHM ###
         * 1.  **Initialization**:
         * - Get the number of meetings, n.
         *
         * 2.  **Calculate Gaps and Durations**:
         * - Create a `gaps` array of size `n + 1` to store the duration of each free time slot.
         * - Create a `durations` array of size `n` for meeting durations.
         *
         * 3.  **Calculate Initial Answer**:
         * - Find the maximum value in the `gaps` array. This is the baseline answer if no meetings are moved.
         *
         * 4.  **Pre-computation for O(1) Lookups**:
         * - Create four arrays: `prefixMax1`, `prefixMax2`, `suffixMax1`, `suffixMax2` to store the top two gap values from the start and end of the `gaps` array, respectively.
         *
         * 5.  **Iterate and Evaluate Scenarios**:
         * - Loop through each meeting `M_i` that we consider moving.
         * a. **Identify Gaps**: Determine the set of `n` free slots available after `M_i` is conceptually removed. This set consists of one large `mergedGap` and all other original gaps.
         * b. **Find Top Two Gaps (h0, h1)**: Using the pre-computed arrays, find the largest (`h0`) and second-largest (`h1`) gaps in this new set.
         * c. **Calculate Potential New Max Free Time (Corrected Logic)**:
         * - Get the duration of the meeting to be moved: `d_i`.
         * - If `d_i <= h1`, we can place the meeting in a non-largest slot. The best result is `h0`.
         * - Else if `d_i <= h0`, we must place the meeting in the largest slot. The best result is `h0 - d_i`.
         * - Otherwise, the move is not possible.
         * d. **Update Global Maximum**: Update `maxFreeTime` with the best possible result from the current scenario.
         *
         * 6.  **Return Result**: Return `maxFreeTime`.
         */

        /*
         * ### PSEUDO CODE ###
         * function maxFreeTime(eventTime, startTime, endTime):
         * n = startTime.length
         *
         * // Steps 2 & 3: Calculate Gaps, Durations, and Initial Answer
         * gaps = calculate_gaps(...)
         * durations = calculate_durations(...)
         * maxFreeTime = max(gaps)
         *
         * // Step 4: Pre-computation
         * prefixMax1, prefixMax2 = computePrefixTopTwo(gaps)
         * suffixMax1, suffixMax2 = computeSuffixTopTwo(gaps)
         *
         * // Step 5: Iterate through each meeting
         * for i from 0 to n-1:
         * mergedGap = calculate_merged_gap_for_moving(i)
         * o0, o1 = find_top_two_other_gaps_using_prefix_suffix_arrays(i)
         * h0, h1 = find_top_two_from({mergedGap, o0, o1})
         * * d_i = durations[i]
         * potentialMax = -1
         * * // ** THE CORE LOGIC FIX IS HERE **
         * if d_i <= h1:
         * potentialMax = h0
         * else if d_i <= h0:
         * potentialMax = h0 - d_i
         * * if potentialMax != -1:
         * maxFreeTime = max(maxFreeTime, potentialMax)
         *
         * return maxFreeTime
         */
        
        /*
         * ### VISUALIZATION ###
         *
         * Consider available slots {h0, h1, h2, ...} where h0 > h1 > h2 ...
         * We need to place meeting `d_i`.
         *
         * WRONG WAY (Old Logic): Always place in h0.
         * Timeline: | <--- h0 ---> | | <--- h1 ---> |
         * Place d_i:  |<- h0-d_i ->|d| | <--- h1 ---> |
         * Result: max(h0-d_i, h1)
         *
         * RIGHT WAY (New Logic): Preserve h0 if possible.
         * If d_i fits in h1:
         * Timeline: | <--- h0 ---> | | <--- h1 ---> |
         * Place d_i:  | <--- h0 ---> | |<-h1-d_i->|d|
         * Result: h0
         */

        int n = startTime.length;
        if (n < 2) {
            return eventTime;
        }

        long[] gaps = new long[n + 1];
        gaps[0] = startTime[0];
        for (int i = 1; i < n; i++) {
            gaps[i] = (long)startTime[i] - endTime[i-1];
        }
        gaps[n] = (long)eventTime - endTime[n-1];

        long[] durations = new long[n];
        for (int i = 0; i < n; i++) {
            durations[i] = (long)endTime[i] - startTime[i];
        }

        long maxFreeTime = 0;
        for (long gap : gaps) {
            maxFreeTime = Math.max(maxFreeTime, gap);
        }

        long[] prefixMax1 = new long[n + 1];
        long[] prefixMax2 = new long[n + 1];
        long[] suffixMax1 = new long[n + 1];
        long[] suffixMax2 = new long[n + 1];

        prefixMax1[0] = gaps[0];
        prefixMax2[0] = -1;
        for (int i = 1; i <= n; i++) {
            if (gaps[i] >= prefixMax1[i-1]) {
                prefixMax1[i] = gaps[i];
                prefixMax2[i] = prefixMax1[i-1];
            } else if (gaps[i] > prefixMax2[i-1]) {
                prefixMax1[i] = prefixMax1[i-1];
                prefixMax2[i] = gaps[i];
            } else {
                prefixMax1[i] = prefixMax1[i-1];
                prefixMax2[i] = prefixMax2[i-1];
            }
        }

        suffixMax1[n] = gaps[n];
        suffixMax2[n] = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (gaps[i] >= suffixMax1[i+1]) {
                suffixMax1[i] = gaps[i];
                suffixMax2[i] = suffixMax1[i+1];
            } else if (gaps[i] > suffixMax2[i+1]) {
                suffixMax1[i] = suffixMax1[i+1];
                suffixMax2[i] = gaps[i];
            } else {
                suffixMax1[i] = suffixMax1[i+1];
                suffixMax2[i] = suffixMax2[i+1];
            }
        }
        
        for (int i = 0; i < n; i++) {
            long mergedGap;
            if (i == 0) {
                mergedGap = startTime[1];
            } else if (i == n - 1) {
                mergedGap = (long)eventTime - endTime[n-2];
            } else {
                mergedGap = (long)startTime[i+1] - endTime[i-1];
            }

            long o0 = -1, o1 = -1;
            if (i == 0) {
                if (n + 1 > 2) {
                    o0 = suffixMax1[2];
                    o1 = suffixMax2[2];
                }
            } else if (i == n - 1) {
                 if (n - 2 >= 0) {
                    o0 = prefixMax1[n-2];
                    o1 = prefixMax2[n-2];
                }
            } else {
                long p1 = prefixMax1[i-1];
                long p2 = prefixMax2[i-1];
                long s1 = suffixMax1[i+2];
                long s2 = suffixMax2[i+2];
                
                Long[] candidates = {p1, p2, s1, s2};
                Arrays.sort(candidates, Collections.reverseOrder());
                o0 = candidates[0];
                o1 = candidates[1];
            }
            
            Long[] finalCandidates = {mergedGap, o0, o1};
            Arrays.sort(finalCandidates, Collections.reverseOrder());
            long h0 = finalCandidates[0];
            long h1 = finalCandidates[1];

            // This is the corrected logic block
            long durationToPlace = durations[i];
            long potentialMax = -1;

            if (durationToPlace <= h1) {
                // Best case: place meeting in the 2nd largest (or smaller) slot.
                // This leaves the largest slot 'h0' untouched.
                potentialMax = h0;
            } else if (durationToPlace <= h0) {
                // Otherwise: we must place it in the largest slot, reducing its size.
                potentialMax = h0 - durationToPlace;
            }
            
            if (potentialMax != -1) {
                 maxFreeTime = Math.max(maxFreeTime, potentialMax);
            }
        }

        return (int)maxFreeTime;
    }
}