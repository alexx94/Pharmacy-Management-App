import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PaginaPrincipala extends JFrame{
    private JTextField user;
    private JPasswordField parola;
    private JButton login, client;
    private ColectieMedici cm;
    private AscultatorC a;

    PaginaPrincipala() {
        super("EFarmacie Logare");
        var p1 = new JPanel();
        p1.setBackground(Color.white);
        p1.add(new JLabel("Logare Cont Medic") {{
            setForeground(new Color(9, 93, 126));
            setFont(new Font("Verdana", Font.BOLD, 20));
        }});
        var p = new JPanel();
        p.setLayout(new GridLayout(5, 2, 15, 15));
        p.setBackground(Color.white);
        user = new JTextField(20);
        parola = new JPasswordField(20);
        login = new JButton("Login");
        login.setBackground(Color.cyan);
        client = new JButton("Modul Client");
        client.setBackground(Color.cyan);
        a = new AscultatorC();
        login.addActionListener(a);
        client.addActionListener(a);
        for (int i = 1; i <= 10; i++) {
            var p0 = new JPanel();
            p0.setBackground(Color.white);
            if (i == 1) {
                p.add(new JLabel("Cod/User:"));
            }
            else if (i == 2) {
                p.add(user);
            }
            else if (i == 3) {
                p.add(new JLabel("Parola:"));
            }
            else if (i == 4) {
                p.add(parola);
            }
            else if (i == 5) {
                p.add(login);
            }
            else if (i == 6) {
                p.add(client);
            }
            else p.add(p0);
        }

        add(p1, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);
    }

    private class AscultatorC implements ActionListener {
        private String info;
        private char[] parola_info;
        private JFrame f;

        AscultatorC(){
            cm = ColectieMedici.getInstanta();	 
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                cm.resetareDelogat();
                info = user.getText();
                parola_info = parola.getPassword();
                String parola_decriptata = new String(parola_info);
                parola_info = parola.getPassword(); 
                if (cm.logareContMedic(info, parola_decriptata)) {
                    f = new FarmacieAcasa();
                    f.pack();
                    f.setVisible(true);
                }
            }
            else if (e.getSource() == client) {
                cm.resetareDelogat();
                JOptionPane.showMessageDialog(null, "Modul client permite doar Vizualizarea/Cautarea produselor din stoc.\nPentru a cumpara, este nevoie de prezenta unui medic/farmacist!", "Information", JOptionPane.INFORMATION_MESSAGE);
                f = new VizualizeazaFrame();
                f.pack();
                f.setVisible(true);
            }
            
        }
    }
}
