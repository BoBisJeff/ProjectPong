import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Ball {
    static GamePanel mainPanel;
    /*
    static float ballX;
    static float ballY;
    static float box1X;
    static float box1Y;
    static float box2X;
    static float box2Y;*/

    static float ballX = 594.0f;
    static float ballY = 297.5f;
    static float box1X = 100.0f;
    static float box1Y = 267.5f;
    static float box2X = 1094.0f;
    static float box2Y = 267.5f;





    static Shape ball;
    static Shape box1;
    static Shape box2;
    static Area ballArea;
    static Area box1Area;
    static Area box2Area;
    static Area walls;

    float panelHeight = 1194.0f;
    float panelWidth = 565.0f;

    //speed multiplier
    float ballSpeed = 1.0f;
    //store movement of ball
    float ballMoveX = 0.1f;
    float ballMoveY = 0.1f;



    public Ball() {
        System.out.println("Ball made");

        //setting all starting positions
        /*
        ballX = 594.0f;
        ballY = 297.5f;
        box1X = 100.0f;
        box1Y = 267.5f;
        box2X = 1094.0f;
        box2Y = 267.5f;*/

        ball = new Ellipse2D.Float(ballX, ballY, 30.0f, 30.0f);
        box1 = new Rectangle2D.Float(box1X, box1Y, 30.0f, 100.0f);
        box2 = new Rectangle2D.Float(box2X, box2Y, 30.0f, 100.0f);

        //Areas for each thing
        ballArea = new Area(ball);
        box1Area = new Area(box1);
        box2Area = new Area(box2);
        walls = new Area(new Rectangle.Float(0,0, panelHeight, panelWidth));

        mainPanel = new GamePanel(ball, box1, box2, ballX, ballY);
        Main.mainWindow.getContentPane().add(mainPanel);
        Main.mainWindow.setVisible(true);


        beginBall();

    }



    public void beginBall(){
        //This is where the runnable will be initiated
        for (int i = 0; i < 400; i++){

            ballX += 1.0f;
            //ballY += 0.1f;
            //for some reason repainting is not moving the ball.
            mainPanel.repaint();
            System.out.println(ballX);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("timer failed");
            }
        }
    }

    //commented out for testing
    /*
    public void ballMove(){


        ballX += ballMoveX * ballSpeed;
        ballY += ballMoveY * ballSpeed;
        mainPanel.repaint();
    }

    //The ball's movement
    Runnable runBall = new Runnable(){
        @Override
        public void run(){
            ballMove();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("timer failed");
            }

        }
    };*/

    public boolean isCollision(Area a1, Area a2){
        boolean coll = false;

        Area coll1 = new Area(a1);
        coll1.subtract(a2);
        if (!coll1.equals(a1)){
            coll = true;
        }

        Area coll2 = new Area(a2);
        coll2.subtract(a1);
        if (!coll2.equals(a2)){
            coll = true;
        }
        return coll;
    }


}

//https://stackoverflow.com/questions/8201705/java-awt-graphics-change-color-after-drawing something similar?
class GamePanel extends JPanel {
    // Set background in constructor.
    //can change these values to move it
    Shape ball;
    Shape box1;
    Shape box2;

    //testing
    float ballX;
    float ballY;

    public GamePanel (Shape ball, Shape box1, Shape box2, float ballX, float ballY)
    {
        this.setBackground (Color.white);
        this.ball = ball;
        this.box1 = box1;
        this.box2 = box2;
        System.out.println("Made");

        //testing
        this.ballX = ballX;
        this.ballY = ballY;
    }

    // Override paintComponent(): - this is an existing function that becomes changed by this addition
    public void paintComponent (Graphics g)
    {
        Graphics2D graphics2d = (Graphics2D)g;
        super.paintComponent(g);

        graphics2d.draw(ball);
        graphics2d.draw(box1);
        graphics2d.draw(box2);

        //testing
        Shape ball1 = new Ellipse2D.Float(ballX, ballY, 30.0f, 30.0f);
        graphics2d.draw(ball1);
        graphics2d.setColor(Color.yellow);
        graphics2d.fill(ball1);
        //THE INSIDE VALUE ISN'T CHANGING
        System.out.println(ballX + " inside");



        //for some reason it's like a brush
        graphics2d.setColor(Color.red);
        graphics2d.fill(ball);
        graphics2d.setColor(Color.blue);
        graphics2d.fill(box1);
        graphics2d.setColor(Color.green);
        graphics2d.fill(box2);
        System.out.println ("Inside paintComponent");
    }

}



