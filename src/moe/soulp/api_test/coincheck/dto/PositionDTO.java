package moe.soulp.api_test.coincheck.dto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;

/**
 * <b>ポジション情報</b><br>
 * date: 2017/08/22 last_date: 2017/08/22
 * 
 * @author ソウルP
 * @version 1.0 2017/08/22 PositionDTO作成
 * @see Pair 取引ペア
 * @see Type 種類
 * @see PositionOrderDTO ポジション情報の注文情報
 */
public class PositionDTO {
    private long                   id;          // ID
    private Pair                   pair;        // 取引ペア
    private String                 status;      // ポジションの状態
    private ZonedDateTime          created_at;  // ポジションの作成日時
    private ZonedDateTime          closed_at;   // ポジションの決済完了日時
    private double                 open_rate;   // ポジションの平均取得価格
    private double                 closed_rate; // ポジションの平均決済価格
    private double                 amount;      // 現在のポジションの数量（BTC）
    private double                 all_amount;  // ポジションの数量（BTC）
    private Type                   side;        // ポジションの種類
    private double                 pl;          // 利益
    private PositionOrderDTO       new_order;   // 新規注文についての情報
    private List<PositionOrderDTO> close_orders;// 決済注文についての情報

    /**
     * 初期設定
     */
    public PositionDTO() {
        close_orders = new ArrayList<>();
    }

    /**
     * <b>ID 出力</b>
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * <b>ID 入力</b>
     * 
     * @param id
     *            ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * <b>取引ペア 出力</b>
     * 
     * @return pair
     * @see Pair 取引ペア
     */
    public Pair getPair() {
        return pair;
    }

    /**
     * <b>取引ペア 入力</b>
     * 
     * @param pair
     *            取引ペア
     * @see Pair 取引ペア
     */
    public void setPair(Pair pair) {
        this.pair = pair;
    }

    /**
     * <b>取引ペア 入力</b>
     * 
     * @param pair
     *            取引ペア
     * @see Pair 取引ペア
     */
    public void setPair(String pair) {
        this.pair = Pair.valueOf(pair);
    }

    /**
     * <b>ポジションの状態 出力</b>
     * 
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * <b>ポジションの状態 入力</b>
     * 
     * @param status
     *            ポジションの状態
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <b>ポジションの作成日時 出力</b>
     * 
     * @return created_at
     */
    public ZonedDateTime getCreatedAt() {
        return created_at;
    }

    /**
     * <b>ポジションの作成日時 入力</b>
     * 
     * @param created_at
     *            ポジションの作成日時
     */
    public void setCreatedAt(ZonedDateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * <b>ポジションの作成日時 入力</b>
     * 
     * @param created_at
     *            ポジションの作成日時
     */
    public void setCreatedAt(String created_at) {
        this.created_at = ZonedDateTime.parse(created_at, Coincheckable.FORMAT)
                .withZoneSameInstant(ZoneId.systemDefault());
    }

    /**
     * <b>ポジションの決済完了日時 出力</b>
     * 
     * @return closed_at
     */
    public ZonedDateTime getClosedAt() {
        return closed_at;
    }

    /**
     * <b>ポジションの決済完了日時 入力</b>
     * 
     * @param closed_at
     *            ポジションの決済完了日時
     */
    public void setClosedAt(ZonedDateTime closed_at) {
        this.closed_at = closed_at;
    }

    /**
     * <b>ポジションの決済完了日時 入力</b>
     * 
     * @param closed_at
     *            ポジションの決済完了日時
     */
    public void setClosedAt(String closed_at) {
        this.closed_at = ZonedDateTime.parse(closed_at, Coincheckable.FORMAT)
                .withZoneSameInstant(ZoneId.systemDefault());
    }

    /**
     * <b>ポジションの平均取得価格 出力</b>
     * 
     * @return open_rate
     */
    public double getOpenRate() {
        return open_rate;
    }

    /**
     * <b>ポジションの平均取得価格 入力</b>
     * 
     * @param open_rate
     *            ポジションの平均取得価格
     */
    public void setOpenRate(double open_rate) {
        this.open_rate = open_rate;
    }

    /**
     * <b>ポジションの平均決済価格 出力</b>
     * 
     * @return closed_rate
     */
    public double getClosedRate() {
        return closed_rate;
    }

    /**
     * <b>ポジションの平均決済価格 入力</b>
     * 
     * @param closed_rate
     *            ポジションの平均決済価格
     */
    public void setClosedRate(double closed_rate) {
        this.closed_rate = closed_rate;
    }

    /**
     * <b>現在のポジションの数量（BTC） 出力</b>
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <b>現在のポジションの数量（BTC） 入力</b>
     * 
     * @param amount
     *            現在のポジションの数量（BTC）
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <b>ポジションの数量（BTC） 出力</b>
     * 
     * @return all_amount
     */
    public double getAllAmount() {
        return all_amount;
    }

    /**
     * <b>ポジションの数量（BTC） 入力</b>
     * 
     * @param all_amount
     *            ポジションの数量（BTC）
     */
    public void setAllAmount(double all_amount) {
        this.all_amount = all_amount;
    }

    /**
     * <b>ポジションの種類 出力</b>
     * 
     * @return side
     */
    public Type getSide() {
        return side;
    }

    /**
     * <b>ポジションの種類 入力</b>
     * 
     * @param side
     *            ポジションの種類
     */
    public void setSide(Type side) {
        this.side = side;
    }

    /**
     * <b>ポジションの種類 入力</b>
     * 
     * @param side
     *            ポジションの種類
     */
    public void setSide(String side) {
        this.side = Type.valueOf(side);
    }

    /**
     * <b>利益 出力</b>
     * 
     * @return pl
     */
    public double getPl() {
        return pl;
    }

    /**
     * <b>利益 入力</b>
     * 
     * @param pl
     *            利益
     */
    public void setPl(double pl) {
        this.pl = pl;
    }

    /**
     * <b>新規注文についての情報 出力</b>
     * 
     * @return new_order
     * @see PositionOrderDTO ポジション情報の注文情報
     */
    public PositionOrderDTO getNewOrder() {
        return new_order;
    }

    /**
     * <b>新規注文についての情報 入力</b>
     * 
     * @param new_order
     *            新規注文についての情報
     * @see PositionOrderDTO ポジション情報の注文情報
     */
    public void setNewOrder(PositionOrderDTO new_order) {
        this.new_order = new_order;
    }

    /**
     * <b>決済注文についての情報 出力</b>
     * 
     * @return close_orders
     * @see PositionOrderDTO ポジジョン情報の注文情報
     */
    public List<PositionOrderDTO> getCloseOrders() {
        return close_orders;
    }

    /**
     * <b>決済注文についての情報 入力</b>
     * 
     * @param close_orders
     *            決済注文についての情報
     * @see PositionOrderDTO ポジジョン情報の注文情報
     */
    public void setCloseOrders(List<PositionOrderDTO> close_orders) {
        this.close_orders = close_orders;
    }

    /**
     * <b>決済注文についての情報 追加</b>
     * 
     * @param close_order
     *            決済注文についての情報
     * @see PositionOrderDTO ポジジョン情報の注文情報
     */
    public void addCloseOrders(PositionOrderDTO close_order) {
        close_orders.add(close_order);
    }

    /**
     * <b>決済注文についての情報 削除</b>
     * 
     * @param index
     *            削除される要素のインデックス
     */
    public void removeCloseOrders(int index) {
        close_orders.remove(index);
    }

    /**
     * <b>決済注文についての情報 全削除</b>
     */
    public void clearCloseOrders() {
        close_orders.clear();
    }
}
