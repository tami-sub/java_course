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
import java.sql.*;
import java.util.StringJoiner;

public class ControllerHome {

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

    private Connection conn;

    @FXML
    private void initialize() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BankApp", "postgres", "root");
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public void SignUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
        loader.load();
        ControllerInterface controller = loader.getController();
        controller.firstTransfer(conn);
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
    }

    public void select(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
        loader.load();

        ControllerInterface controller = loader.getController();
        controller.secondTransfer(conn,Integer.parseInt(accNumber.getText()));

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Interface");
        stage.show();
    }

    public void showAllTransactions() throws SQLException {

        StringJoiner joiner = new StringJoiner(" ");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"AllTransactions\"");
            while(rs.next()){
            joiner.add(String.format("sender Id: %s amount: %s recipient Id: %s \n",
                    rs.getInt("user_sender"), rs.getDouble("amount"), rs.getInt("user_recipient")));
            }
        stmt.close();
        String joined = joiner.toString();
        screen.setText(joined);
    }

    public void showPivotTable() throws SQLException {
        StringJoiner joiner = new StringJoiner(" ");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Accounts\" ORDER BY id ASC");
        while(rs.next()){
            joiner.add(String.format("Account Id: %s Balance: %s FIO: %s %s %s \n",
                    rs.getInt("id"), rs.getDouble("balance"),
                    rs.getString("last_name"), rs.getString("first_name"), rs.getString("middle_name")));
        }
        stmt.close();
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