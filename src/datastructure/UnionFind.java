package datastructure;


import java.util.Arrays;

/**
 * Integer array id[] of size N.
 * - Interpretation: id[i] is parent of i.
 * - Root of i is id[id[id[...id[i]...]]].
 *
 * time complexity:
 * Unit : O(logn)
 * Find: O(logn)
 */
public class UnionFind {

    private int[] id;
    private int[] size;

    public UnionFind(int N) {
        id = new int[N];
        size = new int[N];
        Arrays.fill(size, 1);

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // the id is in tree structure,
    // root = id[id[id[...id[i]...]]] keep searching until value doesn't change
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    // Check if p and q have the same root.
    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    // find the root of each p and q
    // update the id array,
    // set p's root value to q's root
    // try to balance the tree, by adding smaller size set below larger size tree
    public void unite(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (size[i] > size[j]) {
            id[i] = j;
            size[i] += size[j];
        } else {
            id[j] = i;
            size[j] += size[i];
        }
    }
}
