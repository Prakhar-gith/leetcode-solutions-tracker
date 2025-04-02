// Last updated: 4/2/2025, 3:10:19 PM
// Algorithm:
// 1. Build a directed graph where each ingredient points to recipes that require it.
// 2. Create an indegree map for each recipe representing how many ingredients are needed.
// 3. Initialize a queue with all available supplies (infinite supply).
// 4. Process the queue with BFS:
//    - For each ingredient in the queue, visit all recipes that depend on that ingredient.
//    - Reduce their indegree count. If a recipe's indegree becomes 0, it means all its ingredients are available.
//    - Add that recipe to the answer list and also to the queue (since a recipe can be used as an ingredient).
// 5. Return the list of recipes that can be made.
//
// Pseudo Code:
// -------------
// function findAllRecipes(recipes, ingredients, supplies):
//     graph = map from ingredient to list of recipes that need it
//     indegree = map from recipe to number of ingredients needed
//     for each recipe in recipes:
//         for each ing in ingredients[recipe]:
//             graph[ing].add(recipe)
//             indegree[recipe]++
//     queue = new Queue()
//     add all supplies to queue
//     answer = empty list
//     while queue not empty:
//         cur = queue.poll()
//         for each recipe in graph[cur]:
//             indegree[recipe]--
//             if indegree[recipe] == 0:
//                 answer.add(recipe)
//                 queue.add(recipe)
//     return answer
//
// Visualization Example:
// ------------------------
// recipes = ["bread", "sandwich"]
// ingredients = [["yeast", "flour"], ["bread", "meat"]]
// supplies = ["yeast", "flour", "meat"]
//
// Graph built:
//  "yeast" -> ["bread"]
//  "flour" -> ["bread"]
//  "bread" -> ["sandwich"]
//  "meat"  -> ["sandwich"]
//
// indegree:
//  "bread" = 2, "sandwich" = 2
//
// Start queue with ["yeast", "flour", "meat"]
// Process "yeast": decrease indegree of "bread" to 1.
// Process "flour": decrease indegree of "bread" to 0 -> add "bread" to answer and queue.
// Process "meat": decrease indegree of "sandwich" to 1.
// Process "bread": decrease indegree of "sandwich" to 0 -> add "sandwich" to answer and queue.
// Answer = ["bread", "sandwich"]

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // Map to track which recipes depend on a given ingredient.
        Map<String, List<String>> graph = new HashMap<>();
        // Map to track the number of ingredients needed for each recipe.
        Map<String, Integer> indegree = new HashMap<>();
        
        // Initialize indegree for each recipe to 0.
        for (String recipe : recipes) {
            indegree.put(recipe, 0);
        }
        
        // Build graph and update indegree for each recipe.
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            // For every ingredient required by this recipe
            for (String ing : ingredients.get(i)) {
                // Agar graph mein ingredient key exist nahi karti, to add a new list.
                graph.computeIfAbsent(ing, key -> new ArrayList<>()).add(recipe);
                // Increase the indegree count for the recipe as one more ingredient is needed.
                indegree.put(recipe, indegree.get(recipe) + 1);
            }
        }
        
        // Queue for BFS, starting with all initial supplies.
        Queue<String> queue = new LinkedList<>();
        for (String supply : supplies) {
            queue.offer(supply);
        }
        
        // List to store the recipes that can be made.
        List<String> answer = new ArrayList<>();
        
        // Process the queue.
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            // Check if any recipe depends on this current ingredient.
            if (!graph.containsKey(cur)) continue;
            // For each recipe that requires the current ingredient:
            for (String recipe : graph.get(cur)) {
                // Decrement the count of needed ingredients.
                indegree.put(recipe, indegree.get(recipe) - 1);
                // Agar saare required ingredients mil gaye (indegree becomes 0), then recipe can be made.
                if (indegree.get(recipe) == 0) {
                    answer.add(recipe);
                    // Add the recipe itself to the queue, kyunki recipe bhi ek ingredient ban sakta hai.
                    queue.offer(recipe);
                }
            }
        }
        
        // Return the list of all possible recipes that can be created.
        return answer;
    }
}
