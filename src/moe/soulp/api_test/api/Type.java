package moe.soulp.api_test.api;

/**
 * <b>注文方法</b></br>
 * date: 2017/08/04 last_date: 2017/08/29
 * 
 * @author ソウルP
 * @version 1.0 2017/08/04 Type作成
 * @version 1.1 2017/08/28 銀行口座の種類追加 （futsu : 普通口座, toza : 当座預金口座）
 */
public enum Type {
    buy, sell, market_buy, market_sell, leverage_buy, leverage_sell, close_long, close_short, futsu, toza
}
