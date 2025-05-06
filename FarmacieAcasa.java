import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FarmacieAcasa extends JFrame{
    private JButton[] b;
    private AscultatorC a;

    public FarmacieAcasa() {
        super("EFarmacie Aplicatie");
        var p = new JPanel();
        p.setBackground(new Color(204, 236, 238));

        p.add(new JLabel("EFarmacie APP") {{
            // din cursul 10, Clasa Component
            setForeground(new Color(9, 93, 126));
            setFont(new Font("Verdana", Font.BOLD, 20));
        }});

        var p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        p2.setBackground(new Color(204, 236, 238));

        String[] s = new String[]{"Vizualizeaza", "Modifica Stoc", "Cumpara"};
        a = new AscultatorC();
        b = new JButton[3];
        for (int i = 0; i < b.length; i++) {
            b[i] = new JButton(s[i]);
            b[i].addActionListener(a);
            b[i].setBackground(new Color(241, 249, 255));
            p2.add(b[i]);
        }
        
        add(p, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
    }

    private class AscultatorC implements ActionListener {
        private JFrame f;
        private ColectieMedici cm;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b[0]) {
                cm = ColectieMedici.getInstanta();
                System.out.println(cm.getMedici());

                f = new VizualizeazaFrame();
                f.pack();
                f.setVisible(true);
            }
            else if (e.getSource() == b[1]) {
                f = new ModificaStocFrame();
                f.pack();
                f.setVisible(true);
            }

            else if (e.getSource() == b[2]) {
                f = new CumparaFrame();
                f.pack();
                f.setVisible(true);
            }
            
        }
    }
}
