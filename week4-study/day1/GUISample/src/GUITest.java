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
    }
}
