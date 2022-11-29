package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LogControler {
    @FXML
    private Button back;
    @FXML
    private TableView LogView;
    @FXML
    private TableColumn<Log,String> Operation,Stamp,UserID,M_Id,Movie,Producer,ReleaseDate,Critique ;

    public TableView getDirectorsView() {
        return LogView;
    }

    public TableColumn getOperation() {
        return Operation;
    }

    public TableColumn getStamp() {
        return Stamp;
    }

    public TableColumn getUserID() {
        return UserID;
    }

    public TableColumn getM_Id() {
        return M_Id;
    }

    public TableColumn getMovie() {
        return Movie;
    }

    public TableColumn getProducer() {
        return Producer;
    }

    public TableColumn getReleaseDate() {
        return ReleaseDate;
    }

    public TableColumn getCritique() {
        return Critique;
    }

    public Button getBack() {
        return back;
    }

    private void populateTable(ObservableList LogList) {
        LogView.setItems(LogList);
    }
    public void initialize() throws Exception {
        setLogViewCellData();
        ObservableList LogList = Log.getAllRecords();
        populateTable(LogList);
    }

    public void setLogViewCellData(){
        Operation.setCellValueFactory(cellData -> cellData.getValue().operationProperty());
        Stamp.setCellValueFactory(cellData -> cellData.getValue().stampProperty());
       UserID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());
        M_Id.setCellValueFactory(cellData -> cellData.getValue().m_IdProperty());
        Movie.setCellValueFactory(cellData -> cellData.getValue().movieProperty());
        Producer.setCellValueFactory(cellData -> cellData.getValue().producerProperty());
        ReleaseDate.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        Critique.setCellValueFactory(cellData -> cellData.getValue().critiqueProperty());

    }
}
