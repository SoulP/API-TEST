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
import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;
import moe.soulp.api_test.coincheck.CoincheckRate;
import moe.soulp.api_test.coincheck.dto.OrderDTO;
import moe.soulp.api_test.coincheck.dto.OrderTransactionDTO;
import moe.soulp.api_test.coincheck.dto.PositionDTO;
import moe.soulp.api_test.coincheck.dto.PositionOrderDTO;
import moe.soulp.api_test.coincheck.dto.SendMoneyTransactionDTO;

/**
 * <b>UTテストケース</b><br>
 * date: 2017-08-03 last_date: 2017-08-26
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
            temp = new JSONObject(coincheck.getOrdersRate_amount(Type.buy, Pair.btc_jpy, 0.1d));
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
            temp = new JSONObject(coincheck.getOrdersRate_price(Type.buy, Pair.btc_jpy, 30971));
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
            System.out.println("件数: " + list.size());
            System.out.println();
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

            System.out.println("件数: " + list.size());
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertNotNull(tempArray);
        assertTrue(success);
    }

    /**
     * <b>ポジション一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getPositions() {
        JSONObject temp = null;
        JSONArray tempArray = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        try {
            temp = new JSONObject(coincheck.getPositions());
            JSONObject pagination = temp.isNull("pagination") ? null : temp.getJSONObject("pagination");
            System.out.println("ポジション一覧");
            System.out.println("success: " + (success = temp.getBoolean("success")));
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
            List<PositionDTO> positions = new ArrayList<>();
            for (int i = 0; i < tempArray.length(); i++) {
                JSONObject jObj = tempArray.getJSONObject(i);
                JSONObject new_orderJO = jObj.getJSONObject("new_order");
                JSONArray close_ordersJA = jObj.getJSONArray("close_orders");
                PositionDTO position = new PositionDTO();
                PositionOrderDTO new_order = new PositionOrderDTO();

                position.setId(jObj.getLong("id"));
                position.setPair(jObj.getString("pair"));
                position.setStatus(jObj.getString("status"));
                position.setCreatedAt(jObj.getString("created_at"));
                position.setClosedAt(jObj.isNull("closed_at") ? null : jObj.getString("closed_at"));
                position.setAmount(Double.parseDouble(jObj.getString("amount")));
                position.setAllAmount(Double.parseDouble(jObj.getString("all_amount")));
                position.setSide(jObj.getString("side"));
                position.setPl(Double.parseDouble(jObj.getString("pl")));

                new_order.setId(new_orderJO.getLong("id"));
                new_order.setSide(new_orderJO.getString("side"));
                new_order.setRate(
                        new_orderJO.isNull("rate") ? -1.0d : Double.parseDouble(new_orderJO.getString("rate")));
                new_order.setAmount(
                        new_orderJO.isNull("amount") ? -1.0d : Double.parseDouble(new_orderJO.getString("amount")));
                new_order.setPendingAmount(new_orderJO.isNull("pending_amount") ? -1.0d
                        : Double.parseDouble(new_orderJO.getString("pending_amount")));
                new_order.setStatus(new_orderJO.getString("status"));
                new_order.setCreatedAt(new_orderJO.getString("created_at"));

                position.setNewOrder(new_order);

                for (int n = 0; n < close_ordersJA.length(); n++) {
                    JSONObject close_orderJO = close_ordersJA.getJSONObject(n);
                    PositionOrderDTO close_order = new PositionOrderDTO();
                    close_order.setId(close_orderJO.getLong("id"));
                    close_order.setSide(close_orderJO.getString("side"));
                    close_order.setRate(
                            close_orderJO.isNull("rate") ? -1.0d : Double.parseDouble(close_orderJO.getString("rate")));
                    close_order.setAmount(close_orderJO.isNull("amount") ? -1.0d
                            : Double.parseDouble(close_orderJO.getString("amount")));
                    close_order.setPendingAmount(close_orderJO.isNull("pending_amount") ? -1.0d
                            : Double.parseDouble(close_orderJO.getString("pending_amount")));
                    close_order.setStatus(close_orderJO.getString("status"));
                    close_order.setCreatedAt(close_orderJO.getString("created_at"));

                    position.addCloseOrders(close_order);
                }

                positions.add(position);
            }

            for (PositionDTO pos : positions) {
                System.out.println("id: " + pos.getId());
                System.out.println("pair: " + pos.getPair());
                System.out.println("status: " + pos.getStatus());
                System.out.println("created_at: " + pos.getCreatedAt());
                System.out.println("closed_at: " + pos.getClosedAt());
                System.out.println("open_rate: " + pos.getOpenRate());
                System.out.println("closed_rate: " + pos.getClosedRate());
                System.out.println("amount: " + pos.getAmount());
                System.out.println("all_amount: " + pos.getAllAmount());
                System.out.println("side: " + pos.getSide());
                System.out.println("pl: " + pos.getPl());
                System.out.println("------------------------------");
                System.out.println("new_order:");
                System.out.println("n_id: " + pos.getNewOrder().getId());
                System.out.println("n_side: " + pos.getNewOrder().getSide());
                System.out.println("n_rate: " + pos.getNewOrder().getRate());
                System.out.println("n_amount: " + pos.getNewOrder().getAmount());
                System.out.println("n_pending_amount: " + pos.getNewOrder().getPendingAmount());
                System.out.println("n_status: " + pos.getNewOrder().getStatus());
                System.out.println("n_created_at: " + pos.getNewOrder().getCreatedAt());
                System.out.println("------------------------------");
                System.out.println("close_orders:");
                for (PositionOrderDTO close_order : pos.getCloseOrders()) {
                    System.out.println("c_id: " + close_order.getId());
                    System.out.println("c_side: " + close_order.getSide());
                    System.out.println("c_rate: " + close_order.getRate());
                    System.out.println("c_amount: " + close_order.getAmount());
                    System.out.println("c_pending_amount: " + close_order.getPendingAmount());
                    System.out.println("c_status: " + close_order.getStatus());
                    System.out.println("c_created_at: " + close_order.getCreatedAt());
                    System.out.println("------------------------------");
                }
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
     * <b>残高</b><br>
     * 成功テスト
     */
    @Test
    public void getAccountsBalance() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        System.out.println("残高");
        try {
            temp = new JSONObject(coincheck.getAccountsBalance());
            success = temp.getBoolean("success");
            System.out.print("jpy: ");
            System.out.println(temp.isNull("jpy") ? "null" : temp.getString("jpy"));
            System.out.print("btc: ");
            System.out.println(temp.isNull("btc") ? "null" : temp.getString("btc"));
            System.out.print("jpy_reserved: ");
            System.out.println(temp.isNull("jpy_reserved") ? "null" : temp.getString("jpy_reserved"));
            System.out.print("btc_reserved: ");
            System.out.println(temp.isNull("btc_reserved") ? "null" : temp.getString("btc_reserved"));
            System.out.print("jpy_lend_in_use: ");
            System.out.println(temp.isNull("jpy_lend_in_use") ? "null" : temp.getString("jpy_lend_in_use"));
            System.out.print("btc_lend_in_use: ");
            System.out.println(temp.isNull("btc_lend_in_use") ? "null" : temp.getString("btc_lend_in_use"));
            System.out.print("jpy_lent: ");
            System.out.println(temp.isNull("jpy_lent") ? "null" : temp.getString("jpy_lent"));
            System.out.print("btc_lent");
            System.out.println(temp.isNull("btc_lent") ? "null" : temp.getString("btc_lent"));
            System.out.print("jpy_dept: ");
            System.out.println(temp.isNull("jpy_dept") ? "null" : temp.getString("jpy_dept"));
            System.out.print("btc_dept: ");
            System.out.println(temp.isNull("btc_dept") ? "null" : temp.getString("btc_dept"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertTrue(success);
    }

    /**
     * <b>レバレッジアカウントの残高</b><br>
     * 成功テスト
     */
    @Test
    public void getAccountsLeverageBalance() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        System.out.println("レバレッジアカウントの残高");
        try {
            temp = new JSONObject(coincheck.getAccountsLeverageBalance());
            success = temp.getBoolean("success");
            System.out.print("証拠金: ");
            System.out.println(temp.isNull("margin") ? "null"
                    : (temp.getJSONObject("margin").isNull("jpy") ? "null"
                            : temp.getJSONObject("margin").getString("jpy")));
            System.out.print("利用可能な証拠金");
            System.out.println(temp.isNull("margin_available") ? "null"
                    : (temp.getJSONObject("margin_available").isNull("jpy") ? "null"
                            : temp.getJSONObject("margin_available").getString("jpy")));
            System.out.print("証拠金維持率: ");
            System.out.println(temp.isNull("margin_level") ? "null" : temp.getString("margin_level"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertTrue(success);
    }

    /**
     * <b>ビットコインの送金</b><br>
     * 成功テスト
     */
    @Test
    public void postSendMoney() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        String address = "32EYfRM6P9D1WUBwxzdE8gyodb6R3Kx9Cg";
        double amount = 0.005d;

        System.out.println("ビットコインの送金");

        try {
            temp = new JSONObject(coincheck.postSendMoney(address, amount));
            success = temp.getBoolean("success");
            System.out.print("id: ");
            System.out.println(temp.isNull("id") ? "null" : temp.getString("id"));
            System.out.print("address: ");
            System.out.println(temp.isNull("address") ? "null" : temp.getString("address"));
            System.out.print("amount: ");
            System.out.println(temp.isNull("amount") ? "null" : temp.getString("amount"));
            System.out.print("fee: ");
            System.out.println(temp.isNull("fee") ? "null" : temp.getString("fee"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
        assertTrue(success);
    }

    /**
     * <b>送金履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getSendMoney() {
        JSONObject temp = null;
        coincheck.setAPIkey(API_KEY);
        coincheck.setAPIsecret(API_SECRET);
        boolean success = false;
        List<SendMoneyTransactionDTO> sendMoneyTransactions = new ArrayList<>();
        try {
            temp = new JSONObject(coincheck.getSendMoney(Currency.BTC));
            success = temp.getBoolean("success");
            JSONArray sends = temp.getJSONArray("sends");
            for (int i = 0; i < sends.length(); i++) {
                JSONObject send = sends.getJSONObject(i);
                SendMoneyTransactionDTO sendMoney = new SendMoneyTransactionDTO();
                sendMoney.setId(send.isNull("id") ? -1l : send.getLong("id"));
                sendMoney.setAmount(send.isNull("amount") ? -1.0d : Double.parseDouble(send.getString("amount")));
                sendMoney.setCurrency(send.isNull("currency") ? "" : send.getString("currency"));
                sendMoney.setFee(send.isNull("fee") ? -1.0d : Double.parseDouble(send.getString("fee")));
                sendMoney.setAddress(send.isNull("address") ? "null" : send.getString("address"));
                sendMoney.setCreatedAt(send.isNull("created_at") ? null : send.getString("created_at"));

                sendMoneyTransactions.add(sendMoney);
            }

            System.out.println("送金履歴");
            System.out.println("success: " + success);
            System.out.println("------------------------------");
            for (SendMoneyTransactionDTO item : sendMoneyTransactions) {
                System.out.println("ID: " + item.getId());
                System.out.println("送った量: " + item.getAmount());
                System.out.println("通貨: " + item.getCurrency());
                System.out.println("手数料: " + item.getFee());
                System.out.println("送金先のアドレス: " + item.getAddress());
                System.out.println("日時: " + item.getCreatedAt());
                System.out.println("------------------------------");
            }
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
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
