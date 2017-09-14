package moe.soulp.api_test.api;

/**
 * <b>取引所</b><br>
 * date: 2017/09/06 last_date: 2017/09/14
 * 
 * @author ソウルP
 * @version 1.0 2017/09/06 Exchangeable作成
 */
public interface Exchangeable {
    //Public API
    public String getBoard(); // 板情報

    public String getTicker(); // ティッカー

    public String getTrades(); // 全取引履歴

    public String getChats(); // チャット受信

    public String getRate(); // レート取得

    //Private API
    // 注文
    public String orderBuy(long rate, double amount); // 指値注文 現物取引 買い

    public String orderBuy(Pair pair, long rate, double amount); // 指値注文 現物取引 買い

    public String orderSell(long rate, double amount); // 指値注文 現物取引 売り

    public String orderSell(Pair pair, long rate, double amount); // 指値注文 現物取引 売り

    public String orderMarketBuy(double amount); // 成行注文 現物取引 買い

    public String orderMarketBuy(Pair pair, double amount); // 成行注文 現物取引 買い

    public String orderMarketSell(double amount); // 成行注文 現物取引 売り

    public String orderMarketSell(Pair pair, double amount); // 成行注文 現物取引 売り

    public String leverageBuy(double amount); // 成行注文 レバレッジ取引 買い

    public String leverageBuy(long rate, double amount); // 指値注文 レバレッジ取引 買い

    public String leverageSell(double amount); // 成行注文 レバレッジ取引 売り

    public String leverageSell(long rate, double amount); // 指値注文 レバレッジ取引 売り

    public String getOrdersOpens(); // 未決済の注文一覧

    public String deleteOrder(String id); // 注文のキャンセル

    public String deleteOrder(String id, Pair pair); // 注文のキャンセル

    public String deleteAllOrders(); // 全ての注文をキャンセル

    public String getOrdersTransactions(); // 取引履歴

    public String getPositions(); // ポジション一覧

    // アカウント
    public String getBalance(); // 残高

    public String getLeverageBalance(); // レバレッジアカウントの残高

    public String getSendCoins(); // 送金履歴

    public String getDepositCoins(); // 受け取り履歴

    // 入出金
    public String getBankAcccounts(); // 銀行口座一覧

    public String withdraw(long bank_account_id, long amount, Currency currency); // 出金

    public String getWithdraws(); // 出金履歴

    public String getDeposits(); // 入金履歴
}
