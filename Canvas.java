import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Canvas extends JLayeredPane implements MouseListener, MouseMotionListener {

    public Frame frame;
    public Port lineStartPoint;
    public Block lineEndBlock;
    public ArrayList<Port[]> AssociationLines;
    public ArrayList<Port[]> generalizationLines;
    public ArrayList<Port[]> compositionLines;
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
        AssociationLines = new ArrayList<Port[]>();
        generalizationLines = new ArrayList<Port[]>();
        compositionLines = new ArrayList<Port[]>();
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
    public void paint(Graphics g) { // 要改成不同的線
        super.paint(g);
        g.setColor(Color.BLACK);
        for (Port[] i : AssociationLines) {
            int x1 = i[0].getX() + i[0].block.getX() + 5;
            int y1 = i[0].getY() + i[0].block.getY() + 5;
            int x2 = i[1].getX() + i[1].block.getX() + 5;
            int y2 = i[1].getY() + i[1].block.getY() + 5;
            g.drawLine(x1, y1, x2, y2);
        }
        for (Port[] i : generalizationLines) {
            int x1 = i[0].getX() + i[0].block.getX() + 5;
            int y1 = i[0].getY() + i[0].block.getY() + 5;
            int x2 = i[1].getX() + i[1].block.getX() + 5;
            int y2 = i[1].getY() + i[1].block.getY() + 5;
            g.drawLine(x1, y1, x2, y2);
        }
        for (Port[] i : compositionLines) {
            int x1 = i[0].getX() + i[0].block.getX() + 5;
            int y1 = i[0].getY() + i[0].block.getY() + 5;
            int x2 = i[1].getX() + i[1].block.getX() + 5;
            int y2 = i[1].getY() + i[1].block.getY() + 5;
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public void addLine(Port p1, Port p2) {
        Port[] tmp = { p1, p2 };
        if (frame.function == "associationLine")
            AssociationLines.add(tmp);
        else if (frame.function == "generalizationLine")
            generalizationLines.add(tmp);
        else
            compositionLines.add(tmp);
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