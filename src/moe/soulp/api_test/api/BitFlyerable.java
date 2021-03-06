package moe.soulp.api_test.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <b>bitFlyer</b><br>
 * date: 2017/09/07 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 BitFlyerable作成
 */
public interface BitFlyerable extends Exchangeable {
    DateTimeFormatter FORMAT                    = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // Base API
    String            API                       = "https://api.bitflyer.jp";

    // Public API
    String            GET_MARKETS               = "/v1/getmarkets";                                         // マーケットの一覧
    String            GET_BOARD                 = "/v1/getboard";                                           // 板情報
    String            GET_TICKER                = "/v1/getticker";                                          // Ticker
    String            GET_EXECUTIONS            = "/v1/getexecutions";                                      // 約低履歴
    String            GET_HEALTH                = "/v1/gethealth";                                          // 取引所の状態
    String            GET_CHATS                 = "/v1/getchats";                                           // チャット
    String            API_PRICE                 = "https://bitflyer.jp/api/echo/price";                     // レート取得

    // Private API
    // API
    String            GET_PERMISSIONS           = "/v1/me/getpermissions";                                  // API キーの権限を取得

    // 資産
    String            GET_BALANCE               = "/v1/me/getbalance";                                      // 資産残高を取得
    String            GET_COLLATERAL            = "/v1/me/getcollateral";                                   // 証拠金の状態を取得
    String            GET_COLLATERAL_ACCOUNTS   = "/v1/me/getcollateralaccounts";                           // 通貨別の証拠金の数量を取得

    // 入出金
    String            GET_ADDRESSES             = "/v1/me/getaddresses";                                    // 預入用ビットコイン・イーサリアムアドレス取得
    String            GET_COIN_INS              = "/v1/me/getcoinins";                                      // ビットコイン・イーサ預入履歴
    String            GET_COIN_OUTS             = "/v1/me/getcoinouts";                                     // ビットコイン・イーサ送付履歴
    String            GET_BANK_ACCOUNTS         = "/v1/me/getbankaccounts";                                 // 銀行口座一覧取得
    String            GET_DEPOSITS              = "/v1/me/getdeposits";                                     // 入金履歴
    String            WITHDRAW                  = "/v1/me/withdraw";                                        // 出金
    String            GET_WITHDRAWALS           = "/v1/me/getwithdrawals";                                  // 出金履歴

    // トレード
    String            SEND_ORDER                = "/v1/me/sendchildorder";                                  // 新規注文を出す
    String            CANCEL_ORDER              = "/v1/me/cancelchildorder";                                // 注文をキャンセルする
    String            SEND_ORDER_SUPER          = "/v1/me/sendparentorder";                                 // 新規の親注文を出す（特殊注文）
    String            CANCEL_ORDER_SUPER        = "/v1/me/cancelparentorder";                               // 親注文をキャンセルする
    String            CANCEL_ALL_ORDER          = "/v1/me/cancelallchildorders";                            // すべての注文をキャンセルする
    String            GET_ORDERS                = "/v1/me/getchildorders";                                  // 注文の一覧を取得
    String            GET_ORDERS_SUPER          = "/v1/me/getparentorders";                                 // 親注文の一覧を取得
    String            GET_ORDER_SUPER_INFO      = "/v1/me/getparentorder";                                  // 親注文の詳細を取得
    String            GET_EXECUTIONS_ME         = "/v1/me/getexecutions";                                   // 約定の一覧を取得
    String            GET_POSITIONS             = "/v1/me/getpositions";                                    // 建玉の一覧を取得
    String            GET_MY_COLLATERAL_HISTORY = "/v1/me/getmycollateralhistory";                          // 証拠金の変動履歴を取得
    String            GET_TRADING_COMMISSION    = "/v1/me/gettradingcommission";                            // 取引手数料を取得

    // Public API
    public String getMarkets(); // マーケットの一覧

    public String getBoard(Pair product_code); // 板情報

    public String getBoard(String product_code); // 板情報

    public String getTicker(Pair product_code); // Ticker

    public String getTicker(String product_code); // Ticker

    public String getTrades(Pair product_code); // 約定履歴

    public String getTrades(String product_code); // 約定履歴

    public String getTrades(Pair product_code, Integer count, Long before, Long after); // 約定履歴

    public String getTrades(String product_code, Integer count, Long before, Long after); // 約定履歴

    public String getTrades(Integer count, Long before, Long after); // 約定履歴

    public String getHealth(); // 取引所の状態

    public String getChats(ZonedDateTime from_date); // チャット

    // Private API
    // API
    public String getPermissions(); // API キーの権限を取得

    // 資産
    public String getCollateralAccounts(); // 通貨別の証拠金の数量を取得

    // 入出金
    public String getAddresses(); // 預入用ビットコイン・イーサリアムアドレス取得

    public String getDepositCoins(Integer count, Long before, Long after); // ビットコイン・イーサ預入履歴

    public String getSendCoins(Integer count, Long before, Long after); // ビットコイン・イーサ預入履歴

    public String getDeposits(Integer count, Long before, Long after); // 入金履歴

    public String withdraw(long bank_account_id, long amount, Currency currency, String code); // 出金

    public String getWithdraws(String message_id); // 出金履歴

    public String getWithdraws(Integer count, Long before, Long after); // 出金履歴

    public String getWithdraws(Integer count, Long before, Long after, String message_id); // 出金履歴

    public String orderBuy(String product_code, long price, double size); // 指値注文 現物取引 買い

    public String orderSell(String product_code, long price, double size); // 指値注文 現物取引 売り

    public String orderMarketBuy(String product_code, double size); // 成行注文 現物取引 買い

    public String orderMarketSell(String product_code, double size); // 成行注文 現物取引 売り

    @Override
    public String deleteOrder(String child_order_id, Pair product_code); // 注文のキャンセル

    public String deleteOrder(String child_order_id, String product_code); // 注文のキャンセル

    public String deleteAllOrders(Pair product_code); // 全ての注文をキャンセル

    public String deleteAllOrders(String product_code); // 全ての注文をキャンセル

    public String getOrdersOpens(Pair product_code); // 未決済の注文一覧

    public String getOrdersOpens(String product_code); // 未決済の注文一覧

    public String getOrdersOpens(Pair product_code, Integer count, Long before, Long after); // 未決済の注文一覧

    public String getOrdersOpens(String product_code, Integer count, Long before, Long after); // 未決済の注文一覧

    public String getOrdersOpens(Integer count, Long before, Long after); // 未決済の注文一覧

    public String getOrders(); // 注文一覧

    public String getOrders(Pair product_code); // 注文一覧

    public String getOrders(String product_code); // 注文一覧

    public String getOrders(Integer count, Long before, Long after, Status child_order_state, String child_order_id,
            String child_order_acceptance_id, String parent_order_id); // 注文一覧

    public String getOrders(Pair product_code, Integer count, Long before, Long after, Status child_order_state,
            String child_order_id, String child_order_acceptance_id, String parent_order_id); // 注文一覧

    public String getOrders(String product_code, Integer count, Long before, Long after, Status child_order_state,
            String child_order_id, String child_order_acceptance_id, String parent_order_id); // 注文一覧

    public String getOrdersTransactions(Pair product_code); // 約定一覧

    public String getOrdersTransactions(String product_code); // 約定一覧

    public String getOrdersTransactions(Integer count, Long before, Long after, String child_order_id,
            String child_order_acceptance_id); // 約定一覧

    public String getOrdersTransactions(Pair product_code, Integer count, Long before, Long after,
            String child_order_id, String child_order_acceptance_id); // 約定一覧

    public String getOrdersTransactions(String product_code, Integer count, Long before, Long after,
            String child_order_id, String child_order_acceptance_id); // 約定一覧

    public String getMyCollateralHistory(); // 証拠金の変動履歴

    public String getMyCollateralHistory(Integer count, Long before, Long after); // 証拠金の変動履歴

    public String getTradingCommission(); // 取引手数料

    public String getTradingCommission(Pair prdouct_code); // 取引手数料

    public String getTradingCommission(String product_code); // 取引手数料

    /**
     * <b>日時 データ・タイプ変換</b><br>
     * String(UTC) -> ZonedDateTime(システムのタイムゾーン)
     *
     * @param date
     *            日時 "yyyy-MM-dd'T'HH:mm:ss.SSS"<br>
     *            例え： "2017-08-24T22:33:44.000"
     * @return ZonedDateTime
     */
    public static ZonedDateTime string2zonedDateTime(String date) {
        if (date == null || date.equals("")) return null;
        if (date.length() < 23) for (int i = 23 - date.length(); i > 0; i--)
            date += 0;
        return ZonedDateTime.parse(date + "Z", FORMAT).withZoneSameInstant(ZoneId.systemDefault());
    }
}
