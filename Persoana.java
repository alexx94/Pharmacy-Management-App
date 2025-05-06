public class Persoana{
    private String nume, prenume;
           
    public Persoana(String nume, String pren) {
       this.nume=nume;
       this.prenume=pren;
    }
   
    public String getNume(){
       return nume;
    }
   
    public String getPrenume(){
       return prenume;
       }	
           
    public String toString(){
       return nume+" "+prenume;
       
    }	
}
