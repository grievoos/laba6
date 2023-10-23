package com.example.voting2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML

    private TextField text_num, text_theme, text_start, text_finish, text_status;

    private Stage dialog;

    private Voting voting;

    @FXML
    void edit(ActionEvent event) {
        if (!text_num.getText().isEmpty() && !text_theme.getText().isEmpty() && !text_start.getText().isEmpty()
                && !text_finish.getText().isEmpty() && !text_status.getText().isEmpty()){
            voting.setId(text_num.getText());
            voting.setTheme(text_theme.getText());
            voting.setStart(text_start.getText());
            voting.setFinish(text_finish.getText());
            voting.setStatus(text_status.getText());
            dialog.close();}
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialog);
            alert.setTitle("Пустое поле!");
            alert.setHeaderText("Одно или несколько полей пусты");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {dialog.close();}
    public void getDialog(Stage dialogStage) {this.dialog = dialogStage;}
    public void getVoting(Voting voting) {
        text_num.setText(voting.getId());
        text_theme.setText(voting.getTheme());
        text_start.setText(voting.getStart());
        text_finish.setText(voting.getFinish());
        text_status.setText(voting.getStatus());
        this.voting = voting;}

}
