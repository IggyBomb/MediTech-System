package ControllerLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Main.LoginView;
import ViewGestionnaireAdmin.admin;
import formulaires.GestionnaireAdministratif;
import viewConsultMed.GestionnaireViewMed;


public class ControllerLogin {
	private LoginView view;
	private GestionnaireAdministratif Admin =  new GestionnaireAdministratif("root", "T1t4n1c0");

	public ControllerLogin(LoginView loginView) {
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
					GestionnaireViewMed.main(null);
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
					admin.main(null);
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
}