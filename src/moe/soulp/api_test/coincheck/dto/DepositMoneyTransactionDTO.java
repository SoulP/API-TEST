package moe.soulp.api_test.coincheck.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Status;

/**
 * <b>受け取り履歴</b><br>
 * date: 2017-08-26 last_date: 2017-08-26
 * 
 * @author ソウルP
 * @version 1.0 2017-08-26 DepostMoneyTransactionDTO作成
 * @see Status 状態
 */
public class DepositMoneyTransactionDTO extends SendMoneyTransactionDTO {
    private Status        status;       // 状態
    private ZonedDateTime confirmed_at; // 承認日時

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
