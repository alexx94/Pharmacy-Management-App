import java.awt.event.*;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        var f = new PaginaPrincipala();
        f.pack();
        //f.setSize(500, 300);
        f.setLocation(350, 350);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
