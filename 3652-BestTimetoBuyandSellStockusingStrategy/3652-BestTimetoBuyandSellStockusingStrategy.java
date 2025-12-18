// Last updated: 12/18/2025, 6:37:48 PM
1class Solution {
2    public long maxProfit(int[] prices, int[] strategy, int k) {
3        long base= 0;
4        int n= prices.length;
5        for(int i=0; i< n; i++) base+= (long)strategy[i]* prices[i];
6        
7        long curOrg= 0;
8        long curSec= 0;
9        int m= k/ 2;
10        
11        for(int i=0; i<k; i++){
12            curOrg+= (long)strategy[i]* prices[i];
13            if(i>= m) curSec+= prices[i];
14        }
15        
16        long maxG= Math.max(0, curSec- curOrg);
17        
18        for(int i= 1; i<= n- k; i++){
19            curOrg-= (long)strategy[i- 1]* prices[i- 1];
20            curOrg+= (long)strategy[i+ k- 1]* prices[i+ k- 1];
21            
22            curSec-= prices[i+ m- 1];
23            curSec+= prices[i+ k- 1];
24            
25            maxG= Math.max(maxG, curSec- curOrg);
26        }
27        return base+ maxG;
28    }
29}