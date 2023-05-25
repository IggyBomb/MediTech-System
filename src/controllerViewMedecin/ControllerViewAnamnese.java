package controllerViewMedecin;

import Models.GestionnaireConsultation;

public class ControllerViewAnamnese {
	private viewMedecin.ViewAnamnese view;
	private GestionnaireConsultation model = new GestionnaireConsultation("root", "T1t4n1c0");
	
	public ControllerViewAnamnese(viewMedecin.ViewAnamnese getDetailsPatient) {
		this.view = getDetailsPatient;
	}
	
	public void retrivePatientInfo() {
		String patientInfo = model.getDetailsPatient(view.getDossierMedicale());
		view.setDossierMedicale(patientInfo);
	}

}
