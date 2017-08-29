package moe.soulp.api_test.coincheck;

import java.util.function.Function;

import org.json.JSONException;
import org.json.JSONObject;

import moe.soulp.api_test.api.APIcoincheck;
import moe.soulp.api_test.api.Pair;

/**
 * <b>販売レート</b><br>
 * date: 2017/08/06 last_date: 2017/08/29
 * 
 * @author ソウルP
 * @version 1.0 2017/08/06 CoincheckRate作成
 */
public class CoincheckRate {
    private final static APIcoincheck           coincheck = new APIcoincheck();

    private final static Function<Pair, Double> getRate   = (pair) -> {
                                                              double rate = -1.0d;
                                                              try {
                                                                  rate = new JSONObject(coincheck.getRate(pair))
                                                                          .getDouble("rate");
                                                              } catch (JSONException e) {
                                                                  e.printStackTrace();
                                                              }
                                                              return rate;
                                                          };

    /**
     * <b>販売レート</b>
     * 
     * @return btc_jpy
     */
    public static double getBtcJpy() {
        return getRate.apply(Pair.btc_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return eth_jpy
     */
    public static double getEthJpy() {
        return getRate.apply(Pair.eth_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return etc_jpy
     */
    public static double getEtcJpy() {
        return getRate.apply(Pair.etc_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return dao_jpy
     */
    public static double getDaoJpy() {
        return getRate.apply(Pair.dao_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return lsk_jpy
     */
    public static double getLskJpy() {
        return getRate.apply(Pair.lsk_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return fct_jpy
     */
    public static double getFctJpy() {
        return getRate.apply(Pair.fct_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return xmr_jpy
     */
    public static double getXmrJpy() {
        return getRate.apply(Pair.xmr_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return rep_jpy
     */
    public static double getRepJpy() {
        return getRate.apply(Pair.rep_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return xrp_jpy
     */
    public static double getXrpJpy() {
        return getRate.apply(Pair.xrp_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return zec_jpy
     */
    public static double getZecJpy() {
        return getRate.apply(Pair.zec_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return xem_jpy
     */
    public static double getXemJpy() {
        return getRate.apply(Pair.xem_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return ltc_jpy
     */
    public static double getLtcJpy() {
        return getRate.apply(Pair.ltc_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return dash_jpy
     */
    public static double getDashJpy() {
        return getRate.apply(Pair.dash_jpy);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return eth_btc
     */
    public static double getEthBtc() {
        return getRate.apply(Pair.eth_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return etc_btc
     */
    public static double getEtcBtc() {
        return getRate.apply(Pair.etc_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return lsk_btc
     */
    public static double getLskBtc() {
        return getRate.apply(Pair.lsk_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return fctr_btc
     */
    public static double getFctBtc() {
        return getRate.apply(Pair.fct_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return xmr_btc
     */
    public static double getXmrBtc() {
        return getRate.apply(Pair.xmr_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return rep_btc
     */
    public static double getRepBtc() {
        return getRate.apply(Pair.rep_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return xrp_btc
     */
    public static double getXrpBtc() {
        return getRate.apply(Pair.xrp_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return zec_btc
     */
    public static double getZecBtc() {
        return getRate.apply(Pair.zec_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return xem_btc
     */
    public static double getXemBtc() {
        return getRate.apply(Pair.xem_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return ltc_btc
     */
    public static double getLtcBtc() {
        return getRate.apply(Pair.ltc_btc);
    }

    /**
     * <b>販売レート</b>
     * 
     * @return dash_btc
     */
    public static double getDashBtc() {
        return getRate.apply(Pair.dash_btc);
    }
}
