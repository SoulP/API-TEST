package moe.soulp.api_test.bitFlyer.dto;

/**
 * <b>トランザクション</b><br>
 * date: 2017/09/08 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 Transaction作成
 */
public abstract class Transaction {
    private long id; // ID

    /**
     * <b>ID 出力</b>
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * <b>ID 入力</b>
     * 
     * @param id
     *            ID
     */
    public void setId(long id) {
        this.id = id;
    }
}
