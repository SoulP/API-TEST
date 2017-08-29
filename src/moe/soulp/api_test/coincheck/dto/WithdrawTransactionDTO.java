package moe.soulp.api_test.coincheck.dto;

import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.api.Status;

/**
 * <b>出金履歴</b><br>
 * date: 2017/08/29 last_date: 2017/08/29
 * 
 * @author ソウルP
 * @version 1.0 2017/08/29 WithdrawTransactionDTO作成
 * @see Status 状態
 * @see Currency 通貨
 */
public class WithdrawTransactionDTO extends Transaction {
    private Status   status;          // 出金の状態
    private double   amount;          // 金額
    private Currency currency;        // 通貨
    private long     bank_account_id; // 銀行口座のID
    private double   fee;             // 手数料
    private boolean  is_fast;         // 高速出金のオプション

    /**
     * <b>出金の状態 出力</b>
     * 
     * @return status
     * @see Status 状態
     */
    public Status getStatus() {
        return status;
    }

    /**
     * <b>出金の状態 入力</b>
     * 
     * @param status
     *            出金の状態
     * @see Status 状態
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * <b>出金の状態 入力</b>
     * 
     * @param status
     *            出金の状態
     * @see Status 状態
     */
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    /**
     * <b>金額 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>金額 入力</b>
     * 
     * @param amount
     *            金額
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
     * <b>銀行口座のID 出力</b>
     * 
     * @return bank_account_id
     */
    public long getBankAccountId() {
        return bank_account_id;
    }

    /**
     * <b>銀行口座のID 入力</b>
     * 
     * @param bank_account_id
     *            銀行口座のID
     */
    public void setBankAccountId(long bank_account_id) {
        this.bank_account_id = bank_account_id;
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
     * <b>高速出金のオプション 出力</b>
     * 
     * @return is_fast
     */
    public boolean getIsFast() {
        return is_fast;
    }

    /**
     * <b>高速出金のオプション 入力</b>
     * 
     * @param is_fast
     *            高速出金のオプション
     */
    public void setIsFast(boolean is_fast) {
        this.is_fast = is_fast;
    }
}
