package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ActorDAO {

    static String driverClassName = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280";
    static Connection dbConnection = null;
    static String username = "it185280";
    static String passwd = "kostas2000strap15";
    static Statement statement = null;
    static ResultSet rs = null;


    public static ObservableList<Actor> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from displayactor()";
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Actor> ActorList = getActorObjects(ActorDAO.rs);
            return ActorList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Actor> getActorObjects(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Actor> ActorList = FXCollections.observableArrayList();
            while (rs.next()) {
                Actor Act = new Actor();
                Act.setId(rs.getString(1));
                Act.setActor_name(rs.getString(2));
                Act.setGender(rs.getString(3));
                Act.setAge(rs.getString(4));
                Act.setMovie(rs.getString(5));
                ActorList.add(Act);
            }
            return ActorList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
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



    public static boolean  insertActor(String a_id, String actor_Name, String gender, String age, String movie_title) throws SQLException, ClassNotFoundException {
        Movie selectedMovie = findMovie(movie_title);
        Actor newActor = new Actor();
        newActor.setMovie(selectedMovie.movie_titleProperty().get());
        boolean returnState = false;
        System.out.println(selectedMovie);
        if(selectedMovie != null){
            String query = "select * from insert_actors('"+a_id+"','"+actor_Name+"','"+gender+"','"+age+"','"+movie_title+"')";
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

    public static boolean deleteActor( Actor selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        Movie selectedMovie  = findMovie(selectedItem.getId()) ;
        boolean returnState = false;

        if(selectedMovie != null){

            String query = "select * from delete_actor('"+
                    selectedMovie.getId()+"','"+
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

    public static ObservableList<Actor> filterBy(String epilogh) throws SQLException,ClassNotFoundException {
        String query="Select * from filteractor('" + epilogh + "')";
        if(epilogh.equals("Male")){
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                ObservableList ActorList=FXCollections.observableArrayList();
                while (rs.next()) {
                    Actor Act = new Actor();
                    Act.setId(rs.getString(1));
                    Act.setActor_name(rs.getString(2));
                    Act.setGender(rs.getString(3));
                    Act.setAge(rs.getString(4));
                    Act.setMovie(rs.getString(5));
                    ActorList.add(Act);
                }
                return ActorList;
            } catch (SQLException e) {
                System.out.println("Error occured while fetching the record from the DataBase" + e);
                e.printStackTrace();
                throw e;
            }
        } else if (epilogh.equals("Female")) {
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                ObservableList ActorList=FXCollections.observableArrayList();
                while (rs.next()) {
                    Actor Act = new Actor();
                    Act.setId(rs.getString(1));
                    Act.setActor_name(rs.getString(2));
                    Act.setGender(rs.getString(3));
                    Act.setAge(rs.getString(4));
                    Act.setMovie(rs.getString(5));
                    ActorList.add(Act);
                }
                return ActorList;
            } catch (SQLException e) {
                System.out.println("Error occured while fetching the record from the DataBase" + e);
                e.printStackTrace();
                throw e;
            }
        }else{
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery("select * from actor;");
                ObservableList ActorList=FXCollections.observableArrayList();
                while (rs.next()) {
                    Actor Act = new Actor();
                    Act.setId(rs.getString(1));
                    Act.setActor_name(rs.getString(2));
                    Act.setGender(rs.getString(3));
                    Act.setAge(rs.getString(4));
                    Act.setMovie(rs.getString(5));
                    ActorList.add(Act);
                }
                return ActorList;
            } catch (SQLException e) {
                System.out.println("Error occured while fetching the record from the DataBase" + e);
                e.printStackTrace();
                throw e;
            }
        }

    }


}

