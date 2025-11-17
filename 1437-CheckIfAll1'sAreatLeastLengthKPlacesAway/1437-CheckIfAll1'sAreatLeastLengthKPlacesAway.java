// Last updated: 11/17/2025, 9:54:06 PM
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int p =-1;
        for(int i = 0;i <nums.length; i ++){
            if(nums[i] ==1){
                if(p!= -1&& i - p <=k) return false;
                p= i;
            }
        }
        return true;
    }
}