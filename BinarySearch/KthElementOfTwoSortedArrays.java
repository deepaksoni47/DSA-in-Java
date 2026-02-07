package BinarySearch;

public class KthElementOfTwoSortedArrays {
    public int kthElement(int a[], int b[], int k) {
        // code here
        // better case is O(logN) refer local written code for efficient solution
        int alen = a.length;
        int blen = b.length;
        int aptr = 0;
        int bptr = 0;
        int i = 1;
        while (aptr < alen && bptr < blen) {
            if (a[aptr] <= b[bptr]) {
                if (i == k)
                    return a[aptr];
                aptr++;
            } else {
                if (i == k)
                    return b[bptr];
                bptr++;
            }
            i++;
        }
        while (aptr < alen) {
            if (i == k)
                return a[aptr];
            aptr++;
            i++;
        }
        while (bptr < blen) {
            if (i == k)
                return b[bptr];
            bptr++;
            i++;
        }
        return -1;
    }

    public int kthElementEfficient(int a[], int b[], int k) {
        int alen = a.length;
        int blen = b.length;
        int aptr = 0;
        int bptr = 0;
        while (true) {
            if (aptr == alen) {
                return b[bptr + k - 1];
            }
            if (bptr == blen) {
                return a[aptr + k - 1];
            }
            if (k == 1) {
                return Math.min(a[aptr], b[bptr]);
            }
            int half = k / 2;
            int newAptr = Math.min(aptr + half, alen) - 1;
            int newBptr = Math.min(bptr + half, blen) - 1;
            if (a[newAptr] <= b[newBptr]) {
                k -= (newAptr - aptr + 1);
                aptr = newAptr + 1;
            } else {
                k -= (newBptr - bptr + 1);
                bptr = newBptr + 1;
            }
        }
    }
}
