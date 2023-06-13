package controllerViewMedecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import DossierMedicale.Dossier;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;
import viewMedecin.ViewAnamnese;

public class ControllerViewAnamnese {
	private viewMedecin.ViewAnamnese view;
	private GestionnaireConsultation model = new GestionnaireConsultation("root", "T1t4n1c0");
	private GestionnaireAdministratif modelAdmin = new GestionnaireAdministratif("root", "T1t4n1c0");
	
	public ControllerViewAnamnese(ViewAnamnese viewAnamnese) {
		this.view = viewAnamnese;
	}
	
	public Dossier getDossier(String id) throws SQLException {
		Dossier dossier = modelAdmin.findDossier(id);
		return dossier;
		
	}
	
	public void createDossier(String id) {
		try {
			modelAdmin.createDossier(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class UpdateDossier implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String text = view.getPanelText().getText();
			Dossier dossier = view.getDossier();
			dossier.setAntecedents(text);
			dossier.setDateCreation(LocalDateTime.now());
			try {
				modelAdmin.updateDossier(dossier);
				JOptionPane.showMessageDialog(view.getContentPane(), "Dossier Updated", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
				view.dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public String retriveAnamnse() {
		return model.getDetailsPatient(view.getIdPatient());
	}
	
	public String retrivePatientData() {
		return model.getNomPatient(view.getIdPatient());
	}
	
	

}
