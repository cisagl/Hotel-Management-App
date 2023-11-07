package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.Pension;
import com.patikaacentesi.Model.Room;
import com.patikaacentesi.Model.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_add_room_name;
    private JTextField fld_add_adult_price;
    private JButton btn_add_room;
    private JComboBox cmb_add_pension_spec;
    private JComboBox cmb_add_season_range;
    private JTextField fld_add_stock;
    private JTextField fld_add_child_price;
    private int hotel_id = WorkerGUI.getSelected_hotelId();

    public AddRoomGUI(){
        add(wrapper);
        setSize(400, 400);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        loadSeason();
        loadPension();

        btn_add_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int season_id = -1;
                int pension_id = -1;

                if (Helper.isFieldEmpty(fld_add_room_name) || Helper.isFieldEmpty(fld_add_stock) || Helper.isFieldEmpty(fld_add_adult_price) ||Helper.isFieldEmpty(fld_add_child_price)){
                    Helper.showMessage("fill");
                    return;
                } else {
                    String name = fld_add_room_name.getText();
                    String cmbSeason = (String) cmb_add_season_range.getSelectedItem();
                    String cmbPension = (String) cmb_add_pension_spec.getSelectedItem();
                    int stock = Integer.parseInt(fld_add_stock.getText());
                    int adult_price = Integer.parseInt(fld_add_adult_price.getText());
                    int child_price = Integer.parseInt(fld_add_child_price.getText());

                    season_id = Season.getSeasonId(hotel_id, cmbSeason);
                    pension_id = Pension.getPensionId(hotel_id, cmbPension);

                    Room.add(name,hotel_id,season_id,pension_id,stock,adult_price,child_price);
                    Helper.showMessage("done");
                    dispose();
                }
            }
        });
    }


    public void loadSeason(){
        cmb_add_season_range.removeAllItems();
        for (Season obj : Season.getListByHotelId(hotel_id)) {
            String seasonDate = obj.getName();
            cmb_add_season_range.addItem(seasonDate);
        }
    }

    public void loadPension(){
        cmb_add_pension_spec.removeAllItems();
        for (Pension obj : Pension.getList(hotel_id)) {
            String seasonDate = obj.getFeatures();
            cmb_add_pension_spec.addItem(seasonDate);
        }
    }
}
