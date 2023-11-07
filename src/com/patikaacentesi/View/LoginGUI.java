package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.Admin;
import com.patikaacentesi.Model.User;
import com.patikaacentesi.Model.Worker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_uname;
    private JTextField fld_pass;
    private JButton btn_login;

    public LoginGUI(){
        add(wrapper);
        setSize(250, 250);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);



        // DEĞERLENDİRME FORMU 8
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_pass)){
                    Helper.showMessage("fill");
                } else {
                    User user = User.getFetch(fld_uname.getText(), fld_pass.getText());
                    if (user == null){
                        Helper.showMessage("Kullanıcı bulunamadı");
                    } else {
                        switch (user.getType()){
                            case "admin":
                                AdminGUI adGUI = new AdminGUI((Admin) user);
                                break;
                            case "worker":
                                WorkerGUI wGUI = new WorkerGUI((Worker) user);
                                break;
                        }
                        dispose();
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        Helper.design();
        LoginGUI login = new LoginGUI();
    }
}
