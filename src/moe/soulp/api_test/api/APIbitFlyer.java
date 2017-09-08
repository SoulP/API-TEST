package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * <b>bitFlyerのAPI操作</b><br>
 * date: 2017/09/07 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 APIbitFlyer作成
 */
public class APIbitFlyer extends API implements BitFlyerable {
    private final static String Q_PRODUCT_CODE   = "?product_code=";
    private final static String Q_COUNT          = "?count=";
    private final static String Q_BEFORE         = "?before=";
    private final static String Q_AFTER          = "?after=";
    private final static String Q_FROM_DATE      = "?from_date=";

    private final static String A_COUNT          = "&count=";
    private final static String A_BEFORE         = "&before=";
    private final static String A_AFTER          = "&after=";

    private final static String Z                = "Z";
    private final static String ACCESS_KEY       = "ACCESS-KEY";
    private final static String ACCESS_TIMESTAMP = "ACCESS-TIMESTAMP";
    private final static String ACCESS_SIGN      = "ACCESS-SIGN";

    private static URL          getMarketsURL;
    private static URL          getBoardURL;
    private static URL          getTickerURL;
    private static URL          getExecutionsURL;
    private static URL          getHealthURL;
    private static URL          getChatsURL;
    private static URL          apiPriceURL;
    private static URL          getPermissionsURL;
    private static URL          getBalanceURL;

    static {
        try {
            getMarketsURL = new URL(API + GET_MARKETS);
            getBoardURL = new URL(API + GET_BOARD);
            getTickerURL = new URL(API + GET_TICKER);
            getExecutionsURL = new URL(API + GET_EXECUTIONS);
            getHealthURL = new URL(API + GET_HEALTH);
            getChatsURL = new URL(API + GET_CHATS);
            apiPriceURL = new URL(API_PRICE);
            getPermissionsURL = new URL(API + GET_PERMISSIONS);
            getBalanceURL = new URL(API + GET_BALANCE);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>bitFlyerのAPI</b>
     */
    public APIbitFlyer() {
        super();
        setAPIkeyProperty(ACCESS_KEY);
        setAPInonceProperty(ACCESS_TIMESTAMP);
        setAPIsignProperty(ACCESS_SIGN);
    };

    /**
     * <b>bitFlyerのAPI</b>
     * 
     * @param apiKey
     *            APIキー
     * @param apiSecret
     *            APIシークレット
     */
    public APIbitFlyer(String apiKey, String apiSecret) {
        this();
        setAPIkey(apiKey);
        setAPIsecret(apiSecret);
    }

    // Public API
    /**
     * <b>マーケットの一覧</b>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>product_code</b> マーケットのコード<br>
     *         <b>alias</b> 別名
     */
    @Override
    public String getMarkets() {
        return publicAPI(getMarketsURL, HttpMethod.GET);
    }

    /**
     * <b>板情報</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @return 【JSON】<br>
     *         <b>mid_price</b> 仲値<br>
     *         <hr>
     *         bids 【JSONArray】 買取価格<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <hr>
     *         asks 【JSONArray】 販売価格<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量
     */
    @Override
    public String getBoard() {
        return publicAPI(getBoardURL, HttpMethod.GET);
    }

    /**
     * <b>板情報</b>
     * 
     * @param product_code
     *            マーケットのコード
     * @return 【JSON】<br>
     *         <b>mid_price</b> 仲値<br>
     *         <hr>
     *         bids 【JSONArray】 買取価格<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <hr>
     *         asks 【JSONArray】 販売価格<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量
     * @see Pair 取引ペア
     */
    @Override
    public String getBoard(Pair product_code) {
        return publicAPI(API + GET_BOARD + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>板情報</b>
     * 
     * @param product_code
     *            マーケットのコード
     * @return 【JSON】<br>
     *         <b>mid_price</b> 仲値<br>
     *         <hr>
     *         bids 【JSONArray】 買取価格<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <hr>
     *         asks 【JSONArray】 販売価格<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量
     */
    @Override
    public String getBoard(String product_code) {
        return publicAPI(API + GET_BOARD + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>Ticker</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @return 【JSON】<br>
     *         <b>product_code</b> マーケットのコード<br>
     *         <b>timestamp</b> 日時<br>
     *         <b>tick_id</b> TickerのID<br>
     *         <b>best_bid</b> ベストな買取価格<br>
     *         <b>best_ask</b> ベストな販売価格<br>
     *         <b>best_bid_size</b> ベストな買取価格の量<br>
     *         <b>best_ask_size</b> ベストな販売価格の量<br>
     *         <b>total_bid_depth</b> 買取合計の深さ<br>
     *         <b>total_ask_depth</b> 販売合計の深さ<br>
     *         <b>ltp</b> 最終取引価格<br>
     *         <b>volume</b> 24 時間の取引量<br>
     *         <b>volume_by_product</b> マーケットの24 時間の取引量
     */
    @Override
    public String getTicker() {
        return publicAPI(getTickerURL, HttpMethod.GET);
    }

    /**
     * <b>Ticker</b>
     * 
     * @param product_code
     *            マーケットのコード
     * @return 【JSON】<br>
     *         <b>product_code</b> マーケットのコード<br>
     *         <b>timestamp</b> 日時<br>
     *         <b>tick_id</b> TickerのID<br>
     *         <b>best_bid</b> ベストな買取価格<br>
     *         <b>best_ask</b> ベストな販売価格<br>
     *         <b>best_bid_size</b> ベストな買取価格の量<br>
     *         <b>best_ask_size</b> ベストな販売価格の量<br>
     *         <b>total_bid_depth</b> 買取合計の深さ<br>
     *         <b>total_ask_depth</b> 販売合計の深さ<br>
     *         <b>ltp</b> 最終取引価格<br>
     *         <b>volume</b> 24 時間の取引量<br>
     *         <b>volume_by_product</b> マーケットの24 時間の取引量
     * @see Pair 取引ペア
     */
    @Override
    public String getTicker(Pair product_code) {
        return publicAPI(API + GET_TICKER + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>Ticker</b>
     * 
     * @param product_code
     *            マーケットのコード
     * @return 【JSON】<br>
     *         <b>product_code</b> マーケットのコード<br>
     *         <b>timestamp</b> 日時<br>
     *         <b>tick_id</b> TickerのID<br>
     *         <b>best_bid</b> ベストな買取価格<br>
     *         <b>best_ask</b> ベストな販売価格<br>
     *         <b>best_bid_size</b> ベストな買取価格の量<br>
     *         <b>best_ask_size</b> ベストな販売価格の量<br>
     *         <b>total_bid_depth</b> 買取合計の深さ<br>
     *         <b>total_ask_depth</b> 販売合計の深さ<br>
     *         <b>ltp</b> 最終取引価格<br>
     *         <b>volume</b> 24 時間の取引量<br>
     *         <b>volume_by_product</b> マーケットの24 時間の取引量
     */
    @Override
    public String getTicker(String product_code) {
        return publicAPI(API + GET_TICKER + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTrades() {
        return publicAPI(getExecutionsURL, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTrades(Pair product_code) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTrades(String product_code) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTrades(int count) {
        return publicAPI(API + GET_EXECUTIONS + Q_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTrades(Pair product_code, int count) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTrades(String product_code, int count) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTrades(Pair product_code, int count, long before, long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count + A_BEFORE + before
                + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTrades(String product_code, int count, long before, long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count + A_BEFORE + before
                + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTradesBefore(long before) {
        return publicAPI(API + GET_EXECUTIONS + Q_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTradesBefore(Pair product_code, long before) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param before
     *            ID 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTradesBefore(String product_code, long before) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTradesBefore(Pair product_code, int count, long before) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count + A_BEFORE + before,
                HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTradesBefore(String product_code, int count, long before) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count + A_BEFORE + before,
                HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTradesAfter(long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTradesAfter(Pair product_code, long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTradesAfter(String product_code, long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     * @see Pair 取引ペア
     */
    @Override
    public String getTradesAfter(Pair product_code, int count, long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count + A_AFTER + after,
                HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            マーケットのコード
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>buy_child_order_acceptance_id</b> 新規注文の買いのID<br>
     *         <b>sell_child_order_acceptance_id</b> 新規注文の売りのID
     */
    @Override
    public String getTradesAfter(String product_code, int count, long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + A_COUNT + count + A_AFTER + after,
                HttpMethod.GET);
    }

    /**
     * <b>取引所の状態</b><br>
     * 取引所の稼動状態を取得します。<br>
     * <ul>
     * status 以下のいずれかの値をとります。
     * <li><b>NORMAL</b>: 取引所は稼動しています。</li>
     * <li><b>BUSY</b>: 取引所に負荷がかかっている状態です。</li>
     * <li><b>VERY BUSY</b>: 取引所の負荷が大きい状態です。</li>
     * <li><b>SUPER BUSY</b>: 負荷が非常に大きい状態です。<br>
     * 発注は失敗するか、遅れて処理される可能性があります。</li>
     * <li><b>STOP</b>: 取引所は停止しています。<br>
     * 発注は受付されません。</li>
     * </ul>
     * 
     * @return 【JSON】<br>
     *         <b>status</b> 状態
     */
    @Override
    public String getHealth() {
        return publicAPI(getHealthURL, HttpMethod.GET);
    }

    /**
     * <b>チャット</b><br>
     * チャットの発言一覧を取得します。<br>
     * 5 日前からの発言を返します。
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>nickname</b> ニックネーム<br>
     *         <b>message</b> メッセージ<br>
     *         <b>date</b> 日時
     */
    @Override
    public String getChats() {
        return publicAPI(getChatsURL, HttpMethod.GET);
    }

    /**
     * <b>チャット</b><br>
     * チャットの発言一覧を取得します。
     * 
     * @param from_date
     *            指定された日付以降の発言を取得します。
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>nickname</b> ニックネーム<br>
     *         <b>message</b> メッセージ<br>
     *         <b>date</b> 日時
     */
    @Override
    public String getChats(ZonedDateTime from_date) {
        return publicAPI(API + GET_CHATS + Q_FROM_DATE + from_date.withZoneSameInstant(ZoneId.of(Z)).toLocalDateTime(),
                HttpMethod.GET);
    }

    /**
     * <b>レート取得</b><br>
     * bitFlyerのビットコインレート値を返します。
     * 
     * @return 【JSON】<br>
     *         <b>mid</b> 仲値<br>
     *         <b>ask</b> bitFlyerの1BTC販売価格<br>
     *         <b>bid</b> bitFlyerの1BTC買取価格
     */
    @Override
    public String getRate() {
        return publicAPI(apiPriceURL, HttpMethod.GET);
    }

    /**
     * <b>API キーの権限を取得</b><br>
     * この API キーで呼出可能な HTTP Private API の一覧を取得できます。
     * 
     * @return 【JSONArray】<br>
     *         String
     */
    @Override
    public String getPermissions() {
        return privateAPI(getPermissionsURL, HttpMethod.GET);
    }

    /**
     * <b>資産残高を取得</b>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>available</b> 利用可能な量
     */
    @Override
    public String getBalance() {
        return privateAPI(getBalanceURL, HttpMethod.GET);
    }

    @Override
    protected String createSignature(String apiSecret, String url, String nonce, HttpMethod method) {
        String message = nonce + method + url.substring(API.length()) + getParameters();
        return HMAC_SHA256Encode(apiSecret, message);
    }
}
