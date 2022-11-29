package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MovieDAO {

    static String driverClassName = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280";
    static Connection dbConnection = null;
    static String username = "it185280";
    static String passwd = "kostas2000strap15";
    static Statement statement = null;
    static ResultSet rs = null;


    public static ObservableList<Movie> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from displaymovie()";
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Movie> MovieList = getMovieObjects(MovieDAO.rs);
            return MovieList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Movie> getMovieObjects(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Movie> MovieList = FXCollections.observableArrayList();
            while (rs.next()) {
                Movie Act = new Movie();
                Act.setId(rs.getString(1));
                Act.setMovie_title(rs.getString(2));
                Act.setParagogi(rs.getString(3));
                Act.setReleasedate(rs.getString(4));
                Act.setCritique(rs.getString(5));
                MovieList.add(Act);
            }
            return MovieList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean insertMovie(String m_id,String title,String paragogi,String releaseDate,String critique) throws ClassNotFoundException, SQLException {
        boolean returnState = false;
        String query = "select * from insert_movies('"+m_id+"','"+title+"','"+paragogi+"','"+releaseDate+"','"+critique+"')" ;
        System.out.println(query);

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

    public static boolean deleteMovie(Movie selectedItem){
        String m_id = selectedItem.getId();
        String movie_title = selectedItem.movie_titleProperty().get();
        String paragogi = selectedItem.paragogiProperty().get();
        String releasedate = selectedItem.releasedateProperty().get();
        String critique = selectedItem.critiqueProperty().get();
        boolean returnState = false;
        String query = "select * from delete_movies('"+m_id+"','"+movie_title+"','"+paragogi+"','"+releasedate+"','"+critique+"')";
        System.out.println(query);
        try {
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);

            returnState = true ;
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return returnState;

    }
public static ObservableList<Movie> filterBy(String epilogh) throws SQLException,ClassNotFoundException {
    String query = "Select * from filterMovie('" + epilogh + "')";
    if (epilogh.equals("Greek")) {
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            ObservableList MovieList = FXCollections.observableArrayList();
            while (rs.next()) {
                Movie Act = new Movie();
                Act.setId(rs.getString(1));
                Act.setMovie_title(rs.getString(2));
                Act.setParagogi(rs.getString(3));
                Act.setReleasedate(rs.getString(4));
                Act.setCritique(rs.getString(5));
                MovieList.add(Act);
            }
            return MovieList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    } else if (epilogh.equals("Usa")) {
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            ObservableList MovieList = FXCollections.observableArrayList();
            while (rs.next()) {
                Movie Act = new Movie();
                Act.setId(rs.getString(1));
                Act.setMovie_title(rs.getString(2));
                Act.setParagogi(rs.getString(3));
                Act.setReleasedate(rs.getString(4));
                Act.setCritique(rs.getString(5));
                MovieList.add(Act);
            }
    return MovieList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    } else if (epilogh.equals("Foreign")) {
        try {
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            ObservableList MovieList = FXCollections.observableArrayList();
            while (rs.next()) {
                Movie Act = new Movie();
                Act.setId(rs.getString(1));
                Act.setMovie_title(rs.getString(2));
                Act.setParagogi(rs.getString(3));
                Act.setReleasedate(rs.getString(4));
                Act.setCritique(rs.getString(5));
                MovieList.add(Act);
            }
            return MovieList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }else {
        try {
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("Select * from Movie;");
            ObservableList MovieList = FXCollections.observableArrayList();
            while (rs.next()) {
                Movie Act = new Movie();
                Act.setId(rs.getString(1));
                Act.setMovie_title(rs.getString(2));
                Act.setParagogi(rs.getString(3));
                Act.setReleasedate(rs.getString(4));
                Act.setCritique(rs.getString(5));
                MovieList.add(Act);
            }
            return MovieList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    }

}






