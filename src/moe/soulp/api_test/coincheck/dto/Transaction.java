package moe.soulp.api_test.coincheck.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Currency;

/**
 * <b>トランザクション</b><br>
 * date: 2017-08-26 last_date: 2017-08-26
 * 
 * @author ソウルP
 * @version 1.0 2017-08-26 Transaction作成
 * @see Currency 通貨
 */
public abstract class Transaction {
    private long          id;         // ID
    private double        fee;        // 手数料
    private ZonedDateTime created_at; // 作成日時

    /**
     * <b>ID 出力</b>
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * <b>ID 入力</b>
     * 
     * @param id
     *            ID
     */
    public void setId(long id) {
        this.id = id;
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
     * <b>取引日時 出力</b>
     * 
     * @return created_at
     */
    public ZonedDateTime getCreatedAt() {
        return created_at;
    }

    /**
     * <b>取引日時 入力</b>
     * 
     * @param created_at
     *            取引日時
     */
    public void setCreatedAt(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * <b>取引日時 入力</b>
     * 
     * @param created_at
     *            取引日時
     * @see Coincheckable
     */
    public void setCreatedAt(String created_at) {
        this.created_at = Coincheckable.string2zonedDateTime(created_at);
    }
}
