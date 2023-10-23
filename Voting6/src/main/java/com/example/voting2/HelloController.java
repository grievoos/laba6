package com.example.voting2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Voting, String> col_num, col_theme, col_start, col_finish, col_status;

    @FXML
    private Label l_num, l_theme, l_start, l_finish, l_status;

    @FXML
    private TableView<Voting> voting_table;

    ObservableList<Voting> voting = FXCollections.observableArrayList(
            new Voting("1", "Выборы председателя СтудСовета",
                    "22.09.2023", "23.09.2023", "Завершено"),
            new Voting("2", "Выборы Студ.Декана ЮрФак",
                    "30.10.2023", "30.10.2023", "Запланировано"),
            new Voting("3", "Выборы председателя общежития",
                    "07.10.2023", "09.10.2023", "В процессе")
    );

    @FXML
    void add(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-view.fxml"));
        dialog.setScene(new Scene(loader.load(),600, 400));
        AddController controller = loader.getController();
        controller.getDialog(dialog);
        controller.getVoting(voting);
        dialog.showAndWait();
        voting_table.setItems(FXCollections.observableList(voting));
    }

    @FXML
    void delete(ActionEvent event) {
        if (voting_table.getSelectionModel().getSelectedItem()!=null) {
            voting.remove(voting_table.getSelectionModel().getSelectedItem());
            voting_table.setItems(FXCollections.observableList(voting));
            l_num.setText("Голосование №");
            l_theme.setText("Тема: ");
            l_start.setText("Начало: ");
            l_finish.setText("Конец: ");
            l_status.setText("Статус: ");
        }
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        if (voting_table.getSelectionModel().getSelectedItem()!=null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("edit-view.fxml"));
            stage.setScene(new Scene(loader.load(),600, 400));
            EditController controller = loader.getController();
            controller.getDialog(stage);
            int id = voting.indexOf(voting_table.getSelectionModel().getSelectedItem());
            controller.getVoting(voting.get(id));
            stage.showAndWait();
            voting_table.setItems(voting);
            voting_table.getSelectionModel().clearSelection();
            voting_table.getSelectionModel().select(id);
            voting_table.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_num.setCellValueFactory(new PropertyValueFactory<Voting, String>("id"));
        col_theme.setCellValueFactory(new PropertyValueFactory<Voting, String>("theme"));
        col_start.setCellValueFactory(new PropertyValueFactory<Voting,String>("start"));
        col_finish.setCellValueFactory(new PropertyValueFactory<Voting,String>("finish"));
        col_status.setCellValueFactory(new PropertyValueFactory<Voting,String>("status"));

        voting_table.setItems(voting);
        voting_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Voting voting = voting_table.getSelectionModel().getSelectedItem();
                l_num.setText("Голосование №"+voting.getId());
                l_theme.setText("Тема: "+voting.getTheme());
                l_start.setText("Начало: "+voting.getStart());
                l_finish.setText("Конец: "+voting.getFinish());
                l_status.setText("Статус: "+voting.getStatus());
            }
        });
    }
}
