package ModelsTestsJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Acteurs.Employee;
import Acteurs.Medecin;
import Acteurs.Technicien;
import Models.GestionnaireSuperAdmin;

class GestionnaireSuperAdminTest {

    private static GestionnaireSuperAdmin gestionnaireSuperAdmin;
    private static Medecin med;
   
    @BeforeAll
    public static void setUp() {
        String login = "root";
        String password = "T1t4n1c0";
        gestionnaireSuperAdmin = new GestionnaireSuperAdmin(login, password);
        med = new Medecin("test_id", "test_name", "test_surname", "test_address", 50000);
    }
    
    @AfterAll
    public static void TearDown() {
    	gestionnaireSuperAdmin.deleteEmployee("test_id");
    	gestionnaireSuperAdmin.deleteUser("test_id");
    	
    }
    
    
    @Test
    public void testInsertEmployee() throws SQLIntegrityConstraintViolationException {
        Employee employee = new Medecin("test_id", "test_nom", "test_prenom", "test_adresse", 10000.00);
        boolean result = gestionnaireSuperAdmin.insertEmployee(employee);
        assertTrue(result);
    }

    @Test
    public void testFindEmployeeByID() {
        String testId = "test_id";
        Employee employee = gestionnaireSuperAdmin.findEmployeeByID(testId);
        assertNotNull(employee);
        assertEquals(testId, employee.getId());
        assertEquals("test_nom", employee.getNom());
        assertEquals("test_prenom", employee.getPrenom());
        assertEquals("test_adresse", employee.getAdresse());
        assertEquals(10000.00, employee.getSalaire(), 0.001);
    }
    
    
    @Test
    public void testUpdateEmployee() throws SQLIntegrityConstraintViolationException {
        Medecin Employee = new Medecin("testUp", "Name", "Surname", "Address", 80000.00);
        gestionnaireSuperAdmin.insertEmployee(Employee);
        Medecin updatedEmployee = new Medecin("testUp", "UpdatedName", "UpdatedSurname", "UpdatedAddress", 80000.00);
        boolean updateResult = gestionnaireSuperAdmin.updateEmployee(updatedEmployee, updatedEmployee.getRole());
        assertTrue(updateResult);
        Employee fetchedEmployee = gestionnaireSuperAdmin.findEmployeeByID(updatedEmployee.getId());
        assertEquals(updatedEmployee.getNom(), fetchedEmployee.getNom());
        assertEquals(updatedEmployee.getPrenom(), fetchedEmployee.getPrenom());
        assertEquals(updatedEmployee.getAdresse(), fetchedEmployee.getAdresse());
        assertEquals(updatedEmployee.getSalaire(), fetchedEmployee.getSalaire(), 0.001);
        gestionnaireSuperAdmin.deleteEmployee("testUp");
    }
    
    @Test
    public void testCreateUser() {
        Employee testEmployee = new Technicien("test_id", "test_name", "test_surname", "test_address", 50000);
        String user = "test_user";
        String pass = "test_pass";
        String profession = "test_profession";
        boolean isCreated = gestionnaireSuperAdmin.createUser(testEmployee, user, pass, profession);
        assertTrue(isCreated);
    }

    

    @Test
    public void testSearchUser() {
        Employee testEmployee = new Technicien("test_id", "test_name", "test_surname", "test_address", 50000);
        String user = "test_user";
        String pass = "test_pass";
        String profession = "test_profession";
        boolean isCreated = gestionnaireSuperAdmin.createUser(testEmployee, user, pass, profession);
        String testId = "test_id";
        String result = gestionnaireSuperAdmin.searchUser(testId);
        String expected = "user: test_user --- password: test_pass";
        assertEquals(expected, result);
        boolean isDeleted = gestionnaireSuperAdmin.deleteUser(testId);
    }
    
	@Test
	public void testFindProfession() throws SQLException {
        Employee testEmployee = new Technicien("test_id", "test_name", "test_surname", "test_address", 50000);
        String user = "test_user";
        String pass = "test_pass";
        String profession = "test_profession";
        gestionnaireSuperAdmin.createUser(testEmployee, user, pass, profession);
        String professionSearch = gestionnaireSuperAdmin.findProfession("test_id");
        assertEquals(professionSearch, "test_profession");
        gestionnaireSuperAdmin.deleteEmployee("test_id");
        gestionnaireSuperAdmin.deleteUser("test_id");
	}
	
	@Test
	public void testSearchEmployeeByName() throws SQLIntegrityConstraintViolationException {
	    Employee testEmployee = new Medecin("test_id", "UniqueName", "test_surname", "test_address", 60000);
	    gestionnaireSuperAdmin.insertEmployee(testEmployee);
	    List<Employee> result = gestionnaireSuperAdmin.searchEmployeeByName("UniqueName");
	    assertNotNull(result);
	    assertTrue(result.size() > 0);
	    Employee foundEmployee = result.get(0);
	    assertEquals("test_id", foundEmployee.getId());
	    assertEquals("UniqueName", foundEmployee.getNom());
	    assertEquals("test_surname", foundEmployee.getPrenom());
	    assertEquals("test_address", foundEmployee.getAdresse());
	    assertEquals(60000, foundEmployee.getSalaire(), 0.001);
	    gestionnaireSuperAdmin.deleteEmployee("test_id");
	    gestionnaireSuperAdmin.deleteUser("test_id");
	}
}
