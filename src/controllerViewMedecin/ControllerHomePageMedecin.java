package controllerViewMedecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Acteurs.Medecin;
import Models.Consultation;
import Models.GestionnaireConsultation;
import Models.GestionnaireSuperAdmin;
import viewMedecin.ViewHomePageMedecin;

public class ControllerHomePageMedecin {
	private ViewHomePageMedecin view;
	private GestionnaireConsultation model = new GestionnaireConsultation("root", "T1t4n1c0");
	private GestionnaireSuperAdmin modelSA = new GestionnaireSuperAdmin("root", "T1t4n1c0");

	public ControllerHomePageMedecin(ViewHomePageMedecin gestionnaireVisitMed) {
		this.view = gestionnaireVisitMed;
	}

	public class ShowResultsTable implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation consultation = model.getConsultationByID(view.getidConsultSearch());
			if (consultation == null) {
				JOptionPane.showMessageDialog(view.getContentPane(), "ID not found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"IdConsult", "Patient", "Medecin", "DetailsCliniques", "Date"};
				Medecin medecin = (Medecin) modelSA.findEmployeeByID(consultation.getMedecin());
				String medecinName = medecin.getNom() + " " + medecin.getPrenom();
				String patientName = model.getNomPatient(consultation.getPatient());
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				Object[] rowData = {
						consultation.getIdConsult(),
						patientName, 
						medecinName,
						consultation.getDetailsCliniques(),
						consultation.getDate()
				};
				tableModel.addRow(rowData);
				view.getTableResult().setModel(tableModel); // Set the table model for tableResult
			}
		}
	}

	public boolean SearchConsultationByID(String idC) {
		Consultation c = model.getConsultationByID(idC);
		if(c == null) {
			return false;
		}else {
			return true;
		}
	}

	public class ShowResultsTableSearchByName implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Consultation> list = model.listConsultationPatientByName(view.getName());
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No Consultations found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"IdConsult", "Patient", "Medecin", "DetailsCliniques", "Date"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				for(Consultation c : list) {
					Medecin medecin = (Medecin) modelSA.findEmployeeByID(c.getMedecin());
					String medecinName = medecin.getNom() + " " + medecin.getPrenom();
					String patientName = model.getNomPatient(c.getPatient());
					Object[] rowData = {
							c.getIdConsult(),
							patientName, 
							medecinName,
							c.getDetailsCliniques(),
							c.getDate()
					};
					tableModel.addRow(rowData);
				}
				view.getTableResult().setModel(tableModel); // Set the table model for tableResult
			}
		}
	}
}
