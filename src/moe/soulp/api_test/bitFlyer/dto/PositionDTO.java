package moe.soulp.api_test.bitFlyer.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Type;

/**
 * <b>建玉の情報</b><br>
 * date: 2017/09/21 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/21 PositionDTO
 */
public class PositionDTO {
    private String        product_code;          // プロダクトコード
    private Type          side;                  // 注文の種類
    private long          price;                 // 価格
    private double        size;                  // 量
    private long          commission;            // 手数料
    private long          swap_point_accumulate; // スワップポイント
    private long          require_collateral;    // 必要証拠金
    private ZonedDateTime open_date;             // 日時
    private int           leverage;              // レバレッジ倍率
    private long          pnl;                   // 含み損益

    /**
     * <b>プロダクトコード 出力</b>
     * 
     * @return product_code
     */
    public String getProductCode() {
        return product_code;
    }

    /**
     * <b>プロダクトコード 入力</b>
     * 
     * @param product_code
     *            プロダクトコード
     */
    public void setProductCode(String product_code) {
        this.product_code = product_code;
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
    public long getPrice() {
        return price;
    }

    /**
     * <b>価格 入力</b>
     * 
     * @param price
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

    /**
     * <b>手数料 出力</b>
     * 
     * @return commision
     */
    public long getCommission() {
        return commission;
    }

    /**
     * <b>手数料 入力</b>
     * 
     * @param commission
     *            手数料
     */
    public void setCommission(long commission) {
        this.commission = commission;
    }

    /**
     * <b>スワップポイント 出力</b>
     * 
     * @return swap_point_accumulate
     */
    public long getSwapPointAccumulate() {
        return swap_point_accumulate;
    }

    /**
     * <b>スワップポイント 入力</b>
     * 
     * @param swap_point_accumulate
     *            スワップポイント
     */
    public void setSwapPointAccumulate(long swap_point_accumulate) {
        this.swap_point_accumulate = swap_point_accumulate;
    }

    /**
     * <b>必要証拠金 出力</b>
     * 
     * @return require_collateral
     */
    public long getRequireCollateral() {
        return require_collateral;
    }

    /**
     * <b>必要証拠金 入力</b>
     * 
     * @param require_collateral
     *            必要証拠金
     */
    public void setRequireCollateral(long require_collateral) {
        this.require_collateral = require_collateral;
    }

    /**
     * <b>日時 出力</b>
     * 
     * @return open_date
     */
    public ZonedDateTime getOpenDate() {
        return open_date;
    }

    /**
     * <b>日時 入力</b>
     * 
     * @param open_date
     *            日時
     */
    public void setOpenDate(ZonedDateTime open_date) {
        this.open_date = open_date;
    }

    /**
     * <b>日時 入力</b>
     * 
     * @param open_date
     *            日時
     */
    public void setOpenDate(String open_date) {
        this.open_date = BitFlyerable.string2zonedDateTime(open_date);
    }

    /**
     * <b>レバレッジ倍率 出力</b>
     * 
     * @return leverage
     */
    public int getLeverage() {
        return leverage;
    }

    /**
     * <b>レバレッジ倍率 入力</b>
     * 
     * @param leverage
     *            レバレッジ倍率
     */
    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    /**
     * <b>含み損益 出力</b>
     * 
     * @return pnl
     */
    public long getPnl() {
        return pnl;
    }

    /**
     * <b>含み損益 入力</b>
     * 
     * @param pnl
     *            含み損益
     */
    public void setPnl(long pnl) {
        this.pnl = pnl;
    }
}
