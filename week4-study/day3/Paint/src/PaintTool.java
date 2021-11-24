import java.awt.*;

public class PaintTool {

    public static void main(String[] args) {
        Frame frame = new Frame("Paint");
        frame.setSize(1280, 720);
        frame.setVisible(true);

        Panel panel = new Panel();

        frame.add(panel);
    }
}
