package ModelsTestsJunit;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Models.Consultation;
import Models.Ordonnance;


class testOrdonnance {
	
	Ordonnance ordonnance;
	
	@BeforeEach
	   void setUp() {
		LocalDateTime testTime = LocalDateTime.now();
        Consultation consult = new Consultation("1","test", null, null, testTime);
        ordonnance = new Ordonnance(consult);
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

    

}

