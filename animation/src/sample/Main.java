package sample;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    boolean bool = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = new FlowPane();
        root.setHgap(50);
        root.setVgap(50);
        primaryStage.setTitle("Figures");

        Button closeApp = new Button("Exit");
        closeApp.setOnAction(event -> {Platform.exit();});


        Button animationControl = new Button("Stop animation");

        Circle circle = new Circle(35);
        circle.setFill(Color.BROWN);

        Rectangle square = new Rectangle(35, 35);
        square.setFill(Color.ORANGE);

        Rectangle rectangle = new Rectangle(35, 75);
        rectangle.setFill(Color.LIME);

        FadeTransition circleFade = new FadeTransition(Duration.seconds(0.5), circle);
        circleFade.setAutoReverse(true);
        circleFade.setFromValue(0);
        circleFade.setToValue(1);
        circleFade.setCycleCount(2);

        FadeTransition squareFade = new FadeTransition(Duration.seconds(0.5), square);
        squareFade.setAutoReverse(true);
        squareFade.setFromValue(0);
        squareFade.setToValue(1);
        squareFade.setCycleCount(2);

        FadeTransition rectangleFade = new FadeTransition(Duration.seconds(0.5), rectangle);
        rectangleFade.setAutoReverse(true);
        rectangleFade.setFromValue(0);
        rectangleFade.setToValue(1);
        rectangleFade.setCycleCount(2);

        SequentialTransition stay = new SequentialTransition(circleFade, squareFade, rectangleFade);
        stay.setCycleCount(Timeline.INDEFINITE);
        stay.play();

        animationControl.setOnAction(event -> {
            if (bool) {
                animationControl.setText("Play animation");
                stay.pause();
            } else {
                animationControl.setText("Pause animation");
                stay.play();
            }
            bool = !bool;
        });

        root.getChildren().addAll(animationControl, closeApp, circle, square, rectangle);
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
