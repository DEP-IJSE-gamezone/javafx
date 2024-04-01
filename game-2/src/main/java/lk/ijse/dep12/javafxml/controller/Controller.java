package lk.ijse.dep12.javafxml.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class Controller {


    public Button btnfish;


    public ImageView imgObstacle;


    public ImageView imgroot;


    public ImageView imgroot1;

    public AnchorPane root;
    private final Timeline TIME_LINE = new Timeline();
    double OBSTACAL_START_Y,fishStart;

    public void initialize() {

        final double OBSTACLE_HEIGHT = imgObstacle.getFitHeight();
        final double OBSTACLE_WIDTH = imgObstacle.getFitWidth();
        OBSTACAL_START_Y = btnfish.getLayoutY();
        fishStart=btnfish.getLayoutX();

        //move  back ground
        KeyFrame frame = new KeyFrame(Duration.millis(100), actionEvent -> {
            if (imgroot1.getLayoutX() > imgroot1.getFitWidth()) {
                imgroot1.setLayoutX(0);
            }
            imgroot1.setLayoutX(imgroot1.getLayoutX() + 1);
        });

//move obstacle
        KeyFrame obstacleFrame = new KeyFrame(Duration.millis(50), actionEvent -> {
            if (imgObstacle.getLayoutX()> root.getWidth()) {
                imgObstacle.setLayoutX(0);
                imgObstacle.setLayoutY(0);

            }else {
                imgObstacle.setLayoutX(imgObstacle.getLayoutX() + 1);
            }
        });


        // quite game
        KeyFrame gameOverFrame = new KeyFrame(Duration.millis(50), actionEvent -> {
            double y1 = btnfish.getLayoutY() + btnfish.getHeight() / 3;
            double x1 = btnfish.getLayoutX();

            if ((y1 > imgObstacle.getLayoutY() - OBSTACLE_HEIGHT / 2) && ((x1 == imgObstacle.getLayoutX()+OBSTACLE_WIDTH/2)||(x1 == imgObstacle.getLayoutX()))) {
                TIME_LINE.stop();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game Over");
                alert.setHeaderText("Game Over");
                alert.setTitle("GAME OVER");
                alert.show();
            }
        });

        TIME_LINE.getKeyFrames().addAll(frame, obstacleFrame,gameOverFrame);
        TIME_LINE.setCycleCount(Animation.INDEFINITE);
        TIME_LINE.play();

        Platform.runLater(() -> {
            root.getScene().setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.UP) {
                    moveFish();
                }
            });
        });


    }
//move Fish
private void moveFish(){
    double y = btnfish.getLayoutY() - 5;
    double value = imgObstacle.getLayoutY() - imgObstacle.getFitHeight() - 10;
    if (value < y) {
        btnfish.setLayoutY(btnfish.getLayoutY()-2);
    }else {
        btnfish.setLayoutY(value);
    }
}
    public void OnActionFish(ActionEvent event) {
      moveFish();

    }

    public void btnFishOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP) {
            moveFish();

        }
    }

    public void btnFishOnKeyRelease(KeyEvent keyEvent) {
        btnfish.setLayoutY(OBSTACAL_START_Y);
        btnfish.setLayoutX(fishStart);
    }
}
