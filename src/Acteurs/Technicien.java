package Acteurs;

public class Technicien extends Employee {
	public Technicien(String id, String nom, String prenom, String adresse, double Salaire) {
		super(id, nom, prenom, adresse, Salaire);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public String toString() {
        return this.getNom() + " " + this.getPrenom();
    }
    
    @Override
    public String getRole() {
		return "technicien";
	}



}
