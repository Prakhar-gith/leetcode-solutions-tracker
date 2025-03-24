// Last updated: 3/24/2025, 9:05:42 PM
// Algorithm:
// 1. Sort the meetings based on the starting day. This helps in merging overlapping intervals.
// 2. Merge the overlapping intervals:
//    - Initialize a merged list and iterate through the sorted meetings.
//    - For each meeting, if it overlaps with the last interval in the merged list, update the end day.
//      Otherwise, add the meeting as a new interval.
// 3. Calculate the total number of days occupied by meetings by summing up the lengths of the merged intervals.
//    (Each interval contributes (end - start + 1) days.)
// 4. The result is the total days available minus the days occupied by meetings.
//    (Make sure the result is non-negative, although by constraints it should be.)
//
// Pseudo Code:
// -------------
// function countDaysWithoutMeetings(days, meetings):
//     sort meetings by start day
//     merged = empty list
//     for meeting in meetings:
//         if merged is empty OR meeting.start > last_merged.end:
//             add meeting to merged
//         else:
//             last_merged.end = max(last_merged.end, meeting.end)
//     meetingDays = 0
//     for interval in merged:
//         meetingDays += (interval.end - interval.start + 1)
//     return days - meetingDays
//
// Visualization Example:
// ----------------------
// days = 10, meetings = [[5,7],[1,3],[9,10]]
// Sorted meetings = [[1,3],[5,7],[9,10]]
// Merged intervals = [[1,3], [5,7], [9,10]]
// Total meeting days = (3-1+1) + (7-5+1) + (10-9+1) = 3 + 3 + 2 = 8
// Days without meetings = 10 - 8 = 2

class Solution {
    public int countDays(int days, int[][] meetings) {
        // Sort the meetings by starting day
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        
        // Merge overlapping intervals
        List<int[]> merged = new ArrayList<>();
        for (int[] meeting : meetings) {
            // Agar merged list empty hai ya current meeting start is greater than last merged meeting's end,
            // toh koi overlap nahi hai, so add new interval.
            if (merged.isEmpty() || meeting[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(new int[]{meeting[0], meeting[1]});
            } else {
                // Overlap exists: update the end day of the last interval to the maximum end day.
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], meeting[1]);
            }
        }
        
        // Calculate the total days occupied by meetings
        long meetingDays = 0;
        for (int[] interval : merged) {
            meetingDays += (interval[1] - interval[0] + 1);
        }
        
        // Available days without meetings = total days - meeting days
        // Ensure we return an int value.
        return (int)(days - meetingDays);
    }
}
