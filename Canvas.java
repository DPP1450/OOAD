import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Canvas extends JLayeredPane implements MouseListener, MouseMotionListener {

    public Frame frame;
    public Port lineStartPoint;
    public Block lineEndBlock;
    public ArrayList<Port[]> lines;
    public ArrayList<Block> blocks;
    public ArrayList<Composite> composites;
    public ArrayList<Block> selectedBlock;
    public ArrayList<Composite> selectedComposite;
    public Point selectStartPoint;
    public Point selectEndPoint;
    public Composite selectedGroup;
    public Block renameCandidateBlock;
    public double xShift, yShift;

    Canvas(Frame frame) {
        this.frame = frame;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.white);
        blocks = new ArrayList<Block>();
        composites = new ArrayList<Composite>();
        lines = new ArrayList<Port[]>();
        selectedComposite = new ArrayList<Composite>();
        selectedBlock = new ArrayList<Block>();
        lineStartPoint = null;
        lineEndBlock = null;
        selectedGroup = null;
        renameCandidateBlock = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (frame.function == "select") {
            selectedBlock.clear();
            selectedComposite.clear();
            selectEndPoint = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(selectEndPoint, this);
            select();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (frame.function == "select") {
            selectedBlock.clear();
            selectedComposite.clear();
            selectStartPoint = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(selectStartPoint, this);
            for (Block i : blocks) {
                i.setPortVisible(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (frame.function == "class") {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, this);
            int X = (int) p.getX();
            int Y = (int) p.getLocation().getY();
            Block newBlock = new Block(this, "Class");
            this.add(newBlock, 0);
            newBlock.setLocation(X, Y);
            blocks.add(newBlock);
        }
        if (frame.function == "useCase") {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, this);
            int X = (int) p.getX();
            int Y = (int) p.getLocation().getY();
            Block newBlock = new Block(this, "Oval");
            this.add(newBlock, 0);
            newBlock.setLocation(X, Y);
            blocks.add(newBlock);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        for (Port[] i : lines) {
            int x1 = i[0].getX() + i[0].block.getX();
            int y1 = i[0].getY() + i[0].block.getY();
            int x2 = i[1].getX() + i[1].block.getX();
            int y2 = i[1].getY() + i[1].block.getY();
            g.drawLine(x1 + 5, y1 + 5, x2 + 5, y2 + 5);
        }
    }

    public void addAssociationLine(Port p1, Port p2) {
        Port[] tmp = { p1, p2 };
        lines.add(tmp);
        repaint();
    }

    public void select() {
        double xMax = Math.max(selectStartPoint.getX(), selectEndPoint.getX());
        double xMin = Math.min(selectStartPoint.getX(), selectEndPoint.getX());
        double yMax = Math.max(selectStartPoint.getY(), selectEndPoint.getY());
        double yMin = Math.min(selectStartPoint.getY(), selectEndPoint.getY());
        for (Block i : blocks) {
            if (i.getX() >= xMin && i.getX() <= xMax && i.getY() >= yMin && i.getY() <= yMax) {
                i.setPortVisible(true);
                if (i.parent == null)
                    selectedBlock.add(i);
                else {
                    Composite tmp = i.findParent();
                    if (!selectedComposite.contains(tmp))
                        selectedComposite.add(tmp);
                }
            } else {
                i.setPortVisible(false);
            }
        }
    }

    public void group() {
        if (selectedBlock.size() + selectedComposite.size() == 0)
            return;
        Composite newComposite = new Composite(this, selectedBlock, selectedComposite);
        this.add(newComposite);
        composites.add(newComposite);
        revalidate();
        repaint();
    }

    public void unGroup() {
        if (selectedGroup == null)
            return;
        for (Block i : selectedGroup.subBlock)
            i.parent = null;
        for (Composite i : selectedGroup.subComposite)
            i.parent = null;
        remove(selectedGroup);
        selectedGroup = null;
        revalidate();
        repaint();
    }
}