package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Acteurs.Medecin;
import Acteurs.Patient;
import Consultation.Consultation;
import formulaires.GestionnaireAdministratif;
import formulaires.GestionnaireConsultation;
import ordonnance.Ordonnance;

class ConsultationTest {

	private static Connection connection;
	private static GestionnaireConsultation gestionnaire;
	private static GestionnaireAdministratif admin;
	private static Patient patient;
	private static Medecin medecin;
	private static Consultation consultation;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		String url = "jdbc:mysql://localhost:3306/nfa019project";
		String login = "root";
		String password = "T1t4n1c0";
		connection = DriverManager.getConnection(url, login, password);
		gestionnaire = new GestionnaireConsultation(login, password);
		// Create test data
		admin = new GestionnaireAdministratif(login, password);
		patient = new Patient("Barack", "Obama", "New York", LocalDateTime.of(1985, 6, 15, 10, 0), "1_pat");
		medecin = new Medecin("1_med", "House", "Gregory", "Boston", 0);
		admin.insertPatient(patient);
		String medecinSql = "INSERT INTO medecin (Id, Nom, Prenom, Adresse, Salaire) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(medecinSql);
		pstmt.setString(1, "1_med");
		pstmt.setString(2, "House");
		pstmt.setString(3, "Gregory");
		pstmt.setString(4, "Boston");
		pstmt.setDouble(5, 0);
		pstmt.executeUpdate();
		pstmt.close();
		consultation = new Consultation("Test", "1_pat", "1_med", "details_test", LocalDateTime.of(2023, 1, 1, 0, 0));
		gestionnaire.addConsultation(consultation);

	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		// Delete test data
		String deletePatientSql = "DELETE FROM patient WHERE IdPatient = ?";
		PreparedStatement pstmt = connection.prepareStatement(deletePatientSql);
		pstmt.setString(1, "1_pat");
		pstmt.executeUpdate();
		pstmt.close();
		String deleteMedecintSql = "DELETE FROM medecin WHERE Id = ?";
		pstmt = connection.prepareStatement(deleteMedecintSql);
		pstmt.setString(1, "1_med");
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
	}

	@Test
	public void testCreateOrdonnance() throws SQLIntegrityConstraintViolationException {
		assertTrue(consultation.createOrdonnance());
		assertNotNull(consultation.getOrdonnance());
		assertEquals("Test", consultation.getOrdonnance().getIdOrdonnance());
	}
	
    @Test
    public void testRemoveOrdonnance() throws SQLIntegrityConstraintViolationException {
        assertTrue(consultation.createOrdonnance());
        assertNotNull(consultation.getOrdonnance());
        // remove the ordonnance
        assertTrue(consultation.removeOrdonnance());
        assertNull(consultation.getOrdonnance());
    }
    
	@Test
	void testFindOrdonnance() throws SQLIntegrityConstraintViolationException {
		Consultation cons2 = new Consultation("Test2", "1_pat", "1_med", "details_test", LocalDateTime.of(2023, 1, 1, 0, 0));
		gestionnaire.addConsultation(cons2);
		boolean isOK = cons2.createOrdonnance();
		System.out.println(isOK);
		assertNotNull(cons2.getOrdonnance());
		// Try to find the ordonnance
		
		assertEquals("Test2", cons2.getOrdonnance().getIdOrdonnance());
	}


}
