package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.ParseException;

public class MusicController {
    @FXML
    private TextField Filter;

    public TextField getFilter() {
        return Filter;
    }

    public void setFilter(TextField filter) {
        Filter = filter;
    }

    @FXML
    private TableColumn<Music,String>  MusicTitleColumn;
    @FXML
    private TableColumn<Music,String> idColumn;
    @FXML
    private TableColumn<Music,String>  MovieColumn;
    @FXML
    private TableColumn<Music,String>  ComposerColumn;
    @FXML
    private TableView<Music> MusicView;
    public TableView<Music> getMusicView(){
        return MusicView;
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
    private TextField MusicColumnInput;
    @FXML
    private Button insertButton;
    @FXML
    private TextField numOfComposerColumnInput;
    @FXML
    private TextField MovieNumberColumnInput;
    @FXML
    private Button goBackButton;

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

    public TextField getMusicColumnInput() {
        return MusicColumnInput;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public TextField getNumOfComposerColumnInput() {
        return numOfComposerColumnInput;
    }

    public TextField getMovieNumberColumnInput() {
        return MovieNumberColumnInput;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }


    private void populateTable(ObservableList MusicList) {
        MusicView.setItems(MusicList);
    }
    public void initialize() throws Exception {
        setMusicViewCellData();
        ObservableList MusicList = MusicDAO.getAllRecords();
        populateTable(MusicList);
    }
    public void setMusicViewCellData(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        MusicTitleColumn.setCellValueFactory(cellData -> cellData.getValue().music_titleProperty());
        ComposerColumn.setCellValueFactory(cellData -> cellData.getValue().composerProperty());
        MovieColumn.setCellValueFactory(cellData -> cellData.getValue().movie_titleProperty());
    }
    public boolean insert(String m_id, String Music_title,  String movie_title, String composer) throws SQLException, ClassNotFoundException, SQLException {
        boolean returnState = MusicDAO.insertMusic(m_id,Music_title,movie_title,composer);
        setMusicViewCellData();
        ObservableList MusicList = MusicDAO.getAllRecords();
        populateTable(MusicList);
        return returnState;
    }
    public boolean delete(Music selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        boolean returnState = MusicDAO.deleteMusic(selectedItem);
        setMusicViewCellData();
        ObservableList MusicList =MusicDAO.getAllRecords();
        populateTable(MusicList);
        return  returnState;

    }
    public void filterBy(String epilogi) throws SQLException,ClassNotFoundException{
        ObservableList MusicList=MusicDAO.filterBy(epilogi);
        setMusicViewCellData();
        populateTable(MusicList);
    }

}
