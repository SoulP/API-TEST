package moe.soulp.api_test.bitFlyer.dto;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;

/**
 * <b>新規の親注文の情報</b><br>
 * date: 2017/09/13 last_date: 2017/09/15
 * 
 * @author ソウルP
 * @version 1.0 2017/09/13 NewParentOrderDTP作成
 * @see Type 種類
 */
public class NewParentOrderDTO {
    private Type                order_method;                         // 注文方法
    private Integer             minute_to_expire;                     // 有効期限（分）
    private Type                time_in_force;                        // 執行数量条件
    private List<Parameter>     parameters;                           // 注文内容の一覧
    private Parameter           parameter;                            // 注文内容

    private final static String ORDER_METHOD     = "order_method";
    private final static String MINUTE_TO_EXPIRE = "minute_to_expire";
    private final static String TIME_IN_FORCE    = "time_in_force";
    private final static String PARAMETERS       = "parameters";
    private final static String PRODUCT_CODE     = "product_code";
    private final static String CONDITION_TYPE   = "condition_type";
    private final static String SIDE             = "side";
    private final static String SIZE             = "size";
    private final static String PRICE            = "price";
    private final static String TRIGGER_PRICE    = "trigger_price";
    private final static String OFFSET           = "offset";

    /**
     * <b>注文方法 出力</b>
     * 
     * @return order_method
     * @see Type 種類
     */
    public Type getOrderMethod() {
        return order_method;
    }

    /**
     * <b>注文方法 入力</b>
     * 
     * @param order_method
     *            注文方法
     * @see Type 種類
     */
    public void setOrderMethod(Type order_method) {
        this.order_method = order_method;
    }

    /**
     * <b>注文方法 入力</b>
     * 
     * @param order_method
     *            注文方法
     * @see Type 種類
     */
    public void setOrderMethod(String order_method) {
        this.order_method = Type.valueOf(order_method);
    }

    /**
     * <b>有効期限（分） 出力</b>
     * 
     * @return minute_to_expire
     */
    public Integer getMinuteToExpire() {
        return minute_to_expire;
    }

    /**
     * <b>有効期限（分） 出力</b>
     * 
     * @param minute_to_expire
     *            有効期限（分）
     */
    public void setMinuteToExpire(Integer minute_to_expire) {
        this.minute_to_expire = minute_to_expire;
    }

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
     * <b>注文内容一覧全てクリア</b>
     */
    public void clearParameters() {
        parameters.clear();
    }

    /**
     * <b>注文内容全てクリア</b>
     */
    public void clearParameter() {
        parameter = null;
    }

    /**
     * <b>プロダクトコード 入力</b><br>
     * 注文内容
     * 
     * @param product_code
     *            プロダクトコード
     */
    public void setProductCode(String product_code) {
        checkParameter(parameter);
        parameter.product_code = product_code;
    }

    /**
     * <b>注文の種類 入力</b><br>
     * 注文内容
     * 
     * @param condition_type
     *            注文の種類
     * @see Type 種類
     */
    public void setConditionType(Type condition_type) {
        checkParameter(parameter);
        parameter.condition_type = condition_type;
    }

    /**
     * <b>注文の種類 入力</b><br>
     * 注文内容
     * 
     * @param condition_type
     *            注文の種類
     * @see Type 種類
     */
    public void setConditionType(String condition_type) {
        checkParameter(parameter);
        parameter.condition_type = Type.valueOf(condition_type);
    }

    /**
     * <b>注文の種類 入力</b><br>
     * 注文内容
     * 
     * @param side
     *            注文の種類
     * @see Type 種類
     */
    public void setSide(Type side) {
        checkParameter(parameter);
        parameter.side = side;
    }

    /**
     * <b>注文の種類 入力</b><br>
     * 注文内容
     * 
     * @param side
     *            注文の種類
     * @see Type 種類
     */
    public void setSide(String side) {
        checkParameter(parameter);
        parameter.side = Type.valueOf(side);
    }

    /**
     * <b>量 入力</b><br>
     * 注文内容
     * 
     * @param size
     *            量
     */
    public void setSize(double size) {
        checkParameter(parameter);
        parameter.size = size;
    }

    /**
     * <b>価格 入力</b><br>
     * 注文内容
     * 
     * @param price
     *            価格
     */
    public void setPrice(long price) {
        checkParameter(parameter);
        parameter.price = price;
    }

    /**
     * <b>トリガー価格 入力</b><br>
     * 注文内容
     * 
     * @param trigger_price
     *            トリガー価格
     */
    public void setTriggerPrice(long trigger_price) {
        checkParameter(parameter);
        parameter.trigger_price = trigger_price;
    }

    /**
     * <b>トレール幅 入力</b><br>
     * 注文内容
     * 
     * @param offset
     *            トレール幅
     */
    public void setOffset(long offset) {
        checkParameter(parameter);
        parameter.offset = offset;
    }

    /**
     * <b>確定</b><br>
     * 入力した注文内容を注文内容一覧に追加し、また新たな注文内容入力可能にするため、初期化されます。
     */
    public void commit() {
        if (parameter != null) parameters.add(parameter);
        parameter = null;
    }

    /**
     * <b>JSON 出力</b>
     * 
     * @return
     */
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

    /**
     * <b>JSONの文字列 出力</b>
     */
    @Override
    public String toString() {
        return toJson().toString();
    }

    private void checkParameter(Parameter parameter) {
        if (parameter == null) parameter = new Parameter();
    }

    /**
     * <b>注文内容</b>
     * 
     * @see Pair 取引ペア
     */
    public class Parameter {
        private String product_code;   // プロダクトコード
        private Type   condition_type; // 注文の種類
        private Type   side;           // 注文の種類
        private double size;           // 量
        private Long   price;          // 価格
        private Long   trigger_price;  // トリガー価格
        private Long   offset;         // トレール幅

        /**
         * <b>注文内容</b><br>
         * product_code（デフォルト）: BTC_JPY
         */
        public Parameter() {
            product_code = Pair.BTC_JPY.toString();
        }
    }
}
