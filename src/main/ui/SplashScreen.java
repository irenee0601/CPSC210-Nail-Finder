package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

//represents a splash screen shown during application startup
@ExcludeFromJacocoGeneratedReport
public class SplashScreen extends JFrame {

    //EFFECTS: displays a resized image in a frame for 3 seconds
    public SplashScreen() {
        super("Loading...");

        //load original image
        ImageIcon originalIcon = new ImageIcon("./data/loading.png");
        
        //resize the image
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        //add resized image to label
        JLabel label = new JLabel(scaledIcon);
        add(label);

        //remove title bar and set layout
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //wait for 10seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //close the splash screen
        dispose();
    }
}