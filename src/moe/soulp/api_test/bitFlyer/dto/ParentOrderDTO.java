package moe.soulp.api_test.bitFlyer.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <b>親注文の情報</b><br>
 * date: 2017/09/21 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/21 ParentOrderDTO作成
 */
public class ParentOrderDTO extends ParentOrder {
    private long   id;                         // 親注文一覧のID
    private String parent_order_id;            // 親注文のID
    private String parent_order_acceptance_id; // 新規の親注文のID

    /**
     * <b>親注文一覧のID 出力</b>
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * <b>親注文一覧のID 入力</b>
     * 
     * @param id
     *            親注文一覧のID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * <b>親注文のID 出力</b>
     * 
     * @return parent_order_id
     */
    public String getParentOrderId() {
        return parent_order_id;
    }

    /**
     * <b>親注文のID 入力</b>
     * 
     * @param parent_order_id
     *            親注文のID
     */
    public void setParentOrderId(String parent_order_id) {
        this.parent_order_id = parent_order_id;
    }

    /**
     * <b>新規の親注文のID 出力</b>
     * 
     * @return parent_order_acceptance_id
     */
    public String getParentOrderAcceptanceId() {
        return parent_order_acceptance_id;
    }

    /**
     * <b>新規の親注文のID 入力</b>
     * 
     * @param parent_order_acceptance_id
     *            新規の親注文のID
     */
    public void setParentOrderAcceptanceId(String parent_order_acceptance_id) {
        this.parent_order_acceptance_id = parent_order_acceptance_id;
    }

    /**
     * <b>JSON 出力</b>
     * 
     * @return
     */
    @Override
    public JSONObject toJson() {
        JSONObject temp = new JSONObject();
        JSONArray parametersList = new JSONArray();
        parameters.forEach(p -> {
            JSONObject param = new JSONObject();
            try {
                param.put(PRODUCT_CODE, p.product_code);
                param.put(CONDITION_TYPE, p.condition_type);
                param.put(SIDE, p.side);
                param.put(SIZE, p.size);
                if (p.price != null) param.put(PRICE, p.price);
                if (p.trigger_price != null) param.put(TRIGGER_PRICE, p.trigger_price);
                if (p.offset != null) param.put(OFFSET, p.offset);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            parametersList.put(param);
        });

        try {
            temp.put("id", id);
            temp.put("parent_order_id", parent_order_id);
            temp.put(ORDER_METHOD, order_method);
            temp.put(MINUTE_TO_EXPIRE, minute_to_expire);
            temp.put(PARAMETERS, parametersList);
            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
