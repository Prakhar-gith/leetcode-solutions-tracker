// Last updated: 6/11/2025, 3:51:18 AM
class Solution {
    public int findKthNumber(int n, int k) {
        /*
        Algorithm:
        1. String lexicographical order ko tree/trie ki tarah socho, jaha har node ek prefix denote karta hai.
        2. Start prefix = 1 (sabse chhota lex number). Decrement k by 1 kyunki prefix=1 pehle element hai.
        3. Jab tak k > 0:
           a. count = countNodes(prefix, prefix+1, n)
              // kitne numbers hain prefix ke subtree mein
           b. Agar count <= k:
                 // subtree skip karo, next sibling pe move karo
                 prefix += 1;
                 k -= count;
              Else:
                 // subtree mein hi k-th hai, depth-first descend karo
                 prefix *= 10;
                 k -= 1; // khud prefix consume hua
        4. Jab k == 0, prefix hi answer hai.

        Pseudocode:
        function countNodes(pref, next, n):
          cnt = 0
          while pref <= n:
            cnt += min(n+1, next) - pref
            pref *= 10; next *= 10
          return cnt

        current = 1; k--;
        while k > 0:
          cnt = countNodes(current, current+1, n)
          if cnt <= k:
            current++;
            k -= cnt;
          else:
            current *= 10;
            k--;
        return current;

        Visualization (n=13, k=2):
        Lex order: [1,10,11,12,13,2,...]
        current=1, k->1
        countNodes(1,2,13)=5, 5>1 -> descend: current=10, k->0 -> answer=10
        */

        int current = 1;
        k -= 1;
        while (k > 0) {
            long cnt = countNodes(current, current + 1, n);
            if (cnt <= k) {
                current += 1;
                k -= cnt;
            } else {
                current *= 10;
                k -= 1;
            }
        }
        return current;
    }

    // Helper: count numbers under [prefix, next) within 1..n
    private long countNodes(long prefix, long nextPrefix, int n) {
        long cnt = 0;
        while (prefix <= n) {
            cnt += Math.min((long)n + 1, nextPrefix) - prefix;
            prefix *= 10;
            nextPrefix *= 10;
        }
        return cnt;
    }
}
