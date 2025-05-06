import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.awt.print.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FacturaFrame extends JFrame{
    private String factura;
    private JButton print, cancel, printFile;
    private JTextArea fArie;
    private AscultatorC a;
    private PrintWriter out;

    public FacturaFrame(String factura) {
        super("Factura");
        var p = new JPanel();
        p.setBackground(Color.white);
        fArie = new JTextArea(factura, 20, 50);
        fArie.setEditable(false);
        add(fArie, BorderLayout.CENTER);

        // adaugare butoane, borderlayout.EAST
        a = new AscultatorC();
        print = new JButton("Print");
        printFile = new JButton("Print File");
        cancel = new JButton("Cancel");
        print.addActionListener(a);
        printFile.addActionListener(a);
        cancel.addActionListener(a);
        var p1 = new JPanel();
        p1.add(print);
        p1.add(printFile);
        p1.add(cancel);

        add(p1, BorderLayout.EAST);
    }

    private class AscultatorC implements ActionListener {

        private AscultatorC() {

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cancel) {
                setVisible(false);
            }
            else if (e.getSource() == print) {
                PrinterJob imprimanta = PrinterJob.getPrinterJob();
                Book bk = new Book();
                bk.append(new PanouContinut(fArie), imprimanta.defaultPage());
                imprimanta.setPageable(bk);
                if (imprimanta.printDialog()) {
                    try {
                        imprimanta.print();
                    } catch (PrinterException pe) {
                        fArie.append("Imprimanta nu exista");
                        fArie.repaint();
                    }
                }
            }
            else if (e.getSource() == printFile ) {
                DateFormat data = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
                String ora_data = data.format(new Date()).replace("/", "").replace(" ", "_").replace(":", "").replace(",", "");
                String fisier = "Facturi/factura_" + ora_data + ".dat";
                try {
                    out = new PrintWriter(new FileOutputStream(fisier));
                    out.write(fArie.getText());
                    out.flush();
                    out.close();
                } catch(FileNotFoundException fe) {}
                  catch (IOException ioe) {}
            }
        }
    } 
}
