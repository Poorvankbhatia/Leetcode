/*

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers,
output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).

 */

package hashing.easy;

import java.util.*;

/**
 * Created by poorvank on 28/05/17.
 */
public class MinIndexSum {

    public String[] findRestaurant(String[] list1, String[] list2) {


        if(list1.length==0 || list2.length==0) {
            return new String[0];
        }

        Map<String,Integer> map = new HashMap<>();

        for (int i=0;i<list1.length;i++) {
            map.put(list1[i],i);
        }

        int minValue = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();

        for (int i=0;i<list2.length;i++) {
            if(map.containsKey(list2[i])) {
                int sumIndex = i + map.get(list2[i]);
                if(minValue>sumIndex) {
                    minValue = sumIndex;
                }
            }
        }

        for (int i=0;i<list2.length;i++) {
            if(map.containsKey(list2[i])) {
                int sumIndex = i + map.get(list2[i]);
                if(sumIndex==minValue) {
                    list.add(list2[i]);
                }
            }
        }
        String[] result = new String[list.size()];

        for (int i=0;i<result.length;i++) {
            result[i] = list.get(i);
        }

        return result;

    }

    public static void main(String[] args) {
        String[] list1 = new String[]{"Shogun", "KFC", "Burger King", "dks"};
        String[] list2 = new String[]{"KFC", "Shogun", "Burger King"};
        System.out.println(Arrays.toString(new MinIndexSum().findRestaurant(list1,list2)));
    }

}
