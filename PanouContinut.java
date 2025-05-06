import java.awt.Color;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.*;

public class PanouContinut extends JPanel implements Printable {
    private BufferedReader br;
    private JTextArea arie;
    private String sir = "";

    public PanouContinut(JTextArea arie) {
        this.arie = arie;
    }
    
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        g.setColor(Color.black);
        try {
            StringReader continut = new StringReader(arie.getText());
                br = new BufferedReader(continut);
                int i = 0;
                while ((sir = br.readLine()) != null) {
                    if (sir.length() == 0) sir = " ";
                    g.drawString(sir, 100, 100 + i);
                    i += 20;
                }
            } catch (IOException io) {

            } catch (IllegalArgumentException ie) {

            }
            return Printable.PAGE_EXISTS;
    }

}

