package DossierMedicale;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import Acteurs.Patient;
import Consultation.Consultation;

public class Dossier {
	private LocalDateTime dateCreation;
	private String antecedents;
	private List<Consultation> consultations;
	private String idDossier;
	private Patient patient;

	public Dossier(Patient p) {
		this.patient = p;
		this.idDossier = p.getIdPatient();
		this.dateCreation = LocalDateTime.now();
		this.antecedents = "";
		this.consultations = new ArrayList<Consultation>();
	}


	public void addConsultation(Consultation consult) {
		this.consultations.add(consult);
	}

	
    // Removes a consultation from the list by ID, returns true if a consultation was removed
	public boolean removeConsultation(String idConsult) {
		Consultation c = null;
		int i = 0;
		while (i < consultations.size()) {
			c = consultations.get(i);
			if (c.getIdConsult().equals(idConsult)) {
				consultations.remove(c);
				return true;
			}
			i++;
		}
		return false;
	}
	
	//Search consultation by ID
	public Consultation searchConsultationID(String IdConsult) {
	    for (Consultation c : consultations) {
	        if (c.getIdConsult().equals(IdConsult)) {
	            return c;
	        }
	    }
	    return null;
	}
	
	//Search consultation by date
	public Consultation searchConsultationDate(LocalDateTime dateConsult) {
	    for (Consultation c : consultations) {
	        if (c.getDate().equals(dateConsult)) {
	            return c;
	        }
	    }
	    return null;
	}


    // Prints a list of all consultations, with each consultation's details on a separate line
	public String printConsultations() {
		StringBuilder sb = new StringBuilder();
		sb.append("List of consultations:\n");
		for (Consultation consultation : consultations) {
			sb.append("ID: ")
			.append(consultation.getIdConsult())
			.append(", Details: ")
			.append(consultation.getDetailsCliniques())
			.append(", Medecin: ")
			.append(consultation.getMedecin())
			.append(", Date: ")
			.append(consultation.getDate())
			.append("\n");
		}
		return sb.toString();
	}
	
	public void clearDossier() {
		this.consultations.clear();
	}
	
	//getters and setters
	
	
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getAntecedents() {
		return antecedents;
	}


	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}


	public String getIdDossier() {
		return idDossier;
	}

	public void setIdDossier(String idDossier) {
		this.idDossier = idDossier;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public List<Consultation> getConsultations() {
		return consultations;
	}


	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}


	


}