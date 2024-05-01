package lk.ijse.dep12.fx.media_player;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        AnchorPane container = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
        Scene scene = new Scene(container);
        primaryStage.setScene(scene);
        container.setBackground(Background.EMPTY);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Media Player");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
