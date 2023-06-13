package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelStat {
	private String url;
	private String login, password;
	private Connection connection;

	public ModelStat(String login, String pass) {
		this.url = "jdbc:mysql://127.0.0.1:3306/nfa019project";
		this.login = login;
		this.password = pass;
		this.connection = SingleConnection.getInstance(url, login, pass);
	}
	
	
	/**
	 * This method retrieves the statistics for the consultations from the "consultation" table in a database,
	 * aggregated by month within a given year.
	 *
	 * The returned list contains Map objects, where each map represents data for a specific month. The key is the
	 * month number (1 for January, 2 for February, ..., 12 for December), and the value is the number of consultations 
	 * for that month.
	 *
	 * The list is sorted in descending order based on the number of consultations. That means the month with
	 * the highest number of consultations will be at the beginning of the list.
	 *
	 * @param year The year for which the statistics are to be retrieved.
	 * @return A list of Map objects containing the month number as key and the count of consultations as value.
	 * @throws SQLException If a database access error occurs or this method is called on a closed connection.
	 */
	public List<Map<Integer, Integer>> getMonthlyConsultationsStats(int year) {
		List<Map<Integer, Integer>> monthlyStats = new ArrayList<>();
		String query = "SELECT MONTH(Date) AS month, COUNT(*) AS count " +
				"FROM consultation " +
				"WHERE YEAR(Date) = ? " +
				"GROUP BY MONTH(Date) " +
				"ORDER BY count DESC";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Map<Integer, Integer> monthData = new HashMap<>();
				monthData.put(rs.getInt("month"), rs.getInt("count"));
				monthlyStats.add(monthData);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monthlyStats;
	}
	
	
	
	/**
	 * This method retrieves the total number of consultations from the "consultation" table in the database 
	 * for a given year.
	 *
	 * The method queries the database, counts the number of consultations made in the specified year, 
	 * and then returns this count.
	 *
	 * @param year The year for which the total consultations are to be counted.
	 * @return The total number of consultations made in the specified year.
	 * @throws SQLException If a database access error occurs or this method is called on a closed connection.
	 */
	public int getTotalConsultations(int year) {
		int totalConsultations = 0;
		String query = "SELECT COUNT(*) AS count FROM consultation WHERE YEAR(Date) = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				totalConsultations = rs.getInt("count");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalConsultations;
	}
	
	
	/**
	 * This method retrieves statistics for the granted device statuses from the "ordonnance" table in a database,
	 * aggregated by month within a given year.
	 *
	 * The returned list contains Map objects, where each map represents data for a specific month. The key is the
	 * month number (1 for January, 2 for February, ..., 12 for December), and the value is the number of granted
	 * device statuses for that month.
	 *
	 * The list is sorted in descending order based on the number of granted device statuses. That means the month with
	 * the highest number of granted device statuses will be at the beginning of the list.
	 *
	 * @param year The year for which the statistics are to be retrieved.
	 * @return A list of Map objects containing the month number as key and the count of granted device statuses as value.
	 * @throws SQLException If a database access error occurs or this method is called on a closed connection.
	 */
	public List<Map<Integer, Integer>> getMonthlyGrantedDeviceStatusStats(int year) {
	    List<Map<Integer, Integer>> monthlyStats = new ArrayList<>();
	    String query = "SELECT MONTH(dateCreation) AS month, COUNT(*) AS count " +
	            "FROM ordonnance " +
	            "WHERE YEAR(dateCreation) = ? AND deviceStatus = 'octroyé' " +
	            "GROUP BY MONTH(dateCreation) " +
	            "ORDER BY count DESC";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setInt(1, year);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Map<Integer, Integer> monthData = new HashMap<>();
	            monthData.put(rs.getInt("month"), rs.getInt("count"));
	            monthlyStats.add(monthData);
	        }
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return monthlyStats;
	}
	
	/**
	 * This method retrieves the total number of devices granted from the "ordonannace" table in the database 
	 * for a given year.
	 *
	 * The method queries the database, counts the number of devices granted made in the specified year, 
	 * and then returns this count.
	 *
	 * @param year The year for which the total devices granted are to be counted.
	 * @return The total number of devices granted made in the specified year.
	 * @throws SQLException If a database access error occurs or this method is called on a closed connection.
	 */
	public int getTotalDevicesGranted(int year) {
		int totalConsultations = 0;
		String query = "SELECT COUNT(*) AS count FROM ordonnance WHERE YEAR(dateCreation) = ? AND deviceStatus = 'octroyé' ";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				totalConsultations = rs.getInt("count");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalConsultations;
	}
	
	/**
	 * This method retrieves the total number of devices  from the "ordonannace" table in the database 
	 * for a given year.
	 *
	 * The method queries the database, counts the number of devices prescribed made in the specified year, 
	 * and then returns this count.
	 *
	 * @param year The year for which the total devices prescribed are to be counted.
	 * @return The total number of devices prescribed in the specified year.
	 * @throws SQLException If a database access error occurs or this method is called on a closed connection.
	 */
	public int getTotalDevices(int year) {
		int totalConsultations = 0;
		String query = "SELECT COUNT(*) AS count FROM ordonnance WHERE YEAR(dateCreation) = ? AND deviceStatus IS NOT NULL ";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				totalConsultations = rs.getInt("count");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalConsultations;
	}
}

	


