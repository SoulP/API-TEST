package moe.soulp.api_test.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <b>インターフェース coincheck</b><br>
 * date: 2017/08/03 last_date: 2017/08/22
 * 
 * @author ソウルP
 * @version 1.0 2017/08/03 Coincheckable作成
 * @see Pair 取引ペア
 * @see Type 種類
 */
public interface Coincheckable {
    DateTimeFormatter FORMAT                           = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // Public API
    String            API                              = "https://coincheck.com";                                  // API接続先
    String            TICKER                           = "/api/ticker";                                            // ティッカー
    String            TRADES                           = "/api/trades";                                            // 全取引履歴
    String            ORDER_BOOKS                      = "/api/order_books";                                       // 板情報
    String            ORDERS_RATE                      = "/api/exchange/orders/rate";                              // レート取得
    String            RATE                             = "/api/rate/";                                             // 販売レート取得

    // Private API
    // 注文
    String            ORDERS                           = "/api/exchange/orders";                                   // 新規注文
    String            ORDERS_OPENS                     = "/api/exchange/orders/opens";                             // 未決済の注文一覧
    String            ORDERS_ID                        = "/api/exchange/orders/";                                  // 注文のキャンセル
    String            ORDERS_TRANSACTIONS              = "/api/exchange/orders/transactions";                      // 取引履歴
    String            ORDERS_TRANSACTIONS_PAGINATION   = "/api/exchange/orders/transactions_pagination";           // 取引履歴（ページネーション）
    String            POSITIONS                        = "/api/exchange/leverage/positions";                       // ポジション一覧
    // アカウント
    String            ACCOUNTS_BALANCE                 = "/api/accounts/balance";                                  // 残高
    String            ACCOUNTS_LEVERAGE_BALANCE        = "/api/accounts/leverage_balance";                         // レバレッジアカウントの残高
    String            SEND_MONEY                       = "/api/send_money";                                        // (GET)ビットコインの送金履歴、(POST)ビットコインの送金
    String            DEPOSIT_MONEY                    = "/api/deposit_money";                                     // ビットコインの受け取り履歴
    String            DEPOSIT_MONEY_ID_FAST            = "/api/deposit_money/[id]/fast";                           // ビットコインの高速入金
    String            ACCOUNTS                         = "/api/accounts";                                          // アカウント情報
    // 日本円出金
    String            BANK_ACCOUNTS                    = "/api/bank_accounts";                                     // (GET)銀行口座の一覧、(POST)銀行口座の登録、(DELETE)銀行口座の削除
    String            WITHDRAWS                        = "/api/withdraws";                                         // (GET)出金履歴、(POST)出金申請の作成、(DELETE)出金申請のキャンセル
    // 信用取引
    String            LENDING_BORROWS                  = "/api/lending/borrows";                                   // (POST)借入申請
    String            LENDING_BORROWS_MATCHES          = "/api/lending/borrows/matches";                           // 借入中一覧
    String            LENDING_BORROWS_ID_REPAY         = "/api/lending/borrows/[id]/repay";                        // 返済
    // 振替
    String            EXCHANGE_TRANSFERS_TO_LEVERAGE   = "/api/exchange/transfers/to_leverage";                    // レバレッジアカウントへ振替
    String            EXCHANGE_TRANSFERS_FROM_LEVERAGE = "/api/exchange/transfers/from_leverage";                  // レバレッジアカウントから振替
    // チャット
    String            CHAT_RECEIVE                     = "/ja/chats/list";                                         // チャット受信

    // Public API
    public String getTicker(); // ティッカー

    public String getTrades(); // 全取引履歴

    public String getTrades(int offset); // 全取引履歴

    public String getOrderBooks(); // 板情報

    public String getOrdersRate_amount(Type order_type, Pair pair, double amount); // レート取得

    public String getOrdersRate_price(Type order_type, Pair pair, double price); // レート取得

    public String getRate(Pair pair); // 販売レート取得

    // Private API
    // 注文
    public String postOrderBuy(Pair pair, double rate, double amount); // 指値注文 現物取引 買い

    public String postOrderSell(Pair pair, double rate, double amount); // 指値注文 現物取引 売り

    public String postOrderMarketBuy(Pair pair, double market_buy_amount); // 成行注文 現物取引 買い

    public String postOrderMarketSell(Pair pair, double amount); // 成行注文 現物取引 売り

    public String postOrderLeverageBuy(Pair pair, double amount); // 成行注文 レバレッジ取引新規 買い

    public String postOrderLeverageBuy(Pair pair, double amount, double rate); // 指値注文 レバレッジ取引新規 買い

    public String postOrderLeverageSell(Pair pair, double amount); // 成行注文 レバレッジ取引新規 売り

    public String postOrderLeverageSell(Pair pair, double amount, double rate); // 指値注文 レバレッジ取引新規 売り

    public String postOrderCloseLong(Pair pair, double amount, long position_id); // 成行注文 レバレッジ取引決済 売り

    public String postOrderCloseLong(Pair pair, double amount, long position_id, double rate); // 指値注文 レバレッジ取引決済 売り

    public String postOrderCloseShort(Pair pair, double amount, long position_id); // 成行注文 レバレッジ取引決済 買い

    public String postOrderCloseShort(Pair pair, double amount, long position_id, double rate); // 指値注文 レバレッジ取引決済 買い

    public String getOrdersOpens(); // 未決済の注文一覧

    public String deleteOrdersId(long id); // 注文のキャンセル

    public String getOrdersTransactions(); // 取引履歴

    public String getOrdersTransactionsPagination(); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(boolean order); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(long starting_after); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit, boolean order); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit, long starting_after); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(boolean order, long starting_after); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit, boolean order, long starting_after); // 取引履歴（ページネーション）

    public String getPositions(); // ポジション一覧

    public String getPositions(boolean status); // ポジション一覧

    // アカウント
    public String getAccounts(); // アカウント情報取得

    /**
     * <b>日時 データ・タイプ変換</b><br>
     * String -> ZonedDateTime
     * 
     * @param date
     *            日時 "yyyy-MM-dd'T'HH:mm:ss.SSSX"<br>
     *            例え： "2017-08-24T22:33:44.000Z"
     * @return ZonedDateTime
     */
    public static ZonedDateTime string2zonedDateTime(String date) {
        if (date == null || date.equals("")) return null;
        else return ZonedDateTime.parse(date, Coincheckable.FORMAT).withZoneSameInstant(ZoneId.systemDefault());
    }
}
