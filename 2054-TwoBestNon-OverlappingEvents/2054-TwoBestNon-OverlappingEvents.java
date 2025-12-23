// Last updated: 12/23/2025, 6:51:51 PM
1class Solution {
2    public int maxTwoEvents(int[][] events) {
3        Arrays.sort(events, (a, b)-> a[0]- b[0]);
4        PriorityQueue<int[]> pq= new PriorityQueue<>((a, b)-> a[0]- b[0]);
5        int maxV= 0;
6        int ans= 0;
7        for(int[] e: events){
8            while(!pq.isEmpty() && pq.peek()[0]< e[0]){
9                maxV= Math.max(maxV, pq.poll()[1]);
10            }
11            ans= Math.max(ans, maxV+ e[2]);
12            pq.add(new int[]{e[1], e[2]});
13        }
14        return ans;
15    }
16}