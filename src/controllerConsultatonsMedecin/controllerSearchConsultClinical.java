package controllerConsultatonsMedecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Acteurs.Patient;
import Consultation.Consultation;
import formulaires.GestionnaireAdministratif;
import formulaires.GestionnaireConsultation;
import viewConsultMed.ConsultMedView;

public class controllerSearchConsultClinical {
	private ConsultMedView consultView;
	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	private GestionnaireAdministratif Admin =  new GestionnaireAdministratif("root", "T1t4n1c0");


	public controllerSearchConsultClinical(ConsultMedView consultView) {
		this.consultView = consultView;
	}
	
	
    public void consultationRetrive() {
        String consultId = consultView.getIdConsult();
        Consultation consult = gestionnaire.getConsultationByID(consultId);
        ConsultMedView.setConsultation(consult);
    }

    public void retrivePatient() {
        Consultation consult = consultView.getConsultation();
        if (consult != null) {
            String patientId = consult.getPatient();
            Patient patient = Admin.findByID(patientId);
            consultView.setPatient(patient);
        }
    }
    /*
	public class getDetails implements ActionListener{
		public void actionPerformed(ActionEvent e) {

		}
		

	}
	*/
}
