package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.ParseException;

public class MovieController {
    @FXML
    private Label selectedFilter;
    @FXML
    private RadioButton Greek,Usa,Foreign,NoFilter;
    @FXML
    private TableView<Movie> MovieView;
    public TableView<Movie> getMovieView(){
        return MovieView;
    }
    @FXML
    private TableColumn<Movie,String> idColumn;
    @FXML
    private TableColumn<Movie,String> TitleColumn;
    @FXML
    private TableColumn<Movie,String> ProductionColumn;
    @FXML
    private TableColumn<Movie,String> DateColumn;
    @FXML
    private TableColumn<Movie,String> CritiqueColumn;
    private  ToggleGroup filters = new ToggleGroup() ;
    public ToggleGroup getFilters() {
        return filters;
    }

    @FXML
    private Text insertError;
    @FXML
    private Text deleteError;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField idColumnInput;
    @FXML
    private TextField TitleColumnInput;
    @FXML
    private TextField DateColumnInput;
    @FXML
    private TextField ProductionColumnInput;
    @FXML
    private TextField CritiqueColumnInput;
    @FXML
    private Button insertButton;
    @FXML
    private Button goBackButton;

    public Label getSelectedFilter() {
        return selectedFilter;
    }
    public Text getInsertError() {
        return insertError;
    }

    public Text getDeleteError() {
        return deleteError;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public TextField getIdColumnInput() {
        return idColumnInput;
    }

    public TextField getTitleColumnInput() {
        return TitleColumnInput;
    }

    public TextField getDateColumnInput() {
        return DateColumnInput;
    }

    public TextField getProductionColumnInput() {
        return ProductionColumnInput;
    }

    public TextField getCritiqueColumnInput() {
        return CritiqueColumnInput;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public RadioButton getGreek() {
        return Greek;
    }

    public RadioButton getUsa() {
        return Usa;
    }

    public RadioButton getForeign() {
        return Foreign;
    }

    public RadioButton getNoFilter() {
        return NoFilter;
    }

    private void populateTable(ObservableList MovieList) {
        MovieView.setItems(MovieList);
    }
    public void initialize() throws Exception {
        setMovieViewCellData();
        ObservableList MovieList = MovieDAO.getAllRecords();
        populateTable(MovieList);
    }
    public void setMovieViewCellData(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TitleColumn.setCellValueFactory(cellData -> cellData.getValue().movie_titleProperty());
        ProductionColumn.setCellValueFactory(cellData -> cellData.getValue().paragogiProperty());
        DateColumn.setCellValueFactory(cellData -> cellData.getValue().releasedateProperty());
        CritiqueColumn.setCellValueFactory(cellData -> cellData.getValue().critiqueProperty());
    }
    public boolean insert(String m_id,String title,String paragogi,String Date,String critique) throws SQLException, ClassNotFoundException, SQLException {
        boolean returnState = MovieDAO.insertMovie(m_id,title,paragogi,Date,critique);
        setMovieViewCellData();
        ObservableList MovieList = MovieDAO.getAllRecords();
        populateTable(MovieList);
        return returnState;
    }
    public boolean delete(Movie selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        boolean returnState = MovieDAO.deleteMovie(selectedItem);
        setMovieViewCellData();
        ObservableList MovieList = MovieDAO.getAllRecords();
        populateTable(MovieList);
        return  returnState;

    }
    public void filterBy(String epilogi) throws SQLException,ClassNotFoundException{
        ObservableList MovieList=MovieDAO.filterBy(epilogi);
        setMovieViewCellData();
        populateTable(MovieList);
    }

}
