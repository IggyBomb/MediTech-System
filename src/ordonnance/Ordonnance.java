package ordonnance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import Consultation.Consultation;

public class Ordonnance {
	private String idOrdonnance;
	private String propositionsSoins;
	private String medicaments;
	private LocalDateTime dateCreation;
	private Consultation consultation;
	private String appareilMedical;
	private String deviceStatus;
	private String url = "jdbc:mysql://127.0.0.1:3306/nfa019project";
	private String login = "root";
	private String password = "T1t4n1c0";
	private Connection connection;

	public Ordonnance(Consultation consultation) {
		this.idOrdonnance = consultation.getIdConsult();
		this.medicaments = null;
		this.propositionsSoins = null;
		this.appareilMedical =  null;
		this.deviceStatus = null;
		this.consultation = consultation;
		this.dateCreation = consultation.getDate();
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add an ordonnance to the database.
	 * 
	 * @return true if the ordonnance was added successfully, false otherwise
	 */
	public boolean addOrdonnance() {
		String sql = "INSERT INTO ordonnance (idOrdonnance, propositionsSoins, medicaments, dateCreation, appareilMedical, deviceStatus, consultationId) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, this.getIdOrdonnance());
			pstmt.setString(2, this.getPropositionsSoins());
			pstmt.setString(3, this.getMedicaments());
			pstmt.setTimestamp(4, Timestamp.valueOf(this.getDateCreation()));
			pstmt.setString(5, this.getAppareilMedical());
			pstmt.setString(6, this.getDeviceStatus());
			pstmt.setString(7, this.getConsultation().getIdConsult());
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

	public boolean updateOrdonnance() {
		String sql = "UPDATE ordonnance SET propositionsSoins=?, medicaments=?, appareilMedical=?, deviceStatus=? WHERE idOrdonnance=?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, this.getPropositionsSoins());
			pstmt.setString(2, this.getMedicaments());
			pstmt.setString(3, this.getAppareilMedical());
			pstmt.setString(4, this.getDeviceStatus());
			pstmt.setString(5, this.getIdOrdonnance());
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

	public boolean deleteOrdonnance() {
		String sql = "DELETE FROM ordonnance WHERE idOrdonnance = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, this.getIdOrdonnance());
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

	public String getPropositionsSoins() {
		return propositionsSoins;
	}

	public void setPropositionsSoins(String propositionsSoins) {
		this.propositionsSoins = propositionsSoins;
	}

	public String getAppareilMedical() {
		return appareilMedical;
	}

	public void setAppareilMedical(String appareilMedical) {
		this.appareilMedical = appareilMedical;
		setDeviceStatus("instance");
	}

	public String getIdOrdonnance() {
		return idOrdonnance;
	}

	public void setIdOrdonnance(String idOrdonnance) {
		this.idOrdonnance = idOrdonnance;
	}

	public String getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(String medicaments) {
		this.medicaments = medicaments;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
}
