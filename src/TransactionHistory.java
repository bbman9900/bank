import ENUM.Status;
import ENUM.TransactionType;

import java.time.LocalDateTime;

public class TransactionHistory {
    static long sq = 0;
    private long seqNo;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private Account account;
    private User user;
    private long amount;
    private ATM atm;
    private Status status;

    public TransactionHistory(TransactionType transactionType, Account account, long amount, ATM atm, Status status) {
        this.seqNo = sq++;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
        this.account = account;
        this.user = account.getUser();
        this.amount = amount;
        this.atm = atm;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "seqNo=" + seqNo +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                ", account=" + account.getAccountNumber() +
                ", user=" + user.getUserNumber() +
                ", amount=" + amount +
                ", atm=" + atm.getAtmName() +
                ", status=" + status +
                '}';
    }
}
