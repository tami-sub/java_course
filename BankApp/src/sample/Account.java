package sample;

import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

public class Account implements Serializable {
    private double balance = 0;
    private Client client = new Client();
    private List<Account> comparison = new ArrayList<>();
    private List<List<Object>> allTransactions = new ArrayList<>();

    Account(Client client, double initialDeponent, List<Account> comparison, List<List<Object>> allTransactions){
        this.client = client;
        this.balance = initialDeponent;
        this.comparison = comparison;
        this.allTransactions = allTransactions;
        comparison.add(this);
    }

    public void transaction(int accountNumber, double amount){
        if (amount <= getBalance() && amount > 0){
            comparison.get(accountNumber).transactionAddBalance(amount);
            balance = balance - amount;
            setTransactions(amount, comparison.get(accountNumber));
        }
    }

    public void addBalance(double balance) {
        if (balance>0) {
            this.balance += balance;
            setTransactions(balance, this);
        }
    }

    public double getBalance() {
        return balance;
    }

    public Map<Integer, String> getHistory(){
        List<List<Object>> history = new ArrayList<>();

        for(List<Object> list : allTransactions)
            if (this == list.get(1) || this == list.get(3))
                history.add((List<Object>) ((ArrayList) list).clone());

        for (var list: history) {
            IntStream.range(0, list.size()).filter(i -> i % 2 == 1).forEachOrdered(i -> {
                    Account mem = (Account) list.get(i);
                    list.set(i, mem.getFullFIO());
                });
        }

        Map<Integer, String> table = new HashMap<>();
        for (int i = 0; i < history.size(); i++) {
            table.put(i, String.format("Balance: %s || Sender: %s || Amount: %s || Recipient:%s || Balance: %s\n",
                    history.get(i).get(0), history.get(i).get(1), history.get(i).get(2),
                    history.get(i).get(3),history.get(i).get(4)));
        }

        return table;
    }
    public void withdrawal(double amount){
        if (amount > 0) {
            balance -= amount;
            setTransactions(amount, this);
        }
    }

    public String getClientName() {
        return client.getName();
    }
    public String getClientSurName() {
        return client.getSurname();
    }
    public String getClientMiddleName() {
        return client.getMiddle_name();
    }
    public String getFullFIO(){
        return (String.format("%s %s %s",getClientSurName(), getClientName(), getClientMiddleName()));
    }

    private void transactionAddBalance(double balance) {
        this.balance += balance;
    }
    private void setTransactions(double amount, Account recipient){
        List<Object> transactions = new ArrayList<>();
        transactions.add(this.getBalance());
        transactions.add(this);
        transactions.add(amount);
        transactions.add(recipient);
        transactions.add(recipient.getBalance());
        allTransactions.add(transactions);
    }
}