package constrollersViewAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.GestionnaireAdministratif;
import ViewAdmin.DeletePatient;

public class ControllerViewDeletePatient {
	private DeletePatient view;
	private GestionnaireAdministratif model;

	public ControllerViewDeletePatient(DeletePatient view) {
		this.view = view;
		model = new GestionnaireAdministratif("root", "T1t4n1c0");
	}

	public class deletePatient implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// Get the input values
			String id = view.getId();
			// Delete the patient
			boolean deletionSuccessful = model.deletePatient(id);
			// Show a message to the user depending on the result of the deletion
			if (deletionSuccessful) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Patient eliminated with success!", "Patient Deletion", JOptionPane.INFORMATION_MESSAGE);
				view.dispose();
			} else {
				JOptionPane.showMessageDialog(view.getContentPane(), "No patient with this ID.", "Patient Deletion", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}

