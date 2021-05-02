import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Ball {

    public static void makeBall(){
        Shape ball = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f);
        Main.graphics2D.draw(ball);
    }




}
