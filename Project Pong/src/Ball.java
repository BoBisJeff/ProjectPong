import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Ball {
    static GamePanel mainPanel;

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

    float panelWidth = 1194.0f;
    float panelHeight = 565.0f;

    //speed multiplier, lowered for testing (def: 1.0f)
    float ballSpeed = 0.5f;
    //store movement of ball, start at default speed
    float ballMoveX = 0.1f;
    float ballMoveY = 0.1f;
    //Movement speed of box for easy adjustment
    float boxSpeed = 0.1f;

    //booleans to move boxes
    boolean box1MoveUp = false;
    boolean box2MoveUp = false;
    boolean box1MoveDown = false;
    boolean box2MoveDown = false;



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

        //for testing
        /*
        ballX = 115.0f+100.0f;
        ballY = 267.5f-150.0f;*/

        ball = new Ellipse2D.Float(ballX, ballY, 30.0f, 30.0f);
        box1 = new Rectangle2D.Float(box1X, box1Y, 30.0f, 100.0f);
        box2 = new Rectangle2D.Float(box2X, box2Y, 30.0f, 100.0f);

        //Areas for each thing
        ballArea = new Area(ball);
        box1Area = new Area(box1);
        box2Area = new Area(box2);
        walls = new Area(new Rectangle.Float(0,0, panelWidth, panelHeight));

        mainPanel = new GamePanel();
        Main.mainWindow.getContentPane().add(mainPanel);
        Main.mainWindow.setVisible(true);

        //beginBall();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        //runs the frameMaker to create a new frame every 2ms
        executor.scheduleAtFixedRate(runBall, 0, 1, TimeUnit.MILLISECONDS);

        //for the box
        ScheduledExecutorService executorBox1 = Executors.newScheduledThreadPool(1);
        //runs the frameMaker to create a new frame every 2ms
        //executor.scheduleAtFixedRate(runBox1, 0, 1, TimeUnit.MILLISECONDS);



        Main.mainWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                boxKeyRead(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }


    //the movement that will be made repeatedly (calculating everything)
    public void ballMove(){
        //System.out.println("Starting to move");
        ballX += ballMoveX * ballSpeed;
        //ballY += ballMoveY * ballSpeed;

        //MUST REBUILD EVERYTHING FOR EACH MOVEMENT
        ball = new Ellipse2D.Float(ballX, ballY, 30.0f, 30.0f);
        ballArea = new Area(ball);

        //Collision with walls
        if (isCollision(ballArea, walls)){
            if (ballX + 30.0f > panelWidth || ballX < 0){
                ballMoveX *= -1;
                System.out.println("reverse x");
            }
            if (ballY + 30.0f > panelHeight || ballY < 0){
                ballMoveY *= -1;
            }
        }

        //Collision with boxes MISSING CORNER COLLSIONS - NEED TO TEST WHAT HAPPENS
        if (isCollision (ballArea, box1Area)){
            //Ball to the sides of box1
            if ((ballX > box1X && (ballY > box1Y-15.0f && ballY < box1Y+85.0f)) || (ballX < box1X && (ballY > box1Y-15.0f && ballY < box1Y+85.0f))){
                ballMoveX *= -1;
                System.out.println("reverse x");
            }
            //Ball to the top or bottom of box1
            if ((ballY > box1Y && (ballX > box1X-15.0f && ballX < box1X+15.0f)) || (ballY < box1Y && (ballX > box1X-15.0f && ballX < box1X+15.0f))){
                ballMoveY *=-1;
            }
        }

        //Collision for box 2
        if (isCollision (ballArea, box2Area)){
            //Ball to the sides of box1
            if ((ballX > box2X && (ballY > box2Y-15.0f && ballY < box2Y+85.0f)) || (ballX < box2X && (ballY > box2Y-15.0f && ballY < box2Y+85.0f))){
                ballMoveX *= -1;
                System.out.println("reverse x");
            }
            //Ball to the top or bottom of box1
            if ((ballY > box2Y && (ballX > box2X-15.0f && ballX < box2X+15.0f)) || (ballY < box2Y && (ballX > box2X-15.0f && ballX < box2X+15.0f))){
                ballMoveY *=-1;
            }
        }
        mainPanel.repaint();
    }

    public void boxMove(){
        //move the boxes by redrawing
        box1 = new Rectangle2D.Float(box1X, box1Y, 30.0f, 100.0f);
        box2 = new Rectangle2D.Float(box2X, box2Y, 30.0f, 100.0f);
        box1Area = new Area(box1);
        box2Area = new Area(box2);
        mainPanel.repaint();
    }

    public void boxKeyRead(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP:
                //move up box2
                box2MoveUp = true;
                boolean box1MoveUp = false;
                boolean box2MoveUp = false;
                boolean box1MoveDown = false;
                boolean box2MoveDown = false;
                break;
            case KeyEvent.VK_DOWN:
                box2MoveUp = false;

        }
    }


    Runnable moveBox1Up = new Runnable(){
        @Override
        public void run(){

        }
    };

    Runnable moveBox1Down = new Runnable(){
        @Override
        public void run(){

        }
    };
    Runnable moveBox2Up = new Runnable(){
        @Override
        public void run(){
            while (box2MoveUp == true){
                box2X += boxSpeed;
                boxMove();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("timer failed");
            }
        }
    };
    Runnable moveBox2Down = new Runnable(){
        @Override
        public void run(){

        }
    };


    //The ball's movement
    Runnable runBall = new Runnable(){
        @Override
        public void run(){
            ballMove();
            //May need to change this later to schedule at fixed rate
        }
    };

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

   /* Shape ball = Ball.ball;
    Shape box1 = Ball.box1;
    Shape box2;

    //testing
    float ballX = Ball.ballX;
    float ballY = Ball.ballY;*/

    //PROBLEM: When ball is changed outside, the ball isn't changed inside - they aren't linked
    //Potential SOLUTION: use BALL.---

    public GamePanel ()
    {
        this.setBackground (Color.white);
        //Shape ball, Shape box1, Shape box2, float x, float y

        /*this.ball = ball;
        this.box1 = box1;
        this.box2 = box2;*/
        System.out.println("Made");;
    }

    // Override paintComponent(): - this is an existing function that becomes changed by this addition
    public void paintComponent (Graphics g)
    {
        Graphics2D graphics2d = (Graphics2D)g;
        super.paintComponent(g);
        graphics2d.draw(Ball.walls);
        graphics2d.setColor(Color.LIGHT_GRAY);
        graphics2d.fill(Ball.walls);
        graphics2d.draw(Ball.ball);
        graphics2d.draw(Ball.box1);
        graphics2d.draw(Ball.box2);
        //testing
        /*
        Shape ball1 = new Ellipse2D.Float(Ball.ballX, Ball.ballY, 30.0f, 30.0f);
        graphics2d.draw(ball1);
        graphics2d.setColor(Color.yellow);
        graphics2d.fill(ball1);
        //THE INSIDE VALUE ISN'T CHANGING
        System.out.println(ballX + " inside");*/

        //for some reason it's like a brush
        graphics2d.setColor(Color.red);
        graphics2d.fill(Ball.ball);
        graphics2d.setColor(Color.blue);
        graphics2d.fill(Ball.box1);
        graphics2d.setColor(Color.green);
        graphics2d.fill(Ball.box2);

        System.out.println ("Inside paintComponent");
    }

}


/*
    public void beginBall(){
        //This is where the runnable will be initiated
        for (int i = 0; i < 4000; i++){
            //speed lowered for testing purposes
            ballX += ballMoveX*0.5f;
            ballY += ballMoveY*0.5f;

            //MUST REBUILD EVERYTHING FOR EACH MOVEMENT
            ball = new Ellipse2D.Float(ballX, ballY, 30.0f, 30.0f);
            box1 = new Rectangle2D.Float(box1X, box1Y, 30.0f, 100.0f);
            box2 = new Rectangle2D.Float(box2X, box2Y, 30.0f, 100.0f);
            ballArea = new Area(ball);
            box1Area = new Area(box1);
            box2Area = new Area(box2);

            mainPanel.repaint();
            System.out.println(ballX);

        }
    }
 */



