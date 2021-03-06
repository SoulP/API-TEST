package moe.soulp.api_test.bitFlyer.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.api.Status;

/**
 * <b>入出金の情報</b><br>
 * date: 2017/09/11 last_date: 2017/09/11
 * 
 * @author ソウルP
 * @version 1.0 2017/09/11 MoneyTransactionDTO作成
 * @see Currency 通貨
 * @see Status 状態
 */
public class MoneyTransactionDTO extends Transaction {
    private String        order_id;      // 注文のID
    private Currency      currency_code; // 通貨
    private double        amount;        // 量
    private Status        status;        // 状態
    private ZonedDateTime event_date;    // 日時

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
     * <b>通貨 出力</b>
     * 
     * @return currency_code
     * @see Currency 通貨
     */
    public Currency getCurrencyCode() {
        return currency_code;
    }

    /**
     * <b>通貨 入力</b>
     * 
     * @param currency_code
     *            通貨
     * @see Currency 通貨
     */
    public void setCurrencyCode(Currency currency_code) {
        this.currency_code = currency_code;
    }

    /**
     * <b>通貨 入力</b>
     * 
     * @param currency_code
     *            通貨
     * @see Currency 通貨
     */
    public void setCurrencyCode(String currency_code) {
        this.currency_code = Currency.valueOf(currency_code);
    }

    /**
     * <b>量 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>量 入力</b>
     * 
     * @param amount
     *            量
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <b>状態 出力</b>
     * 
     * @return status
     * @see Status 状態
     */
    public Status getStatus() {
        return status;
    }

    /**
     * <b>状態 入力</b>
     * 
     * @param status
     *            状態
     * @see Status 状態
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * <b>状態 入力</b>
     * 
     * @param status
     *            状態
     * @see Status 状態
     */
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    /**
     * <b>日時 出力</b>
     * 
     * @return event_date
     */
    public ZonedDateTime getEventDate() {
        return event_date;
    }

    /**
     * <b>日時 入力</b>
     * 
     * @param event_date
     *            日時
     */
    public void setEventDate(ZonedDateTime event_date) {
        this.event_date = event_date;
    }

    /**
     * <b>日時 入力</b>
     * 
     * @param event_date
     *            日時
     */
    public void setEventDate(String event_date) {
        this.event_date = BitFlyerable.string2zonedDateTime(event_date);
    }
}
