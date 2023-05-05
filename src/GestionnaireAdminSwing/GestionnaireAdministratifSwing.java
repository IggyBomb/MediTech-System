package GestionnaireAdminSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Acteurs.Patient;
import formulaires.GestionnaireAdministratif;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireAdministratifSwing extends JFrame {

	private String url;
	private String login, password;
	private Connection connection;

	private JLabel nomLabel;
	private JTextField nomTextField;
	private JButton searchButton;

	private JTable resultTable;
	private GestionnaireAdministratif gestionnaire;
	private Component frame;
	private JTable patientTable;

	public GestionnaireAdministratifSwing(String login, String pass) {
		gestionnaire = new GestionnaireAdministratif(login, pass);
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		// Set look and feel to the system's look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create and set up the window
		JFrame frame = new JFrame("Gestionnaire Administratif");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Create patient table and model
		patientTable = new JTable();
		DefaultTableModel patientTableModel = new DefaultTableModel();
		patientTable.setModel(patientTableModel);

		// Search button
		JButton searchButtonByName = new JButton("Search by Name");
		searchButtonByName.addActionListener(new SearchByName());
		panel.add(searchButtonByName);

		JButton searchButtonById = new JButton("Search by ID");
		searchButtonById.addActionListener(new SearchByID());
		panel.add(searchButtonById);

		// Insert button
		JButton insertButton = new JButton("Insert Patient");
		insertButton.addActionListener(new InsertPatient(gestionnaire));
		panel.add(insertButton);

		// Insert button
		JButton deleteButton = new JButton("Delete Patient");
		deleteButton.addActionListener(new DeleteButton(gestionnaire));
		panel.add(deleteButton);

		// Patient information labels
		JLabel nomLabel = new JLabel("Nom:");
		JLabel prenomLabel = new JLabel("Prénom:");
		JLabel dateNaissanceLabel = new JLabel("Date de naissance:");
		JLabel adresseLabel = new JLabel("Adresse:");
		nomLabel.setVisible(false);
		prenomLabel.setVisible(false);
		dateNaissanceLabel.setVisible(false);
		adresseLabel.setVisible(false);
		panel.add(nomLabel);
		panel.add(prenomLabel);
		panel.add(dateNaissanceLabel);
		panel.add(adresseLabel);

		// Patient information text fields
		JTextField nomField = new JTextField();
		JTextField prenomField = new JTextField();
		JTextField dateNaissanceField = new JTextField();
		JTextField adresseField = new JTextField();
		nomField.setEditable(false);
		prenomField.setEditable(false);
		dateNaissanceField.setEditable(false);
		adresseField.setEditable(false);
		nomField.setVisible(false);
		prenomField.setVisible(false);
		dateNaissanceField.setVisible(false);
		adresseField.setVisible(false);
		panel.add(nomField);
		panel.add(prenomField);
		panel.add(dateNaissanceField);
		panel.add(adresseField);

		frame.getContentPane().add(panel);

		// Display the window
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * The listener for the search button. When the button is clicked,
	 * this listener will search for the patient by name in the database
	 * and display the results in the table.
	 */
	private class SearchByName implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Create a new panel for the search field
			JPanel searchPanel = new JPanel();
			searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

			// Patient last name label and text field
			JLabel nomLabel = new JLabel("Patient Last Name:");
			JTextField nomField = new JTextField();
			searchPanel.add(nomLabel);
			searchPanel.add(nomField);

			// Show the search panel in a dialog box
			int result = JOptionPane.showConfirmDialog(frame, searchPanel, "Search by Last Name", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String searchName = nomField.getText();
				List<Patient> patients = gestionnaire.searchPatientsByName(searchName);
				if (patients.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "No patients found with that name.", "Search results", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Object[][] patientData = new Object[patients.size()][3];
					for (int i = 0; i < patients.size(); i++) {
						patientData[i][0] = patients.get(i).getIdPatient();
						patientData[i][1] = patients.get(i).getNom();
						patientData[i][2] = patients.get(i).getPrenom();
					}
					DefaultTableModel patientTableModel = new DefaultTableModel(patientData, new Object[]{"ID", "Nom", "Prénom"});
					patientTable.setModel(patientTableModel);
					patientTable.getColumnModel().getColumn(0).setPreferredWidth(100);
					patientTable.getColumnModel().getColumn(1).setPreferredWidth(150);
					patientTable.getColumnModel().getColumn(2).setPreferredWidth(150);
				}
			}
		}
	}

	/**
	 * The listener for the search button. When the button is clicked,
	 * this listener will search for the patient by ID in the database
	 * and display the result in the table.
	 */
	private class SearchByID implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Create a new panel for the search field
			JPanel searchPanel = new JPanel();
			searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

			// Patient ID label and text field
			JLabel idLabel = new JLabel("Patient ID:");
			JTextField idField = new JTextField();
			searchPanel.add(idLabel);
			searchPanel.add(idField);

			// Show the search panel in a dialog box
			int result = JOptionPane.showConfirmDialog(frame, searchPanel, "Search by ID", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String searchId = idField.getText();
				Patient patient = gestionnaire.findByID(searchId);
				if (patient == null) {
					JOptionPane.showMessageDialog(frame, "No patient found with that ID.", "Search results", JOptionPane.INFORMATION_MESSAGE);
				} else {
					// Update the patient information fields with the patient data
					idField.setText(patient.getIdPatient());
					JTextField nomField = new JTextField();
					nomField.setText(patient.getNom());
					JTextField prenomField = new JTextField();
					prenomField.setText(patient.getPrenom());
					JTextField dateNaissanceField = new JTextField();
					dateNaissanceField.setText(patient.getDateNaissance().toString());
					JTextField adresseField = new JTextField();
					adresseField.setText(patient.getAdresse());
					// Show the patient information in a dialog box
					JPanel patientPanel = new JPanel();
					patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));
					patientPanel.add(new JLabel("ID:"));
					patientPanel.add(idField);
					patientPanel.add(new JLabel("Nom:"));
					patientPanel.add(nomField);
					patientPanel.add(new JLabel("Prénom:"));
					patientPanel.add(prenomField);
					patientPanel.add(new JLabel("Date de naissance:"));
					patientPanel.add(dateNaissanceField);
					patientPanel.add(new JLabel("Adresse:"));
					patientPanel.add(adresseField);
					JOptionPane.showMessageDialog(frame, patientPanel, "Search results", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
	}


	/*
	public Dossier findDossier(String sql, String id, Patient p1) throws SQLException {
        // method code
    }

    public Patient findByID(String id) {
        // method code
    }

    public List<Patient> searchPatientsByName(String name) {
        // method code
    }

    public boolean insertPatient(Patient patient) {
        // method code
    }

    public boolean deletePatient(String id) {
        // method code
    }

    public boolean updatePatient(Patient patient) throws SQLException {
        // method code
    }

    public boolean updateDossier(Dossier dossier) throws SQLException {
        // method code
    }

    private class SearchButtonListener implements ActionListener {
        // method code
    }

    private JTable createTable() {
        // method code
    }

    private void displaySearchResults(List<Patient> patients) {
        // method code
    }

    private void displayPatientInformation(Patient patient) {
        // method code
    }

    private void displayErrorMessage(String message) {
        // method code
    }

    private void clearForm() {
        // method code
    }
	 */

}