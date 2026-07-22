// class Solution {

//     static void buildSegmentTree(int i, int l, int r, int[] segmentTree, int[] arr) {
//         if (l == r) {
//             segmentTree[i] = arr[l];
//             return;
//         }

//         int mid = l + (r - l) / 2;
//         buildSegmentTree(2 * i + 1, l, mid, segmentTree, arr);
//         buildSegmentTree(2 * i + 2, mid + 1, r, segmentTree, arr);

//         segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
//     }

//     static int[] constructST(int[] arr, int n) {
//         int[] segmentTree = new int[4 * n];
//         buildSegmentTree(0, 0, n - 1, segmentTree, arr);
//         return segmentTree;
//     }

//     static int querySegmentTree(int start, int end, int i, int l, int r, int[] segmentTree) {
//         if (l > end || r < start) {
//             return Integer.MIN_VALUE;
//         }

//         if (l >= start && r <= end) {
//             return segmentTree[i];
//         }

//         int mid = l + (r - l) / 2;

//         return Math.max(
//                 querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree),
//                 querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree)
//         );
//     }

//     static int RMQ(int[] st, int n, int a, int b) {
//         return querySegmentTree(a, b, 0, 0, n - 1, st);
//     }

//     static int lowerBound(int[] arr, int len, int key) {
//         int lo = 0, hi = len;

//         while (lo < hi) {
//             int mid = lo + (hi - lo) / 2;

//             if (arr[mid] < key) {
//                 lo = mid + 1;
//             } else {
//                 hi = mid;
//             }
//         }

//         return lo;
//     }

//     static int upperBound(int[] arr, int len, int key) {
//         int lo = 0, hi = len;

//         while (lo < hi) {
//             int mid = lo + (hi - lo) / 2;

//             if (arr[mid] <= key) {
//                 lo = mid + 1;
//             } else {
//                 hi = mid;
//             }
//         }

//         return lo;
//     }

//     public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

//         int n = s.length();

//         int activeCount = 0;
//         for (int i = 0; i < n; i++) {
//             if (s.charAt(i) == '1') {
//                 activeCount++;
//             }
//         }

//         int[] blockStart = new int[n];
//         int[] blockEnd = new int[n];

//         int m = 0;
//         int i = 0;

//         while (i < n) {

//             if (s.charAt(i) == '0') {

//                 int start = i;

//                 while (i < n && s.charAt(i) == '0') {
//                     i++;
//                 }

//                 blockStart[m] = start;
//                 blockEnd[m] = i - 1;
//                 m++;

//             } else {
//                 i++;
//             }
//         }

//         if (m < 2) {

//             List<Integer> ans = new ArrayList<>();

//             for (int k = 0; k < queries.length; k++) {
//                 ans.add(activeCount);
//             }

//             return ans;
//         }

//         int[] blockSize = new int[m];

//         for (int k = 0; k < m; k++) {
//             blockSize[k] = blockEnd[k] - blockStart[k] + 1;
//         }

//         int pairCount = m - 1;
//         int[] pairSum = new int[pairCount];

//         for (int k = 0; k < pairCount; k++) {
//             pairSum[k] = blockSize[k] + blockSize[k + 1];
//         }

//         int[] segmentTree = constructST(pairSum, pairCount);

//         List<Integer> result = new ArrayList<>();

//         for (int[] query : queries) {

//             int left = query[0];
//             int right = query[1];

//             int firstBlock = lowerBound(blockEnd, m, left);
//             int lastBlock = upperBound(blockStart, m, right) - 1;

//             int maxGain = 0;

//             if (firstBlock < lastBlock) {

//                 int firstLength = blockEnd[firstBlock] - Math.max(blockStart[firstBlock], left) + 1;
//                 int lastLength = Math.min(blockEnd[lastBlock], right) - blockStart[lastBlock] + 1;

//                 if (lastBlock - firstBlock == 1) {

//                     maxGain = firstLength + lastLength;

//                 } else {

//                     int option1 = firstLength + blockSize[firstBlock + 1];
//                     int option2 = blockSize[lastBlock - 1] + lastLength;

//                     int option3 = 0;

//                     if (firstBlock + 1 <= lastBlock - 2) {
//                         option3 = RMQ(segmentTree, pairCount, firstBlock + 1, lastBlock - 2);
//                     }

//                     maxGain = Math.max(option1, Math.max(option2, option3));
//                 }
//             }

//             result.add(activeCount + maxGain);
//         }

//         return result;
//     }
// }
//above was segment tree aprroach below is sparse table approach
import java.util.regex.*;


class Solution {
    private int[] zs, ze, V;
    private int nblocks;
    private List<int[]> sparse;

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int ones = (int) s.chars().filter(c -> c == '1').count();

        // maximal zero-blocks (inclusive ends), split into starts / ends
        List<Integer> zsL = new ArrayList<>(), zeL = new ArrayList<>();
        Matcher mo = Pattern.compile("0+").matcher(s);
        while (mo.find()) { zsL.add(mo.start()); zeL.add(mo.end() - 1); }
        zs = zsL.stream().mapToInt(Integer::intValue).toArray();
        ze = zeL.stream().mapToInt(Integer::intValue).toArray();
        nblocks = zs.length;

        // valley j: full value = sum of the two adjacent block lengths
        V = IntStream.range(0, nblocks - 1)
                     .map(j -> (ze[j] - zs[j] + 1) + (ze[j + 1] - zs[j + 1] + 1))
                     .toArray();

        // sparse table for range-max over V
        int nv = V.length;
        sparse = new ArrayList<>();
        sparse.add(V);
        for (int half = 1; half * 2 <= nv; half *= 2) {
            int[] prev = sparse.get(sparse.size() - 1);
            int[] next = new int[prev.length - half];
            for (int i = 0; i < next.length; i++)
                next[i] = Math.max(prev[i], prev[i + half]);
            sparse.add(next);
        }

        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) ans.add(ones + gain(q[0], q[1]));
        return ans;
    }

    private int rmq(int lo, int hi) {                 // inclusive max over V[lo..hi]
        int t = 31 - Integer.numberOfLeadingZeros(hi - lo + 1);
        return Math.max(sparse.get(t)[lo], sparse.get(t)[hi - (1 << t) + 1]);
    }

    private int clip(int j, int l, int r) {           // valley j's gain, clipped to [l, r]
        return V[j] - Math.max(0, l - zs[j]) - Math.max(0, ze[j + 1] - r);
    }

    private int gain(int l, int r) {
        if (nblocks < 2) return 0;
        int ja = lowerBound(ze, l);                   // first usable valley: left block ends >= l
        int jb = upperBound(zs, r) - 2;               // last  usable valley: right block starts <= r
        if (ja > jb) return 0;
        return Math.max(Math.max(clip(ja, l, r), clip(jb, l, r)),
                        jb - ja >= 2 ? rmq(ja + 1, jb - 1) : 0);
    }

    // bisect_left / bisect_right equivalents
    private static int lowerBound(int[] a, int x) {
        int lo = 0, hi = a.length;
        while (lo < hi) { int mid = (lo + hi) >>> 1; if (a[mid] < x) lo = mid + 1; else hi = mid; }
        return lo;
    }
    private static int upperBound(int[] a, int x) {
        int lo = 0, hi = a.length;
        while (lo < hi) { int mid = (lo + hi) >>> 1; if (a[mid] <= x) lo = mid + 1; else hi = mid; }
        return lo;
    }
}