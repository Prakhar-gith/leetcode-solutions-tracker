// Last updated: 7/7/2025, 11:57:23 PM
// Algorithm:
// Ye problem ek greedy approach se solve hogi. Hame maximum events attend karne hain.
// Iske liye hum events ko start day ke hisaab se sort karenge.
// Fir hum ek min-priority queue (min-heap) use karenge jo current available events ki end dates store karegi.

// Steps:
// 1. Events ko unke startDay ke according sort kar do. Agar startDay same ho toh endDay ke according bhi sort kar sakte hain, par yahan sirf startDay se kaam chal jayega.
// 2. Ek min-heap banao jo event ke endDay store karegi.
// 3. Ek variable `attendedEvents` initialize karo 0 se, ye count karega ki kitne events attend hue.
// 4. Ek variable `day` initialize karo 1 se (kyunki days 1 se start ho rahe hain).
// 5. Ek `eventIndex` initialize karo 0 se, ye sorted `events` array ko track karega.

// 6. Loop chalao jab tak `day` maximum possible end day se kam ya barabar ho, ya jab tak saare events process na ho jayein.
//    a. Har `day` par, saare events ko `min-heap` mein add karo jinka `startDay` current `day` ke barabar hai.
//    b. `min-heap` se un events ko remove karo jinka `endDay` current `day` se kam hai (jo pehle hi khatam ho chuke hain).
//    c. Agar `min-heap` empty nahi hai, toh iska matlab hai ki current `day` par koi event attend kiya ja sakta hai.
//       Toh `min-heap` se sabse pehle khatam hone wale event ko remove karo (yani `min-heap` ka peek element).
//       `attendedEvents` ko increment kar do.
//    d. `day` ko increment karo.

// 7. Finally, `attendedEvents` return kar do.

// Pseudo Code:
// function maxEvents(events):
//     sort events by startDay
//     minHeap = new MinPriorityQueue() // stores endDay
//     attendedEvents = 0
//     day = 1
//     eventIndex = 0
//     n = events.length

//     while day <= 10^5 (max possible end day) OR eventIndex < n OR minHeap is not empty:
//         // Add events that start today to minHeap
//         while eventIndex < n AND events[eventIndex][0] == day:
//             minHeap.add(events[eventIndex][1]) // Add endDay to heap
//             eventIndex = eventIndex + 1

//         // Remove events that have already ended
//         while minHeap is not empty AND minHeap.peek() < day:
//             minHeap.remove()

//         // If there's an event available, attend it
//         if minHeap is not empty:
//             minHeap.remove() // Attend the event that ends earliest
//             attendedEvents = attendedEvents + 1
        
//         // If no more events to process and heap is empty, we can stop
//         if eventIndex >= n AND minHeap is empty:
//             break // All events processed and no current events to attend

//         day = day + 1
    
//     return attendedEvents

// Visualization:
// Let's take events = [[1,2],[2,3],[3,4],[1,2]]
// Sorted events: [[1,2],[1,2],[2,3],[3,4]]

// Day 1:
// - eventIndex = 0. Add [1,2] (endDay 2) to heap. heap = [2]
// - eventIndex = 1. Add [1,2] (endDay 2) to heap. heap = [2,2]
// - Remove from heap: No event ended before day 1.
// - Attend event: Heap is not empty. Remove 2. attendedEvents = 1. heap = [2]

// Day 2:
// - eventIndex = 2. Add [2,3] (endDay 3) to heap. heap = [2,3]
// - Remove from heap: No event ended before day 2.
// - Attend event: Heap is not empty. Remove 2. attendedEvents = 2. heap = [3]

// Day 3:
// - eventIndex = 3. Add [3,4] (endDay 4) to heap. heap = [3,4]
// - Remove from heap: No event ended before day 3.
// - Attend event: Heap is not empty. Remove 3. attendedEvents = 3. heap = [4]

// Day 4:
// - eventIndex = 4. No more events to add.
// - Remove from heap: No event ended before day 4.
// - Attend event: Heap is not empty. Remove 4. attendedEvents = 4. heap = []

// Loop ends. Return attendedEvents = 4.

import java.util.Arrays; // Arrays utility class for sorting
import java.util.PriorityQueue; // PriorityQueue for min-heap

class Solution {
    public int maxEvents(int[][] events) {
        // Sabse pehle events ko startDay ke according sort kar denge.
        // Ye isliye zaroori hai taki hum daily basis par events ko sahi order mein process kar sakein.
        // Agar startDay same ho toh endDay se sort karna optional hai, par yahan sirf startDay se kaam chal jayega
        // kyunki hum greedy approach use kar rahe hain.
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // Ek min-priority queue banayenge. Ye un events ke end dates ko store karegi
        // jo current day par available hain aur jinhe hum attend kar sakte hain.
        // Min-heap isliye kyunki hum hamesha us event ko attend karna chahenge
        // jo sabse pehle khatam ho raha hai, taki baki events ke liye zyada din mil sakein.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int attendedEvents = 0; // Ye variable total attend kiye gaye events ko count karega
        int eventIndex = 0; // Ye sorted events array mein current event ko track karega
        int n = events.length; // Total events ki sankhya

        // Loop `day` ko 1 se start karega aur aage badhayega.
        // Loop tab tak chalega jab tak ya toh saare events process na ho jayein (`eventIndex < n`)
        // ya min-heap mein koi event pending na ho (matlab, jo events humne abhi tak dekhe hain
        // unme se koi abhi bhi attend ho sakta hai)
        // Ya fir `day` max possible end day tak na pahunch jaaye (10^5)
        for (int day = 1; day <= 100000; day++) { // Max end day 10^5 hai constraints se
            // Current day par start hone wale sabhi events ko min-heap mein add karo.
            // Hum `eventIndex` ko aage badhaate jayenge jab tak current event ka startDay
            // current `day` ke barabar hai.
            while (eventIndex < n && events[eventIndex][0] == day) {
                minHeap.add(events[eventIndex][1]); // Event ka endDay add kar rahe hain
                eventIndex++;
            }

            // Un events ko min-heap se remove karo jo current day tak khatam ho chuke hain.
            // Agar kisi event ka `endDay` current `day` se kam hai, toh wo event ab attend nahi ho sakta.
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll(); // minHeap.poll() removes the smallest element (earliest ending event)
            }

            // Agar min-heap empty nahi hai, toh iska matlab hai ki current day par
            // koi na koi event attend karne ke liye available hai.
            // Hum sabse pehle khatam hone wale event ko attend karenge (greedy choice).
            if (!minHeap.isEmpty()) {
                minHeap.poll(); // Sabse pehle khatam hone wale event ko attend kar liya, use heap se hata do
                attendedEvents++; // Attended events ka count badha do
            }

            // Optimization: Agar saare events process ho chuke hain aur heap bhi empty hai,
            // toh aage days simulate karne ka koi matlab nahi hai.
            // Isse time complexity improve hogi bade test cases ke liye.
            if (eventIndex >= n && minHeap.isEmpty()) {
                break;
            }
        }

        // Final result: total attended events.
        return attendedEvents;
    }
}