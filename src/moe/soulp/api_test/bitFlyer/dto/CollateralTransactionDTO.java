package moe.soulp.api_test.bitFlyer.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.coincheck.dto.Transaction;

/**
 * <b>証拠金の変動の情報</b><br>
 * date: 2017/09/21 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/21 CollateralTransactionDTO作成
 * @see Currency 通貨
 */
public class CollateralTransactionDTO extends Transaction {
    private Currency      currency_code; // 通貨
    private double        change;        // 証拠金の変動額
    private double        amount;        // 変動後の証拠金の残高
    private String        reason_code;   // 理由コード
    private ZonedDateTime date;          // 日時

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
     * <b>証拠金の変動額 出力</b>
     * 
     * @return change
     */
    public double getChange() {
        return change;
    }

    /**
     * <b>証拠金の変動額 入力</b>
     * 
     * @param change
     *            証拠金の変動額
     */
    public void setChange(double change) {
        this.change = change;
    }

    /**
     * <b>変動後の証拠金の残高 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>変動後の証拠金の残高 入力</b>
     * 
     * @param amount
     *            変動後の証拠金の残高
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <b>理由コード 出力</b>
     * 
     * @return reason_code
     */
    public String getReasonCode() {
        return reason_code;
    }

    /**
     * <b>理由コード 入力</b>
     * 
     * @param reason_code
     *            理由コード
     */
    public void setReasonCode(String reason_code) {
        this.reason_code = reason_code;
    }

    /**
     * <b>日時 出力</b>
     * 
     * @return date
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * <b>日時 入力</b>
     * 
     * @param date
     *            日時
     */
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    /**
     * <b>日時 入力</b>
     * 
     * @param date
     *            日時
     */
    public void setDate(String date) {
        this.date = BitFlyerable.string2zonedDateTime(date);
    }
}
