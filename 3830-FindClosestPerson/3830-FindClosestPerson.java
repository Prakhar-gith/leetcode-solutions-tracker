// Last updated: 8/30/2025, 6:55:58 PM
class Solution {
    public int findClosest(int x, int y, int z) {
        x = Math.abs(x-z);
        y = Math.abs(y-z);
        if(x < y ){
            return 1;
        }
        else if(x > y){
            return 2;
        }
        else{
            return 0;
        }
    }
}