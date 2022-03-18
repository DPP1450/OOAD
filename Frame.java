import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Frame extends JFrame {

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
    public JButton function;
    public ArrayList<JButton> buttonList;

    Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("UML");
        this.setResizable(false);
        this.setSize(1280, 720);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(null);
        buttonList = new ArrayList<JButton>();
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
        select.setBackground(Color.WHITE);
        select.setForeground(Color.BLACK);
        associationLine = new JButton("association line");
        associationLine.setOpaque(true);
        associationLine.setBackground(Color.WHITE);
        associationLine.setForeground(Color.BLACK);
        generalizationLine = new JButton("generalization line");
        generalizationLine.setOpaque(true);
        generalizationLine.setBackground(Color.WHITE);
        generalizationLine.setForeground(Color.BLACK);
        compositionLine = new JButton("composition line");
        compositionLine.setOpaque(true);
        compositionLine.setBackground(Color.WHITE);
        compositionLine.setForeground(Color.BLACK);
        classBtn = new JButton("class");
        classBtn.setOpaque(true);
        classBtn.setBackground(Color.WHITE);
        classBtn.setForeground(Color.BLACK);
        useCase = new JButton("use case");
        useCase.setOpaque(true);
        useCase.setBackground(Color.WHITE);
        useCase.setForeground(Color.BLACK);
        buttonList = new ArrayList<JButton>();
        buttonList.add(select);
        buttonList.add(associationLine);
        buttonList.add(generalizationLine);
        buttonList.add(compositionLine);
        buttonList.add(classBtn);
        buttonList.add(useCase);
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction(select);
            }
        });
        associationLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction(associationLine);
            }
        });

        generalizationLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction(generalizationLine);
            }
        });

        compositionLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction(compositionLine);
            }
        });

        classBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction(classBtn);
            }
        });

        useCase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction(useCase);
            }
        });

        ButtonGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == select) {
                    canvas.group();
                }
            }
        });
        ButtonUngroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (function == select) {
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

    private void changeFunction(JButton selectFunction) {
        if (function == selectFunction) {
            selectFunction.setBackground(Color.WHITE);
            function = null;
        } else {
            function = selectFunction;
            selectFunction.setBackground(Color.RED);
            for (JButton i : buttonList) {
                if (i != selectFunction) {
                    i.setSelected(false);
                    i.setBackground(Color.WHITE);
                }
            }
        }
    }
}