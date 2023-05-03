package formulaires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Acteurs.Patient;
import Consultation.Consultation;
import DossierMedicale.Dossier;
import ordonnance.AppareilMedical;
import ordonnance.Ordonnance;

public class GestionnaireAppareilMedical {

	private Patient patient;
	private static Map<String, AppareilMedical> listeAppareilsMedicaux = new HashMap<String, AppareilMedical>();
	private static Map<String, Patient> listePatients = GestionnaireAdministratif.getListePatients();

	public GestionnaireAppareilMedical() {

	}

	// Search for a patient by ID
	public Patient searchPatient(String ID) {
		return listePatients.get(ID);
	}


	/**
	 * This method returns a list of Consultation objects where the associated Ordonnance contains at least one AppareilMedical.
	 * 
	 * @param p The Patient object for whom the consultations with AppareilMedical objects are to be retrieved.
	 * @return A list of Consultation objects that have an Ordonnance containing at least one AppareilMedical.
	 */
	public List<Consultation> listConsultations(Patient p) {
		ArrayList<Consultation> listConsult = new ArrayList<Consultation>();
		Dossier dossier = p.getDossier();
		Ordonnance ord;
		for(Consultation c : dossier.getConsultations()) {
			ord = c.getOrdonnance();
			if(!ord.getAppareilsMedicaux().isEmpty()) {
				listConsult.add(c);
			}	
		}
		return listConsult;
	}
	
	
	// print the list of consultation and access the one selected
	public Consultation accessConsultation(List<Consultation> list) {
		//if the list is empty it ends the method
	    if (list.isEmpty()) {
	        System.out.println("There are no consultations with AppareilMedical for this patient.");
	        return null;
	    }
	    //print the list of consultation
	    int index = 1;
	    for (Consultation c : list) {
	        System.out.println(index + " " + c.getDate() + " " + c.getDetailsCliniques());
	        index++;
	    }
	    Scanner scan = new Scanner(System.in);
	    int consultationIndex = -1;
	    //enters an infinite loop that is broken only if the user select a valid index
	    while (true) {
	        System.out.println("Select index");
	        if (!scan.hasNextInt()) {
	            System.out.println("Please select a valid index.");
	            scan.nextLine();
	        } else {
	            consultationIndex = scan.nextInt() - 1;
	            if (consultationIndex >= 0 && consultationIndex < list.size()) {
	                break;
	            } else {
	                System.out.println("Please select a valid index.");
	            }
	        }
	    }
	    scan.close();
	    return list.get(consultationIndex);
	}
	
	public void octroyerAppareilMedical(Consultation c) {
		List<AppareilMedical> listeAppareils = c.getOrdonnance().getAppareilsMedicaux();
		if(listeAppareils.isEmpty()) {
			System.out.println("No orthoses were prescribed during this visit");
			return;
		}
		for(AppareilMedical a : listeAppareils) {
			if(a.isInstance() && !a.isOctroye()) {
				a.setOctroye(true);
				System.out.println(a.getName() + " - model : " + a.getModel() + " - to provide");
			}else if(a.isInstance()&& a.isOctroye()) {
				System.out.println(a.getName() + " - model : " + a.getModel() + "- already provided");
			}
		}
	}

	//getters and setters
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
