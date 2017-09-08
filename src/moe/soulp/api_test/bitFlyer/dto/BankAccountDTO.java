package moe.soulp.api_test.bitFlyer.dto;

/**
 * <b>銀行口座の情報</b><br>
 * date: 2017/09/08 last_date: 2017/09/08
 * 
 * @author ソウルP
 * @version 1.0 2017/09/08 BankAccountDTO作成
 */
public class BankAccountDTO extends Transaction {
    private boolean is_verified;    // 承認有無
    private String  bank_name;      // 銀行名
    private String  branch_name;    // 支店名
    private String  account_type;   // 口座種類
    private String  account_number; // 口座番号
    private String  account_name;   // 口座名義

    /**
     * <b>承認有無 出力</b>
     * 
     * @return <b>true</b> 承認済<br>
     *         <b>false</b> 未承認
     */
    public boolean getIsVerified() {
        return is_verified;
    }

    /**
     * <b>承認有無 入力</b>
     * 
     * @param is_verified
     *            true 承認済, false 未承認
     */
    public void setIsVerified(boolean is_verified) {
        this.is_verified = is_verified;
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
     * <b>口座種類 出力</b>
     * 
     * @return account_type
     */
    public String getAccountType() {
        return account_type;
    }

    /**
     * <b>口座種類 入力</b>
     * 
     * @param account_type
     *            口座種類
     */
    public void setAccountType(String account_type) {
        this.account_type = account_type;
    }

    /**
     * <b>口座番号 出力</b>
     * 
     * @return account_number
     */
    public String getAccountNumber() {
        return account_number;
    }

    /**
     * <b>口座番号 入力</b>
     * 
     * @param account_number
     *            口座番号
     */
    public void setAccountNumber(String account_number) {
        this.account_number = account_number;
    }

    /**
     * <b>口座名義 出力</b>
     * 
     * @return account_name
     */
    public String getAccountName() {
        return account_name;
    }

    /**
     * <b>口座名義 入力</b>
     * 
     * @param account_name
     *            口座名義
     */
    public void setAccountName(String account_name) {
        this.account_name = account_name;
    }
}
