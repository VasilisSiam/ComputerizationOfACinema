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

public class DirectorsController {
    @FXML
    private TextField filter;

    public TextField getFilter() {
        return filter;
    }

    public void setFilter(TextField filter) {
        this.filter = filter;
    }

    @FXML
    private TableView<Directors> DirectorsView;
    public TableView<Directors> DirectorsView(){
        return DirectorsView;
    }

    @FXML
    private TableColumn<Directors,String> AgeColumn;
    @FXML
    private TableColumn<Directors,String> MovieColumn;
    @FXML
    private TableColumn<Directors,String> NameTitleColumn;
    @FXML
    private TableColumn<Directors,String> idColumn;

    @FXML
    private TextField MovieColumnInput;
    @FXML
    private TextField AgeColumnInput;
    @FXML
    private Button insertButton;
    @FXML
    private TextField NameColumnInput;
    @FXML
    private TextField idColumnInput;
    @FXML
    private Button deleteButton;
    @FXML
    private Text deleteError;
    @FXML
    private Text insertError;
    @FXML
    private Button goBackButton;

    public TextField getMovieColumnInput() {
        return MovieColumnInput;
    }

    public TextField getAgeColumnInput() {
        return AgeColumnInput;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public TextField getNameColumnInput() {
        return NameColumnInput;
    }

    public TextField getIdColumnInput() {
        return idColumnInput;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Text getDeleteError() {
        return deleteError;
    }

    public Text getInsertError() {
        return insertError;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }


    private void populateTable(ObservableList DirectorsList) {
        DirectorsView.setItems(DirectorsList);
    }
    public void initialize() throws Exception {
        setDirectorsViewCellData();
        ObservableList DirectorsList = DirectorDAO.getAllRecords();
        populateTable(DirectorsList);
    }
    public void setDirectorsViewCellData(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().d_idProperty());
        NameTitleColumn.setCellValueFactory(cellData -> cellData.getValue().d_nameProperty());
        AgeColumn.setCellValueFactory(cellData -> cellData.getValue().d_ageProperty());
        MovieColumn.setCellValueFactory(cellData -> cellData.getValue().movie_titleProperty());
    }

    public boolean insert(String D_id, String D_Name,  String movie_title, String age) throws SQLException, ClassNotFoundException, SQLException {
        boolean returnState = DirectorDAO.insertDirector(D_id,D_Name,movie_title,age);
        setDirectorsViewCellData();
        ObservableList DirectorList = DirectorDAO.getAllRecords();
        populateTable(DirectorList);
        return returnState;
    }
    public boolean delete(Directors selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        boolean returnState = DirectorDAO.deleteDirector(selectedItem);
        setDirectorsViewCellData();
        ObservableList DirectorList = DirectorDAO.getAllRecords();
        populateTable(DirectorList);
        return  returnState;

    }
    public void filterBy(String epilogi) throws SQLException,ClassNotFoundException{
        ObservableList DirectorList=DirectorDAO.filterBy(epilogi);
        setDirectorsViewCellData();
        populateTable(DirectorList);
    }

}
