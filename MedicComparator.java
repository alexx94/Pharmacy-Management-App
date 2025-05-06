import java.util.*;

public class MedicComparator implements Comparator<Medic>{

    public int compare(Medic m1, Medic m2) {
        int rez = m1.getCod().compareTo(m2.getCod());
        return rez;
    }
}
