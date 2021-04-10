package sample;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.*;
import java.util.StringJoiner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerInterface {

    public ControllerInterface(){

    }
    @FXML
    private TextField firstField;

    @FXML
    private TextField name;

    @FXML
    private TextField thirdField;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnAddBalance;

    @FXML
    private Button btnTransfer;

    @FXML
    private Button btnWithdrawal;

    @FXML
    private Button btnAccounts;

    @FXML
    private TextField accountNumber;

    @FXML
    private TextField amountTransfer;

    @FXML
    private Button btnShowBalance;

    @FXML
    private Button btnShowHistory;

    @FXML
    private TextArea allAccounts;

    private Boolean way;
    private int senderId;
    @FXML
    private void initialize() {
    }

    private Connection conn;

    public void firstTransfer (Connection conn){
        this.conn = conn;
        way = true;
    }

    public void secondTransfer (Connection conn, Integer accNumber) throws SQLException {
        this.conn = conn;
        this.senderId = accNumber;
        way = false;
        transform();
        btnShowBalance.setText(String.valueOf(getBalance(senderId)));
    }

    private void transform() {
        btnCreate.setVisible(false);
        name.setVisible(false);

        accountNumber.setVisible(true);
        amountTransfer.setVisible(true);
        btnShowBalance.setVisible(true);
        btnShowHistory.setVisible(true);

        btnAddBalance.setVisible(true);
        btnTransfer.setVisible(true);
        btnWithdrawal.setVisible(true);
        btnAccounts.setVisible(true);
        firstField.clear();
        firstField.setPromptText("Amount");
        thirdField.clear();
        thirdField.setPromptText("Amount");
    }

    public void addWithdrawal() throws SQLException {
        double amount = Double.parseDouble(thirdField.getText());
        if (amount < getBalance(senderId)) {
            Statement st = conn.createStatement();
            st.executeUpdate(String.format("UPDATE public.\"Accounts\" SET balance = balance - %s WHERE id=%s", amount, senderId));
            st.close();

            transfer(-amount, senderId);
        }
        btnShowBalance.setText(String.valueOf(getBalance(senderId)));
    }

    public void addBalance() throws SQLException {
        double amount = Double.parseDouble(firstField.getText());
        if (amount > 0) {
            Statement st = conn.createStatement();
            st.executeUpdate(String.format("UPDATE public.\"Accounts\" SET balance = balance + %s WHERE id=%s", amount, senderId));
            st.close();
            transfer(amount, senderId);
        }
        btnShowBalance.setText(String.valueOf(getBalance(senderId)));
    }

    public double getBalance(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT balance FROM public.\"Accounts\" WHERE id = %s",id));
        rs.next();
        double money = rs.getDouble(1);
        stmt.close();
        return money;
    }

    public void transfer(double amount, int recipientId) throws SQLException {
        PreparedStatement st1 = conn.prepareStatement("" +
                "INSERT INTO public.\"AllTransactions\" (user_sender, amount, user_recipient) VALUES (?, ?, ?)");
        st1.setDouble(1, senderId);
        st1.setDouble(2, amount);
        st1.setInt(3, recipientId);
        st1.executeUpdate();
    }

    public void addTransfer() throws SQLException {
        double amount = Integer.parseInt(amountTransfer.getText());
        int recipientId = Integer.parseInt(accountNumber.getText());
        transfer(amount, recipientId);

        if (amount <= getBalance(senderId) && amount > 0 && senderId != recipientId) {
            Statement st = conn.createStatement();
            st.executeUpdate(String.format("UPDATE public.\"Accounts\" SET balance = balance + %s WHERE id=%s",amount, recipientId));
            st.executeUpdate(String.format("UPDATE public.\"Accounts\" SET balance = balance - %s WHERE id=%s",amount, senderId));
            st.close();
        }

        btnShowBalance.setText(String.valueOf(getBalance(senderId)));
    }

    public void showAccounts() throws SQLException {
        StringJoiner joiner = new StringJoiner(" ");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Accounts\" ORDER BY id ASC");
        while(rs.next()){
            joiner.add(String.format("Account Id: %s FIO: %s %s %s \n", rs.getInt("id"),
                    rs.getString("last_name"), rs.getString("first_name"), rs.getString("middle_name")));
        }
        stmt.close();
        String joined = joiner.toString();
        allAccounts.setText(joined);
    }

    public void showHistory() throws SQLException {

        StringJoiner joiner = new StringJoiner(" ");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
                "SELECT * FROM public.\"AllTransactions\" WHERE user_sender = %s OR user_recipient = %s" , senderId, senderId));
        while(rs.next()){
            joiner.add(String.format("sender Id: %s amount: %s recipient Id: %s \n",
                    rs.getInt("user_sender"), rs.getDouble("amount"), rs.getInt("user_recipient")));
        }
        stmt.close();
        String joined = joiner.toString();
        allAccounts.setText(joined);
    }

    public void createAccount(ActionEvent event) throws IOException, SQLException {
        Client client = new Client(name.getText(), firstField.getText(), thirdField.getText());
        Account clientAcc = new Account(client, 0, conn);
        senderId = clientAcc.getSenderId();
        transform();
    }
}