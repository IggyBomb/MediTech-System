package ViewAdmin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import Acteurs.Patient;
import Models.Consultation;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;
import ViewSuperAdmin.ViewUpdateEmployee;
import constrollersViewAdmin.ControllerViewHomePageAdmin;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class ViewHomePageAdmin {

	private JPanel contentPane;
	private GestionnaireAdministratif Admin =  new GestionnaireAdministratif("root", "T1t4n1c0");
	private GestionnaireConsultation gestionnaireConsult = new GestionnaireConsultation("root", "T1t4n1c0");
	private JFrame frame;
	private JTextField IDsearchTextField;
	private JTextField textFieldName;
	private JTable tableResultsPatient;
	private JTable tableResultsConsult;
	private JTextField textField_idPatientConsult;
	private JTextField textField_searchByName;
	private ControllerViewHomePageAdmin controller;
	private JTextField textField_UpdatePat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHomePageAdmin window = new ViewHomePageAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewHomePageAdmin() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controller = new ControllerViewHomePageAdmin(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 676);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Gestionnaire Administratif");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblNewLabel.setBounds(149, 10, 214, 26);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 47, 228, 73);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID ");
		lblNewLabel_1.setBounds(21, 21, 31, 13);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel_1);

		IDsearchTextField = new JTextField();
		IDsearchTextField.setBounds(62, 18, 156, 19);
		panel.add(IDsearchTextField);
		IDsearchTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = Admin.findPatientByID(IDsearchTextField.getText());
				if (patient == null) {
					JOptionPane.showMessageDialog(frame, "ID not found.", "Search results", JOptionPane.INFORMATION_MESSAGE);
				}else {
					String[] columnNames = {"ID", "Last Name", "First Name", "Address",  "Birthdate"};
					DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
					Object[] rowData = {
							patient.getIdPatient(),
							patient.getNom(), 
							patient.getPrenom(), 
							patient.getAdresse(),
							patient.getDateNaissance()
					};
					tableModel.addRow(rowData);
					tableResultsPatient.setModel(tableModel); // Set the table model for tableResults
				}
			}
		});
		btnSearch.setBounds(54, 44, 76, 19);
		panel.add(btnSearch);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDsearchTextField.setText("");				
			}
		});
		btnDelete.setBounds(142, 44, 76, 19);
		panel.add(btnDelete);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search Patient By Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 130, 228, 73);
		frame.getContentPane().add(panel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(10, 21, 40, 13);
		panel_2.add(lblNewLabel_1_1);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(60, 18, 158, 19);
		panel_2.add(textFieldName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 46, 401, 153);
		frame.getContentPane().add(scrollPane);

		tableResultsPatient = new JTable();
		scrollPane.setViewportView(tableResultsPatient);

		JButton btnSearchByName = new JButton("Search");
		btnSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Patient> patients = Admin.searchPatientsByName(textFieldName.getText());
				if (patients.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Name not found.", "Search results", JOptionPane.INFORMATION_MESSAGE);
				}else {
					String[] columnNames = {"ID", "Last Name", "First Name", "Address",  "Birthdate"};
					DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
					for (Patient patient : patients) {
						Object[] rowData = {
								patient.getIdPatient(),
								patient.getNom(), 
								patient.getPrenom(), 
								patient.getAdresse(),
								patient.getDateNaissance()
						};
						tableModel.addRow(rowData);
					}
					tableResultsPatient.setModel(tableModel); // Set the table model for tableResults

				}
			}
		});
		btnSearchByName.setBounds(54, 44, 76, 19);
		panel_2.add(btnSearchByName);

		JButton btnDeleteByName = new JButton("Delete");
		btnDeleteByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldName.setText("");
			}
		});
		btnDeleteByName.setBounds(142, 44, 76, 19);
		panel_2.add(btnDeleteByName);

		JButton btnInsert = new JButton("New patient");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewInsertPatient.main(null);

			}
		});
		btnInsert.setBounds(398, 243, 120, 33);
		frame.getContentPane().add(btnInsert);

		JButton btnDeletePatient = new JButton("Delete patient");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePatient.main(null);
			}
		});
		btnDeletePatient.setBounds(528, 243, 122, 33);
		frame.getContentPane().add(btnDeletePatient);

		JButton btnRefreshTable = new JButton("Refresh");
		btnRefreshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				tableResultsPatient.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnRefreshTable.setBounds(564, 209, 85, 21);
		frame.getContentPane().add(btnRefreshTable);

		JScrollPane scrollPane_Consult = new JScrollPane();
		scrollPane_Consult.setBounds(248, 332, 401, 180);
		frame.getContentPane().add(scrollPane_Consult);

		JLabel lblTitle2 = new JLabel("Gestionnaire Consultation");
		lblTitle2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle2.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle2.setBounds(149, 286, 271, 35);
		frame.getContentPane().add(lblTitle2);

		JPanel panel_SearchConsult = new JPanel();
		panel_SearchConsult.setBorder(new TitledBorder(null, "Search Consultation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SearchConsult.setBounds(10, 332, 228, 79);
		frame.getContentPane().add(panel_SearchConsult);
		panel_SearchConsult.setLayout(null);

		JLabel lblSearchConsult = new JLabel("Id Consultation");
		lblSearchConsult.setBounds(10, 24, 59, 13);
		panel_SearchConsult.add(lblSearchConsult);

		JTextField textField_searchConsult = new JTextField();
		textField_searchConsult.setBounds(79, 21, 139, 19);
		panel_SearchConsult.add(textField_searchConsult);
		textField_searchConsult.setColumns(10);

		tableResultsConsult = new JTable();
		scrollPane_Consult.setViewportView(tableResultsConsult);

		JButton btnSearch_Consult = new JButton("Search");
		btnSearch_Consult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultation consultation = gestionnaireConsult.getConsultationByID(textField_searchConsult.getText());
				if (consultation == null) {
					JOptionPane.showMessageDialog(frame, "ID not found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				}else {
					String[] columnNames = {"IdConsult", "PatientID", "MedecinID", "DetailsCliniques", "Date"};
					DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
					Object[] rowData = {
							consultation.getIdConsult(),
							consultation.getPatient(), 
							consultation.getMedecin(), 
							consultation.getDetailsCliniques(),
							consultation.getDate()
					};
					tableModel.addRow(rowData);
					tableResultsConsult.setModel(tableModel); // Set the table model for tableResult
				}
			}
		});
		btnSearch_Consult.setBounds(76, 50, 76, 19);
		panel_SearchConsult.add(btnSearch_Consult);

		JButton btnRefreshTable_Consult = new JButton("Refresh");
		btnRefreshTable_Consult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				tableResultsConsult.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnRefreshTable_Consult.setBounds(564, 522, 85, 21);
		frame.getContentPane().add(btnRefreshTable_Consult);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search Patient Consultations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 426, 228, 86);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_PatientConsult = new JLabel("ID Patient");
		lblNewLabel_PatientConsult.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_PatientConsult.setBounds(10, 30, 48, 13);
		panel_1.add(lblNewLabel_PatientConsult);

		textField_idPatientConsult = new JTextField();
		textField_idPatientConsult.setBounds(83, 27, 135, 19);
		panel_1.add(textField_idPatientConsult);
		textField_idPatientConsult.setColumns(10);

		JButton btnSearch_Consult_byName = new JButton("Search");
		btnSearch_Consult_byName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Consultation> consultations = gestionnaireConsult.listConsultationPatient(textField_idPatientConsult.getText());
				if (consultations.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "No consultations for this patient.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
				}else {
					String[] columnNames = {"IdConsult", "PatientID", "MedecinID", "DetailsCliniques", "Date"};
					DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
					for (Consultation consultation : consultations) {
						Object[] rowData = {
								consultation.getIdConsult(),
								consultation.getPatient(), 
								consultation.getMedecin(), 
								consultation.getDetailsCliniques(),
								consultation.getDate()
						};
						tableModel.addRow(rowData);
					}
					tableResultsConsult.setModel(tableModel); // Set the table model for tableResults
				}
			}
		});
		btnSearch_Consult_byName.setBounds(83, 56, 76, 19);
		panel_1.add(btnSearch_Consult_byName);

		JButton btnNewConsultation = new JButton("New Consultation");
		btnNewConsultation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertConsultationAdmin.main(null);
			}
		});
		btnNewConsultation.setBounds(304, 584, 177, 33);
		frame.getContentPane().add(btnNewConsultation);

		JButton btnDeleteConsult = new JButton("Delete Consultation");
		btnDeleteConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteConsultation.main(null);
			}
		});
		btnDeleteConsult.setBounds(497, 584, 152, 33);
		frame.getContentPane().add(btnDeleteConsult);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search Consultations by Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 533, 271, 96);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Patient name");
		lblNewLabel_2.setBounds(10, 34, 89, 13);
		panel_3.add(lblNewLabel_2);

		textField_searchByName = new JTextField();
		textField_searchByName.setBounds(113, 29, 143, 19);
		panel_3.add(textField_searchByName);
		textField_searchByName.setColumns(10);

		JButton btnSearchName = new JButton("Search");
		btnSearchName.addActionListener(controller.new ShowResultsTableSearchByName());
		btnSearchName.setBounds(111, 59, 85, 21);
		panel_3.add(btnSearchName);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Update Patient Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(13, 211, 222, 79);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Id Patient");
		lblNewLabel_3.setBounds(12, 23, 58, 13);
		panel_4.add(lblNewLabel_3);

		textField_UpdatePat = new JTextField();
		textField_UpdatePat.setBounds(71, 20, 141, 19);
		panel_4.add(textField_UpdatePat);
		textField_UpdatePat.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(getIdPatientUpdate());
					boolean result = controller.checkId(getIdPatientUpdate());
					if(result == true) {
						ViewUpdatePatient.main(getIdPatientUpdate());
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "ID not found.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(NullPointerException e1) {
					JOptionPane.showMessageDialog(getContentPane(), "Please insert an ID.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnUpdate.setBounds(74, 48, 85, 21);
		panel_4.add(btnUpdate);
		
		JButton btnNewButton = new JButton("View All Patient");
		btnNewButton.addActionListener(controller.new ShowAllPatients());
		btnNewButton.setBounds(248, 243, 140, 33);
		frame.getContentPane().add(btnNewButton);

	}

	public String getPatientNameConsult() {
		return textField_searchByName.getText();
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	public JTable getTableResultConsult() {
		return this.tableResultsConsult;
	}
	
	public JTable getTableResultPatient() {
		return this.tableResultsPatient;
	}
	
	public String getIdPatientUpdate() {
		return textField_UpdatePat.getText();
	}
}
