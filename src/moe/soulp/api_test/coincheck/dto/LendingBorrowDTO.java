package moe.soulp.api_test.coincheck.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Currency;

/**
 * <b>借入中の情報</b><br>
 * date: 2017/09/06 last_date: 2017/09/06
 * 
 * @author ソウルP
 * @version 1.0 2017/09/06 LendingBorrowDTO作成
 * @see Currency 通貨
 */
public class LendingBorrowDTO {
    private long          id;             // 借入中一覧のID
    private long          borrow_id;      // 借入申請のID
    private double        rate;           // 日当たりのレート
    private double        amount;         // 借りた量
    private double        pending_amount; // 借りている量（利子付き）
    private Currency      currency;       // 通貨
    private ZonedDateTime deadline;       // 返却期日

    /**
     * <b>借入中一覧のID 出力</b>
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * <b>借入中一覧のID 入力</b>
     * 
     * @param id
     *            借入中一覧のID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * <b>借入申請のID 出力</b>
     * 
     * @return borrow_id
     */
    public long getBorrowId() {
        return borrow_id;
    }

    /**
     * <b>借入申請のID 入力</b>
     * 
     * @param borrow_id
     *            借入申請のID
     */
    public void setBorrowId(long borrow_id) {
        this.borrow_id = borrow_id;
    }

    /**
     * <b>日当たりのレート 出力</b>
     * 
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * <b>日当たりのレート 入力</b>
     * 
     * @param rate
     *            日当たりのレート
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * <b>借りた量 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>借りた量 入力</b>
     * 
     * @param amount
     *            借りた量
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <b>借りている量（利子付き） 出力</b>
     * 
     * @return pending_amount
     */
    public double getPendingAmount() {
        return pending_amount;
    }

    /**
     * <b>借りている量（利子付き） 入力</b>
     * 
     * @param pending_amount
     *            借りている量（利子付き）
     */
    public void setPendingAmount(double pending_amount) {
        this.pending_amount = pending_amount;
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
     * <b>返却期日 出力</b>
     * 
     * @return deadline
     */
    public ZonedDateTime getDeadline() {
        return deadline;
    }

    /**
     * <b>返却期日 入力</b>
     * 
     * @param deadline
     *            返却期日
     */
    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * <b>返却期日 入力</b>
     * 
     * @param deadline
     *            返却期日
     */
    public void setDeadline(String deadline) {
        this.deadline = Coincheckable.string2zonedDateTime(deadline);
    }
}
