package moe.soulp.api_test.bitFlyer.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Status;
import moe.soulp.api_test.api.Type;

/**
 * <b>注文の情報</b><br>
 * date: 2017/09/08 last_date: 2017/09/14
 * 
 * @author ソウルP
 * @version 1.0 2017/09/08 OrderDTO作成
 * @see Type 種類
 * @see Status 状態
 */
public class OrderDTO extends OrderExecTransaction {
    private String        order_id;            // 注文のID
    private String        product_code;        // プロダクトコード
    private Type          order_type;          // 注文の種類
    private double        average_price;       // 平均価格
    private Status        order_state;         // 注文の状態
    private ZonedDateTime expire_date;         // 有効期限
    private ZonedDateTime order_date;          // 注文日時
    private String        order_acceptance_id; // 新規注文のID
    private double        outstanding_size;    // 未払いの量
    private double        cancel_size;         // キャンセルした量
    private double        executed_size;       // 約定した量
    private double        total_commission;    // 合計手数料

    /**
     * <b>注文のID 出力</b>
     * 
     * @return order_id
     */
    public String getOrderId() {
        return order_id;
    }

    /**
     * <b>注文のID 入力</b>
     * 
     * @param order_id
     *            注文のID
     */
    public void setOrderId(String order_id) {
        this.order_id = order_id;
    }

    /**
     * <b>プロダクトコード 出力</b>
     * 
     * @return product_code
     */
    public String getProductCode() {
        return product_code;
    }

    /**
     * <b>プロダクトコード 入力</b>
     * 
     * @param product_code
     *            プロダクトコード
     * 
     */
    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    /**
     * <b>注文の種類 出力</b>
     * 
     * @return order_type;
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
    public void setOrderType(Type order_type) {
        this.order_type = order_type;
    }

    /**
     * <b>注文の種類 入力</b>
     * 
     * @param order_type
     *            注文の種類
     * @see Type 種類
     */
    public void setOrderType(String order_type) {
        this.order_type = Type.valueOf(order_type);
    }

    /**
     * <b>平均価格 出力</b>
     * 
     * @return average_price
     */
    public double getAveragePrice() {
        return average_price;
    }

    /**
     * <b>平均価格 入力</b>
     * 
     * @param average_price
     *            平均価格
     */
    public void setAveragePrice(double average_price) {
        this.average_price = average_price;
    }

    /**
     * <b>注文の状態 出力</b>
     * 
     * @return order_state
     * @see Status 状態
     */
    public Status getOrderState() {
        return order_state;
    }

    /**
     * <b>注文のの状態 入力</b>
     * 
     * @param order_state
     *            注文の種類
     * @see Status 状態
     */
    public void setOrderState(Status order_state) {
        this.order_state = order_state;
    }

    /**
     * <b>注文の状態 入力</b>
     * 
     * @param order_state
     *            注文の状態
     * @see Status 状態
     */
    public void setOrderState(String order_state) {
        this.order_state = Status.valueOf(order_state);
    }

    /**
     * <b>有効期限 出力</b>
     * 
     * @return expire_date
     */
    public ZonedDateTime getExpireDate() {
        return expire_date;
    }

    /**
     * <b>有効期限 入力</b>
     * 
     * @param expire_date
     *            有効期限
     */
    public void setExpireDate(ZonedDateTime expire_date) {
        this.expire_date = expire_date;
    }

    /**
     * <b>有効期限 入力</b>
     * 
     * @param expire_date
     *            有効期限
     */
    public void setExpireDate(String expire_date) {
        this.expire_date = BitFlyerable.string2zonedDateTime(expire_date);
    }

    /**
     * <b>注文日時 出力</b>
     * 
     * @return order_date
     */
    public ZonedDateTime getOrderDate() {
        return order_date;
    }

    /**
     * <b>注文日時 入力</b>
     * 
     * @param order_date
     *            注文日時
     */
    public void setOrderDate(ZonedDateTime order_date) {
        this.order_date = order_date;
    }

    /**
     * <b>注文日時 入力</b>
     * 
     * @param order_date
     *            注文日時
     */
    public void setOrderDate(String order_date) {
        this.order_date = BitFlyerable.string2zonedDateTime(order_date);
    }

    /**
     * <b>新規注文のID 出力</b>
     * 
     * @return order_acceptance_id
     */
    public String getOrderAcceptanceId() {
        return order_acceptance_id;
    }

    /**
     * <b>新規注文のID 入力</b>
     * 
     * @param order_acceptance_id
     *            新規注文のID
     */
    public void setOrderAcceptanceId(String order_acceptance_id) {
        this.order_acceptance_id = order_acceptance_id;
    }

    /**
     * <b>未払いの量 出力</b>
     * 
     * @return outstanding_size
     */
    public double getOutstandingSize() {
        return outstanding_size;
    }

    /**
     * <b>未払いの量 入力</b>
     * 
     * @param outstanding_size
     *            未払いの量
     */
    public void setOutstandingSize(double outstanding_size) {
        this.outstanding_size = outstanding_size;
    }

    /**
     * <b>キャンセルした量 出力</b>
     * 
     * @return cancel_size
     */
    public double getCancelSize() {
        return cancel_size;
    }

    /**
     * <b>キャンセルした量 入力</b>
     * 
     * @param cancel_size
     *            キャンセルした量
     */
    public void setCancelSize(double cancel_size) {
        this.cancel_size = cancel_size;
    }

    /**
     * <b>約定した量 出力</b>
     * 
     * @return executed_size
     */
    public double getExecutedSize() {
        return executed_size;
    }

    /**
     * <b>約定した量 入力</b>
     * 
     * @param executed_size
     */
    public void setExecutedSize(double executed_size) {
        this.executed_size = executed_size;
    }

    /**
     * <b>合計手数料 出力</b>
     * 
     * @return total_commission
     */
    public double getTotalCommission() {
        return total_commission;
    }

    /**
     * <b>合計手数料 入力</b>
     * 
     * @param total_commission
     *            合計手数料
     */
    public void setTotalCommission(double total_commission) {
        this.total_commission = total_commission;
    }
}
