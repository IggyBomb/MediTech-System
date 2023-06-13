package controllerViewMedecin;

import Models.Consultation;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;
import Models.SingleConnection;
import viewMedecin.ViewNewConsultation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Acteurs.Medecin;
import Acteurs.Patient;

public class ControllerNewConsultation {

	private ViewNewConsultation view;
	private GestionnaireConsultation model = new GestionnaireConsultation("root", "T1t4n1c0");
	private Connection connection = SingleConnection.getInstance("jdbc:mysql://127.0.0.1:3306/nfa019project", "root", "T1t4n1c0");
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ControllerNewConsultation(ViewNewConsultation InsertCMed) {
		this.view = InsertCMed;

	}

	public void fillComboPhysician(@SuppressWarnings("rawtypes") JComboBox comboBox_physician) throws SQLException {
		try {
			String sql = "SELECT * FROM medecin";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("Id");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				double salaire = rs.getDouble("Salaire");
				Medecin medecin = new Medecin(id, nom, prenom, adresse, salaire);
				comboBox_physician.addItem(medecin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillComboPatient(JComboBox comboBox_patient) throws SQLException {
		try {
			String sql = "SELECT * FROM nfa019project.patient";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String idPatient = rs.getString("IdPatient");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				LocalDateTime dateNaissance = rs.getTimestamp("DateNaissance").toLocalDateTime();
				Patient patient = new Patient(nom, prenom, adresse, dateNaissance, idPatient);
				comboBox_patient.addItem(patient);
				System.out.println(nom +" "+prenom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}


	public class createNewConsultation implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				String id = view.getIDConsult();
				String med = view.getMedecin();
				String pat = view.getPatient();
				String details = view.getDetails();
				LocalDateTime ConsultTime = view.getTimeAndDate();
				Consultation consultation = new Consultation(id, pat, med, details, ConsultTime);
				Boolean result = model.addConsultation(consultation);
				if(result) {
					JOptionPane.showMessageDialog(view.getContentPane(), "Consultation confirmed", "Visit", JOptionPane.INFORMATION_MESSAGE);
					view.dispose();
				}else {
					JOptionPane.showMessageDialog(view.getContentPane(), "Error, ID already taken", "Visit", JOptionPane.INFORMATION_MESSAGE); 
				}
			}catch(NullPointerException e2) {
				JOptionPane.showMessageDialog(view.getContentPane(), "Please, fill all the fields", "Visit", JOptionPane.INFORMATION_MESSAGE); 
			}catch(SQLIntegrityConstraintViolationException e2) {
				JOptionPane.showMessageDialog(view.getContentPane(), "ID already taken", "Visit", JOptionPane.INFORMATION_MESSAGE); 
			}
		}
	}
}
