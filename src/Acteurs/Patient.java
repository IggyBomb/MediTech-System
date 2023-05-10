package Acteurs;

import java.time.LocalDateTime;

import DossierMedicale.Dossier;

public class Patient {
	private String idPatient;
	private String nom;
	private String prenom;
	private LocalDateTime dateNaissance;
	private String adresse;
	private Dossier dossier;
	
	
	/**

	Creates a new Patient object with the specified attributes.
	@param nom the patient's last name
	@param prenom the patient's first name
	@param adresse the patient's address
	@param dateNaissance the patient's date of birth as a LocalDateTime object
	@param Id the patient's unique identifier
	*/
	public Patient(String nom, String prenom, String adresse,LocalDateTime dateNaissance,  String Id) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.idPatient = Id;
	}
	
	public LocalDateTime getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDateTime dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getNom() {
		return nom;
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

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	
    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }


	

}
