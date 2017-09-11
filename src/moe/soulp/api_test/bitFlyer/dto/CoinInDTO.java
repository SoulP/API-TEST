package moe.soulp.api_test.bitFlyer.dto;

/**
 * <b>ビットコイン・イーサ預入の情報</b><br>
 * date: 2017/09/08 last_date: 2017/09/11
 * 
 * @author ソウルP
 * @version 1.0 2017/09/08 CoinInDTO作成
 * @version 1.1 2017/09/11 一部をMoneyTransactionDTOに移動
 */
public class CoinInDTO extends MoneyTransactionDTO {
    private String address; // アドレス
    private String tx_hash; // トランザクションのハッシュ

    /**
     * <b>アドレス 出力</b>
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * <b>アドレス 入力</b>
     * 
     * @param address
     *            アドレス
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * <b>トランザクションのハッシュ 出力</b>
     * 
     * @return tx_hash
     */
    public String getTxHash() {
        return tx_hash;
    }

    /**
     * <b>トランザクションのハッシュ 入力</b>
     * 
     * @param tx_hash
     *            トランザクションのハッシュ
     */
    public void setTxHash(String tx_hash) {
        this.tx_hash = tx_hash;
    }
}
