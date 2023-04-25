package org.example;

public class Account {
    private String type;
    private String number;
    private int amount;

    public Account(String type, String number, int amount) {
        this.type = type;
        this.number = number;
        this.amount = amount;
    }

    public void transfer(Account from, Account to, int creditAmount) throws Exception {
        if (isAccountAmountUnderflow()) {
            throw new Exception("Minimum amount should be over 200");
        }
        to.amount = amount + creditAmount;
        from.amount = amount - creditAmount;
    }

    public void debit(int debit) throws Exception {
        if (isAccountAmountUnderflow()) {
            throw new Exception("Minimum amount should be over 200");
        }
        amount = amount - debit;
        System.out.println("Amount is " + amount);
    }

    public boolean isAccountAmountUnderflow() {
        if (amount <= 200) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        Account account = new Account("Personal", "INFOM124", 500);
        account.debit(300);
    }
}
