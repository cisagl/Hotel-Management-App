package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.Hotel;
import com.patikaacentesi.Model.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelRegistrationGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_name;
    private JTextField fld_city;
    private JTextField fld_district;
    private JTextField fld_address;
    private JTextField fld_email;
    private JButton btn_add_hotel;
    private JTextField fld_phone;
    private JTextField fld_star;
    private JButton btn_logout;
    private JComboBox cmb_stars;

    public HotelRegistrationGUI() {
        add(wrapper);
        setSize(600, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        btn_add_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_city) || Helper.isFieldEmpty(fld_district) || Helper.isFieldEmpty(fld_address) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_phone)) {
                    Helper.showMessage("fill");
                } else {
                    String name = fld_name.getText();
                    String city = fld_city.getText();
                    String district = fld_district.getText();
                    String address = fld_address.getText();
                    String email = fld_email.getText();
                    String phone = fld_phone.getText();
                    String star = (String) cmb_stars.getSelectedItem();
                    if (Hotel.add(name, city, district, address, email, phone, star)) {
                        Helper.showMessage("done");
                    } else {
                        Helper.showMessage("error");
                    }
                    fld_name.setText(null);
                    fld_city.setText(null);
                    fld_district.setText(null);
                    fld_address.setText(null);
                    fld_email.setText(null);
                    fld_phone.setText(null);
                    cmb_stars.setSelectedIndex(0);
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
