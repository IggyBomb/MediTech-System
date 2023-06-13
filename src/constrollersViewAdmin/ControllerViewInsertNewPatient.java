package constrollersViewAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import Acteurs.Patient;
import Models.GestionnaireAdministratif;
import ViewAdmin.ViewInsertPatient;

public class ControllerViewInsertNewPatient {
	private ViewInsertPatient view;
	private GestionnaireAdministratif model;

	public ControllerViewInsertNewPatient(ViewInsertPatient view) {
		this.view = view;
		model = new GestionnaireAdministratif("root", "T1t4n1c0");
	}

	public class InsertPatient implements ActionListener {
		public void actionPerformed(ActionEvent e) {				
			try {
				// Get the input values
				String id = view.getId();
				String nom = view.getLastName();
				String prenom = view.getFistName();
				LocalDateTime birthDate = view.getBirthDay();
				String adresse = view.getAddress();
				// Create the new patient object
				Patient patient = new Patient(nom, prenom, adresse, birthDate, id);
				// Insert the patient into the database
				try {
					boolean insertionSuccessful = model.insertPatient(patient);
					// Show a message to the user depending on the result of the insertion
					if (insertionSuccessful) {
						JOptionPane.showMessageDialog(view.getContentPane(), "Patient added to the database.", "Insert Patient", JOptionPane.INFORMATION_MESSAGE);
						view.dispose();
					} else {
						JOptionPane.showMessageDialog(view.getContentPane(), "An error occurred when adding the patient.", "Insert Patient", JOptionPane.ERROR_MESSAGE);
					}
				}catch(SQLIntegrityConstraintViolationException e2) {
						JOptionPane.showMessageDialog(view.getContentPane(), "Database error: " + e2.getMessage(), "Insert Patient", JOptionPane.ERROR_MESSAGE);
					}
				} catch(NullPointerException e1) {
					JOptionPane.showMessageDialog(view.getContentPane(), "Please fill all the informations", "Insert Patient", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}



