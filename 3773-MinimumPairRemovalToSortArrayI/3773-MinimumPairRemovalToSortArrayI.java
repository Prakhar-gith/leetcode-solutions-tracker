// Last updated: 8/30/2025, 6:56:08 PM
class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> ls = new ArrayList<>();
        for(int num : nums){
            ls.add(num);
        }
        int ops = 0;

        while(true){
            boolean nd = true;
            for(int i = 1; i< ls.size(); i++){
                if(ls.get(i) < ls.get(i-1)){
                    nd = false;
                    break;
                }
            }
            if (nd){
                return ops;
            }

            int minP = Integer.MAX_VALUE;
            int pos = 0;
            for(int i =0; i<ls.size() - 1; i++){
                int cp = ls.get(i) + ls.get(i+1);
                if(cp < minP){
                    minP = cp;
                    pos = i;
                }
            }

            List<Integer> ans = new ArrayList<>();
            for(int i = 0; i < pos; i++){
                ans.add(ls.get(i));
            }
            ans.add(minP);

            for(int i = pos+2; i<ls.size(); i++){
                ans.add(ls.get(i));
            }
            ls = ans;
            ops++;
        }
        
        
    }
}