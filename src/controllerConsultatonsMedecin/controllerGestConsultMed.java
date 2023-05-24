package controllerConsultatonsMedecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Consultation.Consultation;
import formulaires.GestionnaireConsultation;
import viewConsultMed.GestionnaireViewMed;

public class controllerGestConsultMed {
	private GestionnaireViewMed gestVisitMed;
	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	
	public controllerGestConsultMed(GestionnaireViewMed gestionnaireVisitMed) {
		this.gestVisitMed = gestionnaireVisitMed;
	}
	
	public class searchConsultationByID implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation consultation = gestionnaire.getConsultationByID(gestVisitMed.getidConsultSearch());
			if (consultation == null) {
				JOptionPane.showMessageDialog(gestVisitMed.getContentPane(), "ID not found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"IdConsult", "PatientID", "MedecinID", "DetailsCliniques", "Date"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				Object[] rowData = {
						consultation.getIdConsult(),
						consultation.getPatient(), 
						consultation.getMedecin(), 
						consultation.getDetailsCliniques(),
						consultation.getDate()
				};
				tableModel.addRow(rowData);
				gestVisitMed.getTableResult().setModel(tableModel); // Set the table model for tableResult
			}
		}
	}
}
