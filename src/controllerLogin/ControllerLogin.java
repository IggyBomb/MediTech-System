package controllerLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Main.ViewLogin;
import Models.GestionnaireAdministratif;
import ViewGestionnaireAdmin.ViewHomePageAdmin;
import viewMedecin.ViewHomePageMedecin;


public class ControllerLogin {
	private ViewLogin view;
	private GestionnaireAdministratif Admin =  new GestionnaireAdministratif("root", "T1t4n1c0");

	public ControllerLogin(ViewLogin loginView) {
		this.view = loginView;
	}

	public class loginMedecin implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String user = view.getUser(); 
			String password = view.getPassword();
			try {
				boolean access = Admin.accessMedecin(user, password);
				if(access) {
					view.dispose();
					ViewHomePageMedecin.main(null);
				}else {
					JOptionPane.showMessageDialog(view, "Incorrect credentials.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	public class loginAdmin implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String user = view.getUser(); 
			String password = view.getPassword();
			try {
				boolean access = Admin.accessAdmin(user, password);
				if(access) {
					view.dispose();
					ViewHomePageAdmin.main(null);
				}else {
					JOptionPane.showMessageDialog(view, "Incorrect credentials.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	public class loginTechnicien implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String user = view.getUser(); 
			String password = view.getPassword();
			try {
				boolean access = Admin.accessTechnicien(user, password);
				if(access) {
					view.dispose();
				}else {
					JOptionPane.showMessageDialog(view, "Incorrect credentials.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
	
	public class loginSuperAdmin implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String user = view.getUser(); 
			String password = view.getPassword();
			try {
				boolean access = Admin.accessTechnicien(user, password);
				if(access) {
					view.dispose();
				}else {
					JOptionPane.showMessageDialog(view, "Incorrect credentials.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}
}