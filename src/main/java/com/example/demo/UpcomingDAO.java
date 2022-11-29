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

    static String driverClassName = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280";
    static Connection dbConnection = null;
    static String username = "it185280";
    static String passwd = "kostas2000strap15";
    static Statement statement = null;
    static ResultSet rs = null;


    public static ObservableList<Upcoming> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from displayupcoming()";
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Upcoming> UpcomingList = getUpcomingObjects(UpcomingDAO.rs);
            return UpcomingList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Upcoming> getUpcomingObjects(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Upcoming> UpcomingList = FXCollections.observableArrayList();
            while (rs.next()) {
                Upcoming Act = new Upcoming();
                Act.setU_id(rs.getString(1));
                Act.setUpcoming_Title(rs.getString(2));
                Act.setReleaseDate(rs.getString(3));
                Act.setParagogi(rs.getString(4));
                UpcomingList.add(Act);
            }
            return UpcomingList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean  insertUpcoming(String u_id, String upcoming_Title, String releasedate, String paragogi) throws SQLException, ClassNotFoundException {
        Movie selectedMovie = findMovie(releasedate);
        Upcoming newUpcoming = new Upcoming();
        newUpcoming.setReleaseDate(selectedMovie.releasedateProperty().get());
        boolean returnState = false;
        System.out.println(selectedMovie);
        if(selectedMovie != null){
            String query = "select * from insert_upcoming('"+u_id+"','"+upcoming_Title+"','"+releasedate+"','"+paragogi+"')";
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

    public static boolean deleteUpcoming( Upcoming selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        Movie selectedMovie  = findMovie(selectedItem.releaseDateProperty().get());
        boolean returnState = false;

        if(selectedMovie != null){

            String query = "select * from delete_music('"+
                    selectedMovie.releasedateProperty().get()+"','"+
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
}