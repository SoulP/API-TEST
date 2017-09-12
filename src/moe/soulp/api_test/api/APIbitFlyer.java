package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <b>bitFlyerのAPI操作</b><br>
 * date: 2017/09/07 last_date: 2017/09/12
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
    private final static String Q_MESSAGE_ID     = "?message_id=";

    private final static String A_COUNT          = "&count=";
    private final static String A_BEFORE         = "&before=";
    private final static String A_AFTER          = "&after=";
    private final static String A_MESSAGE_ID     = "&message_id=";

    private final static String Z                = "Z";
    private final static String ACCESS_KEY       = "ACCESS-KEY";
    private final static String ACCESS_TIMESTAMP = "ACCESS-TIMESTAMP";
    private final static String ACCESS_SIGN      = "ACCESS-SIGN";

    private final static String CURRENCY_CODE    = "currency_code";
    private final static String BANK_ACCOUNT_ID  = "bank_account_id";
    private final static String AMOUNT           = "amount";
    private final static String CODE             = "code";
    private final static String BODY             = "Body";

    private static URL          getMarketsURL;
    private static URL          getBoardURL;
    private static URL          getTickerURL;
    private static URL          getExecutionsURL;
    private static URL          getHealthURL;
    private static URL          getChatsURL;
    private static URL          apiPriceURL;
    private static URL          getPermissionsURL;
    private static URL          getBalanceURL;
    private static URL          getCollateralURL;
    private static URL          getCollateralAccountsURL;
    private static URL          getAddressesURL;
    private static URL          getCoinInsURL;
    private static URL          getCoinOutsURL;
    private static URL          getBankAccountsURL;
    private static URL          getDepositsURL;
    private static URL          withdrawURL;
    private static URL          getWithdrawalsURL;

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
            getCollateralURL = new URL(API + GET_COLLATERAL);
            getCollateralAccountsURL = new URL(API + GET_COLLATERAL_ACCOUNTS);
            getAddressesURL = new URL(API + GET_ADDRESSES);
            getCoinInsURL = new URL(API + GET_COIN_INS);
            getCoinOutsURL = new URL(API + GET_COIN_OUTS);
            getBankAccountsURL = new URL(API + GET_BANK_ACCOUNTS);
            getDepositsURL = new URL(API + GET_DEPOSITS);
            withdrawURL = new URL(API + WITHDRAW);
            getWithdrawalsURL = new URL(API + GET_WITHDRAWALS);
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

    /**
     * <b>証拠金の状態を取得</b>
     * 
     * @return 【JSON】<br>
     *         <b>collateral</b> 預け入れた証拠金の評価額<br>
     *         <b>open_position_pnl</b> 建玉の評価損益<br>
     *         <b>require_collateral</b> 現在の必要証拠金<br>
     *         <b>keep_rate</b> 現在の証拠金維持率
     */
    @Override
    public String getLeverageBalance() {
        return privateAPI(getCollateralURL, HttpMethod.GET);
    }

    /**
     * <b>通貨別の証拠金の数量を取得</b>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量
     */
    @Override
    public String getCollateralAccounts() {
        return privateAPI(getCollateralAccountsURL, HttpMethod.GET);
    }

    /**
     * <b>預入用ビットコイン・イーサリアムアドレス取得</b>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>type</b> 種類<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>address</b> アドレス
     */
    @Override
    public String getAddresses() {
        return privateAPI(getAddressesURL, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoins() {
        return privateAPI(getCoinInsURL, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoins(int count) {
        return privateAPI(API + GET_COIN_INS + Q_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
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
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoins(int count, long before, long after) {
        return privateAPI(API + GET_COIN_INS + Q_COUNT + count + A_BEFORE + before + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoinsBefore(long before) {
        return privateAPI(API + GET_COIN_INS + Q_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoinsBefore(int count, long before) {
        return privateAPI(API + GET_COIN_INS + Q_COUNT + count + A_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoinsAfter(long after) {
        return privateAPI(API + GET_COIN_INS + Q_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositCoinsAfter(int count, long after) {
        return privateAPI(API + GET_COIN_INS + Q_COUNT + count + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoins() {
        return privateAPI(getCoinOutsURL, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoins(int count) {
        return privateAPI(API + GET_COIN_OUTS + Q_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
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
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoins(int count, long before, long after) {
        return privateAPI(API + GET_COIN_OUTS + Q_COUNT + count + A_BEFORE + before + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoinsBefore(long before) {
        return privateAPI(API + GET_COIN_OUTS + Q_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoinsBefore(int count, long before) {
        return privateAPI(API + GET_COIN_OUTS + Q_COUNT + count + A_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoinsAfter(long after) {
        return privateAPI(API + GET_COIN_OUTS + Q_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>ビットコイン・イーサ送付履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> ID<br>
     *         <b>order_id</b> 注文ID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 量<br>
     *         <b>address</b> アドレス<br>
     *         <b>tx_hash</b> ハッシュ<br>
     *         <b>fee</b> 手数料<br>
     *         <b>additional_fee</b> 追加手数料<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getSendCoinsAfter(int count, long after) {
        return privateAPI(API + GET_COIN_OUTS + Q_COUNT + count + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>銀行口座一覧取得</b><br>
     * アカウントに登録された銀行口座の一覧を取得します。
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 口座のID<br>
     *         <b>is_verified</b> 承認有無<br>
     *         <b>bank_name</b> 銀行名<br>
     *         <b>branch_name</b> 支店名<br>
     *         <b>account_type</b> 口座種類<br>
     *         <b>account_number</b> 口座番号<br>
     *         <b>account_name</b> 口座名義
     */
    @Override
    public String getBankAccounts() {
        return privateAPI(getBankAccountsURL, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDeposits() {
        return privateAPI(getDepositsURL, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDeposits(int count) {
        return privateAPI(API + GET_DEPOSITS + Q_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDeposits(int count, long before, long after) {
        return privateAPI(API + GET_DEPOSITS + Q_COUNT + count + A_BEFORE + before + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositsBefore(long before) {
        return privateAPI(API + GET_DEPOSITS + Q_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositsBefore(int count, long before) {
        return privateAPI(API + GET_DEPOSITS + Q_COUNT + count + A_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositsAfter(long after) {
        return privateAPI(API + GET_DEPOSITS + Q_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>入金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 入金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getDepositsAfter(int count, long after) {
        return privateAPI(API + GET_DEPOSITS + Q_COUNT + count + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>出金</b>
     * 
     * @param bank_account_id
     *            口座のID
     * @param amount
     *            金額
     * @param currency_code
     *            通貨
     * @return 【JSON】<br>
     *         <b>message_id</b> 出金メッセージの受付 ID
     */
    @Override
    public String withdraw(long bank_account_id, long amount, Currency currency_code) {
        clearParameters();
        JSONObject temp = null;
        try {
            temp = new JSONObject().put(CURRENCY_CODE, currency_code).put(BANK_ACCOUNT_ID, bank_account_id).put(AMOUNT,
                    amount);
            addParameter(BODY, temp.toString());
            return privateAPI(withdrawURL, HttpMethod.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            clearParameters();
            return null;
        }
    }

    /**
     * <b>出金</b>
     * 
     * @param bank_account_id
     *            口座のID
     * @param amount
     *            金額
     * @param currency_code
     *            通貨
     * @param code
     *            二段階認証の確認コード
     * @return 【JSON】<br>
     *         <b>message_id</b> 出金メッセージの受付 ID
     */
    @Override
    public String withdraw(long bank_account_id, long amount, Currency currency_code, String code) {
        clearParameters();
        JSONObject temp = null;
        try {
            temp = new JSONObject().put(CURRENCY_CODE, currency_code).put(BANK_ACCOUNT_ID, bank_account_id)
                    .put(AMOUNT, amount).put(CODE, code);
            addParameter(BODY, temp.toString());
            return privateAPI(withdrawURL, HttpMethod.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            clearParameters();
            return null;
        }
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdraws() {
        return privateAPI(getWithdrawalsURL, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdraws(int count) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdraws(String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_MESSAGE_ID + message_id, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdraws(long before, long after, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_BEFORE + before + A_AFTER + after + A_MESSAGE_ID + message_id,
                HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdraws(int count, long before, long after) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count + A_BEFORE + before + A_AFTER + after,
                HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdraws(int count, long before, long after, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count + A_BEFORE + before + A_AFTER + after + A_MESSAGE_ID
                + message_id, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsBefore(long before) {
        return privateAPI(API + GET_WITHDRAWALS + Q_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsBefore(int count, long before) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count + A_BEFORE + before, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsBefore(long before, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_BEFORE + before + A_MESSAGE_ID + message_id, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsBefore(int count, long before, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count + A_BEFORE + before + A_MESSAGE_ID + message_id,
                HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsAfter(long after) {
        return privateAPI(API + GET_WITHDRAWALS + Q_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsAfter(int count, long after) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count + A_AFTER + after, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsAfter(long after, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_AFTER + after + A_MESSAGE_ID + message_id, HttpMethod.GET);
    }

    /**
     * <b>出金履歴</b><br>
     * <ul>
     * status
     * <li><b>PENDING</b>: 手続き中</li>
     * <li><b>COMPLETED</b>: 完了</li>
     * </ul>
     * 
     * @param count
     *            最大表示件数
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param message_id
     *            出金メッセージの受付のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 出金のID<br>
     *         <b>order_id</b> 注文のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>amount</b> 金額<br>
     *         <b>status</b> 状態<br>
     *         <b>event_date</b> 日時
     */
    @Override
    public String getWithdrawsAfter(int count, long after, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + Q_COUNT + count + A_AFTER + after + A_MESSAGE_ID + message_id,
                HttpMethod.GET);
    }

    @Override
    protected String createSignature(String apiSecret, String url, String nonce, HttpMethod method) {
        String message = nonce + method + url.substring(API.length()) + getParameters();
        return HMAC_SHA256Encode(apiSecret, message);
    }
}
