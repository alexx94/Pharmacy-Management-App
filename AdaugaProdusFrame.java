import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdaugaProdusFrame extends JFrame{
    private JTextField nt, ct, pt, compozitieText, indicatieText, contraindicatieText;
    private JButton adauga;
    private AscultatorC a;

    public AdaugaProdusFrame() {
        super ("Adaugarea unui produs nou");
        var p=new JPanel(new GridLayout(6,2,10,10));
        p.setBackground(new Color(204, 236, 238));	
            
        p.add(new JLabel("Nume"));
        nt = new JTextField(20);
        p.add(nt);
            
        p.add(new JLabel("Cantitate"));
        ct = new JTextField(10);
        p.add(ct);
        
        p.add(new JLabel("Pret"));
        pt = new JTextField(10);
        p.add(pt);

        p.add(new JLabel("Compozitie"));
        compozitieText = new JTextField(10);
        p.add(compozitieText);

        p.add(new JLabel("Indicatie"));
        indicatieText = new JTextField(10);
        p.add(indicatieText); 

        p.add(new JLabel("Contraindicatie"));
        contraindicatieText = new JTextField(10);
        p.add(contraindicatieText);
        add(p);
            
        a = new AscultatorC();
        p = new JPanel();
        p.setBackground(new Color(9, 93, 126));
        adauga = new JButton("Adauga Produs");
        adauga.addActionListener(a);
        p.add(adauga);

        add(p, BorderLayout.SOUTH);		
        setLocation(450,450);
    }

    private class AscultatorC implements ActionListener{
        private  ColectieProduse cp;

        AscultatorC(){
            cp = ColectieProduse.getInstanta();	 	 
        }
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == adauga) {
                if (!nt.getText().equals("") && !ct.getText().equals("") && !pt.getText().equals("") && !compozitieText.getText().equals("") && !indicatieText.getText().equals("") && !contraindicatieText.getText().equals("")) {
                    cp.adaugaProdus(nt.getText(), Integer.parseInt(ct.getText()), Double.parseDouble(pt.getText()), compozitieText.getText(), indicatieText.getText(), contraindicatieText.getText());
                }
                }
                System.out.println(cp.getProduse());
                AdaugaProdusFrame.this.dispose();
            }
    }
}