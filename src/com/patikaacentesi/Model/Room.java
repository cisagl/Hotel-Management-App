package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Room {
    private int id;
    private String name;
    private int hotel_id;
    private int season_id;
    private int pension_id;
    private int stock;
    private int adult_price;
    private int child_price;

    public Room() {
    }

    public Room(int id, String name, int hotel_id, int season_id, int pension_id, int stock, int adult_price, int child_price) {
        this.id = id;
        this.name = name;
        this.hotel_id = hotel_id;
        this.season_id = season_id;
        this.pension_id = pension_id;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
    }
    public static ArrayList<Room> getList() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement st = DBConnector.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setPension_id(rs.getInt("pension_id"));
                obj.setStock(rs.getInt("stock"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public static ArrayList<Room> getSearchList(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        Room obj;
        try {
            Statement st = DBConnector.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setPension_id(rs.getInt("pension_id"));
                obj.setStock(rs.getInt("stock"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }


    // DEĞERLENDİRME FORMU 10 - ÇALIŞAN ODA GİRİŞİ YAPARKEN FİYATI KENDİSİ BELİRLİYOR
    // DEĞERLENDİRME FORMU 12 - ÇALIŞAN ODA GİRİŞİ YAPARKEN FİYATI KENDİSİ BELİRLİYOR
    public static boolean add(String name, int hotel_id, int season_id, int pension_id, int stock, int adult_price, int child_price) {

        String query = "INSERT INTO room(name, hotel_id, season_id, pension_id, stock, adult_price, child_price) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, hotel_id);
            pr.setInt(3, season_id);
            pr.setInt(4, pension_id);
            pr.setInt(5, stock);
            pr.setInt(6, adult_price);
            pr.setInt(7, child_price);

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
        String query = "DELETE FROM room WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean deleteById(int room_id) {
        String query = "DELETE FROM room WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean update(int stock, int id){
        String query = "UPDATE room SET stock = ? WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, stock);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static String shQuery(String searchInput, String checkInDate, String checkOutDate) {
        String query = "SELECT * FROM room AS r " +
                "INNER JOIN hotels AS h " +
                "ON r.hotel_id = h.id " +
                "INNER JOIN season AS s " +
                "ON s.id = r.season_id";

        if (!searchInput.isEmpty() || !checkInDate.isEmpty() || !checkOutDate.isEmpty()){
            query += " WHERE ";
            if (!searchInput.isEmpty()){
                query += "(h.district LIKE '%{{district}}%' OR h.name LIKE '%{{name}}%' OR h.city LIKE '%{{city}}%')";
                query = query.replace("{{district}}", searchInput);
                query = query.replace("{{name}}", searchInput);
                query = query.replace("{{city}}", searchInput);
            }
            if (!checkInDate.isEmpty()){
                if (!searchInput.isEmpty()){
                    query += " AND ";
                }
                query += "('{{checkin}}' BETWEEN s.start_date AND s.finish_date)";
                query = query.replace("{{checkin}}", checkInDate);
            }
            if (!checkOutDate.isEmpty()){
                if (!searchInput.isEmpty() || !checkInDate.isEmpty()){
                    query += " AND ";
                }
                query += "('{{checkout}}' BETWEEN s.start_date AND s.finish_date)";
                query = query.replace("{{checkout}}", checkOutDate);
            }
        }
        return query;
    }




    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public int getSeason_id() {
        return season_id;
    }
    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }
    public int getPension_id() {
        return pension_id;
    }
    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getAdult_price() {
        return adult_price;
    }
    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }
    public int getChild_price() {
        return child_price;
    }
    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }
}
