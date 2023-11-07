package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;

import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {

    private int id;
    private String name;
    private int hotel_id;
    private String start_date;
    private String finish_date;


    public Season() {
    }

    public Season(int id, String name, int hotel_id, String start_date, String finish_date) {
        this.id = id;
        this.name = name;
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public static ArrayList<Season> getList() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season";
        Season obj;
        try {
            Statement st = DBConnector.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setStart_date(rs.getString("start_date"));
                obj.setFinish_date(rs.getString("finish_date"));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seasonList;
    }
    public static ArrayList<Season> getListByHotelId(int hotel_id) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        Season obj;
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setStart_date(rs.getString("start_date"));
                obj.setFinish_date(rs.getString("finish_date"));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seasonList;
    }
    public static int getHotelID(int hotel_id) {
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        int i = 0;
        Season obj;

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                i = rs.getInt("hotel_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }


    public static String getSeasonAttribute(int season_id, String attribute) {
        String value = "";
        String query = "SELECT " + attribute + " FROM season WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, season_id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                value = rs.getString(attribute);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return value;
    }
    public static String getSeasonName(int hotel_id) {
        return getSeasonAttribute(hotel_id, "name");

    }
    public static String getSeasonStartDate(int hotel_id) {
        return getSeasonAttribute(hotel_id, "start_date");
    }

    public static String getSeasonFinishDate(int hotel_id) {
        return getSeasonAttribute(hotel_id, "finish_date");
    }
    public static ArrayList<Season> getSeasonFeatures(int hotel_id) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season WHERE id = ?";
        Season obj;
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new Season();
                obj.setName(rs.getString("name"));
                obj.setStart_date(rs.getString("start_date"));
                obj.setStart_date(rs.getString("finish_date"));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seasonList;
    }
    public static int getSeasonId(int hotel_id, String name) {
        int i = 0;
        String query = "SELECT id FROM season WHERE hotel_id = ? AND name = ?";
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, name);
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


    public static boolean add(String name, int hotel_id, String start_date, String finish_date) {
        String query = "INSERT INTO season(name,hotel_id,start_date,finish_date) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, hotel_id);
            pr.setString(3, start_date);
            pr.setString(4, finish_date);
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

    public static boolean delete(int hotel_id) {
        String query = "DELETE FROM season WHERE hotel_id = ?";

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }
}
