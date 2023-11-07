package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_room_specs;
    private JPanel pnl_hotel_features;
    private JPanel pnl_room_features;
    private JPanel pnl_customer_infos;
    private JPanel pnl_prices;
    private JTextField fld_room_name;
    private JTextField fld_hotel_name;
    private JTextField fld_season_name;
    private JTextField fld_season_start_date;
    private JTextField fld_season_finish_date;
    private JTextField fld_park;
    private JTextField fld_wifi;
    private JTextField fld_pool;
    private JTextField fld_npc;
    private JTextField fld_spa;
    private JTextField fld_room_service;
    private JTextField fld_ac;
    private JTextField fld_proj;
    private JTextField fld_case;
    private JTextField fld_console;
    private JTextField fld_minibar;
    private JTextField fld_tv;
    private JTextField fld_customer_name;
    private JTextField fld_customer_tel;
    private JTextField fld_customer_mail;
    private JTextField fld_idcard_no;
    private JButton btn_add_customer;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_bed_number;
    private JTextField fld_stock;
    private JTextArea txt_reservation_note;
    private JTextField fld_howmanydays;
    private JButton btn_reservation_done;
    private JComboBox cmb_reservation_number_of_adults;
    private JComboBox cmb_reservation_number_of_children;
    private JTextField fld_pension;
    private JComboBox cmb_howmanydays;
    private JTextField fld_total_price;
    private JButton btn_calculate;

    private int room_id = WorkerGUI.getSelected_room_id();
    private String room_name = WorkerGUI.getSelected_room_name();
    private String hotel_name = WorkerGUI.getSelected_room_hotel_name();
    private String season_name = WorkerGUI.getSelected_room_season_name();
    private String start_date = WorkerGUI.getSelected_room_season_start_date();
    private String finish_date = WorkerGUI.getSelected_room_season_finish_date();
    private String pension_name = WorkerGUI.getSelected_room_pension_name();
    private int stock = WorkerGUI.getSelected_room_stock();
    private int adult_price = WorkerGUI.getSelected_room_adult_price();
    private int child_price = WorkerGUI.getSelected_room_child_price();
    private int hotel_id = Hotel.getHotelId(hotel_name);

    private int adultTotal;
    private int childTotal;
    private int dys;

    public ReservationGUI() {
        add(wrapper);
        setSize(1280, 720);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);

        // FACILITY FEATURES
        ArrayList<Facility> facility = Facility.getFacility(hotel_id);
        for (Facility f : facility) {
            String facilityFeatures = f.getFeatures();
            switch (facilityFeatures) {
                case "Ücretsiz Otopark":
                    fld_park.setText("Var");
                    break;
                case "Ücretsiz WiFi":
                    fld_wifi.setText("Var");
                    break;
                case "SPA":
                    fld_spa.setText("Var");
                    break;
                case "Hotel Concierge":
                    fld_npc.setText("Var");
                    break;
                case "Yüzme Havuzu":
                    fld_pool.setText("Var");
                    break;
                case "7/24 Oda Servisi":
                    fld_room_service.setText("Var");
                    break;
                default:
                    break;
            }
        }
        // ## FACILITY FEATURES

        // ROOM FEATURES
        ArrayList<RoomFeatures> roomFeatures = RoomFeatures.getFeatures(room_id);
        for (RoomFeatures rF : roomFeatures) {
            String rFeatures = rF.getFeatures();
            if (rFeatures.equals("1") || rFeatures.equals("2") || rFeatures.equals("3") || rFeatures.equals("4") || rFeatures.equals("5") || rFeatures.equals("6") || rFeatures.equals("7") || rFeatures.equals("8") || rFeatures.equals("9")){
                fld_bed_number.setText(rFeatures);
            }

            switch (rFeatures){
                case "Televizyon":
                    fld_tv.setText("Var");
                    break;
                case "Minibar":
                    fld_minibar.setText("Var");
                    break;
                case "Oyun Konsolu":
                    fld_console.setText("Var");
                    break;
                case "Kasa":
                    fld_case.setText("Var");
                    break;
                case "Projeksiyon":
                    fld_proj.setText("Var");
                    break;
                case "Klima":
                    fld_ac.setText("Var");
                    break;
                default:
                    break;
            }

        }
        // ## ROOM FEATURES


        fld_room_name.setText(room_name);
        fld_hotel_name.setText(hotel_name);
        fld_season_name.setText(season_name);
        fld_season_start_date.setText(start_date);
        fld_season_finish_date.setText(finish_date);
        fld_pension.setText(pension_name);
        fld_stock.setText(String.valueOf(stock));

        // DEĞERLENDİRME FORMU 15
        cmb_reservation_number_of_adults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected_adult_number = cmb_reservation_number_of_adults.getSelectedIndex();
                int a = adult_price * selected_adult_number;
                adultTotal = a;
                fld_adult_price.setText(String.valueOf(a));
            }
        });
        cmb_reservation_number_of_children.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected_child_number = cmb_reservation_number_of_children.getSelectedIndex();
                int c = child_price * selected_child_number;
                childTotal = c;
                fld_child_price.setText(String.valueOf(c));
            }
        });
        cmb_howmanydays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int days = cmb_howmanydays.getSelectedIndex() + 1;
                dys = days;
            }
        });



        txt_reservation_note.setText("Rezervasyon notu yok");

        btn_add_customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customer_name = fld_customer_name.getText();
                String customer_phone = fld_customer_tel.getText();
                String customer_email = fld_customer_mail.getText();
                String customer_idcard_no = fld_idcard_no.getText();
                String customer_reservation_note = txt_reservation_note.getText();
                String howmanydays = (String) cmb_howmanydays.getSelectedItem();

                int digitCount = 0;
                for (char c : customer_idcard_no.toCharArray()) {
                    if (Character.isDigit(c)) {
                        digitCount++;
                    }
                }
                if (digitCount < 11) {
                    Helper.showMessage("Kimlik Numarası 11 haneli olmalıdır");
                }

                if (Helper.isFieldEmpty(fld_customer_name) || Helper.isFieldEmpty(fld_customer_tel) || Helper.isFieldEmpty(fld_customer_mail)  || customer_reservation_note.isEmpty()){
                    Helper.showMessage("fill");
                    return;
                } else {
                    // DEĞERLENDİRME FORMU 16
                    Reservation.add(customer_name,customer_phone,customer_email,customer_idcard_no,customer_reservation_note, howmanydays,room_id);
                    Helper.showMessage("done");
                    fld_customer_name.setText(null);
                    fld_customer_tel.setText(null);
                    fld_customer_mail.setText(null);
                    fld_idcard_no.setText(null);
                    cmb_howmanydays.setSelectedIndex(0);
                    txt_reservation_note.setText("Rezervasyon notu yok");
                }
            }
        });

        // DEĞERLENDİRME FORMU 17
        btn_reservation_done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newStock = stock - 1;
                if (Helper.confirm("Bütün işlemler tamamlandıysa odanın stoğu azaltılacak")){
                    Room.update(newStock, room_id);
                    dispose();
                }
            }
        });
        btn_calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sum = adultTotal + childTotal;
                int total = dys * sum;
                fld_total_price.setText(String.valueOf(total));
            }
        });
    }

    private void createUIComponents() throws ParseException {
       this.fld_idcard_no = new JFormattedTextField(new MaskFormatter("###########"));
    }
}