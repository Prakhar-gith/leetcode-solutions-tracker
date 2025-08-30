// Last updated: 8/30/2025, 6:56:20 PM
class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        boolean[] visited = new boolean[n];
        long scr = 0;
        int cidx  = 0;
        while(cidx >= 0 && cidx < n){
            if(visited[cidx]){
                break;
            }
            visited[cidx] = true;
            String instruction = instructions[cidx];
            if("add".equals(instruction)){
                scr += values[cidx];
                cidx++;
                
            }
            else{
                cidx += values[cidx];
            }
        }
        return scr;
    }
}