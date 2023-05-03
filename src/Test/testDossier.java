package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Consultation.Consultation;
import DossierMedicale.Dossier;

class testDossier {

	Dossier dossier;

	@BeforeEach
	void setup() {
		dossier = new Dossier(null);
	}

	@AfterEach
	void tearDown() {
		dossier = null;
	}

	@Test
	void testDateDossier() {
	    LocalDateTime dossierDateTime = dossier.getDateCreation();

	    // Get current date values
	    int currentYear = LocalDateTime.now().getYear();
	    int currentMonth = LocalDateTime.now().getMonthValue();
	    int currentDay = LocalDateTime.now().getDayOfMonth();

	    // Get dossier date values
	    int dossierYear = dossierDateTime.getYear();
	    int dossierMonth = dossierDateTime.getMonthValue();
	    int dossierDay = dossierDateTime.getDayOfMonth();

	    // Compare date components
	    assertEquals(currentYear, dossierYear);
	    assertEquals(currentMonth, dossierMonth);
	    assertEquals(currentDay, dossierDay);
	}
	
	@Test
	void testRemoveConsultation() {
		Consultation consult = new Consultation("1", "test", null, null, null);
		dossier.addConsultation(consult);
		assertFalse(dossier.removeConsultation("0"));
		assertTrue(dossier.removeConsultation("1"));
	}
	
	@Test
	void testSearchConsultationID() {
		Consultation consult = new Consultation("123", "test", null, null, null);
		dossier.addConsultation(consult);
		assertEquals(consult, dossier.searchConsultationID("123"));
	}
	
	@Test
	void testSearchConsultationDate() {
		LocalDateTime dateConsult = LocalDateTime.of(2023, 01, 01, 10, 00);
	    Consultation consult = new Consultation(null, "test", null, null, dateConsult);
	    dossier.addConsultation(consult);
	    assertEquals(consult, dossier.searchConsultationDate(dateConsult));
	}
	
}


