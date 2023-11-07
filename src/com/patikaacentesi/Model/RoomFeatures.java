package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomFeatures {
    private int id;
    private int room_id;
    private String features;

    public RoomFeatures(){}

    public RoomFeatures(int id, int room_id, String features) {
        this.id = id;
        this.room_id = room_id;
        this.features = features;
    }

    public static boolean checkRoomFeatures(int room_id) {
        ArrayList<RoomFeatures> roomFeatures = getFeatures(room_id);

        for (RoomFeatures feature : roomFeatures) {
            String featuresStr = feature.getFeatures();
            String[] featureArray = featuresStr.split(",");

            for (String featureValue : featureArray) {
                String trimmedValue = featureValue.trim();

                if (!isValidFeature(trimmedValue)) {
                    return false;
                }
            }
        }

        return true;
    }
    public static boolean isValidFeature(String featureValue) {
        try {
            int value = Integer.parseInt(featureValue);
            return value >= 1 && value <= 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public static ArrayList<RoomFeatures> getFeatures(int room_id) {
        ArrayList<RoomFeatures> roomF = new ArrayList<>();
        String query = "SELECT * FROM roomfeatures WHERE room_id = ?";
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, room_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                RoomFeatures obj = new RoomFeatures();
                obj.setId(rs.getInt("id"));
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setFeatures(rs.getString("features"));
                roomF.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomF;
    }
    public static boolean add(int room_id, String features){
        String query = "INSERT INTO roomfeatures(room_id,features) VALUES (?,?)";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, room_id);
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
    public static boolean delete(int room_id){
        String query = "DELETE FROM roomfeatures WHERE room_id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, room_id);
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
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
}
