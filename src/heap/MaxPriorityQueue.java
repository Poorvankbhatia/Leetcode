package heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 05/09/16.
 */
@SuppressWarnings("unchecked")
public class MaxPriorityQueue<Item extends Comparable<? super Item>> implements Iterable<Item> {

    private Item[] pq;
    private int size;

    public MaxPriorityQueue(Item[] temp) {
        int capacity = temp.length;
        pq = (Item[]) new Comparable[capacity+1];
        System.arraycopy(temp, 0, pq, 1, capacity);
        size = capacity;
        for (int i=size/2;i>=1;i--) {
            sink(i);
        }

    }

    public boolean isEmpty() {
        return size==0;
    }

    public Iterator<Item> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Item>{

        private MaxPriorityQueue<Item> copy;

        public HeapIterator() {
            copy = new MaxPriorityQueue<>(size);
            for(int i=1;i<=size;i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public Item next() {
            return copy.deleteMax();
        }

    }


    public MaxPriorityQueue(int capacity) {
        pq = (Item[]) new Comparable[capacity+1];
        size = 0;
    }

    private int swim(int k) {

        while (k>1 && isSmall(k/2,k)) {
            exchange(k,k/2);
            k = k/2;
        }

        return k;

    }

    public int insert(Item item) {
        pq[++size] = item;
        return swim(size);
    }

    private void exchange(int i,int j) {
        Item temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean isSmall(int i,int j) {
        return pq[i].compareTo(pq[j])<0;
    }

    public void replaceRoot(Item k) {
        pq[1] = k;
        sink(1);
    }

    public Item getMaximumElement() {
        return pq[1];
    }

    public Item deleteMax() {

        Item temp = pq[1];
        pq[1]=pq[size];
        pq[size] = null;
        size--;
        sink(1);

        return temp;
    }

    private void sink(int k) {

        while ((2*k)<=size) {
            int j = (2*k);
            if(j<size && isSmall(j,j+1)) {
                j++;
            }
            if(isSmall(j,k)) {
                break;
            }
            exchange(k,j);
            k = j;
        }

    }

}
