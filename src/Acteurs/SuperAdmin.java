package Acteurs;



public class SuperAdmin extends Employee {
	public SuperAdmin(String id, String nom, String prenom, String adresse, double Salaire) {
		super(id, nom, prenom, adresse, Salaire);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public String getRole() {
		return "admin";
	}


}
