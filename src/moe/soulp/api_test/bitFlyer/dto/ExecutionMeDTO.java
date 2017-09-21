package moe.soulp.api_test.bitFlyer.dto;

/**
 * <b>約定の情報(自分)</b><br>
 * date: 2017/09/07 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 MeExecutionDTO作成
 * @version 1.1 2017/09/21 手数料のデータ・タイプをdoubleからlongに変更
 */
public class ExecutionMeDTO extends ExecutionTransaction {
    private String child_order_id;            // 注文のID
    private String child_order_acceptance_id; // 新規注文のID
    private long   commission;                // 手数料

    /**
     * <b>注文のID 出力</b>
     * 
     * @return child_order_id
     */
    public String getChildOrderId() {
        return child_order_id;
    }

    /**
     * <b>注文のID 入力</b>
     * 
     * @param child_order_id
     *            注文のID
     */
    public void setChildOrderId(String child_order_id) {
        this.child_order_id = child_order_id;
    }

    /**
     * <b>新規注文のID 出力</b>
     * 
     * @return child_order_acceptance_id
     */
    public String getChildOrderAcceptanceId() {
        return child_order_acceptance_id;
    }

    /**
     * <b>新規注文のID 入力</b>
     * 
     * @param child_order_acceptance_id
     *            新規注文のID
     */
    public void setChildOrderAcceptanceId(String child_order_acceptance_id) {
        this.child_order_acceptance_id = child_order_acceptance_id;
    }

    /**
     * <b>手数料 出力</b>
     * 
     * @return commission
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
}
