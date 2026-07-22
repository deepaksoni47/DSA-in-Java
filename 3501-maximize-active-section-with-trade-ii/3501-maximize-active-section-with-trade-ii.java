class Solution {

    static void buildSegmentTree(int i, int l, int r, int[] segmentTree, int[] arr) {
        if (l == r) {
            segmentTree[i] = arr[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * i + 1, l, mid, segmentTree, arr);
        buildSegmentTree(2 * i + 2, mid + 1, r, segmentTree, arr);

        segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
    }

    static int[] constructST(int[] arr, int n) {
        int[] segmentTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1, segmentTree, arr);
        return segmentTree;
    }

    static int querySegmentTree(int start, int end, int i, int l, int r, int[] segmentTree) {
        if (l > end || r < start) {
            return Integer.MIN_VALUE;
        }

        if (l >= start && r <= end) {
            return segmentTree[i];
        }

        int mid = l + (r - l) / 2;

        return Math.max(
                querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree),
                querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree)
        );
    }

    static int RMQ(int[] st, int n, int a, int b) {
        return querySegmentTree(a, b, 0, 0, n - 1, st);
    }

    static int lowerBound(int[] arr, int len, int key) {
        int lo = 0, hi = len;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < key) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    static int upperBound(int[] arr, int len, int key) {
        int lo = 0, hi = len;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= key) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int n = s.length();

        int activeCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                activeCount++;
            }
        }

        int[] blockStart = new int[n];
        int[] blockEnd = new int[n];

        int m = 0;
        int i = 0;

        while (i < n) {

            if (s.charAt(i) == '0') {

                int start = i;

                while (i < n && s.charAt(i) == '0') {
                    i++;
                }

                blockStart[m] = start;
                blockEnd[m] = i - 1;
                m++;

            } else {
                i++;
            }
        }

        if (m < 2) {

            List<Integer> ans = new ArrayList<>();

            for (int k = 0; k < queries.length; k++) {
                ans.add(activeCount);
            }

            return ans;
        }

        int[] blockSize = new int[m];

        for (int k = 0; k < m; k++) {
            blockSize[k] = blockEnd[k] - blockStart[k] + 1;
        }

        int pairCount = m - 1;
        int[] pairSum = new int[pairCount];

        for (int k = 0; k < pairCount; k++) {
            pairSum[k] = blockSize[k] + blockSize[k + 1];
        }

        int[] segmentTree = constructST(pairSum, pairCount);

        List<Integer> result = new ArrayList<>();

        for (int[] query : queries) {

            int left = query[0];
            int right = query[1];

            int firstBlock = lowerBound(blockEnd, m, left);
            int lastBlock = upperBound(blockStart, m, right) - 1;

            int maxGain = 0;

            if (firstBlock < lastBlock) {

                int firstLength = blockEnd[firstBlock] - Math.max(blockStart[firstBlock], left) + 1;
                int lastLength = Math.min(blockEnd[lastBlock], right) - blockStart[lastBlock] + 1;

                if (lastBlock - firstBlock == 1) {

                    maxGain = firstLength + lastLength;

                } else {

                    int option1 = firstLength + blockSize[firstBlock + 1];
                    int option2 = blockSize[lastBlock - 1] + lastLength;

                    int option3 = 0;

                    if (firstBlock + 1 <= lastBlock - 2) {
                        option3 = RMQ(segmentTree, pairCount, firstBlock + 1, lastBlock - 2);
                    }

                    maxGain = Math.max(option1, Math.max(option2, option3));
                }
            }

            result.add(activeCount + maxGain);
        }

        return result;
    }
}