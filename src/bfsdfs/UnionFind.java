package bfsdfs;

import java.util.Arrays;

/**
 * Created by poorvank.b on 10/06/17.
 */
public class UnionFind {

    public int count;
    public int[] size;
    public int[] parent;

    public UnionFind(int count) {
        this.count = count;
        size = new int[count];
        parent = new int[count];
        for (int i=0;i<count;i++) {
            size[i] = 1;
            parent[i] = i;
        }
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


    public int find(int p) {

        while (p!=parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
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
            return size[pRoot];
        } else {
            parent[pRoot] = qRoot;
            size[qRoot]+=size[pRoot];
            return size[qRoot];
        }

    }

}

/*
 G I
 */