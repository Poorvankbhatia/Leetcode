/*

You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region X contains another region Y then X is bigger than Y.

Given two regions region1, region2, find out the smallest region that contains both of them.

If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.

It's guaranteed the smallest region exists.



Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"


Constraints:

2 <= regions.length <= 10^4
region1 != region2
All strings consist of English letters and spaces with at most 20 letters.

 */
package tree.medium;

import java.util.*;

public class SmallestCommonMedium {

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> parents = new HashMap<>();
        Set<String> ancestryHistory = new HashSet<>();
        for (List<String> region : regions) {
            for (int i = 1; i < region.size(); ++i) {
                parents.put(region.get(i), region.get(0));
            }
        }
        while (region1 != null) {
            ancestryHistory.add(region1);
            region1 = parents.get(region1);
        }
        while (!ancestryHistory.contains(region2)) {
            region2 = parents.get(region2);
        }
        return region2;
    }

}
