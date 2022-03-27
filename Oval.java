import java.awt.*;

public class Oval extends Block {

    Oval(Canvas canvas) {
        super(canvas);
        this.setSize(80, 60);
        super.title.setSize(60, 20);
        super.title.setLocation(25, 15);
        super.p1.setLocation(30, 0);
        super.p2.setLocation(30, 50);
        super.p3.setLocation(0, 30);
        super.p4.setLocation(70, 30);
        super.p5.setLocation(70, 50);
        super.height = 60;
        super.width = 80;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(5, 5, 70, 50);
        g.setColor(Color.BLACK);
        g.drawOval(5, 5, 70, 50);
    }
}
