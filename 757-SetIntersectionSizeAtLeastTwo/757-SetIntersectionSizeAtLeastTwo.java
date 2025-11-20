// Last updated: 11/20/2025, 9:40:42 PM
class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        java.util.Arrays.sort(intervals, (a,b) -> a[1] == b[1]? b[0]-a[0] : a[1]-b[1]);
        int p1= -1, p2= -1, res = 0;
        
        for(int[] val : intervals) {
            int s = val[0];
            int e = val[1];
            
            if(s > p2) {
                res += 2;
                p1 = e- 1;
                p2 = e;
            } else if(s > p1) {
                res++;
                p1 = p2;
                p2 = e;
            }
        }
        return res;
    }
}