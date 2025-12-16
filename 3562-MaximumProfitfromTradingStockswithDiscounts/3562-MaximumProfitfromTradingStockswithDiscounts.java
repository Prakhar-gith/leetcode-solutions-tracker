// Last updated: 12/16/2025, 5:02:33 PM
1class Solution {
2    List<Integer>[] adj;
3    int[] p,f;
4    int B;
5    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
6        adj = new ArrayList[n+ 1];
7        for(int i= 0; i<=n; i++) adj[i]= new ArrayList<>();
8        for(int[] h : hierarchy) adj[h[0]].add(h[1]);
9        p=present; f=future; B=budget;
10        return dfs(1)[0][B];
11    }
12    int[][] dfs(int u){
13        int[][] res = new int[2][B+1];
14        int[] noBuy = new int[B+1];
15        int[] yesBuy = new int[B+1];
16        for(int v:adj[u]){
17            int[][] child = dfs(v);
18            noBuy = merge(noBuy, child[0]);
19            yesBuy = merge(yesBuy, child[1]);
20        }
21        int costFull=p[u-1];
22        int profFull=f[u-1]-costFull;
23        int costHalf=p[u-1]/2;
24        int profHalf=f[u-1]-costHalf;
25        for(int j=0; j<=B; j++){
26            res[0][j]= noBuy[j];
27            if(j>= costFull) res[0][j]= Math.max(res[0][j], profFull+ yesBuy[j- costFull]);
28            res[1][j]= noBuy[j];
29            if(j>= costHalf) res[1][j]= Math.max(res[1][j], profHalf+ yesBuy[j- costHalf]);
30        }
31        return res;
32    }
33    int[] merge(int[] a, int[] b){
34        int[] res= new int[B+ 1];
35        for(int j=B; j>=0; j--){
36            for(int k=0; k<=j; k++){
37                res[j]= Math.max(res[j], a[j-k]+ b[k]);
38            }
39        }
40        return res;
41    }
42}