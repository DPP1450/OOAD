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
    public JButton select;
    public JButton associationLine;
    public JButton generalizationLine;
    public JButton compositionLine;
    public JButton classBtn;
    public JButton useCase;

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
        select = new JButton("select");
        select.setOpaque(true);
        associationLine = new JButton("association line");
        associationLine.setOpaque(true);
        generalizationLine = new JButton("generalization line");
        generalizationLine.setOpaque(true);
        compositionLine = new JButton("composition line");
        compositionLine.setOpaque(true);
        classBtn = new JButton("class");
        classBtn.setOpaque(true);
        useCase = new JButton("use case");
        useCase.setOpaque(true);
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "select") {
                    select.setBackground(Color.WHITE);
                    function = "NULL";
                } else {
                    function = "select";
                    select.setBackground(Color.RED);
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                    associationLine.setBackground(Color.WHITE);
                    generalizationLine.setBackground(Color.WHITE);
                    compositionLine.setBackground(Color.WHITE);
                    classBtn.setBackground(Color.WHITE);
                    useCase.setBackground(Color.WHITE);
                }
            }
        });
        select.setBackground(Color.WHITE);
        select.setForeground(Color.BLACK);
        associationLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "associationLine") {
                    function = "NULL";
                    associationLine.setBackground(Color.WHITE);
                } else {
                    associationLine.setBackground(Color.RED);
                    function = "associationLine";
                    select.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                    select.setBackground(Color.WHITE);
                    generalizationLine.setBackground(Color.WHITE);
                    compositionLine.setBackground(Color.WHITE);
                    classBtn.setBackground(Color.WHITE);
                    useCase.setBackground(Color.WHITE);
                }
            }
        });
        associationLine.setBackground(Color.WHITE);
        associationLine.setForeground(Color.BLACK);
        generalizationLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "generalizationLine") {
                    generalizationLine.setBackground(Color.WHITE);
                    function = "NULL";
                } else {
                    generalizationLine.setBackground(Color.RED);
                    function = "generalizationLine";
                    associationLine.setSelected(false);
                    select.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                    select.setBackground(Color.WHITE);
                    associationLine.setBackground(Color.WHITE);
                    compositionLine.setBackground(Color.WHITE);
                    classBtn.setBackground(Color.WHITE);
                    useCase.setBackground(Color.WHITE);
                }
            }
        });
        generalizationLine.setBackground(Color.WHITE);
        generalizationLine.setForeground(Color.BLACK);
        compositionLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "compositionLine") {
                    compositionLine.setBackground(Color.WHITE);
                    function = "NULL";
                } else {
                    compositionLine.setBackground(Color.RED);
                    function = "compositionLine";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    select.setSelected(false);
                    classBtn.setSelected(false);
                    useCase.setSelected(false);
                    select.setBackground(Color.WHITE);
                    associationLine.setBackground(Color.WHITE);
                    generalizationLine.setBackground(Color.WHITE);
                    classBtn.setBackground(Color.WHITE);
                    useCase.setBackground(Color.WHITE);
                }
            }
        });
        compositionLine.setBackground(Color.WHITE);
        compositionLine.setForeground(Color.BLACK);
        classBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "class") {
                    classBtn.setBackground(Color.WHITE);
                    function = "NULL";
                } else {
                    classBtn.setBackground(Color.RED);
                    function = "class";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    select.setSelected(false);
                    useCase.setSelected(false);
                    select.setBackground(Color.WHITE);
                    associationLine.setBackground(Color.WHITE);
                    generalizationLine.setBackground(Color.WHITE);
                    compositionLine.setBackground(Color.WHITE);
                    useCase.setBackground(Color.WHITE);
                }
            }
        });
        classBtn.setBackground(Color.WHITE);
        classBtn.setForeground(Color.BLACK);
        useCase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == "useCase") {
                    useCase.setBackground(Color.WHITE);
                    function = "NULL";
                } else {
                    useCase.setBackground(Color.RED);
                    function = "useCase";
                    associationLine.setSelected(false);
                    generalizationLine.setSelected(false);
                    compositionLine.setSelected(false);
                    classBtn.setSelected(false);
                    select.setSelected(false);
                    select.setBackground(Color.WHITE);
                    associationLine.setBackground(Color.WHITE);
                    generalizationLine.setBackground(Color.WHITE);
                    compositionLine.setBackground(Color.WHITE);
                    classBtn.setBackground(Color.WHITE);
                }
            }
        });
        useCase.setBackground(Color.WHITE);
        useCase.setForeground(Color.BLACK);
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
                    String tmp = canvas.renameCandidateBlock.title.getText();
                    JFrame popwindow = new JFrame();
                    String newTitle = JOptionPane.showInputDialog(popwindow, "輸入title");
                    if (newTitle == null)
                        newTitle = tmp;
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