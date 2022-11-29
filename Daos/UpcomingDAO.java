package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpcomingDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280" ;
    static Connection dbConnection = null;
    static String     username = "it185280";
    static String     passwd = "Joykos18/";
    static Statement  statement = null;
    static ResultSet rs = null;

    public static boolean  insertUpcoming(int u_id, String upcoming_title, int releasedate, String paragogi) throws SQLException, ClassNotFoundException {
        movie selectedMovie = findMovie(releasedate);
        director newDirector = new Director();
        newDirector.setreleasedate(selectedMovie.getNameProperty().get());      
        boolean returnState = false;
        System.out.println(selectedMovie);
        if(selectedMovie != null){
            String query = "select * from insert_upcoming('"+
                    selectedMovie.getIdProperty().get()+"','"+
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
        }else return false;

    }

    public static boolean deleteUpcoming(upcoming selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        movie selectedMovie  = findMovie(selectedItem.releasedateProperty().get()) ;
        boolean returnState = false;

        if(selectedMovie != null){
    
            String query = "select * from delete_upcoming('"+
                    selectedMovie.getm_idProperty().get()+"','"+
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
    
        }else return false;
    }
    
    public static Movie findMovie(String releasedate) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try{
            Movie mo = new Movie();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("select * from findMovie('"+releasedate+"')");
            ResultSetMetaData rsMetadata = rs.getMetaData();
            System.out.println(rsMetadata.getColumnCount());
            while (rs.next()) {
                mo.setm_id(rs.getInt(1));
                mo.setmovie_title(rs.getString(2));
                mo.setparagogi(rs.getString(3));
                mo.setreleaseDate(rs.getInt(4));
                mo.setcritue(rs.getString(5));
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
}