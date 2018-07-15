package leetcode;

// Say you have an array for which the ith element is the price of a given stock on day i.
//
// If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
// design an algorithm to find the maximum profit.
//
// Note that you cannot sell a stock before you buy one.
//
// Example 1:
//
// Input: [7,1,5,3,6,4]
// Output: 5
// Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
// Not 7-1 = 6, as selling price needs to be larger than buying price.
// Example 2:
//
// Input: [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems

import java.util.Arrays;

public class BestTimeToBuyAndSellStock {

    // dp idea,
    // T[i][k][0] indicate at ith day with at most k transactions and 0 share stock on hold
    // T[i][k][1] indicate at ith day with at most k transactions and 1 share stock on hold

    // Base cases:
    // T[-1][k][0] = 0, T[-1][k][1] = -Infinity // no stock
    // T[i][0][0] = 0, T[i][0][1] = -Infinity   // no transactions allowed
    //
    // Recurrence relations:
    // T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])     // at ith day, no stock on hold, it could be no stock on i-1 day or sell at ith day
    // T[i][k][1] = max(T[i-1][k][1], T[i-1][k-1][0] - prices[i])   // at ith day, 1 share hold, it could be buy a share of stock


    public int maxProfit(int[] prices) {
        // buy and sell at most once, k ==1
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE;

        for (int price : prices) {
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);   // here T_i00 is 0
        }

        return T_i10;
    }

    // 122. Best Time to Buy and Sell Stock II
    // You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
    public int maxProfit2(int[] prices) {
        // The intuitive solution is keep adding the sum of prices[i+1]-prices[i], if we found prices[i+1] > prices[i]
        // using the unified pattern, we can see the k is +infinity, so T[i][k-1][0] == T[i][k][0] and T[i][k-1][1] == T[i][k][1]
        // so all we need is save the previous T_ik0

        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int T_ik0_perv = T_ik0;

            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_perv - price);
        }

        return T_ik0;
    }

    // 123. Best Time to Buy and Sell Stock III
    // You may complete at most two transactions.
    public int maxProfit3(int[] prices) {
        // using the unified pattern
        // now we have 4 variable
        int T_i20 = 0, T_i21 = Integer.MIN_VALUE, T_i10 = 0, T_i11 = Integer.MIN_VALUE;

        for (int price : prices) {
            T_i20 = Math.max(T_i20, T_i21 + price);
            T_i21 = Math.max(T_i21, T_i10 - price);
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);
        }

        return T_i20;

    }


    // 188. Best Time to Buy and Sell Stock IV
    // You may complete at most k transactions.
    public int maxProfit4(int k, int[] prices) {
        // we can buy at ith day and sell at i+1 day
        // so we can perform at most n/2 transactions
        // if k >= n/2, then it is maxprofit2 problem
        int n = prices.length;
        if (k >= n / 2) {
            return maxProfit2(prices);
        }

        // dp array
        int[] T_ik0 = new int[k + 1];
        int[] T_ik1 = new int[k + 1];
        Arrays.fill(T_ik1, Integer.MIN_VALUE);

        for (int price : prices) {

            for (int j = k; j > 0; j--) {
                T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);

                T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
            }
        }

        return T_ik0[k];
    }

    // 309. Best Time to Buy and Sell Stock with Cooldown
    // You may complete as many transactions as you like
    // (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
    //
    // - You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    // - After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
    public int maxProfit5(int[] prices) {

        // buy[i] means before day i what is the maxProfit for any sequence end with buy.
        //
        // sell[i] means before day i what is the maxProfit for any sequence end with sell.
        //
        // rest[i] means before day i what is the maxProfit for any sequence end with rest.
        // the DP transition function is:
        // - buy[i] = max(buy[i-1], rest[i-1] - price)      // We have to "rest" before we "buy"
        // - sell[i] = max(sell[i-1], buy[i-1] + price)     // we have to "buy" before we "sell"
        // - rest[i] = max(rest[i-1], buy[i-1], sell[i-1])

        // but the sequence [buy rest buy] is possible here, so need to guarantee buy[i] <= rest[i]
        // so the transition function is:
        // - buy[i] = max(sell[i-2] - price, buy[i-1])
        // - sell[i] = max(sell[i-1], buy[i-1] + price)

        // use the unified pattern, we have:
        // T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + price)
        // T[i][k][1] = max(T[i-1][k][1], T[i-2][k-1][0] - price)

        int sell = 0;
        int prev_sell = 0;
        int buy = Integer.MIN_VALUE;
        int prev_buy = 0;

        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);

            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }

        return sell;


        // int n = prices.length;
        // if (n == 0) {
        //     return 0;
        // }
        //
        // int[] hold = new int[n + 1];
        // int[] sell = new int[n + 1];
        // hold[0] = -prices[0];
        //
        // for (int i = 1; i <= n; i++) {
        //
        //     if ( i >= 2) {
        //         hold[i] = Math.max(hold[i - 1], sell[i-2] - prices[i-1]);
        //     } else {
        //         hold[i] = hold[i-1];
        //     }
        //
        //     sell[i] = Math.max(sell[i-1], hold[i-1] + prices[i-1]);
        // }
        //
        // return sell[n];
    }

    // 714 Best Time to Buy and Sell Stock with Transaction Fee
    // You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
    // You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

    public int maxProfit6(int[] prices, int fee) {

        int n = prices.length;
        // int[] sold = new int[n + 1];
        // int[] hold = new int[n + 1];
        // hold[0] = -prices[0];
        //
        // for (int i = 1; i <= n; i++) {
        //
        //     sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i - 1] - fee);
        //
        //     hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i - 1]);
        // }
        //
        //

        int sold = 0;
        int hold = -prices[0];

        for (int price : prices) {

            int sold_prev = sold;
            sold = Math.max(sold, hold + price - fee);
            hold = Math.max(hold, sold_prev - price);
        }

        return sold;
    }
}
