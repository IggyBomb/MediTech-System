package controllerViewMedecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

import Models.Consultation;
import Models.GestionnaireConsultation;
import Models.Ordonnance;
import viewMedecin.ViewOrdonnance;

public class ControllerViewOrdonnance {
	 private ViewOrdonnance view;
	 private GestionnaireConsultation modelConsult = new GestionnaireConsultation("root", "T1t4n1c0");

	 
	 public ControllerViewOrdonnance(ViewOrdonnance view) {
		 this.view = view;
	 }
	 
	 
	 public class setOrdonnace implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation c = modelConsult.getConsultationByID(view.getIdConsult());
			try {
				c.createOrdonnance();
				String medicaments = view.getEditorPane_medicaments();
				String soins = view.getEditorPane_propSoins();
				String devices = view.getEditorPane_appareilMed();
				Ordonnance o = c.findOrdonnance();
				o.setMedicaments(medicaments);
				o.setPropositionsSoins(soins);
				o.setAppareilMedical(devices);
				o.updateOrdonnance();
				JOptionPane.showMessageDialog(view.getContentPane(), "Prescription created", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
				view.dispose();
			} catch (SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Prescription already in the database", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	 }

}
