package moe.soulp.api_test.coincheck.dto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Type;

/**
 * <b>ポジション情報の注文情報</b><br>
 * date: 2017/08/22 last_date: 2017/08/22
 * 
 * @author ソウルP
 * @version 1.0 2017/08/22 PositionOrderDTO作成
 * @see Type 種類
 */
public class PositionOrderDTO {
    private long          id;             // ID
    private Type          side;           // 注文の種類
    private double        rate;           // 注文のレート
    private double        amount;         // 注文の量
    private double        pending_amount; // 注文の未決済の量
    private String        status;         // 注文の状態
    private ZonedDateTime created_at;     // 注文の作成日時

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
     * <b>注文の種類 出力</b>
     * 
     * @return side
     * @see Type 種類
     */
    public Type getSide() {
        return side;
    }

    /**
     * <b>注文の種類 入力</b>
     * 
     * @param side
     *            注文の種類
     * @see Type 種類
     */
    public void setSide(Type side) {
        this.side = side;
    }

    /**
     * <b>注文の種類 入力</b>
     * 
     * @param side
     *            注文の種類
     * @see Type 種類
     */
    public void setSide(String side) {
        this.side = Type.valueOf(side);
    }

    /**
     * <b>注文のレート 出力</b>
     * 
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * <b>注文のレート 入力</b>
     * 
     * @param rate
     *            注文のレート
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * <b>注文の量 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>注文の量 入力</b>
     * 
     * @param amount
     *            注文の量
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <b>注文の未決済の量 出力</b>
     * 
     * @return pending_amount
     */
    public double getPendingAmount() {
        return pending_amount;
    }

    /**
     * <b>注文の未決済の量 入力</b>
     * 
     * @param pending_amount
     *            注文の未決済の量
     */
    public void setPendingAmount(double pending_amount) {
        this.pending_amount = pending_amount;
    }

    /**
     * <b>注文の状態 出力</b>
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * <b>注文の状態 入力</b>
     * 
     * @param status
     *            注文の状態
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <b>注文の作成日時 出力</b>
     * 
     * @return created_at
     */
    public ZonedDateTime getCreatedAt() {
        return created_at;
    }

    /**
     * <b>注文の作成日時 入力</b>
     * 
     * @param created_at
     *            注文の作成日時
     */
    public void setCreatedAt(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * <b>注文の作成日時 入力</b>
     * 
     * @param created_at
     *            注文の作成日時
     */
    public void setCreatedAt(String created_at) {
        this.created_at = ZonedDateTime.parse(created_at, Coincheckable.FORMAT)
                .withZoneSameInstant(ZoneId.systemDefault());
    }
}
