package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

/**
 * <b>coincheckのAPI操作</b><br>
 * date: 2017/08/03 last_date: 2017/08/21
 * 
 * @author ソウルP
 * @version 1.0
 * @see API 汎用API接続
 * @see Coincheckable coincheck
 */
public class APIcoincheck extends API implements Coincheckable {
    static URL tickerURL;
    static URL tradesURL;
    static URL orderBooksURL;
    static URL ordersURL;
    static URL ordersOpensURL;

    static URL accountsURL;

    static {
        try {
            tickerURL = new URL(API + TICKER);
            tradesURL = new URL(API + TRADES);
            orderBooksURL = new URL(API + ORDER_BOOKS);
            ordersURL = new URL(API + ORDERS);
            ordersOpensURL = new URL(API + ORDERS_OPENS);

            accountsURL = new URL(API + ACCOUNTS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    Function<String, String> getPubAPI   = (url) -> {
                                             try {
                                                 return getPublicAPI(new URL(url));
                                             } catch (MalformedURLException e) {
                                                 e.printStackTrace();
                                             }
                                             return null;
                                         };

    Function<URL, String>    getPrivAPI  = (url) -> {
                                             try {
                                                 if (apiKeyIsEmpty()) throw new Exception("apiKeyの値がありません。");
                                                 if (apiSecretIsEmpty()) throw new Exception("apiSecretの値がありません。");
                                                 return getPrivateAPI(url);
                                             } catch (Exception e) {
                                                 e.printStackTrace();
                                             }
                                             return null;
                                         };

    Function<URL, String>    postPrivAPI = (url) -> {
                                             try {
                                                 if (apiKeyIsEmpty()) throw new Exception("apiKeyの値がありません。");
                                                 if (apiSecretIsEmpty()) throw new Exception("apiSecretの値がありません。");
                                                 return postPrivateAPI(url);
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
     *         <b>order_type</b> 注文のタイプ<br>
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
     *         <b>order_type</b> 注文のタイプ<br>
     *         <b>created_at</b> 注文の作成日時
     */
    @Override
    public String getTrades(int offset) {
        return getPubAPI.apply(API + TRADES + "?offset=" + offset);
    }

    /**
     * <b>板情報</b><br>
     * 板情報を取得できます。
     * 
     * @return 【JSON{JSONArray}】<br>
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
     *            注文のタイプ<br>
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
     * @see OrderType 注文方法
     */
    @Override
    public String getOrdersRate_amount(OrderType order_type, Pair pair, double amount) {
        return getPubAPI.apply(API + ORDERS_RATE + "?order_type=" + order_type + "&pair=" + pair + "&amount=" + amount);
    }

    /**
     * <b>レート取得</b><br>
     * 取引所の注文を元にレートを算出します。
     * 
     * @param order_type
     *            注文のタイプ<br>
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
     * @see OrderType 注文方法
     */
    @Override
    public String getOrdersRate_price(OrderType order_type, Pair pair, double price) {
        return getPubAPI.apply(API + ORDERS_RATE + "?order_type=" + order_type + "&pair=" + pair + "&price=" + price);
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
        return getPubAPI.apply(API + RATE + pair);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderBuy(Pair pair, double rate, double amount) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.buy.toString());
        addParameter("rate", String.valueOf(rate));
        addParameter("amount", String.valueOf(amount));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderSell(Pair pair, double rate, double amount) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.sell.toString());
        addParameter("rate", String.valueOf(rate));
        addParameter("amount", String.valueOf(amount));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderMarketBuy(Pair pair, double market_buy_amount) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.market_buy.toString());
        addParameter("market_buy_amount", String.valueOf(market_buy_amount));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderMarketSell(Pair pair, double amount) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.market_sell.toString());
        addParameter("amount", String.valueOf(amount));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderLeverageBuy(Pair pair, double amount) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.leverage_buy.toString());
        addParameter("amount", String.valueOf(amount));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderLeverageBuy(Pair pair, double amount, double rate) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.leverage_buy.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("rate", String.valueOf(rate));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderLeverageSell(Pair pair, double amount) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.leverage_sell.toString());
        addParameter("amount", String.valueOf(amount));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderLeverageSell(Pair pair, double amount, double rate) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.leverage_sell.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("rate", String.valueOf(rate));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderCloseLong(Pair pair, double amount, long position_id) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.close_long.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderCloseLong(Pair pair, double amount, long position_id, double rate) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.close_long.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
        addParameter("rate", String.valueOf(rate));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderCloseShort(Pair pair, double amount, long position_id) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.close_short.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
        return postPrivAPI.apply(ordersURL);
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
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     * @see Pair 取引ペア
     * @see OrderType 注文方法
     */
    @Override
    public String postOrderCloseShort(Pair pair, double amount, long position_id, double rate) {
        clearParameters();
        addParameter("pair", pair.toString());
        addParameter("order_type", OrderType.close_short.toString());
        addParameter("amount", String.valueOf(amount));
        addParameter("position_id", String.valueOf(position_id));
        addParameter("rate", String.valueOf(rate));
        return postPrivAPI.apply(ordersURL);
    }

    /**
     * <b>未決済の注文一覧</b><br>
     * アカウントの未決済の注文を一覧で表示します。
     * 
     * @return 【JSON(JSON, JSONArray)】<br>
     *         <b>success</b> 情報取得結果<br>
     *         <hr>
     *         ordersの配列<br>
     *         <b>id</b> 注文のID<br>
     *         <b>rate</b> 注文のレート<br>
     *         <b>pending_amount</b> 注文の未決済の量<br>
     *         <b>pending_market_buy_amount</b> 注文の未決済の量（現物成行買いの場合のみ）<br>
     *         <b>order_type</b> 注文方法<br>
     *         <b>stop_loss_rate</b> 逆指値レート<br>
     *         <b>pair</b> 取引ペア<br>
     *         <b>created_at</b> 注文の作成日時
     */
    @Override
    public String getOrdersOpens() {
        return getPrivAPI.apply(ordersOpensURL);
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
        try {
            return deletePrivateAPI(new URL(API + ORDERS_ID + id));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
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
        return getPrivAPI.apply(accountsURL);
    }
}
