// Last updated: 8/30/2025, 6:56:09 PM
class Solution {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        // Convert the string into a list of integer digits.
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            digits.add(s.charAt(i) - '0');
        }
        
        // Perform the operation exactly (n - 2) times.
        // Each operation reduces the length by 1.
        int operations = n - 2;
        for (int op = 0; op < operations; op++) {
            List<Integer> nextDigits = new ArrayList<>();
            // Compute the new list by summing adjacent pairs modulo 10.
            for (int i = 0; i < digits.size() - 1; i++) {
                int newDigit = (digits.get(i) + digits.get(i + 1)) % 10;
                nextDigits.add(newDigit);
            }
            digits = nextDigits;
        }
        
        // After (n - 2) operations, the list should contain exactly 2 digits.
        return digits.get(0).equals(digits.get(1));
    }
}




// while (s.length() > 2) {
//             StringBuilder s2 = new StringBuilder();
//             for (int i = 0; i < s.length() - 1; i++) {
//                 int d1 = s.charAt(i) - '0';
//                 int d2 = s.charAt(i + 1) - '0';
//                 int op = (d1 + d2) % 10;
//                 s2.append(op);
//             }
//             s = s2.toString();
//         }
//         return s.charAt(0) == s.charAt(1);