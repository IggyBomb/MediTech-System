package controllerViewSuperAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Acteurs.Employee;
import Models.GestionnaireSuperAdmin;
import ViewSuperAdmin.ViewSuperAdmin;

public class ControllerViewSuperAdmin {
	private ViewSuperAdmin view;
	private GestionnaireSuperAdmin model = new GestionnaireSuperAdmin("root", "T1t4n1c0");

	public ControllerViewSuperAdmin(ViewSuperAdmin view) {
		this.view = view;
	}

	public class searchEmployeeByID implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Employee employee = model.findEmployeeByID(view.getIdEmployee());
			String accountEmployee = model.searchUser(view.getIdEmployee());
			if (employee == null) {
				JOptionPane.showMessageDialog(view.getContentPane(), "ID not found.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"Id", "Nom", "Prenom", "Profession", "Adresse", "Salaire", "Account"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				Object[] rowData = {
						employee.getId(),
						employee.getNom(),
						employee.getPrenom(), 
						employee.getRole(),
						employee.getAdresse(),
						employee.getSalaire(),
						accountEmployee
				};
				tableModel.addRow(rowData);
				view.getTableResults().setModel(tableModel); // Set the table model for tableResult
			}
		}
	}

	public class SearchEmployeeByName implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Employee> employees = model.searchEmployeeByName(view.getName());
			if(employees.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Name not found.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"Id", "Nom", "Prenom", "Profession"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				for(int i = 0; i<employees.size(); i++) {
					Employee emp = employees.get(i);
					Object[] rowData = {
							emp.getId(),
							emp.getNom(),
							emp.getPrenom(),
							model.findProfession(emp.getId())
					};
					tableModel.addRow(rowData);
					view.getTableResults().setModel(tableModel);
				}
			}
		}
	}

	public class deleteEmployee implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			boolean result = model.deleteEmployee(view.getIdDelete());
			if(result) {
				model.deleteUser(view.getIdDelete());
				JOptionPane.showMessageDialog(view.getContentPane(), "Employee deleted", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(view.getContentPane(), "No employee with this ID", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public class searchEmployeeByProfession implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Employee> list = model.searchEmployeeByProfession(view.getProfession());
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No employees in this role.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String[] columnNames = {"Id", "Nom", "Prenom", "Profession"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				for(int i = 0; i<list.size(); i++) {
					Employee emp = list.get(i);
					Object[] rowData = {
							emp.getId(),
							emp.getNom(),
							emp.getPrenom(),
							model.findProfession(emp.getId())
					};
					tableModel.addRow(rowData);
					view.getTableResults().setModel(tableModel);
				}
			}
		}
	}
	
	public boolean checkId(String id) {
		Employee emp = model.findEmployeeByID(id);
		if(emp == null) {
			return false;
		}else {
			return true;
		}
	}
}

