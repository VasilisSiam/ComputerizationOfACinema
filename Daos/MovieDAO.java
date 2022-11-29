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

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280" ;
    static Connection dbConnection = null;
    static String     username = "it185280";
    static String     passwd = "Joykos18/";
    static Statement  statement = null;
    static ResultSet rs = null;

    public static boolean insertMovie(String [] pedia,int m_id,int releaseDate) throws ClassNotFoundException, SQLException {
        boolean returnState = false;
        String query = "select * from insert_movies('"+m_id+"','"+pedia[0]+"','"+pedia[1]+"','"+releaseDate+"','"+pedia[2]+"')" ;
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

    public static boolean deleteMovie(movie selectedItem){
        int m_id = selectedItem.m_idProperty().get();
        String movie_title = selectedItem.movie_titleProperty().get();
        String paragogi = selectedItem.paragogiProperty().get();
        int releaseDate = selectedItem.releaseDateProperty().get();
        String critue = selectedItem.critueProperty().get();
        boolean returnState = false;
        String query = "select * from delete_movies('"+m_id+"','"+movie_title+"','"+paragogi+"','"+releaseDate+"','"+critue+"')";
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


}

