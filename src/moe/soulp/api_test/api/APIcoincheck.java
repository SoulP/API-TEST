package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

/**
 * <b>coincheckのAPI操作</b><br>
 * date: 2017/08/03 last_date: 2017/08/22
 * 
 * @author ソウルP
 * @version 1.0 2017/08/03 APIcoincheck作成
 * @see API 汎用API接続
 * @see Coincheckable coincheck
 */
public class APIcoincheck extends API implements Coincheckable {
    static URL tickerURL;
    static URL tradesURL;
    static URL orderBooksURL;
    static URL ordersURL;
    static URL ordersOpensURL;
    static URL ordersTransactionsURL;
    static URL ordersTransactionsPaginationURL;
    static URL positionsURL;

    static URL accountsURL;

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

            accountsURL = new URL(API + ACCOUNTS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    Function<String, String> deletePrivAPI = (url) -> {
        try {
            if (apiKeyIsEmpty()) throw new Exception("apiKeyの値がありません。");
            if (apiSecretIsEmpty()) throw new Exception("apiSecretの値がありません。");
            return deletePrivateAPI(new URL(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };

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
        return getPublicAPI(API + TRADES + "?offset=" + offset);
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
        return getPublicAPI(API + ORDERS_RATE + "?order_type=" + order_type + "&pair=" + pair + "&amount=" + amount);
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
        return getPublicAPI(API + ORDERS_RATE + "?order_type=" + order_type + "&pair=" + pair + "&price=" + price);
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.buy.toString());
        addParameter("rate", String.valueOf(rate));
        addParameter("amount", String.valueOf(amount));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.sell.toString());
        addParameter("rate", String.valueOf(rate));
        addParameter("amount", String.valueOf(amount));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.market_buy.toString());
        addParameter("market_buy_amount", String.valueOf(market_buy_amount));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.market_sell.toString());
        addParameter("amount", String.valueOf(amount));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.leverage_buy.toString());
        addParameter("amount", String.valueOf(amount));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.leverage_buy.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("rate", String.valueOf(rate));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.leverage_sell.toString());
        addParameter("amount", String.valueOf(amount));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.leverage_sell.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("rate", String.valueOf(rate));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.close_long.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.close_long.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
        addParameter("rate", String.valueOf(rate));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.close_short.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
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
        addParameter("pair", pair.toString());
        addParameter("order_type", Type.close_short.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
        addParameter("rate", String.valueOf(rate));
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
        return deletePrivAPI.apply(API + ORDERS_ID + id);
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
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + "?limit=" + limit);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     * 
     * @param order
     *            <br>
     *            true "desc" 降順 (デフォルト)<br>
     *            false "asc" 昇順
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
    public String getOrdersTransactionsPagination(boolean order) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + "?order=" + (order ? "desc" : "asc"));
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
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + "?starting_after=" + starting_after);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     * 
     * @param limit
     *            最大表示件数
     * @param order
     *            <br>
     *            true "desc" 降順 (デフォルト)<br>
     *            false "asc" 昇順
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
    public String getOrdersTransactionsPagination(int limit, boolean order) {
        return getPrivateAPI(
                API + ORDERS_TRANSACTIONS_PAGINATION + "?limit=" + limit + "&order=" + (order ? "desc" : "asc"));
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
                API + ORDERS_TRANSACTIONS_PAGINATION + "?limit=" + limit + "&starting_after=" + starting_after);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     * 
     * @param order
     *            <br>
     *            true "desc" 降順 (デフォルト)<br>
     *            false "asc" 昇順
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
    public String getOrdersTransactionsPagination(boolean order, long starting_after) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + "?order=" + (order ? "desc" : "asc")
                + "&starting_after=" + starting_after);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 自分の最近の取引履歴を参照できます。
     * 
     * @param limit
     *            最大表示件数
     * @param order
     *            <br>
     *            true "desc" 降順 (デフォルト)<br>
     *            false "asc" 昇順
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
    public String getOrdersTransactionsPagination(int limit, boolean order, long starting_after) {
        return getPrivateAPI(API + ORDERS_TRANSACTIONS_PAGINATION + "?limit=" + limit + "&order="
                + (order ? "desc" : "asc") + "&starting_after=" + starting_after);
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
     *            <br>
     *            true "open"<br>
     *            false "closed"
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
    public String getPositions(boolean status) {
        return getPrivateAPI(API + POSITIONS + "?status=" + (status ? "open" : "closed"));
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
