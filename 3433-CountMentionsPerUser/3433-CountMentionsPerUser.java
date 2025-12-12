// Last updated: 12/13/2025, 12:30:02 AM
1class Solution {
2    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
3        java.util.Collections.sort(events, (a, b) -> {
4            int t1= Integer.parseInt(a.get(1));
5            int t2= Integer.parseInt(b.get(1));
6            if(t1 != t2) return t1 - t2;
7            return a.get(0).equals("OFFLINE") ? -1 : 1;
8        });
9        
10        int[] ans= new int[numberOfUsers];
11        int[] onlineTime= new int[numberOfUsers];
12        
13        for(List<String> e : events) {
14            String type= e.get(0);
15            int t= Integer.parseInt(e.get(1));
16            
17            if(type.equals("OFFLINE")) {
18                int id= Integer.parseInt(e.get(2));
19                onlineTime[id] = t + 60;
20            } else {
21                String s= e.get(2);
22                if(s.equals("ALL")) {
23                    for(int i= 0; i< numberOfUsers; i++) ans[i]++;
24                } else if(s.equals("HERE")) {
25                    for(int i= 0; i< numberOfUsers; i++) {
26                        if(onlineTime[i] <= t) ans[i]++;
27                    }
28                } else {
29                    String[] ids= s.split(" ");
30                    for(String token : ids) {
31                        int id= Integer.parseInt(token.substring(2));
32                        ans[id]++;
33                        
34                    }
35                }
36            }
37        }
38        return ans;
39    }
40}