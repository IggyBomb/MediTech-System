package Acteurs;

public class AgentAdministration extends Employee {

	public AgentAdministration(String id, String nom, String prenom, String adresse, double Salaire) {
		super(id, nom, prenom, adresse, Salaire);
	}
	
    @Override
    public String toString() {
        return this.getNom() + " " + this.getPrenom();
    }
    
    @Override
    public String getRole() {
		return "admin";
	}



}
