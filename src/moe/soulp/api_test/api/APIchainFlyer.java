package moe.soulp.api_test.api;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <b>chainFlyerのAPI操作</b><br>
 * date: 2017/09/22 last_date: 2017/09/22
 * 
 * @author ソウルP
 * @version 1.0 2017/09/22 APIchainFlyer作成
 */
public class APIchainFlyer extends API implements ChainFlyerable {
    private static URL blockLatestURL;

    static {
        try {
            blockLatestURL = new URL(API + BLOCK_LATEST);
        } catch (MalformedURLException e) {
        }
    }

    /**
     * <b>ブロック</b><br>
     * ブロック情報取得
     * 
     * @param blockHash
     *            ブロックハッシュ
     * @return 【JSON】<br>
     *         <b>block_hash</b> ブロックハッシュ<br>
     *         <b>height</b> ブロックの高さ<br>
     *         <b>is_main</b> メインチェーンの場合はtrue<br>
     *         <b>version</b> ブロックのバージョン<br>
     *         <b>prev_block</b> 前のブロックハッシュ<br>
     *         <b>merkle_root</b> マークル・ルートのハッシュ<br>
     *         <b>timestamp</b> ブロックが生成された日時(UTC)<br>
     *         <b>bits</b> マイニング難易度<br>
     *         <b>nonce</b> ナンス<br>
     *         <b>txnum</b> トランザクションの個数<br>
     *         <b>total_fees</b> 手数料の合計（satoshi単位）<br>
     *         <b>tx_hashes</b> トランザクションハッシュ 【JSONArray】
     */
    @Override
    public String getBlock(String blockHash) {
        return publicAPI(API + BLOCK + blockHash, HttpMethod.GET);
    }

    /**
     * <b>ブロック</b><br>
     * 最新ブロック情報取得
     * 
     * @return 【JSON】<br>
     *         <b>block_hash</b> ブロックハッシュ<br>
     *         <b>height</b> ブロックの高さ<br>
     *         <b>is_main</b> メインチェーンの場合はtrue<br>
     *         <b>version</b> ブロックのバージョン<br>
     *         <b>prev_block</b> 前のブロックハッシュ<br>
     *         <b>merkle_root</b> マークル・ルートのハッシュ<br>
     *         <b>timestamp</b> ブロックが生成された日時(UTC)<br>
     *         <b>bits</b> マイニング難易度<br>
     *         <b>nonce</b> ナンス<br>
     *         <b>txnum</b> トランザクションの個数<br>
     *         <b>total_fees</b> 手数料の合計（satoshi単位）<br>
     *         <b>tx_hashes</b> トランザクションハッシュ 【JSONArray】
     */
    @Override
    public String getBlockLatest() {
        return publicAPI(blockLatestURL, HttpMethod.GET);
    }

    /**
     * <b>ブロック</b><br>
     * ブロック情報取得
     * 
     * @param height
     *            ブロックの高さ
     * @return 【JSON】<br>
     *         <b>block_hash</b> ブロックハッシュ<br>
     *         <b>height</b> ブロックの高さ<br>
     *         <b>prev_block</b> 前のブロックハッシュ<br>
     *         <b>is_main</b> メインチェーンの場合はtrue<br>
     *         <b>version</b> ブロックのバージョン<br>
     *         <b>merkle_root</b> マークル・ルートのハッシュ<br>
     *         <b>timestamp</b> ブロックが生成された日時（UTC）<br>
     *         <b>bits</b> マイニング難易度<br>
     *         <b>nonce</b> ナンス<br>
     *         <b>txnum</b> トランザクションの個数<br>
     *         <b>total_fees</b> 手数料の合計（satoshi単位）<br>
     *         <b>tx_hashes</b> トランザクションハッシュ 【JSONArray】
     */
    @Override
    public String getBlockHeight(long height) {
        return publicAPI(API + BLOCK_HEIGHT + height, HttpMethod.GET);
    }

    /**
     * <b>トランザクション</b><br>
     * トランザクション情報取得
     * 
     * @param txHash
     *            トランザクションハッシュ
     * @return 【JSON】<br>
     *         <b>tx_hash</b> トランザクションハッシュ<br>
     *         <b>block_height</b> このトランザクションを含むブロックの高さ。含まれていない場合は、-1<br>
     *         <b>confirmed</b> 承認された回数<br>
     *         <b>fees</b> 手数料（satoshi単位）<br>
     *         <b>size</b> サイズ（bytes）<br>
     *         <b>received_date</b> 受取日時（UTC）<br>
     *         <b>version</b> トランザクションのバージョン<br>
     *         <b>lock_time</b> ロックタイム<br>
     *         <hr>
     *         inputs 【JSONArray】<br>
     *         【JSON】<br>
     *         <b>prev_hash</b> 前のトランザクションのハッシュ<br>
     *         <b>prev_index</b> 前のトランザクションのインデックス<br>
     *         <b>value</b> ビットコインの数量（satoshi単位）<br>
     *         <b>script</b> 署名スクリプト（16進数表記）<br>
     *         <b>address</b> ビットコインアドレス<br>
     *         <b>sequence</b> シーケンス番号<br>
     *         <hr>
     *         outputs 【JSONArray】<br>
     *         【JSON】<br>
     *         <b>value</b> ビットコインの数量（satoshi単位）<br>
     *         <b>script</b> 署名スクリプト（16進数表記）<br>
     *         <b>address</b> ビットコインアドレス
     */
    @Override
    public String getTx(String txHash) {
        return publicAPI(API + TX + txHash, HttpMethod.GET);
    }

    /**
     * <b>アドレス</b><br>
     * アドレスの情報取得
     * 
     * @param address
     *            ビットコインアドレス
     * @return 【JSON】<br>
     *         <b>address</b> ビットコインアドレス<br>
     *         <b>unconfirmed_balance</b> 未確定残高<br>
     *         <b>confirmed_balance</b> 確定残高
     */
    @Override
    public String getAddress(String address) {
        return publicAPI(API + ADDRESS + address, HttpMethod.GET);
    }

    @Override
    protected String createSignature(String apiSecret, String url, String nonce, HttpMethod method) {
        return null;
    }

}
