package controllerViewSuperAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import Acteurs.AgentAdministration;
import Acteurs.Medecin;
import Acteurs.SuperAdmin;
import Acteurs.Technicien;
import Models.GestionnaireSuperAdmin;
import ViewSuperAdmin.ViewNewEmployee;

public class ControllerViewNewEmployee {
	private ViewNewEmployee view;
	private GestionnaireSuperAdmin model;

	public ControllerViewNewEmployee(ViewNewEmployee view) {
		this.view = view;
		model = new GestionnaireSuperAdmin("root", "T1t4n1c0");
	}

	public class AddNewEmployee implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				String profession = view.getProfession();
				String firstName = view.getFirstName();
				String lastName = view.getLastName();
				String id = view.getId();
				String address = view.getAddress();
				Double wage = view.getWage();
				String password = view.getPassword();
				boolean result = false;
				if (profession.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || id.isEmpty() || address.isEmpty()) {
					JOptionPane.showMessageDialog(view.getContentPane(), "ID not found.", "Please fill all the fields!", JOptionPane.INFORMATION_MESSAGE); 
					return;
				}
				if(profession.equals("medecin")) {
					Medecin med = new Medecin(id, lastName, firstName, address, wage);
					result = model.insertEmployee(med);
					if(result) {
						model.createUser(med, id, password, "medecin");
					}
				} else if (profession.equals("technicien")) {
					Technicien tech = new Technicien(id, lastName, firstName, address, wage);
					result =model.insertEmployee(tech);
					if(result) {
						model.createUser(tech, id, password, "technicien");
					}
				} else if (profession.equals("admin")) {
					AgentAdministration admin = new AgentAdministration(id, lastName, firstName, address, wage);
					result =model.insertEmployee(admin);
					if(result) {
						model.createUser(admin, id, password, "admin");
					}
				} else if (profession.equals("superAdmin")) {
					SuperAdmin superAdmin = new SuperAdmin(id, lastName, firstName, address, wage);
					result = model.insertEmployee(superAdmin);
					if(result) {
						model.createUser(superAdmin, id, password, "superAdmin");
					}
				}
				if(result) {
					JOptionPane.showMessageDialog(view.getContentPane(), "Employee created", "Success", JOptionPane.INFORMATION_MESSAGE); 
					view.dispose();
				}else {
					JOptionPane.showMessageDialog(view.getContentPane(), "Error!", "Unable to create employee.", JOptionPane.ERROR_MESSAGE); 
				}
			} catch (SQLIntegrityConstraintViolationException icve) {
				// Display error message in content panel
				JOptionPane.showMessageDialog(view.getContentPane(), "Database error: " + icve.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException se) {
				// Generic SQL exception handling
				JOptionPane.showMessageDialog(view.getContentPane(), "Error!", "Please fill all the fields!", JOptionPane.ERROR_MESSAGE); 
			}
		}
	}

}
