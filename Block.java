import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Block extends JPanel implements MouseListener, MouseMotionListener {

    public JLabel title;
    private boolean selected;
    public Point P;
    public Point moveStartPoint;
    public Canvas canvas;
    public Composite parent;
    public double parentDistanceX, parentDistanceY;
    public int height, width;
    private Polygon poly1, poly2, poly3, poly4;
    Port p1;
    Port p2;
    Port p3;
    Port p4;
    Port p5;

    Block(Canvas canvas) {
        title = new JLabel();
        this.canvas = canvas;
        p1 = new Port(this);
        p2 = new Port(this);
        p3 = new Port(this);
        p4 = new Port(this);
        p5 = new Port(this);
        poly1 = new Polygon();
        poly2 = new Polygon();
        poly3 = new Polygon();
        poly4 = new Polygon();
        this.setTitle("Title");
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(title);
        canvas.moveToFront(this);
        setPortVisible(false);
        p5.setVisible(false);
        parent = null;
        this.setOpaque(false);
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
        if (canvas.frame.function == canvas.frame.associationLine
                || canvas.frame.function == canvas.frame.generalizationLine
                || canvas.frame.function == canvas.frame.compositionLine) {
            if (canvas.lineStartPoint != null && canvas.lineEndBlock != null) {
                Port shortestPort = canvas.lineEndBlock.findPort();
                if (canvas.lineStartPoint.block != shortestPort.block)
                    canvas.addLine(canvas.lineStartPoint, shortestPort);
                canvas.lineStartPoint = null;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.associationLine
                || canvas.frame.function == canvas.frame.generalizationLine
                || canvas.frame.function == canvas.frame.compositionLine && canvas.lineStartPoint != null) {
            canvas.lineEndBlock = this;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.select && parent == null) {
            moveStartPoint = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(moveStartPoint, this);
        }
        if (canvas.frame.function == canvas.frame.select && parent != null) {
            findParent().getStartPoint();
        }
        if (canvas.frame.function == canvas.frame.associationLine
                || canvas.frame.function == canvas.frame.generalizationLine
                || canvas.frame.function == canvas.frame.compositionLine) {
            canvas.lineStartPoint = findPort();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.select) {
            if (parent == null)
                canvas.moveToFront(this);
            canvas.renameCandidateBlock = this;
            for (Block i : canvas.blocks) {
                i.setPortVisible(false);
            }
            setPortVisible(true);
            canvas.selectedGroup = findParent();
        }
        canvas.addBlock();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.associationLine
                || canvas.frame.function == canvas.frame.generalizationLine
                || canvas.frame.function == canvas.frame.compositionLine) {
            canvas.lineEndBlock = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.select && parent == null) {
            canvas.moveToFront(this);
            P = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(P, canvas);
            this.setLocation((int) (P.getX() - moveStartPoint.getX()),
                    (int) (P.getY() - moveStartPoint.getY()));
            canvas.repaint();
        }
        if (canvas.frame.function == canvas.frame.select && parent != null) {
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

    protected void initPoly() {
        poly1 = new Polygon();
        poly2 = new Polygon();
        poly3 = new Polygon();
        poly4 = new Polygon();

        poly1.addPoint(5, 5);
        poly1.addPoint(p4.getX() + 5, 5);
        poly1.addPoint(p1.getX() + 5, p4.getY() + 5);

        poly2.addPoint(5, p2.getY() + 5);
        poly2.addPoint(p4.getX() + 5, p2.getY() + 5);
        poly2.addPoint(p1.getX() + 5, p4.getY() + 5);

        poly3.addPoint(5, 5);
        poly3.addPoint(5, p2.getY() + 5);
        poly3.addPoint(p1.getX() + 5, p4.getY() + 5);

        poly4.addPoint(p4.getX() + 5, 5);
        poly4.addPoint(p4.getX() + 5, p2.getY() + 5);
        poly4.addPoint(p1.getX() + 5, p4.getY() + 5);
    }

    public Port findPort() {
        P = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(P, this);
        if (poly1.contains(P))
            return p1;
        if (poly2.contains(P))
            return p2;
        if (poly3.contains(P))
            return p3;
        if (poly4.contains(P))
            return p4;
        return findShortestPort(P);
    }

    public Port findShortestPort(Point P) {
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
        if (parent == null)
            return null;
        return parent.findParent();
    }
}
