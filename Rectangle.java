import java.awt.*;
import java.awt.geom.*;

public class Rectangle extends Block {

    Rectangle() {
        this.setSize(80, 120);
        super.title.setSize(70, 30);
        super.title.setLocation(10, 10);
        super.p1.setLocation(40, 0);
        super.p2.setLocation(40, 110);
        super.p3.setLocation(0, 60);
        super.p4.setLocation(70, 60);
        super.p5.setLocation(70, 110);
        super.height = 120;
        super.width = 80;
        super.initPoly();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GeneralPath rectangle = new GeneralPath();
        g2.setColor(Color.WHITE);
        rectangle.moveTo(5, 5);
        rectangle.lineTo(5, 115);
        rectangle.lineTo(75, 115);
        rectangle.lineTo(75, 5);
        rectangle.closePath();
        g2.fill(rectangle);
        g2.setColor(Color.black);
        g2.drawLine(5, 5, 5, 115);
        g2.drawLine(75, 5, 75, 115);
        g2.drawLine(5, 5, 75, 5);
        g2.drawLine(5, 5, 75, 5);
        g2.drawLine(5, 40, 75, 40);
        g2.drawLine(5, 80, 75, 80);
        g2.drawLine(5, 115, 75, 115);
    }
}
