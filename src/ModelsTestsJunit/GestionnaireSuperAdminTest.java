package ModelsTestsJunit;

import static org.junit.jupiter.api.Assertions.*;


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
    
    @Test
    public void testInsertEmployee() {
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
    public void testUpdateEmployee() {
        Medecin Employee = new Medecin("testUp", "Name", "Surname", "Address", 80000.00);
        gestionnaireSuperAdmin.insertEmployee(Employee);
        Medecin updatedEmployee = new Medecin("testUp", "UpdatedName", "UpdatedSurname", "UpdatedAddress", 80000.00);
        boolean updateResult = gestionnaireSuperAdmin.updateEmployee(updatedEmployee);
        assertTrue(updateResult);
        Employee fetchedEmployee = gestionnaireSuperAdmin.findEmployeeByID(updatedEmployee.getId());
        assertEquals(updatedEmployee.getNom(), fetchedEmployee.getNom());
        assertEquals(updatedEmployee.getPrenom(), fetchedEmployee.getPrenom());
        assertEquals(updatedEmployee.getAdresse(), fetchedEmployee.getAdresse());
        assertEquals(updatedEmployee.getSalaire(), fetchedEmployee.getSalaire(), 0.001);
        gestionnaireSuperAdmin.deleteEmployee("testUp");
    }
    
    
    @Test
    public void testDeleteEmployee() {
        boolean result = gestionnaireSuperAdmin.deleteEmployee("test_id");
        assertTrue(result);
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
    public void testDeleteUser() {
        String testId = "test_id";
        boolean isDeleted = gestionnaireSuperAdmin.deleteUser(testId);
        assertTrue(isDeleted);
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
}
