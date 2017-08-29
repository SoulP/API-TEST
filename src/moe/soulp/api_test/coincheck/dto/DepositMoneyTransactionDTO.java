package moe.soulp.api_test.coincheck.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.api.Status;

/**
 * <b>受け取り履歴</b><br>
 * date: 2017/08/26 last_date: 2017/08/29
 * 
 * @author ソウルP
 * @version 1.0 2017/08/26 DepostMoneyTransactionDTO作成
 * @see Currency 通貨
 * @see Status 状態
 */
public class DepositMoneyTransactionDTO extends Transaction {
    private double        amount;       // 受け取った量
    private Currency      currency;     // 通貨
    private String        address;      // 受け取り元のアドレス
    private Status        status;       // 状態
    private ZonedDateTime confirmed_at; // 承認日時

    /**
     * <b>受け取った量 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>受け取った量 入力</b>
     * 
     * @param amount
     *            受け取った量
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <b>通貨 出力</b>
     * 
     * @return currency
     * @see Currency 通貨
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * <b>通貨 入力</b>
     * 
     * @param currency
     *            通貨
     * @see Currency 通貨
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * <b>通貨 入力</b>
     * 
     * @param currency
     *            通貨
     * @see Currency 通貨
     */
    public void setCurrency(String currency) {
        this.currency = Currency.valueOf(currency);
    }

    /**
     * <b>受け取り元のアドレス 出力</b>
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * <b>受け取り元のアドレス 入力</b>
     * 
     * @param address
     *            受け取り元のアドレス
     */
    public void setAddress(String address) {
        this.address = address;
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
     * <b>承認日時 出力</b>
     * 
     * @return confirmed_at
     */
    public ZonedDateTime getConfirmedAt() {
        return confirmed_at;
    }

    /**
     * <b>承認日時 入力</b>
     * 
     * @param confirmed_at
     *            承認日時
     */
    public void setConfirmedAt(ZonedDateTime confirmed_at) {
        this.confirmed_at = confirmed_at;
    }

    /**
     * <b>承認日時 入力</b>
     * 
     * @param confirmed_at
     *            承認日時
     */
    public void setConfirmedAt(String confirmed_at) {
        this.confirmed_at = Coincheckable.string2zonedDateTime(confirmed_at);
    }
}
