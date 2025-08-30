// Last updated: 8/30/2025, 6:56:06 PM
class Solution {
    public long maxWeight(int[] pizzas) {
        int n = pizzas.length;
        long ans = 0; 
        long totalDays = n/4;
        long oddDays = totalDays - totalDays/2;

        Arrays.sort(pizzas);
        
        int day = 1;
        int i = n-1;

        while(day <= totalDays){
            if(day>oddDays) i--;
            ans = ans + pizzas[i];
            i--;
            day++;
        }
        return ans;
        
        // Arrays.sort(pizzas);
        // int n = pizzas.length;
        // int weight = 0;
        // int days = n/4;
        // int left = 0;
        // int right = n -1;

        // for(int i = 1; i <= days; i++){
        //     if(i%2 == 1){
        //         weight = weight + pizzas[right];
        //         left+=3;
        //         right--;
        //     }
        //     else{
        //         weight += pizzas[right-1];
        //         left+=2;
        //         right-=2;
        //     }
        // }
        // return weight;
    }
}