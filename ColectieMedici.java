import java.util.*;

import javax.swing.JOptionPane;

import java.io.*; 

public class ColectieMedici {
    private TreeSet<Medic> ts;
	private BufferedReader br;
	private PrintWriter pw;
	private String l;
    private static ColectieMedici cm;

    public ColectieMedici() {
        File f=new File("medici.txt");
        ts=new TreeSet<>(new MedicComparator());
        Medic m;
        String[] s;
        if (f.exists()){
            try{
                br=new BufferedReader(new FileReader(f));
                while ((l=br.readLine()) != null) {
                    s=l.split(" ");
                    m = new Medic(s[0], s[1], s[2], s[3], s[4]);
                    ts.add(m);
                }
            }catch(IOException ioe){ioe.printStackTrace();}	
        
        } else System.out.println("Fisierul nu exista");
    }

    public boolean logareContMedic(String cod, String parolaDeVerificat) {
        Medic m = cautaMedic(cod);
        if (m == null) {
            JOptionPane.showMessageDialog(null, "Codul de medic nu exista!", "Information", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        else {
            if (parolaDeVerificat.equals(m.getParola())) {
                m.setLogat();
                return true;
            }
            else {
                System.out.println(m.getParola() + "-" + parolaDeVerificat + "-");
                JOptionPane.showMessageDialog(null, "Parola nu este corecta!", "Information", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
    }

    public String getMedici(){
        Medic m;
        var rez = new StringBuffer();
        Iterator<Medic> it = ts.iterator();
        while (it.hasNext()) {
            m = it.next();
			rez.append(m.toString());
            rez.append("\n");
		}
        return rez.toString();
    }

    public Medic cautaMedicActiv() {
        Medic m;
        Iterator<Medic> it = ts.iterator();
        while(it.hasNext()) {
            m = it.next();
            if (m.getLogat().equals("true")) {
                return m;
            }
        }
        return null;
    }

    public Medic cautaMedic(String cod) {
        Medic m;
        Iterator<Medic> it = ts.iterator();
        while (it.hasNext()) {
			m = it.next();
			if (cod.equals(m.getCod())) return m;
		}	
		return null;
    }

    public void resetareDelogat() {
        Medic m;
        Iterator<Medic> it = ts.iterator();
        while (it.hasNext()) {
			m = it.next();
			m.setDelogat();
		}	
    }

    public void salveaza(){
        try {
            pw = new PrintWriter(new FileWriter("medici.txt"));
            for(Medic m : ts) pw.println(m);
            pw.close();	
        }catch(IOException e){e.printStackTrace();}

    }
        
    public static ColectieMedici getInstanta(){
        if (cm == null) cm = new ColectieMedici();
        return cm;
    }	
}
