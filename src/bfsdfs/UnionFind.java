package bfsdfs;

import java.util.Arrays;

/**
 * Created by poorvank.b on 10/06/17.
 */
public class UnionFind {

    private int count;
    public int[] size;
    public int[] parent;
    private int maxSize;

    public UnionFind(int count) {
        this.count = count;
        size = new int[count];
        parent = new int[count];
        for (int i=0;i<count;i++) {
            size[i] = 1;
            parent[i] = i;
        }
        maxSize = 0;
    }

    public UnionFind(int count,int[] size,int[] parent) {
        this.count = count;
        this.size = size;
        this.parent = parent;
    }

    public void print() {
        System.out.println("Parent Array - " + Arrays.toString(parent));
        System.out.println("Size Array - "+ Arrays.toString(size));
    }

    public int getSize(int p) {
        return size[p];
    }

    public boolean isConnected(int p,int q) {
        return find(p)==find(q);
    }

    public void reset(int p) {
        size[p]=1;
        parent[p]=p;
    }


    public int find(int p) {

        while (p!=parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public int getMaxSize() {
       return maxSize;
    }

    public int getCount() {
        return count;
    }

    public int union(int p,int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot==qRoot) {
            return size[pRoot];
        }

        count--;
        if(size[pRoot]> size[qRoot]) {
            parent[qRoot] = pRoot;
            size[pRoot]+=size[qRoot];
            maxSize = Math.max(size[pRoot],maxSize);
            return size[pRoot];
        } else {
            if(pRoot<qRoot) {
                parent[pRoot] = qRoot;
                size[qRoot]+=size[pRoot];
                maxSize = Math.max(size[qRoot],maxSize);
                return size[qRoot];
            } else {
                parent[qRoot] = pRoot;
                size[pRoot]+=size[qRoot];
                maxSize = Math.max(size[pRoot],maxSize);
                return size[pRoot];
            }
        }



    }

}

/*
 G I
 */