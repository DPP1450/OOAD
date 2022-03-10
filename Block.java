import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Block extends JPanel implements MouseListener, MouseMotionListener {

    private JLabel title;
    private boolean selected;
    public Point P;
    public Point moveStartPoint;
    private Canvas canvas;
    public Composite parent;
    public String type;
    public double parentDistanceX, parentDistanceY;
    Port p1;
    Port p2;
    Port p3;
    Port p4;

    Block(Canvas canvas, String type) {
        this.type = type;
        title = new JLabel();
        this.canvas = canvas;
        p1 = new Port(this);
        p2 = new Port(this);
        p3 = new Port(this);
        p4 = new Port(this);
        this.setTitle("Title");
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(title);
        canvas.moveToFront(this);
        setPortVisible(false);
        parent = null;
        if (type == "Class") {
            this.setSize(80, 120);
            title.setSize(70, 30);
            title.setLocation(10, 10);
            p1.setLocation(40, 0);
            p2.setLocation(40, 110);
            p3.setLocation(0, 60);
            p4.setLocation(70, 60);
        }
        if (type == "Oval") {
            this.setSize(80, 60);
            title.setSize(60, 20);
            title.setLocation(25, 15);
            p1.setLocation(0, 30);
            p2.setLocation(70, 30);
            p3.setLocation(30, 0);
            p4.setLocation(30, 50);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        if (type == "Class") {
            g.drawLine(5, 5, 5, 115);
            g.drawLine(75, 5, 75, 115);
            g.drawLine(5, 5, 75, 5);
            g.drawLine(5, 5, 75, 5);
            g.drawLine(5, 40, 75, 40);
            g.drawLine(5, 80, 75, 80);
            g.drawLine(5, 115, 75, 115);
        }
        if (type == "Oval") {
            g.drawOval(5, 5, 70, 50);
        }
    }

    public void setTitle(String s) {
        title.setText(s);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        selected = b;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (canvas.frame.function == "associationLine" || canvas.frame.function == "generalizationLine"
                || canvas.frame.function == "compositionLine") {
            if (canvas.lineStartPoint != null && canvas.lineEndBlock != null) {
                Port shortestPort = canvas.lineEndBlock.findShortestPort();
                canvas.addAssociationLine(canvas.lineStartPoint, shortestPort); // 之後要新增其他種類的line
                canvas.lineStartPoint = null;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (canvas.frame.function == "associationLine" || canvas.frame.function == "generalizationLine"
                || canvas.frame.function == "compositionLine" && canvas.lineStartPoint != null) {
            canvas.lineEndBlock = this;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canvas.frame.function == "select" && parent == null) {
            moveStartPoint = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(moveStartPoint, this);
        }
        if (canvas.frame.function == "select" && parent != null) {
            findParent().getStartPoint();
        }
        if (canvas.frame.function == "associationLine" || canvas.frame.function == "generalizationLine"
                || canvas.frame.function == "compositionLine") {
            canvas.lineStartPoint = findShortestPort();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (canvas.frame.function == "select") {
            if (parent == null)
                canvas.moveToFront(this);
            canvas.renameCandidateBlock = this;
            for (Block i : canvas.blocks) {
                i.setPortVisible(false);
            }
            setPortVisible(true);
            canvas.selectedGroup = findParent();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (canvas.frame.function == "associationLine" || canvas.frame.function == "generalizationLine"
                || canvas.frame.function == "compositionLine") {
            canvas.lineEndBlock = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (canvas.frame.function == "select" && parent == null) {
            canvas.moveToFront(this);
            P = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(P, canvas);
            this.setLocation((int) (P.getX() - moveStartPoint.getX()),
                    (int) (P.getY() - moveStartPoint.getY()));
            canvas.repaint();
        }
        if (canvas.frame.function == "select" && parent != null) {
            findParent().move();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void setPortVisible(boolean f) {
        p1.setVisible(f);
        p2.setVisible(f);
        p3.setVisible(f);
        p4.setVisible(f);
        canvas.revalidate();
        canvas.repaint();
    }

    public Port findShortestPort() {
        P = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(P, this);
        Port shortestPort = p1;
        int X = (int) P.getX();
        int Y = (int) P.getY();
        double minn = Math.pow(p1.getX() - X, 2) + Math.pow(p1.getY() - Y, 2);
        if (Math.pow(p2.getX() - X, 2) + Math.pow(p2.getY() - Y, 2) < minn) {
            shortestPort = p2;
            minn = Math.pow(p2.getX() - X, 2) + Math.pow(p2.getY() - Y, 2);
        }
        if (Math.pow(p3.getX() - X, 2) + Math.pow(p3.getY() - Y, 2) < minn) {
            shortestPort = p3;
            minn = Math.pow(p3.getX() - X, 2) + Math.pow(p3.getY() - Y, 2);
        }
        if (Math.pow(p4.getX() - X, 2) + Math.pow(p4.getY() - Y, 2) < minn) {
            shortestPort = p4;
        }
        return shortestPort;
    }

    public Composite findParent() {
        return parent.findParent();
    }
}
