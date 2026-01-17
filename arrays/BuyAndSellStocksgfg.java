// User function Template for Java
class BuyAndSellStocksgfg {
    public int maximumProfit(int prices[]) {
        int bp = prices[0];
        int sp = 0;
        int mp = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                sp = prices[i - 1];
                int profit = sp - bp;
                mp = mp + profit;
                bp = prices[i];
            } else if (i == prices.length - 1) {
                sp = prices[i];
                int profit = sp - bp;
                mp = mp + profit;
            }
        }
        return mp;
    }
}