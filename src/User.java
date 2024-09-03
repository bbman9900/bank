import ENUM.BankCode;

import java.util.ArrayList;
import java.util.List;

public class User {
    private BankCode bankCode;
    private String userNumber;
    private String userName;
    private List<Account> accountList;

    public User(BankCode bankCode, String userNumber, String userName) {
        this.bankCode = bankCode;
        this.userNumber = userNumber;
        this.userName = userName;
        this.accountList = new ArrayList<Account>();
    }

    public BankCode getBankCode() {
        return bankCode;
    }

    public void setBankCode(BankCode bankCode) {
        this.bankCode = bankCode;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
