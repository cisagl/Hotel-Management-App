package com.patikaacentesi.Model;

import com.patikaacentesi.Helper.DBConnector;
import com.patikaacentesi.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private String customer_name;
    private String customer_phone;
    private String customer_email;
    private String customer_idcard_no;
    private String customer_reservation_note;
    private String howmanydays;
    private int room_id;

    public Reservation(){}

    public Reservation(int id, String customer_name, String customer_phone, String customer_email, String customer_idcard_no, String customer_reservation_note, String howmanydays, int room_id) {
        this.id = id;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_email = customer_email;
        this.customer_idcard_no = customer_idcard_no;
        this.customer_reservation_note = customer_reservation_note;
        this.howmanydays = howmanydays;
        this.room_id = room_id;
    }

    public static ArrayList<Reservation> getList(){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        Reservation obj;
        try {
            Statement st = DBConnector.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setCustomer_name(rs.getString("customer_name"));
                obj.setCustomer_phone(rs.getString("customer_phone"));
                obj.setCustomer_email(rs.getString("customer_email"));
                obj.setCustomer_idcard_no(rs.getString("customer_idcard_no"));
                obj.setCustomer_reservation_note(rs.getString("customer_reservation_note"));
                obj.setHowmanydays(rs.getString("howmanydays"));
                obj.setRoom_id(rs.getInt("room_id"));
                reservationList.add(obj);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reservationList;
    }
    public static boolean add(String customer_name, String customer_phone, String customer_email, String customer_idcard_no, String customer_reservation_note, String howmanydays, int room_id ){
        String query = "INSERT INTO reservation(customer_name,customer_phone,customer_email,customer_idcard_no,customer_reservation_note,howmanydays,room_id) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setString(1, customer_name);
            pr.setString(2, customer_phone);
            pr.setString(3, customer_email);
            pr.setString(4, customer_idcard_no);
            pr.setString(5, customer_reservation_note);
            pr.setString(6, howmanydays);
            pr.setInt(7, room_id);
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
        String query = "DELETE FROM reservation WHERE room_id = ?";

        try {
            PreparedStatement pr = DBConnector.connect().prepareStatement(query);
            pr.setInt(1, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean deleteById(int id){
        String query = "DELETE FROM reservation WHERE id = ?";

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
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_phone() {
        return customer_phone;
    }
    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
    public String getCustomer_email() {
        return customer_email;
    }
    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
    public String getCustomer_idcard_no() {
        return customer_idcard_no;
    }
    public void setCustomer_idcard_no(String customer_idcard_no) {
        this.customer_idcard_no = customer_idcard_no;
    }
    public String getCustomer_reservation_note() {
        return customer_reservation_note;
    }
    public void setCustomer_reservation_note(String customer_reservation_note) {
        this.customer_reservation_note = customer_reservation_note;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public String getHowmanydays() {
        return howmanydays;
    }
    public void setHowmanydays(String howmanydays) {
        this.howmanydays = howmanydays;
    }
}
