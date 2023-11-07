package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.Facility;
import com.patikaacentesi.Model.Pension;
import com.patikaacentesi.Model.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHotelFeaturesGUI extends JFrame {
    private JPanel wrapper;
    private JCheckBox cb_facility_freeautopark;
    private JCheckBox cb_facility_fitness;
    private JCheckBox cb_facility_pool;
    private JCheckBox cb_facility_freewifi;
    private JCheckBox cb_facility_concierge;
    private JCheckBox cb_facility_spa;
    private JCheckBox cb_facility_roomservice;
    private JPanel pnl_facility_specs;
    private JButton btn_add_facilityfeatures;
    private JCheckBox cb_pension_ultrainclusive;
    private JCheckBox cb_pension_fullpension;
    private JCheckBox cb_pension_inclusive;
    private JCheckBox cb_pension_breakfast;
    private JCheckBox cb_pension_halfpension;
    private JCheckBox cb_pension_onlybed;
    private JCheckBox cb_pension_allcreditwoalcohol;
    private JButton btn_add_pensionfeatures;
    private JTextField kışSezonuYazSezonuTextField;
    private JButton btn_add_hotel_season;
    private int selected_id = WorkerGUI.getSelected_hotelId();

    public AddHotelFeaturesGUI() {
        add(wrapper);
        setSize(700, 350);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);
        btn_add_facilityfeatures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cb_facility_freeautopark.isSelected() == false &&
                        cb_facility_freewifi.isSelected() == false &&
                        cb_facility_pool.isSelected() == false &&
                        cb_facility_concierge.isSelected() == false &&
                        cb_facility_fitness.isSelected() == false &&
                        cb_facility_roomservice.isSelected() == false &&
                        cb_facility_spa.isSelected() == false) {
                    Helper.showMessage("Herhangi bir seçim yapılmadı");
                } else if (selected_id <= 0) {
                    Helper.showMessage("Herhangi bir otel seçilmedi, lütfen otel menüsünden ilgili otele tıklayınız");
                    return;
                }

                int i = 0;
                if (cb_facility_freeautopark.isSelected()) {
                    String park = "Ücretsiz Otopark";
                    Facility.add(selected_id, park);
                    i += 1;
                    cb_facility_freeautopark.setSelected(false);
                }

                if (cb_facility_freewifi.isSelected()) {
                    String wifi = "Ücretsiz WiFi";
                    Facility.add(selected_id, wifi);
                    i += 1;
                    cb_facility_freewifi.setSelected(false);
                }

                if (cb_facility_fitness.isSelected()) {
                    String fitness = "Fitness Center";
                    Facility.add(selected_id, fitness);
                    i += 1;
                    cb_facility_fitness.setSelected(false);
                }

                if (cb_facility_pool.isSelected()) {
                    String pool = "Yüzme Havuzu";
                    Facility.add(selected_id, pool);
                    i += 1;
                    cb_facility_pool.setSelected(false);
                }

                if (cb_facility_concierge.isSelected()) {
                    String concierge = "Hotel Concierge";
                    Facility.add(selected_id, concierge);
                    i += 1;
                    cb_facility_concierge.setSelected(false);
                }

                if (cb_facility_spa.isSelected()) {
                    String spa = "SPA";
                    Facility.add(selected_id, spa);
                    i += 1;
                    cb_facility_spa.setSelected(false);
                }

                if (cb_facility_roomservice.isSelected()) {
                    String roomservice = "7/24 Oda Servisi";
                    Facility.add(selected_id, roomservice);
                    i += 1;
                    cb_facility_roomservice.setSelected(false);
                }


                if (i > 0) {
                    Helper.showMessage("done");
                }

            }
        });
        btn_add_pensionfeatures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cb_pension_ultrainclusive.isSelected() == false &&
                        cb_pension_halfpension.isSelected() == false &&
                        cb_pension_allcreditwoalcohol.isSelected() == false &&
                        cb_pension_inclusive.isSelected() == false &&
                        cb_pension_allcreditwoalcohol.isSelected() == false &&
                        cb_pension_breakfast.isSelected() == false &&
                        cb_pension_fullpension.isSelected() == false) {
                    Helper.showMessage("Herhangi bir seçim yapılmadı");
                } else if (selected_id <= 0) {
                    Helper.showMessage("Herhangi bir otel seçilmedi, lütfen Otel menüsünden ilgili otele tıklayınız");
                    return;
                }

                int i = 0;
                if (cb_pension_ultrainclusive.isSelected()) {
                    String ultrainclusive = "Ultra Herşey Dahil";
                    Pension.add(selected_id, ultrainclusive);
                    i += 1;
                    cb_pension_ultrainclusive.setSelected(false);
                }

                if (cb_pension_inclusive.isSelected()) {
                    String inclusive = "Herşey Dahil";
                    Pension.add(selected_id, inclusive);
                    i += 1;
                    cb_pension_inclusive.setSelected(false);
                }

                if (cb_pension_breakfast.isSelected()) {
                    String breakfast = "Oda Kahvaltı";
                    Pension.add(selected_id, breakfast);
                    i += 1;
                    cb_pension_breakfast.setSelected(false);
                }

                if (cb_pension_fullpension.isSelected()) {
                    String fullpension = "Tam Pansiyon";
                    Pension.add(selected_id, fullpension);
                    i += 1;
                    cb_pension_fullpension.setSelected(false);
                }

                if (cb_pension_halfpension.isSelected()) {
                    String halfpension = "Yarım Pansiyon";
                    Pension.add(selected_id, halfpension);
                    i += 1;
                    cb_pension_halfpension.setSelected(false);
                }

                if (cb_pension_onlybed.isSelected()) {
                    String onlybed = "Sadece Yatak";
                    Pension.add(selected_id, onlybed);
                    i += 1;
                    cb_pension_onlybed.setSelected(false);
                }

                if (cb_pension_allcreditwoalcohol.isSelected()) {
                    String allcreditwoalcohol = "Alkol Hariç Full credit";
                    Pension.add(selected_id, allcreditwoalcohol);
                    i += 1;
                    cb_pension_allcreditwoalcohol.setSelected(false);
                }

                if (i > 0) {
                    Helper.showMessage("done");
                }

            }
        });
        btn_add_hotel_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Season.getHotelID(selected_id) == selected_id){
                    Helper.showMessage("Zaten sezon atanmış");
                    return;
                } else {
                    Season.add("Kış Sezonu", selected_id, "2023-01-01", "2023-05-31");
                    Season.add("Yaz Sezonu", selected_id, "2023-06-01", "2023-12-31");
                    Helper.showMessage("done");
                }
            }
        });
    }
}
