package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Facility {
    private int id;
    private int hotel_id;
    private String features;

    public Facility(){}

    public Facility(int id, int hotel_id, String features) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.features = features;
    }

    public static ArrayList<Facility> getFacility(int hotel_id) {
        ArrayList<Facility> facilities = new ArrayList<>();
        String query = "SELECT * FROM facility WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Facility obj = new Facility();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setFeatures(rs.getString("features"));
                facilities.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return facilities;
    }

    public static boolean add(int hotel_id, String features) {

        String query = "INSERT INTO facility(hotel_id,features) VALUES (?,?)";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, features);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMessage("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean delete(int hotel_id){
        String query = "DELETE FROM facility WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getHotel_id() {
        return hotel_id;
    }
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
}
