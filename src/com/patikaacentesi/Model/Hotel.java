package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;

import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String district;
    private String address;
    private String email;
    private String phone;
    private String star;

    public Hotel(){}

    public Hotel(int id, String name, String city, String district, String address, String email, String phone, String star) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.district = district;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
    }

    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotel obj;
        try {
            Statement st = DBConnector.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setDistrict(rs.getString("district"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhone(rs.getString("phone"));
                obj.setStar(rs.getString("star"));
                hotelList.add(obj);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hotelList;
    }
    public static String getHotelName(int id){
        String name = "";
        String query = "SELECT name FROM hotels WHERE id = ?";
        Hotel obj;
        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setName(rs.getString("name"));
                name += obj.getName();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }
    public static int getHotelId(String hotel_name){
        int i = 0;
        String query = "SELECT id FROM hotels WHERE name = ?";
        Hotel obj;

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setString(1, hotel_name);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                i += obj.getId();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    public static boolean add(String name, String city, String district, String address, String email, String phone, String star) {

        String query = "INSERT INTO hotels(name,city,district,address,email,phone,star) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, district);
            pr.setString(4, address);
            pr.setString(5, email);
            pr.setString(6, phone);
            pr.setString(7, star);

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
    public static boolean delete(int id){
        String query = "DELETE FROM hotels WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, id);
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStar() {
        return star;
    }
    public void setStar(String star) {
        this.star = star;
    }
}
