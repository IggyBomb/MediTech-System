package controllerViewMedecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Acteurs.Medecin;
import Acteurs.Patient;
import Models.Consultation;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;
import Models.GestionnaireSuperAdmin;
import Models.Ordonnance;
import viewMedecin.ViewConsultationMedecin;
import viewMedecin.ViewOrdonnance;

public class ControllerViewConsultationMedecin {
	private ViewConsultationMedecin view;
	private GestionnaireConsultation modelConsult = new GestionnaireConsultation("root", "T1t4n1c0");
	private GestionnaireAdministratif modelAdmin =  new GestionnaireAdministratif("root", "T1t4n1c0");
	private GestionnaireSuperAdmin modelSuperAdmin = new GestionnaireSuperAdmin("root", "T1t4n1c0");

	public ControllerViewConsultationMedecin(ViewConsultationMedecin consultView) {
		this.view = consultView;
	}


	public void consultationRetrive(String idConsult) {
		Consultation consult = modelConsult.getConsultationByID(idConsult);
		view.setConsultation(consult);
	}

	public void retrivePatient() {
		Consultation consult = view.getConsultation();
		if (consult != null) {
			String patientId = consult.getPatient();
			Patient patient = modelAdmin.findPatientByID(patientId);
			view.setPatient(patient);
		}
	}

	public void getMedecin(String idMed) {
		Medecin med = (Medecin) modelSuperAdmin.findEmployeeByID(idMed);
		view.setMedecin(med);
	}

	public class createNewOrdonnance implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation c = view.getConsultation();
			Ordonnance o = c.findOrdonnance();
			if(o == null) {
				ViewOrdonnance.main(c.getIdConsult());
			}else {
				JOptionPane.showMessageDialog(view.getContentPane()	, "Prescription already created for this visit", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public class DeleteOrdonnance implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation c = view.getConsultation();
			try {
				c.findOrdonnance().deleteOrdonnance();
				JOptionPane.showMessageDialog(view.getContentPane(), "Prescription deleted", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			}catch(NullPointerException e1) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No prescription in the database", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public class printOrdonnance implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Consultation consultation = modelConsult.getConsultationByID(view.getIdConsult());
			try {
			    File prescr = consultation.createOrdonnanceFile();
			    if (prescr != null) {
			        StringBuilder content = new StringBuilder();
			        try (Scanner scanner = new Scanner(prescr)) {
			            while (scanner.hasNextLine()) {
			                content.append(scanner.nextLine()).append("\n");
			            }
			        }
			        JDialog dialog = new JDialog();
			        dialog.setTitle("Ordonnance");
			        dialog.setSize(600, 400);
			        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        
			        JTextArea textArea = new JTextArea(content.toString());
			        textArea.setEditable(false);
			        textArea.setWrapStyleWord(true);
			        textArea.setLineWrap(true);
			        JScrollPane scrollPane = new JScrollPane(textArea);
			        dialog.getContentPane().add(scrollPane);
			        
			        dialog.setLocationRelativeTo(null); // Center the dialog
			        dialog.setVisible(true);
			    } else {
			        JOptionPane.showMessageDialog(view.getContentPane(), "Prescription not existing", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			    }
			} catch (IOException e1) {
			    JOptionPane.showMessageDialog(view.getContentPane(), "Prescription not existing", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}

