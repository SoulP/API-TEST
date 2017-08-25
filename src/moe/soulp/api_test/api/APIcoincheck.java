package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <b>coincheckのAPI操作</b><br>
 * date: 2017/08/03 last_date: 2017/08/25
 *
 * @author ソウルP
 * @version 1.0 2017/08/03 APIcoincheck作成
 * @see API 汎用API接続
 * @see Coincheckable coincheck
 */
public class APIcoincheck extends API implements Coincheckable {
    private final static String Q_OFFSET          = "?offset=";
    private final static String Q_ORDER_TYPE      = "?order_type=";
    private final static String Q_LIMIT           = "?limit=";
    private final static String Q_ORDER           = "?order=";
    private final static String Q_STARTING_AFTER  = "?starting_after=";
    private final static String Q_STATUS          = "?status=";

    private final static String A_PAIR            = "&pair=";
    private final static String A_AMOUNT          = "&amount=";
    private final static String A_PRICE           = "&price=";
    private final static String A_ORDER           = "&order=";
    private final static String A_STARTING_AFTER  = "&starting_after=";
    private final static String A_LIMIT           = "&limit=";

    private final static String PAIR              = "pair";
    private final static String ORDER_TYPE        = "order_type";
    private final static String RATE              = "rate";
    private final static String AMOUNT            = "amount";
    private final static String MARKET_BUY_AMOUNT = "market_buy_amount";
    private final static String POSITION_ID       = "position_id";

    private static URL          tickerURL;
    private static URL          tradesURL;
    private static URL          orderBooksURL;
    private static URL          ordersURL;
    private static URL          ordersOpensURL;
    private static URL          ordersTransactionsURL;
    private static URL          ordersTransactionsPaginationURL;
    private static URL          positionsURL;
    private static URL          accountsBalanceURL;
    private static URL          accountsLeverageBalanceURL;
    private static URL          accountsURL;

    static {
        try {
            tickerURL = new URL(API + TICKER);
            tradesURL = new URL(API + TRADES);
            orderBooksURL = new URL(API + ORDER_BOOKS);
            ordersURL = new URL(API + ORDERS);
            ordersOpensURL = new URL(API + ORDERS_OPENS);
            ordersTransactionsURL = new URL(API + ORDERS_TRANSACTIONS);
            ordersTransactionsPaginationURL = new URL(API + ORDERS_TRANSACTIONS_PAGINATION);
            positionsURL = new URL(API + POSITIONS);
            accountsBalanceURL = new URL(API + ACCOUNTS_BALANCE);
            accountsLeverageBalanceURL = new URL(API + ACCOUNTS_LEVERAGE_BALANCE);
            accountsURL = new URL(API + ACCOUNTS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>ティッカー</b><br>
     * 各種最新情報を簡易に取得することができます。<br>
     *
     * @return 【JSON】<br>
     *         <b>last</b> 最後の取引の価格<br>
     *         <b>bid</b> 現在の買い注文の最高価格<br>
     *         <b>ask</b> 現在の売り注文の最安価格<br>
     *         <b>high</b> 24時間での最高取引価格<br>
     *         <b>low</b> 24時間での最安取引価格<br>
     *         <b>volume</b> 24時間での取引量<br>
     *         <b>timestamp</b> 現在の時刻<br>
     */
    @Override
    public String getTicker() {
        return getPublicAPI(tickerURL);
    }

    /**
     * <b>全取引履歴</b><br>
     * 最新の取引履歴を取得できます。
     *
     * @return 【JSONArray】<br>
     *         <b>id</b> 注文のID<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>created_at</b> 注文の作成日時
     */
    @Override
    public String getTrades() {
        return getPublicAPI(tradesURL);
    }

    /**
     * <b>全取引履歴</b><br>
     * 最新の取引履歴を取得できます。
     *
     * @param offset
     *            指定された数だけスキップ<br>
     *            最大99件スキップ可能 (1~99)
     * @return 【JSONArray】<br>
     *         <b>id</b> 注文のID<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>created_at</b> 注文の作成日時
     */
    @Override
    public String getTrades(int offset) {
        return getPublicAPI(API + TRADES + Q_OFFSET + offset);
    }

    /**
     * <b>板情報</b><br>
     * 板情報を取得できます。
     *
     * @return 【JSON】<br>
     *         <b>asks</b> 売り注文の情報<br>
     *         <b>bids</b> 買い注文の情報
     */
    @Override
    public String getOrderBooks() {
        return getPublicAPI(orderBooksURL);
    }

    /**
     * <b>レート取得</b><br>
     * 取引所の注文を元にレートを算出します。
     *
     * @param order_type
     *            注文の種類<br>
     *            "sell" もしくは "buy"
     * @param pair
     *            取引ペア<br>
     *            現在は "btc_jpy" のみです。
     * @param amount
     *            注文の量
     * @return 【JSON】<br>
     *         <b>success</b> 情報取得結果<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>price</b> 注文の金額
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String getOrdersRate_amount(Type order_type, Pair pair, double amount) {
        return getPublicAPI(API + ORDERS_RATE + Q_ORDER_TYPE + order_type + A_PAIR + pair + A_AMOUNT + amount);
    }

    /**
     * <b>レート取得</b><br>
     * 取引所の注文を元にレートを算出します。
     *
     * @param order_type
     *            注文の種類<br>
     *            "sell" もしくは "buy"
     * @param pair
     *            取引ペア<br>
     *            現在は "btc_jpy" のみです。
     * @param price
     *            注文の金額
     * @return 【JSON】<br>
     *         <b>success</b> 情報取得結果<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>price</b> 注文の金額
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String getOrdersRate_price(Type order_type, Pair pair, double price) {
        return getPublicAPI(API + ORDERS_RATE + Q_ORDER_TYPE + order_type + A_PAIR + pair + A_PRICE + price);
    }

    /**
     * <b>販売レート取得</b><br>
     * 販売所のレートを取得します。
     *
     * @param pair
     *            取引ペア
     * @return 【JSON】<br>
     *         <b>rate</b> 販売のレート
     * @see Pair 取引ペア
     */
    @Override
    public String getRate(Pair pair) {
        return getPublicAPI(API + RATE + pair);
    }

    /**
     * <b>指値注文 現物取引 買い</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param rate
     *            注文のレート
     * @param amount
     *            注文の量
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderBuy(Pair pair, double rate, double amount) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.buy.toString());
        addParameter(RATE, String.valueOf(rate));
        addParameter(AMOUNT, String.valueOf(amount));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>指値注文 現物取引 売り</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param rate
     *            注文のレート
     * @param amount
     *            注文の量
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderSell(Pair pair, double rate, double amount) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.sell.toString());
        addParameter(RATE, String.valueOf(rate));
        addParameter(AMOUNT, String.valueOf(amount));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>成行注文 現物取引 買い</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param market_buy_amount
     *            成行買で利用する日本円の金額
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 注文の種類
     */
    @Override
    public String postOrderMarketBuy(Pair pair, double market_buy_amount) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.market_buy.toString());
        addParameter(MARKET_BUY_AMOUNT, String.valueOf(market_buy_amount));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>成行注文 現物取引 売り</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderMarketSell(Pair pair, double amount) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.market_sell.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>成行注文 レバレッジ取引新規 買い</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderLeverageBuy(Pair pair, double amount) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.leverage_buy.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>指値注文 レバレッジ取引新規 買い</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @param rate
     *            注文のレート
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderLeverageBuy(Pair pair, double amount, double rate) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.leverage_buy.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        addParameter(RATE, String.valueOf(rate));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>成行注文 レバレッジ取引新規 売り</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderLeverageSell(Pair pair, double amount) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.leverage_sell.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>指値注文 レバレッジ取引新規 売り</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @param rate
     *            注文のレート
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderLeverageSell(Pair pair, double amount, double rate) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.leverage_sell.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        addParameter(RATE, String.valueOf(rate));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>成行注文 レバレッジ取引決済 売り</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @param position_id
     *            決済するポジションのID
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderCloseLong(Pair pair, double amount, long position_id) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.close_long.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        addParameter(POSITION_ID, String.valueOf(position_id));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>指値注文 レバレッジ取引決済 売り</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @param position_id
     *            決済するポジションのID
     * @param rate
     *            注文のレート
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderCloseLong(Pair pair, double amount, long position_id, double rate) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.close_long.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        addParameter(POSITION_ID, String.valueOf(position_id));
        addParameter(RATE, String.valueOf(rate));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>成行注文 レバレッジ取引決済 買い</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @param position_id
     *            決済するポジションのID
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderCloseShort(Pair pair, double amount, long position_id) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.close_short.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        addParameter(POSITION_ID, String.valueOf(position_id));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>指値注文 レバレッジ取引決済 買い</b><br>
     * 取引所に新規注文を発行します。<br>
     *
     * @param pair
     *            取引ペア
     * @param amount
     *            注文の量
     * @param position_id
     *            決済するポジションのID
     * @param rate
     *            注文のレート
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> 新規注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>amount</b> 注文の量<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see Type 種類
     */
    @Override
    public String postOrderCloseShort(Pair pair, double amount, long position_id, double rate) {
        clearParameters();
        addParameter(PAIR, pair.toString());
        addParameter(ORDER_TYPE, Type.close_short.toString());
        addParameter(AMOUNT, String.valueOf(amount));
        addParameter(POSITION_ID, String.valueOf(position_id));
        addParameter(RATE, String.valueOf(rate));
        return postPrivateAPI(ordersURL);
    }

    /**
     * <b>未決済の注文一覧</b><br>
     * アカウントの未決済の注文を一覧で表示します。
     *
     * @return 【JSON】<br>
     *         <b>success</b> 情報取得結果<br>
     *         <hr>
     *         ordersの配列<br>
     *         <b>id</b> 注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>pending_amount</b> 注文の未決済の量<br>
     *         <b>pending_market_buy_amount</b> 注文の未決済の量（現物成行買いの場合のみ）<br>
     *         <b>order_type</b> 注文の種類<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     */
    @Override
    public String getOrdersOpens() {
        return getPrivateAPI(ordersOpensURL);
    }

    /**
     * <b>注文のキャンセル</b><br>
     * 新規注文または未決済の注文一覧のIDを指定してキャンセルすることができます。
     *
     * @param id
     *            新規注文または未決済の注文一覧のID
     * @return 【JSON】<br>
     *         <b>success</b> 結果<br>
     *         <b>id</b> キャンセルした注文のID
     */
    @Override
    public String deleteOrdersId(long id) {
        return deletePrivateAPI(API + ORDERS_ID + id);
    }

    /**
     * <b>取引履歴</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <hr>
     *         transactionsの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     */
    @Override
    public String getOrdersTransactions() {
        return getPrivateAPI(ordersTransactionsURL);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     */
    @Override
    public String getOrdersTransactionsPagination() {
        return getPrivateAPI(ordersTransactionsPaginationURL);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param limit
     *            最大表示件数
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     */
    @Override
    public String getOrdersTransactionsPagination(int limit) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + Q_LIMIT + limit);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param order
     *            ソート
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     * @see Sort ソート
     */
    @Override
    public String getOrdersTransactionsPagination(Sort order) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + Q_ORDER + order);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     */
    @Override
    public String getOrdersTransactionsPagination(long starting_after) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + Q_STARTING_AFTER + starting_after);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param limit
     *            最大表示件数
     * @param order
     *            ソート
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     * @see Sort ソート
     */
    @Override
    public String getOrdersTransactionsPagination(int limit, Sort order) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + Q_LIMIT + limit + A_ORDER + order);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param limit
     *            最大表示件数
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     */
    @Override
    public String getOrdersTransactionsPagination(int limit, long starting_after) {
        return getPrivateAPI(
                API + ORDERS_TRANSACTIONS_PAGINATION + Q_LIMIT + limit + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param order
     *            ソート
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     * @see Sort ソート
     */
    @Override
    public String getOrdersTransactionsPagination(Sort order, long starting_after) {
        return getPrivateAPI(
                API + ORDERS_TRANSACTIONS_PAGINATION + Q_ORDER + order + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     *
     * @param limit
     *            最大表示件数
     * @param order
     *            ソート
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>created_at</b> 取引日時<br>
     *         <b>funds</b> 各残高の増減分 JSON(btc, jpy)<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>rate</b> 約定価格<br>
     *         <b>fee_currency</b> 手数料の通貨<br>
     *         <b>fee</b> 発生した手数料<br>
     *         <b>liquidity</b> 流動性 "T" ( Taker ) or "M" ( Maker )<br>
     *         <b>side</b> 取引の種類 ( "buy", "sell" )
     * @see Sort ソート
     */
    @Override
    public String getOrdersTransactionsPagination(int limit, Sort order, long starting_after) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + Q_LIMIT + limit + A_ORDER + order + A_STARTING_AFTER
                + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     */
    @Override
    public String getPositions() {
        return getPrivateAPI(positionsURL);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     */
    @Override
    public String getPositions(Status status) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param limit
     *            最大表示件数
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     */
    @Override
    public String getPositions(int limit) {
        return getPrivateAPI(API + POSITIONS + Q_LIMIT + limit);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param order
     *            ソート
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Sort ソート
     */
    @Override
    public String getPositions(Sort order) {
        return getPrivateAPI(API + POSITIONS + Q_ORDER + order);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     */
    @Override
    public String getPositions(long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_STARTING_AFTER + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param limit
     *            最大表示件数
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     */
    @Override
    public String getPositions(Status status, int limit) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_LIMIT + limit);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param order
     *            ソート
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     * @see Sort ソート
     */
    @Override
    public String getPositions(Status status, Sort order) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_ORDER + order);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     */
    @Override
    public String getPositions(Status status, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param limit
     *            最大表示件数
     * @param order
     *            ソート
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     * @see Sort ソート
     */
    @Override
    public String getPositions(Status status, int limit, Sort order) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_LIMIT + limit + A_ORDER + order);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param limit
     *            最大表示件数
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     */
    @Override
    public String getPositions(Status status, int limit, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_LIMIT + limit + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param order
     *            ソート
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     * @see Sort ソート
     */
    @Override
    public String getPositions(Status status, Sort order, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_ORDER + order + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param status
     *            ポジションの状態
     * @param limit
     *            最大表示件数
     * @param order
     *            ソート
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Status 状態
     * @see Sort ソート
     */
    @Override
    public String getPositions(Status status, int limit, Sort order, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_STATUS + status + A_LIMIT + limit + A_ORDER + order + A_STARTING_AFTER
                + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param limit
     *            最大表示件数
     * @param order
     *            ソート
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Sort ソート
     */
    @Override
    public String getPositions(int limit, Sort order) {
        return getPrivateAPI(API + POSITIONS + Q_LIMIT + limit + A_ORDER + order);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param limit
     *            最大表示件数
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     */
    @Override
    public String getPositions(int limit, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_LIMIT + limit + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param limit
     *            最大表示件数
     * @param order
     *            ソート
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Sort ソート
     */
    @Override
    public String getPositions(int limit, Sort order, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_LIMIT + limit + A_ORDER + order + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>ポジション一覧</b><br>
     * レバレッジ取引のポジション一覧を表示します。
     *
     * @param order
     *            ソート
     * @param starting_after
     *            指定したIDより後の取引履歴 starting_after < id
     * @return 【JSON】<br>
     *         <b>succecc</b> 結果<br>
     *         <b>pagination</b> ページネーション JSON(limit, order, starting_after,
     *         ending_before)<br>
     *         <hr>
     *         dataの配列<br>
     *         <b>id</b> ID<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>status</b> ポジションの状態 ( "open", "closed" )<br>
     *         <b>created_at</b> ポジションの作成日時<br>
     *         <b>closed_at</b> ポジションの決済完了日時<br>
     *         <b>open_rate</b> ポジションの平均取得価格<br>
     *         <b>closed_rate</b> ポジションの平均決済価格<br>
     *         <b>amount</b> 現在のポジションの数量（BTC）<br>
     *         <b>all_amount</b> ポジションの数量（BTC）<br>
     *         <b>side</b> ポジションの種類 ( "buy", "sell" )<br>
     *         <b>pl</b> 利益<br>
     *         <b>new_order</b> 新規注文についての情報 JSON(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at )<br>
     *         <b>close_orders</b> 決済注文についての情報 JSONArray(id, side, rate, amount,
     *         pending_amount, status( "complete", "cancel" ), created_at)
     * @see Sort ソート
     */
    @Override
    public String getPositions(Sort order, long starting_after) {
        return getPrivateAPI(API + POSITIONS + Q_ORDER + order + A_STARTING_AFTER + starting_after);
    }

    /**
     * <b>残高</b><br>
     * アカウントの残高を確認できます。<br>
     * jpy, btc には未決済の注文に利用している jpy_reserved, btc_reserved は含まれていません。
     * 
     * @return 【JSON】<br>
     *         <b>jpy</b> 日本円の残高<br>
     *         <b>btc</b> ビットコインの残高<br>
     *         <b>jpy_reserved</b> 未決済の買い注文に利用している日本円の合計<br>
     *         <b>btc_reserved</b> 未決済の売り注文に利用しているビットコインの合計<br>
     *         <b>jpy_lend_in_use</b> 貸出申請をしている日本円の合計<br>
     *         <b>btc_lend_in_use</b> 貸出申請をしているビットコインの合計<br>
     *         <b>jpy_lent</b> 貸出をしている日本円の合計<br>
     *         <b>btc_lent</b> 貸出をしているビットコインの合計<br>
     *         <b>jpy_debt</b> 借りている日本円の合計<br>
     *         <b>btc_debt</b> 借りているビットコインの合計
     */
    @Override
    public String getAccountsBalance() {
        return getPrivateAPI(accountsBalanceURL);
    }

    /**
     * <b>レバレッジアカウントの残高</b><br>
     * レバレッジアカウントの残高を確認できます。
     * 
     * @return 【JSON】<br>
     *         <b>margin</b> 証拠金 JSON(jpy)<br>
     *         <b>margin_available</b> 利用可能な証拠金 JSON(jpy)<br>
     *         <b>margin_level</b> 証拠金維持率
     */
    @Override
    public String getAccountsLeverageBalance() {
        return getPrivateAPI(accountsLeverageBalanceURL);
    }

    /**
     * <b>アカウント情報</b><br>
     * アカウントの情報を表示します。
     *
     * @return 【JSON】<br>
     *         <b>success</b> 情報取得結果<br>
     *         <b>id</b> アカウントのID<br>
     *         <b>email</b> 登録されたメールアドレス<br>
     *         <b>identity_status</b> 本人確認書類の提出状況<br>
     *         <b>bitcoin_address</b> ビットコインのアドレス<br>
     *         <b>lending_leverage</b> レバレッジ<br>
     *         <b>taker_fee</b> Takerとして注文を行った場合の手数料<br>
     *         <b>maker_fee</b> Makerとして注文を行った場合の手数料
     */
    @Override
    public String getAccounts() {
        return getPrivateAPI(accountsURL);
    }

    protected String getPublicAPI(String url) {
        try {
            return getPublicAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getPrivateAPI(String url) {
        try {
            return getPrivateAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getPrivateAPI(URL url) {
        try {
            checkAPIkeys();
            return super.getPrivateAPI(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String postPrivateAPI(String url) {
        try {
            return postPrivateAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String postPrivateAPI(URL url) {
        try {
            checkAPIkeys();
            return super.postPrivateAPI(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String deletePrivateAPI(String url) {
        try {
            return deletePrivateAPI(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String deletePrivateAPI(URL url) {
        try {
            checkAPIkeys();
            return super.deletePrivateAPI(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkAPIkeys() throws Exception {
        if (apiKeyIsEmpty()) throw new Exception("apiKeyの値がありません。");
        if (apiSecretIsEmpty()) throw new Exception("apiSecretの値がありません。");
    }
}
