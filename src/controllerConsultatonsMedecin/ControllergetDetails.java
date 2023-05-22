package controllerConsultatonsMedecin;

import ConsultationSwing.getDetailsPatient;
import formulaires.GestionnaireConsultation;

public class ControllergetDetails {
	private viewConsultMed.getDetailsPatient getDetailsView;
	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	
	public ControllergetDetails(viewConsultMed.getDetailsPatient getDetailsPatient) {
		this.getDetailsView = getDetailsPatient;
	}
	
	public void retrivePatientInfo() {
		String patientInfo = gestionnaire.getDetailsPatient(getDetailsView.getDossierMedicale());
		getDetailsView.setDossierMedicale(patientInfo);
	}

}
