public class BuyAndSellStocks {
    public static int profit(int prices[]) {
        int max = 0;
        int bp = Integer.MAX_VALUE;
        for (int sp : prices) {
            if (bp < sp) {
                int profit = sp - bp;
                max = Math.max(max, profit);
            } else {
                bp = sp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int prices[] = { 7, 1, 5, 3, 6, 4 };
        System.out.println(profit(prices));
    }
}
