package moe.soulp.api_test.coincheck.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Status;
import moe.soulp.api_test.api.Type;

/**
 * <b>ポジション</b><br>
 * date: 2017/08/26 last_date: 2017/08/26
 * 
 * @author ソウルP
 * @version 1.0 2017/08/26 Position作成
 * @see Type 種類
 * @see Status 状態
 */
public abstract class Position {
    private long          id;         // ID
    private Type          side;       // 種類
    private double        amount;     // 量
    private Status        status;     // 状態
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
     * <b>種類 出力</b>
     * 
     * @return side
     * @see Type 種類
     */
    public Type getSide() {
        return side;
    }

    /**
     * <b>種類 入力</b>
     * 
     * @param side
     *            種類
     * @see Type 種類
     */
    public void setSide(Type side) {
        this.side = side;
    }

    /**
     * <b>種類 入力</b>
     * 
     * @param side
     *            種類
     * @see Type 種類
     */
    public void setSide(String side) {
        this.side = Type.valueOf(side);
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
     * <b>作成日時 出力</b>
     * 
     * @return created_at
     */
    public ZonedDateTime getCreatedAt() {
        return created_at;
    }

    /**
     * <b>作成日時 入力</b>
     * 
     * @param created_at
     *            作成日時
     */
    public void setCreatedAt(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * <b>作成日時 入力</b>
     * 
     * @param created_at
     *            作成日時
     */
    public void setCreatedAt(String created_at) {
        this.created_at = Coincheckable.string2zonedDateTime(created_at);
    }
}
