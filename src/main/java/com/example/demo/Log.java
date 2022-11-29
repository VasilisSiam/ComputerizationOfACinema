package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Log {

    static String driverClassName = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185280";
    static Connection dbConnection = null;
    static String username = "it185280";
    static String passwd = "kostas2000strap15";
    static Statement statement = null;
    static ResultSet rs = null;


    private StringProperty Operation,Stamp,UserID,M_Id,Movie,Producer,ReleaseDate,Critique;

    public Log(){
        this.Critique=new SimpleStringProperty();
        this.M_Id=new SimpleStringProperty();
        this.Operation=new SimpleStringProperty();
        this.Producer=new SimpleStringProperty();
        this.Stamp=new SimpleStringProperty();
        this.UserID=new SimpleStringProperty();
        this.Movie=new SimpleStringProperty();
        this.ReleaseDate=new SimpleStringProperty();
    }

    public void setOperation(String operation) {
        this.Operation.set(operation);
    }

    public void setStamp(String stamp) {
        this.Stamp.set(stamp);
    }

    public void setUserID(String userID) {
        this.UserID.set(userID);
    }

    public void setM_Id(String m_Id) {
        this.M_Id.set(m_Id);
    }

    public void setMovie(String movie) {
        this.Movie.set(movie);
    }

    public void setProducer(String producer) {
        this.Producer.set(producer);
    }

    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate.set(releaseDate);
    }

    public void setCritique(String critique) {
        this.Critique.set(critique);
    }


    public StringProperty operationProperty() {
        return Operation;
    }


    public StringProperty stampProperty() {
        return Stamp;
    }



    public StringProperty userIDProperty() {
        return UserID;
    }



    public StringProperty m_IdProperty() {
        return M_Id;
    }



    public StringProperty movieProperty() {
        return Movie;
    }



    public StringProperty producerProperty() {
        return Producer;
    }



    public StringProperty releaseDateProperty() {
        return ReleaseDate;
    }


    public StringProperty critiqueProperty() {
        return Critique;
    }

    public static ObservableList<Log> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from displaylog_mo()";
        try {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Log> LogList = getLogObjects(Log.rs);
            return LogList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }
    public static ObservableList<Log> getLogObjects(ResultSet rs) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Log> LogList = FXCollections.observableArrayList();
            while (rs.next()) {
                Log Act = new Log();
                Act.setOperation(rs.getString(1));
                Act.setStamp(rs.getString(2));
                Act.setUserID(rs.getString(3));
                Act.setM_Id(rs.getString(4));
                Act.setMovie(rs.getString(5));
                Act.setProducer(rs.getString(6));
                Act.setReleaseDate(rs.getString(7));
                Act.setCritique(rs.getString(8));
                LogList.add(Act);
            }
            return LogList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the record from the DataBase" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
