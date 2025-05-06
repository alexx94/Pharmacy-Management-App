import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class CumparaFrame extends JFrame{
    private JTextField ct, nt;
    private JComboBox<String> listaP;
    private JTable tabel;
    private DefaultTableModel model;
    private ColectieProduse cp;
    private Factura factura;
    private AscultatorC a;
    private JButton b1, b2;
    private JCheckBox cb;
    private FacturaEfectiva fe;
    private boolean cuReteta;

    public CumparaFrame() {
        super ("Cos de cumparaturi");
        var p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 3, 20, 10));
        p1.setBackground(new Color(204, 236, 238));
        cp = ColectieProduse.getInstanta();	
        listaP = new JComboBox<>(cp.incarcaProduse());
        a = new AscultatorC();
        for (int i = 1; i <= 12; i++) {
            var p0 = new JPanel();
            p0.setBackground(new Color(204, 236, 238));
            if (i == 7) {
                p1.add(listaP);
            }
            else if (i == 2) {
                p1.add(new JLabel("Produse din Cos") {{
                    // din cursul 10, Clasa Component
                    setForeground(new Color(9, 93, 126));
                    setFont(new Font("Verdana", Font.BOLD, 22));
                }});
            }
            else if (i == 6) {
                p1.add(new JLabel("Cantitate") {{
                    setForeground(new Color(9, 93, 126));
                    setFont(new Font("Verdana", Font.BOLD, 14));
                }});
            }
            else if (i == 5) {
                p1.add(new JLabel("Cauta produs") {{
                    setForeground(new Color(9, 93, 126));
                    setFont(new Font("Verdana", Font.BOLD, 14));
                }});
            }
            else if (i == 8) {
                nt = new JTextField(20);
                p1.add(nt);
            }
            else if (i == 9) {
                ct = new JTextField(20);
                p1.add(ct);
            }
            else if (i == 10) {
                cb = new JCheckBox("Cu Reteta");
                cb.setBackground(new Color(204, 236, 238));
                p1.add(cb);
            }
            else if (i == 11) {
                b1 = new JButton("Adauga Produs");
                p1.add(b1);
                b1.addActionListener(a);
                b1.setBackground(Color.white);
            }
            else if (i == 12) {
                b2 = new JButton("Executa");
                b2.setEnabled(false);
                p1.add(b2);
                b2.addActionListener(a);
                b2.setBackground(Color.white);
            }
            else p1.add(p0);
        }
        String[] coloane = {"Produs", "Bucati", "Pret Baza", "Pret Vanzare", "Total"};
        model = new DefaultTableModel(coloane, 0);
        tabel = new JTable(model);
        tabel.setEnabled(false);
		tabel.setRowSelectionAllowed(true);
		tabel.getTableHeader().setReorderingAllowed(false);
        var p2 = new JScrollPane(tabel);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
    }

    private class AscultatorC implements ActionListener {
        private String info;
        private int cantitate;
        private JFrame f;

        AscultatorC(){
            cp = ColectieProduse.getInstanta();	 
            factura = Factura.getInstanta();	 
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                b2.setEnabled(true);
                cuReteta = cb.isSelected();
                info = (String) listaP.getSelectedItem(); //trebuie convertit ca e de tip object 
                Produs p = cp.cautaProdus(info);
                if (!ct.getText().equals("")) {
                    cantitate = Integer.parseInt(ct.getText());
                    if (nt.getText().equals("")) {
                        factura.adaugaProdusFactura(p, cantitate, model, cuReteta);
                    }
                    else {
                        info = nt.getText();
                        p = cp.cautaProdus(info);
                        if (p != null) {
                            factura.adaugaProdusFactura(p, cantitate, model, cuReteta);
                        }
                        else JOptionPane.showMessageDialog(null, "Introduceti un produs valid!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Introduceti cantitatea!", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (e.getSource() == b2) {
                cp.salveaza();
                fe = new FacturaEfectiva(tabel);
                f = new FacturaFrame(fe.toString());
                f.pack();
                f.setVisible(true);
            }
        }
    }
}