package com.example.voting2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class AddController{
    @FXML
    private TextField text_num, text_theme, text_start, text_finish, text_status;
    private Stage dialog;
    private ObservableList<Voting> voting = FXCollections.observableArrayList();
    @FXML
    void add(ActionEvent event) {
        if(!text_num.getText().isEmpty() && !text_theme.getText().isEmpty()
                && !text_start.getText().isEmpty() && !text_finish.getText().isEmpty() && !text_status.getText().isEmpty()){
            voting.add(new Voting(text_num.getText(),text_theme.getText(),
                    text_status.getText(),text_finish.getText(),text_status.getText()));
            dialog.close();}
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialog);
            alert.setTitle("Пустое поле!");
            alert.setHeaderText("Одно или несколько полей пусты!");
            alert.showAndWait();
        }
    }
    @FXML
    void cancel(ActionEvent event) {dialog.close();}
    public void getDialog(Stage dialog)
    {this.dialog = dialog;}
    public void getVoting(ObservableList<Voting> voting) {this.voting = voting;}
}



