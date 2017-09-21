package moe.soulp.api_test.bitFlyer.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import moe.soulp.api_test.api.Type;

/**
 * <b>新規の親注文の情報</b><br>
 * date: 2017/09/13 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/13 NewParentOrderDTP作成
 * @version 1.1 2017/09/21 一部除いて、ParentOrderに移動
 * @see Type 種類
 */
public class NewParentOrderDTO extends ParentOrder {
    private Type time_in_force; // 執行数量条件

    /**
     * <b>執行数量条件 出力</b>
     * 
     * @return time_in_force
     * @see Type 種類
     */
    public Type getTimeInForce() {
        return time_in_force;
    }

    /**
     * <b>執行数量条件 入力</b>
     * 
     * @param time_in_force
     *            執行数量条件
     * @see Type 種類
     */
    public void setTimeInForce(Type time_in_force) {
        this.time_in_force = time_in_force;
    }

    /**
     * <b>執行数量条件 入力</b>
     * 
     * @param time_in_force
     *            執行数量条件
     * @see Type 種類
     */
    public void setTimeInForce(String time_in_force) {
        this.time_in_force = Type.valueOf(time_in_force);
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
            if (order_method != null) temp.put(ORDER_METHOD, order_method);
            if (minute_to_expire != null) temp.put(MINUTE_TO_EXPIRE, minute_to_expire);
            if (time_in_force != null) temp.put(TIME_IN_FORCE, time_in_force);
            temp.put(PARAMETERS, parametersList);
            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
