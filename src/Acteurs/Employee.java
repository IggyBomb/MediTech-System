package Acteurs;

public class Employee {
	private String id;
	private String nom;
	private String prenom;
	private String adresse;
	private double Salaire;
	public String getNom() {
		return nom;
	}
	
	public  Employee(String id, String nom, String prenom, String adresse, double Salaire) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.Salaire = Salaire;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public double getSalaire() {
		return Salaire;
	}
	public void setSalaire(double salaire) {
		Salaire = salaire;
	}
	
	public String getRole() {
		return "";
	}
	
	

}
