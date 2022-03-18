import java.awt.*;

public class Oval extends Block {

    Oval(Canvas canvas) {
        super(canvas);
        this.setSize(80, 60);
        super.title.setSize(60, 20);
        super.title.setLocation(25, 15);
        super.p1.setLocation(0, 30);
        super.p2.setLocation(70, 30);
        super.p3.setLocation(30, 0);
        super.p4.setLocation(30, 50);
        super.p5.setLocation(70, 50);
        super.height = 80;
        super.width = 60;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawOval(5, 5, 70, 50);
    }
}
