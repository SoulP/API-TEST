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
import moe.soulp.api_test.api.Currency;
import moe.soulp.api_test.api.Pair;
import moe.soulp.api_test.api.Type;
import moe.soulp.api_test.bitFlyer.dto.BankAccountDTO;
import moe.soulp.api_test.bitFlyer.dto.CoinInDTO;
import moe.soulp.api_test.bitFlyer.dto.CoinOutDTO;
import moe.soulp.api_test.bitFlyer.dto.ExecutionGetDTO;
import moe.soulp.api_test.bitFlyer.dto.ExecutionMeDTO;
import moe.soulp.api_test.bitFlyer.dto.MoneyTransactionDTO;
import moe.soulp.api_test.bitFlyer.dto.NewParentOrderDTO;
import moe.soulp.api_test.bitFlyer.dto.OrderDTO;
import moe.soulp.api_test.bitFlyer.dto.ParentOrderDTO;

/**
 * <b>bitFlyer用のUTテストケース</b><br>
 * date: 2017/09/07 last_date: 2017/09/21
 * 
 * @author ソウルP
 */
public class APIbitFlyerUT extends APIkey {
    final static APIbitFlyer bitFlyer = new APIbitFlyer(BITFLYER_API_KEY, BITFLYER_API_SECRET);
    final static String      LINE     = "------------------------------";

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
                execution.setPrice(exec.getLong("price"));
                execution.setSize(exec.getDouble("size"));
                execution.setExecDate(exec.getString("exec_date"));
                execution.setBuyChildOrderAcceptanceId(exec.getString("buy_child_order_acceptance_id"));
                execution.setSellChildOrderAcceptanceId(exec.getString("sell_child_order_acceptance_id"));

                executions.add(execution);
            }

            System.out.println(LINE);
            executions.forEach(exec -> {
                System.out.println("ID: " + exec.getId());
                System.out.println("注文の種類: " + exec.getSide());
                System.out.println("価格: " + exec.getPrice());
                System.out.println("量: " + exec.getSize());
                System.out.println("約定日時: " + exec.getExecDate());
                System.out.println("新規注文の買いのID: " + exec.getBuyChildOrderAcceptanceId());
                System.out.println("新規注文の売りのID: " + exec.getSellChildOrderAcceptanceId());
                System.out.println(LINE);
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
            temp = new JSONArray(bitFlyer.getTrades(5, null, null));
            List<ExecutionGetDTO> executions = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject exec = temp.getJSONObject(i);
                ExecutionGetDTO execution = new ExecutionGetDTO();
                execution.setId(exec.getLong("id"));
                execution.setSide(exec.getString("side"));
                execution.setPrice(exec.getLong("price"));
                execution.setSize(exec.getDouble("size"));
                execution.setExecDate(exec.getString("exec_date"));
                execution.setBuyChildOrderAcceptanceId(exec.getString("buy_child_order_acceptance_id"));
                execution.setSellChildOrderAcceptanceId(exec.getString("sell_child_order_acceptance_id"));

                executions.add(execution);
            }

            System.out.println(LINE);
            executions.forEach(exec -> {
                System.out.println("ID: " + exec.getId());
                System.out.println("注文の種類: " + exec.getSide());
                System.out.println("価格: " + exec.getPrice());
                System.out.println("量: " + exec.getSize());
                System.out.println("約定日時: " + exec.getExecDate());
                System.out.println("新規注文の買いのID: " + exec.getBuyChildOrderAcceptanceId());
                System.out.println("新規注文の売りのID: " + exec.getSellChildOrderAcceptanceId());
                System.out.println(LINE);
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
            System.out.println(LINE);
            for (int i = 0; i < chats.length(); i++) {
                JSONObject temp = chats.getJSONObject(i);
                System.out.println("日時: " + BitFlyerable.string2zonedDateTime(temp.getString("date")));
                System.out.println("ニックネーム: " + temp.getString("nickname"));
                System.out.println("メッセージ: " + temp.getString("message"));
                System.out.println(LINE);
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

    /**
     * <b>API キーの権限を取得</b><br>
     * 成功テスト
     */
    @Test
    public void getPermissions() {
        JSONArray temp = null;
        System.out.println("API キーの権限を取得");
        try {
            temp = new JSONArray(bitFlyer.getPermissions());
            for (int i = 0; i < temp.length(); i++)
                System.out.println(temp.getString(i));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>資産残高を取得</b><br>
     * 成功テスト
     */
    @Test
    public void getBalance() {
        JSONArray temp = null;
        try {
            temp = new JSONArray(bitFlyer.getBalance());
            for (int i = 0; i < temp.length(); i++) {
                JSONObject balance = temp.getJSONObject(i);
                System.out.println(balance.getString("currency_code") + ": " + balance.getDouble("amount") + "(利用可能: "
                        + balance.getDouble("available") + ")");
            }
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>通貨別の証拠金の数量を取得</b><br>
     * 成功テスト
     */
    @Test
    public void getCollateralAccounts() {
        JSONArray temp = null;
        System.out.println("通貨別の証拠金の数量を取得");
        try {
            temp = new JSONArray(bitFlyer.getCollateralAccounts());
            for (int i = 0; i < temp.length(); i++) {
                JSONObject collateral = temp.getJSONObject(i);
                System.out.println(collateral.getString("currency_code") + ": " + collateral.getDouble("amount"));
            }
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>預入用ビットコイン・イーサリアムアドレス取得</b><br>
     * 成功テスト
     */
    @Test
    public void getAddresses() {
        JSONArray temp = null;
        System.out.println("預入用ビットコイン・イーサリアムアドレス取得");
        try {
            temp = new JSONArray(bitFlyer.getAddresses());
            for (int i = 0; i < temp.length(); i++) {
                JSONObject address = temp.getJSONObject(i);
                System.out.println("種類: " + address.getString("type"));
                System.out.println(address.getString("currency_code") + "アドレス: " + address.getString("address"));
                System.out.println();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getDepositCoin() {
        JSONArray temp = null;
        System.out.println("ビットコイン・イーサ預入履歴");
        try {
            temp = new JSONArray(bitFlyer.getDepositCoins());
            List<CoinInDTO> ins = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject coin = temp.getJSONObject(i);
                CoinInDTO in = new CoinInDTO();
                in.setId(coin.getLong("id"));
                in.setOrderId(coin.getString("order_id"));
                in.setCurrencyCode(coin.getString("currency_code"));
                in.setAmount(coin.getDouble("amount"));
                in.setAddress(coin.getString("address"));
                in.setTxHash(coin.getString("tx_hash"));
                in.setStatus(coin.getString("status"));
                in.setEventDate(coin.getString("event_date"));

                ins.add(in);
            }
            System.out.println(LINE);
            ins.forEach(in -> {
                System.out.println("ID: " + in.getId());
                System.out.println("注文のID: " + in.getOrderId());
                System.out.println("通貨: " + in.getCurrencyCode());
                System.out.println("量: " + in.getAmount());
                System.out.println("アドレス: " + in.getAddress());
                System.out.println("トランザクションのハッシュ: " + in.getTxHash());
                System.out.println("状態: " + in.getStatus());
                System.out.println("日時: " + in.getEventDate());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>ビットコイン・イーサ預入履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getSendCoin() {
        JSONArray temp = null;
        System.out.println("ビットコイン・イーサ預入履歴");
        try {
            temp = new JSONArray(bitFlyer.getSendCoins());
            List<CoinOutDTO> outs = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject coin = temp.getJSONObject(i);
                CoinOutDTO out = new CoinOutDTO();
                out.setId(coin.getLong("id"));
                out.setOrderId(coin.getString("order_id"));
                out.setCurrencyCode(coin.getString("currency_code"));
                out.setAmount(coin.getDouble("amount"));
                out.setAddress(coin.getString("address"));
                out.setTxHash(coin.getString("tx_hash"));
                out.setFee(coin.getDouble("fee"));
                out.setAdditionalFee(coin.getDouble("additional_fee"));
                out.setStatus(coin.getString("status"));
                out.setEventDate(coin.getString("event_date"));

                outs.add(out);
            }
            System.out.println(LINE);
            outs.forEach(out -> {
                System.out.println("ID: " + out.getId());
                System.out.println("注文のID: " + out.getOrderId());
                System.out.println("通貨: " + out.getCurrencyCode());
                System.out.println("量: " + out.getAmount());
                System.out.println("アドレス: " + out.getAddress());
                System.out.println("トランザクションのハッシュ: " + out.getTxHash());
                System.out.println("手数料: " + out.getFee());
                System.out.println("追加手数料: " + out.getAdditionalFee());
                System.out.println("状態: " + out.getStatus());
                System.out.println("日時: " + out.getEventDate());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>銀行口座一覧取得</b><br>
     * 成功テスト
     */
    @Test
    public void getBankAccounts() {
        JSONArray temp = null;
        System.out.println("銀行口座一覧取得");
        try {
            temp = new JSONArray(bitFlyer.getBankAccounts());
            List<BankAccountDTO> accounts = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject info = temp.getJSONObject(i);
                BankAccountDTO account = new BankAccountDTO();
                account.setId(info.getLong("id"));
                account.setIsVerified(info.getBoolean("is_verified"));
                account.setBankName(info.getString("bank_name"));
                account.setBranchName(info.getString("branch_name"));
                account.setAccountType(info.getString("account_type"));
                account.setAccountNumber(info.getString("account_number"));
                account.setAccountName(info.getString("account_name"));

                accounts.add(account);
            }

            System.out.println(LINE);
            accounts.forEach(account -> {
                System.out.println("口座のID: " + account.getId());
                System.out.println("承認有無: " + account.getIsVerified());
                System.out.println("銀行名: " + account.getBankName());
                System.out.println("支店名: " + account.getBranchName());
                System.out.println("口座種類: " + account.getAccountType());
                System.out.println("口座番号: " + account.getAccountNumber());
                System.out.println("口座名義: " + account.getAccountName());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>入金履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getDeposits() {
        JSONArray temp = null;
        System.out.println("入金履歴");
        try {
            temp = new JSONArray(bitFlyer.getDeposits());
            List<MoneyTransactionDTO> deposits = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject tran = temp.getJSONObject(i);
                MoneyTransactionDTO deposit = new MoneyTransactionDTO();

                deposit.setId(tran.getLong("id"));
                deposit.setOrderId(tran.getString("order_id"));
                deposit.setCurrencyCode(tran.getString("currency_code"));
                deposit.setAmount(tran.getDouble("amount"));
                deposit.setStatus(tran.getString("status"));
                deposit.setEventDate(tran.getString("event_date"));

                deposits.add(deposit);
            }

            System.out.println(LINE);
            deposits.forEach(d -> {
                System.out.println("入金のID: " + d.getId());
                System.out.println("注文のID: " + d.getOrderId());
                System.out.println("通貨: " + d.getCurrencyCode());
                System.out.println("金額: " + d.getAmount());
                System.out.println("状態: " + d.getStatus());
                System.out.println("日時: " + d.getEventDate());
                System.out.println(LINE);
            });

            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>出金</b><br>
     * 成功テスト
     */
    @Test
    public void withdraw() {
        JSONObject temp = null;
        long bank_account_id = 0l;
        long amount = 0l;
        Currency currency_code = Currency.JPY;
        System.out.println("出金");

        try {
            temp = new JSONObject(bitFlyer.withdraw(bank_account_id, amount, currency_code));
            System.out.println("口座のID: " + bank_account_id);
            System.out.println("金額(" + currency_code + "): " + amount);
            System.out.println("出金メッセージの受付 ID: " + temp.getString("message_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>出金履歴</b><br>
     * 成功テスト
     */
    @Test
    public void getWithdraws() {
        JSONArray temp = null;
        System.out.println("出金履歴");
        try {
            temp = new JSONArray(bitFlyer.getWithdraws());
            List<MoneyTransactionDTO> withdrawals = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                JSONObject tran = temp.getJSONObject(i);
                MoneyTransactionDTO withdraw = new MoneyTransactionDTO();

                withdraw.setId(tran.getLong("id"));
                withdraw.setOrderId(tran.getString("order_id"));
                withdraw.setCurrencyCode(tran.getString("currency_code"));
                withdraw.setAmount(tran.getDouble("amount"));
                withdraw.setStatus(tran.getString("status"));
                withdraw.setEventDate(tran.getString("event_date"));

                withdrawals.add(withdraw);
            }

            System.out.println(LINE);
            withdrawals.forEach(w -> {
                System.out.println("出金のID: " + w.getId());
                System.out.println("注文のID: " + w.getOrderId());
                System.out.println("通貨: " + w.getCurrencyCode());
                System.out.println("金額: " + w.getAmount());
                System.out.println("状態: " + w.getStatus());
                System.out.println("日時: " + w.getEventDate());
                System.out.println(LINE);
            });

            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>指値注文 現物取引 買い</b><br>
     * 成功テスト
     */
    @Test
    public void orderBuy() {
        JSONObject buy = null;
        long price = 0l;
        double size = 0d;

        System.out.println("指値注文 現物取引 買い");
        System.out.println("価格: " + price);
        System.out.println("量: " + size);
        try {
            buy = new JSONObject(bitFlyer.orderBuy(price, size));
            System.out.println("新規注文のID: " + buy.getString("child_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(buy);
    }

    /**
     * <b>指値注文 現物取引 売り</b><br>
     * 成功テスト
     */
    @Test
    public void orderSell() {
        JSONObject sell = null;
        long price = 0l;
        double size = 0d;

        System.out.println("指値注文 現物取引 売り");
        System.out.println("価格: " + price);
        System.out.println("量: " + size);
        try {
            sell = new JSONObject(bitFlyer.orderSell(price, size));
            System.out.println("新規注文のID: " + sell.getString("child_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(sell);
    }

    /**
     * <b>成行注文 現物取引 買い</b><br>
     * 成功テスト
     */
    @Test
    public void orderMarketBuy() {
        JSONObject buy = null;
        double size = 0d;

        System.out.println("指値注文 現物取引 買い");
        System.out.println("量: " + size);
        try {
            buy = new JSONObject(bitFlyer.orderMarketBuy(size));
            System.out.println("新規注文のID: " + buy.getString("child_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(buy);
    }

    /**
     * <b>成行注文 現物取引 売り</b><br>
     * 成功テスト
     */
    @Test
    public void orderMarketSell() {
        JSONObject sell = null;
        double size = 0d;

        System.out.println("指値注文 現物取引 売り");
        System.out.println("量: " + size);
        try {
            sell = new JSONObject(bitFlyer.orderMarketSell(size));
            System.out.println("新規注文のID: " + sell.getString("child_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(sell);
    }

    /**
     * <b>成行注文 建玉取引 買い</b><br>
     * 成功テスト
     */
    @Test
    public void leverageBuy() {
        JSONObject buy = null;
        double size = 0d;

        System.out.println("成行注文 建玉取引 買い");
        System.out.println("量: " + size);
        try {
            buy = new JSONObject(bitFlyer.leverageBuy(size));
            System.out.println("新規注文のID: " + buy.getString("child_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(buy);
    }

    /**
     * <b>指値注文 建玉取引 売り</b><br>
     * 成功テスト
     */
    @Test
    public void leverageSell() {
        JSONObject sell = null;
        long price = 0l;
        double size = 0d;

        System.out.println("指値注文 建玉取引 売り");
        System.out.println("価格: " + price);
        System.out.println("量: " + size);
        try {
            sell = new JSONObject(bitFlyer.leverageSell(price, size));
            System.out.println("新規注文のID: " + sell.getString("child_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(sell);
    }

    /**
     * <b>注文キャンセル</b><br>
     * 成功テスト
     */
    @Test
    public void deleteOrder() {
        String child_order_id = "";
        System.out.println("注文キャンセル");
        assertNotNull(bitFlyer.deleteOrder(child_order_id));
    }

    /**
     * <b>新規の親注文（特殊注文）</b><br>
     * 成功テスト
     */
    @Test
    public void newParentOrder() {
        JSONObject temp = null;
        NewParentOrderDTO order = new NewParentOrderDTO();
        order.setOrderMethod(Type.IFDOCO);
        order.setMinuteToExpire(10000);
        order.setConditionType(Type.LIMIT);
        order.setSide(Type.BUY);
        order.setPrice(300000l);
        order.setSize(0.1d);
        order.commit();
        order.setConditionType(Type.LIMIT);
        order.setSide(Type.SELL);
        order.setPrice(320000l);
        order.setSize(0.1d);
        order.commit();
        order.setConditionType(Type.STOP_LIMIT);
        order.setSide(Type.SELL);
        order.setPrice(288000l);
        order.setTriggerPrice(290000l);
        order.setSize(0.1d);
        order.commit();

        System.out.println("新規の親注文（特殊注文）");
        try {
            temp = new JSONObject(bitFlyer.newParentOrder(order));
            System.out.println("新規の親注文のID: " + temp.getString("parent_order_acceptance_id"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>親注文キャンセル</b><br>
     * 成功テスト
     */
    @Test
    public void deleteParentOrder() {
        String parent_order_id = "";
        System.out.println("親注文キャンセル");
        assertNotNull(bitFlyer.deleteParentOrder(parent_order_id));
    }

    /**
     * <b>全ての注文キャンセル</b><br>
     * 成功テスト
     */
    @Test
    public void deleteAllOrders() {
        System.out.println("全ての注文キャンセル");
        assertNotNull(bitFlyer.deleteAllOrders());
    }

    /**
     * <b>全ての注文キャンセル</b><br>
     * プロダクトコード指定あり<br>
     * 成功テスト
     */
    @Test
    public void deleteAllOrders_FX() {
        System.out.println("全ての注文キャンセル");
        assertNotNull(bitFlyer.deleteAllOrders(Pair.FX_BTC_JPY));
    }

    /**
     * <b>未決済の注文一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersOpens() {
        JSONArray temp = null;
        System.out.println("未決済の注文一覧");

        try {
            temp = new JSONArray(bitFlyer.getOrdersOpens());
            List<OrderDTO> orders = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                OrderDTO order = new OrderDTO();
                try {
                    JSONObject obj = temp.getJSONObject(i);
                    order.setId(obj.getLong("id"));
                    order.setOrderId(obj.getString("child_order_id"));
                    order.setProductCode(obj.getString("product_code"));
                    order.setSide(obj.getString("side"));
                    order.setOrderType(obj.getString("child_order_type"));
                    order.setPrice(obj.getLong("price"));
                    order.setAveragePrice(obj.getLong("average_price"));
                    order.setSize(obj.getDouble("size"));
                    order.setOrderState(obj.getString("child_order_state"));
                    order.setExpireDate(obj.getString("expire_date"));
                    order.setOrderDate(obj.getString("child_order_date"));
                    order.setOrderAcceptanceId(obj.getString("child_order_acceptance_id"));
                    order.setOutstandingSize(obj.getDouble("outstanding_size"));
                    order.setCancelSize(obj.getDouble("cancel_size"));
                    order.setExecutedSize(obj.getDouble("executed_size"));
                    order.setTotalCommission(obj.getLong("total_commission"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    fail(e.getMessage());
                }

                orders.add(order);
            }

            System.out.println(LINE);
            orders.forEach(o -> {
                System.out.println("注文一覧のID: " + o.getId());
                System.out.println("注文のID: " + o.getOrderId());
                System.out.println("プロダクトコード: " + o.getProductCode());
                System.out.println("注文の種類: " + o.getSide());
                System.out.println("注文の種類: " + o.getOrderType());
                System.out.println("価格: " + o.getPrice());
                System.out.println("平均価格: " + o.getAveragePrice());
                System.out.println("量: " + o.getSize());
                System.out.println("注文の状態: " + o.getOrderState());
                System.out.println("有効期限: " + o.getExpireDate());
                System.out.println("注文日時: " + o.getOrderDate());
                System.out.println("新規注文のID: " + o.getOrderAcceptanceId());
                System.out.println("未決済の量: " + o.getOutstandingSize());
                System.out.println("約定した量: " + o.getExecutedSize());
                System.out.println("合計手数料: " + o.getTotalCommission());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * <b>注文一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getOrders() {
        JSONArray temp = null;
        System.out.println("注文一覧");

        try {
            temp = new JSONArray(bitFlyer.getOrders());
            List<OrderDTO> orders = new ArrayList<>();
            for (int i = 0; i < temp.length(); i++) {
                OrderDTO order = new OrderDTO();
                try {
                    JSONObject obj = temp.getJSONObject(i);
                    order.setId(obj.getLong("id"));
                    order.setOrderId(obj.getString("child_order_id"));
                    order.setProductCode(obj.getString("product_code"));
                    order.setSide(obj.getString("side"));
                    order.setOrderType(obj.getString("child_order_type"));
                    order.setPrice(obj.getLong("price"));
                    order.setAveragePrice(obj.getLong("average_price"));
                    order.setSize(obj.getDouble("size"));
                    order.setOrderState(obj.getString("child_order_state"));
                    order.setExpireDate(obj.getString("expire_date"));
                    order.setOrderDate(obj.getString("child_order_date"));
                    order.setOrderAcceptanceId(obj.getString("child_order_acceptance_id"));
                    order.setOutstandingSize(obj.getDouble("outstanding_size"));
                    order.setCancelSize(obj.getDouble("cancel_size"));
                    order.setExecutedSize(obj.getDouble("executed_size"));
                    order.setTotalCommission(obj.getLong("total_commission"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    fail(e.getMessage());
                }

                orders.add(order);
            }

            System.out.println(LINE);
            orders.forEach(o -> {
                System.out.println("注文一覧のID: " + o.getId());
                System.out.println("注文のID: " + o.getOrderId());
                System.out.println("プロダクトコード: " + o.getProductCode());
                System.out.println("注文の種類: " + o.getSide());
                System.out.println("注文の種類: " + o.getOrderType());
                System.out.println("価格: " + o.getPrice());
                System.out.println("平均価格: " + o.getAveragePrice());
                System.out.println("量: " + o.getSize());
                System.out.println("注文の状態: " + o.getOrderState());
                System.out.println("有効期限: " + o.getExpireDate());
                System.out.println("注文日時: " + o.getOrderDate());
                System.out.println("新規注文のID: " + o.getOrderAcceptanceId());
                System.out.println("未決済の量: " + o.getOutstandingSize());
                System.out.println("約定した量: " + o.getExecutedSize());
                System.out.println("合計手数料: " + o.getTotalCommission());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * <b>親注文一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getParentOrders() {
        JSONArray temp = null;
        System.out.println("親注文一覧");

        try {
            temp = new JSONArray(bitFlyer.getParentOrders());
            List<OrderDTO> orders = new ArrayList<>();

            for (int i = 0; i < temp.length(); i++) {
                JSONObject parent = temp.getJSONObject(i);
                OrderDTO order = new OrderDTO();

                order.setId(parent.getLong("id"));
                order.setOrderId(parent.getString("parent_order_id"));
                order.setSide(parent.getString("side"));
                order.setPrice(parent.getLong("price"));
                order.setAveragePrice(parent.getLong("average_price"));
                order.setSize(parent.getDouble("size"));
                order.setOrderState(parent.getString("parent_order_state"));
                order.setExpireDate(parent.getString("expire_date"));
                order.setOrderDate(parent.getString("parent_order_date"));
                order.setOrderAcceptanceId(parent.getString("parent_order_acceptance_id"));
                order.setOutstandingSize(parent.getDouble("outstanding_size"));
                order.setCancelSize(parent.getDouble("cancel_size"));
                order.setExecutedSize(parent.getDouble("executed_size"));
                order.setTotalCommission(parent.getLong("total_commission"));

                orders.add(order);
            }

            System.out.println(LINE);
            orders.forEach(o -> {
                System.out.println("親注文一覧のID: " + o.getId());
                System.out.println("親注文のID: " + o.getOrderId());
                System.out.println("プロダクトコード: " + o.getProductCode());
                System.out.println("注文の種類: " + o.getSide());
                System.out.println("注文の種類: " + o.getOrderType());
                System.out.println("価格: " + o.getPrice());
                System.out.println("平均価格: " + o.getAveragePrice());
                System.out.println("量: " + o.getSize());
                System.out.println("注文の状態: " + o.getOrderState());
                System.out.println("有効期限: " + o.getExpireDate());
                System.out.println("注文日時: " + o.getOrderDate());
                System.out.println("新規の親注文のID: " + o.getOrderAcceptanceId());
                System.out.println("未決済の量: " + o.getOutstandingSize());
                System.out.println("約定した量: " + o.getExecutedSize());
                System.out.println("合計手数料: " + o.getTotalCommission());
                System.out.println(LINE);
            });
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>親注文詳細</b><br>
     * 成功テスト
     */
    @Test
    public void getParentOrderInfo() {
        JSONObject temp = null;
        String parent_order_id = "";
        System.out.println("親注文詳細");
        System.out.println();

        try {
            temp = new JSONObject(bitFlyer.getParentOrderInfo(parent_order_id, null));
            ParentOrderDTO info = new ParentOrderDTO();

            info.setId(temp.getLong("id"));
            info.setParentOrderId(temp.getString("parent_order_id"));
            info.setOrderMethod(temp.getString("order_method"));
            info.setMinuteToExpire(temp.getInt("minute_to_expire"));
            info.setParentOrderAcceptanceId(temp.getString("parent_order_acceptance_id"));
            JSONArray parameters = temp.getJSONArray("parameters");

            for (int i = 0; i < parameters.length(); i++) {
                JSONObject parameter = parameters.getJSONObject(i);

                info.setProductCode(parameter.getString("product_code"));
                info.setConditionType(parameter.getString("condition_type"));
                info.setSide(parameter.getString("side"));
                info.setPrice(parameter.getLong("price"));
                info.setSize(parameter.getDouble("size"));
                info.setTriggerPrice(parameter.getLong("trigger_price"));
                info.setOffset(parameter.getLong("offset"));
                info.commit();
            }

            System.out.println("親注文一覧のID: " + info.getId());
            System.out.println("親注文のID: " + info.getParentOrderId());
            System.out.println("新規の親注文のID: " + info.getParentOrderAcceptanceId());
            System.out.println("注文方法: " + info.getOrderMethod());
            System.out.println("有効期限（分）: " + info.getMinuteToExpire());
            System.out.println(LINE);
            info.getParameters().forEach(p -> {
                System.out.println("プロダクトコード: " + p.getProductCode());
                System.out.println("注文の種類（コンディション）: " + p.getConditionType());
                System.out.println("注文の種類（サイド）: " + p.getSide());
                System.out.println("価格: " + p.getPrice());
                System.out.println("量: " + p.getSize());
                System.out.println("トリガー価格: " + p.getTriggerPrice());
                System.out.println("トレール幅: " + p.getOffset());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>約定一覧</b><br>
     * 成功テスト
     */
    @Test
    public void getOrdersTransactions() {
        JSONArray temp = null;
        System.out.println("約定一覧");

        try {
            temp = new JSONArray(bitFlyer.getOrdersTransactions());
            List<ExecutionMeDTO> transactions = new ArrayList<>();

            for (int i = 0; i < temp.length(); i++) {
                JSONObject me = temp.getJSONObject(i);
                ExecutionMeDTO transaction = new ExecutionMeDTO();

                transaction.setId(me.getLong("id"));
                transaction.setChildOrderId(me.getString("child_order_id"));
                transaction.setSide(me.getString("side"));
                transaction.setPrice(me.getLong("price"));
                transaction.setSize(me.getDouble("size"));
                transaction.setCommission(me.getLong("commission"));
                transaction.setExecDate(me.getString("exec_date"));
                transaction.setChildOrderAcceptanceId(me.getString("child_order_acceptance_id"));

                transactions.add(transaction);
            }

            System.out.println(LINE);
            transactions.forEach(t -> {
                System.out.println("約定一覧のID: " + t.getId());
                System.out.println("注文のID: " + t.getChildOrderId());
                System.out.println("注文の種類: " + t.getSide());
                System.out.println("価格: " + t.getPrice());
                System.out.println("量: " + t.getSize());
                System.out.println("手数料: " + t.getCommission());
                System.out.println("新規注文のID: " + t.getChildOrderAcceptanceId());
                System.out.println(LINE);
            });
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }
}
