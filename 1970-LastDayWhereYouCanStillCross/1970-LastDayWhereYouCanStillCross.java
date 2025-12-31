// Last updated: 1/1/2026, 3:14:39 AM
1class Solution {
2    public int latestDayToCross(int row, int col, int[][] cells) {
3        int l= 1, r= cells.length, ans= 0;
4        while(l<= r){
5            int mid= (l+ r)>>> 1;
6            if(can(row, col, mid, cells)){
7                ans= mid;
8                l= mid+ 1;
9            }else r= mid- 1;
10        }
11        return ans;
12    }
13    boolean can(int R, int C, int d, int[][] cells){
14        boolean[][] g= new boolean[R][C];
15        for(int i=0; i< d; i++) g[cells[i][0]- 1][cells[i][1]- 1]= true;
16        Queue<int[]> q= new LinkedList<>();
17        for(int j=0; j< C; j++){
18            if(!g[0][j]){
19                q.offer(new int[]{0, j});
20                g[0][j]= true;
21            }
22        }
23        int[] dir= {0, 1, 0, -1, 0};
24        while(!q.isEmpty()){
25            int[] cur= q.poll();
26            int r= cur[0], c= cur[1];
27            if(r== R- 1) return true;
28            for(int i=0; i< 4; i++){
29                int nr= r+ dir[i], nc= c+ dir[i+ 1];
30                if(nr>= 0 && nr< R && nc>= 0 && nc< C && !g[nr][nc]){
31                    g[nr][nc]= true;
32                    q.offer(new int[]{nr, nc});
33                }
34            }
35        }
36        return false;
37    }
38}