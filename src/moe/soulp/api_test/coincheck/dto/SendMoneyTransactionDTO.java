package moe.soulp.api_test.coincheck.dto;

import moe.soulp.api_test.api.Currency;

/**
 * <b>送金履歴</b><br>
 * date: 2017-08-26 last_date: 2017-08-28
 * 
 * @author ソウルP
 * @version 1.0 2017-08-26 SendMoneyTransaction作成
 * @see Transaction トランザクション
 * @see Currecny 通貨
 */
public class SendMoneyTransactionDTO extends Transaction {
    private double   amount;  // 量
    private Currency currency;// 通貨
    private double   fee;     // 手数料
    private String   address; // アドレス

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
     * <b>手数料 出力</b>
     * 
     * @return fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * <b>手数料 入力</b>
     * 
     * @param fee
     *            手数料
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * <b>アドレス 出力</b>
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * <b>アドレス 入力</b>
     * 
     * @param address
     *            アドレス
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
