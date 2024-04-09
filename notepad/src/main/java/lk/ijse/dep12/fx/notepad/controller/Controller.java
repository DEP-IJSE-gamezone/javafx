package lk.ijse.dep12.fx.notepad.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.dep12.fx.notepad.AppInitializer;

import java.io.*;
import java.util.Optional;

public class Controller {
    public MenuItem mntAbout;

    public TextArea txtDisplay;
    public AnchorPane rootMain;

    private boolean isalreadySaved = false;
    private File currentFile;
    private boolean isEdited = false;
    private boolean isOpened = false;

    public void initialize() {
        //set property listener to set the title
        txtDisplay.textProperty().addListener((observble, previous, current) -> {
            if (current != previous) isEdited = true;
            if (isEdited) {
                if (currentFile == null) setTitle("*Untitled Document");
                else setTitle("*" + currentFile.getName());
            }
        });

        //Close window
        Platform.runLater(() -> {
            Stage stage = (Stage) rootMain.getScene().getWindow();
            stage.setOnCloseRequest(windowEvent -> {
                windowEvent.consume();
                if (!isEdited) stage.close();
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is unsaved data. Do you want to exit?", ButtonType.NO, ButtonType.YES);
                    alert.setHeaderText("Exit?");
                    alert.setTitle("Exit Window");
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) stage.close();
                }
            });

        });
    }

    //Confirmation of exit window
//    private void confirmationExit(){
//        Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is unsaved data. Do you want to exit ?", ButtonType.NO, ButtonType.YES);
//        alert.setTitle("Exit Window");
//        alert.setHeaderText("Exit ?");
//        Optional<ButtonType> buttonType = alert.showAndWait();
//        if (buttonType.get() == ButtonType.YES) Platform.exit();
//        else return;
//    }
    public void mntExitOnAction(ActionEvent event) {
        if (isEdited) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is unsaved data. Do you want to exit ?", ButtonType.NO, ButtonType.YES);
            alert.setTitle("Exit Window");
            alert.setHeaderText("Exit ?");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) Platform.exit();
            else return;
        } else {
            Platform.exit();
        }

    }

    private void loadToTextArea() {
        try (FileInputStream fis = new FileInputStream(currentFile)) {
            byte[] bytes = new byte[(int) currentFile.length()];
            fis.read(bytes);
            txtDisplay.setText(new String(bytes));

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Can not Open the file").showAndWait();
        }
    }

    public void mntOpenOnAction(ActionEvent event) throws IOException {
        isOpened = true;
        fileChooser();
        if (currentFile == null) return;
        //todo:isedited open in new window
//        if(isEdited){
//            Stage stage = (Stage)txtDisplay.getScene().getWindow();
//            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainView.fxml"))));
//            stage.show();
//            loadToTextArea();
//        }

        loadToTextArea();
        isOpened = false;
    }

    public void mntSaveAsOnAction(ActionEvent event) {
        isalreadySaved = false;
        mntSaveOnAction(event);

    }

    private void setTitle(String title) {
        Stage stage = (Stage) txtDisplay.getScene().getWindow();
        stage.setTitle(title);
    }


    private void saveToFile(File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(txtDisplay.getText().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "failed to save the file.").showAndWait();
        }

    }

    private void fileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Plain text file (*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle("Folder Selection");
        fileChooser.setInitialDirectory(new File(System.getenv("HOME"), "Documents"));
        File file = isOpened ? fileChooser.showOpenDialog(txtDisplay.getScene().getWindow()) : fileChooser.showSaveDialog(txtDisplay.getScene().getWindow());
        currentFile = file;
        //File file = fileChooser.showSaveDialog(txtDisplay.getScene().getWindow());
        if (currentFile == null) return;
        if (!currentFile.getName().contains(".txt"))
            currentFile = new File(file.getParentFile(), file.getName() + ".txt");

    }

    public void mntSaveOnAction(ActionEvent event) {
        if (!isalreadySaved) {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Plain text file (*.txt)", "*.txt"));
//            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
//            fileChooser.setTitle("Folder Selection");
//            fileChooser.setInitialDirectory(new File(System.getenv("HOME"), "Documents"));
//            File file = fileChooser.showSaveDialog(txtDisplay.getScene().getWindow());
//            if (currentFile == null) return;
//            if (!file.getName().contains(".txt")) file = new File(file.getParentFile(), file.getName() + ".txt");
//            currentFile = file;
            fileChooser();

        }
        saveToFile(currentFile);
        isalreadySaved = true;
        //to change the title
        isEdited = false;
        setTitle(currentFile.getName());

    }

    public void mntAboutOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/HelpView.fxml")));
        stage.setScene(scene);
        stage.setTitle("About");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(rootMain.getScene().getWindow());
        stage.show();
        stage.centerOnScreen();
    }


    public void mntNewOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainView.fxml"))));
        stage.setTitle("Untitled Document");
        stage.show();
    }
}
