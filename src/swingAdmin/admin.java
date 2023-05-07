package swingAdmin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import Acteurs.Patient;
import formulaires.GestionnaireAdministratif;
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

public class admin {

	private GestionnaireAdministratif gestionnaire =  new GestionnaireAdministratif("root", "T1t4n1c0");
	private JFrame frame;
	private JTextField IDsearchTextField;
	private JTextField textFieldName;
	private JTable tableResults;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin window = new admin();
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
	public admin() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		lblNewLabel_1.setBounds(10, 21, 31, 13);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel_1);

		IDsearchTextField = new JTextField();
		IDsearchTextField.setBounds(44, 18, 174, 19);
		panel.add(IDsearchTextField);
		IDsearchTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = gestionnaire.findByID(IDsearchTextField.getText());
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
					tableResults.setModel(tableModel); // Set the table model for tableResults
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

		tableResults = new JTable();
		scrollPane.setViewportView(tableResults);

		JButton btnSearchByName = new JButton("Search");
		btnSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Patient> patients = gestionnaire.searchPatientsByName(textFieldName.getText());
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
					tableResults.setModel(tableModel); // Set the table model for tableResults

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
				InsertPatient.main(null);

			}
		});
		btnInsert.setBounds(65, 285, 120, 33);
		frame.getContentPane().add(btnInsert);

		JButton btnDeletePatient = new JButton("Delete patient");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePatient.main(null);
			}
		});
		btnDeletePatient.setBounds(288, 285, 122, 33);
		frame.getContentPane().add(btnDeletePatient);

		JButton btnUpdatePatient = new JButton("Update patient");
		btnUpdatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePatient.main(null);
			}
		});
		btnUpdatePatient.setBounds(513, 285, 130, 33);
		frame.getContentPane().add(btnUpdatePatient);

		JButton btnRefreshTable = new JButton("Refresh");
		btnRefreshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				tableResults.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnRefreshTable.setBounds(564, 209, 85, 21);
		frame.getContentPane().add(btnRefreshTable);


	}
}
