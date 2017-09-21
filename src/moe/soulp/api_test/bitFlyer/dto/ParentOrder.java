package moe.soulp.api_test.bitFlyer.dto;

import java.util.List;

import org.json.JSONObject;

import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;

/**
 * <b>親注文の情報</b><br>
 * date: 2017/09/21 last_date: 2017/09/21
 * 
 * @author ソウルP
 * @version 1.0 2017/09/21 ParentOrder作成
 * @see Type 種類
 */
public abstract class ParentOrder {
    protected Type                order_method;                         // 注文方法
    protected Integer             minute_to_expire;                     // 有効期限（分）
    protected List<Parameter>     parameters;                           // 注文内容の一覧
    protected Parameter           parameter;                            // 注文内容

    protected final static String ORDER_METHOD     = "order_method";
    protected final static String MINUTE_TO_EXPIRE = "minute_to_expire";
    protected final static String TIME_IN_FORCE    = "time_in_force";
    protected final static String PARAMETERS       = "parameters";
    protected final static String PRODUCT_CODE     = "product_code";
    protected final static String CONDITION_TYPE   = "condition_type";
    protected final static String SIDE             = "side";
    protected final static String SIZE             = "size";
    protected final static String PRICE            = "price";
    protected final static String TRIGGER_PRICE    = "trigger_price";
    protected final static String OFFSET           = "offset";

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

    public abstract JSONObject toJson();

    /**
     * <b>JSONの文字列 出力</b>
     */
    @Override
    public String toString() {
        return toJson().toString();
    }

    /**
     * <b>注文内容の一覧 出力</b>
     * 
     * @return parameters
     */
    public List<Parameter> getParameters() {
        return parameters;
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
        protected String product_code;   // プロダクトコード
        protected Type   condition_type; // 注文の種類
        protected Type   side;           // 注文の種類
        protected double size;           // 量
        protected Long   price;          // 価格
        protected Long   trigger_price;  // トリガー価格
        protected Long   offset;         // トレール幅

        /**
         * <b>注文内容</b><br>
         * product_code（デフォルト）: BTC_JPY
         */
        public Parameter() {
            product_code = Pair.BTC_JPY.toString();
        }

        /**
         * <b>プロダクトコード 出力</b>
         * 
         * @return product_code
         */
        public String getProductCode() {
            return product_code;
        }

        /**
         * <b>注文の種類 出力</b>
         * 
         * @return condition_type
         */
        public Type getConditionType() {
            return condition_type;
        }

        /**
         * <b>注文の種類 出力</b>
         * 
         * @return side
         */
        public Type getSide() {
            return side;
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
         * <b>価格 出力</b>
         * 
         * @return price
         */
        public Long getPrice() {
            return price;
        }

        /**
         * <b>トリガー価格 出力</b>
         * 
         * @return trigger_price
         */
        public Long getTriggerPrice() {
            return trigger_price;
        }

        /**
         * <b>トレール幅 出力</b>
         * 
         * @return offset
         */
        public Long getOffset() {
            return offset;
        }
    }
}
