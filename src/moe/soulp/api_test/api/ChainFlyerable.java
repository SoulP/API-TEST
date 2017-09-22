package moe.soulp.api_test.api;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <b>chainFlyer</b><br>
 * date: 2017/09/22 last_date: 2017/09/22
 * 
 * @author ソウルP
 * @version 1.0 2017/09/22 ChainFlyerable作成
 */
public interface ChainFlyerable {
    DateTimeFormatter FORMAT       = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    // Base API
    String            API          = "https://api.bitflyer.jp";

    // Public API
    String            BLOCK        = "/v1/block/";
    String            BLOCK_LATEST = "/v1/block/latest";
    String            BLOCK_HEIGHT = "/v1/block/height/";
    String            TX           = "/v1/tx/";
    String            ADDRESS      = "/v1/address/";

    // Public API
    public String getBlock(String blockHash); // ブロック

    public String getBlockLatest(); // ブロック

    public String getBlockHeight(long height); // ブロック

    public String getTx(String txHash); // トランザクション

    public String getAddress(String address); // アドレス

    /**
     * <b>日時 データ・タイプ変換</b><br>
     * String(UTC) -> ZonedDateTime(システムのタイムゾーン)
     *
     * @param date
     *            日時 "yyyy-MM-dd'T'HH:mm:ss.SSSX"<br>
     *            例え： "2017-08-24T22:33:44.000Z"
     * @return ZonedDateTime
     */
    public static ZonedDateTime string2zonedDateTime(String date) {
        if (date == null || date.equals("")) return null;
        else return ZonedDateTime.parse(date, FORMAT).withZoneSameInstant(ZoneId.systemDefault());
    }
}
