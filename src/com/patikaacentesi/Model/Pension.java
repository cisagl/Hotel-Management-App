package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Pension {

    private int id;
    private int hotel_id;
    private String features;

    public Pension() {
    }

    public Pension(int id, int hotel_id, String features) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.features = features;
    }

    public static ArrayList<Pension> getList() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM pension";
        Pension obj;
        try {
            Statement st = DBConnector.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Pension();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setFeatures(rs.getString("features"));
                pensionList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pensionList;
    }
    public static ArrayList<Pension> getList(int hotel_id) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM pension WHERE hotel_id = ?";
        Pension obj;
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Pension();
                obj.setId(rs.getInt("id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setFeatures(rs.getString("features"));
                pensionList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pensionList;
    }
    public static String getPensionName(int hotel_id) {
        String name = "";
        String query = "SELECT * FROM pension WHERE id = ?";
        Pension obj;
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Pension();
                obj.setFeatures(rs.getString("features"));
                name += obj.getFeatures();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }
    public static boolean add(int hotel_id, String features) {

        String query = "INSERT INTO pension(hotel_id,features) VALUES (?,?)";

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
        String query = "DELETE FROM pension WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static int getPensionId(int hotel_id, String features) {
        int i = 0;
        String query = "SELECT id FROM pension WHERE hotel_id = ? AND features = ?";
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, features);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                i += rs.getInt("id");
                return i;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
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
