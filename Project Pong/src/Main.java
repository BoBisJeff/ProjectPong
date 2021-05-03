import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Main {
    static JFrame mainWindow;
    static JComponent component;
    static Graphics2D graphics2D;
    static Ball ball;

    public static void main(String[] args){
        //create Window
        System.out.println("Start");
        createWindow();

        //Making the ball
        ball = new Ball();
        //ball.beginBall();


    }

    public static void createWindow(){
        //Make main frame
        mainWindow = new JFrame("Main Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        //mainWindow.pack();
        mainWindow.setSize(1200, 600);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        System.out.println("Window Displayed");



    }

}


