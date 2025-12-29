// Last updated: 12/29/2025, 7:24:23 PM
1class Solution {
2    int[][] map= new int[7][7];
3    Set<String> memo= new HashSet<>();
4    public boolean pyramidTransition(String bottom, List<String> allowed) {
5        for(String s: allowed) map[s.charAt(0)-'A'][s.charAt(1)-'A']|= (1<<(s.charAt(2)-'A'));
6        return solve(bottom);
7    }
8    boolean solve(String cur){
9        if(cur.length()==1) return true;
10        if(memo.contains(cur)) return false;
11        if(dfs(cur, 0, new StringBuilder())) return true;
12        memo.add(cur);
13        return false;
14    }
15    boolean dfs(String cur, int i, StringBuilder next){
16        if(i==cur.length()-1) return solve(next.toString());
17        int mask= map[cur.charAt(i)-'A'][cur.charAt(i+1)-'A'];
18        for(int j=0; j<7; j++){
19            if(((mask>>j)&1)==1){
20                next.append((char)('A'+j));
21                if(dfs(cur, i+1, next)) return true;
22                next.setLength(next.length()-1);
23            }
24        }
25        return false;
26    }
27}