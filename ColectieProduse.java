import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ColectieProduse {
    private ArrayList<Produs> lp; 
    private BufferedReader br;
    private PrintWriter pw;
    private String l;
    private static ColectieProduse cp;

    public ColectieProduse() {
        File f = new File("produse.txt");
        lp = new ArrayList<>();
        Produs p;
        String[] s;
        if (f.exists()) {
            try{
                br=new BufferedReader(new FileReader(f));
                while((l = br.readLine()) != null) {
                    s = l.split("@"); // nu dupa spatiu ca sa nu fie probleme in caz ca sunt mai multe cuvinte
                    p = new Produs(s[0], Integer.parseInt(s[1]), Double.parseDouble(s[2]), s[3], s[4], s[5]);
                    lp.add(p);
                }
            }catch(IOException ioe){ioe.printStackTrace();}	
        }
        else {
            System.out.println("Fisierul nu exista");
        }
    }

    public String getProduse() {
        var rez = new StringBuffer();
        for(Produs p : lp) {
                rez.append(p.getInformatii());
                rez.append("\n");
            }
        return rez.toString();
	}

    public ArrayList<Produs> getListaProduse() {
        return lp;
    } 

    public Produs cautaProdus(String nume){
        for (Produs p : lp) {
            if (nume.toLowerCase().equals(p.getNume().toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    public void adaugaProdus(String n, int c, double p, String compozitie, String i, String ci) {
        lp.add(new Produs(n, c, p, compozitie, i, ci));
        salveaza();
        JOptionPane.showMessageDialog(null, "Produsul a fost adaugat cu succes!", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editeazaProdus(int cod, String n, int c, double pret, String compozitie, String i, String ci) {
        boolean b = true;
        if (cod < 0 || cod >= lp.size()) {
            b = false;
        }
        if (b) {
            Produs p = lp.get(cod);
            p.setNume(n);
            p.setCantitate(c);
            p.setPret(pret);
            p.setCompozitie(compozitie);
            p.setIndicatie(i);
            p.setContraindicatie(ci);
            salveaza();
        }
        else {
            JOptionPane.showMessageDialog(null, "Nu a fost gasit produsul! Introduceti un index valid!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void salveaza(){
        try{
            pw=new PrintWriter(new FileWriter("produse.txt"));
            for(Produs p : lp) {
                pw.println(p.toString());
            }
            pw.close();	
        }catch(IOException e){e.printStackTrace();}

    }

    public String[] incarcaProduse() {
        String[] s = new String[lp.size()];
        int index = 0;
        for (Produs p : lp) {
            s[index++] = p.getNume();
        }
        return s;
    }
	
    public static ColectieProduse getInstanta(){
        if (cp == null) cp =new ColectieProduse();
        return cp;
    }	
}