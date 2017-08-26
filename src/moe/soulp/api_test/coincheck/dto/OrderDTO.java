package moe.soulp.api_test.coincheck.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;

/**
 * <b>未決済の注文</b><br>
 * date: 2017-08-06 last_date: 2017-08-26
 * 
 * @author ソウルP
 * @version 1.0 2017-08-06 未決済の注文DTO作成
 * @version 1.1 2017-08-21 setCreatedAt(String)追加
 * @version 1.2 2017-08-21 created_atのLocalDateTime型をZonedDateTimeに変更
 * @see Pair 取引ペア
 * @see Type 種類
 */
public class OrderDTO {
    private long          id;                        // 注文のID
    private Type          order_type;                // 注文の種類
    private double        rate;                      // 注文のレート
    private Pair          pair;                      // 取引ペア
    private double        pending_amount;            // 注文の未決済の量
    private double        pending_market_buy_amount; // 注文の未決済の量（現物成行買いの場合のみ）
    private double        stop_less_rate;            // 逆指値レート
    private ZonedDateTime created_at;                // 注文の作成日時

    /**
     * <b>注文のID 出力</b>
     * 
     * @return 注文のID
     */
    public long getId() {
        return id;
    }

    /**
     * <b>注文のID 入力</b>
     * 
     * @param id
     *            注文のID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * <b>注文の種類 出力</b>
     * 
     * @return 注文の種類
     * @see Type 種類
     */
    public Type getOrderType() {
        return order_type;
    }

    /**
     * <b>注文の種類 入力</b>
     * 
     * @param order_type
     *            注文の種類
     * @see Type 種類
     */
    public void setOrderType(String order_type) {
        setOrderType(Type.valueOf(order_type));
    }

    /**
     * <b>注文の種類 入力</b>
     * 
     * @param order_type
     *            注文の種類
     * @see Type 種類
     */
    public void setOrderType(Type order_type) {
        this.order_type = order_type;
    }

    /**
     * <b>注文のレート 出力</b>
     * 
     * @return 注文のレート
     */
    public double getRate() {
        return rate;
    }

    /**
     * <b>注文のレート 入力</b>
     * 
     * @param rate
     *            注文のレート
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * <b>取引ペア 出力</b>
     * 
     * @return 取引ペア
     * @see Pair 取引ペア
     */
    public Pair getPair() {
        return pair;
    }

    /**
     * <b>取引ペア 入力</b>
     * 
     * @param pair
     *            取引ペア
     * @see Pair 取引ペア
     */
    public void setPair(String pair) {
        if (!(pair == null || pair.isEmpty())) setPair(Pair.valueOf(pair));
    }

    /**
     * <b>取引ペア 入力</b>
     * 
     * @param pair
     *            取引ペア
     * @see Pair 取引ペア
     */
    public void setPair(Pair pair) {
        this.pair = pair;
    }

    /**
     * <b>注文の未決済の量 出力</b>
     * 
     * @return 注文の未決済の量
     */
    public double getPendingAmount() {
        return pending_amount;
    }

    /**
     * <b>注文の未決済の量 入力</b>
     * 
     * @param pending_amount
     *            注文の未決済の量
     */
    public void setPendingAmount(double pending_amount) {
        this.pending_amount = pending_amount;
    }

    /**
     * <b>注文の未決済の量 出力</b>
     * 
     * @return 注文の未決済の量
     */
    public double getPendingMarketBuyAmount() {
        return pending_market_buy_amount;
    }

    /**
     * <b>注文の未決済の量 入力</b>
     * 
     * @param pending_market_buy_amount
     *            注文の未決済の量
     */
    public void setPendingMarketBuyAmount(double pending_market_buy_amount) {
        this.pending_market_buy_amount = pending_market_buy_amount;
    }

    /**
     * <b>逆指値レート 出力</b>
     * 
     * @return 逆指値レート
     */
    public double getStopLessRate() {
        return stop_less_rate;
    }

    /**
     * <b>逆指値レート 入力</b>
     * 
     * @param stop_less_rate
     *            逆指値レート
     */
    public void setStopLessRate(double stop_less_rate) {
        this.stop_less_rate = stop_less_rate;
    }

    /**
     * <b>注文の作成日時 出力</b>
     * 
     * @return 注文の作成日時
     */
    public ZonedDateTime getCreatedAt() {
        return created_at;
    }

    /**
     * <b>注文の作成日時 入力</b>
     * 
     * @param created_at
     *            注文の作成日時
     */
    public void setCreatedAt(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * <b>注文の作成日時 入力</b>
     * 
     * @param created_at
     *            注文の作成日時
     */
    public void setCreatedAt(String created_at) {
        this.created_at = Coincheckable.string2zonedDateTime(created_at);
    }
}
