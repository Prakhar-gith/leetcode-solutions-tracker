// Last updated: 12/30/2025, 4:42:10 PM
1class Solution {
2    public int numMagicSquaresInside(int[][] grid) {
3        int R= grid.length, C= grid[0].length;
4        int ans= 0;
5        for(int r= 0; r< R- 2; r++){
6            for(int c= 0; c< C- 2; c++){
7                if(grid[r+ 1][c+ 1]!= 5) continue;
8                if(magic(grid, r, c)) ans++;
9            }
10        }
11        return ans;
12    }
13    boolean magic(int[][] g, int r, int c){
14        int[] cnt= new int[16];
15        for(int i= r; i< r+ 3; i++)
16            for(int j= c; j< c+ 3; j++)
17                cnt[g[i][j]]++;
18        for(int k= 1; k<= 9; k++) if(cnt[k]!= 1) return false;
19        
20        if(g[r][c]+ g[r][c+ 1]+ g[r][c+ 2]!= 15) return false;
21        if(g[r+ 2][c]+ g[r+ 2][c+ 1]+ g[r+ 2][c+ 2]!= 15) return false;
22        if(g[r][c]+ g[r+ 1][c]+ g[r+ 2][c]!= 15) return false;
23        if(g[r][c+ 2]+ g[r+ 1][c+ 2]+ g[r+ 2][c+ 2]!= 15) return false;
24        if(g[r][c]+ g[r+ 1][c+ 1]+ g[r+ 2][c+ 2]!= 15) return false;
25        if(g[r][c+ 2]+ g[r+ 1][c+ 1]+ g[r+ 2][c]!= 15) return false;
26        return true;
27    }
28}