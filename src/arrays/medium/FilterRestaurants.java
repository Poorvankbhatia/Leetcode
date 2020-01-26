package arrays.medium;

import java.util.*;

public class FilterRestaurants {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> indexIDMap = new HashMap<>();
        for (int i=0;i<restaurants.length;i++) {
            if(restaurants[i][2]>=veganFriendly && restaurants[i][3]<=maxPrice && restaurants[i][4]<=maxDistance) {
                list.add(restaurants[i][0]);
                indexIDMap.put(restaurants[i][0],i);
            }
        }
        list.sort((o1, o2) -> {
            int compare = Integer.compare(restaurants[indexIDMap.get(o2)][1], restaurants[indexIDMap.get(o1)][1]);
            if (compare == 0) {
                return Integer.compare(restaurants[indexIDMap.get(o2)][0], restaurants[indexIDMap.get(o1)][0]);
            }
            return compare;
        });
        return list;
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1,4,1,40,10},
                {2,8,0,50,5},
                {3,8,1,30,4},
                {4,10,0,10,3},
                {5,1,1,15,1}
        };
        System.out.println(new FilterRestaurants().filterRestaurants(a,0,50,10));
    }

}
