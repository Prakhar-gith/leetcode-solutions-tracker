// Last updated: 9/22/2025, 1:37:16 AM
import java.util.*;

class MovieRentingSystem {

    private Map<Integer, Set<int[]>> unrented;
    private Set<int[]> rented;
    private Map<Integer, Map<Integer, Integer>> moviePrices;

    public MovieRentingSystem(int n, int[][] entries) {
        unrented = new HashMap<>();
        rented = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        moviePrices = new HashMap<>();

        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            unrented.computeIfAbsent(movie, k -> new TreeSet<>((a, b) -> {
                if (a[1] != b[1]) return a[1] - b[1];
                return a[0] - b[0];
            })).add(new int[]{shop, price});
            
            moviePrices.computeIfAbsent(shop, k -> new HashMap<>()).put(movie, price);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        if (!unrented.containsKey(movie)) {
            return result;
        }

        Set<int[]> shops = unrented.get(movie);
        int count = 0;
        for (int[] shopInfo : shops) {
            if (count++ < 5) {
                result.add(shopInfo[0]);
            } else {
                break;
            }
        }
        return result;
    }

    public void rent(int shop, int movie) {
        int price = moviePrices.get(shop).get(movie);
        unrented.get(movie).remove(new int[]{shop, price});
        rented.add(new int[]{price, shop, movie});
    }

    public void drop(int shop, int movie) {
        int price = moviePrices.get(shop).get(movie);
        rented.remove(new int[]{price, shop, movie});
        unrented.get(movie).add(new int[]{shop, price});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        for (int[] rentalInfo : rented) {
            if (count++ < 5) {
                result.add(Arrays.asList(rentalInfo[1], rentalInfo[2]));
            } else {
                break;
            }
        }
        return result;
    }
}