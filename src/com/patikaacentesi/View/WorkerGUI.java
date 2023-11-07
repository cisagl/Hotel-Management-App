package com.patikaacentesi.View;

import com.patikaacentesi.Helper.Config;
import com.patikaacentesi.Helper.Helper;
import com.patikaacentesi.Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// DEĞERLENDİRME FORMU 5
public class WorkerGUI extends JFrame {
    // DEĞERLENDİRME FORMU 1
    private JPanel wrapper;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTabbedPane tabbedPane1;
    private JButton btn_add_hotels;
    private JPanel pnl_hotels;
    private JScrollPane scrl_hotels;
    private JTable tbl_hotels;
    private JPanel pnl_add_hotel;
    private JButton btn_refresh_hotels;
    private JTextField fld_selected_hotel_name;
    private JCheckBox cb_facility_freeautopark;
    private JCheckBox cb_facility_freewifi;
    private JCheckBox cb_facility_pool;
    private JCheckBox cb_facility_fitness;
    private JCheckBox cb_facility_concierge;
    private JCheckBox cb_facility_spa;
    private JCheckBox cb_facility_roomservice;
    private JCheckBox cb_pension_ultrainclusive;
    private JCheckBox cb_pension_inclusive;
    private JCheckBox cb_pension_breakfast;
    private JCheckBox cb_pension_fullpension;
    private JCheckBox cb_pension_onlybed;
    private JCheckBox cb_pension_allcreditwoalcohol;
    private JButton btn_add_pensionfeatures;
    private JButton btn_add_facilityfeatures;
    private JCheckBox cb_pension_halfpension;
    private JButton btn_add_facilityAndPension;
    private JTable tbl_rooms;
    private JScrollPane scrl_rooms;
    private JPanel pnl_rooms;
    private JPanel pnl_add_hotelFeatures;
    private JTextField fld_selected_roomAndHotel_name;
    private JButton btn_add_roomFeatures;
    private JButton btn_add_room;
    private JTextField fld_search_room_name_city_district;
    private JButton btn_search_room;
    private JButton btn_delete_hotel;
    private JButton btn_delete_room;
    private JButton btn_reservation;
    private JButton btn_refresh_room;
    private JTable tbl_reservations;
    private JScrollPane scrl_reservations;
    private JPanel pnl_reservations;
    private JTextField fld_selected_reservation;
    private JButton btn_delete_reservation;
    private JButton btn_refresh_reservations;
    private JTextField fld_search_room_start_date;
    private JTextField fld_search_room_finish_date;
    private JButton btn_refresh_rooms;
    private final Worker worker;
    private static int selected_hotelId;
    private static int selected_roomID;
    private static int selected_reservation_id;

    private static int selected_room_id;
    private static String selected_room_name;
    private static String selected_room_hotel_name;
    private static String selected_room_season_name;
    private static String selected_room_season_start_date;
    private static String selected_room_season_finish_date;
    private static String selected_room_pension_name;
    private static int selected_room_stock;
    private static int selected_room_adult_price;
    private static int selected_room_child_price;
    private static int room_features_id;
    private static String startDate;
    private static String finishDate;

    private DefaultTableModel mdl_hotels;
    private Object[] row_hotels;
    private DefaultTableModel mdl_room;
    private Object[] row_room;
    private DefaultTableModel mdl_reservation;
    private Object[] row_reservation;

    public WorkerGUI(Worker worker) {
        this.worker = worker;
        add(wrapper);
        setSize(1400, 600);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hosgeldin " + worker.getName());

        // HOTELS
        mdl_hotels = new DefaultTableModel();
        Object[] col_hotels = {"ID", "OTEL ADI", "ŞEHİR", "BÖLGE", "ADRES", "EMAIL", "TEL NO", "YILDIZ"};
        mdl_hotels.setColumnIdentifiers(col_hotels);
        row_hotels = new Object[col_hotels.length];
        loadHotels();
        tbl_hotels.setModel(mdl_hotels);
        tbl_hotels.getTableHeader().setReorderingAllowed(false);
        tbl_hotels.getColumnModel().getColumn(0).setMaxWidth(75);
        // ## HOTELS


        // ROOMS
        mdl_room = new DefaultTableModel();
        Object[] col_room = {"ID", "ODA ADI", "OTEL ADI", "SEZON ADI", "SEZON BAŞLANGICI", "SEZON SONU", "PANSİYON ADI", "STOK SAYISI", "YETİŞKİN FİYATI", "ÇOCUK FİYATI"};
        mdl_room.setColumnIdentifiers(col_room);
        row_room = new Object[col_room.length];
        loadRoom();
        tbl_rooms.setModel(mdl_room);
        tbl_rooms.getTableHeader().setReorderingAllowed(false);
        tbl_rooms.getColumnModel().getColumn(0).setMaxWidth(75);
        // ## ROOMS

        // RESERVATIONS
        // DEĞERLENDİRME FORMU 18
        mdl_reservation = new DefaultTableModel();
        Object[] col_reservation = {"ID", "MÜŞTERİ ADI", "TEL NO", "EMAİL", "KİMLİK NO", "REZERVASYON NOTU", "KALACAĞI GÜN SAYISI", "ODA ID"};
        mdl_reservation.setColumnIdentifiers(col_reservation);
        row_reservation = new Object[col_reservation.length];
        loadReservation();
        tbl_reservations.setModel(mdl_reservation);
        tbl_reservations.getTableHeader().setReorderingAllowed(false);
        tbl_reservations.getColumnModel().getColumn(0).setMaxWidth(75);
        // ## RESERVATIONS

        // LISTENERS
        ListSelectionListener combinedListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getSource() == tbl_hotels.getSelectionModel()) {
                    String selected_hotel_name = tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 1).toString();
                    int selected_hotel_id = (int) tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 0);
                    selected_hotelId = selected_hotel_id;
                    fld_selected_hotel_name.setText(selected_hotel_name);
                } else if (e.getSource() == tbl_rooms.getSelectionModel()) {
                    selected_room_id = (int) tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 0);
                    selected_room_name = tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 1).toString();
                    selected_room_hotel_name = tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 2).toString();
                    selected_room_season_name = tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 3).toString();
                    selected_room_season_start_date = tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 4).toString();
                    selected_room_season_finish_date = tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 5).toString();
                    selected_room_pension_name = tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 6).toString();
                    selected_room_stock = (int) tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 7);
                    selected_room_adult_price = (int) tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 8);
                    selected_room_child_price = (int) tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 9);
                    selected_roomID = selected_room_id;
                    fld_selected_roomAndHotel_name.setText(selected_room_name + ", " + selected_room_hotel_name);
                } else if (e.getSource() == tbl_reservations.getSelectionModel()) {
                    selected_reservation_id = (int) tbl_reservations.getValueAt(tbl_reservations.getSelectedRow(), 0);
                    fld_selected_reservation.setText(String.valueOf(selected_reservation_id));
                }
            }
        };
        tbl_hotels.getSelectionModel().addListSelectionListener(combinedListener);
        tbl_rooms.getSelectionModel().addListSelectionListener(combinedListener);
        tbl_reservations.getSelectionModel().addListSelectionListener(combinedListener);
        // ## LISTENERS


        // BUTTONS
        // DEĞERLENDİRME FORMU 9
        btn_add_hotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelRegistrationGUI hrGUI = new HotelRegistrationGUI();
            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI lGUI = new LoginGUI();
            }
        });
        btn_refresh_hotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbl_hotels.getSelectionModel().removeListSelectionListener(combinedListener);
                try {
                    loadHotels();
                    Helper.showMessage("Oteller yenilendi");
                } catch (Exception exception) {
                    Helper.showMessage("error");
                    dispose();
                    LoginGUI lg = new LoginGUI();
                }
                tbl_hotels.getSelectionModel().addListSelectionListener(combinedListener);
            }
        });
        btn_refresh_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbl_rooms.getSelectionModel().removeListSelectionListener(combinedListener);
                try {
                    loadRoom();
                    Helper.showMessage("Odalar yenilendi");
                } catch (Exception exception) {
                    Helper.showMessage("error");
                    dispose();
                }
                tbl_rooms.getSelectionModel().addListSelectionListener(combinedListener);
            }
        });
        btn_refresh_reservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbl_reservations.getSelectionModel().removeListSelectionListener(combinedListener);
                try {
                    loadReservation();
                    Helper.showMessage("Rezervasyonlar yenilendi");
                } catch (Exception exception) {
                    Helper.showMessage("error");
                    dispose();
                }
                tbl_reservations.getSelectionModel().addListSelectionListener(combinedListener);
            }
        });
        btn_add_facilityAndPension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Helper.isFieldEmpty(fld_selected_hotel_name)) {
                    Helper.showMessage("Lütfen bir otel seçiniz");
                    return;
                }
                AddHotelFeaturesGUI afGUI = new AddHotelFeaturesGUI();
            }
        });
        // DEĞERLENDİRME FORMU 11
        btn_add_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Season.getHotelID(selected_hotelId) != selected_hotelId) {
                    Helper.showMessage("Lütfen otele sezon atayınız");
                    return;
                }

                if (Helper.isFieldEmpty(fld_selected_hotel_name)) {
                    Helper.showMessage("Lütfen bir otel seçiniz");
                    return;
                }
                AddRoomGUI arGUI = new AddRoomGUI();
            }
        });
        btn_add_roomFeatures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean hasRoomFeatures = false;

                ArrayList<RoomFeatures> roomftrs = new ArrayList<>();
                for (RoomFeatures rft : RoomFeatures.getFeatures(selected_roomID)) {
                    room_features_id = rft.getId();
                    hasRoomFeatures = true;
                }

                if (Helper.isFieldEmpty(fld_selected_roomAndHotel_name)) {
                    Helper.showMessage("Lütfen bir oda seçiniz");
                    return;
                } else if (hasRoomFeatures) {
                    Helper.showMessage("Bu odaya zaten özellik atanmış");
                    return;
                } else {

                    AddRoomFeaturesGUI arfGUI = new AddRoomFeaturesGUI();
                }
            }
        });
        // DEĞERLENDİRME FORMU 13
        btn_search_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    tbl_rooms.getSelectionModel().removeListSelectionListener(combinedListener);
                    String srch = fld_search_room_name_city_district.getText();
                    String st = fld_search_room_start_date.getText();
                    if (!st.isEmpty()){
                        st = LocalDate.parse(st, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
                    }
                    String en = fld_search_room_finish_date.getText();
                    if (!en.isEmpty()){
                        en = LocalDate.parse(en, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
                    }

                    String query = Room.shQuery(srch, st, en);
                    List<Room> rooms = Room.getSearchList(query);
                    loadRoomBySearch(rooms);
                } catch (Exception ex){
                    System.out.println(ex.getMessage());

                }
            }
        });
        btn_reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Helper.isFieldEmpty(fld_selected_roomAndHotel_name)) {
                    Helper.showMessage("Rezerve edebilmek için önce odayı seçmelisiniz");
                    return;
                } else if (getSelected_room_stock() < 1) {
                    Helper.showMessage("Rezeve etmek için seçilen odanın stoğu yok");
                    return;
                } else if (RoomFeatures.checkRoomFeatures(selected_room_id)) {
                    Helper.showMessage("Önce odanın özelliklerini tanımlayınız");
                    return;
                } else {
                    ReservationGUI rGUI = new ReservationGUI();
                }
            }
        });
        btn_delete_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Helper.isFieldEmpty(fld_selected_hotel_name)) {
                    Helper.showMessage("Lütfen bir otel seçiniz");
                    return;
                } else {
                    if (Helper.confirm("Eğer oteli silerseniz otel ile ilişkili bütün veriler silinecektir")) {
                        tbl_hotels.getSelectionModel().removeListSelectionListener(combinedListener);
                        Hotel.delete(selected_hotelId);
                        Pension.delete(selected_hotelId);
                        Facility.delete(selected_hotelId);
                        Room.delete(selected_hotelId);
                        Season.delete(selected_hotelId);
                        Helper.showMessage("done");
                        loadHotels();
                    }
                }
            }
        });
        btn_delete_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_selected_roomAndHotel_name)) {
                    Helper.showMessage("Lütfen bir oda seçiniz");
                    return;
                } else {
                    if (Helper.confirm("Eğer odayı silerseniz oda ile ilişkili bütün veriler silinecektir")) {
                        tbl_rooms.getSelectionModel().removeListSelectionListener(combinedListener);
                        Room.deleteById(selected_roomID);
                        RoomFeatures.delete(selected_roomID);
                        Reservation.delete(selected_roomID);
                        Helper.showMessage("done");
                        loadRoom();
                    }
                }
            }
        });
        btn_delete_reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_selected_reservation)) {
                    Helper.showMessage("fill");
                } else {
                    if (Helper.confirm("Rezervasyonu silmek istediğinize emin misiniz?")) {
                        tbl_reservations.getSelectionModel().removeListSelectionListener(combinedListener);
                        Reservation.deleteById(selected_reservation_id);
                        Helper.showMessage("done");
                        loadReservation();
                    }
                }
            }
        });
        // ## BUTTONS
    }

    public void loadHotels() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotels.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()) {
            i = 0;
            row_hotels[i++] = obj.getId();
            row_hotels[i++] = obj.getName();
            row_hotels[i++] = obj.getCity();
            row_hotels[i++] = obj.getDistrict();
            row_hotels[i++] = obj.getAddress();
            row_hotels[i++] = obj.getEmail();
            row_hotels[i++] = obj.getPhone();
            row_hotels[i++] = obj.getStar();
            mdl_hotels.addRow(row_hotels);
        }
    }

    public void loadRoom() {
        try {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_rooms.getModel();
            clearModel.setRowCount(0);
            int i;
            for (Room obj : Room.getList()) {
                i = 0;
                row_room[i++] = obj.getId();
                row_room[i++] = obj.getName();
                row_room[i++] = Hotel.getHotelName(obj.getHotel_id());
                row_room[i++] = Season.getSeasonName(obj.getSeason_id());
                row_room[i++] = Season.getSeasonStartDate(obj.getSeason_id());
                row_room[i++] = Season.getSeasonFinishDate(obj.getSeason_id());
                row_room[i++] = Pension.getPensionName(obj.getPension_id());
                row_room[i++] = obj.getStock();
                row_room[i++] = obj.getAdult_price();
                row_room[i++] = obj.getChild_price();
                mdl_room.addRow(row_room);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // DEĞERLENDİRME FORMU 14
    public void loadRoomBySearch(List<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rooms.getModel();
        clearModel.setRowCount(0);

        for (Room obj : list) {
            int i = 0;
            row_room[i++] = obj.getId();
            row_room[i++] = obj.getName();
            row_room[i++] = Hotel.getHotelName(obj.getHotel_id());
            row_room[i++] = Season.getSeasonName(obj.getSeason_id());
            row_room[i++] = Season.getSeasonStartDate(obj.getSeason_id());
            row_room[i++] = Season.getSeasonFinishDate(obj.getSeason_id());
            row_room[i++] = Pension.getPensionName(obj.getPension_id());
            row_room[i++] = obj.getStock();
            row_room[i++] = obj.getAdult_price();
            row_room[i++] = obj.getChild_price();
            mdl_room.addRow(row_room);
        }
    }

    public void loadReservation() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservations.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Reservation obj : Reservation.getList()) {
            i = 0;
            row_reservation[i++] = obj.getId();
            row_reservation[i++] = obj.getCustomer_name();
            row_reservation[i++] = obj.getCustomer_phone();
            row_reservation[i++] = obj.getCustomer_email();
            row_reservation[i++] = obj.getCustomer_idcard_no();
            row_reservation[i++] = obj.getCustomer_reservation_note();
            row_reservation[i++] = obj.getHowmanydays();
            row_reservation[i++] = obj.getRoom_id();
            mdl_reservation.addRow(row_reservation);
        }
    }


    // GETTER SETTER
    public static int getSelected_hotelId() {
        return selected_hotelId;
    }

    public static void setSelected_hotelId(int selected_hotelId) {
        WorkerGUI.selected_hotelId = selected_hotelId;
    }

    public static int getSelected_roomID() {
        return selected_roomID;
    }

    public static void setSelected_roomID(int selected_roomID) {
        WorkerGUI.selected_roomID = selected_roomID;
    }

    public static int getSelected_room_id() {
        return selected_room_id;
    }

    public static void setSelected_room_id(int selected_room_id) {
        WorkerGUI.selected_room_id = selected_room_id;
    }

    public static String getSelected_room_name() {
        return selected_room_name;
    }

    public static void setSelected_room_name(String selected_room_name) {
        WorkerGUI.selected_room_name = selected_room_name;
    }

    public static String getSelected_room_hotel_name() {
        return selected_room_hotel_name;
    }

    public static void setSelected_room_hotel_name(String selected_room_hotel_name) {
        WorkerGUI.selected_room_hotel_name = selected_room_hotel_name;
    }

    public static String getSelected_room_season_name() {
        return selected_room_season_name;
    }

    public static void setSelected_room_season_name(String selected_room_season_name) {
        WorkerGUI.selected_room_season_name = selected_room_season_name;
    }

    public static String getSelected_room_season_start_date() {
        return selected_room_season_start_date;
    }

    public static void setSelected_room_season_start_date(String selected_room_season_start_date) {
        WorkerGUI.selected_room_season_start_date = selected_room_season_start_date;
    }

    public static String getSelected_room_season_finish_date() {
        return selected_room_season_finish_date;
    }

    public static void setSelected_room_season_finish_date(String selected_room_season_finish_date) {
        WorkerGUI.selected_room_season_finish_date = selected_room_season_finish_date;
    }

    public static String getSelected_room_pension_name() {
        return selected_room_pension_name;
    }

    public static void setSelected_room_pension_name(String selected_room_pension_name) {
        WorkerGUI.selected_room_pension_name = selected_room_pension_name;
    }

    public static int getSelected_room_stock() {
        return selected_room_stock;
    }

    public static void setSelected_room_stock(int selected_room_stock) {
        WorkerGUI.selected_room_stock = selected_room_stock;
    }

    public static int getSelected_room_adult_price() {
        return selected_room_adult_price;
    }

    public static void setSelected_room_adult_price(int selected_room_adult_price) {
        WorkerGUI.selected_room_adult_price = selected_room_adult_price;
    }

    public static int getSelected_room_child_price() {
        return selected_room_child_price;
    }

    public static void setSelected_room_child_price(int selected_room_child_price) {
        WorkerGUI.selected_room_child_price = selected_room_child_price;
    }

    // ## GETTER SETTER
}
