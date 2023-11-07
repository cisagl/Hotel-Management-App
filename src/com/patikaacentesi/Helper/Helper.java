package com.patikaacentesi.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void design(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Windows".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                        UnsupportedLookAndFeelException e){
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenter(String ex, Dimension size){
        int point;
        switch (ex){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }
    public static void showMessage(String str){
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "İlgili alan boş bırakılamaz";
                title = "SİSTEM MESAJI";
                break;
            case "done":
                msg = "İşlem başarıyla tamamlandı";
                title = "SİSTEM MESAJI";
                break;
            case "error":
                msg = "Bir hata oluştu";
                title = "SİSTEM MESAJI";
                break;
            default:
                msg = str;
                title = "SİSTEM MESAJI";
        }
        JOptionPane.showMessageDialog(null,msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str){
        optionPageTR();
        String msg;
        switch (str){
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
                break;
            default:
                msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"SİSTEM MESAJI", JOptionPane.YES_NO_OPTION) == 0;
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static void optionPageTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

}
