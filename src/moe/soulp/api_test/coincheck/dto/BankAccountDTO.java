package moe.soulp.api_test.coincheck.dto;

import moe.soulp.api_test.api.Type;

/**
 * <b>銀行口座</b><br>
 * date: 2017/08/28 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/08/28 BankAccountDTO作成
 * @version 1.1 2017/09/08 口座種類のデータ・タイプ変更
 */
public class BankAccountDTO {
    private long   id;                // ID
    private String bank_name;         // 銀行名
    private String branch_name;       // 支店名
    private String bank_account_type; // 口座種類
    private String number;            // 口座番号
    private String name;              // 口座名義

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

    /**
     * <b>銀行名 出力</b>
     * 
     * @return bank_name
     */
    public String getBankName() {
        return bank_name;
    }

    /**
     * <b>銀行名 入力</b>
     * 
     * @param bank_name
     *            銀行名
     */
    public void setBankName(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * <b>支店名 出力</b>
     * 
     * @return branch_name
     */
    public String getBranchName() {
        return branch_name;
    }

    /**
     * <b>支店名 入力</b>
     * 
     * @param branch_name
     *            支店名
     */
    public void setBranchName(String branch_name) {
        this.branch_name = branch_name;
    }

    /**
     * <b>銀行口座の種類 出力</b>
     * 
     * @return bank_account_type
     */
    public String getBankAccountType() {
        return bank_account_type;
    }

    /**
     * <b>銀行口座の種類 入力</b><br>
     * 
     * @param bank_account_type
     *            銀行口座の種類<br>
     *            Type.futsu -> "futsu"<br>
     *            Type.toza -> "toza"
     * @see Type 種類
     */
    public void setBankAccountType(Type bank_account_type) {
        this.bank_account_type = bank_account_type.toString();
    }

    /**
     * <b>銀行口座の種類 入力</b><br>
     * 
     * @param bank_account_type
     *            銀行口座の種類<br>
     *            "futsu" -> "普通"<br>
     *            "toza" -> "当座"
     */
    public void setBankAccountType(String bank_account_type) {
        if (bank_account_type.startsWith(Type.futsu.toString())) bank_account_type = "普通";
        if (bank_account_type.startsWith(Type.toza.toString())) bank_account_type = "当座";
        this.bank_account_type = bank_account_type;
    }

    /**
     * <b>口座番号 出力</b>
     * 
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * <b>口座番号 入力</b>
     * 
     * @param number
     *            口座番号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * <b>口座名義 出力</b>
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * <b>口座名義 入力</b>
     * 
     * @param name
     *            口座名義
     */
    public void setName(String name) {
        this.name = name;
    }
}
