package moe.soulp.api_test.ut;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import moe.soulp.api_test.api.APIcoincheck;
import moe.soulp.api_test.api.Coincheckable;
import moe.soulp.api_test.api.OrderType;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.coincheck.CoincheckRate;
import moe.soulp.api_test.coincheck.dto.OrderDTO;
import moe.soulp.api_test.coincheck.dto.OrderTransactionDTO;

/**
 * <b>UTテストケース</b><br>
 * date: 2017/08/03 last_date: 2017/08/22
 * 
 * @author ソウルP
 */
public class APIcoincheckUT extends APIkey {
    final static APIcoincheck coincheck = new APIcoincheck();

    /**
     * <b>ティッカー</b><br>
     * 成功テスト
     */
    @Test
    public void getTicker() {
        JSONObject ticker = null;
        double last, bid, ask, high, low, volume;
        last = bid = ask = high = low = volume = -1.0d;
        Timestamp timestamp = null;
        try {
            ticker = new JSONObject(coincheck.getTicker());
            last = ticker.getDouble("last");
            bid = ticker.getDouble("bid");
            ask = ticker.getDouble("ask");
            high = ticker.getDouble("high");
            low = ticker.getDouble("low");
            volume = ticker.getDouble("volume");
            timestamp = new Timestamp(ticker.getLong("timestamp") * 1000l);
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(ticker);
        assertThat(last, is(not(-1.0d)));
        assertThat(bid, is(not(-1.0d)));
        assertThat(ask, is(not(-1.0d)));
        assertThat(high, is(not(-1.0d)));
        assertThat(low, is(not(-1.0d)));
        assertThat(volume, is(not(-1.0d)));

        System.out.println(timestamp);
        System.out.println("last: " + last);
        System.out.println("bid: " + bid);
        System.out.println("ask: " + ask);
        System.out.println("high: " + high);
        System.out.println("low: " + low);
        System.out.println("volume: " + volume);
        System.out.println();
    }

    /**
     * <b>全取引履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getTrades() {
        JSONArray trades = null;
        JSONObject temp = null;
        int id = -1;
        String amount = null;
        int rate = -1;
        String order_type = null;
        ZonedDateTime created_at = null;
        try {
            trades = new JSONArray(coincheck.getTrades());
            temp = trades.getJSONObject(0);
            id = temp.getInt("id");
            amount = temp.getString("amount");
            rate = temp.getInt("rate");
            order_type = temp.getString("order_type");
            created_at = ZonedDateTime.parse(temp.getString("created_at"), Coincheckable.FORMAT)
                    .withZoneSameInstant(ZoneId.systemDefault());
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(trades);
        assertThat(id, is(not(-1)));
        assertNotNull(amount);
        assertThat(rate, is(not(-1)));
        assertNotNull(order_type);
        assertNotNull(created_at);

        System.out.println("created_at: " + created_at);
        System.out.println("id: " + id);
        System.out.println("amount: " + amount);
        System.out.println("rate: " + rate);
        System.out.println("order_type: " + order_type);
        System.out.println();
    }

    /**
     * <b>全取引履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getTrades_offset() {
        JSONArray trades = null;
        JSONObject temp = null;
        int id = -1;
        String amount = null;
        int rate = -1;
        String order_type = null;
        ZonedDateTime created_at = null;
        try {
            trades = new JSONArray(coincheck.getTrades(99));
            temp = trades.getJSONObject(0);
            id = temp.getInt("id");
            amount = temp.getString("amount");
            rate = temp.getInt("rate");
            order_type = temp.getString("order_type");
            created_at = ZonedDateTime.parse(temp.getString("created_at"), Coincheckable.FORMAT)
                    .withZoneSameInstant(ZoneId.systemDefault());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertNotNull(trades);
        assertThat(id, is(not(-1)));
        assertNotNull(amount);
        assertThat(rate, is(not(-1)));
        assertNotNull(order_type);
        assertNotNull(created_at);

        System.out.println("created_at: " + created_at);
        System.out.println("id: " + id);
        System.out.println("amount: " + amount);
        System.out.println("rate: " + rate);
        System.out.println("order_type: " + order_type);
        System.out.println();
    }

    /**
     * <b>板情報</b><br>
     * 成功テスト
     */
    @Test
    public void getOrderBooks() {
        JSONArray asks = null;
        JSONArray bids = null;
        JSONObject temp = null;
        JSONArray maxAsk = null;
        JSONArray minAsk = null;
        JSONArray maxBid = null;
        JSONArray minBid = null;
        double maxAskPrice = -1.0d;
        double maxAskAmount = -1.0d;
        double minAskPrice = -1.0d;
        double minAskAmount = -1.0d;
        double maxBidPrice = -1.0d;
        double maxBidAmount = -1.0d;
        double minBidPrice = -1.0d;
        double minBidAmount = -1.0d;
        try {
            temp = new JSONObject(coincheck.getOrderBooks());
            asks = temp.getJSONArray("asks");
            bids = temp.getJSONArray("bids");
            maxAsk = asks.getJSONArray((asks.length() - 1));
            minAsk = asks.getJSONArray(0);
            maxBid = bids.getJSONArray(bids.length() - 1);
            minBid = bids.getJSONArray(0);
            maxAskPrice = maxAsk.getDouble(0);
            maxAskAmount = maxAsk.getDouble(1);
            minAskPrice = minAsk.getDouble(0);
            minAskAmount = minAsk.getDouble(1);
            maxBidPrice = maxBid.getDouble(0);
            maxBidAmount = maxBid.getDouble(1);
            minBidPrice = minBid.getDouble(0);
            minBidAmount = minBid.getDouble(1);
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(asks);
        assertNotNull(bids);
        assertNotNull(maxAsk);
        assertNotNull(minAsk);
        assertNotNull(maxBid);
        assertNotNull(minBid);
        assertThat(maxAskPrice, is(not(-1.0d)));
        assertThat(maxAskAmount, is(not(-1.0d)));
        assertThat(minAskPrice, is(not(-1.0d)));
        assertThat(minAskAmount, is(not(-1.0d)));
        assertThat(maxBidPrice, is(not(-1.0d)));
        assertThat(maxBidAmount, is(not(-1.0d)));
        assertThat(minBidPrice, is(not(-1.0d)));
        assertThat(minBidAmount, is(not(-1.0d)));

        System.out.println("【買】");
        System.out.println("最高: " + minBidPrice + "円, " + minBidAmount + "btc");
        System.out.println("最安: " + maxBidPrice + "円, " + maxBidAmount + "btc");
        System.out.println();
        System.out.println("【売】");
        System.out.println("最安: " + minAskPrice + "円, " + minAskAmount + "btc");
        System.out.println("最高: " + maxAskPrice + "円, " + maxAskAmount + "btc");
        System.out.println();
    }

    /**
     * <b>販売レート取得</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersRate_amount() {
        JSONObject temp = null;
        boolean success = false;
        double rate, amount, price;
        rate = amount = price = -1.0d;
        try {
            temp = new JSONObject(coincheck.getOrdersRate_amount(OrderType.buy, Pair.btc_jpy, 0.1d));
            success = temp.getBoolean("success");
            rate = temp.getDouble("rate");
            amount = temp.getDouble("amount");
            price = temp.getDouble("price");
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(temp);
        assertTrue(success);
        assertThat(rate, is(not(-1.0d)));
        assertThat(amount, is(not(-1.0d)));
        assertThat(price, is(not(-1.0d)));

        System.out.println("rate: " + rate);
        System.out.println("amount: " + amount);
        System.out.println("price: " + price);
        System.out.println();
    }

    /**
     * <b>販売レート取得</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersRate_price() {
        JSONObject temp = null;
        boolean success = false;
        double rate, amount, price;
        rate = amount = price = -1.0d;
        try {
            temp = new JSONObject(coincheck.getOrdersRate_price(OrderType.buy, Pair.btc_jpy, 30971));
            success = temp.getBoolean("success");
            rate = temp.getDouble("rate");
            amount = temp.getDouble("amount");
            price = temp.getDouble("price");
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertNotNull(temp);
        assertTrue(success);
        assertThat(rate, is(not(-1.0d)));
        assertThat(amount, is(not(-1.0d)));
        assertThat(price, is(not(-1.0d)));

        System.out.println("rate: " + rate);
        System.out.println("amount: " + amount);
        System.out.println("price: " + price);
        System.out.println();
    }

    /**
     * <b>販売レート取得</b><br>
     * 成功テスト
     */
    @Test
    public void getRate() {
        System.out.println("販売レート");
        System.out.println(Pair.btc_jpy + " rate: " + CoincheckRate.getBtcJpy());
        System.out.println(Pair.eth_jpy + " rate: " + CoincheckRate.getEthJpy());
        System.out.println(Pair.etc_jpy + " rate: " + CoincheckRate.getEtcJpy());
        System.out.println(Pair.dao_jpy + " rate: " + CoincheckRate.getDaoJpy());
        System.out.println(Pair.lsk_jpy + " rate: " + CoincheckRate.getLskJpy());
        System.out.println(Pair.fct_jpy + " rate: " + CoincheckRate.getFctJpy());
        System.out.println(Pair.xmr_jpy + " rate: " + CoincheckRate.getXmrJpy());
        System.out.println(Pair.rep_jpy + " rate: " + CoincheckRate.getRepJpy());
        System.out.println(Pair.xrp_jpy + " rate: " + CoincheckRate.getXrpJpy());
        System.out.println(Pair.zec_jpy + " rate: " + CoincheckRate.getZecJpy());
        System.out.println(Pair.ltc_jpy + " rate: " + CoincheckRate.getLtcJpy());
        System.out.println(Pair.dash_jpy + " rate: " + CoincheckRate.getDashJpy());
        System.out.println(Pair.eth_btc + " rate: " + CoincheckRate.getEthBtc());
        System.out.println(Pair.etc_btc + " rate: " + CoincheckRate.getEtcBtc());
        System.out.println(Pair.lsk_btc + " rate: " + CoincheckRate.getLskBtc());
        System.out.println(Pair.fct_btc + " rate: " + CoincheckRate.getFctBtc());
        System.out.println(Pair.xmr_btc + " rate: " + CoincheckRate.getXmrBtc());
        System.out.println(Pair.rep_btc + " rate: " + CoincheckRate.getRepBtc());
        System.out.println(Pair.xrp_btc + " rate: " + CoincheckRate.getXrpBtc());
        System.out.println(Pair.zec_btc + " rate: " + CoincheckRate.getZecBtc());
        System.out.println(Pair.xem_btc + " rate: " + CoincheckRate.getXemBtc());
        System.out.println(Pair.ltc_btc + " rate: " + CoincheckRate.getLtcBtc());
        System.out.println(Pair.dash_btc + " rate: " + CoincheckRate.getDashBtc());
        System.out.println();
    }

    /**
     * <b>指値注文 レバレッジ取引新規 買い</b><br>
     * 成功テスト
     */
    @Test
    public void postOrderLeverageBuy() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        double amount = 0.005d;
        double rate = 300000d;
        assertTrue(amount >= 0.005d);
        try {
            temp = new JSONObject(coincheck.postOrderLeverageBuy(Pair.btc_jpy, amount, rate));
            System.out.println("id: " + temp.getInt("id"));
            System.out.println("rate: " + temp.getDouble("rate"));
            System.out.println("amount: " + temp.getDouble("amount"));
            System.out.println("order_type: " + temp.getString("order_type"));
            double stop = (temp.isNull("stop_loss_rate")) ? -1.0d : temp.getDouble("stop_loss_rate");
            System.out.println("stop_loss_rate: " + stop);
            System.out.println("pair: " + temp.getString("pair"));
            System.out.println("created_at: " + ZonedDateTime.parse(temp.getString("created_at"), Coincheckable.FORMAT)
                    .withZoneSameInstant(ZoneId.systemDefault()));
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>指値注文 レバレッジ取引新規 売り</b><br>
     * 成功テスト
     */
    @Test
    public void postOrderLeverageSell() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        double amount = 0.005d;
        double rate = 450000d;
        assertTrue(amount >= 0.005d);
        try {
            temp = new JSONObject(coincheck.postOrderLeverageSell(Pair.btc_jpy, amount, rate));
            System.out.println("id: " + temp.getInt("id"));
            System.out.println("rate: " + temp.getDouble("rate"));
            System.out.println("amount: " + temp.getDouble("amount"));
            System.out.println("order_type: " + temp.getString("order_type"));
            double stop = (temp.isNull("stop_loss_rate")) ? -1.0d : temp.getDouble("stop_loss_rate");
            System.out.println("stop_loss_rate: " + stop);
            System.out.println("pair: " + temp.getString("pair"));
            System.out.println("created_at: " + ZonedDateTime.parse(temp.getString("created_at"), Coincheckable.FORMAT)
                    .withZoneSameInstant(ZoneId.systemDefault()));
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>指値注文 レバレッジ取引決済 売り</b><br>
     * 成功テスト
     */
    @Test
    public void postOrderCloseLong() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        double amount = 0.05d;
        long position_id = 193728148l;
        double rate = 450000d;
        try {
            temp = new JSONObject(coincheck.postOrderCloseLong(Pair.btc_jpy, amount, position_id, rate));
            System.out.println(temp);
            System.out.println("id: " + temp.getInt("id"));
            System.out.println("rate: " + temp.getDouble("rate"));
            System.out.println("amount: " + temp.getDouble("amount"));
            System.out.println("order_type: " + temp.getString("order_type"));
            double stop = (temp.isNull("stop_loss_rate")) ? -1.0d : temp.getDouble("stop_loss_rate");
            System.out.println("stop_loss_rate: " + stop);
            System.out.println("pair: " + temp.getString("pair"));
            System.out.println("created_at: " + ZonedDateTime.parse(temp.getString("created_at"), Coincheckable.FORMAT)
                    .withZoneSameInstant(ZoneId.systemDefault()));
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>成行注文 レバレッジ取引決済 買い</b><br>
     * 成功テスト
     */
    public void postOrderCloseShort() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        double amount = 0.05d;
        long position_id = 193728148l;
        double rate = 500000d;
        try {
            temp = new JSONObject(coincheck.postOrderCloseShort(Pair.btc_jpy, amount, position_id, rate));
            System.out.println(temp);
            System.out.println("id: " + temp.getInt("id"));
            System.out.println("rate: " + temp.getDouble("rate"));
            System.out.println("amount: " + temp.getDouble("amount"));
            System.out.println("order_type: " + temp.getString("order_type"));
            double stop = (temp.isNull("stop_loss_rate")) ? -1.0d : temp.getDouble("stop_loss_rate");
            System.out.println("stop_loss_rate: " + stop);
            System.out.println("pair: " + temp.getString("pair"));
            System.out.println("created_at: " + ZonedDateTime.parse(temp.getString("created_at"), Coincheckable.FORMAT)
                    .withZoneSameInstant(ZoneId.systemDefault()));
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>未決済の注文一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersOpens() {
        JSONObject temp = null;
        boolean success = false;
        JSONArray orders = null;
        List<OrderDTO> list = new ArrayList<>();
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        try {
            temp = new JSONObject(coincheck.getOrdersOpens());
            success = temp.getBoolean("success");
            orders = temp.getJSONArray("orders");
            for (int i = 0; i < orders.length(); i++) {
                JSONObject obj = orders.getJSONObject(i);
                OrderDTO tempOrder = new OrderDTO();
                tempOrder.setId(obj.getLong("id"));
                tempOrder.setOrderType(obj.getString("order_type"));
                tempOrder.setRate(obj.getDouble("rate"));
                tempOrder.setPair(obj.getString("pair"));
                tempOrder.setPendingAmount(
                        obj.isNull("pending_amount") ? -1.0d : Double.parseDouble(obj.getString("pending_amount")));
                tempOrder.setPendingMarketBuyAmount(obj.isNull("pending_market_buy_amount") ? -1.0d
                        : Double.parseDouble(obj.getString("pending_market_buy_amount")));
                tempOrder.setStopLessRate(obj.isNull("stop_less_rate") ? -1.0d : obj.getDouble("stop_less_rate"));
                tempOrder.setCreatedAt(obj.getString("created_at"));
                list.add(tempOrder);
            }
            System.out.println("未決済の注文一覧");
            for (OrderDTO obj : list) {
                System.out.println("id: " + obj.getId());
                System.out.println("order_type: " + obj.getOrderType());
                System.out.println("rate: " + obj.getRate());
                System.out.println("pair: " + obj.getPair());
                System.out.println("pending_amount: " + obj.getPendingAmount());
                System.out.println("pending_market_buy_amount: " + obj.getPendingMarketBuyAmount());
                System.out.println("stop_less_rate: " + obj.getStopLessRate());
                System.out.println("created_at: " + obj.getCreatedAt());
                System.out.println();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertTrue(success);
    }

    /**
     * <b>注文のキャンセル</b><br>
     * 成功テスト
     */
    @Test
    public void deleteOrdersId() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        long id = 210480568l;
        boolean success = false;
        try {
            temp = new JSONObject(coincheck.deleteOrdersId(id));
            System.out.println("注文のキャンセル");
            System.out.println("success: " + (success = temp.getBoolean("success")));
            System.out.println("id: " + temp.getLong("id"));
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertTrue(success);
    }

    /**
     * <b>取引履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersTransactions() {
        JSONObject temp = null;
        JSONArray tempArray = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        List<OrderTransactionDTO> list = new ArrayList<>();
        try {
            temp = new JSONObject(coincheck.getOrdersTransactions());
            System.out.println("取引履歴");
            System.out.println("success: " + (success = temp.getBoolean("success")));
            tempArray = temp.getJSONArray("transactions");
            for (int i = 0; i < tempArray.length(); i++) {
                JSONObject jObj = tempArray.getJSONObject(i);
                OrderTransactionDTO dto = new OrderTransactionDTO();
                dto.setId(jObj.getLong("id"));
                dto.setOrderId(jObj.getLong("order_id"));
                dto.setCreatedAt(jObj.getString("created_at"));
                dto.setFundsBtc(Double.parseDouble(jObj.getJSONObject("funds").getString("btc")));
                dto.setFundsJpy(Double.parseDouble(jObj.getJSONObject("funds").getString("jpy")));
                dto.setPair(jObj.getString("pair"));
                dto.setRate(Double.parseDouble(jObj.getString("rate")));
                dto.setFeeCurrency(jObj.isNull("fee_currency") ? null : jObj.getString("fee_currency"));
                dto.setFee(Double.parseDouble(jObj.getString("fee")));
                dto.setLiquidity(jObj.getString("liquidity"));
                dto.setSide(jObj.getString("side"));
                list.add(dto);
            }

            for (OrderTransactionDTO transaction : list) {
                System.out.println("id: " + transaction.getId());
                System.out.println("order_id: " + transaction.getOrderId());
                System.out.println("created_at: " + transaction.getCreatedAt());
                System.out.println("funds_btc: " + transaction.getFundsBtc());
                System.out.println("funds_jpy: " + transaction.getFundsJpy());
                System.out.println("pair: " + transaction.getPair());
                System.out.println("rate: " + transaction.getRate());
                System.out.println("fee_currency: " + transaction.getFeeCurrency());
                System.out.println("fee: " + transaction.getFee());
                System.out.println("liquidity: " + transaction.getLiquidity());
                System.out.println("side: " + transaction.getSide());
                System.out.println();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertNotNull(tempArray);
        assertTrue(success);
    }

    /**
     * <b>取引履歴（ページネーション）</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersTransactionsPagination() {
        JSONObject temp = null;
        JSONArray tempArray = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        List<OrderTransactionDTO> list = new ArrayList<>();
        int limit = 5;
        try {
            temp = new JSONObject(coincheck.getOrdersTransactionsPagination(limit));
            System.out.println("取引履歴");
            System.out.println("success: " + (success = temp.getBoolean("success")));
            JSONObject pagination = temp.isNull("pagination") ? null : temp.getJSONObject("pagination");
            if (pagination != null) {
                System.out.print("pagination_limit: ");
                System.out.println(pagination.isNull("limit") ? "null" : pagination.getLong("limit"));
                System.out.print("pagination_order: ");
                System.out.println(pagination.isNull("order") ? "null" : pagination.getString("order"));
                System.out.print("pagination_starting_after: ");
                System.out.println(pagination.isNull("starting_after") ? "null" : pagination.getLong("starting_after"));
                System.out.print("pagination_ending_before: ");
                System.out.println(pagination.isNull("ending_before") ? "null" : pagination.getLong("ending_before"));
            } else System.out.println("pagination: null");
            System.out.println();

            tempArray = temp.getJSONArray("data");
            for (int i = 0; i < tempArray.length(); i++) {
                JSONObject jObj = tempArray.getJSONObject(i);
                OrderTransactionDTO dto = new OrderTransactionDTO();
                dto.setId(jObj.getLong("id"));
                dto.setOrderId(jObj.getLong("order_id"));
                dto.setCreatedAt(jObj.getString("created_at"));
                dto.setFundsBtc(Double.parseDouble(jObj.getJSONObject("funds").getString("btc")));
                dto.setFundsJpy(Double.parseDouble(jObj.getJSONObject("funds").getString("jpy")));
                dto.setPair(jObj.getString("pair"));
                dto.setRate(Double.parseDouble(jObj.getString("rate")));
                dto.setFeeCurrency(jObj.isNull("fee_currency") ? null : jObj.getString("fee_currency"));
                dto.setFee(Double.parseDouble(jObj.getString("fee")));
                dto.setLiquidity(jObj.getString("liquidity"));
                dto.setSide(jObj.getString("side"));
                list.add(dto);
            }

            for (OrderTransactionDTO transaction : list) {
                System.out.println("id: " + transaction.getId());
                System.out.println("order_id: " + transaction.getOrderId());
                System.out.println("created_at: " + transaction.getCreatedAt());
                System.out.println("funds_btc: " + transaction.getFundsBtc());
                System.out.println("funds_jpy: " + transaction.getFundsJpy());
                System.out.println("pair: " + transaction.getPair());
                System.out.println("rate: " + transaction.getRate());
                System.out.println("fee_currency: " + transaction.getFeeCurrency());
                System.out.println("fee: " + transaction.getFee());
                System.out.println("liquidity: " + transaction.getLiquidity());
                System.out.println("side: " + transaction.getSide());
                System.out.println();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertNotNull(tempArray);
        assertTrue(success);
    }

    /**
     * <b>アカウント情報</b><br>
     * 成功テスト
     */
    @Test
    public void getAccounts() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        int id = -1;
        String email = null;
        String identity_status = null;
        String bitcoin_address = null;
        double lending_leverage = -1.0d;
        double taker_fee = -1.0d;
        double maker_fee = -1.0d;
        try {
            temp = new JSONObject(coincheck.getAccounts());
            success = temp.getBoolean("success");
            id = temp.getInt("id");
            email = temp.getString("email");
            identity_status = temp.getString("identity_status");
            bitcoin_address = temp.getString("bitcoin_address");
            lending_leverage = temp.getDouble("lending_leverage");
            taker_fee = temp.getDouble("taker_fee");
            maker_fee = temp.getDouble("maker_fee");
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        System.out.println("id: " + id);
        System.out.println("email: " + email);
        System.out.println("identity_status: " + identity_status);
        System.out.println("bitcoin_address: " + bitcoin_address);
        System.out.println("lending_leverage" + lending_leverage);
        System.out.println("taker_fee: " + taker_fee);
        System.out.println("maker_fee: " + maker_fee);
        System.out.println();

        assertNotNull(temp);
        assertFalse(coincheck.apiKeyIsEmpty());
        assertFalse(coincheck.apiSecretIsEmpty());
        assertTrue(success);
        assertThat(id, is(not(-1)));
        assertNotNull(email);
        assertNotNull(identity_status);
        assertNotNull(bitcoin_address);
        assertThat(lending_leverage, is(not(-1.0d)));
        assertThat(taker_fee, is(not(-1.0d)));
        assertThat(maker_fee, is(not(-1.0d)));
    }
}
