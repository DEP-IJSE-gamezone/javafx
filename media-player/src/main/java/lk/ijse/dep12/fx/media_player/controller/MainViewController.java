package lk.ijse.dep12.fx.media_player.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainViewController {


    public Button btnPause;

    public Button btnPlay;

    public Button btnPrevius;

    public HBox hBoxContainer;

    public Rectangle rctTime;

    public Rectangle rctWindow;

    public AnchorPane root;

    public ImageView rootImge;

    public Slider slrbar;
    public Button btnNext;
    public Button btnClose;
    public Label lblTime;
    private final Timeline TIME_LINE = new Timeline();


    public void initialize() {
        //set time
        lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Timeline timeline = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();

        // set Slider action

        KeyFrame sldrKeframe = new KeyFrame(Duration.seconds(1), actionEvent -> {
            slrbar.setValue(slrbar.getValue() + 1);
        });
        TIME_LINE.getKeyFrames().add(sldrKeframe);
        TIME_LINE.setCycleCount(TIME_LINE.INDEFINITE);

    }

    public void btnNextOnAction(ActionEvent event) {
        if (slrbar.getValue()+5 < slrbar.getMax()) slrbar.setValue(slrbar.getValue() + 5);
        else slrbar.setValue(slrbar.getMax());
        TIME_LINE.play();

    }

    public void btnPauseOnAction(ActionEvent event) {
        TIME_LINE.pause();
    }

    public void btnPlayOnAction(ActionEvent event) {
        TIME_LINE.play();
    }

    public void btnPreviuosOnAction(ActionEvent event) {
        if (slrbar.getValue()-5>slrbar.getMin())slrbar.setValue(slrbar.getValue()-5);
        else slrbar.setValue(slrbar.getMin());
        TIME_LINE.play();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        ((Stage) root.getScene().getWindow()).close();
    }
}
