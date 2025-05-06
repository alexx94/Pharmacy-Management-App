import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Factura {
    private ArrayList<Produs> produse;
    private static Factura f;

    public Factura() {
        produse = new ArrayList<Produs>();
    }

    public void adaugaProdusFactura(Produs p, int cantitate, DefaultTableModel tabel, boolean cuReteta) {
        if (cantitate > p.getCantitate() || cantitate <= 0) {
            JOptionPane.showMessageDialog(null, "Stoc insuficient", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else {
            produse.add(new Produs(p.getNume(), cantitate, p.getPret(), p.getCompozitie(), p.getIndicatite(), p.getContraindicatie()));
            p.setCantitate(p.getCantitate() - cantitate);
            JOptionPane.showMessageDialog(null, "Produs adaugat cu succes!", "Information", JOptionPane.INFORMATION_MESSAGE);
            NumberFormat nf= NumberFormat.getInstance();
		    nf.setMaximumFractionDigits(3);
            if (cuReteta) {
                tabel.addRow(new Object[] {p.getNume(), cantitate, p.getPret(), String.format("%.2f", pretProduseReteta(p) * 1.1), String.format("%.2f", pretProduseReteta(p) * cantitate * 1.1)});
                if (p.getPret() != pretProduseReteta(p)) JOptionPane.showMessageDialog(null, "Medicament compensat cu reteta!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                tabel.addRow(new Object[] {p.getNume(), cantitate, p.getPret(), String.format("%.2f", calculProdusCuTVA(p)), String.format("%.2f", calculTotalProdus(p, cantitate))});
            }    
        }
    }

    public double calculProdusCuTVA(Produs p) {
        return p.getPret() * 1.1;
    }

    public double calculTotalProdus(Produs p, int cantitate) {
        return cantitate * p.getPret() * 1.1;
    }

    // FUNCTIE DE SCRIERE A FACTURII EFECTIVE, INAINTE DE PRINTARE cu string etc.

    public double pretProduseReteta(Produs p) {
        // cele doua liste reprezinta "keyworduri" pt a vedea de unde se poate compensa medicamentu
        // si se poate actualiza cu mai multe pt o acuratete mai mare
        String[] compensare_90 = {"cancer"};
        String[] compensare_100 = {"mintale", "mental", "schizofrenie", "depresie"};
        for (String s : compensare_90) {
            if (p.getIndicatite().toLowerCase().contains(s)) {
                return 0.9 * p.getPret();
            }
        }

        for (String s : compensare_100) {
            if (p.getIndicatite().toLowerCase().contains(s)) {
                return 0;
            }
        }

        return p.getPret();
    }

    public static Factura getInstanta(){
        if (f == null) f = new Factura();
        return f;
    }
}
