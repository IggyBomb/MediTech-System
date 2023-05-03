package Consultation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import formulaires.SingleConnection;
import ordonnance.Ordonnance;



public class Consultation {
	private String idConsult;
	private String patient;
	private String medecin;
	private String detailsCliniques;
	private LocalDateTime date;
	private Ordonnance ordonnance;
	private String url = "jdbc:mysql://127.0.0.1:3306/nfa019project";
	private String login = "root";
	private String password = "T1t4n1c0";
	private Connection connection;


	public Consultation(String IdConsult, String pat, String med, String details, LocalDateTime d) {
		this.idConsult = IdConsult;
		this.patient = pat;
		this.medecin = med;
		this.detailsCliniques = details;
		this.date = d;
		this.ordonnance = null;
		this.connection = SingleConnection.getInstance(url, login, password);
	}


	public String getIdConsult() {
		return idConsult;
	}

	public void setIdConsult(String idConsult) {
		this.idConsult = idConsult;
	}

	public String getDetailsCliniques() {
		return detailsCliniques;
	}

	public void setDetailsCliniques(String detailsCliniques) {
		this.detailsCliniques = detailsCliniques;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getMedecin() {
		return medecin;
	}

	public void setMedecin(String medecin) {
		this.medecin = medecin;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Ordonnance getOrdonnance() {
		return this.ordonnance;
	}


	public void setOrdonnance(Ordonnance Ordonnance) {
		this.ordonnance = Ordonnance;
	}

	public boolean createOrdonnance() {
		this.ordonnance = new Ordonnance(this);
		boolean created = this.ordonnance.addOrdonnance();
		this.setOrdonnance(ordonnance);
		return created;
	}

	public boolean removeOrdonnance() {
		if (this.ordonnance == null) {
			return false;
		}
		String sql = "DELETE FROM ordonnance WHERE idOrdonnance = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, this.ordonnance.getIdOrdonnance());
			int rowsAffected = pstmt.executeUpdate();
			pstmt.close();
			if (rowsAffected > 0) {
				this.ordonnance = null;
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Ordonnance findOrdonnance() {
		String sql = "SELECT * FROM ordonnance WHERE idOrdonnance = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, this.idConsult);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Ordonnance ordonnance = new Ordonnance(this);
				ordonnance.setPropositionsSoins(rs.getString("propositionsSoins"));
				ordonnance.setMedicaments(rs.getString("medicaments"));
				ordonnance.setDateCreation(rs.getTimestamp("dateCreation").toLocalDateTime());
				ordonnance.setAppareilMedical(rs.getString("appareilMedical"));
				ordonnance.setDeviceStatus(rs.getString("deviceStatus"));
				this.ordonnance = ordonnance;
				return ordonnance;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

