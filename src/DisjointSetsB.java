import java.util.Arrays;

/****************************
*
* COMP251 template file
*
* Assignment 1, Question 2b
*
*****************************/

/* constructor: creates a partition of n elements. */
/* Each element is in a separate disjoint set */
public class DisjointSetsB {

    private int[] par;
    private int[] rank;

    /* constructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSetsB(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find representative of element i */
    public int find(int i) {

        /* Fill this method (The statement return 0 is here only to compile) */
        if (par[i] == i) {
            return i;
        }
        else {
            par[i] = find(par[i]);
            return par[i];
        }

    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {

        /* Fill this method (The statement return 0 is here only to compile) */
        if (find(i) == j) {
            return j;
        }
        if (find(j) == i) {
            return i;
        }
        int a = rank[find(i)];
        int b = rank[find(j)];
        if (a < b) {
            par[find(i)] =  find(j);
            return j;
        }
        else if  (a > b) {
            par[find(j)] =  find(i);
            return i;
        }
        else {
            par[find(i)] =  find(j);
            rank[find(j)]++;
            return j;
        }

    }

    /* move i to the set containing j */
    public void move(int i, int j) {
        int newRoot;
        int oldRoot = find(i);
        if (find(i) == find(j)) {
            return;
        }
        else {
           par[i] = i;

           for (int k = 0; k < par.length; k++) {
               if (find(k) == i && k != i) {
                   newRoot = k;
                   par[k] = k;
                   for  (int l = 0; l < par.length; l++) {
                       if (l != newRoot && l != i && find(l) == oldRoot || find(l) == i) {
                           par[l] = newRoot;
                       }
                   }
                   break;
               }
           }
           par[i] = find(j);
        }
    }


    /* return the sum of elements in the set of i */
    public int sum_elements(int i) {

        /* Fill this method */
        int root = find(i);
        int count = 0;
        for  (int k = 0; k < par.length; k++) {
            if (find(k) == root) {
                count += k;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        DisjointSetsB myset = new DisjointSetsB(7);
        System.out.println(myset);
        myset.move(1, 0);
        myset.move(2, 1);
        myset.move(4, 3);
        myset.move(5, 3);
        myset.union(3, 0);
        myset.move(3, 6);
        System.out.println(myset);
        System.out.println(Arrays.toString(myset.par));
        System.out.println(myset.sum_elements(0));
        System.out.println(myset.sum_elements(1));
        System.out.println(myset.sum_elements(2));
        System.out.println(myset.sum_elements(3));
        System.out.println(myset.sum_elements(4));
        System.out.println(myset.sum_elements(5));
    }

}
