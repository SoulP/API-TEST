package moe.soulp.api_test.bitFlyer.dto;

import moe.soulp.api_test.api.Type;

/**
 * <b>注文と約定の情報</b><br>
 * date: 2017/09/08 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/08 OrderExecTransaction作成
 * @version 1.1 2017/09/21 価格のデータ・タイプをdoubleからlongに変更
 * @see Type 種類
 */
public abstract class OrderExecTransaction extends Transaction {
    private Type   side;  // 注文の種類
    private long   price; // 価格
    private double size;  // 量

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
    public long getPrice() {
        return price;
    }

    /**
     * <b>価格 入力</b>
     * 
     * @param price
     *            価格
     */
    public void setPrice(long price) {
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
}
