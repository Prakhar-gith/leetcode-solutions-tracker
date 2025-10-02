// Last updated: 10/2/2025, 7:08:02 PM
class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int bottlesDrunk = numBottles;
        int emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            emptyBottles -= numExchange;
            numExchange++;
            
            bottlesDrunk++;
            emptyBottles++;
        }

        return bottlesDrunk;
    }
}