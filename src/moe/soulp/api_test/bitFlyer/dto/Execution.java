package moe.soulp.api_test.bitFlyer.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Type;

/**
 * <b>約定の情報</b><br>
 * date: 2017/09/07 last_date: 2017/09/07
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 Execution作成
 * @see Type 種類
 */
public abstract class Execution {
    private long          id;        // ID
    private Type          side;      // 注文の種類
    private double        price;     // 価格
    private double        size;      // 量
    private ZonedDateTime exec_date; // 約定日時

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
     * <b>価格 出力</b>
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * <b>価格 入力</b>
     * 
     * @param price
     *            価格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * <b>量 出力</b>
     * 
     * @return size
     */
    public double getSize() {
        return size;
    }

    /**
     * <b>量 入力</b>
     * 
     * @param size
     *            量
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * <b>約定日時 出力</b>
     * 
     * @return exec_date
     */
    public ZonedDateTime getExecDate() {
        return exec_date;
    }

    /**
     * <b>約定日時 入力</b>
     * 
     * @param exec_date
     *            約定日時
     */
    public void setExecDate(ZonedDateTime exec_date) {
        this.exec_date = exec_date;
    }

    /**
     * <b>約定日時 入力</b>
     * 
     * @param exec_date
     *            約定日時
     */
    public void setExecDate(String exec_date) {
        this.exec_date = BitFlyerable.string2zonedDateTime(exec_date);
    }
}
