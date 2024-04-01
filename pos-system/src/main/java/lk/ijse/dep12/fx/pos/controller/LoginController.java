package lk.ijse.dep12.fx.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class LoginController {
    public Button btnLogin;

    public PasswordField pwd;

    public AnchorPane root;

    public TextField txtUserName;

    public void initialize() {

    }

    public void btnLoginOnAction(ActionEvent event) throws IOException {
        txtUserName.getStyleClass().remove("error");
        pwd.getStyleClass().remove("error");

        if (!(txtUserName.getText().strip().equals("admin") && pwd.getText().equals("admin"))) {

            if (!pwd.getText().equals("admin")) {
                pwd.requestFocus();
                pwd.getStyleClass().add("error");
                return;
            }

            if (!txtUserName.getText().strip().equals("admin")) {
                txtUserName.requestFocus();
                txtUserName.getStyleClass().add("error");
                return;
            }

            return;
        }
//Dispose the login window
        ((Stage) btnLogin.getScene().getWindow()).close();


        URL resource = getClass().getResource("/view/DashBoard.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane container = fxmlLoader.load();
//        Scene loginScene = new Scene(container);
//        Stage loginStage = new Stage();
//        loginStage.setScene(loginScene);
//        loginStage.setTitle("Dash Board");
//        loginStage.initStyle(StageStyle.UNDECORATED);
        dashBoardController controller = fxmlLoader.getController();
        controller.loadStage(container);

//        loginStage.show();
//        loginStage.centerOnScreen();


    }

    public void onKeyPressedUserName(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            pwd.requestFocus();
    }

    public void onKeyPressedpwd(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnLogin.fire();
    }

}
