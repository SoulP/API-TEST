package moe.soulp.api_test.bitFlyer.dto;

/**
 * <b>約定の情報(GET)</b><br>
 * date: 2017/09/07 last_date: 2017/09/07
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 GetExecutionDTO作成
 */
public class ExecutionGetDTO extends Execution {
    private String buy_child_order_acceptance_id;  // 新規注文の買いのID
    private String sell_child_order_acceptance_id; // 新規注文の売りのID

    /**
     * <b>新規注文の買いのID 出力</b>
     * 
     * @return buy_child_order_acceptance_id
     */
    public String getBuyChildOrderAcceptanceId() {
        return buy_child_order_acceptance_id;
    }

    /**
     * <b>新規注文の買いのID 入力</b>
     * 
     * @param buy_child_order_acceptance_id
     *            新規注文の買いのID
     */
    public void setBuyChildOrderAcceptanceId(String buy_child_order_acceptance_id) {
        this.buy_child_order_acceptance_id = buy_child_order_acceptance_id;
    }

    /**
     * <b>新規注文の売りのID 出力</b>
     * 
     * @return sell_child_order_acceptance_id
     */
    public String getSellChildOrderAcceptanceId() {
        return sell_child_order_acceptance_id;
    }

    /**
     * <b>新規注文の売りのID 入力</b>
     * 
     * @param sell_child_order_acceptance_id
     *            新規注文の売りのID
     */
    public void setSellChildOrderAcceptanceId(String sell_child_order_acceptance_id) {
        this.sell_child_order_acceptance_id = sell_child_order_acceptance_id;
    }
}
