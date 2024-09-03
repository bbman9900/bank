import ENUM.BankCode;
import ENUM.Status;
import ENUM.TransactionType;

import java.util.*;

public class Main {
    public static Status s = Status.NORMAL;

    // 은행별 ATM
    public static ATM atm1 = new ATM(BankCode.SHINHAN, 1, "ori station");
    public static ATM atm2 = new ATM(BankCode.KOOKMIN, 2, "ori station");
    public static ATM atm3 = new ATM(BankCode.KAKAO, 3, "ori station");
    public static ATM atm4 = new ATM(BankCode.WOORI, 4, "ori station");
    public static ArrayList<ATM> atm = new ArrayList<>();

    // 고객 리스트
    public static Map<String, User> userList1 = new HashMap<>();
    public static Map<String, User> userList2 = new HashMap<>();
    public static Map<String, User> userList3 = new HashMap<>();
    public static Map<String, User> userList4 = new HashMap<>();
    public static ArrayList<Map<String, User>> ul = new ArrayList<>();

    // 계좌 리스트
    public static Map<String, Account> accountList1 = new HashMap<>();
    public static Map<String, Account> accountList2 = new HashMap<>();
    public static Map<String, Account> accountList3 = new HashMap<>();
    public static Map<String, Account> accountList4 = new HashMap<>();
    public static ArrayList<Map<String, Account>> al = new ArrayList<>();

    // 거래내역 리스트
    public static Map<Long, TransactionHistory> th1 = new HashMap<>();
    public static Map<Long, TransactionHistory> th2 = new HashMap<>();
    public static Map<Long, TransactionHistory> th3 = new HashMap<>();
    public static Map<Long, TransactionHistory> th4 = new HashMap<>();
    public static ArrayList<Map<Long, TransactionHistory>> th = new ArrayList<>();



    public static void main(String[] args) {
        atm.add(atm1);
        atm.add(atm2);
        atm.add(atm3);
        atm.add(atm4);
        ul.add(userList1);
        ul.add(userList2);
        ul.add(userList3);
        ul.add(userList4);
        al.add(accountList1);
        al.add(accountList2);
        al.add(accountList3);
        al.add(accountList4);
        th.add(th1);
        th.add(th2);
        th.add(th3);
        th.add(th4);

        while (true) {
            System.out.println("은행 번호를 입력 후 Enter 키를 입력해주세요.\n1 : 신한은행\n2 : 국민은행\n3 : 우리은행\n4 : 카카오뱅크\n5 : 거래 종료");
            Scanner sc = new Scanner(System.in);
            int i, bank;
            BankCode bc;

            try {
                i = sc.nextInt();
                if (i == 5) {
                    System.out.println("감사합니다.");
                    break;
                }
                bc = switch (i) {
                    case 1 -> BankCode.SHINHAN;
                    case 2 -> BankCode.KOOKMIN;
                    case 3 -> BankCode.WOORI;
                    case 4 -> BankCode.KAKAO;
                    default -> throw new IllegalStateException("Unexpected value: " + i);
                };
                bank = i;
            } catch (Exception e) {
                System.out.println("1, 2, 3, 4 중 하나를 입력해주세요.");
                continue;
            }
            System.out.println("원하시는 거래의 번호를 입력 후 Enter 키를 입력해주세요\n1 : 계좌 개설\n2 : 입금\n3 : 출금\n4 : 잔액 조회\n5 : 계좌 삭제\n6 : 거래 종료");
            sc = new Scanner(System.in);
            try {
                i = sc.nextInt();
                if (i == 6) {
                    System.out.println("감사합니다.");
                    break;
                }
                if (i == 1) {
                    System.out.println("계좌 개설입니다.");
                    System.out.println("주민번호 앞자리 6자리를 입력 후 Enter 키를 입력해주세요.");
                    sc = new Scanner(System.in);
                    String userNum = sc.nextLine();
                    if (userNum.length() != 6) {
                        System.out.println("6자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                        continue;
                    } else {
                        System.out.println("고객 이름을 입력 후 Enter 키를 입력해주세요.");
                        sc = new Scanner(System.in);
                        String userName = sc.nextLine();
                        System.out.println("개설하고자 하는 계좌번호 5자리를 입력 후 Enter 키를 입력해주세요.");
                        sc = new Scanner(System.in);
                        String accountNum = sc.nextLine();
                        while (al.get(bank - 1).containsKey(accountNum)) {
                            System.out.println("이미 있는 계좌번호입니다. 다른 계좌번호 5자리를 입력 후 Enter 키를 입력해주세요.");
                            sc = new Scanner(System.in);
                            accountNum = sc.nextLine();
                        }
                        if (accountNum.length() != 5) {
                            System.out.println("5자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                            continue;
                        } else {
                            System.out.println("계좌의 비밀번호 4자리를 입력 후 Enter 키를 입력해주세요.");
                            sc = new Scanner(System.in);
                            String pw = sc.nextLine();
                            if (pw.length() != 4) {
                                System.out.println("4자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                                continue;
                            }
                            User user1;
                            user1 = ul.get(bank - 1).containsKey(userNum) ? ul.get(bank - 1).get(userNum) : new User(bc, userNum, userName);;
                            // 계좌 개설
                            Account account1 = new Account(bc, accountNum, user1, pw);
                            // 고객의 계좌 목록에 추가
                            user1.getAccountList().add(account1);
                            // 은행 계좌 목록에 추가
                            accountList1.put(account1.getAccountNumber(), account1);
                            // 은행 거래 내역 목록에 추가
                            th.get(bank - 1).put(TransactionHistory.sq, new TransactionHistory(TransactionType.CREATE, account1, 0, atm.get(bank - 1), s));
                            System.out.println("계좌가 개설되었습니다. 계좌번호 : " + account1.getAccountNumber() + ", 고객 번호 : " + account1.getUser().getUserNumber() + ", 비밀번호 : " + account1.getPassword());
                        }
                    }
                } else if (i == 2) {
                    System.out.println("입금입니다.");
                    System.out.println("계좌번호 5자리를 입력 후 Enter 키를 입력해주세요.");
                    sc = new Scanner(System.in);
                    String accountNum = sc.nextLine();
                    if (!(al.get(bank - 1).containsKey(accountNum))) {
                        System.out.println("입력하신 번호의 계좌가 없습니다. 초기 화면으로 돌아갑니다.");
                    } else {
                        System.out.println("고객번호 6자리를 입력 후 Enter 키를 입력해주세요.");
                        sc = new Scanner(System.in);
                        String userNum = sc.nextLine();
                        if (userNum.length() != 6) {
                            System.out.println("6자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                            continue;
                        } else {
                            System.out.println("입금하실 금액을 입력 후 Enter 키를 입력해주세요.");
                            sc = new Scanner(System.in);
                            long am = sc.nextLong();
                            atm.get(bank - 1).deposit(al.get(bank - 1).get(accountNum), userNum, am);
                            th.get(bank - 1).put(TransactionHistory.sq, new TransactionHistory(TransactionType.DEPOSIT, al.get(bank - 1).get(accountNum), am, atm.get(bank - 1), s));
                        }
                    }
                } else if (i == 3) {
                    System.out.println("출금입니다.");
                    System.out.println("계좌번호 5자리를 입력 후 Enter 키를 입력해주세요.");
                    sc = new Scanner(System.in);
                    String accountNum = sc.nextLine();
                    if (!(al.get(bank - 1).containsKey(accountNum))) {
                        System.out.println("입력하신 번호의 계좌가 없습니다. 초기 화면으로 돌아갑니다.");
                    } else {
                        System.out.println("고객번호 6자리를 입력 후 Enter 키를 입력해주세요.");
                        sc = new Scanner(System.in);
                        String userNum = sc.nextLine();
                        if (userNum.length() != 6) {
                            System.out.println("6자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                            continue;
                        } else {
                            System.out.println("계좌의 비밀번호 4자리를 입력 후 Enter 키를 입력해주세요.");
                            sc = new Scanner(System.in);
                            String pw = sc.nextLine();
                            if (!(al.get(bank - 1).get(accountNum).getPassword().equals(pw))) System.out.println("비밀번호가 틀렸습니다. 초기 화면으로 돌아갑니다.");
                            else {
                                System.out.println("출금하실 금액을 입력 후 Enter 키를 입력해주세요.");
                                sc = new Scanner(System.in);
                                long am = sc.nextLong();
                                boolean withdraw1 = atm.get(bank - 1).withdraw(al.get(bank - 1).get(accountNum), userNum, pw, am);
                                if (!withdraw1) s = Status.ERROR;
                                th.get(bank - 1).put(TransactionHistory.sq, new TransactionHistory(TransactionType.DEPOSIT, al.get(bank - 1).get(accountNum), am, atm.get(bank - 1), s));
                                s = Status.NORMAL;
                            }
                        }
                    }
                } else if (i == 4) {
                    System.out.println("잔액 조회입니다.");
                    System.out.println("계좌번호 5자리를 입력 후 Enter 키를 입력해주세요.");
                    sc = new Scanner(System.in);
                    String accountNum = sc.nextLine();
                    if (!(al.get(bank - 1).containsKey(accountNum))) {
                        System.out.println("입력하신 번호의 계좌가 없습니다. 초기 화면으로 돌아갑니다.");
                    } else {
                        System.out.println("고객번호 6자리를 입력 후 Enter 키를 입력해주세요.");
                        sc = new Scanner(System.in);
                        String userNum = sc.nextLine();
                        if (userNum.length() != 6) {
                            System.out.println("6자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                            continue;
                        } else {
                            System.out.println("계좌의 비밀번호 4자리를 입력 후 Enter 키를 입력해주세요.");
                            sc = new Scanner(System.in);
                            String pw = sc.nextLine();
                            if (!(al.get(bank - 1).get(accountNum).getPassword().equals(pw))) System.out.println("비밀번호가 틀렸습니다. 초기 화면으로 돌아갑니다.");
                            else {
                                atm.get(bank - 1).check(al.get(bank - 1).get(accountNum), userNum, pw);
                                th.get(bank - 1).put(TransactionHistory.sq, new TransactionHistory(TransactionType.CHECK, al.get(bank - 1).get(accountNum), 0, atm.get(bank - 1), s));
                            }
                        }
                    }
                } else if (i == 5) {
                    System.out.println("계좌 삭제입니다.");
                    System.out.println("계좌번호 5자리를 입력 후 Enter 키를 입력해주세요.");
                    sc = new Scanner(System.in);
                    String accountNum = sc.nextLine();
                    if (!(al.get(bank - 1).containsKey(accountNum))) {
                        System.out.println("입력하신 번호의 계좌가 없습니다. 초기 화면으로 돌아갑니다.");
                    } else {
                        System.out.println("고객번호 6자리를 입력 후 Enter 키를 입력해주세요.");
                        sc = new Scanner(System.in);
                        String userNum = sc.nextLine();
                        if (userNum.length() != 6) {
                            System.out.println("6자리가 아닙니다. 초기 화면으로 돌아갑니다.");
                            continue;
                        } else {
                            System.out.println("계좌의 비밀번호 4자리를 입력 후 Enter 키를 입력해주세요.");
                            sc = new Scanner(System.in);
                            String pw = sc.nextLine();
                            if (!(al.get(bank - 1).get(accountNum).getPassword().equals(pw))) System.out.println("비밀번호가 틀렸습니다. 초기 화면으로 돌아갑니다.");
                            else {
                                atm.get(bank - 1).delete(al.get(bank - 1).get(accountNum), userNum, pw);
                                th.get(bank - 1).put(TransactionHistory.sq, new TransactionHistory(TransactionType.DELETE, al.get(bank - 1).get(accountNum), 0, atm.get(bank - 1), s));
                            }
                        }
                    }
                } else {
                    System.out.println("1, 2, 3, 4, 5, 6 중 하나를 입력해주세요.");
                }
            } catch (Exception e) {
                System.out.println("1, 2, 3, 4, 5 중 하나를 입력해주세요.");
                continue;
            }

        }


        // 거래 내역 조회
        System.out.println("-------------거래 내역----------------");
        for (Map.Entry<Long, TransactionHistory> lthe : th1.entrySet()) {
            System.out.println(lthe.getKey() + " : " + lthe.getValue());
        }
    }
}