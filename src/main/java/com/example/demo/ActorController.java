package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.ParseException;

public class ActorController {
    @FXML
    private Label selectedFilter;
    private  ToggleGroup filters = new ToggleGroup() ;
    public ToggleGroup getFilters() {
        return filters;
    }
    public Label getSelectedFilter() {
        return selectedFilter;
    }

    public RadioButton getNoFilter() {
        return NoFilter;
    }

    public RadioButton getMale() {
        return Male;
    }

    public RadioButton getFemale() {
        return Female;
    }

    @FXML
    private RadioButton NoFilter,Male,Female;
    @FXML
    private Button goBackButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Text deleteError;
    @FXML
    private Text insertError;
    @FXML
    private TextField idColumnInput;
    @FXML
    private TextField NameColumnInput;
    @FXML
    private TextField AgeColumnInput;
    @FXML
    private TextField GenderColumnInput;
    @FXML
    private TextField MovieColumnInput;

        @FXML
        private TableView<Actor> ActorView;
            public TableView<Actor> getActorView(){
                return ActorView;
    }
    @FXML
    private TableColumn<Actor,String> idColumn;
    @FXML
    private TableColumn<Actor,String> NameColumn;
    @FXML
    private TableColumn<Actor,String> GenderColumn;
    @FXML
    private TableColumn<Actor,String> AgeColumn;
    @FXML
    private TableColumn<Actor,String> MovieColumn;

    public Button getGoBackButton() {
        return goBackButton;
    }

    public Button getInsertButton() {
        return insertButton;
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
    @FXML
    public TextField getIdColumnInput() {
        return idColumnInput;
    }

    @FXML
    public TextField getNameColumnInput() {
        return NameColumnInput;
    }
    @FXML
    public TextField getAgeColumnInput() {
        return AgeColumnInput;
    }
    @FXML
    public TextField getGenderColumnInput() {
        return GenderColumnInput;
    }
    @FXML
    public TextField getMovieColumnInput() {
        return MovieColumnInput;
    }

    private void populateTable(ObservableList ActorList) {
        ActorView.setItems(ActorList);
    }
    public void initialize() throws Exception {
        setActorViewCellData();
        ObservableList ActorList = ActorDAO.getAllRecords();
        populateTable(ActorList);
    }
    public void setActorViewCellData(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().actor_nameProperty());
        AgeColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        GenderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        MovieColumn.setCellValueFactory(cellData -> cellData.getValue().movieProperty());
    }
    public boolean insert(String a_id, String actor_Name, String gender, String age, String movie_title) throws SQLException, ClassNotFoundException, SQLException {
        boolean returnState = ActorDAO.insertActor(a_id,  actor_Name,  gender, age,  movie_title);
        setActorViewCellData();
        ObservableList ActorList = ActorDAO.getAllRecords();
        populateTable(ActorList);
        return returnState;
    }

    public boolean delete(Actor selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        boolean returnState = ActorDAO.deleteActor(selectedItem);
        setActorViewCellData();
        ObservableList ActorList = ActorDAO.getAllRecords();
        populateTable(ActorList);
        return  returnState;

    }
    public void filterBy(String epilogi) throws SQLException,ClassNotFoundException{
        ObservableList ActorList=ActorDAO.filterBy(epilogi);
        setActorViewCellData();
        populateTable(ActorList);
    }
}
