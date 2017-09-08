package moe.soulp.api_test.api;

/**
 * <b>種類</b></br>
 * date: 2017/08/04 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/08/04 Type作成
 * @version 1.1 2017/08/28 銀行口座の種類追加 （ futsu : 普通口座, toza : 当座預金口座 ）
 * @version 1.2 2017/09/07 注文の種類追加 ( SELL : 売り, BUY : 買い )
 * @version 1.3 2017/09/08 注文の種類追加 ( LIMIT : 指値注文, MARKET : 成行注文 )
 * @version 1.4 2017/09/08 執行数量条件の種類追加 ( GTC, IOC, FOK )
 * @version 1.5 2017/09/08 注文の種類追加 ( STOP : ストップ注文, STOP_LIMIT : ストップ・リミット注文,
 *          TRAIL : トレーリング・ストップ注文 )
 */
public enum Type {
    buy, sell, market_buy, market_sell, leverage_buy, leverage_sell, close_long, close_short, futsu, toza, SELL, BUY, LIMIT, MARKET, GTC, IOC, FOK, STOP, STOP_LIMIT, TRAIL
}
