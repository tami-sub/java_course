package sample;

import javafx.event.ActionEvent;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sound.midi.MidiChannel;

public class ControllerInterface {

    List<Account> comparison = new ArrayList<>();
    List<List<Object>> allTransactions = new ArrayList<>();
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

    @FXML
    private void initialize(){
    }
    private Account clientAcc;
    public void transfer (List<Account> comparison, List<List<Object>> allTransactions){
        this.comparison = comparison;
        this.allTransactions = allTransactions;
        way = true;
    }

    public void singIn (List<Account> comparison, List<List<Object>> allTransactions, Integer accNumber){
        this.comparison = comparison;
        this.allTransactions = allTransactions;
        clientAcc = comparison.get(accNumber);
        way = false;
        transform();
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

    public void addWithdrawal(){
        clientAcc.withdrawal(Double.parseDouble(thirdField.getText()));
        btnShowBalance.setText(String.valueOf(clientAcc.getBalance()));
    }

    public void addBalance(){
        clientAcc.addBalance(Double.parseDouble(firstField.getText()));
        btnShowBalance.setText(String.valueOf(clientAcc.getBalance()));
    }

    public void addTransfer(){
        clientAcc.transaction(Integer.parseInt(accountNumber.getText()), Double.parseDouble(amountTransfer.getText()));
        btnShowBalance.setText(String.valueOf(clientAcc.getBalance()));
    }

    public void showAccounts(){
        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0; i < comparison.size(); i++){
            joiner.add(String.format("Acc №: %s | %s\n", i, comparison.get(i).getFullFIO()));
        }
        String joined = joiner.toString();
        allAccounts.setText(joined);
    }

    public void showHistory(){
        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0; i < clientAcc.getHistory().size(); i++){
            joiner.add(String.format("%s\n",clientAcc.getHistory().get(i)));
        }
        String joined = joiner.toString();
        allAccounts.setText(joined);
    }
    public void createAccount(ActionEvent event) throws IOException {
        Client client = new Client(name.getText(), firstField.getText(), thirdField.getText());
        clientAcc = new Account(client, 0, comparison, allTransactions);
        transform();
    }
}

