package moe.soulp.api_test.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <b>coincheck</b><br>
 * date: 2017/08/03 last_date: 2017/09/07
 *
 * @author ソウルP
 * @version 1.0 2017/08/03 Coincheckable作成
 * @version 1.1 2017/09/06 Exchangeable継承
 * @see Exchangeable 取引所
 * @see Pair 取引ペア
 * @see Type 種類
 * @see Sort ソート
 * @see Status 状態
 * @see Currency 通貨
 */
public interface Coincheckable extends Exchangeable {
    DateTimeFormatter FORMAT                           = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // Base API
    String            API                              = "https://coincheck.com";                                  // API接続先

    // Public API
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
    public String getTrades(int offset); // 全取引履歴

    public String getOrdersRate_amount(Type order_type, Pair pair, double amount); // レート取得

    public String getOrdersRate_price(Type order_type, Pair pair, double price); // レート取得

    public String getRate(Pair pair); // 販売レート取得

    public String getChat(long since_id); // チャット受信

    // Private API
    // 注文
    public String leverageBuy(Pair pair, double amount); // 成行注文 レバレッジ取引新規 買い

    public String leverageBuy(Pair pair, double amount, double rate); // 指値注文 レバレッジ取引新規 買い

    public String leverageSell(Pair pair, double amount); // 成行注文 レバレッジ取引新規 売り

    public String leverageSell(Pair pair, double amount, double rate); // 指値注文 レバレッジ取引新規 売り

    public String closeLong(double amount, long position_id); // 成行注文 レバレッジ取引決済 売り

    public String closeLong(Pair pair, double amount, long position_id); // 成行注文 レバレッジ取引決済 売り

    public String closeLong(double amount, long position_id, double rate); // 指値注文 レバレッジ取引決済 売り

    public String closeLong(Pair pair, double amount, long position_id, double rate); // 指値注文 レバレッジ取引決済 売り

    public String closeShort(double amount, long position_id); // 成行注文 レバレッジ取引決済 買い

    public String closeShort(Pair pair, double amount, long position_id); // 成行注文 レバレッジ取引決済 買い

    public String closeShort(double amount, long position_id, double rate); // 指値注文 レバレッジ取引決済 買い

    public String closeShort(Pair pair, double amount, long position_id, double rate); // 指値注文 レバレッジ取引決済 買い

    public String getOrdersTransactionsPagination(); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(Sort order); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(long starting_after); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit, Sort order); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit, long starting_after); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(Sort order, long starting_after); // 取引履歴（ページネーション）

    public String getOrdersTransactionsPagination(int limit, Sort order, long starting_after); // 取引履歴（ページネーション）

    public String getPositions(Status status); // ポジション一覧

    public String getPositions(int limit); // ポジション一覧

    public String getPositions(Sort order); // ポジション一覧

    public String getPositions(long starting_after); // ポジション一覧

    public String getPositions(Status status, int limit); // ポジション一覧

    public String getPositions(Status status, Sort order); // ポジション一覧

    public String getPositions(Status status, long starting_after); // ポジション一覧

    public String getPositions(Status status, int limit, Sort order); // ポジション一覧

    public String getPositions(Status status, int limit, long starting_after); // ポジション一覧

    public String getPositions(Status status, Sort order, long starting_after); // ポジション一覧

    public String getPositions(Status status, int limit, Sort order, long starting_after); // ポジション一覧

    public String getPositions(int limit, Sort order); // ポジション一覧

    public String getPositions(int limit, long starting_after); // ポジション一覧

    public String getPositions(int limit, Sort order, long starting_after); // ポジション一覧

    public String getPositions(Sort order, long starting_after); // ポジション一覧

    // アカウント

    public String sendMoney(String address, double amount); // ビットコインの送金

    public String getSendCoin(Currency currency); // 送金履歴

    public String getDepositCoin(Currency currency); // 受け取り履歴

    public String depositMoneyFast(long id); // 高速入金

    public String getAccounts(); // アカウント情報取得

    // 入出金
    public String addBankAccount(String bank_name, String branch_name, Type bank_account_type, String number,
            String name); // 銀行口座の登録

    public String deleteBankAccount(long id); // 銀行口座の削除

    public String getWithdraws(int limit); // 出金履歴

    public String getWithdraws(Sort order); // 出金履歴

    public String getWithdraws(long starting_after); // 出金履歴

    public String getWithdraws(int limit, Sort order); // 出金履歴

    public String getWithdraws(int limit, long starting_after); // 出金履歴

    public String getWithdraws(int limit, Sort order, long starting_after); // 出金履歴

    public String getWithdraws(Sort order, long starting_after); // 出金履歴

    public String deleteWithdraw(long id); // 出金申請のキャンセル

    // 信用取引
    public String lendingBorrow(double amount, Currency currency); // 借入申請

    public String getLendingBorrowsMatches(); // 借入中一覧

    public String lendingBorrowRepay(long id); // 返済

    // 為替
    public String toLeverage(Currency currency, double amount); // レバレッジアカウントへ振替

    public String fromLeverage(Currency currency, double amount); // レバレッジアカウントから振替

    /**
     * <b>日時 データ・タイプ変換</b><br>
     * String(UTC) -> ZonedDateTime(システムのタイムゾーン)
     *
     * @param date
     *            日時 "yyyy-MM-dd'T'HH:mm:ss.SSSX"<br>
     *            例え： "2017-08-24T22:33:44.000Z"
     * @return ZonedDateTime
     */
    public static ZonedDateTime string2zonedDateTime(String date) {
        if (date == null || date.equals("")) return null;
        else return ZonedDateTime.parse(date, FORMAT).withZoneSameInstant(ZoneId.systemDefault());
    }
}
