// Last updated: 12/4/2025, 7:25:28 PM
1class Solution {
2    public int countCollisions(String directions) {
3        int l= 0, r= directions.length()- 1;
4        while(l<= r && directions.charAt(l)== 'L') l++;
5        while(r>= l && directions.charAt(r)== 'R') r--;
6        
7        int ans= 0;
8        for(int i=l; i<= r; i++) {
9            if(directions.charAt(i)!= 'S') ans++;
10        }
11        return ans;
12    }
13}