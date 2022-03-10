import java.awt.*;
import javax.swing.*;

public class Port extends JPanel {

    public Block block;

    Port(Block block) {
        this.setLayout(null);
        this.setSize(10, 10);
        this.setBackground(Color.BLUE);
        this.block = block;
    }
}
