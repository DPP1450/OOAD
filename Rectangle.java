import java.awt.*;

public class Rectangle extends Block {

    Rectangle(Canvas canvas) {
        super(canvas);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(5, 5, 5, 115);
        g.drawLine(75, 5, 75, 115);
        g.drawLine(5, 5, 75, 5);
        g.drawLine(5, 5, 75, 5);
        g.drawLine(5, 40, 75, 40);
        g.drawLine(5, 80, 75, 80);
        g.drawLine(5, 115, 75, 115);
    }
}
