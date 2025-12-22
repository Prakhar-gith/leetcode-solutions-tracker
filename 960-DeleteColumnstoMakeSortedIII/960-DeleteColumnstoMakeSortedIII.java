// Last updated: 12/22/2025, 4:39:06 PM
1class Solution {
2    public int minDeletionSize(String[] strs) {
3        int n= strs.length;
4        int m= strs[0].length();
5        int[] dp= new int[m];
6        int maxK= 1;
7        for(int j=0; j< m; j++){
8            dp[j]= 1;
9            for(int i=0; i< j; i++){
10                boolean ok= true;
11                for(int r=0; r< n; r++){
12                    if(strs[r].charAt(i)> strs[r].charAt(j)){
13                        ok= false;
14                        break;
15                    }
16                }
17                if(ok) dp[j]= Math.max(dp[j], dp[i]+ 1);
18            }
19            maxK= Math.max(maxK, dp[j]);
20            
21        }
22        return m- maxK;
23    }
24}