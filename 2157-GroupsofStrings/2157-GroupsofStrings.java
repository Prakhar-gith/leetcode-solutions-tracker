// Algorithm:
// 1. Convert each word to a bitmask representation where each bit represents a letter (a-z).
// 2. Use a Union-Find (Disjoint Set Union) structure to group words that are "connected" by the defined operations.
// 3. First, for duplicate bitmasks, union their indices using a HashMap that maps a bitmask to its first occurrence.
// 4. For each word, simulate the three operations:
//    a. Deletion: Remove one letter at a time (i.e., unset one bit) and if the new bitmask exists, union the groups.
//    b. Addition: Add one letter (i.e., set one bit) and if the new bitmask exists, union the groups.
//    c. Replacement: Remove one letter and add a different letter (i.e., unset one bit and set another) and if the new bitmask exists, union the groups.
// 5. After processing all words, count the number of distinct groups and the maximum size among them.
// 6. Return an array where ans[0] is the number of groups and ans[1] is the size of the largest group.

/*
   Pseudo Code:
   -------------
   function groupStrings(words):
       n = length(words)
       masks = new array of size n
       for i from 0 to n-1:
           masks[i] = bitmask representation of words[i]

       uf = new UnionFind(n)
       map = new HashMap  // Map from bitmask to first occurrence index
       for i from 0 to n-1:
           if map contains masks[i]:
               uf.union(i, map.get(masks[i]))
           else:
               map.put(masks[i], i)

       for i from 0 to n-1:
           mask = masks[i]
           // Deletion: remove one letter
           for j from 0 to 25:
               if mask has bit j:
                   newMask = mask with bit j removed
                   if map contains newMask:
                       uf.union(i, map.get(newMask))
           
           // Addition: add one letter
           for j from 0 to 25:
               if mask does not have bit j:
                   newMask = mask with bit j added
                   if map contains newMask:
                       uf.union(i, map.get(newMask))
           
           // Replacement: remove one letter and add a different letter
           for j from 0 to 25:
               if mask has bit j:
                   for k from 0 to 25:
                       if mask does not have bit k:
                           newMask = (mask with bit j removed) with bit k added
                           if map contains newMask:
                               uf.union(i, map.get(newMask))
       
       compSize = new HashMap  // Map from root to group size
       for i from 0 to n-1:
           root = uf.find(i)
           compSize[root]++ 
       
       groups = number of keys in compSize
       maxSize = maximum value in compSize
       return [groups, maxSize]
       
   Visualization Example:
   ----------------------
   For words = ["a", "b", "ab", "cde"]:
   - "a"    => mask: 000...0001
   - "b"    => mask: 000...0010
   - "ab"   => mask: 000...0011
   - "cde"  => mask: bits for c, d, e set (e.g., 000...11100)
   Grouping process:
   - "a" can connect to "b" (via replacement/addition) and "ab" (via addition or deletion).
   - "cde" remains isolated.
   Result: 2 groups with the largest group size of 3.
*/

class Solution {
    public int[] groupStrings(String[] words) {
        int n = words.length; // total number of words
        int[] masks = new int[n]; // array to store bitmask representation for each word
        
        // Convert each word into its bitmask representation
        for (int i = 0; i < n; i++) {
            int mask = 0;
            // Har character ke liye, uska corresponding bit set karo
            for (char c : words[i].toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            masks[i] = mask;
        }
        
        // Initialize Union-Find (Disjoint Set Union) structure for n words
        UF uf = new UF(n);
        
        // HashMap to map each bitmask to the first index where it occurs
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int mask = masks[i];
            // Agar same mask pehle se exist karta hai, union kar do current index se
            if (map.containsKey(mask)) {
                uf.union(i, map.get(mask));
            } else {
                map.put(mask, i);
            }
        }
        
        // For each word, try deletion, addition, and replacement operations
        for (int i = 0; i < n; i++) {
            int mask = masks[i];
            
            // Operation 1: Deletion - Remove one letter at a time
            for (int j = 0; j < 26; j++) {
                // Agar letter j is present in mask
                if ((mask & (1 << j)) != 0) {
                    int newMask = mask ^ (1 << j); // letter j ko remove karo
                    if (map.containsKey(newMask)) {
                        uf.union(i, map.get(newMask));
                    }
                }
            }
            
            // Operation 2: Addition - Add one letter not already present
            for (int j = 0; j < 26; j++) {
                // Agar letter j is not present in mask
                if ((mask & (1 << j)) == 0) {
                    int newMask = mask | (1 << j); // letter j ko add karo
                    if (map.containsKey(newMask)) {
                        uf.union(i, map.get(newMask));
                    }
                }
            }
            
            // Operation 3: Replacement - Remove one letter and add another
            for (int j = 0; j < 26; j++) {
                // Agar letter j present hai, remove it
                if ((mask & (1 << j)) != 0) {
                    for (int k = 0; k < 26; k++) {
                        // Sirf un letters ke liye jinke bits abhi set nahi hain
                        if ((mask & (1 << k)) == 0) {
                            int newMask = (mask ^ (1 << j)) | (1 << k); // remove j and add k
                            if (map.containsKey(newMask)) {
                                uf.union(i, map.get(newMask));
                            }
                        }
                    }
                }
            }
        }
        
        // Count the number of groups and determine the size of the largest group
        int groups = 0;
        int maxSize = 0;
        Map<Integer, Integer> compSize = new HashMap<>();
        // For each word, find its group's representative and count members
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            compSize.put(root, compSize.getOrDefault(root, 0) + 1);
        }
        // Calculate total groups and find the largest group size
        for (int size : compSize.values()) {
            groups++; // har unique root ek alag group hai
            maxSize = Math.max(maxSize, size);
        }
        
        // Return the result: [number of groups, size of largest group]
        return new int[]{groups, maxSize};
    }
    
    // Union-Find class with path compression and union by rank
    class UF {
        int[] parent;
        int[] rank;
        
        // Constructor: initialize parent and rank arrays for n elements
        public UF(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // initially, each element is its own parent
            }
        }
        
        // Find function with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // recursively find root and compress path
            }
            return parent[x];
        }
        
        // Union function to merge two sets using union by rank
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            // Agar dono already same root mein hain, toh kuch karne ki zarurat nahi
            if (rootX == rootY) return;
            // Merge smaller rank tree under larger rank tree
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++; // agar ranks equal hain, toh ek ka rank increment karo
            }
        }
    }
}
