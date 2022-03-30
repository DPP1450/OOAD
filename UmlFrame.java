import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UmlFrame extends JFrame {

    public Canvas canvas;
    private JMenuBar menuBar;
    private JMenuItem ButtonGroup;
    private JMenuItem ButtonUngroup;
    private JMenuItem changeObjectName;
    private JMenu edit;
    private JPanel functionSelect;
    public FunctionButton select;
    public FunctionButton associationLine;
    public FunctionButton generalizationLine;
    public FunctionButton compositionLine;
    public FunctionButton classBtn;
    public FunctionButton useCase;
    public FunctionButton function;
    public ArrayList<FunctionButton> buttonList;

    UmlFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("UML");
        this.setResizable(false);
        this.setSize(1280, 720);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(null);
        newItem(); // 新增frame 上的物件
        addItem(); // 把他加到frame 中
        initLayout(); // 佈局
        groupAddActionListener(); // 增加meunbar內選項的功能
        validate();
    }

    public void changeFunction(FunctionButton selectFunction) {
        if (function != selectFunction) {
            function = selectFunction;
            selectFunction.setBackground(Color.RED);
            for (JButton i : buttonList) {
                if (i != selectFunction) {
                    i.setSelected(false);
                    i.setBackground(Color.WHITE);
                }
            }
            canvas.setAllUnvisible();
            canvas.renameCandidateBlock = null;
        }
    }

    private void groupAddActionListener() {
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
    }

    private void initLayout() {
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
    }

    private void newItem() {
        buttonList = new ArrayList<FunctionButton>();
        functionSelect = new JPanel();
        ButtonGroup = new JMenuItem("group");
        ButtonUngroup = new JMenuItem("ungroup");
        changeObjectName = new JMenuItem("change object name");
        edit = new JMenu("Edit");
        menuBar = new JMenuBar();
        canvas = new Canvas(this);
        functionSelect = new JPanel();
        select = new FunctionButton("select", this);
        associationLine = new FunctionButton("<html>association<br>line</html>", this);
        generalizationLine = new FunctionButton("<html>generalization<br>line</html>", this);
        compositionLine = new FunctionButton("<html>composition<br>line</html>", this);
        classBtn = new FunctionButton("class", this);
        useCase = new FunctionButton("use case", this);
        buttonList = new ArrayList<FunctionButton>();
    }

    private void addItem() {
        buttonList.add(select);
        buttonList.add(associationLine);
        buttonList.add(generalizationLine);
        buttonList.add(compositionLine);
        buttonList.add(classBtn);
        buttonList.add(useCase);
        edit.add(ButtonGroup);
        edit.add(ButtonUngroup);
        edit.add(changeObjectName);
        menuBar.add(edit);
        this.add(functionSelect);
        this.add(menuBar);
        this.add(canvas);
        this.add(functionSelect);
        this.setJMenuBar(menuBar);
        functionSelect.setLayout(null);
        functionSelect.setBackground(Color.gray);
        functionSelect.add(select);
        functionSelect.add(associationLine);
        functionSelect.add(generalizationLine);
        functionSelect.add(compositionLine);
        functionSelect.add(classBtn);
        functionSelect.add(useCase);
    }
}