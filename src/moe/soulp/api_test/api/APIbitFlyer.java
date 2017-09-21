package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import moe.soulp.api_test.bitFlyer.dto.NewOrderDTO;
import moe.soulp.api_test.bitFlyer.dto.NewParentOrderDTO;

/**
 * <b>bitFlyerのAPI操作</b><br>
 * date: 2017/09/07 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 APIbitFlyer作成
 */
public class APIbitFlyer extends API implements BitFlyerable {
    private final static String Q_PRODUCT_CODE               = "?product_code=";
    private final static String Q_COUNT                      = "?count=";
    private final static String Q_BEFORE                     = "?before=";
    private final static String Q_AFTER                      = "?after=";
    private final static String Q_FROM_DATE                  = "?from_date=";
    private final static String Q_PARENT_ORDER_ID            = "?parent_order_id=";
    private final static String Q_PARENT_ORDER_ACCEPTANCE_ID = "?parent_order_acceptance_id=";

    private final static String A_COUNT                      = "&count=";
    private final static String A_BEFORE                     = "&before=";
    private final static String A_AFTER                      = "&after=";
    private final static String A_MESSAGE_ID                 = "&message_id=";
    private final static String A_CHILD_ORDER_STATE          = "&child_order_state=";
    private final static String A_CHILD_ORDER_ID             = "&child_order_id=";
    private final static String A_CHILD_ORDER_ACCEPTANCE_ID  = "&child_order_acceptance_id=";
    private final static String A_PARENT_ORDER_ID            = "&parent_order_id=";
    private final static String A_PARENT_ORDER_STATE         = "&parent_order_state=";

    private final static String Z                            = "Z";
    private final static String ACCESS_KEY                   = "ACCESS-KEY";
    private final static String ACCESS_TIMESTAMP             = "ACCESS-TIMESTAMP";
    private final static String ACCESS_SIGN                  = "ACCESS-SIGN";

    private final static String CURRENCY_CODE                = "currency_code";
    private final static String BANK_ACCOUNT_ID              = "bank_account_id";
    private final static String AMOUNT                       = "amount";
    private final static String CODE                         = "code";
    private final static String BODY                         = "Body";
    private final static String PRODUCT_CODE                 = "product_code";
    private final static String CHILD_ORDER_ID               = "child_order_id";
    private final static String PARENT_ORDER_ID              = "parent_order_id";
    private final static String ASKS                         = "asks";
    private final static String BIDS                         = "bids";
    private final static String PRICE                        = "price";

    private long                maxAsk                       = -1l;
    private long                minAsk                       = -1l;
    private long                maxBid                       = -1l;
    private long                minBid                       = -1l;
    private long                balanceJPY                   = -1l;
    private double              balanceBTC                   = -1.0d;

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
    private static URL          getBankAccountsURL;
    private static URL          withdrawURL;
    private static URL          sendOrderURL;
    private static URL          cancelOrderURL;
    private static URL          sendOrderSuperURL;
    private static URL          cancelOrderSuperURL;
    private static URL          cancelAllOrderURL;
    private static URL          getMyCollateralHistoryURL;

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
            getBankAccountsURL = new URL(API + GET_BANK_ACCOUNTS);
            withdrawURL = new URL(API + WITHDRAW);
            sendOrderURL = new URL(API + SEND_ORDER);
            cancelOrderURL = new URL(API + CANCEL_ORDER);
            sendOrderSuperURL = new URL(API + SEND_ORDER_SUPER);
            cancelOrderSuperURL = new URL(API + CANCEL_ORDER_SUPER);
            cancelAllOrderURL = new URL(API + CANCEL_ALL_ORDER);
            getMyCollateralHistoryURL = new URL(API + GET_MY_COLLATERAL_HISTORY);
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
     *         <b>product_code</b> プロダクトコード<br>
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
     *            プロダクトコード
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
        return getBoard(product_code.toString());
    }

    /**
     * <b>板情報</b>
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSON】<br>
     *         <b>mid_price</b> 仲値<br>
     *         <hr>
     *         bids 【JSONArray】 買取価格<br>
     *         【JSON】<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <hr>
     *         asks 【JSONArray】 販売価格<br>
     *         【JSON】<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量
     */
    @Override
    public String getBoard(String product_code) {
        return publicAPI(API + GET_BOARD + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>板情報</b><br>
     * 更新
     */
    @Override
    public void updateBoard() {
        try {
            JSONObject board = new JSONObject(getBoard());
            JSONArray asks = board.getJSONArray(ASKS);
            JSONArray bids = board.getJSONArray(BIDS);
            maxAsk = asks.getJSONObject((asks.length() - 1)).getLong(PRICE);
            minAsk = asks.getJSONObject(0).getLong(PRICE);
            maxBid = bids.getJSONObject(0).getLong(PRICE);
            minBid = bids.getJSONObject(bids.length() - 1).getLong(PRICE);
        } catch (JSONException | NullPointerException e) {
        }
    }

    /**
     * <b>板情報</b><br>
     * 売り注文
     *
     * @return 最高価格
     */
    @Override
    public long getMaxAsk() {
        return maxAsk;
    }

    /**
     * <b>板情報</b><br>
     * 売り注文
     *
     * @return 最低価格
     */
    @Override
    public long getMinAsk() {
        return minAsk;
    }

    /**
     * <b>板情報</b><br>
     * 買い注文
     *
     * @return 最高価格
     */
    @Override
    public long getMaxBid() {
        return maxBid;
    }

    /**
     * <b>板情報</b><br>
     * 買い注文
     *
     * @return 最低価格
     */
    @Override
    public long getMinBid() {
        return minBid;
    }

    /**
     * <b>Ticker</b><br>
     * product_code(デフォルト): BTC_JPY
     * 
     * @return 【JSON】<br>
     *         <b>product_code</b> プロダクトコード<br>
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
     *            プロダクトコード
     * @return 【JSON】<br>
     *         <b>product_code</b> プロダクトコード<br>
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
        return getTicker(product_code.toString());
    }

    /**
     * <b>Ticker</b>
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSON】<br>
     *         <b>product_code</b> プロダクトコード<br>
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
     *            プロダクトコード
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
        return getTrades(product_code, null, null, null);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            プロダクトコード
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
        return getTrades(product_code, null, null, null);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            プロダクトコード
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
    public String getTrades(Pair product_code, Integer count, Long before, Long after) {
        return getTrades(product_code.toString(), count, before, after);
    }

    /**
     * <b>約定履歴</b><br>
     * 
     * @param product_code
     *            プロダクトコード
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
    public String getTrades(String product_code, Integer count, Long before, Long after) {
        return publicAPI(API + GET_EXECUTIONS + Q_PRODUCT_CODE + product_code + setGetParametersA(count, before, after),
                HttpMethod.GET);
    }

    /**
     * <b>約定履歴</b><br>
     * product_code（デフォルト）: BTC_JPY
     * 
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
    public String getTrades(Integer count, Long before, Long after) {
        return getTrades(Pair.BTC_JPY, count, before, after);
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
    public void updateBalance() {
        try {
            JSONArray balance = new JSONArray(getBalance());
            for (int i = 0; i < balance.length(); i++) {
                JSONObject b = balance.getJSONObject(i);
                switch (b.getString(CURRENCY_CODE)) {
                    case "JPY":
                        balanceJPY = b.getLong(AMOUNT);
                        break;
                    case "BTC":
                        balanceBTC = b.getDouble(AMOUNT);
                        break;
                    default:
                        break;
                }
            }
        } catch (JSONException | NullPointerException e) {
        }
    }

    /**
     * <b>日本円の残高 出力</b>
     * 
     * @return 日本円
     */
    @Override
    public long getBalanceJPY() {
        return balanceJPY;
    }

    /**
     * <b>BTCの残高 出力</b>
     * 
     * @return BTC
     */
    @Override
    public double getBalanceBTC() {
        return balanceBTC;
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
    public String getDepositCoins(Integer count, Long before, Long after) {
        return privateAPI(API + GET_COIN_INS + setGetParametersQ(count, before, after), HttpMethod.GET);
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
        return getSendCoins(null, null, null);
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
    public String getSendCoins(Integer count, Long before, Long after) {
        return privateAPI(API + GET_COIN_OUTS + setGetParametersQ(count, before, after), HttpMethod.GET);
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
        return getDeposits(null, null, null);
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
    public String getDeposits(Integer count, Long before, Long after) {
        return privateAPI(API + GET_DEPOSITS + setGetParametersQ(count, before, after), HttpMethod.GET);
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
        JSONObject body = null;
        try {
            body = new JSONObject().put(CURRENCY_CODE, currency_code).put(BANK_ACCOUNT_ID, bank_account_id).put(AMOUNT,
                    amount);
            addParameter(BODY, body.toString());
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
        return getWithdraws(null, null, null);
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
        return getWithdraws(null, null, null, message_id);
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
    public String getWithdraws(Integer count, Long before, Long after) {
        return privateAPI(API + GET_WITHDRAWALS + setGetParametersQ(count, before, after), HttpMethod.GET);
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
    public String getWithdraws(Integer count, Long before, Long after, String message_id) {
        return privateAPI(API + GET_WITHDRAWALS + setGetParametersQ(count, before, after) + A_MESSAGE_ID + message_id,
                HttpMethod.GET);
    }

    /**
     * <b>新規注文</b><br>
     * 新規注文を出す
     * 
     * @param order
     *            新規注文の情報
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see NewOrderDTO 新規注文の情報
     */
    public String newOrder(NewOrderDTO order) {
        clearParameters();
        addParameter(BODY, order.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>指値注文 現物取引 買い</b><br>
     * 新規注文を出す<br>
     * product_code(デフォルト): BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param price
     *            価格
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderBuy(long price, double size) {
        return orderBuy(Pair.BTC_JPY, price, size);
    }

    /**
     * <b>指値注文 現物取引 買い</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param price
     *            価格
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see Pair 取引ペア
     */
    @Override
    public String orderBuy(Pair product_code, long price, double size) {
        return orderBuy(product_code.toString(), price, size);
    }

    /**
     * <b>指値注文 現物取引 買い</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param price
     *            価格
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderBuy(String product_code, long price, double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(product_code);
        body.setChildOrderType(Type.LIMIT);
        body.setSide(Type.BUY);
        body.setPrice(price);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>指値注文 現物取引 売り</b><br>
     * 新規注文を出す<br>
     * product_code（デフォルト）: BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param price
     *            価格
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderSell(long price, double size) {
        return orderSell(Pair.BTC_JPY, price, size);
    }

    /**
     * <b>指値注文 現物取引 売り</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param price
     *            価格
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see Pair 取引ペア
     */
    @Override
    public String orderSell(Pair product_code, long price, double size) {
        return orderSell(product_code.toString(), price, size);
    }

    /**
     * <b>指値注文 現物取引 売り</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param price
     *            価格
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderSell(String product_code, long price, double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(product_code);
        body.setChildOrderType(Type.LIMIT);
        body.setSide(Type.SELL);
        body.setPrice(price);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>成行注文 現物取引 買い</b><br>
     * 新規注文を出す<br>
     * product_code(デフォルト): BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderMarketBuy(double size) {
        return orderMarketBuy(Pair.BTC_JPY, size);
    }

    /**
     * <b>成行注文 現物取引 買い</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see Pair 取引ペア
     */
    @Override
    public String orderMarketBuy(Pair product_code, double size) {
        return orderMarketBuy(product_code.toString(), size);
    }

    /**
     * <b>成行注文 現物取引 買い</b><br>
     * 新規注文を出す<br>
     * product_code(デフォルト): BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderMarketBuy(String product_code, double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(product_code);
        body.setChildOrderType(Type.MARKET);
        body.setSide(Type.BUY);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>成行注文 現物取引 売り</b><br>
     * 新規注文を出す<br>
     * product_code(デフォルト): BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderMarketSell(double size) {
        return orderMarketSell(Pair.BTC_JPY, size);
    }

    /**
     * <b>成行注文 現物取引 売り</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see Pair 取引ペア
     */
    @Override
    public String orderMarketSell(Pair product_code, double size) {
        return orderMarketSell(product_code.toString(), size);
    }

    /**
     * <b>成行注文 現物取引 売り</b><br>
     * 新規注文を出す<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param product_code
     *            プロダクトコード
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String orderMarketSell(String product_code, double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(product_code);
        body.setChildOrderType(Type.MARKET);
        body.setSide(Type.SELL);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>成行注文 建玉取引 買い</b><br>
     * 新規注文を出す<br>
     * product_code（デフォルト）: FX_BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String leverageBuy(double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(Pair.FX_BTC_JPY.toString());
        body.setChildOrderType(Type.MARKET);
        body.setSide(Type.BUY);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>指値注文 建玉取引 買い</b><br>
     * 新規注文を出す<br>
     * product_code（デフォルト）: FX_BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param size
     *            量
     * @param price
     *            価格
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String leverageBuy(long price, double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(Pair.FX_BTC_JPY.toString());
        body.setChildOrderType(Type.LIMIT);
        body.setSide(Type.BUY);
        body.setPrice(price);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>成行注文 建玉取引 売り</b><br>
     * 新規注文を出す<br>
     * product_code（デフォルト）: FX_BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param size
     *            量
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String leverageSell(double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(Pair.FX_BTC_JPY.toString());
        body.setChildOrderType(Type.MARKET);
        body.setSide(Type.SELL);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>指値注文 建玉取引 売り</b><br>
     * 新規注文を出す<br>
     * product_code（デフォルト）: FX_BTC_JPY<br>
     * minute_to_expire（デフォルト）: 43200（30日間）<br>
     * time_in_force（デフォルト）: GTC
     * 
     * @param size
     *            量
     * @param price
     *            価格
     * @return 【JSON】<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String leverageSell(long price, double size) {
        clearParameters();
        NewOrderDTO body = new NewOrderDTO();
        body.setProductCode(Pair.FX_BTC_JPY.toString());
        body.setChildOrderType(Type.LIMIT);
        body.setSide(Type.BUY);
        body.setPrice(price);
        body.setSize(size);
        addParameter(BODY, body.toString());
        return privateAPI(sendOrderURL, HttpMethod.POST);
    }

    /**
     * <b>注文キャンセル</b><br>
     * 注文をキャンセルする<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @param child_order_id
     *            注文のID
     * @return 空のJSON
     */
    @Override
    public String deleteOrder(String child_order_id) {
        return deleteOrder(child_order_id, Pair.BTC_JPY);
    }

    /**
     * <b>注文キャンセル</b><br>
     * 注文をキャンセルする
     * 
     * @param child_order_id
     *            新規注文のID
     * @param product_code
     *            プロダクトコード
     * @return 空のJSON
     * @see Pair 取引ペア
     */
    @Override
    public String deleteOrder(String child_order_id, Pair product_code) {
        return deleteOrder(child_order_id, product_code.toString());
    }

    /**
     * <b>注文キャンセル</b><br>
     * 注文をキャンセルする
     * 
     * @param child_order_id
     *            新規注文のID
     * @param product_code
     *            プロダクトコード
     * @return 空のJSON
     */
    @Override
    public String deleteOrder(String child_order_id, String product_code) {
        clearParameters();
        try {
            JSONObject body = new JSONObject().put(PRODUCT_CODE, product_code).put(CHILD_ORDER_ID, child_order_id);
            addParameter(BODY, body.toString());
            privateAPI(cancelOrderURL, HttpMethod.POST);
            return new JSONObject().toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <b>新規の親注文（特殊注文）</b><br>
     * 新規の親注文を出す。
     * 
     * @param parent_order
     *            新規の親注文の情報
     * @return 【JSON】<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID
     * @see NewParentOrderDTO 新規の親注文の情報
     */
    public String newParentOrder(NewParentOrderDTO parent_order) {
        clearParameters();
        addParameter(BODY, parent_order.toString());
        return privateAPI(sendOrderSuperURL, HttpMethod.POST);
    }

    /**
     * <b>親注文キャンセル</b><br>
     * 親注文をキャンセルする<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @param parent_order_id
     *            親注文のID
     * @return 空のJSON
     */
    public String deleteParentOrder(String parent_order_id) {
        return deleteParentOrder(parent_order_id, Pair.BTC_JPY);
    }

    /**
     * <b>親注文キャンセル</b><br>
     * 親注文をキャンセルする
     * 
     * @param product_code
     *            プロダクトコード
     * @param parent_order_id
     *            親注文のID
     * @return 空のJSON
     * @see Pair 取引ペア
     */
    public String deleteParentOrder(String parent_order_id, Pair product_code) {
        return deleteParentOrder(parent_order_id, product_code.toString());
    }

    /**
     * <b>親注文キャンセル</b><br>
     * 親注文をキャンセルする
     * 
     * @param product_code
     *            プロダクトコード
     * @param parent_order_id
     *            親注文のID
     * @return 空のJSON
     */
    public String deleteParentOrder(String parent_order_id, String product_code) {
        clearParameters();
        try {
            JSONObject body = new JSONObject().put(PRODUCT_CODE, product_code).put(PARENT_ORDER_ID, parent_order_id);
            addParameter(BODY, body.toString());
            privateAPI(cancelOrderSuperURL, HttpMethod.POST);
            return new JSONObject().toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <b>全ての注文キャンセル</b><br>
     * 全ての注文をキャンセルする<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @return 空のJSON
     */
    @Override
    public String deleteAllOrders() {
        return deleteAllOrders(Pair.BTC_JPY);
    }

    /**
     * <b>全ての注文キャンセル</b><br>
     * 全ての注文をキャンセルする
     * 
     * @param product_code
     *            プロダクトコード
     * @return 空のJSON
     * @see Pair 取引ペア
     */
    @Override
    public String deleteAllOrders(Pair product_code) {
        return deleteAllOrders(product_code.toString());
    }

    /**
     * <b>全ての注文キャンセル</b><br>
     * 全ての注文をキャンセルする
     * 
     * @param product_code
     *            プロダクトコード
     * @return 空のJSON
     */
    @Override
    public String deleteAllOrders(String product_code) {
        clearParameters();
        try {
            addParameter(BODY, new JSONObject().put(PRODUCT_CODE, product_code).toString());
            privateAPI(cancelAllOrderURL, HttpMethod.POST);
            return new JSONObject().toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <b>未決済の注文一覧</b><br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    @Override
    public String getOrdersOpens() {
        return getOrdersOpens(Pair.BTC_JPY);
    }

    /**
     * <b>未決済の注文一覧</b>
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Pair 取引ペア
     */
    @Override
    public String getOrdersOpens(Pair product_code) {
        return getOrdersOpens(product_code.toString());
    }

    /**
     * <b>未決済の注文一覧</b>
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    @Override
    public String getOrdersOpens(String product_code) {
        return getOrdersOpens(product_code, null, null, null);
    }

    /**
     * <b>未決済の注文一覧</b>
     * 
     * @param product_code
     *            プロダクトコード
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
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Pair 取引ペア
     */
    @Override
    public String getOrdersOpens(Pair product_code, Integer count, Long before, Long after) {
        return getOrdersOpens(product_code.toString(), count, before, after);
    }

    /**
     * <b>未決済の注文一覧</b>
     * 
     * @param product_code
     *            プロダクトコード
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
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    @Override
    public String getOrdersOpens(String product_code, Integer count, Long before, Long after) {
        return privateAPI(API + GET_ORDERS + Q_PRODUCT_CODE + A_CHILD_ORDER_STATE + Status.ACTIVE + product_code
                + setGetParametersA(count, before, after), HttpMethod.GET);
    }

    /**
     * <b>未決済の注文一覧</b> product_code（デフォルト）: BTC_JPY
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
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    @Override
    public String getOrdersOpens(Integer count, Long before, Long after) {
        return getOrdersOpens(Pair.BTC_JPY, count, before, after);
    }

    /**
     * <b>未決済の注文 有無</b>
     *
     * @return <b>true</b> 未決済注文なし<br>
     *         <b>false</b> 未決済注文あり
     */
    @Override
    public boolean ordersIsEmpty() {
        try {
            return new JSONArray(getOrdersOpens()).length() == 0;
        } catch (NullPointerException e) {
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * <b>注文一覧</b><br>
     * 注文の一覧を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    @Override
    public String getOrders() {
        return getOrders(Pair.BTC_JPY);
    }

    /**
     * <b>注文一覧</b><br>
     * 注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Pair 取引ペア
     */
    @Override
    public String getOrders(Pair product_code) {
        return getOrders(product_code.toString());
    }

    /**
     * <b>注文一覧</b><br>
     * 注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    @Override
    public String getOrders(String product_code) {
        return privateAPI(API + GET_ORDERS + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>注文一覧</b><br>
     * <br>
     * 注文の一覧を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param child_order_state
     *            注文の状態
     * @param child_order_id
     *            注文のID
     * @param child_order_acceptance_id
     *            新規注文のID
     * @param parent_order_id
     *            親注文のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Status 状態
     */
    @Override
    public String getOrders(Integer count, Long before, Long after, Status child_order_state, String child_order_id,
            String child_order_acceptance_id, String parent_order_id) {
        return getOrders(Pair.BTC_JPY, count, before, after, child_order_state, child_order_id,
                child_order_acceptance_id, parent_order_id);
    }

    /**
     * <b>注文一覧</b><br>
     * 注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param child_order_state
     *            注文の状態
     * @param child_order_id
     *            注文のID
     * @param child_order_acceptance_id
     *            新規注文のID
     * @param parent_order_id
     *            親注文のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Pair 取引ペア
     * @see Status 状態
     */
    @Override
    public String getOrders(Pair product_code, Integer count, Long before, Long after, Status child_order_state,
            String child_order_id, String child_order_acceptance_id, String parent_order_id) {
        return getOrders(product_code.toString(), count, before, after, child_order_state, child_order_id,
                child_order_acceptance_id, parent_order_id);
    }

    /**
     * <b>注文一覧</b><br>
     * 注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param child_order_state
     *            注文の状態
     * @param child_order_id
     *            注文のID
     * @param child_order_acceptance_id
     *            新規注文のID
     * @param parent_order_id
     *            親注文のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 注文一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>child_order_type</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>child_order_stat</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>child_order_date</b> 注文日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Status 状態
     */
    @Override
    public String getOrders(String product_code, Integer count, Long before, Long after, Status child_order_state,
            String child_order_id, String child_order_acceptance_id, String parent_order_id) {
        String str = setGetParametersA(count, before, after);
        if (!(child_order_id == null || child_order_id.equals(""))) str += A_CHILD_ORDER_ID + child_order_id;
        if (!(child_order_acceptance_id == null || child_order_acceptance_id.equals("")))
            str += A_CHILD_ORDER_ACCEPTANCE_ID + child_order_acceptance_id;
        if (!(parent_order_id == null || parent_order_id.equals(""))) str += A_PARENT_ORDER_ID + parent_order_id;
        return privateAPI(API + GET_ORDERS + Q_PRODUCT_CODE + product_code + str, HttpMethod.GET);
    }

    /**
     * <b>親注文一覧</b><br>
     * 親注文の一覧を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>parent_order_type</b> 注文方法<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>parent_order_date</b> 日時<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    public String getParentOrders() {
        return getParentOrders(Pair.BTC_JPY);
    }

    /**
     * <b>親注文一覧</b><br>
     * 親注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>parent_order_type</b> 注文方法<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>parent_order_state</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>parent_order_date</b> 日時<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Pair 取引ペア
     */
    public String getParentOrders(Pair product_code) {
        return getParentOrders(product_code.toString());
    }

    /**
     * <b>親注文一覧</b><br>
     * 親注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>parent_order_type</b> 注文方法<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>parent_order_state</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>parent_order_date</b> 日時<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     */
    public String getParentOrders(String product_code) {
        return privateAPI(API + GET_ORDERS_SUPER + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>親注文一覧</b><br>
     * 親注文の一覧を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param parent_order_state
     *            注文の状態
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>parent_order_type</b> 注文方法<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>parent_order_state</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>parent_order_date</b> 日時<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Status 状態
     */
    public String getParentOrders(Integer count, Long before, Long after, Status parent_order_state) {
        return getParentOrders(Pair.BTC_JPY, count, before, after, parent_order_state);
    }

    /**
     * <b>親注文一覧</b><br>
     * 親注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param parent_order_state
     *            注文の状態
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>parent_order_type</b> 注文方法<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>parent_order_state</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>parent_order_date</b> 日時<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Pair 取引ペア
     * @see Status 状態
     */
    public String getParentOrders(Pair product_code, Integer count, Long before, Long after,
            Status parent_order_state) {
        return getParentOrders(product_code.toString(), count, before, after, parent_order_state);
    }

    /**
     * <b>親注文一覧</b><br>
     * 親注文の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param parent_order_state
     *            注文の状態
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>parent_order_type</b> 注文方法<br>
     *         <b>price</b> 価格<br>
     *         <b>average_price</b> 平均価格<br>
     *         <b>size</b> 量<br>
     *         <b>parent_order_state</b> 注文の状態<br>
     *         <b>expire_date</b> 有効期限<br>
     *         <b>parent_order_date</b> 日時<br>
     *         <b>parent_order_acceptance_id</b> 新規の親注文のID<br>
     *         <b>outstanding_size</b> 未決済の量<br>
     *         <b>cancel_size</b> キャンセルした量<br>
     *         <b>executed_size</b> 約定した量<br>
     *         <b>total_commission</b> 合計手数料
     * @see Status 状態
     */
    public String getParentOrders(String product_code, Integer count, Long before, Long after,
            Status parent_order_state) {
        String str = setGetParametersA(count, before, after);
        if (parent_order_state != null) str += A_PARENT_ORDER_STATE + parent_order_state;
        return privateAPI(API + GET_ORDERS_SUPER + Q_PRODUCT_CODE + product_code + str, HttpMethod.GET);
    }

    /**
     * <b>親注文詳細</b><br>
     * 親注文の詳細を取得
     * 
     * @param parent_order_id
     *            親注文のID
     * @param parent_order_acceptance_id
     *            新規の親注文のID
     * @return 【JSON】<br>
     *         <b>id</b> 親注文一覧のID<br>
     *         <b>parent_order_id</b> 親注文のID<br>
     *         <b>order_method</b> 注文方法<br>
     *         <b>minute_to_expire</b> 有効期限（分）<br>
     *         <hr>
     *         parameters 【JSONArray】<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>condition_type</b> 注文の種類<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>trigger_price</b> トリガー価格<br>
     *         <b>offset</b> トレール幅<br>
     *         <hr>
     *         <b>parrent_order_acceptance_id</b> 新規の親注文のID
     */
    public String getParentOrderInfo(String parent_order_id, String parent_order_acceptance_id) {
        return privateAPI(API + GET_ORDER_SUPER_INFO + ((parent_order_id == null || parent_order_id.equals(""))
                ? Q_PARENT_ORDER_ACCEPTANCE_ID + parent_order_acceptance_id : Q_PARENT_ORDER_ID + parent_order_id),
                HttpMethod.GET);
    }

    /**
     * <b>約定一覧</b><br>
     * 約定の一覧を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 約定一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String getOrdersTransactions() {
        return getOrdersTransactions(Pair.BTC_JPY);
    }

    /**
     * <b>約定一覧</b><br>
     * 約定の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 約定一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see Pair 取引ペア
     */
    @Override
    public String getOrdersTransactions(Pair product_code) {
        return getOrdersTransactions(product_code.toString());
    }

    /**
     * <b>約定一覧</b><br>
     * 約定の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 約定一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String getOrdersTransactions(String product_code) {
        return privateAPI(API + GET_EXECUTIONS_ME + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    /**
     * <b>約定一覧</b><br>
     * 約定の一覧を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param child_order_id
     *            注文のID
     * @param child_order_acceptance_id
     *            新規注文のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 約定一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String getOrdersTransactions(Integer count, Long before, Long after, String child_order_id,
            String child_order_acceptance_id) {
        return getOrdersTransactions(Pair.BTC_JPY, count, before, after, child_order_id, child_order_acceptance_id);
    }

    /**
     * <b>約定一覧</b><br>
     * 約定の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param child_order_id
     *            注文のID
     * @param child_order_acceptance_id
     *            新規注文のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 約定一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     * @see Pair 取引ペア
     */
    @Override
    public String getOrdersTransactions(Pair product_code, Integer count, Long before, Long after,
            String child_order_id, String child_order_acceptance_id) {
        return getOrdersTransactions(product_code.toString(), count, before, after, child_order_id,
                child_order_acceptance_id);
    }

    /**
     * <b>約定一覧</b><br>
     * 約定の一覧を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @param count
     *            最大表示件数
     * @param before
     *            ID, 指定する値より前のIDを持つデータ取得<br>
     *            ID < before
     * @param after
     *            ID, 指定する値より後のIDを持つデータ取得<br>
     *            after < ID
     * @param child_order_id
     *            注文のID
     * @param child_order_acceptance_id
     *            新規注文のID
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 約定一覧のID<br>
     *         <b>child_order_id</b> 注文のID<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>exec_date</b> 約定日時<br>
     *         <b>child_order_acceptance_id</b> 新規注文のID
     */
    @Override
    public String getOrdersTransactions(String product_code, Integer count, Long before, Long after,
            String child_order_id, String child_order_acceptance_id) {
        String str = setGetParametersA(count, before, after);
        if (!(child_order_id == null || child_order_id.equals(""))) str += A_CHILD_ORDER_ID + child_order_id;
        else if (!(child_order_acceptance_id == null || child_order_acceptance_id.equals("")))
            str += A_CHILD_ORDER_ACCEPTANCE_ID + child_order_acceptance_id;
        return privateAPI(API + GET_EXECUTIONS_ME + Q_PRODUCT_CODE + product_code + str, HttpMethod.GET);
    }

    /**
     * <b>建玉一覧</b><br>
     * 建玉の一覧を取得
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>product_code</b> プロダクトコード<br>
     *         <b>side</b> 注文の種類<br>
     *         <b>price</b> 価格<br>
     *         <b>size</b> 量<br>
     *         <b>commission</b> 手数料<br>
     *         <b>swap_point_accumulate</b> スワップポイント<br>
     *         <b>require_collateral</b> 必要証拠金<br>
     *         <b>open_date</b> 日時<br>
     *         <b>leverage</b> レバレッジ倍率<br>
     *         <b>pnl</b> 含み損益
     */
    @Override
    public String getPositions() {
        return privateAPI(API + GET_POSITIONS + Q_PRODUCT_CODE + Pair.FX_BTC_JPY, HttpMethod.GET);
    }

    /**
     * <b>証拠金の変動履歴</b><br>
     * 証拠金の変動履歴を取得
     * 
     * @return 【JSONArray】<br>
     *         <hr>
     *         【JSON】<br>
     *         <b>id</b> 証拠金の変動履歴のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>change</b> 証拠金の変動額<br>
     *         <b>amount</b> 変動後の証拠金の残高<br>
     *         <b>reason_code</b> 理由コード<br>
     *         <b>date</b> 日時
     */
    @Override
    public String getMyCollateralHistory() {
        return privateAPI(getMyCollateralHistoryURL, HttpMethod.GET);
    }

    /**
     * <b>証拠金の変動履歴</b><br>
     * 証拠金の変動履歴を取得
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
     *         <b>id</b> 証拠金の変動履歴のID<br>
     *         <b>currency_code</b> 通貨<br>
     *         <b>change</b> 証拠金の変動額<br>
     *         <b>amount</b> 変動後の証拠金の残高<br>
     *         <b>reason_code</b> 理由コード<br>
     *         <b>date</b> 日時
     */
    @Override
    public String getMyCollateralHistory(Integer count, Long before, Long after) {
        return privateAPI(API + GET_MY_COLLATERAL_HISTORY + setGetParametersQ(count, before, after), HttpMethod.GET);
    }

    /**
     * <b>取引手数料</b><br>
     * 取引手数料を取得<br>
     * product_code（デフォルト）: BTC_JPY
     * 
     * @return 【JSON】<br>
     *         <b>commission_rate</b> 手数料
     */
    @Override
    public String getTradingCommission() {
        return getTradingCommission(Pair.BTC_JPY);
    }

    /**
     * <b>取引手数料</b><br>
     * 取引手数料を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSON】<br>
     *         <b>commission_rate</b> 手数料
     * @see Pair 取引ペア
     */
    @Override
    public String getTradingCommission(Pair product_code) {
        return getTradingCommission(product_code.toString());
    }

    /**
     * <b>取引手数料</b><br>
     * 取引手数料を取得
     * 
     * @param product_code
     *            プロダクトコード
     * @return 【JSON】<br>
     *         <b>commission_rate</b> 手数料
     */
    @Override
    public String getTradingCommission(String product_code) {
        return privateAPI(API + GET_TRADING_COMMISSION + Q_PRODUCT_CODE + product_code, HttpMethod.GET);
    }

    @Override
    protected String createSignature(String apiSecret, String url, String nonce, HttpMethod method) {
        String message = nonce + method + url.substring(API.length()) + getParameters();
        return HMAC_SHA256Encode(apiSecret, message);
    }

    private String setGetParametersQ(Integer count, Long before, Long after) {
        String parameters = "";
        if (count != null) {
            parameters += Q_COUNT + count;
            if (before != null) parameters += A_BEFORE + before;
            if (after != null) parameters += A_AFTER + after;
        } else if (before != null) {
            parameters += Q_BEFORE + before;
            if (after != null) parameters += A_AFTER + after;
        } else if (after != null) parameters += Q_AFTER + after;
        return parameters;
    }

    private String setGetParametersA(Integer count, Long before, Long after) {
        String parameters = "";
        if (count != null) parameters += A_COUNT + count;
        if (before != null) parameters += A_BEFORE + before;
        if (after != null) parameters += A_AFTER + after;
        return parameters;
    }
}
