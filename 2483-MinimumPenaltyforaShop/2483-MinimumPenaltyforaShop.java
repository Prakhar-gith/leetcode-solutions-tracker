// Last updated: 12/27/2025, 12:02:13 AM
1class Solution {
2    public int bestClosingTime(String customers) {
3        int m=0, c= 0;
4        int ans=0;
5        for(int i=0;i<customers.length();i++){
6             if(customers.charAt(i)=='Y') c++;
7             else c--;
8             if(c> m){
9                 m= c;
10                 ans= i+1;
11             }
12        }
13        return ans;
14    }
15}