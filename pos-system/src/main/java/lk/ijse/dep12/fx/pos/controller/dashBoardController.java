package lk.ijse.dep12.fx.pos.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class dashBoardController {
    public Button btnDashBoard;

    public Button btnLogOut;

    public Button btnManageCustomer;

    public Button btnManageStock;

    public Button btnPlaceOrder;

    public Button btnViewResponse;

    public AnchorPane root;

    public AnchorPane rootCantainer;

    public VBox vBox;

    public void initialize() {
//btnDashBoard.fire();
    }

    private void navigation(AnchorPane root1) {
        rootCantainer.getChildren().clear();
        rootCantainer.getChildren().add(root1);

        AnchorPane.setBottomAnchor(root1, 0.0);
        AnchorPane.setTopAnchor(root1, 0.0);
        AnchorPane.setRightAnchor(root1, 0.0);
        AnchorPane.setLeftAnchor(root1, 0.0);
        //System.out.println("LOaded");
        FadeTransition transition = new FadeTransition(Duration.seconds(500), root1);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
    }


    public void btnLogOutOnAction(ActionEvent event) throws IOException {
       ((Stage)root.getScene().getWindow()).close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"))));
        stage.setTitle("LOGIN");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();

    }

    public void btnManageCustomerOnAction(ActionEvent event) throws IOException {
        navigation(FXMLLoader.load(getClass().getResource("/view/ManageCustomer.fxml")));
    }

    public void btnManageStockOnAction(ActionEvent event) throws IOException {
        navigation(FXMLLoader.load(getClass().getResource("/view/MangeStock.fxml")));
    }

    public void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        navigation(FXMLLoader.load(getClass().getResource("/view/PlaceOrder.fxml")));
    }

    public void btnViewResponseOnAction(ActionEvent event) throws IOException {
        navigation(FXMLLoader.load(getClass().getResource("/view/ViewResponce.fxml")));
    }

    public void btnDashBoardController(ActionEvent actionEvent) throws IOException {
        navigation(FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml")));

    }

//    public void loadStage(AnchorPane container){
//        Stage stage = new Stage();
//        stage.setScene(new Scene(container));
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.centerOnScreen();
//        stage.setTitle("Dash Board");
//        stage.show();
//
//    }
}
