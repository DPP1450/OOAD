import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FunctionButton extends JButton {
    Frame frame;

    FunctionButton(String s, Frame frame) {
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

    void changeFunction() {
        frame.changeFunction(this);
    }
}