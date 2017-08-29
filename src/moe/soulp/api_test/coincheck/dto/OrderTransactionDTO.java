package moe.soulp.api_test.coincheck.dto;

import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;

/**
 * <b>取引履歴</b><br>
 * date: 2017/08/21 last_date: 2017/08/29
 * 
 * @author ソウルP
 * @version 1.0 2017/08/21 OrderTransactionDTO作成
 * @version 1.1 2017/08/26 継承することで不要な部分を排除
 * @see Pair 取引ペア
 * @see Type 種類
 * @see Currency 通貨
 */
public class OrderTransactionDTO extends Transaction {
    private long     order_id;     // 注文のID
    private double   funds_btc;    // btc残高の増減分
    private double   funds_jpy;    // jpy残高の増減分
    private Pair     pair;         // 取引ペア
    private double   rate;         // 約定価格
    private Currency fee_currency; // 手数料の通貨
    private double   fee;          // 手数料
    private String   liquidity;    // 流動性 "T" ( Taker ) or "M" ( Maker )
    private Type     side;         // 取引の種類 "sell" or "buy"

    /**
     * <b>注文のID 出力</b>
     * 
     * @return order_id
     */
    public long getOrderId() {
        return order_id;
    }

    /**
     * <b>注文のID 入力</b>
     * 
     * @param order_id
     *            注文のID
     */
    public void setOrderId(long order_id) {
        this.order_id = order_id;
    }

    /**
     * <b>btc残高の増減分 出力</b>
     * 
     * @return funds_btc;
     */
    public double getFundsBtc() {
        return funds_btc;
    }

    /**
     * <b>btc残高の増減分 入力</b>
     * 
     * @param funds_btc
     *            btc残高の増減分
     */
    public void setFundsBtc(double funds_btc) {
        this.funds_btc = funds_btc;
    }

    /**
     * <b>jpy残高の増減分 出力</b>
     * 
     * @return funds_jpy
     */
    public double getFundsJpy() {
        return funds_jpy;
    }

    /**
     * <b>jpy残高の増減分 入力</b>
     * 
     * @param funds_jpy
     *            jpy残高の増減分
     */
    public void setFundsJpy(double funds_jpy) {
        this.funds_jpy = funds_jpy;
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
        if (!(pair == null || pair.isEmpty())) setPair(Pair.valueOf(pair));
    }

    /**
     * <b>約定価格 出力</b>
     * 
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * <b>約定価格 入力</b>
     * 
     * @param rate
     *            約定価格
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * <b>手数料の通貨 出力</b>
     * 
     * @return fee_currency
     * @see Currency 通貨
     */
    public Currency getFeeCurrency() {
        return fee_currency;
    }

    /**
     * <b>手数料の通貨 入力</b>
     * 
     * @param fee_currency
     *            手数料の通貨
     * @see Currency 通貨
     */
    public void setFeeCurrency(Currency fee_currency) {
        this.fee_currency = fee_currency;
    }

    /**
     * <b>手数料の通貨 入力</b>
     * 
     * @param fee_currency
     *            手数料の通貨
     * @see Currency 通貨
     */
    public void setFeeCurrency(String fee_currency) {
        if (!(fee_currency == null || fee_currency.isEmpty())) setFeeCurrency(Currency.valueOf(fee_currency));
    }

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
     * <b>流動性 出力</b><br>
     * "T" ( Taker ) or "M" ( Maker )
     * 
     * @return liquidity
     */
    public String getLiquidity() {
        return liquidity;
    }

    /**
     * <b>流動性 入力</b><br>
     * "T" ( Taker ) or "M" ( Maker )
     * 
     * @param liquidity
     *            流動性
     */
    public void setLiquidity(String liquidity) {
        this.liquidity = liquidity;
    }

    /**
     * <b>取引の種類 出力</b><br>
     * "sell" or "buy"
     * 
     * @return side
     * @see Type 種類
     */
    public Type getSide() {
        return side;
    }

    /**
     * <b>取引の種類 入力</b><br>
     * "sell" or "buy"
     * 
     * @param side
     *            取引の種類
     * @see Type 種類
     */
    public void setSide(Type side) {
        this.side = side;
    }

    /**
     * <b>取引の種類 入力</b><br>
     * "sell" or "buy"
     * 
     * @param side
     *            取引の種類
     * @see Type 種類
     */
    public void setSide(String side) {
        this.side = Type.valueOf(side);
    }
}
