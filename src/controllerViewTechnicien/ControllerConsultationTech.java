package controllerViewTechnicien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Models.Consultation;
import Models.GestionnaireConsultation;
import Models.Ordonnance;
import viewTechnicien.ViewConsultationTech;

public class ControllerConsultationTech {
	private ViewConsultationTech view;
	private GestionnaireConsultation gestionnaire;
	private Consultation consult;

	public ControllerConsultationTech(ViewConsultationTech view) {
		this.view = view;
		gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	}

	public String getPrescription() {
		this.consult = gestionnaire.getConsultationByID(view.getIdConsultation());
		String appareilMed = "Description: \n" + consult.findOrdonnance().getAppareilMedical() + "\n" +"status: " + consult.findOrdonnance().getDeviceStatus();
		return appareilMed;
	}

	public class octroyerMedicalDevice implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation consult = gestionnaire.getConsultationByID(view.getIdConsultation());
			Ordonnance ordonnance = consult.findOrdonnance();
			ordonnance.setDeviceStatus("octroyé");
			boolean update = ordonnance.updateOrdonnance();
			if(update) {
				JOptionPane.showMessageDialog(view.getJPanel(), "Medical device issued", "Consultation Technicien", JOptionPane.INFORMATION_MESSAGE);
				view.dispose();
			}else {
				JOptionPane.showMessageDialog(view.getJPanel(), "Error", "Consultation Technicien", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
