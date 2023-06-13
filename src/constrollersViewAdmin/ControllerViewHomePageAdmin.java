package constrollersViewAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Acteurs.Medecin;
import Acteurs.Patient;
import Models.Consultation;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;
import Models.GestionnaireSuperAdmin;
import ViewAdmin.ViewHomePageAdmin;

public class ControllerViewHomePageAdmin {
	private ViewHomePageAdmin view;
	private GestionnaireSuperAdmin modelSA;
	private GestionnaireAdministratif model;
	private GestionnaireConsultation modelC;


	public ControllerViewHomePageAdmin(ViewHomePageAdmin view) {
		this.view = view;
		model = new GestionnaireAdministratif("root", "T1t4n1c0");
		modelSA = new GestionnaireSuperAdmin("root", "T1t4n1c0");
		modelC = new GestionnaireConsultation("root", "T1t4n1c0");
	}


	public class ShowResultsTableSearchByName implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Consultation> list = modelC.listConsultationPatientByName(view.getPatientNameConsult());
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No Consultations found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"IdConsult", "Patient", "Medecin", "DetailsCliniques", "Date"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				for(Consultation c : list) {
					Medecin medecin = (Medecin) modelSA.findEmployeeByID(c.getMedecin());
					String medecinName = medecin.getNom() + " " + medecin.getPrenom();
					String patientName = modelC.getNomPatient(c.getPatient());
					Object[] rowData = {
							c.getIdConsult(),
							patientName, 
							medecinName,
							c.getDetailsCliniques(),
							c.getDate()
					};
					tableModel.addRow(rowData);
				}
				view.getTableResultConsult().setModel(tableModel); // Set the table model for tableResult
			}
		}
	}

	public class ShowAllPatients implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Patient> list = model.getAllPatient();
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No patient in the database", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"ID", "Last Name", "First Name", "Address",  "Birthdate"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				for (Patient patient : list) {
					Object[] rowData = {
							patient.getIdPatient(),
							patient.getNom(), 
							patient.getPrenom(), 
							patient.getAdresse(),
							patient.getDateNaissance()
					};
					tableModel.addRow(rowData);
				}
				view.getTableResultPatient().setModel(tableModel);
			}
		}
	}

	public boolean checkId(String id) {
		Patient p = model.findPatientByID(id);
		if(p == null) {
			System.out.println("No patient");
			return false;
		}else {
			return true;
		}
	}
}
