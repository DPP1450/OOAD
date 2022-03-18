import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Composite extends JPanel implements MouseListener, MouseMotionListener {

    public ArrayList<Block> subBlock;
    public ArrayList<Composite> subComposite;
    public int xMin = 2000, xMax = -2000, yMin = 2000, yMax = -2000, xSize, ySize;
    public Point moveStartPoint;
    public Point location;
    public Composite parent;
    public Canvas canvas;
    public Point P;
    public double parentDistanceX, parentDistanceY;

    Composite(Canvas canvas, ArrayList<Block> subBlock, ArrayList<Composite> subComposite) {
        this.subBlock = new ArrayList<Block>(subBlock);
        this.subComposite = new ArrayList<Composite>(subComposite);
        findBound();
        xSize = xMax - xMin + 20;
        ySize = yMax - yMin + 20;
        this.setSize(xSize, ySize);
        this.setLocation(xMin - 10, yMin - 10);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        parent = null;
        this.canvas = canvas;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        getParentDistance();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.select && parent == null) {
            getStartPoint();
        }
        if (canvas.frame.function == canvas.frame.select && parent != null) {
            findParent().getStartPoint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.select)
            canvas.selectedGroup = findParent();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (canvas.frame.function == canvas.frame.select && parent == null) {
            move();
        }
        if (canvas.frame.function == canvas.frame.select && parent != null) {
            findParent().move();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void findBound() {
        for (Block i : subBlock) {
            i.parent = this;
            xMin = Math.min(i.getX(), xMin);
            xMax = Math.max(i.getX() + i.width, xMax);
            yMin = Math.min(i.getY(), yMin);
            yMax = Math.max(i.getY() + i.height, yMax);
        }
        for (Composite i : subComposite) {
            i.parent = this;
            xMin = Math.min(i.getX(), xMin);
            xMax = Math.max(i.getX() + i.xSize, xMax);
            yMin = Math.min(i.getY(), yMin);
            yMax = Math.max(i.getY() + i.ySize, yMax);
        }
    }

    public Composite findParent() {
        if (parent == null)
            return this;
        return parent.findParent();
    }

    void getParentDistance() {
        for (Block i : subBlock) {
            i.parentDistanceX = i.getX() - this.getX();
            i.parentDistanceY = i.getY() - this.getY();
        }
        for (Composite i : subComposite) {
            i.parentDistanceX = i.getX() - this.getX();
            i.parentDistanceY = i.getY() - this.getY();
        }
    }

    void moveByParent() {
        canvas.moveToFront(this);
        for (Block i : subBlock) {
            canvas.moveToFront(i);
            i.setLocation((int) (this.getX() + i.parentDistanceX), (int) (this.getY() + i.parentDistanceY));
        }
        for (Composite i : subComposite) {
            i.setLocation((int) (this.getX() + i.parentDistanceX), (int) (this.getY() + i.parentDistanceY));
            i.moveByParent();
        }
    }

    void getStartPoint() {
        moveStartPoint = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(moveStartPoint, this);
    }

    void move() {
        P = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(P, canvas);
        this.setLocation((int) (P.getX() - moveStartPoint.getX()),
                (int) (P.getY() - moveStartPoint.getY()));
        moveByParent();
        canvas.repaint();
    }
}
