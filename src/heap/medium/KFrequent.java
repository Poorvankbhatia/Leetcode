package heap.medium;

import heap.MinPriorityQueue;

import java.util.*;

/**
 * Created by poorvank on 05/09/16.
 */
public class KFrequent {

    private class Num implements Comparable<Num> {
        private int value;
        private int frequency;

        public Num(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        public int compareTo(Num num) {
            if(this.frequency>num.frequency) {
                return 1;
            } else if(this.frequency<num.frequency) {
                return -1;
            } else {
                return 0;
            }
        }

        public String toString() {
            return value+"~"+frequency;
        }
    }

    private HashMap<Integer,Num> frequencyMap = new HashMap<>();

    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();
        List<Num> list = new ArrayList<>();
        for (int number : nums) {
            if (frequencyMap.containsKey(number)) {
                Num n = frequencyMap.get(number);
                n.frequency++;
                frequencyMap.put(number, n);
            } else {
                Num n = new Num(number,1);
                frequencyMap.put(number, n);
                list.add(n);
            }
        }


        System.out.println(frequencyMap);

        MinPriorityQueue<Num> minPriorityQueue = new MinPriorityQueue<>(k);

        for (int i=0;i<k && i<list.size();i++) {
            minPriorityQueue.insert(list.get(i));
        }


        for (int i=k;i<list.size();i++) {
            if(list.get(i).frequency>minPriorityQueue.getMinimumElement().frequency) {
                minPriorityQueue.replaceRoot(list.get(i));
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (Num n : minPriorityQueue) {
            stack.push(n.value);
        }

        while (!stack.empty()) {
            result.add(stack.pop());
        }

        return result;

    }

    public static void main(String[] args) {
        KFrequent kf = new KFrequent();
        int[] nums = new int[]{5,2,5,3,5,3,1,1,3};
        System.out.println(kf.topKFrequent(nums,2));
    }

}
