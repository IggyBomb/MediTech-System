package formulaires;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import Consultation.Consultation;

public class GestionnaireConsultation {
	private String url;
	private String login, password;
	private Connection connection;

	public GestionnaireConsultation(String login, String pass) {
		this.url = "jdbc:mysql://127.0.0.1:3306/nfa019project";
		this.login = login;
		this.password = pass;
		this.connection = SingleConnection.getInstance(url, login, pass);
	}
	
	
	
	// search the database for a consultation using the ID as input
	public Consultation getConsultationByID(String idConsult) {
	    String sql = "SELECT * FROM consultation WHERE IdConsult = ?";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, idConsult);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            String patient = rs.getString("PatientID");
	            String medecin = rs.getString("MedecinID");
	            Consultation consultation = new Consultation(rs.getString("IdConsult"), patient, medecin, rs.getString("DetailsCliniques"), rs.getTimestamp("Date").toLocalDateTime());
	            consultation.setOrdonnance(consultation.findOrdonnance());
	            return consultation;
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	// search all the consultation with the same patient, using the ID of the patient as key
	public List<Consultation> listConsultationPatient(String idPatient){
		List<Consultation> listConsultation = new ArrayList<Consultation>();
		String sql = "SELECT * FROM consultation WHERE PatientID = ?";
		try {
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, idPatient);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String IdConsult = rs.getString("IdConsult");
	            String patient = rs.getString("PatientID");
	            String medicin = rs.getString("MedecinID");
	            String details = rs.getString("DetailsCliniques");
	            LocalDateTime dateConsult = rs.getTimestamp("Date").toLocalDateTime();
	            Consultation consultation = new Consultation(IdConsult, patient, medicin, details, dateConsult);
	            listConsultation.add(consultation);
	        }
	        pstmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listConsultation;
	}
	
	/**
	 * Add a consultation from the database.
	 * 
	 * @return true if the consultation was added successfully, false otherwise
	 */	public boolean addConsultation(Consultation consultation) {
	    String sql = "INSERT INTO consultation (IdConsult, PatientID, MedecinID, DetailsCliniques, Date) VALUES (?, ?, ?, ?, ?)";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, consultation.getIdConsult());
	        pstmt.setString(2, consultation.getPatient());
	        pstmt.setString(3, consultation.getMedecin());
	        pstmt.setString(4, consultation.getDetailsCliniques());
	        pstmt.setTimestamp(5, Timestamp.valueOf(consultation.getDate()));
	        int rowsAffected = pstmt.executeUpdate();
	        pstmt.close();
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	/**
	 * Deletes a consultation from the database.
	 * 
	 * @param idConsult the ID of the consultation to delete
	 * @return true if the consultation was deleted successfully, false otherwise
	 */
	public boolean deleteConsultation(String idConsult) {
	    String sql = "DELETE FROM consultation WHERE IdConsult = ?";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, idConsult);
	        int deletedRows = pstmt.executeUpdate();
	        pstmt.close();
	        return deletedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	public boolean updateConsultation(Consultation consultation) {
	    String sql = "UPDATE consultation SET PatientID = ?, MedecinID = ?, DetailsCliniques = ?, Date = ? WHERE IdConsult = ?";
	    try {
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, consultation.getPatient());
	        pstmt.setString(2, consultation.getMedecin());
	        pstmt.setString(3, consultation.getDetailsCliniques());
	        pstmt.setTimestamp(4, Timestamp.valueOf(consultation.getDate()));
	        pstmt.setString(5, consultation.getIdConsult());
	        int rowsAffected = pstmt.executeUpdate();
	        pstmt.close();
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	/**
	This method gets the details of a patient using their ID. It returns a String
	containing the patient's ID, last name, first name, and antecedents.
	@param idPatient the ID of the patient to search for
	@return a formatted String with the patient's details, including their antecedents
	*/
	public String getDetailsPatient(String idPatient) {
	    StringBuilder str = new StringBuilder();
	    String sql = "SELECT IdPatient, Nom, Prenom FROM patient WHERE IdPatient = ?";
	    try {
	        //get the id of the patient
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, idPatient);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) { 
	            String IdPatient = rs.getString("IdPatient");
	            String nom = rs.getString("Nom");
	            String prenom = rs.getString("Prenom");
	            str.append(IdPatient).append(" - ").append(nom).append(" ").append(prenom).append("\n");
	        }
	        pstmt.close();
	        rs.close();
	        //get the antecedents of the patient
	        sql = "SELECT Antecedents FROM dossier WHERE PatientID = ?";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, idPatient);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            String antecedents = rs.getString("Antecedents");
	            str.append("Antecedents: ").append("\n").append(antecedents);
	        }
	        pstmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return str.toString();
	}
}

