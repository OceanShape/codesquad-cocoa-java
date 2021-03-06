import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUITest {

    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(1080, 720);
        f.setVisible(true);
        f.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent e){
                e.getWindow().setVisible(false);
                e.getWindow().dispose();
                System.exit(0);
            }

            public void windowOpened(WindowEvent e) {}
            public void windowClosed(WindowEvent e){}
            public void windowIconified(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
        });

        Panel panel = new Panel() {
            Image image = new ImageIcon(Main.class.getResource( "./Aptenodytes forsteri sample.jpg")).getImage();
            public void paint(Graphics g) {
                int w = f.getWidth() / 2 - image.getWidth(null) / 2;
                int h = f.getHeight() / 2 - image.getHeight(null) / 2;
                g.drawImage(image, w, h, null);
            }
        };
        f.add(panel);
    }
}
