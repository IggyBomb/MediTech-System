package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Acteurs.Patient;
import DossierMedicale.Dossier;

public class GestionnaireAdministratif {
	private String url;
	private String login, password;
	private Connection connection;

	public GestionnaireAdministratif(String login, String pass) {
		this.url = "jdbc:mysql://127.0.0.1:3306/nfa019project";
		this.login = login;
		this.password = pass;
		this.connection = SingleConnection.getInstance(url, login, pass);
	}


	public Dossier findDossier(String id) throws SQLException {
		String sql = "SELECT * FROM dossier WHERE IdDossier = ?";
		Dossier dossier = null;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {
			dossier = new Dossier(findByID(id));
			dossier.setIdDossier(resultSet.getString("IdDossier"));
			dossier.setDateCreation(resultSet.getTimestamp("DateCreation").toLocalDateTime());
			dossier.setAntecedents(resultSet.getString("Antecedents"));
		}
		resultSet.close();
		pstmt.close();
		return dossier;
	}

	// Search for a patient by ID
	public Patient findByID(String id) {
		Patient patient = null;
		String patientSql = "SELECT * FROM patient WHERE IdPatient  = ?";
		String dossierSql = "SELECT * FROM dossier WHERE IdDossier  = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(patientSql);
			pstmt.setString(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				patient = new Patient(resultSet.getString("Nom"),resultSet.getString("Prenom"), resultSet.getString("Adresse"), resultSet.getTimestamp("DateNaissance").toLocalDateTime(), resultSet.getString("IdPatient"));
				Dossier dossier = findDossier(dossierSql);
				if (dossier != null) {
					patient.setDossier(dossier);
				}
			}
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}


	// search all the patients with the same name and returns a list
	public List<Patient> searchPatientsByName(String name) {
	    List<Patient> patients = new ArrayList<>();
	    String sql = "SELECT * FROM patient WHERE Nom = ?";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, name);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String id = rs.getString("IdPatient");
	            String nom = rs.getString("Nom");
	            String prenom = rs.getString("Prenom");
	            LocalDateTime dateNaissance = rs.getTimestamp("DateNaissance").toLocalDateTime();
	            String adresse = rs.getString("Adresse");
	            Patient patient = new Patient(nom, prenom, adresse, dateNaissance, id);
	            patients.add(patient);
	        }
	        pstmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return patients;
	}

	public boolean insertPatient (Patient patient) throws SQLIntegrityConstraintViolationException {
	    boolean insertionSuccessful = false;
	    try {
	        // Start transaction
	        connection.setAutoCommit(false);
	        // Insert patient
	        String patientSql = "INSERT INTO patient (IdPatient, Nom, Prenom, DateNaissance, Adresse) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement pstmt = connection.prepareStatement(patientSql);
	        pstmt.setString(1, patient.getIdPatient());
	        pstmt.setString(2, patient.getNom());
	        pstmt.setString(3, patient.getPrenom());
	        pstmt.setTimestamp(4, Timestamp.valueOf(patient.getDateNaissance()));
	        pstmt.setString(5, patient.getAdresse());
	        pstmt.executeUpdate();
	        pstmt.close();
	        // Insert dossier in the database
	        Dossier dossier = new Dossier(patient);
	        patient.setDossier(dossier);
	        String dossierSql = "INSERT INTO dossier (IdDossier, DateCreation, Antecedents) VALUES (?, ?, ?)";
	        pstmt = connection.prepareStatement(dossierSql);
	        pstmt.setString(1, dossier.getIdDossier());
	        pstmt.setTimestamp(2, Timestamp.valueOf(dossier.getDateCreation()));
	        pstmt.setString(3, dossier.getAntecedents());
	        pstmt.executeUpdate();
	        pstmt.close();

	        // Commit transaction
	        connection.commit();
	        insertionSuccessful = true;
	    } catch (SQLIntegrityConstraintViolationException e) {
	        // Rollback transaction
	        try {
	            connection.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        throw e; // re-throw the exception
	    } catch (SQLException e) {
	        // Rollback transaction
	        try {
	            connection.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            // Reset connection to default behavior
	            connection.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return insertionSuccessful;
	}
	
	//delete a patient based on the ID
	public boolean deletePatient(String id) {
	    try {
	        // Start a transaction
	        connection.setAutoCommit(false);
	        // Delete the patient's dossier first, if it exists
	        String dossierSql = "DELETE FROM dossier WHERE IdDossier = ?";
	        PreparedStatement pstmt = connection.prepareStatement(dossierSql);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
	        pstmt.close();
	        // Delete the patient from the database
	        String patientSql = "DELETE FROM patient WHERE IdPatient = ?";
	        pstmt = connection.prepareStatement(patientSql);
	        pstmt.setString(1, id);
	        int rowsAffected = pstmt.executeUpdate();
	        pstmt.close();
	        // Commit the transaction
	        connection.commit();
	        // Check if the delete was successful
	        return (rowsAffected == 1);
	    } catch (SQLException e) {
	        // Rollback the transaction in case of an error
	        try {
	            connection.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        // Set the auto-commit back to true
	        try {
	            connection.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	/**
	This method updates a dossier.
	@param the dossier of the patient
	@return a boolean with the result of the operation
	*/
	public boolean updatePatient(Patient patient) throws SQLException {
	    String updatePatientSql = "UPDATE patient SET Nom=?, Prenom=?, DateNaissance=?, Adresse=? WHERE IdPatient=?";
	    PreparedStatement updatePatientStmt = connection.prepareStatement(updatePatientSql);
	    updatePatientStmt.setString(1, patient.getNom());
	    updatePatientStmt.setString(2, patient.getPrenom());
	    updatePatientStmt.setTimestamp(3, Timestamp.valueOf(patient.getDateNaissance()));
	    updatePatientStmt.setString(4, patient.getAdresse());
	    updatePatientStmt.setString(5, patient.getIdPatient());
	    int rowsUpdated = updatePatientStmt.executeUpdate();
	    updatePatientStmt.close();
	    if (rowsUpdated == 1) {
	        return true;
	    }
	    return false;
	}
	
	/**
	This method creates a new dossier.
	@param the dossier of the patient
	@return a boolean with the result of the operation
	*/
	public boolean createDossier(String id) throws SQLException {
		String createDossierSql = "INSERT INTO dossier (IdDossier, DateCreation, Antecedents) VALUES (?, ?, ?)";
	    PreparedStatement updatePatientStmt = connection.prepareStatement(createDossierSql); 
	    updatePatientStmt.setString(1, id);
	    updatePatientStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
	    updatePatientStmt.setNull(3, Types.VARCHAR); // Or another SQL type if 'Antecedents' is not a VARCHAR;
	    int rowsUpdated = updatePatientStmt.executeUpdate();
	    updatePatientStmt.close();
	    if (rowsUpdated == 1) {
	        return true;
	    }
	    return false;
	}
	
	
	
	/**
	This method updates the dossier of a patient the details of a patient using their ID. 
	@param the dossier of the patient
	@return a boolean with the result of the operation
	*/
	public boolean updateDossier(Dossier dossier) throws SQLException {
	    String updatePatientSql = "UPDATE dossier SET IdDossier=?, DateCreation=?, Antecedents=? WHERE IdDossier=?";
	    PreparedStatement updatePatientStmt = connection.prepareStatement(updatePatientSql);
	    updatePatientStmt.setString(1, dossier.getIdDossier());
	    updatePatientStmt.setTimestamp(2, Timestamp.valueOf(dossier.getDateCreation()));
	    updatePatientStmt.setString(3, dossier.getAntecedents());
	    updatePatientStmt.setString(4, dossier.getIdDossier());
	    int rowsUpdated = updatePatientStmt.executeUpdate();
	    updatePatientStmt.close();
	    if (rowsUpdated == 1) {
	        return true;
	    }
	    return false;
	}
	
	
	//For the login page, find if there is an account with the given parameters and if the user is a doctor
	public boolean accessMedecin(String user, String password) throws SQLException {
	    String query = "SELECT * FROM Login WHERE username = ? and password = ? and profession = 'medecin'";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, user);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()) {
	            return true; // Record found with matching username and password.
	        } else {
	            return false; // No record found with matching username and password.
	        }
	    } catch (SQLException ex) {
	        throw new SQLException(ex);
	    }
	}
	
	//For the login page, find if there is an account with the given parameters and if the user is a admin
	public boolean accessAdmin(String user, String password) throws SQLException {
	    String query = "SELECT * FROM Login WHERE username = ? and password = ?";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, user);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()) {
	            return true; // Record found with matching username and password.
	        } else {
	            return false; // No record found with matching username and password.
	        }
	    } catch (SQLException ex) {
	        throw new SQLException(ex);
	    }
	}
	
	//For the login page, find if there is an account with the given parameters and if the user is a technicien
	public boolean accessTechnicien(String user, String password) throws SQLException {
	    String query = "SELECT * FROM Login WHERE username = ? and password = ? and profession = 'technicien'";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, user);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()) {
	            return true; // Record found with matching username and password.
	        } else {
	            return false; // No record found with matching username and password.
	        }
	    } catch (SQLException ex) {
	        throw new SQLException(ex);
	    }
	}
	
	//For the login page, find if there is an account with the given parameters and if the user is a technicien
		public boolean accessSuperAdmin(String user, String password) throws SQLException {
		    String query = "SELECT * FROM Login WHERE username = ? and password = ? ANDprofession = 'superAdmin'";
		    try {
		        PreparedStatement pstmt = connection.prepareStatement(query);
		        pstmt.setString(1, user);
		        pstmt.setString(2, password);
		        ResultSet rs = pstmt.executeQuery();
		        if(rs.next()) {
		            return true; // Record found with matching username and password.
		        } else {
		            return false; // No record found with matching username and password.
		        }
		    } catch (SQLException ex) {
		        throw new SQLException(ex);
		    }
		}
}
