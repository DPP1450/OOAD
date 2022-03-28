import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FunctionButton extends JButton {
    UmlFrame frame;

    FunctionButton(String s, UmlFrame frame) {
        super(s);
        this.frame = frame;
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeFunction();
            }
        });
    }

    private void changeFunction() {
        frame.changeFunction(this);
    }
}