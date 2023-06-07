package ModelsTestsJunit;

import org.junit.jupiter.api.*;

import Acteurs.Medecin;
import Acteurs.Patient;
import DossierMedicale.Dossier;
import Models.Consultation;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class GestonnaireConsultationTest {

	private static Connection connection;
	private static GestionnaireConsultation gestionnaire;
	private static GestionnaireAdministratif admin;
	private static Patient patient;
	private static Medecin medecin;

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
	public void testGetConsultationByID() {
		// Add a new consultation to the database
		Consultation consultation = new Consultation("Test_2", "1_pat", "1_med", "details_test", LocalDateTime.of(2023, 1, 1, 0, 0));
		boolean insertionSuccessful = gestionnaire.addConsultation(consultation);
		assertTrue(insertionSuccessful);
		// Retrieve the consultation using its ID
		Consultation dbConsultation = gestionnaire.getConsultationByID(consultation.getIdConsult());
		assertNotNull(dbConsultation);
		// Verify that the retrieved consultation matches the original one
		assertEquals(consultation.getPatient(), dbConsultation.getPatient());
		assertEquals(consultation.getMedecin(), dbConsultation.getMedecin());
		assertEquals(consultation.getDetailsCliniques(), dbConsultation.getDetailsCliniques());
		assertEquals(consultation.getDate(), dbConsultation.getDate());
		assertEquals(consultation.getOrdonnance(), dbConsultation.getOrdonnance());
		// Remove the test consultation from the database
		boolean deletionSuccessful = gestionnaire.deleteConsultation(consultation.getIdConsult());
		assertTrue(deletionSuccessful);
	}



	@Test
	public void testAddConsultation() {
		Consultation consultation = new Consultation("Test_1" ,"1_pat", "1_med", "details_test", LocalDateTime.of(2023, 1, 1, 0, 0));
		boolean insertionSuccessful = gestionnaire.addConsultation(consultation);
		System.out.println("Insertion successful: " + insertionSuccessful);
		assertTrue(insertionSuccessful);
		// Verify that the consultation was added to the database
		Consultation DBConsultation = gestionnaire.getConsultationByID(consultation.getIdConsult());
		assertNotNull(DBConsultation);
		assertEquals(consultation.getPatient(), DBConsultation.getPatient());
		assertEquals(consultation.getMedecin(), DBConsultation.getMedecin());
		assertEquals(consultation.getDetailsCliniques(), DBConsultation.getDetailsCliniques());
		assertEquals(consultation.getDate(), DBConsultation.getDate());
		assertEquals(consultation.getOrdonnance(), DBConsultation.getOrdonnance());
		gestionnaire.deleteConsultation("Test_1");
		
	}


	@Test
	public void testListConsultationPatient() {
		// Insert test data
		Consultation c1 = new Consultation("Test1", "1_pat", "1_med", "details_test1", LocalDateTime.of(2023, 1, 1, 0, 0));
		Consultation c2 = new Consultation("Test2", "1_pat", "1_med", "details_test2", LocalDateTime.of(2022, 1, 1, 0, 0));
		Consultation c3 = new Consultation("Test3", "1_pat", "1_med", "details_test3", LocalDateTime.of(2021, 1, 1, 0, 0));
		gestionnaire.addConsultation(c1);
		gestionnaire.addConsultation(c2);
		gestionnaire.addConsultation(c3);
		List<Consultation> result = gestionnaire.listConsultationPatient("1_pat");
		assertNotNull(result);
		assertEquals(3, result.size());
		assertEquals(c1.getIdConsult(), result.get(0).getIdConsult());
		assertEquals(c2.getIdConsult(), result.get(1).getIdConsult());
		// Delete test data
		gestionnaire.deleteConsultation(c1.getIdConsult());
		gestionnaire.deleteConsultation(c2.getIdConsult());
		gestionnaire.deleteConsultation(c3.getIdConsult());
	}

	@Test
	void testUpdateConsultation() {
		Consultation consultation4 = new Consultation("Test4", "1_pat", "1_med", "Neurologie", LocalDateTime.now());
		gestionnaire.addConsultation(consultation4);
		consultation4.setDetailsCliniques("Migraine");
		boolean updated = gestionnaire.updateConsultation(consultation4);
		assertTrue(updated);
		Consultation updatedConsultation = gestionnaire.getConsultationByID("Test4");
		// check that the details cliniques have been updated
		assertEquals("Migraine", updatedConsultation.getDetailsCliniques());
		boolean deleted = gestionnaire.deleteConsultation("Test4");
		assertTrue(deleted);
	}

	@Test
	public void testGetDetailsPatient() throws SQLException {
		// Add a new patient and dossier to the database
		Patient patient = new Patient("Macron", "Emmanuel", "Paris", LocalDateTime.of(1980, 1, 1, 0, 0), "2_pat");
		admin.insertPatient(patient);
		Dossier dossier = new Dossier(patient);
		dossier.setAntecedents("Antecedents test");
		admin.updateDossier(dossier);
		// Retrieve the patient details and antecedents
		String expectedDetails = "Antecedents test";
		String actualDetails = gestionnaire.getDetailsPatient(patient.getIdPatient());
		assertEquals(expectedDetails, actualDetails);
		// Delete the test patient and dossier from the database
		admin.deletePatient(patient.getIdPatient());
	}
	
	
	/*
	@Test
	public void testListConsultationPatientByName() {
		// Add new patients and consultations to the database
		Patient patient1 = new Patient("John", "Doe", "New York", LocalDateTime.of(1990, 1, 1, 0, 0), "john1");
		Patient patient2 = new Patient("John", "Doe", "Los Angeles", LocalDateTime.of(1990, 2, 2, 0, 0), "john2");
		admin.insertPatient(patient1);
		admin.insertPatient(patient2);
		Consultation consultation1 = new Consultation("C1", "john1", "1_med", "details_test", LocalDateTime.now());
		Consultation consultation2 = new Consultation("C2", "john2", "1_med", "details_test", LocalDateTime.now());
		gestionnaire.addConsultation(consultation1);
		gestionnaire.addConsultation(consultation2);

		// Test the listConsultationPatientByName method
		List<Consultation> result = gestionnaire.listConsultationPatientByName("John Doe");

		// Check if the result is not null and contains the consultations we added
		assertNotNull(result);
		assertTrue(result.stream().anyMatch(c -> c.getIdConsult().equals("C1")));
		assertTrue(result.stream().anyMatch(c -> c.getIdConsult().equals("C2")));

		// Clean up the test data
		gestionnaire.deleteConsultation(consultation1.getIdConsult());
		gestionnaire.deleteConsultation(consultation2.getIdConsult());
		admin.deletePatient(patient1.getIdPatient());
		admin.deletePatient(patient2.getIdPatient());
	}
	*/



}
