package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import Acteurs.AgentAdministration;
import Acteurs.Employee;
import Acteurs.Medecin;
import Acteurs.Technicien;

public class GestionnaireSuperAdmin {

	private String url;
	private String login, password;
	private Connection connection;

	public GestionnaireSuperAdmin(String login, String pass) {
		this.url = "jdbc:mysql://127.0.0.1:3306/nfa019project";
		this.login = login;
		this.password = pass;
		this.connection = SingleConnection.getInstance(url, login, pass);
	}

	/**
	 * Inserts a new employee into the database.
	 * 
	 * @param employee The employee to insert.
	 * @param table The table to insert the employee into.
	 * @return true if the employee was inserted successfully, false otherwise.
	 */
	public boolean insertEmployee(Employee employee) throws SQLIntegrityConstraintViolationException  {
		String sql = "INSERT INTO " + employee.getRole() + " (Id, Nom, Prenom, Adresse, Salaire) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, employee.getId());
			pstmt.setString(2, employee.getNom());
			pstmt.setString(3, employee.getPrenom());
			pstmt.setString(4, employee.getAdresse());
			pstmt.setDouble(5, employee.getSalaire());
			int rowsAffected = pstmt.executeUpdate();
			pstmt.close();
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Deletes an employee from the database.
	 * 
	 * @param id the ID of the employee to delete
	 * @return true if the employee was deleted successfully, false otherwise
	 */
	public boolean deleteEmployee(String id) {
		String sql = 
				"CREATE TEMPORARY TABLE tmp AS " +
						"SELECT 'medecin' as source_table FROM medecin WHERE Id = ? " +
						"UNION " +
						"SELECT 'technicien' FROM technicien WHERE Id = ? " +
						"UNION " +
						"SELECT 'admin' FROM admin WHERE Id = ?";

		String sourceTable = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.execute();

			// Now, retrieve the source_table from the temporary table
			sql = "SELECT source_table FROM tmp LIMIT 1";
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sourceTable = rs.getString("source_table");
			}
			pstmt.execute("DROP TABLE tmp");  // Remember to drop the temporary table

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!sourceTable.isEmpty()) {
			sql = "DELETE FROM " + sourceTable + " WHERE Id = ?";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, id);
				int deletedRows = pstmt.executeUpdate();
				return deletedRows > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}



	/**
	 * Updates an employee data in the database.
	 * 
	 * @return true if the employee was updated successfully, false otherwise
	 */
	public boolean updateEmployee(Employee employee, String profession) {
		String sql = "UPDATE " + profession + " SET Id = ?, Nom = ?, Prenom = ?, Adresse = ?, Salaire = ? WHERE Id = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, employee.getId());
			pstmt.setString(2, employee.getNom());
			pstmt.setString(3, employee.getPrenom());
			pstmt.setString(4, employee.getAdresse());
			pstmt.setDouble(5, employee.getSalaire());
			pstmt.setString(6, employee.getId());
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

	/**
	 * Search all the doctors with the same name in the database.
	 * 
	 * @param the name of the doctor to search
	 * @return a list with all the doctors with the same name.
	 */
	public List<Medecin> searchMedecinByName(String name) {
		List<Medecin> medecins = new ArrayList<>();
		String sql = "SELECT * FROM medecin WHERE Nom = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("Id");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				Double salaire = rs.getDouble("Salaire");
				Medecin medecin  = new Medecin(id, nom, prenom, adresse, salaire);
				medecins.add(medecin);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecins;
	}



	/**
	 * Search for a employee in the database.
	 * 
	 * @param id the ID of the employee to search
	 * @return the Employee with the corresponding input ID
	 */
	public Employee findEmployeeByID(String id) {
		Employee employee = null;
		String sql = 
				"SELECT 'medecin' as source_table, Id, Nom, Prenom, Adresse, Salaire FROM medecin WHERE Id = ? " +
						"UNION " +
						"SELECT 'technicien' as source_table, Id, Nom, Prenom, Adresse, Salaire FROM technicien WHERE Id = ? " +
						"UNION " +
						"SELECT 'admin' as source_table, Id, Nom, Prenom, Adresse, Salaire FROM admin WHERE Id = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				String sourceTable = resultSet.getString("source_table");
				if (sourceTable.equals("medecin")) {
					employee = new Medecin(
							resultSet.getString("Id"),
							resultSet.getString("Nom"),
							resultSet.getString("Prenom"),
							resultSet.getString("Adresse"),
							resultSet.getDouble("Salaire")
							);
				} else if (sourceTable.equals("technicien")) {
					employee = new Technicien(
							resultSet.getString("Id"),
							resultSet.getString("Nom"),
							resultSet.getString("Prenom"),
							resultSet.getString("Adresse"),
							resultSet.getDouble("Salaire")
							);
				} else if (sourceTable.equals("admin")) {
					employee = new AgentAdministration(
							resultSet.getString("Id"),
							resultSet.getString("Nom"),
							resultSet.getString("Prenom"),
							resultSet.getString("Adresse"),
							resultSet.getDouble("Salaire")
							);
				}
			}
			resultSet.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	



	/**
	 * Search all the employees with the same name in the database.
	 * 
	 * @param the name of the employee to search
	 * @return a list with all the doctors with the same name.
	 */
	public List<Employee> searchEmployeeByName(String name) {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT 'medecin' as source_table, Id, Nom, Prenom, Adresse, Salaire FROM medecin WHERE Nom = ? " +
				"UNION " +
				"SELECT 'technicien' as source_table, Id, Nom, Prenom, Adresse, Salaire FROM technicien WHERE Nom = ? " +
				"UNION " +
				"SELECT 'admin' as source_table, Id, Nom, Prenom, Adresse, Salaire FROM admin WHERE Nom = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			pstmt.setString(3, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("Id");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				Double salaire = rs.getDouble("Salaire");
				Employee employee  = new Employee(id, nom, prenom, adresse, salaire);
				employees.add(employee);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public String findProfession(String id) {
		String profession = "";
		String sql = "SELECT profession FROM login WHERE ID = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				profession = rs.getString("profession");
			}
			pstmt.close();
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return profession;
	}
	
	/**
	 * Search employees by their profession.
	 *
	 * @param profession The profession to search by.
	 * @return A List of Employee objects with the given profession.
	 * @throws SQLException If there is an error executing the SQL query.
	 */
	public List<Employee> searchEmployeeByProfession(String profession){
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM " + profession;
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("Id");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				Double salaire = rs.getDouble("Salaire");
				Employee employee  = new Employee(id, nom, prenom, adresse, salaire);
				list.add(employee);
			}
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * create a new User Login in the database.
	 *
	 * @return a boolean if the insertion was successful.
	 */
	public boolean createUser(Employee employee, String user, String pass, String profession) {
		String userSql = "INSERT INTO login (ID, username, password, profession) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(userSql);
			pstmt.setString(1, employee.getId());
			pstmt.setString(2, user);
			pstmt.setString(3, pass);
			pstmt.setString(4, profession);
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

	/**
	 * Delete a new User Login in the database.
	 *
	 * @return a boolean if the deletion was successful.
	 */
	public boolean deleteUser(String  idUser) {
		String userSql = "DELETE FROM login WHERE ID = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(userSql);
			pstmt.setString(1, idUser);
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


	/**
	 * Search a  User  in the database.
	 *
	 *
	 * @return a String with the password and username.
	 */
	public String searchUser(String idEmployee){
		String account = "";
		String userSql = "SELECT username, password FROM login WHERE ID = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(userSql);
			pstmt.setString(1, idEmployee);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				account = "user: " + username + " --- " + "password: " + password;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
	
	/**
	 * Update a  User  in the database.
	 *
	 * @param the new ID, the new UserName, the new Password, The new Profession, the old ID to search the account in the database
	 * @return a boolean with the result of the update.
	 */
	public boolean updateUser(String ID, String user, String passw, String profess){
		String sql = "UPDATE login SET ID = ?, username = ?, password = ?, profession = ? WHERE ID = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ID);
			pstmt.setString(2, user);
			pstmt.setString(3, passw);
			pstmt.setString(4, profess);
			pstmt.setString(5, ID);
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
}

