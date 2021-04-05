package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Main extends Application {
//    public Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent first = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setScene(new Scene(first, 500, 400));
        primaryStage.setTitle("Main");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

//        try {
//          Saver.INSTANCE.saveAccount(joji, jojiAcc,"ko");  Saver из пункта 3. Проект значительно изменился в пункте 4.
//        } catch (IOException e) {
//
//        }
    }

}
