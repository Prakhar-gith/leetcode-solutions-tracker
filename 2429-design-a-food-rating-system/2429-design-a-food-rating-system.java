import java.util.*;

class FoodRatings {
    private Map<String, Integer> foodRatings;
    private Map<String, String> foodCuisines;
    private Map<String, PriorityQueue<Food>> cuisineRatings;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodRatings = new HashMap<>();
        foodCuisines = new HashMap<>();
        cuisineRatings = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            foodRatings.put(foods[i], ratings[i]);
            foodCuisines.put(foods[i], cuisines[i]);
            cuisineRatings.putIfAbsent(cuisines[i], new PriorityQueue<>((a, b) -> {
                if (a.rating != b.rating) {
                    return b.rating - a.rating;
                }
                return a.name.compareTo(b.name);
            }));
            cuisineRatings.get(cuisines[i]).add(new Food(foods[i], ratings[i]));
        }
    }
    
    public void changeRating(String food, int newRating) {
        foodRatings.put(food, newRating);
        String cuisine = foodCuisines.get(food);
        cuisineRatings.get(cuisine).add(new Food(food, newRating));
    }
    
    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineRatings.get(cuisine);
        while (!pq.isEmpty()) {
            Food food = pq.peek();
            if (food.rating == foodRatings.get(food.name)) {
                return food.name;
            }
            pq.poll();
        }
        return null;
    }

    private static class Food {
        String name;
        int rating;

        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }
}