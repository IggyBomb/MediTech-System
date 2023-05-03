package Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Consultation.Consultation;
import ordonnance.AppareilMedical;
import ordonnance.Ordonnance;


class testOrdonnance {
	
	Ordonnance ordonnance;
	
	@BeforeEach
	   void setUp() {
		LocalDateTime testTime = LocalDateTime.now();
        Consultation consult = new Consultation("1","test", null, null, testTime);
        ordonnance = new Ordonnance(consult);
    }
	
	@AfterEach
	void tearDown() {
		ordonnance.clearAppareilMedicaux();
	}

	@Test
	void testDateOrdonnance() {
		LocalDateTime dateOrdonannance = ordonnance.getDateCreation();

	    // Get current date values
	    int currentYear = LocalDateTime.now().getYear();
	    int currentMonth = LocalDateTime.now().getMonthValue();
	    int currentDay = LocalDateTime.now().getDayOfMonth();

	    // Get Ordonnance date values
	    int dossierYear = dateOrdonannance.getYear();
	    int dossierMonth = dateOrdonannance.getMonthValue();
	    int dossierDay = dateOrdonannance.getDayOfMonth();

	    // Compare date
	    assertEquals(currentYear, dossierYear);
	    assertEquals(currentMonth, dossierMonth);
	    assertEquals(currentDay, dossierDay);		
	}

    @Test
    void testAddAppareilMedical() {
        AppareilMedical prothese = new AppareilMedical("P1","prothese main", null, "ottobock", "main gauche");
        assertFalse(prothese.isInstance(), "AppareilMedical instance devrait �tre faux avant d�ajouter � Ordonnance");
        ordonnance.addAppareilMedical(prothese);
        assertTrue(prothese.isInstance(), "AppareilMedical instance devrait �tre vrai apr�s l�ajout � Ordonnance");
    }
    
    @Test
    void testRemoveAppareilMedical() {
        AppareilMedical prothese = new AppareilMedical("P1","prothese main", null, "ottobock", "main gauche");
        ordonnance.addAppareilMedical(prothese);
        assertTrue(prothese.isInstance(), "AppareilMedical instance devrait �tre vrai apr�s l�ajout � Ordonnance");
        assertFalse(ordonnance.removeAppareilMedicalByName("inexistant"), "inexistant non retir�");
        assertTrue(ordonnance.removeAppareilMedicalByName("prothese main"));
        assertFalse(prothese.isInstance(), "AppareilMedical instance devrait �tre faux apr�s l'avoir retir� de l'Ordonnance");
    }
    
}

