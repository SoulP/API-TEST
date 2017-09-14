package moe.soulp.api_test.bitFlyer.dto;

import org.json.JSONException;
import org.json.JSONObject;

import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;

/**
 * <b>新規注文の情報</b><br>
 * date: 2017/09/13 last_date: 2017/09/14
 * 
 * @author ソウルP
 * @version 1.0 2017/09/13 NewOrderDTO作成
 */
public class NewOrderDTO {
    private String       product_code;                         // プロダクトコード
    private Type         child_order_type;                     // 注文方法
    private Type         side;                                 // 注文の種類
    private Long         price;                                // 価格
    private double       size;                                 // 量
    private Integer      minute_to_expire;                     // 有効期限（分）
    private Type         time_in_force;                        // 執行数量条件
    private final String PRODUCT_CODE     = "product_code";
    private final String CHILD_ORDER_TYPE = "child_order_type";
    private final String SIDE             = "side";
    private final String PRICE            = "price";
    private final String SIZE             = "size";
    private final String MINUTE_TO_EXPIRE = "minute_to_expire";
    private final String TIME_IN_FORCE    = "time_in_force";

    /**
     * <b>新規注文</b> product_code（デフォルト）: BTC_JPY
     */
    public NewOrderDTO() {
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
     * <b>プロダクトコード 入力</b>
     * 
     * @param product_code
     *            プロダクトコード
     */
    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    /**
     * <b>注文方法 出力</b><br>
     * <ul>
     * child_order_type
     * <li><b>LIMIT</b>: 指値注文</li>
     * <li><b>MARKET</b>: 成行注文</li>
     * <ul>
     * 
     * @return child_order_type
     * @see Type 種類
     */
    public Type getChildOrderType() {
        return child_order_type;
    }

    /**
     * <b>注文方法 入力</b><br>
     * <ul>
     * child_order_type
     * <li><b>LIMIT</b>: 指値注文</li>
     * <li><b>MARKET</b>: 成行注文</li>
     * <ul>
     * 
     * @param child_order_type
     *            注文方法
     * @see Type 種類
     */
    public void setChildOrderType(Type child_order_type) {
        this.child_order_type = child_order_type;
    }

    /**
     * <b>注文方法 入力</b><br>
     * <ul>
     * child_order_type
     * <li><b>LIMIT</b>: 指値注文</li>
     * <li><b>MARKET</b>: 成行注文</li>
     * <ul>
     * 
     * @param child_order_type
     *            注文方法
     * @see Type 種類
     */
    public void setChildOrderType(String child_order_type) {
        this.child_order_type = Type.valueOf(child_order_type);
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
    public Long getPrice() {
        return price;
    }

    /**
     * <b>価格 入力</b>
     * 
     * @param price
     *            価格
     */
    public void setPrice(Long price) {
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
     * @see Type 種類
     */
    public void setTimeInForce(Type time_in_force) {
        this.time_in_force = time_in_force;
    }

    /**
     * <b>執行数量条件 入力</b>
     * 
     * @param time_in_force
     * @see Type 種類
     */
    public void setTimeInForce(String time_in_force) {
        this.time_in_force = Type.valueOf(time_in_force);
    }

    /**
     * <b>JSON 出力</b>
     * 
     * @return JSONObject
     */
    public JSONObject toJson() {
        JSONObject temp = new JSONObject();
        try {
            temp = temp.put(PRODUCT_CODE, product_code).put(CHILD_ORDER_TYPE, child_order_type).put(SIDE, side)
                    .put(SIZE, size);
            if (price != null) temp = temp.put(PRICE, price);
            if (minute_to_expire != null) temp = temp.put(MINUTE_TO_EXPIRE, minute_to_expire);
            if (time_in_force != null) temp = temp.put(TIME_IN_FORCE, time_in_force);
            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <b>JSON 出力</b>
     * 
     * @return 文字列のJSON
     */
    @Override
    public String toString() {
        return toJson().toString();
    }
}
