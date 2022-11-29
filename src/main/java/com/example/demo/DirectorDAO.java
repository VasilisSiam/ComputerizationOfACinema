package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DirectorDAO {

    static String driverClassName = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280";
    static Connection dbConnection = null;
    static String username = "it185280";
    static String passwd = "kostas2000strap15";
    static Statement statement = null;
    static ResultSet rs = null;

    public static ObservableList<Directors> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from displaydirector()";
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Directors> DirectorsList = getDirectorsObjects(DirectorDAO.rs);
            return DirectorsList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Directors> getDirectorsObjects(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Directors> DirectorsList = FXCollections.observableArrayList();
            while (rs.next()) {
                Directors Act = new Directors();
                Act.setD_id(rs.getString(1));
                Act.setD_name(rs.getString(2));
                Act.setD_age(rs.getString(4));
                Act.setMovie_title(rs.getString(3));
                DirectorsList.add(Act);
            }
            return DirectorsList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean insertDirector(String D_id, String D_Name, String D_age, String movie_title) throws SQLException, ClassNotFoundException {
        Movie selectedMovie = findMovie(movie_title);
        Directors newDirector = new Directors();
        newDirector.setMovie_title(selectedMovie.movie_titleProperty().get());
        boolean returnState = false;
        System.out.println(selectedMovie);
        if (selectedMovie != null) {
            String query = "select * from insert_directors('" + D_id + "','" + D_Name + "','" + D_age + "','" + movie_title + "')";
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            try {
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                returnState = true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnState;
        } else return false;

    }

    public static boolean deleteDirector(Directors selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        Movie selectedMovie = findMovie(selectedItem.movie_titleProperty().get());
        boolean returnState = false;

        if (selectedMovie != null) {

            String query = "select * from delete_directors('" +
                    selectedMovie.movie_titleProperty().get() + "','" +
                    Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            try {
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                returnState = true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnState;

        } else return false;
    }

    public static Movie findMovie(String movie_title) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url, username, passwd);
        try {
            Movie mo = new Movie();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("select * from findMovie('" + movie_title + "')");
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
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Directors> filterBy(String epilogh) throws SQLException, ClassNotFoundException {
        String query = "Select * from filterdirector('" + epilogh + "')";
        if (epilogh.isEmpty()) {
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery("SELECT * From director;");
                ObservableList<Directors> DirectorsList = FXCollections.observableArrayList();
                while (rs.next()) {
                    Directors Act = new Directors();
                    Act.setD_id(rs.getString(1));
                    Act.setD_name(rs.getString(2));
                    Act.setD_age(rs.getString(4));
                    Act.setMovie_title(rs.getString(3));
                    DirectorsList.add(Act);
                }
                return DirectorsList;
            } catch (SQLException e) {
                System.out.println("Error occured while fetching the record from the DataBase" + e);
                e.printStackTrace();
                throw e;
            }
        } else {
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                    ObservableList<Directors> DirectorsList = FXCollections.observableArrayList();
                    while (rs.next()) {
                        Directors Act = new Directors();
                        Act.setD_id(rs.getString(1));
                        Act.setD_name(rs.getString(2));
                        Act.setD_age(rs.getString(4));
                        Act.setMovie_title(rs.getString(3));
                        DirectorsList.add(Act);
                    }
                    return DirectorsList;
                } catch (SQLException e) {
                    System.out.println("Error occured while fetching the record from the DataBase" + e);
                    e.printStackTrace();
                    throw e;
                }
            }
        }
    }

