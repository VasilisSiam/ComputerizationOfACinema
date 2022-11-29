package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.ParseException;

public class UpcomingController {

    @FXML
    private TableView<Upcoming> UpcomingView;
    public TableView<Upcoming> UpcomingView(){
        return UpcomingView;
    }
    @FXML
    private TableColumn<Upcoming,String> idColumn;
    @FXML
    private TableColumn<Upcoming,String> MovieColumn;
    @FXML
    private TableColumn<Upcoming,String> ReleaseDateColumn;
    @FXML
    private TableColumn<Upcoming,String> ProductionColumn;

    @FXML
    private Button deleteButton;
    @FXML
    private Text deleteError;
    @FXML
    private Text insertError;

    @FXML
    private TextField idColumnInput;
    @FXML
    private TextField MovieTitleColumnInput;
    @FXML
    private Button insertButton;
    @FXML
    private Button goBackButton;
    @FXML
    private TextField numOfComposerColumnInput;
    @FXML
    private TextField ReleaseDateColumnInput;

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Text getDeleteError() {
        return deleteError;
    }

    public Text getInsertError() {
        return insertError;
    }

    public TextField getIdColumnInput() {
        return idColumnInput;
    }

    public TextField getMovieTitleColumnInput() {
        return MovieTitleColumnInput;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public TextField getNumOfComposerColumnInput() {
        return numOfComposerColumnInput;
    }

    public TextField getReleaseDateColumnInput() {
        return ReleaseDateColumnInput;
    }


    private void populateTable(ObservableList UpcomingList) {
        UpcomingView.setItems(UpcomingList);
    }
    public void initialize() throws Exception {
        setUpcomingViewCellData();
        ObservableList UpcomingList = UpcomingDAO.getAllRecords();
        populateTable(UpcomingList);
    }
    public void setUpcomingViewCellData(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().u_idProperty());
        MovieColumn.setCellValueFactory(cellData -> cellData.getValue().upcoming_TitleProperty());
       ReleaseDateColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
        ProductionColumn.setCellValueFactory(cellData -> cellData.getValue().paragogiProperty());
    }
    public boolean insert(String m_id, String Movie_title,  String ReleaseDate, String production) throws SQLException, ClassNotFoundException, SQLException {
        boolean returnState = UpcomingDAO.insertUpcoming(m_id,Movie_title,ReleaseDate,production);
        setUpcomingViewCellData();
        ObservableList UpcomingList = UpcomingDAO.getAllRecords();
        populateTable(UpcomingList);
        return returnState;
    }
    public boolean delete(Upcoming selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        boolean returnState = UpcomingDAO.deleteUpcoming(selectedItem);
        setUpcomingViewCellData();
        ObservableList UpcomingList = DirectorDAO.getAllRecords();
        populateTable(UpcomingList);
        return  returnState;

    }

}
