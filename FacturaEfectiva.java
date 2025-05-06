import java.util.*;
import java.text.*;
import javax.swing.*;

public class FacturaEfectiva {
    private Calendar data;
    private ColectieMedici cm;
    private JTable tabel;

    public FacturaEfectiva(JTable tabel) {
        data = Calendar.getInstance();
        this.tabel = tabel;

    }

    public String toString() {
        cm = ColectieMedici.getInstanta();
        Medic m = cm.cautaMedicActiv();
        String sir = "FACTURA " + "\n";
        sir += "Data emiterii: " + DateFormat.getDateInstance(DateFormat.LONG).format(data.getTime()) + "\n";
        sir += "--------------------------------------------------------------------------------------\n";
        sir += "Medic: " + m.getInformatiiMedic() + "\n";
        sir += "--------------------------------------------------------------------------------------\n";
        sir += String.format("%-30s", "Produs") + String.format("%-15s", "Cantitate") + String.format("%-20s", "Pret vanzare") + String.format("%-20s", "Pret total") + "\n";
        for (int i = 0; i < tabel.getRowCount(); i++) {
            sir += String.format("%-30s", tabel.getValueAt(i, 0));
            sir += String.format("%-30s", tabel.getValueAt(i, 1));
            sir += String.format("%-20s", tabel.getValueAt(i, 3));
            sir += String.format("%-20s", tabel.getValueAt(i, 4)) + "\n";
        }
        sir += "--------------------------------------------------------------------------------------\n";
        sir += "\nTOTAL: " + calculTotalFactura() + " RON, TVA inclus\n";

        return sir;
    }

    public String calculTotalFactura() {
        double total = 0;
        for (int i = 0; i < tabel.getRowCount(); i++) {
            total += Double.parseDouble((String) tabel.getValueAt(i, 4));
        }
        return String.format("%.2f", total);
    }
}
