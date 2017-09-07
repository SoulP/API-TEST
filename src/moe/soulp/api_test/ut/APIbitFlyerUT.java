package moe.soulp.api_test.ut;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import moe.soulp.api_test.api.APIbitFlyer;
import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.bitFlyer.dto.ExecutionGetDTO;

/**
 * <b>bitFlyer用のUTテストケース</b><br>
 * date: 2017/09/07 last_date: 2017/09/07
 * 
 * @author ソウルP
 */
public class APIbitFlyerUT extends APIkey {
    final static APIbitFlyer bitFlyer = new APIbitFlyer(BITFLYER_API_KEY, BITFLYER_API_SECRET);

    /**
     * <b>マーケットの一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getMarkets() {
        JSONArray temp = null;
        System.out.println("マーケットの一覧");
        try {
            temp = new JSONArray(bitFlyer.getMarkets());
            for (int i = 0; i < temp.length(); i++) {
                System.out.println("product_code: " + temp.getJSONObject(i).getString("product_code"));
            }
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>板情報</b><br>
     * 成功テスト
     */
    @Test
    public void getBoard() {
        JSONObject temp = null;
        System.out.println("板情報");
        try {
            temp = new JSONObject(bitFlyer.getBoard());
            JSONArray asks = temp.getJSONArray("asks");
            JSONArray bids = temp.getJSONArray("bids");
            JSONObject maxAsk = asks.getJSONObject(asks.length() - 1);
            JSONObject minAsk = asks.getJSONObject(0);
            JSONObject maxBid = bids.getJSONObject(0);
            JSONObject minBid = bids.getJSONObject(bids.length() - 1);
            System.out.println("仲値: " + temp.getDouble("mid_price"));
            System.out.println("最高販売価格: " + maxAsk.getDouble("price") + ", 量: " + maxAsk.getDouble("size"));
            System.out.println("最安販売価格: " + minAsk.getDouble("price") + ", 量: " + minAsk.getDouble("size"));
            System.out.println("最高買取価格: " + maxBid.getDouble("price") + ", 量: " + maxBid.getDouble("size"));
            System.out.println("最安買取価格: " + minBid.getDouble("price") + ", 量: " + minBid.getDouble("size"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>建玉の板情報</b><br>
     * 成功テスト
     */
    @Test
    public void getBoard_FX() {
        JSONObject temp = null;
        System.out.println("板情報");
        try {
            temp = new JSONObject(bitFlyer.getBoard(Pair.FX_BTC_JPY));
            JSONArray asks = temp.getJSONArray("asks");
            JSONArray bids = temp.getJSONArray("bids");
            JSONObject maxAsk = asks.getJSONObject(asks.length() - 1);
            JSONObject minAsk = asks.getJSONObject(0);
            JSONObject maxBid = bids.getJSONObject(0);
            JSONObject minBid = bids.getJSONObject(bids.length() - 1);
            System.out.println("仲値: " + temp.getDouble("mid_price"));
            System.out.println("最高販売価格: " + maxAsk.getDouble("price") + ", 量: " + maxAsk.getDouble("size"));
            System.out.println("最安販売価格: " + minAsk.getDouble("price") + ", 量: " + minAsk.getDouble("size"));
            System.out.println("最高買取価格: " + maxBid.getDouble("price") + ", 量: " + maxBid.getDouble("size"));
            System.out.println("最安買取価格: " + minBid.getDouble("price") + ", 量: " + minBid.getDouble("size"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>Ticker</b><br>
     * 成功テスト
     */
    @Test
    public void getTicker() {
        JSONObject temp = null;
        System.out.println("Ticker");
        try {
            temp = new JSONObject(bitFlyer.getTicker());
            System.out.println("product_code: " + temp.getString("product_code"));
            System.out.println("日時: " + BitFlyerable.string2zonedDateTime(temp.getString("timestamp")));
            System.out.println("TickerのID: " + temp.getLong("tick_id"));
            System.out.println("ベストな買取価格: " + temp.getDouble("best_bid"));
            System.out.println("ベストな買取価格の量: " + temp.getDouble("best_bid_size"));
            System.out.println("ベストな販売価格: " + temp.getDouble("best_ask"));
            System.out.println("ベストな販売価格の量: " + temp.getDouble("best_ask_size"));
            System.out.println("買取合計の深さ: " + temp.getDouble("total_bid_depth"));
            System.out.println("販売合計の深さ: " + temp.getDouble("total_ask_depth"));
            System.out.println("最終取引価格: " + temp.getDouble("ltp"));
            System.out.println("24 時間の取引量: " + temp.getDouble("volume"));
            System.out.println("マーケットの24 時間の取引量: " + temp.getDouble("volume_by_product"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>Ticker 建玉</b><br>
     * 成功テスト
     */
    @Test
    public void getTicker_FX() {
        JSONObject temp = null;
        System.out.println("Ticker 建玉");
        try {
            temp = new JSONObject(bitFlyer.getTicker(Pair.FX_BTC_JPY));
            System.out.println("product_code: " + temp.getString("product_code"));
            System.out.println("日時: " + BitFlyerable.string2zonedDateTime(temp.getString("timestamp")));
            System.out.println("TickerのID: " + temp.getLong("tick_id"));
            System.out.println("ベストな買取価格: " + temp.getDouble("best_bid"));
            System.out.println("ベストな買取価格の量: " + temp.getDouble("best_bid_size"));
            System.out.println("ベストな販売価格: " + temp.getDouble("best_ask"));
            System.out.println("ベストな販売価格の量: " + temp.getDouble("best_ask_size"));
            System.out.println("買取合計の深さ: " + temp.getDouble("total_bid_depth"));
            System.out.println("販売合計の深さ: " + temp.getDouble("total_ask_depth"));
            System.out.println("最終取引価格: " + temp.getDouble("ltp"));
            System.out.println("24 時間の取引量: " + temp.getDouble("volume"));
            System.out.println("マーケットの24 時間の取引量: " + temp.getDouble("volume_by_product"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>約定履歴(GET)</b><br>
     * 成功テスト
     */
    @Test
    public void getTrades() {
        JSONArray temp = null;
        System.out.println("約定履歴");
        try {
            temp = new JSONArray(bitFlyer.getTrades());
            List<ExecutionGetDTO> executions = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject exec = temp.getJSONObject(i);
                ExecutionGetDTO execution = new ExecutionGetDTO();
                execution.setId(exec.getLong("id"));
                execution.setSide(exec.getString("side"));
                execution.setPrice(exec.getDouble("price"));
                execution.setSize(exec.getDouble("size"));
                execution.setExecDate(exec.getString("exec_date"));
                execution.setBuyChildOrderAcceptanceId(exec.getString("buy_child_order_acceptance_id"));
                execution.setSellChildOrderAcceptanceId(exec.getString("sell_child_order_acceptance_id"));

                executions.add(execution);
            }

            System.out.println("------------------------------");
            executions.forEach(exec -> {
                System.out.println("ID: " + exec.getId());
                System.out.println("注文の種類: " + exec.getSide());
                System.out.println("価格: " + exec.getPrice());
                System.out.println("量: " + exec.getSize());
                System.out.println("約定日時: " + exec.getExecDate());
                System.out.println("新規注文の買いのID: " + exec.getBuyChildOrderAcceptanceId());
                System.out.println("新規注文の売りのID: " + exec.getSellChildOrderAcceptanceId());
                System.out.println("------------------------------");
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>約定履歴(GET)</b><br>
     * cout指定あり 成功テスト
     */
    @Test
    public void getTrades01() {
        JSONArray temp = null;
        System.out.println("約定履歴");
        try {
            temp = new JSONArray(bitFlyer.getTrades(5));
            List<ExecutionGetDTO> executions = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject exec = temp.getJSONObject(i);
                ExecutionGetDTO execution = new ExecutionGetDTO();
                execution.setId(exec.getLong("id"));
                execution.setSide(exec.getString("side"));
                execution.setPrice(exec.getDouble("price"));
                execution.setSize(exec.getDouble("size"));
                execution.setExecDate(exec.getString("exec_date"));
                execution.setBuyChildOrderAcceptanceId(exec.getString("buy_child_order_acceptance_id"));
                execution.setSellChildOrderAcceptanceId(exec.getString("sell_child_order_acceptance_id"));

                executions.add(execution);
            }

            System.out.println("------------------------------");
            executions.forEach(exec -> {
                System.out.println("ID: " + exec.getId());
                System.out.println("注文の種類: " + exec.getSide());
                System.out.println("価格: " + exec.getPrice());
                System.out.println("量: " + exec.getSize());
                System.out.println("約定日時: " + exec.getExecDate());
                System.out.println("新規注文の買いのID: " + exec.getBuyChildOrderAcceptanceId());
                System.out.println("新規注文の売りのID: " + exec.getSellChildOrderAcceptanceId());
                System.out.println("------------------------------");
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>取引所の状態</b><br>
     * 成功テスト
     */
    @Test
    public void getHealth() {
        JSONObject temp = null;
        System.out.println("取引所の状態");
        try {
            temp = new JSONObject(bitFlyer.getHealth());
            System.out.print("状態: ");
            String str = temp.getString("status");
            switch (str) {
                case "NORMAL":
                    str = "通常";
                    break;
                case "BUSY":
                    str = "負荷 - 小";
                    break;
                case "VERY BUSY":
                    str = "負荷 - 中";
                    break;
                case "SUPER BUSY":
                    str = "負荷 - 高";
                    break;
                default:
                    break;
            }
            System.out.println(str);
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>チャット</b><br>
     * 成功テスト
     */
    @Test
    public void getChats() {
        JSONArray chats = null;
        System.out.println("チャット");
        try {
            chats = new JSONArray(bitFlyer.getChats(ZonedDateTime.now().minusMinutes(1l)));
            System.out.println("------------------------------");
            for (int i = 0; i < chats.length(); i++) {
                JSONObject temp = chats.getJSONObject(i);
                System.out.println("日時: " + BitFlyerable.string2zonedDateTime(temp.getString("date")));
                System.out.println("ニックネーム: " + temp.getString("nickname"));
                System.out.println("メッセージ: " + temp.getString("message"));
                System.out.println("------------------------------");
            }
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(chats);
    }

    /**
     * <b>レート取得</b><br>
     * 成功テスト
     */
    @Test
    public void getRate() {
        JSONObject temp = null;
        System.out.println("レート取得");
        try {
            temp = new JSONObject(bitFlyer.getRate());
            System.out.println("仲値: " + temp.getDouble("mid"));
            System.out.println("販売価格: " + temp.getDouble("ask"));
            System.out.println("買取価格: " + temp.getDouble("bid"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }
}
