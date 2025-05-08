import Frames.LoginFrame;
import Frames.RoomDesigner;

import javax.swing.*;

// The Main class
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
//            new RoomDesigner().setVisible(true);
        });
    }
}
