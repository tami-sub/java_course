package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ControllerHome {

    private List<Account> comparison = new ArrayList<>();
    private List<List<Object>> allTransactions = new ArrayList<>();
    private Collector collector = new Collector(comparison, allTransactions);
    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnSignIn;

    @FXML
    private TextArea screen;
    @FXML
    private TextField accNumber;
    @FXML
    private Button btnSelect;

    @FXML
    private Button btnMainMenu;


    @FXML
    private void initialize() {
        Client rob = new Client("Robert", "Barton","Johnson");
        Account robAcc = new Account(rob,0, comparison, allTransactions);
        robAcc.addBalance(100);

        Client jon = new Client("John", "Ban","Johnson");
        Account jonAcc = new Account(jon,0, comparison, allTransactions);
        jonAcc.addBalance(300);
    }


    public void SignUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
        loader.load();

        ControllerInterface controller = loader.getController();
        controller.transfer(comparison, allTransactions);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Sign Up");
        stage.show();
    }

    public void SignIn(ActionEvent event){
        btnSignIn.setVisible(false);
        btnSignUp.setVisible(false);
        btnSelect.setVisible(true);
        accNumber.setVisible(true);
        btnMainMenu.setVisible(true);

        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0; i < comparison.size(); i++){
            joiner.add(String.format("Account №: %s | %s\n", i, comparison.get(i).getFullFIO()));
        }
        String joined = joiner.toString();
        screen.setText(joined);
    }

    public void select(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
        loader.load();

        ControllerInterface controller = loader.getController();
        controller.singIn(comparison, allTransactions, Integer.parseInt(accNumber.getText()));

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Interface");
        stage.show();
    }

    public void showAllTransactions(){
        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0; i < collector.getTransactions().size(); i++){
            joiner.add(String.format("%s\n",collector.getTransactions().get(i)));
        }
        String joined = joiner.toString();
        screen.setText(joined);
    }

    public void showPivotTable(){
        StringJoiner joiner = new StringJoiner(" ");
        for(int i = 0; i < collector.pivotTable().size(); i++){
            joiner.add(String.format("%s\n",collector.pivotTable().get(i)));
        }
        String joined = joiner.toString();
        screen.setText(joined);
    }

    public void mainMenu(){
        btnMainMenu.setVisible(false);
        btnSignIn.setVisible(true);
        btnSignUp.setVisible(true);
        btnSelect.setVisible(false);
        accNumber.setVisible(false);
        screen.clear();
    }

}
