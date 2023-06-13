package constrollersViewAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Acteurs.Patient;
import Models.GestionnaireAdministratif;
import ViewAdmin.ViewUpdatePatient;

public class ControllerViewUpdatePatient {
	private ViewUpdatePatient view;
	private GestionnaireAdministratif model;
	
	public ControllerViewUpdatePatient(ViewUpdatePatient view) {
		this.view = view;
		this.model = new GestionnaireAdministratif("root", "T1t4n1c0");
	}
	
	public boolean FillData(String id) {
		Patient p = model.findPatientByID(id);
		try {
			view.setId(id);
			view.setLastName(p.getNom());
			view.setFirstName(p.getPrenom());
			view.SetAddress(p.getAdresse());
			view.setBirthDay(p.getDateNaissance());
		}catch(NullPointerException e) {
			view.dispose();
		}
		return true;
	}

	public class updateEmployee implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String Id = view.getID();
			Patient newPat = new Patient(view.getLastName(),view.getFirstName(),view.getAddress(),view.getBirthDay(),view.getID());
			boolean resultUpdate = false;
			try {
				resultUpdate = model.updatePatient(newPat);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Error! SQL", "Update Patient", JOptionPane.INFORMATION_MESSAGE);				e1.printStackTrace();
			}
			if(!resultUpdate) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Error! please fill all the fields", "Update Patient", JOptionPane.INFORMATION_MESSAGE);
			}else if(resultUpdate) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Patient data updated", "Update Patient", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	

}
