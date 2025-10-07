// Last updated: 10/7/2025, 10:18:28 PM
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] revs1 = version1.split("\\.");
        String[] revs2 = version2.split("\\.");
        
        int maxLength = Math.max(revs1.length, revs2.length);
        
        for (int i = 0; i < maxLength; i++) {
            int v1 = (i < revs1.length) ? Integer.parseInt(revs1[i]) : 0;
            int v2 = (i < revs2.length) ? Integer.parseInt(revs2[i]) : 0;
            
            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
        }
        
        return 0;
    }
}