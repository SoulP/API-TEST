package moe.soulp.api_test.api;

/**
 * <b>状態</b><br>
 * date: 2017/08/24 last_date: 2017/09/08
 *
 * @author ソウルP
 * @version 1.0 2017/08/24 Status作成
 * @version 1.1 2017/08/29 出金状態追加 ( pending 未処理, processing 手続き中, finished 完了,
 *          canceled キャンセル済み)
 * @version 1.2 2017/09/08 注文の状態追加 ( ACTIVE : オープン, COMPLETED : 完了, CANCELED :
 *          キャンセル, EXPIRED : 有効期限切れ, REJECTED : 失敗 )
 */
public enum Status {
    open, closed, complete, cancel, confirmed, received, finished, pending, processing, canceled, ACTIVE, COMPLETED, CANCELED, EXPIRED, REJECTED
}
