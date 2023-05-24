package org.example;
import java.util.regex.Pattern;

public class Account {
    private String type;
    private AccountNumber number;
    private int amount;

    public Account(String type, AccountNumber number, int amount) {
        this.type = type;
        this.number = number;
        this.amount = amount;
    }

    public void transfer(Account from, Account to, int creditAmount) throws Exception {
        isAccountAmountUnderflow();
        to.amount = amount + creditAmount;
        from.amount = amount - creditAmount;
    }

    public void debit(int debit) throws Exception {
        isAccountAmountUnderflow();
        amount = amount - debit;
        System.out.println("Amount is " + amount);
    }

    public void isAccountAmountUnderflow() throws Exception {
        if (amount <= 200) {
            throw new Exception("Minimum amount should be over 200");
        }
    }

    public static void main(String[] args) throws Exception {
        Account account = new Account("Personal", new AccountNumber("INFOM124"), 500);
        account.debit(300);
    }
}

class AccountNumber{
    String number;
    String regex = "\\b[A-Z]{5}[0-9]{3}\\b";
    public AccountNumber(String number) throws IllegalArgumentException{
        if(Pattern.compile(regex).matcher(number).find()){
            this.number = number;
        } else {
            throw new IllegalArgumentException();
        }

    }
}
