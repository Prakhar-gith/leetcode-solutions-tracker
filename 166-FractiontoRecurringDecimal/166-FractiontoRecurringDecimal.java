// Last updated: 10/7/2025, 10:16:47 PM
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        
        if ((numerator > 0) ^ (denominator > 0)) {
            res.append("-");
        }

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        res.append(num / den);
        long remainder = num % den;

        if (remainder == 0) {
            return res.toString();
        }

        res.append(".");
        Map<Long, Integer> remainderMap = new HashMap<>();

        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                res.insert(remainderMap.get(remainder), "(");
                res.append(")");
                break;
            }
            
            remainderMap.put(remainder, res.length());
            remainder *= 10;
            res.append(remainder / den);
            remainder %= den;
        }

        return res.toString();
    }
}