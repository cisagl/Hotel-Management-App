package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.Room;
import com.patikaacentesi.Model.RoomFeatures;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomFeaturesGUI extends JFrame{
    private JPanel wrapper;
    private JCheckBox cb_room_tv;
    private JCheckBox cb_room_console;
    private JCheckBox cb_room_minibar;
    private JCheckBox cb_room_case;
    private JCheckBox cb_room_projection;
    private JCheckBox cb_room_ac;
    private JButton btn_add_roomFeatures;
    private JComboBox cmb_room_bednumber;
    private int selected_id = WorkerGUI.getSelected_roomID();

    public AddRoomFeaturesGUI(){
        add(wrapper);
        setSize(500, 250);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);

        btn_add_roomFeatures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cb_room_tv.isSelected() == false &&
                        cb_room_minibar.isSelected() == false &&
                        cb_room_console.isSelected() == false &&
                        cb_room_case.isSelected() == false &&
                        cb_room_projection.isSelected() == false &&
                        cb_room_ac.isSelected() == false) {
                    Helper.showMessage("Yatak sayısı dışında odaya en az bir özellik verilmelidir");
                } else if (selected_id <= 0) {
                    Helper.showMessage("Herhangi bir oda seçilmedi, lütfen oda menüsünden ilgili odaya tıklayınız");
                }

                int i = 0;
                if (cb_room_tv.isSelected()){
                    String tv = "Televizyon";
                    RoomFeatures.add(selected_id, tv);
                    i += 1;
                    cb_room_tv.setSelected(false);
                }

                if (cb_room_minibar.isSelected()){
                    String minibar = "Minibar";
                    RoomFeatures.add(selected_id, minibar);
                    i += 1;
                    cb_room_minibar.setSelected(false);
                }

                if (cb_room_console.isSelected()){
                    String console = "Oyun Konsolu";
                    RoomFeatures.add(selected_id, console);
                    i += 1;
                    cb_room_console.setSelected(false);
                }

                if (cb_room_case.isSelected()){
                    String casE = "Kasa";
                    RoomFeatures.add(selected_id, casE);
                    i += 1;
                    cb_room_case.setSelected(false);
                }

                if (cb_room_projection.isSelected()){
                    String projection = "Projeksiyon";
                    RoomFeatures.add(selected_id, projection);
                    i += 1;
                    cb_room_projection.setSelected(false);
                }

                if (cb_room_ac.isSelected()){
                    String ac = "Klima";
                    RoomFeatures.add(selected_id, ac);
                    i += 1;
                    cb_room_ac.setSelected(false);
                }

                if (i > 0){
                    String bedNumber = cmb_room_bednumber.getSelectedItem().toString();
                    RoomFeatures.add(selected_id,bedNumber);
                    Helper.showMessage("done");
                    dispose();
                }
            }
        });
    }
}
