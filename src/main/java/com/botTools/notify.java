package com.botTools;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

import doryan.windowsnotificationapi.fr.Notification;

public class notify {
	
	public static void main(String[] args) throws AWTException, java.net.MalformedURLException {
		try{
		    //Obtain only one instance of the SystemTray object
		    SystemTray tray = SystemTray.getSystemTray();

		    // If you want to create an icon in the system tray to preview
		    Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
		    //Alternative (if the icon is on the classpath):
		    //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

		    TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
		    //Let the system resize the image if needed
		    trayIcon.setImageAutoSize(true);
		    //Set tooltip text for the tray icon
		    trayIcon.setToolTip("System tray icon demo");
		    tray.add(trayIcon);

		    // Display info notification:
		    trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.INFO);
		    // Error:
		    // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
		    // Warning:
		    // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
		}catch(Exception ex){
		    System.err.print(ex);
		}
    }

    public void displayTray() throws AWTException, java.net.MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
    }

}
