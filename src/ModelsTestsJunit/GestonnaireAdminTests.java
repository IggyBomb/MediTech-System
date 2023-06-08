package ModelsTestsJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Acteurs.Patient;
import DossierMedicale.Dossier;
import Models.GestionnaireAdministratif;

class GestonnaireAdminTests {

	private static Connection connection;
	private static GestionnaireAdministratif gestionnaire;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		String url = "jdbc:mysql://localhost:3306/nfa019project";
		String login = "root";
		String password = "T1t4n1c0";
		connection = DriverManager.getConnection(url, login, password);
		gestionnaire = new GestionnaireAdministratif(login, password);
		createTestData();
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		gestionnaire.deletePatient("1");
		gestionnaire.deletePatient("2");
	}

	@Test
	public void testFindByID() {
		Patient patient = gestionnaire.findByID("1");
		System.out.println(patient.getIdPatient());
		assertNotNull(patient);
		assertEquals("Barack", patient.getPrenom()); 
		assertEquals("Obama", patient.getNom());
		assertEquals("New York", patient.getAdresse());
		assertEquals(LocalDateTime.of(1990, 1, 1, 0, 0), patient.getDateNaissance());
		assertEquals("1", patient.getIdPatient());
		assertNotNull(patient.getDossier());
		assertEquals("1", patient.getDossier().getIdDossier());
		assertEquals("COVID19", patient.getDossier().getAntecedents());
		assertTrue(patient.getDossier().getConsultations().isEmpty());
	}

	private static void createTestData() throws SQLException {
		String patientSql = "INSERT INTO patient (IdPatient, Nom, Prenom, DateNaissance, Adresse) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(patientSql);
		pstmt.setString(1, "1");
		pstmt.setString(2, "Obama");
		pstmt.setString(3, "Barack");
		pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0)));
		pstmt.setString(5, "New York");
		pstmt.executeUpdate();
		pstmt.close();
		String dossierSql = "INSERT INTO dossier (IdDossier, DateCreation, Antecedents, PatientID) VALUES (?, ?, ?, ?)";
		pstmt = connection.prepareStatement(dossierSql);
		pstmt.setString(1, "1");
		pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(2021, 1, 1, 0, 0)));
		pstmt.setString(3, "COVID19");
		pstmt.setString(4, "1");
		pstmt.executeUpdate();
		pstmt.close();
	}



	@Test
	public void testInsertPatient() throws SQLException {
		// Create a new patient for testing
		Patient patient = new Patient("Biden", "Joe", "Washington D.C.", LocalDateTime.of(1980, 1, 1, 0, 0), "2");
		// Call the method under test within a transaction
		connection.setAutoCommit(false);
		boolean insertionSuccessful = false;
		try {
			insertionSuccessful = gestionnaire.insertPatient(patient);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		assertTrue(insertionSuccessful);
		// Verify that the patient was added to the database
		Patient DBPatient = gestionnaire.findByID("2");
		assertNotNull(DBPatient);
		assertEquals(patient.getNom(), DBPatient.getNom());
		assertEquals(patient.getPrenom(), DBPatient.getPrenom());
		assertEquals(patient.getAdresse(), DBPatient.getAdresse());
		assertEquals(patient.getDateNaissance(), DBPatient.getDateNaissance());
		assertEquals(patient.getIdPatient(), DBPatient.getIdPatient());
		assertNotNull(DBPatient.getDossier());
		assertEquals(patient.getDossier().getIdDossier(), DBPatient.getDossier().getIdDossier());
		assertEquals(patient.getDossier().getAntecedents(), DBPatient.getDossier().getAntecedents());
		assertTrue(DBPatient.getDossier().getConsultations().isEmpty());
	}

	@Test
	public void testDeletePatient() throws SQLIntegrityConstraintViolationException {
		// Create a new patient for testing
		Patient patient = new Patient("Trump", "Donald", "Chicago", LocalDateTime.of(1970, 1, 1, 0, 0), "3");
		boolean insertionSuccessful = gestionnaire.insertPatient(patient);
		assertTrue(insertionSuccessful);
		// Delete the patient from the database
		boolean deletionSuccessful = gestionnaire.deletePatient(patient.getIdPatient());
		assertTrue(deletionSuccessful);
		// Verify that the patient was deleted from the database
		Patient DBPatient = gestionnaire.findByID(patient.getIdPatient());
		assertNull(DBPatient);
	}

	@Test
	public void testUpdatePatient() throws SQLException {
		// Create a new patient for testing
		Patient patient = new Patient("Biden", "Joe", "Washington D.C.", LocalDateTime.of(1980, 1, 1, 0, 0), "2");
		boolean insertionSuccessful = gestionnaire.insertPatient(patient);
		assertTrue(insertionSuccessful);
		// Update the patient's information
		patient.setNom("Clinton");
		patient.setPrenom("Bill");
		patient.setDateNaissance(LocalDateTime.of(1960, 1, 1, 0, 0));
		patient.setAdresse("Arkansas");
		boolean updateSuccessful = gestionnaire.updatePatient(patient);
		assertTrue(updateSuccessful);
		// Retrieve the updated patient from the database
		Patient updatedPatient = gestionnaire.findByID("2");
		assertNotNull(updatedPatient);
		assertEquals(patient.getNom(), updatedPatient.getNom());
		assertEquals(patient.getPrenom(), updatedPatient.getPrenom());
		assertEquals(patient.getAdresse(), updatedPatient.getAdresse());
		assertEquals(patient.getDateNaissance(), updatedPatient.getDateNaissance());
		assertEquals(patient.getIdPatient(), updatedPatient.getIdPatient());
		// Clean up the test data
		boolean deletionSuccessful = gestionnaire.deletePatient(patient.getIdPatient());
		assertTrue(deletionSuccessful);
	}

	@Test
	void testFindByName() throws SQLException {
		String patientSql = "INSERT INTO patient (IdPatient, Nom, Prenom, DateNaissance, Adresse) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(patientSql);
		pstmt.setString(1, "10");
		pstmt.setString(2, "Obama");
		pstmt.setString(3, "John");
		pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.of(1970, 1, 1, 0, 0)));
		pstmt.setString(5, "Dallas");
		pstmt.executeUpdate();
		pstmt.close();
		List<Patient> patients = gestionnaire.searchPatientsByName("Obama");
		assertEquals(2, patients.size());
		Patient patient = patients.get(0);
		assertEquals("Obama", patient.getNom());
		gestionnaire.deletePatient("10");
	}
	
	@Test
	public void testUpdateDossier() throws SQLException {
	    // Create a new patient and dossier for testing
	    Patient patient = new Patient("Whashington", "George", "Albuquerque", LocalDateTime.of(1880, 1, 1, 0, 0), "3");
	    boolean insertionSuccessful = gestionnaire.insertPatient(patient);
	    assertTrue(insertionSuccessful);
	    Dossier dossier = new Dossier(patient);
	    dossier.setAntecedents("BPCO - 1999");
	    patient.setDossier(dossier);
	    boolean updateSuccessful = gestionnaire.updateDossier(dossier);
	    assertTrue(updateSuccessful);
	    // Retrieve the updated dossier from the database
	    Dossier updatedDossier = gestionnaire.findDossier("SELECT * FROM dossier WHERE IdDossier  = ?");
	    assertNotNull(updatedDossier);
	    assertEquals(dossier.getIdDossier(), updatedDossier.getIdDossier());
	    assertEquals(dossier.getAntecedents(), updatedDossier.getAntecedents());
	    assertEquals(dossier.getPatient().getIdPatient(), updatedDossier.getPatient().getIdPatient());
	    // Clean up the test data
	    boolean deletionSuccessful = gestionnaire.deletePatient(patient.getIdPatient());
	    assertTrue(deletionSuccessful);
	}

}


