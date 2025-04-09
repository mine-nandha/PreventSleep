package org.example;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) throws AWTException {
        if(!SystemTray.isSupported()){
            System.out.println("System tray is not supported.");
            return;
        }

        BufferedImage icon = new BufferedImage(16,16, BufferedImage.TYPE_INT_ARGB);
        TrayIcon trayIcon = new TrayIcon(icon, "Stay Awake");
        PopupMenu popupMenu = new PopupMenu();
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> {
            SystemTray.getSystemTray().remove(trayIcon);
            System.exit(0);
        });
        popupMenu.add(exitItem);
        trayIcon.setPopupMenu(popupMenu);
        SystemTray.getSystemTray().add(trayIcon);

        Robot robot = new Robot();
        new Thread(()->{
            try{
                while(true){
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    Thread.sleep(60000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}