// Last updated: 12/27/2025, 5:26:32 PM
1class Solution {
2    public int mostBooked(int n, int[][] meetings) {
3        Arrays.sort(meetings, (a, b)-> a[0]- b[0]);
4        int[] cnt= new int[n];
5        PriorityQueue<Integer> free= new PriorityQueue<>();
6        for(int i=0; i< n; i++) free.add(i);
7        PriorityQueue<long[]> busy= new PriorityQueue<>((a, b)-> a[0]!= b[0]? Long.compare(a[0], b[0]): Long.compare(a[1], b[1]));
8        
9        for(int[] m: meetings){
10            long start= m[0], end= m[1];
11            while(!busy.isEmpty() && busy.peek()[0]<= start) free.add((int)busy.poll()[1]);
12            
13            if(free.isEmpty()){
14                long[] cur= busy.poll();
15                int r= (int)cur[1];
16                cnt[r]++;
17                busy.add(new long[]{cur[0]+ end- start, r});
18            } else {
19                int r= free.poll();
20                cnt[r]++;
21                busy.add(new long[]{end, r});
22            }
23        }
24        
25        int max= -1, idx= -1;
26        for(int i=0; i< n; i++){
27            if(cnt[i]> max){
28                max= cnt[i];
29                idx= i;
30            }
31        }
32        return idx;
33    }
34}