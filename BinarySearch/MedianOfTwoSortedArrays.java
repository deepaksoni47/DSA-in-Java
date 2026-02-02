package BinarySearch;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int l = 0;
        int r = m;
        while (l <= r) {
            int Px = l + (r - l) / 2;
            int Py = (m + n + 1) / 2 - Px;
            int x1 = (Px == 0) ? Integer.MIN_VALUE : nums1[Px - 1];
            int x2 = (Py == 0) ? Integer.MIN_VALUE : nums2[Py - 1];

            int x3 = (Px == m) ? Integer.MAX_VALUE : nums1[Px];
            int x4 = (Py == n) ? Integer.MAX_VALUE : nums2[Py];

            if (x1 <= x4 && x2 <= x3) {
                if ((m + n) % 2 == 1) {
                    return Math.max(x1, x2);
                } else {
                    return ((Math.max(x1, x2) + Math.min(x3, x4)) / 2.0);
                }
            }
            if (x1 > x4) {
                r = Px - 1;// reduce elements in array 1
            } else {
                l = Px + 1;
            }
        }
        return -1;
    }

    // brute force approach O((n+m) time and O(1) space
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int merged[] = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < n) {
            merged[k++] = nums1[i++];
        }
        while (j < m) {
            merged[k++] = nums2[j++];
        }
        int totalLength = n + m;
        if (totalLength % 2 == 0) {
            return (merged[(totalLength / 2) - 1] + merged[totalLength / 2]) / 2.0;
        } else {
            return merged[totalLength / 2];
        }
    }
}
