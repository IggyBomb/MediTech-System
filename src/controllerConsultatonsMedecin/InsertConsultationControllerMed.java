package controllerConsultatonsMedecin;

import Consultation.Consultation;
import formulaires.GestionnaireAdministratif;
import formulaires.GestionnaireConsultation;
import formulaires.SingleConnection;
import viewConsultMed.GestionnaireViewMed;
import viewConsultMed.InsertConsultationMed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JComboBox;

import Acteurs.Medecin;
import Acteurs.Patient;

public class InsertConsultationControllerMed {

	private InsertConsultationMed InsertCMed;
	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	private Connection connection = SingleConnection.getInstance("jdbc:mysql://127.0.0.1:3306/nfa019project", "root", "T1t4n1c0");
	private PreparedStatement pstmt;
	private ResultSet rs;

	public InsertConsultationControllerMed(InsertConsultationMed InsertCMed) {
		this.InsertCMed = InsertCMed;

	}

	public void fillComboPhysician(JComboBox comboBox_physician) throws SQLException {
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


	public boolean createConsultation(String idConsult, String patientId, String medecinId, String detailsCliniques, LocalDateTime date) {
		Consultation consultation = new Consultation(idConsult, patientId, medecinId, detailsCliniques, date);
		boolean result = gestionnaire.addConsultation(consultation);
		return result;
	}

}
