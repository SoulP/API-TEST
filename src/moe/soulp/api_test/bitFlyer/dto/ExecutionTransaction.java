package moe.soulp.api_test.bitFlyer.dto;

import java.time.ZonedDateTime;

import moe.soulp.api_test.api.BitFlyerable;
import moe.soulp.api_test.api.Type;

/**
 * <b>約定の情報</b><br>
 * date: 2017/09/07 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/09/07 ExecutionTransaction作成
 * @see Type 種類
 */
public abstract class ExecutionTransaction extends OrderExecTransaction {
    private ZonedDateTime exec_date; // 約定日時

    /**
     * <b>約定日時 出力</b>
     * 
     * @return exec_date
     */
    public ZonedDateTime getExecDate() {
        return exec_date;
    }

    /**
     * <b>約定日時 入力</b>
     * 
     * @param exec_date
     *            約定日時
     */
    public void setExecDate(ZonedDateTime exec_date) {
        this.exec_date = exec_date;
    }

    /**
     * <b>約定日時 入力</b>
     * 
     * @param exec_date
     *            約定日時
     */
    public void setExecDate(String exec_date) {
        this.exec_date = BitFlyerable.string2zonedDateTime(exec_date);
    }
}
