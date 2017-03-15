/*

Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is
linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

 */

package design.hard;

import java.util.*;

/**
 * Created by poorvank.b on 14/03/17.
 */
public class RandomizedCollection {

    private Map<Integer,LinkedHashSet<Integer>> map;
    private List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) {
            LinkedHashSet<Integer> set = map.get(val);
            set.add(list.size());
            list.add(val);
            map.put(val,set);
            return false;
        } else {
            LinkedHashSet<Integer> set = new LinkedHashSet<>();
            set.add(list.size());
            list.add(val);
            map.put(val,set);
            return true;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        } else {
            LinkedHashSet<Integer> set = map.get(val);
            int indexToBeReplaced = set.iterator().next();
            int lastVal = list.get(list.size() - 1);
            list.set(indexToBeReplaced, lastVal);
            if(set.size()==1) {
                map.remove(val);
                // We need to update the positions of the different last element
                if(indexToBeReplaced!=list.size()-1) {
                    LinkedHashSet<Integer> lastElementSet = map.get(lastVal);
                    lastElementSet.remove(list.size()-1);
                    lastElementSet.add(indexToBeReplaced);
                    map.put(lastVal,lastElementSet);
                }
                list.remove(list.size()-1);

            }
            else {
                if(lastVal==val) {
                    set.remove(list.size()-1);
                } else {
                    //Updating positions of last element as above
                    LinkedHashSet<Integer> lastElementSet = map.get(lastVal);
                    lastElementSet.remove(list.size()-1);
                    lastElementSet.add(indexToBeReplaced);
                    map.put(lastVal,lastElementSet);
                    set.remove(indexToBeReplaced);
                }
                map.put(val,set);
                list.remove(list.size()-1);
            }
            return true;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();

        System.out.println(randomizedCollection.insert(9));
        System.out.println(randomizedCollection.insert(9));
        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.insert(2));
        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.remove(2));
        System.out.println(randomizedCollection.remove(1));
        System.out.println(randomizedCollection.remove(1));
        System.out.println(randomizedCollection.insert(9));
        System.out.println(randomizedCollection.remove(1));
    }

}

/*

Same as Randomized Set problem we are just using Linked Hash set

 */