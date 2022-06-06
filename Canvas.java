import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Canvas extends JLayeredPane implements MouseListener, MouseMotionListener {

    public UmlFrame frame;
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
    private static Canvas instance;

    private Canvas() {
        frame = UmlFrame.getInstance();
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
        instance = this;
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }
        return instance;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (frame.function == frame.select) {
            selectedBlock.clear();
            selectedComposite.clear();
            selectEndPoint = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(selectEndPoint, this);
            select();
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (frame.function == frame.select) {
            selectedBlock.clear();
            selectedComposite.clear();
            selectStartPoint = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(selectStartPoint, this);
            setAllUnvisible();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        addBlock();
    }

    public void addBlock() {
        if (frame.function == frame.classBtn || frame.function == frame.useCase) {
            Block newBlock;
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, this);
            int X = (int) p.getX();
            int Y = (int) p.getLocation().getY();
            if (frame.function == frame.classBtn)
                newBlock = new Rectangle();
            else
                newBlock = new Oval();
            this.add(newBlock, 0);
            if (frame.function == frame.classBtn)
                newBlock.setLocation(X, Y);
            else
                newBlock.setLocation(X - 40, Y);
            blocks.add(newBlock);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        for (Port[] i : AssociationLines)
            drawAL(i, g2, "AssociationLines", 12, 8); // 箭頭長12 寬8
        for (Port[] i : generalizationLines)
            drawAL(i, g2, "generalizationLines", 12, 8);
        for (Port[] i : compositionLines)
            drawAL(i, g2, "compositionLines", 10, 6);
        if (frame.function == frame.select && selectStartPoint != null)
            selectRectangle(g2);
    }

    private void selectRectangle(Graphics2D g2) {
        Point P;
        P = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(P, this);
        int xs = (int) Math.min(selectStartPoint.getX(), P.getX());
        int ys = (int) Math.min(selectStartPoint.getY(), P.getY());
        int xd = (int) Math.abs(P.getX() - selectStartPoint.getX());
        int yd = (int) Math.abs(P.getY() - selectStartPoint.getY());
        g2.drawRect(xs, ys, xd, yd);
    }

    public void addLine(Port p1, Port p2) {
        Port[] tmp = { p1, p2 };
        if (frame.function == frame.associationLine)
            AssociationLines.add(tmp);
        else if (frame.function == frame.generalizationLine)
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
        setAllUnvisible();
        for (Block i : blocks) {
            if (inBound(i, xMax, xMin, yMax, yMin)) {
                i.setPortVisible(true);
                if (i.parent == null)
                    selectedBlock.add(i);
                else {
                    Composite tmp = i.findParent();
                    if (!selectedComposite.contains(tmp))
                        selectedComposite.add(tmp);
                }
            }
        }
        renameCandidateBlock = null;
        selectEndPoint = null;
        selectStartPoint = null;
        repaint();
    }

    private boolean inBound(Block i, double xMax, double xMin, double yMax, double yMin) {
        return (i.getX() >= xMin && i.getX() <= xMax && i.getY() >= yMin && i.getY() <= yMax
                && i.getX() + i.p5.getX() >= xMin
                && i.getX() + i.p5.getX() <= xMax && i.getY() + i.p5.getY() >= yMin
                && i.p5.getY() + i.getY() <= yMax);
    }

    public void setAllUnvisible() {
        for (Block i : blocks) {
            i.setPortVisible(false);
        }
    }

    public void group() {
        if (selectedBlock.size() + selectedComposite.size() == 0)
            return;
        Composite newComposite = new Composite(this, selectedBlock, selectedComposite);
        this.add(newComposite);
        composites.add(newComposite);
        setAllUnvisible();
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
        setAllUnvisible();
        revalidate();
        repaint();
    }

    public static void drawAL(Port[] i,
            Graphics2D g2, String type, double H, double L) {

        double sx = i[0].getX() + i[0].block.getX() + 5;
        double sy = i[0].getY() + i[0].block.getY() + 5;
        double ex = i[1].getX() + i[1].block.getX() + 5;
        double ey = i[1].getY() + i[1].block.getY() + 5;
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];
        if (type == "AssociationLines") {
            g2.drawLine((int) sx, (int) sy, (int) ex, (int) ey);
            g2.drawLine((int) ex, (int) ey, (int) x_3, (int) y_3);
            g2.drawLine((int) ex, (int) ey, (int) x_4, (int) y_4);
        } else if (type == "generalizationLines") {
            GeneralPath triangle = new GeneralPath();
            triangle.moveTo(ex, ey);
            triangle.lineTo(x_3, y_3);
            triangle.lineTo(x_4, y_4);
            triangle.closePath();
            g2.draw(triangle);
            g2.drawLine((int) sx, (int) sy, (int) (x_3 + (x_4 - x_3) / 2), (int) (y_3 + (y_4 - y_3) / 2));
        } else {
            double x_5 = x_3 + (x_4 - x_3) / 2;
            double y_5 = y_3 + (y_4 - y_3) / 2;
            double x_6 = ex - (ex - x_5) * 2;
            double y_6 = ey - (ey - y_5) * 2;
            g2.drawLine((int) sx, (int) sy, (int) x_6, (int) y_6);
            g2.drawLine((int) ex, (int) ey, (int) x_3, (int) y_3);
            g2.drawLine((int) ex, (int) ey, (int) x_4, (int) y_4);
            g2.drawLine((int) x_6, (int) y_6, (int) x_3, (int) y_3);
            g2.drawLine((int) x_6, (int) y_6, (int) x_4, (int) y_4);
        }
    }

    public static double[] rotateVec(double e, double f, double ang,
            boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        double vx = e * Math.cos(ang) - f * Math.sin(ang);
        double vy = e * Math.sin(ang) + f * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }
}