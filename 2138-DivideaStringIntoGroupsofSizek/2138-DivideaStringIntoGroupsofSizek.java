// Last updated: 6/23/2025, 1:29:52 AM
import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] divideString(String s, int k, char fill) {
        List<String> groups = new ArrayList<>();
        int n = s.length();

        for (int i = 0; i < n; i += k) {
            if (i + k <= n) {
                // Full group
                groups.add(s.substring(i, i + k));
            } else {
                // Last, potentially partial, group
                StringBuilder sb = new StringBuilder(s.substring(i));
                int charsNeeded = k - (n - i);
                for (int j = 0; j < charsNeeded; j++) {
                    sb.append(fill);
                }
                groups.add(sb.toString());
            }
        }

        return groups.toArray(new String[0]);
    }
}