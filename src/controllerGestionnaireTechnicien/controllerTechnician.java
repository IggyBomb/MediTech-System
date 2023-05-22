package controllerGestionnaireTechnicien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Consultation.Consultation;
import ViewGestionnaireConsultTech.ViewGestionnaireTech;
import formulaires.GestionnaireConsultation;

public class controllerTechnician {
	private ViewGestionnaireTech view;
	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	
	public controllerTechnician(ViewGestionnaireTech view) {
		this.view = view;
	}
	
	public class searchConsultationsByID implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Consultation> consultations = gestionnaire.listConsultationTechnician(view.getIdPatient());
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
}
