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

    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (buy < prices[i]) {
                profit = Math.max(prices[i] - buy, profit);
            } else {
                buy = prices[i];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        int prices[] = { 7, 1, 5, 3, 6, 4 };
        System.out.println(profit(prices));
    }
}
