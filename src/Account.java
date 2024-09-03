import ENUM.BankCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Account {
    private BankCode bankCode;
    private String accountNumber;
    private User user;
    private LocalDateTime openDate;
    private LocalDateTime deleteDate;
    private boolean dormancy;
    private String password;
    private long balance;

    public Account(BankCode bankCode, String accountNumber, User user, String password) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.user = user;
        this.openDate = LocalDateTime.now();
        this.deleteDate = null;
        this.dormancy = false;
        this.password = password;
        this.balance = 0;
    }

    public BankCode getBankCode() {
        return bankCode;
    }

    public void setBankCode(BankCode bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public boolean isDormancy() {
        return dormancy;
    }

    public void setDormancy(boolean dormancy) {
        this.dormancy = dormancy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
