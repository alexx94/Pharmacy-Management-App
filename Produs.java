import java.text.NumberFormat;

public class Produs {
    private String nume;
    private int cantitate;
    private double pret;
    private String compozitie;
    private String indicatie;
    private String contraindicatie;

    public Produs(String nume, int cantitate, double pret, String compozitie, String indicatie, String contraindicatie) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
        this.compozitie = compozitie;
        this.indicatie = indicatie;
        this.contraindicatie = contraindicatie;
    }

    public String getNume() {
        return this.nume;
    }

    public void setNume(String n) {
		this.nume = n;
	}
	
	public int getCantitate() {
		return this.cantitate;
	}
	
	public void setCantitate(int c) {
		this.cantitate = c;
	}
	
	public double getPret() {
		return this.pret;
	}
	
	public void setPret(double p) {
		this.pret = p;
	}

	public String getCompozitie() {
		return this.compozitie;
	}
	
	public void setCompozitie(String s) {
		this.compozitie = s;
	} 
	
	public String getIndicatite() {
		return this.indicatie;
	}
	
	public void setIndicatie(String s) {
		this.indicatie = s;
	} 
	
	public String getContraindicatie() {
		return this.contraindicatie;
	}
	
	public void setContraindicatie(String s) {
		this.contraindicatie = s;
	}

	public String toString(){
		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
  		return nume+"@"+cantitate+"@"+nf.format(pret)+"@"+compozitie+"@"+indicatie+"@"+contraindicatie;
	}

	public String getInformatii(){
		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		return nume+" - "+cantitate+" buc. - "+nf.format(pret)+" Lei (fara TVA) - "+compozitie+" - "+indicatie+" - "+contraindicatie;
	}
}
