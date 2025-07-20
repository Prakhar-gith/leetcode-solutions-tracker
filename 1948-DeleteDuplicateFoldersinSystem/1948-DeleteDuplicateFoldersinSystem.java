// Last updated: 7/20/2025, 5:10:46 PM
class Solution {

    // TrieNode class represents a single folder in our file system.
    // Har node ek folder hai.
    private static class TrieNode {
        String name; // Folder ka naam
        Map<String, TrieNode> children = new TreeMap<>(); // Subfolders, TreeMap use karne se children hamesha sorted rahenge.
        boolean deleted = false; // Flag to mark this folder for deletion.
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        
        // Algorithm:
        // The problem asks us to find and delete duplicate folders. Two folders are duplicate
        // if they have the same non-empty structure of subfolders.
        // This is a great problem for a Trie + DFS approach.
        // 1. Build a Trie: First, we insert all paths into a Trie to represent the file system structure.
        //    Using a TreeMap for children keeps them sorted, simplifying signature generation.
        // 2. Serialize Structures (Post-order DFS): We traverse the Trie from the bottom up. For each
        //    folder (node), we create a unique string "signature" that represents its subfolder structure.
        //    This signature is formed by its children's names and their signatures.
        // 3. Find & Mark Duplicates: We use a HashMap to keep track of the signatures we've seen.
        //    If we generate a signature that's already in the map, we've found a duplicate. We mark
        //    both the current folder and the one we saw earlier for deletion.
        // 4. Collect Final Paths: We do a final DFS traversal from the root. We build the paths for
        //    all folders that are NOT marked as deleted. If a parent is marked, we skip its entire
        //    subtree, which correctly handles deleting subfolders of deleted folders.

        // Pseudo Code:
        // 1. Create a root TrieNode.
        // 2. For each path in `paths`:
        // 3.   Insert path into the Trie.
        // 4. Create a HashMap `seenSignatures` to store <Signature, Node>.
        // 5. Call `getSignature(root, seenSignatures)` to traverse and mark duplicates.
        // 6. Create a `result` list.
        // 7. Call `collectPaths(root, new LinkedList(), result)` to gather non-deleted paths.
        // 8. Return `result`.

        // Step 1: Build the Trie from the given paths.
        // Saare paths ko Trie structure me daal do.
        TrieNode root = new TrieNode();
        for (List<String> path : paths) {
            TrieNode curr = root;
            for (String folderName : path) {
                // `computeIfAbsent` is a clean way to get an existing child or create a new one.
                curr = curr.children.computeIfAbsent(folderName, k -> new TrieNode());
            }
        }

        // Step 2 & 3: Generate signatures and mark duplicates using a post-order DFS.
        // Ek map banayenge jisme har structure signature ke corresponding node store karenge.
        Map<String, TrieNode> seenSignatures = new HashMap<>();
        getSignature(root, seenSignatures);

        // Step 4: Collect all paths that are not marked for deletion.
        // Final list banane ke liye Trie ko traverse karo.
        List<List<String>> result = new ArrayList<>();
        collectPaths(root, new LinkedList<>(), result);
        
        return result;
    }

    // This is a post-order DFS function to generate a structure signature for each node.
    // Har folder ka ek unique signature banayenge taaki duplicates pehchaan sakein.
    private String getSignature(TrieNode node, Map<String, TrieNode> seenSignatures) {
        // Base case: If a folder is empty, it has an empty structure.
        if (node.children.isEmpty()) {
            return "";
        }

        // Recursively get signatures for all children.
        StringBuilder signatureBuilder = new StringBuilder();
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String folderName = entry.getKey();
            TrieNode childNode = entry.getValue();
            signatureBuilder.append(folderName)
                           .append("(")
                           .append(getSignature(childNode, seenSignatures))
                           .append(")");
        }
        
        String signature = signatureBuilder.toString();

        // If we've seen this exact structure before, it's a duplicate.
        // "non-empty set" rule means we only check non-leaf folders.
        if (seenSignatures.containsKey(signature)) {
            // Mark both the previously seen node and the current node for deletion.
            seenSignatures.get(signature).deleted = true;
            node.deleted = true;
        } else {
            // Otherwise, record this new structure signature.
            seenSignatures.put(signature, node);
        }

        return signature;
    }

    // This is a pre-order DFS to collect the paths of non-deleted folders.
    // Ab final result ke liye, un sabhi folders ka path collect karo jo delete nahi hue hain.
    private void collectPaths(TrieNode node, LinkedList<String> currentPath, List<List<String>> result) {
        // Traverse through the children of the current node.
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String folderName = entry.getKey();
            TrieNode childNode = entry.getValue();

            // If a folder is marked for deletion, skip it and all its subfolders.
            // Agar folder deleted hai, toh uske subtree ko ignore kar do.
            if (childNode.deleted) {
                continue;
            }

            // Add the current folder to the path, add the path to our result list.
            currentPath.add(folderName);
            result.add(new ArrayList<>(currentPath));
            
            // Recurse to its children.
            collectPaths(childNode, currentPath, result);

            // Backtrack to explore other branches.
            currentPath.removeLast();
        }
    }

    /*
     * Visualization of the Trie and Signature Process:
     *
     * For paths = [["a"], ["c"], ["a","b"], ["c","b"]]
     *
     * 1. Trie Structure:
     * (root)
     * /     \
     * "a"     "c"
     * |       |
     * "b"     "b"
     *
     * 2. Post-order Signature Generation (Bottom-up):
     * - Node /a/b is a leaf. Its signature is "".
     * - Node /c/b is a leaf. Its signature is "".
     *
     * - For Node /a:
     * - It has one child "b" with signature "".
     * - Signature for /a becomes "b()".
     * - We store this in our map: seenSignatures.put("b()", node /a).
     *
     * - For Node /c:
     * - It has one child "b" with signature "".
     * - Signature for /c also becomes "b()".
     * - We check our map. "b()" already exists! It's a duplicate.
     * - Mark node /c as deleted.
     * - Mark the previously seen node (/a) as deleted.
     *
     * 3. Collect Paths:
     * - Start at root.
     * - Try to visit child "a". Node "a" is marked `deleted=true`. Skip it.
     * - Try to visit child "c". Node "c" is marked `deleted=true`. Skip it.
     * - Result is an empty list, which matches the expected output for this sub-problem.
     */
}