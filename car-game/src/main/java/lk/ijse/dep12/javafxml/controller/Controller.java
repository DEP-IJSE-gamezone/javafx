package lk.ijse.dep12.javafxml.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public ImageView imgroad;
    public ImageView imgroad1;


    public AnchorPane root;
    public ImageView imgCar;


    public void initialize() {
        imgCar.setVisible(true);
        Platform.runLater(() -> {
            root.getScene().setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    if(root.getWidth()-50>(imgCar.getLayoutX()+imgCar.getFitWidth())){
                        imgCar.setLayoutX(imgCar.getLayoutX() + 10);
                        OnKeyPressedRoad(keyEvent);
                    }

                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    if(imgCar.getLayoutX()>50){
                        imgCar.setLayoutX(imgCar.getLayoutX() - 10);
                        OnKeyPressedRoad(keyEvent);
                    }

                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    OnKeyPressedRoad(keyEvent);
                }
            });
        });
    }

    public void OnKeyPressedCar(KeyEvent event) {
    }


    public void OnKeyPressedRoad(KeyEvent event) {

        if ((event.getCode() == KeyCode.UP) || (event.getCode()==KeyCode.LEFT)||(event.getCode()==KeyCode.RIGHT)){
            imgroad.setLayoutY(imgroad.getLayoutY() + 10);
            if (imgroad.getLayoutY() > root.getHeight()) {
                imgroad.setLayoutX(imgroad1.getLayoutX());
                imgroad.setLayoutY(imgroad1.getLayoutY());
            }
        }

    }


    public void OnKeyRealesedCar(KeyEvent event) {

    }


    public void OnKeyReleasedRoad(KeyEvent event) {

    }



}
