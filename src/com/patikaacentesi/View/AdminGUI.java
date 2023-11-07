package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.Admin;
import com.patikaacentesi.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JTable tbl_users;
    private JPanel pnl_users;
    private JScrollPane scrl_users;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTextField fld_add_name;
    private JTextField fld_add_uname;
    private JPasswordField fld_add_pass;
    private JComboBox cmb_add_type;
    private JButton btn_add_user;
    private final Admin admin;
    private JPopupMenu userMenu;

    private DefaultTableModel mdl_users;
    private Object[] row_users;

    public AdminGUI(Admin admin) {
        this.admin = admin;
        add(wrapper);
        setSize(1000, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldin " + admin.getName());

        mdl_users = new DefaultTableModel();
        Object[] col_users = {"ID", "AD SOYAD", "KULLANICI ADI", "ŞİFRE", "KULLANICI TİPİ"};
        mdl_users.setColumnIdentifiers(col_users);
        row_users = new Object[col_users.length];
        loadUsers();
        tbl_users.setModel(mdl_users);
        tbl_users.getTableHeader().setReorderingAllowed(false);
        tbl_users.getColumnModel().getColumn(0).setMaxWidth(75);

        userMenu = new JPopupMenu();
        tbl_users.setComponentPopupMenu(userMenu);
        JMenuItem deleteUser = new JMenuItem("Sil");
        userMenu.add(deleteUser);

        tbl_users.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_users.rowAtPoint(point);
                tbl_users.setRowSelectionInterval(selected_row, selected_row);

            }
        });
        tbl_users.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int usID = Integer.parseInt(tbl_users.getValueAt(tbl_users.getSelectedRow(), 0).toString());
                    String name = tbl_users.getValueAt(tbl_users.getSelectedRow(), 1).toString();
                    String uname = tbl_users.getValueAt(tbl_users.getSelectedRow(), 2).toString();
                    String pass = tbl_users.getValueAt(tbl_users.getSelectedRow(), 3).toString();
                    if (usID <= 0 || name.length() <= 0 || uname.length() <= 0 || pass.length() <= 0) {
                        Helper.showMessage("fill");
                    } else if (User.update(usID, name, uname, pass)) {
                        Helper.showMessage("done");
                    }
                    loadUsers();
                }
            }
        });
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl_users.getSelectedRow();
                int selectedID = Integer.parseInt(tbl_users.getValueAt(selectedRow, 0).toString());
                if (Helper.confirm("sure")) {
                    User.delete(selectedID);
                    loadUsers();
                } else {
                    return;
                }
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI lGUI = new LoginGUI();
            }
        });
        btn_add_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_add_name) || Helper.isFieldEmpty(fld_add_uname) || Helper.isFieldEmpty(fld_add_pass) || cmb_add_type.getSelectedIndex() <= 0) {
                    Helper.showMessage("fill");
                } else {
                    String name = fld_add_name.getText();
                    String uname = fld_add_uname.getText();
                    String pass = fld_add_pass.getText();
                    String type = cmb_add_type.getSelectedItem().toString();
                    if (User.add(name, uname, pass, type)) {
                        Helper.showMessage("done");
                        loadUsers();
                        fld_add_name.setText(null);
                        fld_add_uname.setText(null);
                        fld_add_pass.setText(null);
                        cmb_add_type.setSelectedIndex(0);
                    }
                }
            }
        });
    }

    public void loadUsers() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_users.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj : User.getList()) {
            i = 0;
            row_users[i++] = obj.getId();
            row_users[i++] = obj.getName();
            row_users[i++] = obj.getUname();
            row_users[i++] = obj.getPass();
            row_users[i++] = obj.getType();
            mdl_users.addRow(row_users);
        }
    }

    public static void main(String[] args) {
        Helper.design();
        Admin a = new Admin();
        AdminGUI aGUI = new AdminGUI(a);
    }
}
