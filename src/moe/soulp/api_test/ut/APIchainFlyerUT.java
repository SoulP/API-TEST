package moe.soulp.api_test.ut;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import moe.soulp.api_test.api.APIchainFlyer;
import moe.soulp.api_test.api.ChainFlyerable;

/**
 * <b>chainFlyerのUTテストケース</b><br>
 * date: 2017/09/22 last_date: 2017/09/27
 * 
 * @author ソウルP
 */
public class APIchainFlyerUT {
    final static APIchainFlyer chainFlyer = new APIchainFlyer();
    final static String        LINE       = "------------------------------";

    /**
     * <b>ブロック</b><br>
     * 成功テスト
     */
    @Test
    public void getBlock() {
        JSONObject temp = null;
        String blockHash = "";
        System.out.println("ブロック");

        try {
            temp = new JSONObject(chainFlyer.getBlock(blockHash));

            System.out.println("ブロックハッシュ: " + temp.getString("block_hash"));
            System.out.println("ブロックの高さ: " + temp.getLong("height"));
            System.out.println("メインチェーン: " + temp.getBoolean("is_main"));
            System.out.println("ブロックのバージョン: " + temp.getLong("version"));
            System.out.println("前のブロックハッシュ: " + temp.getString("prev_block"));
            System.out.println("マークル・ルートのハッシュ: " + temp.getString("merkle_root"));
            System.out.println("ブロックが生成された日時: " + ChainFlyerable.string2zonedDateTime(temp.getString("timestamp")));
            System.out.println("マイニング難易度: " + temp.getLong("bits"));
            System.out.println("nonce: " + temp.getLong("nonce"));
            System.out.println("トランザクション数: " + temp.getInt("txnum"));
            System.out.println("合計手数料: " + String.format("%f", temp.getLong("total_fees") / 100000000.0d));

            System.out.println(LINE);
            JSONArray tx = temp.getJSONArray("tx_hashes");
            for (int i = 0; i < tx.length(); i++) {
                System.out.println("トランザクションハッシュ: " + tx.getString(i));
            }
            System.out.println(LINE);
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>ブロック</b><br>
     * 成功テスト
     */
    @Test
    public void getBlockLatest() {
        JSONObject temp = null;
        System.out.println("ブロック");

        try {
            temp = new JSONObject(chainFlyer.getBlockLatest());

            System.out.println("ブロックハッシュ: " + temp.getString("block_hash"));
            System.out.println("ブロックの高さ: " + temp.getLong("height"));
            System.out.println("メインチェーン: " + temp.getBoolean("is_main"));
            System.out.println("ブロックのバージョン: " + temp.getLong("version"));
            System.out.println("前のブロックハッシュ: " + temp.getString("prev_block"));
            System.out.println("マークル・ルートのハッシュ: " + temp.getString("merkle_root"));
            System.out.println("ブロックが生成された日時: " + ChainFlyerable.string2zonedDateTime(temp.getString("timestamp")));
            System.out.println("マイニング難易度: " + temp.getLong("bits"));
            System.out.println("nonce: " + temp.getLong("nonce"));
            System.out.println("トランザクション数: " + temp.getInt("txnum"));
            System.out.println("合計手数料: " + String.format("%f", temp.getLong("total_fees") / 100000000.0d));

            System.out.println(LINE);
            JSONArray tx = temp.getJSONArray("tx_hashes");
            for (int i = 0; i < tx.length(); i++) {
                System.out.println("トランザクションハッシュ: " + tx.getString(i));
            }
            System.out.println(LINE);
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>ブロック</b><br>
     * 成功テスト
     */
    @Test
    public void getBlockHeight() {
        JSONObject temp = null;
        long height = 0l;
        System.out.println("ブロック");

        try {
            temp = new JSONObject(chainFlyer.getBlockHeight(height));

            System.out.println("ブロックハッシュ: " + temp.getString("block_hash"));
            System.out.println("ブロックの高さ: " + temp.getLong("height"));
            System.out.println("メインチェーン: " + temp.getBoolean("is_main"));
            System.out.println("ブロックのバージョン: " + temp.getLong("version"));
            System.out.println("前のブロックハッシュ: " + temp.getString("prev_block"));
            System.out.println("マークル・ルートのハッシュ: " + temp.getString("merkle_root"));
            System.out.println("ブロックが生成された日時: " + ChainFlyerable.string2zonedDateTime(temp.getString("timestamp")));
            System.out.println("マイニング難易度: " + temp.getLong("bits"));
            System.out.println("nonce: " + temp.getLong("nonce"));
            System.out.println("トランザクション数: " + temp.getInt("txnum"));
            long total_fees = temp.getLong("total_fees");
            System.out.println(
                    "合計手数料: " + ((0 < total_fees) ? String.format("%f", total_fees / 100000000.0d) : total_fees));

            System.out.println(LINE);
            JSONArray tx = temp.getJSONArray("tx_hashes");
            for (int i = 0; i < tx.length(); i++) {
                System.out.println("トランザクションハッシュ: " + tx.getString(i));
            }
            System.out.println(LINE);
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>トランザクション</b><br>
     * 成功テスト
     */
    @Test
    public void getTx() {
        JSONObject temp = null;
        String txHash = "";
        System.out.println("トランザクション");

        try {
            temp = new JSONObject(chainFlyer.getTx(txHash));

            System.out.println("トランザクションハッシュ: " + temp.getString("tx_hash"));
            System.out.println("ブロックの高さ: " + temp.getLong("block_height"));
            System.out.println("承認された回数: " + temp.getInt("confirmed"));
            long fees = temp.getLong("fees");
            System.out.println("手数料: " + ((0 < fees) ? String.format("%f", fees / 100000000.0d) : fees));
            System.out.println("サイズ（bytes）: " + temp.getLong("size"));
            System.out.println("受取日時: " + ChainFlyerable.string2zonedDateTime(temp.getString("received_date")));
            System.out.println("トランザクションのバージョン: " + temp.getLong("version"));
            System.out.println("ロックタイム: " + temp.getLong("lock_time"));
            System.out.println("inputs");
            System.out.println(LINE);

            JSONArray inputs = temp.getJSONArray("inputs");
            for (int i = 0; i < inputs.length(); i++) {
                JSONObject input = inputs.getJSONObject(i);
                System.out.println("前のトランザクションハッシュ: " + input.getString("prev_hash"));
                System.out.println("前のトランザクションのインデックス: " + input.getLong("prev_index"));
                long value = input.getLong("value");
                System.out.println("ビットコインの数量: " + ((0 < value) ? String.format("%f", value / 100000000.0d) : value));
                System.out.println("署名スクリプト: " + input.getString("script"));
                System.out.println("ビットコインアドレス: " + input.getString("address"));
                System.out.println("シーケンス番号: " + input.getLong("sequence"));
                if (i != inputs.length() - 1) System.out.println();
            }
            System.out.println(LINE);

            System.out.println("outputs");
            JSONArray outputs = temp.getJSONArray("outputs");
            for (int i = 0; i < outputs.length(); i++) {
                JSONObject output = outputs.getJSONObject(i);
                long value = output.getLong("value");
                System.out.println("ビットコインの数量: " + ((0 < value) ? String.format("%f", value / 100000000.0d) : value));
                System.out.println("署名スクリプト: " + output.getString("script"));
                System.out.println("ビットコインアドレス: " + output.getString("address"));
                if (i != outputs.length() - 1) System.out.println();
            }
            System.out.println(LINE);
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }

    /**
     * <b>アドレス</b><br>
     * 成功テスト
     */
    @Test
    public void getAddress() {
        JSONObject temp = null;
        String address = "";
        System.out.println("アドレス");
        try {
            temp = new JSONObject(chainFlyer.getAddress(address));
            System.out.println("ビットコインアドレス: " + temp.getString("address"));
            System.out.println("未確定残高: " + temp.getDouble("unconfirmed_balance"));
            System.out.println("確定残高: " + temp.getDouble("confirmed_balance"));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertNotNull(temp);
    }
}
