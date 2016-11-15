package file;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.File;

public class ChooserFile {
    private JFrame frame;

    public ChooserFile() {
        frame = new JFrame();
        frame.setVisible(true);
        bringToFront();
    }

    private void bringToFront() {
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);
    }

    public File getFile() {
        JFileChooser fc = new JFileChooser();
        if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)){
            frame.setVisible(false);
            return fc.getSelectedFile();
        }else {
            System.out.println("Next time select a text file.");
            System.exit(1);
        }
        return null;
    }

}
