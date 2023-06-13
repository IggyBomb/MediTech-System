package controllerViewSuperAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Acteurs.Employee;
import Models.GestionnaireSuperAdmin;
import ViewSuperAdmin.ViewUpdateEmployee;

public class ControllerViewUpdateEmployee {
	private ViewUpdateEmployee view;
	private GestionnaireSuperAdmin model;

	public ControllerViewUpdateEmployee(ViewUpdateEmployee view) {
		this.view = view;
		model = new GestionnaireSuperAdmin("root", "T1t4n1c0");
	}

	private String getPassword(String s) {
		// Split the account String into an array using "password: " as the delimiter
		String[] parts = s.split("password: ");
		if (parts.length > 1) {
			return parts[1];
		}
		return "";
	}

	private String getUser(String s) {
		String[] parts = s.split(" --- ");
		if (parts.length > 1) {
			String[] userParts = parts[0].split("user: ");
			if (userParts.length > 1) {
				return userParts[1];
			}
		}
		return "";
	}
	
	
	public boolean FillData() {
		Employee oldEmp = model.findEmployeeByID(view.getIdEmployee());
		String profession = model.findProfession(view.getIdEmployee());
		try {
			view.setIdEmployee(oldEmp.getId());
			view.setLastName(oldEmp.getNom());
			view.setFirstName(oldEmp.getPrenom());
			view.setAdresse(oldEmp.getAdresse());
			view.setWage(Double.toString(oldEmp.getSalaire()));
			String account = model.searchUser(view.getIdEmployee());
			String password = getPassword(account);
			view.setUser(getUser(account));
			view.setPassword(password);
			view.setProfession(profession);
		}catch(NullPointerException e) {
			view.dispose();
		}
		return true;
	}

	public class updateEmployee implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String Id = view.getIdEmployee();
			Employee newEmpl = new Employee(view.getIdEmployee(), view.getLastName(), view.getFirstName(), view.getAdresse(), view.getWage());
			String profession = view.getProfession();
			if(profession.equals("superAdmin")) {
				profession = "admin";
			}
			boolean resultUpdataEmployee = model.updateEmployee(newEmpl, profession);
			if(!resultUpdataEmployee) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Error during the update of Employee.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}else if(resultUpdataEmployee) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Employee data updated", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}
			boolean resultUser = model.updateUser(Id, view.getUser(), view.getPassword(), view.getProfession());
			if(!resultUser) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Error during the update of User.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}else if(resultUser) {
				JOptionPane.showMessageDialog(view.getContentPane(), "User data updated", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
