package moe.soulp.api_test.bitFlyer.dto;

/**
 * <b>ビットコイン・イーサ送付の情報</b><br>
 * date: 2017/09/08 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/09/08 CoinOutDTO作成
 */
public class CoinOutDTO extends CoinInDTO {
    private double fee;            // 手数料
    private double additional_fee; // 追加手数料

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
     * <b>追加手数料 出力</b>
     * 
     * @return additional_fee
     */
    public double getAdditionalFee() {
        return additional_fee;
    }

    /**
     * <b>追加手数料 入力</b>
     * 
     * @param additional_fee
     *            追加手数料
     */
    public void setAdditionalFee(double additional_fee) {
        this.additional_fee = additional_fee;
    }
}
