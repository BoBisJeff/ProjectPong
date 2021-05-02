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
        NewPanel panel = new NewPanel(100.0f, 100.0f);
        mainWindow.getContentPane().add(panel);
        mainWindow.setVisible(true);

        //ball.makeBall();


    }

    public static void createWindow(){
        //Make main frame
        mainWindow = new JFrame("Main Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        //mainWindow.pack();
        mainWindow.setSize(1200, 600);
        System.out.println("Window Displayed");


    }

}

//https://stackoverflow.com/questions/8201705/java-awt-graphics-change-color-after-drawing
class NewPanel extends JPanel {
    // Set background in constructor.
    //can change these values to move it
    float x;
    float y;

    public NewPanel (float x, float y)
    {
        this.setBackground (Color.cyan);
        this.x = x;
        this.y = y;
    }

    // Override paintComponent(): - this is an exisiting function that becomes changed by this addition
    public void paintComponent (Graphics g, float x, float y)
    {
        Graphics2D graphics2d = (Graphics2D)g;
        super.paintComponent(g);
        Shape ball = new Ellipse2D.Float(x, y, 100.0f, 100.0f);
        graphics2d.draw(ball);
        graphics2d.setColor(Color.RED);
        graphics2d.fill(ball);
        System.out.println ("Inside paintComponent");
    }

}
