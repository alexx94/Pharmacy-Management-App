import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class VizualizeazaFrame extends JFrame{
    private JTextField baraCautare;
    private JTable tabel;
    private ColectieProduse cp;
    private ArrayList<Produs> lp;
    private AscultatorC a;
    
    public VizualizeazaFrame() {
        super ("Vizualizeaza Produse");
        var p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 3));
        p1.setBackground(new Color(204, 236, 238));
        baraCautare = new JTextField(25);
        for (int i = 1; i <= 9; i++) {
            var p0 = new JPanel();
            p0.setBackground(new Color(204, 236, 238));
            if (i == 5) {
                p1.add(baraCautare);
            }
            else if (i == 2) {
                p1.add(new JLabel("Cauta un produs:") {{
                    // din cursul 10, Clasa Component
                    setForeground(new Color(9, 93, 126));
                    setFont(new Font("Verdana", Font.BOLD, 16));
                }});
            }
            else p1.add(p0);
        }
        a = new AscultatorC();
        baraCautare.addActionListener(a);
        String[] coloane = {"Index", "Nume", "Stoc", "Pret fara TVA-RON", "Compozitie", "Indicatii", "Contraindicatii"};
        DefaultTableModel model = new DefaultTableModel(coloane, 0);

        cp = new ColectieProduse();
        lp = cp.getListaProduse();
        
        for (Produs p : lp) {
            model.addRow(new Object[]{lp.indexOf(p), p.getNume(), p.getCantitate(), p.getPret(), p.getCompozitie(), p.getIndicatite(), p.getContraindicatie()});
        }
        TableRowSorter<DefaultTableModel> sortare = new TableRowSorter<>(model);
        tabel = new JTable(model);
        tabel.setRowSorter(sortare);
		tabel.setEnabled(false);
		tabel.setRowSelectionAllowed(true);
		tabel.getTableHeader().setReorderingAllowed(false);

        add(p1, BorderLayout.NORTH);
        var p2 = new JScrollPane(tabel);
        add(p2, BorderLayout.CENTER);
    }
    
    private class AscultatorC implements ActionListener {
        private String info;

        AscultatorC(){
            cp = ColectieProduse.getInstanta();	 	 
        }

        public void actionPerformed(ActionEvent e) {
            info = baraCautare.getText();
            Produs p = cp.cautaProdus(info);
            if (p != null) {
                JOptionPane.showMessageDialog(null, p.getInformatii(), "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Nu exista produsul cautat", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
