import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {

    public String function;
    public Canvas canvas;
    private JMenuBar menuBar;
    private JMenuItem ButtonGroup;
    private JMenuItem ButtonUngroup;
    private JMenuItem changeObjectName;
    private JMenu edit;
    private JPanel functionSelect;
    public JToggleButton select;
    public JToggleButton associationLine;
    public JToggleButton generalizationLine;
    public JToggleButton compositionLine;
    public JToggleButton classBtn;
    public JToggleButton useCase;

    Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("UML");
        this.setResizable(false);
        this.setSize(1280, 720);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(null);
        functionSelect = new JPanel();
        ButtonGroup = new JMenuItem("group");
        ButtonUngroup = new JMenuItem("ungroup");
        changeObjectName = new JMenuItem("change object name");
        edit = new JMenu("Edit");
        menuBar = new JMenuBar();
        canvas = new Canvas(this);
        functionSelect = new JPanel();
        select = new JToggleButton("select");
        associationLine = new JToggleButton("association line");
        generalizationLine = new JToggleButton("generalization line");
        compositionLine = new JToggleButton("composition line");
        classBtn = new JToggleButton("class");
        useCase = new JToggleButton("use case");
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "select") {
                    function = "NULL";
                } else {
                    function = "select";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                }
            }
        });
        associationLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "associationLine") {
                    function = "NULL";
                } else {
                    function = "associationLine";
                    select.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                }
            }
        });
        generalizationLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "generalizationLine") {
                    function = "NULL";
                } else {
                    function = "generalizationLine";
                    associationLine.setSelected(false);
                    select.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                }
            }
        });
        compositionLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "compositionLine") {
                    function = "NULL";
                } else {
                    function = "compositionLine";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    select.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                }
            }
        });
        classBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "class") {
                    function = "NULL";
                } else {
                    function = "class";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    select.setSelected(false);
                    useCase.setSelected(false);
                }
            }
        });
        useCase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "useCase") {
                    function = "NULL";
                } else {
                    function = "useCase";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    select.setSelected(false);
                }
            }
        });
        ButtonGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "select") {
                    canvas.group();
                }
            }
        });
        ButtonUngroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "select") {
                    canvas.unGroup();
                }
            }
        });
        changeObjectName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (canvas.renameCandidateBlock != null) {
                    JFrame popwindow = new JFrame();
                    String newTitle = JOptionPane.showInputDialog(popwindow, "輸入title");
                    canvas.renameCandidateBlock.setTitle(newTitle);
                }
            }
        });
        edit.add(ButtonGroup);
        edit.add(ButtonUngroup);
        edit.add(changeObjectName);
        menuBar.add(edit);
        this.add(functionSelect);
        this.add(menuBar);
        this.add(canvas);
        this.add(functionSelect);
        functionSelect.setLayout(null);
        functionSelect.setBackground(Color.gray);
        functionSelect.add(select);
        functionSelect.add(associationLine);
        functionSelect.add(generalizationLine);
        functionSelect.add(compositionLine);
        functionSelect.add(classBtn);
        functionSelect.add(useCase);
        select.setSize(140, 60);
        select.setLocation(0, 20);
        associationLine.setSize(140, 60);
        associationLine.setLocation(0, 100);
        generalizationLine.setSize(140, 60);
        generalizationLine.setLocation(0, 180);
        compositionLine.setSize(140, 60);
        compositionLine.setLocation(0, 260);
        classBtn.setSize(140, 60);
        classBtn.setLocation(0, 340);
        useCase.setSize(140, 60);
        useCase.setLocation(0, 420);
        canvas.setSize(1140, 715);
        canvas.setLocation(140, 10);
        functionSelect.setSize(140, 700);
        functionSelect.setLocation(0, 20);
        this.setJMenuBar(menuBar);
        validate();
    }
}