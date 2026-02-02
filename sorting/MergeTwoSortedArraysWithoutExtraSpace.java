package sorting;

import java.util.Arrays;

public class MergeTwoSortedArraysWithoutExtraSpace {
    public void mergeArrays(int a[], int b[]) {
        int ap = a.length - 1;
        int bp = 0;

        while (ap >= 0 && bp < b.length && a[ap] > b[bp]) {
            int temp = a[ap];
            a[ap] = b[bp];
            b[bp] = temp;
            ap--;
            bp++;
        }

        Arrays.sort(a);
        Arrays.sort(b);
    }

    // better approach O((n+m) log(n+m)) time and O(1) space
    public void mergeArrays2(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int gap = nextGap(n + m);

        while (gap > 0) {

            int i = 0;
            int j = gap;

            while (j < n + m) {

                // i in a[], j in a[]
                if (i < n && j < n) {
                    if (a[i] > a[j]) {
                        swap(a, i, a, j);
                    }
                }
                // i in a[], j in b[]
                else if (i < n && j >= n) {
                    if (a[i] > b[j - n]) {
                        swap(a, i, b, j - n);
                    }
                }
                // i in b[], j in b[]
                else {
                    if (b[i - n] > b[j - n]) {
                        swap(b, i - n, b, j - n);
                    }
                }

                i++;
                j++;
            }

            gap = nextGap(gap);
        }
    }

    private int nextGap(int gap) {
        if (gap <= 1)
            return 0;
        return (gap / 2) + (gap % 2);
    }

    private void swap(int[] x, int i, int[] y, int j) {
        int temp = x[i];
        x[i] = y[j];
        y[j] = temp;
    }
}
