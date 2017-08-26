package moe.soulp.api_test.coincheck.dto;

/**
 * <b>ポジション情報の注文情報</b><br>
 * date: 2017-08-22 last_date: 2017-08-26
 * 
 * @author ソウルP
 * @version 1.0 2017-08-22 PositionOrderDTO作成
 * @version 1.1 2017-08-26 継承することで不要な部分を排除
 * @see Position ポジション
 */
public class PositionOrderDTO extends Position {
    private double rate;           // 注文のレート
    private double pending_amount; // 注文の未決済の量

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

}
