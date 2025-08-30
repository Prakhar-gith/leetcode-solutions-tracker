// Last updated: 8/30/2025, 6:56:15 PM
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        // Algorithm:
        // Is problem ko solve karne ke liye, hum sliding window technique ka use karenge.
        // Humara goal hai sabse lamba continuous free time period dhundhna.
        // Jab hum 'k' meetings ko reschedule karte hain, toh iska matlab hai ki hum un 'k' meetings
        // ko unke original slots se hata kar, unke beech ke gaps ko merge kar sakte hain.
        //
        // Iska key insight yeh hai ki agar hum 'k' meetings ko reschedule karte hain, toh hum effectively
        // 'k+1' consecutive free gaps ko merge kar sakte hain. Kyunki in 'k+1' gaps ko merge karne ke liye
        // unke beech mein exactly 'k' meetings honi chahiye, jinhe hum reschedule kar sakte hain.
        // Jab hum in meetings ko reschedule karte hain, toh unki duration ko hum free time mein convert kar dete hain
        // (ya unhe event ke kisi aur hisse mein shift kar dete hain jahan se woh hamare current free period ko disturb na karein).
        //
        // Steps:
        // 1. Sabse pehle, saare possible free time gaps ki durations calculate karo.
        //    Yeh gaps teen prakar ke honge:
        //    a. Event ki shuruat (t=0) se pehli meeting ke start tak ka free time: [0, startTime[0]]
        //    b. Do consecutive meetings ke beech ka free time: [endTime[i], startTime[i+1]]
        //    c. Aakhri meeting ke baad se event ke end (eventTime) tak ka free time: [endTime[n-1], eventTime]
        // 2. In sabhi gap durations ko ek list mein store kar lo. Is list mein total 'n+1' gaps honge.
        // 3. Ab, is 'gaps' list par ek sliding window apply karo jiska size 'k+1' hoga.
        //    Yeh window 'k+1' consecutive gaps ke sum ko calculate karegi.
        // 4. Window ko slide karte hue, har window ke sum ko calculate karo.
        // 5. Sabse bada sum hi humara 'maximum continuous free time' hoga.

        // Pseudo Code:
        // 1. Initialize an empty list `gapDurations` (type Long to handle large sums).
        // 2. Add `startTime[0] - 0` to `gapDurations`. (This is the free time from 0 to the first meeting's start)
        // 3. Loop from `i = 0` to `n - 2`:
        //    Add `startTime[i+1] - endTime[i]` to `gapDurations`. (These are the free times between consecutive meetings)
        // 4. Add `eventTime - endTime[n-1]` to `gapDurations`. (This is the free time from the last meeting's end to eventTime)
        // 5. Initialize `maxFreeTime = 0L`. (Use Long for sums to prevent overflow)
        // 6. Initialize `currentWindowSum = 0L`.
        // 7. Define `windowSize = k + 1`.
        // 8. Loop from `j = 0` to `windowSize - 1`: (Build the initial window of size k+1)
        //    `currentWindowSum += gapDurations.get(j)`
        // 9. Set `maxFreeTime = currentWindowSum`. (The first window's sum is our initial max candidate)
        // 10. Loop from `j = windowSize` to `gapDurations.size() - 1`: (Slide the window across the remaining gaps)
        //    `currentWindowSum += gapDurations.get(j)` (Add the new gap on the right)
        //    `currentWindowSum -= gapDurations.get(j - windowSize)` (Remove the oldest gap from the left)
        //    `maxFreeTime = max(maxFreeTime, currentWindowSum)` (Update maxFreeTime if current window sum is greater)
        // 11. Return `(int) maxFreeTime`. (Cast back to int as per problem's return type)

        // Visualization (Text-based):
        // Chalo Example 2 ko dekhte hain:
        // eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
        // Meetings hain: M0: [0,1], M1: [2,4], M2: [9,10]
        // Total meetings (n) = 3.
        // Hum k = 1 meeting ko reschedule kar sakte hain.
        // Iska matlab hai ki hum window size = k + 1 = 1 + 1 = 2 gaps ko merge kar sakte hain.
        //
        // Step 1: Sabhi free gap durations calculate karte hain:
        // Gap F0 (event start se M0 tak): startTime[0] - 0 = 0 - 0 = 0
        // Gap F1 (M0 ke end se M1 ke start tak): startTime[1] - endTime[0] = 2 - 1 = 1
        // Gap F2 (M1 ke end se M2 ke start tak): startTime[2] - endTime[1] = 9 - 4 = 5
        // Gap F3 (M2 ke end se event end tak): eventTime - endTime[2] = 10 - 10 = 0
        //
        // Toh, `gapDurations` list banegi: [0, 1, 5, 0]
        //
        // Step 2: Sliding Window of size 2 (k+1) apply karte hain:
        //
        // Initial Window (j = 0 to 1):
        // currentWindowSum = gapDurations[0] + gapDurations[1] = 0 + 1 = 1
        // maxFreeTime = 1
        // (Yeh window [0,1] free time ko represent karti hai, jisme M0 ko reschedule karke [0,2] free banaya ja sakta hai)
        //
        // Window Slide (j = 2):
        // Naya element add hoga: gapDurations[2] (value 5)
        // Purana element remove hoga: gapDurations[2 - (1+1)] = gapDurations[0] (value 0)
        // currentWindowSum = 1 (previous sum) + 5 (new element) - 0 (removed element) = 6
        // maxFreeTime = max(1, 6) = 6
        // (Yeh window [1,5] free time ko represent karti hai, jisme M1 ko reschedule karke [2,9] free banaya ja sakta hai)
        //
        // Window Slide (j = 3):
        // Naya element add hoga: gapDurations[3] (value 0)
        // Purana element remove hoga: gapDurations[3 - (1+1)] = gapDurations[1] (value 1)
        // currentWindowSum = 6 (previous sum) + 0 (new element) - 1 (removed element) = 5
        // maxFreeTime = max(6, 5) = 6
        // (Yeh window [5,0] free time ko represent karti hai, jisme M2 ko reschedule karke [9,10] free banaya ja sakta hai)
        //
        // Loop khatam. Final `maxFreeTime` = 6.
        // Yeh Example 2 ke output se match karta hai.

        int n = startTime.length; // Meetings ki total sankhya.

        // `gapDurations` list banate hain saare free time segments ki lambai store karne ke liye.
        // Hum Long type ka use kar rahe hain taaki sum overflow na ho, kyunki eventTime aur gaps bade ho sakte hain.
        List<Long> gapDurations = new ArrayList<>();

        // Pehla gap: Event ki shuruat (t=0) se pehli meeting ke start tak.
        // Agar pehli meeting 0 par shuru hoti hai, toh yeh gap 0 hoga.
        gapDurations.add((long) startTime[0]); // Type casting to long to ensure correct arithmetic.

        // Beech ke gaps: Har meeting ke end se agali meeting ke start tak.
        // Yeh loop n-1 meetings ke beech ke n-1 gaps ko cover karega.
        for (int i = 0; i < n - 1; i++) {
            gapDurations.add((long) startTime[i + 1] - endTime[i]);
        }

        // Aakhri gap: Last meeting ke end se event ke end (eventTime) tak.
        // Agar aakhri meeting eventTime par khatam hoti hai, toh yeh gap 0 hoga.
        gapDurations.add((long) eventTime - endTime[n - 1]);

        // `maxFreeTime` ko initialize karte hain 0 se.
        // Kyunki free time kabhi negative nahi ho sakta, aur agar koi free time nahi hai toh 0 hoga.
        long maxFreeTime = 0L;

        // `currentWindowSum` current sliding window mein gaps ka sum store karega.
        long currentWindowSum = 0L;

        // Window size 'k+1' hai, kyunki 'k' meetings ko reschedule karke hum 'k+1' gaps ko merge kar sakte hain.
        int windowSize = k + 1;

        // Pehle 'windowSize' (k+1) gaps ko jod kar initial window banate hain.
        // Yeh loop `j=0` se `j=windowSize-1` tak chalega.
        for (int j = 0; j < windowSize; j++) {
            currentWindowSum += gapDurations.get(j);
        }

        // Initial window ka sum hi pehla candidate hai `maxFreeTime` ke liye.
        maxFreeTime = currentWindowSum;

        // Ab window ko slide karte hain.
        // Loop `j=windowSize` se `gapDurations.size()-1` tak chalega.
        // Har iteration mein, naya element (right side se) add hoga aur sabse purana element (left side se) remove hoga.
        for (int j = windowSize; j < gapDurations.size(); j++) {
            // Naya gap window mein add karte hain.
            currentWindowSum += gapDurations.get(j);
            // Window se sabse purana gap remove karte hain.
            // `j - windowSize` se hum window ke left-most element ka index nikalte hain.
            currentWindowSum -= gapDurations.get(j - windowSize);

            // `currentWindowSum` ko `maxFreeTime` se compare karte hain aur update karte hain.
            maxFreeTime = Math.max(maxFreeTime, currentWindowSum);
        }

        // Final maximum continuous free time return karte hain.
        // Problem signature ke according return type `int` hai, toh `long` value ko `int` mein cast karte hain.
        // Note: Since the maximum possible free time cannot exceed `eventTime` (which fits in int),
        // and `eventTime` is up to 10^9, casting back to `int` is safe here.
        return (int) maxFreeTime;
    }
}
