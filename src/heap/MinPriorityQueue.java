package heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 05/09/16.
 */
public class MinPriorityQueue<Item extends Comparable<? super Item>> implements Iterable<Item> {


    private Item[] pq;
    private int size;

    public MinPriorityQueue(Item[] temp) {
        int capacity =temp.length;
        size = capacity;
        pq = (Item[]) new Comparable[capacity+1];
        for(int i=0;i<capacity;i++) {
            pq[i+1] = temp[i];
        }
        for(int k=size/2;k>=1;k--) {
            sink(k);
        }
    }

    public MinPriorityQueue(int capacity) {
        pq = (Item[]) new Comparable[capacity+1];
        size = 0;
    }

    public MinPriorityQueue() {
        this(1);
    }

    public void reArrangeItems() {
        for(int k=size/2;k>=1;k--) {
            sink(k);
        }
    }

    public boolean isEmpty() {
        return size==0;
    }

    private void exchange(int i,int j) {
        Item temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean isSmall(int i,int j) {
        return pq[i].compareTo(pq[j])<0;
    }

    private int swim(int k) {

        while (k>1 && isSmall(k,k/2)) {
            exchange(k,k/2);
            k=k/2;
        }

        return k;

    }

    private void sink(int k) {

        while ((2*k)<=size) {

            int j= (2*k);
            if(j<size && isSmall(j+1,j)){
                j++;
            }
            if(isSmall(k,j)) {
                break;
            }
            exchange(k,j);
            k=j;

        }

    }

    public int getSize() {
        return size;
    }

    private void resize(int capacity) {
        if(capacity>size) {
            Item[] temp = (Item[]) new Comparable[capacity];
            for(int i=1;i<=size;i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }
    }

    public int insert(Item item){
        if(getSize()>=pq.length-1) {
            resize(2*pq.length);
        }
        pq[++size] = item;
        return swim(size);
    }

    public Item getMinimumElement() {
        return pq[1];
    }

    public void replaceRoot(Item item) {
        pq[1] = item;
        sink(1);
    }

    public Iterator<Item> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Item> {

        private MinPriorityQueue<Item> copy;

        public HeapIterator() {
            copy = new MinPriorityQueue<>(size);
            for(int i=1;i<=size;i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public Item next() {
            return copy.deleteMin();
        }

    }

    public Item deleteMin() {
        Item item = pq[1];
        pq[1] = pq[size];
        pq[size] = null;
        size--;
        sink(1);
        return item;
    }

}
