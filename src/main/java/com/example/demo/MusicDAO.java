package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;

public class MusicDAO {

    static String driverClassName = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280";
    static Connection dbConnection = null;
    static String username = "it185280";
    static String passwd = "kostas2000strap15";
    static Statement statement = null;
    static ResultSet rs = null;


    public static ObservableList<Music> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from displaymusic()";
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Music> MusicList = getMusicObjects(MusicDAO.rs);
            return MusicList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Music> getMusicObjects(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Music> MusicList = FXCollections.observableArrayList();
            while (rs.next()) {
                Music Act = new Music();
                Act.setId(rs.getString(1));
                Act.setMusic_title(rs.getString(2));
                Act.setMovie_title(rs.getString(3));
                Act.setComposer(rs.getString(4));
                MusicList.add(Act);
            }
            return MusicList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean  insertMusic(String id, String music_title, String movie_title, String composer) throws SQLException, ClassNotFoundException {
        Movie selectedMovie = findMovie(movie_title);
        Music newMusic = new Music();
        newMusic.setMovie_title(selectedMovie.movie_titleProperty().get());
        boolean returnState = false;
        System.out.println(selectedMovie);
        if(selectedMovie != null){
            String query = "select * from insert_music('"+id+"','"+music_title+"','"+movie_title+"','"+composer+"')";
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url,username,passwd);
            try {
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                returnState = true;
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnState;
        }
        else return false;

    }

    public static boolean deleteMusic( Music selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        boolean returnState = false;
        String m_id=selectedItem.idProperty().get();
        String musictitle=selectedItem.music_titleProperty().get();
        String movie=selectedItem.movie_titleProperty().get();
        String composer=selectedItem.composerProperty().get();
            String query = "select * from delete_music('"+m_id+"','"+musictitle+"','"+movie+"','"+composer+"')";
            try {
                System.out.println(m_id+musictitle);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                returnState = true;
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnState;

    }

    public static Movie findMovie(String movie_title) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try{
            Movie mo = new Movie();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("select * from findMovie('"+movie_title+"')");
            ResultSetMetaData rsMetadata = rs.getMetaData();
            System.out.println(rsMetadata.getColumnCount());
            while (rs.next()) {
                mo.setId(rs.getString(1));
                mo.setMovie_title(rs.getString(2));
                mo.setParagogi(rs.getString(3));
                mo.setReleasedate(rs.getString(4));
                mo.setCritique(rs.getString(5));
            }
            return mo;
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static ObservableList<Music> filterBy(String epilogh) throws SQLException,ClassNotFoundException {
        String query = "Select * from filtermusic('"+epilogh +"')";
       if(epilogh.isEmpty()){
           try {
               Class.forName(driverClassName);
               dbConnection = DriverManager.getConnection(url, username, passwd);
               statement = dbConnection.createStatement();
               rs = statement.executeQuery("SELECT * From Music;");
               ObservableList MusicList = FXCollections.observableArrayList();
               while (rs.next()) {
                   Music Act = new Music();
                   Act.setId(rs.getString(1));
                   Act.setMusic_title(rs.getString(2));
                   Act.setMovie_title(rs.getString(3));
                   Act.setComposer(rs.getString(4));
                   MusicList.add(Act);
               }
               return MusicList;
           } catch (SQLException e) {
               System.out.println("Error occured while fetching the record from the DataBase" + e);
               e.printStackTrace();
               throw e;
           }
       }else {
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            ObservableList MusicList = FXCollections.observableArrayList();
            while (rs.next()) {
                Music Act = new Music();
                Act.setId(rs.getString(1));
                Act.setMusic_title(rs.getString(2));
                Act.setMovie_title(rs.getString(3));
                Act.setComposer(rs.getString(4));
                MusicList.add(Act);
            }
            return MusicList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
}