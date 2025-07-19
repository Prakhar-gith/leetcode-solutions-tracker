// Last updated: 7/19/2025, 5:43:09 PM


class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Algorithm:
        // The main idea is that if we sort the folder paths alphabetically (lexicographically),
        // a parent folder will always appear right before its sub-folders in the sorted list.
        // For example, "/a" comes before "/a/b", and "/c/d" comes before "/c/d/e".
        //
        // Is logic ka fayda utha kar, hum ek simple single-pass solution bana sakte hain.
        // 1. First, sort the entire array of folder paths.
        // 2. Create a result list and add the very first folder from the sorted array. This is guaranteed to be a root-level folder (in the context of our list).
        // 3. Iterate through the rest of the sorted array. For each folder, compare it with the last folder we added to our result list.
        // 4. If the current folder is a sub-folder of the last one we added, we ignore it.
        // 5. A folder is a sub-folder if it starts with the parent's path followed by a '/'. For example, "/a/b" starts with "/a/".
        // 6. If the current folder is NOT a sub-folder, it means we've found a new root-level folder. We add it to our result list and update our reference to this new "last added" folder.
        // 7. After checking all the folders, the result list will contain only the parent folders.

        // Pseudo Code:
        // 1. Sort(folder)
        // 2. result = new ArrayList()
        // 3. if folder.length == 0: return result
        //
        // 4. // Add the first folder, it's always a valid parent.
        // 5. result.add(folder[0])
        // 6. last_parent = folder[0]
        //
        // 7. for i = 1 to folder.length - 1:
        // 8.    current_folder = folder[i]
        // 9.    // Check if current folder is a sub-folder of the last one added to result.
        // 10.   // The check requires the parent path PLUS a slash.
        // 11.   if not current_folder.startsWith(last_parent + "/"):
        // 12.      // If not a sub-folder, it's a new parent.
        // 13.      result.add(current_folder)
        // 14.      last_parent = current_folder
        //
        // 15. return result

        // Step 1: Sort the folder array lexicographically.
        // Sabse pehle, array ko sort karlo. Isse parent folder apne sub-folders se theek pehle aa jayenge.
        Arrays.sort(folder);

        // This list will store our final answer.
        List<String> result = new ArrayList<>();
        if (folder.length == 0) {
            return result; // Agar input khali hai, to khali list return karo.
        }

        // The first folder in the sorted list is always a parent folder, so add it.
        // Hum ise `lastAddedParent` variable me store karenge taaki aage ke folders se compare kar sakein.
        String lastAddedParent = folder[0];
        result.add(lastAddedParent);

        // Iterate from the second folder onwards.
        for (int i = 1; i < folder.length; i++) {
            String currentFolder = folder[i];

            // Check if the current folder is a sub-folder of the last parent we added.
            // Crucial check: We must append a "/" to the parent path to ensure we don't mistakenly flag
            // folders like "/a/b/ca" as a sub-folder of "/a/b/c".
            // The `startsWith` method checks if `currentFolder` begins with the string `lastAddedParent + "/"`.
            if (!currentFolder.startsWith(lastAddedParent + "/")) {
                // If it's not a sub-folder, it's a new parent folder.
                // Toh isko result me add karo aur `lastAddedParent` ko update kar do.
                result.add(currentFolder);
                lastAddedParent = currentFolder;
            }
            // Agar yeh ek sub-folder hai, toh hum kuch nahi karte aur use ignore kar dete hain.
        }

        return result;

        /*
         * Visualization of the Sorting Approach:
         *
         * Input:          ["/c/d/e", "/a", "/c/f", "/a/b", "/c/d"]
         *
         * After Sorting:  ["/a", "/a/b", "/c/d", "/c/d/e", "/c/f"]
         *
         * Iteration Trace:
         * 1. Initialize `result = []`, `lastAddedParent = null`
         * 2. Add first element: `result = ["/a"]`, `lastAddedParent = "/a"`
         *
         * 3. i = 1, currentFolder = "/a/b"
         * - Does "/a/b" start with "/a/"? Yes.
         * - It's a sub-folder. Ignore it. `result` is unchanged.
         *
         * 4. i = 2, currentFolder = "/c/d"
         * - Does "/c/d" start with "/a/"? No.
         * - It's a new parent. Add it.
         * - `result = ["/a", "/c/d"]`, `lastAddedParent` becomes "/c/d".
         *
         * 5. i = 3, currentFolder = "/c/d/e"
         * - Does "/c/d/e" start with "/c/d/"? Yes.
         * - It's a sub-folder. Ignore it. `result` is unchanged.
         *
         * 6. i = 4, currentFolder = "/c/f"
         * - Does "/c/f" start with "/c/d/"? No.
         * - It's a new parent. Add it.
         * - `result = ["/a", "/c/d", "/c/f"]`, `lastAddedParent` becomes "/c/f".
         *
         * Loop ends. Final result is ["/a", "/c/d", "/c/f"].
        */
    }
}