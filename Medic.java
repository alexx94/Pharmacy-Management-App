public class Medic {
    private Persoana p;
    private String cod, parola, nume, prenume, logat;

    public Medic(String cod, String parola, String nume, String prenume, String logat) {
        p = new Persoana(nume, prenume);
        this.cod = cod;
        this.parola = parola;
        this.logat = "false";
    }

    public void setLogat() {
        this.logat = "true";
    }

    public void setDelogat() {
        this.logat = "false";
    }

    public String getLogat() {
        return this.logat;
    }

    public String getCod() {
        return this.cod;
    }

    public void setCod(String s) {
        this.cod = s;
    }

    public String getParola() {
        return parola;
    }

    public String getInformatiiMedic() {
        return p.getNume() + " " + p.getPrenume() + " - " + cod;
    }

    public String toString() {
        return cod + " " + parola + " " + p.getNume() + " " + p.getPrenume() + " " + logat;
    }
}