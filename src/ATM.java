import ENUM.BankCode;
import ENUM.Status;

public class ATM {
    private BankCode bankCode;
    private int atmNumber;
    private String atmName;

    public ATM(BankCode bankCode, int atmNumber, String atmName) {
        this.bankCode = bankCode;
        this.atmNumber = atmNumber;
        this.atmName = atmName;
    }

    void deposit(Account account, String userNumber, long amount) {
        if (account.getUser().getUserNumber().equals(userNumber)) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("입금이 완료되었습니다.\n입금 후 잔액은 " + account.getBalance() + "원 입니다.");
        } else System.out.println("계좌의 고객 번호가 틀렸습니다. 다시 확인해주세요.");
    }

    boolean withdraw(Account account, String userNumber, String password, long amount) {
        if (account.getUser().getUserNumber().equals(userNumber) && account.getPassword().equals(password)) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                System.out.println("출금이 완료되었습니다.\n출금 후 잔액은 " + account.getBalance() + "원 입니다.");
                return true;
            }
            else System.out.println("잔액이 부족합니다. 현재 잔액은 " + account.getBalance() + "원 입니다.");
        } else System.out.println("고객번호 또는 비밀번호가 틀렸습니다. 다시 확인해주세요.");
        return false;
    }

    void check(Account account, String userNumber, String password) {
        if (account.getUser().getUserNumber().equals(userNumber) && account.getPassword().equals(password)) {
            System.out.println("현재 잔액은 " + account.getBalance() + "원 입니다.");
        } else System.out.println("고객번호 또는 비밀번호가 틀렸습니다. 다시 확인해주세요.");
    }

    void delete(Account account, String userNumber, String password) {
        if (account.getUser().getUserNumber().equals(userNumber) && account.getPassword().equals(password)) {
            if (account.getBalance() > 0) System.out.println("남은 잔액 " + account.getBalance() + "원을 출금하였습니다.");
            account.setDormancy(true);
            System.out.println("계좌번호" + account.getAccountNumber() + "의 계좌가 삭제되었습니다.");
        } else System.out.println("고객번호 또는 비밀번호가 틀렸습니다. 다시 확인해주세요.");
    }

    public BankCode getBankCode() {
        return bankCode;
    }

    public void setBankCode(BankCode bankCode) {
        this.bankCode = bankCode;
    }

    public int getAtmNumber() {
        return atmNumber;
    }

    public void setAtmNumber(int atmNumber) {
        this.atmNumber = atmNumber;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }
}
