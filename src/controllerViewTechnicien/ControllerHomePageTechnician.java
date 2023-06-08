package controllerViewTechnicien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Acteurs.Medecin;
import Models.Consultation;
import Models.GestionnaireConsultation;
import Models.GestionnaireSuperAdmin;
import Models.Ordonnance;
import viewTechnicien.ViewHomePageTechnicien;

public class ControllerHomePageTechnician {
	private ViewHomePageTechnicien view;
	private GestionnaireConsultation model;
	private GestionnaireSuperAdmin modelSA;
	
	public ControllerHomePageTechnician(ViewHomePageTechnicien view) {
		this.view = view;
		model = new GestionnaireConsultation("root", "T1t4n1c0");
		modelSA = new GestionnaireSuperAdmin("root", "T1t4n1c0");
	}
	
	public class TableResultsSearch implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Consultation> consultations = model.listConsultationTechnician(view.getIdPatient());
			if (consultations.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No consultations", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"IdConsult", "dateCreation"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				for (Consultation consultation : consultations) {
					Object[] rowData = {
							consultation.getIdConsult(),
							consultation.getDate()
					};
					tableModel.addRow(rowData);
				}
				view.getTableResult().setModel(tableModel); // Set the table model for tableResults
			}
		}
	}
	
	public boolean searchOrdonnanceById(String id) {
		Consultation c = model.getConsultationByID(id);
		if(c == null) {
			JOptionPane.showMessageDialog(view.getContentPane(), "No consultations with this ID", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else {
			Ordonnance o = c.findOrdonnance();
			if(o == null) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No prescriptions associated with this consultation", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}else if("not prescribed".equals(o.getDeviceStatus())) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No medical device prescribed in this visit", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}else if("octroyé".equals(o.getDeviceStatus())) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Medical device already supplied", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}else {
				return true;
			}
		}
	}
	
	public class ShowResultsTableSearchByName implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Consultation> list = model.listConsultationsByPatientNameTechnician(view.getName());
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
