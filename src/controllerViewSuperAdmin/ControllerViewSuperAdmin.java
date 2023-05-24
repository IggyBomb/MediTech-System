package controllerViewSuperAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Acteurs.Employee;
import ViewSuperAdmin.ViewSuperAdmin;
import formulaires.GestionnaireSuperAdmin;

public class ControllerViewSuperAdmin {
	private ViewSuperAdmin view;
	private GestionnaireSuperAdmin model = new GestionnaireSuperAdmin("root", "T1t4n1c0");

	public ControllerViewSuperAdmin(ViewSuperAdmin view) {
		this.view = view;
	}

	public class searchEmployee implements ActionListener{
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

}

