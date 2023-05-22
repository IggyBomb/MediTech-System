package Main;

import java.time.LocalDateTime;

import Acteurs.Medecin;
import Acteurs.Patient;
import Consultation.Consultation;
import formulaires.GestionnaireAdministratif;
import formulaires.GestionnaireConsultation;

public class Main {

	public static void main(String[] args) {
		GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
		Consultation cons = gestionnaire.getConsultationByID("test");
		System.out.println(cons.toString());
	}

}
