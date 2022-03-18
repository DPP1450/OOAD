import java.awt.*;
import javax.swing.*;

public class Port extends JPanel {

    public Block block;
    public int height = 10;
    public int width = 10;

    Port(Block block) {
        this.setLayout(null);
        this.setSize(height, height);
        this.setBackground(Color.BLUE);
        this.block = block;
    }
}
