package viewMedecin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllerViewMedecin.ControllerHomePageMedecin;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewHomePageMedecin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_idConsultClinical;
	private JTextField textField_idConsultSearch;
	private JTable tableResults;
	ControllerHomePageMedecin controller;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHomePageMedecin frame = new ViewHomePageMedecin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewHomePageMedecin() {
		controller = new ControllerHomePageMedecin(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 916, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Gestionnaire Consultation Medecin");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(267, 25, 321, 13);
		contentPane.add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Clinical View", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(39, 81, 271, 85);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblIDConsultation = new JLabel("ID Consultation");
		lblIDConsultation.setBounds(10, 27, 99, 13);
		panel.add(lblIDConsultation);

		textField_idConsultClinical = new JTextField();
		textField_idConsultClinical.setBounds(114, 24, 147, 19);
		panel.add(textField_idConsultClinical);
		textField_idConsultClinical.setColumns(10);

		JButton btnSearchConsultationID_clinical = new JButton("Search");
		btnSearchConsultationID_clinical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = controller.SearchConsultationByID(textField_idConsultClinical.getText());
				if(result) {
					ViewConsultationMedecin.main(idConsultClinical());
				}else {
					JOptionPane.showMessageDialog(contentPane, "No consultations with this ID", "Search results", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSearchConsultationID_clinical.setBounds(114, 53, 85, 21);
		panel.add(btnSearchConsultationID_clinical);


		JButton btnNewConsult = new JButton("New Visit");
		btnNewConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewNewConsultation.main(null);
			}
		});
		btnNewConsult.setBounds(63, 451, 137, 34);
		contentPane.add(btnNewConsult);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(320, 87, 572, 264);
		contentPane.add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search Consultation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(39, 176, 271, 85);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIDConsultation_1 = new JLabel("ID Consultation");
		lblIDConsultation_1.setBounds(10, 26, 99, 13);
		panel_1.add(lblIDConsultation_1);

		textField_idConsultSearch = new JTextField();
		textField_idConsultSearch.setColumns(10);
		textField_idConsultSearch.setBounds(114, 23, 147, 19);
		panel_1.add(textField_idConsultSearch);

		tableResults = new JTable();
		scrollPane.setViewportView(tableResults);

		JButton btnSearchConsultationID_admin = new JButton("Search");
		btnSearchConsultationID_admin.addActionListener(controller.new ShowResultsTable());
		btnSearchConsultationID_admin.setBounds(114, 52, 85, 21);
		panel_1.add(btnSearchConsultationID_admin);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				tableResults.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnNewButton.setBounds(781, 363, 111, 21);
		contentPane.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search By Patient Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(39, 271, 271, 77);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(115, 20, 146, 19);
		panel_2.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btn_SearchByName = new JButton("Search");
		btn_SearchByName.addActionListener(controller.new ShowResultsTableSearchByName());
		btn_SearchByName.setBounds(115, 49, 85, 21);
		panel_2.add(btn_SearchByName);
		
		JLabel lblNewLabel = new JLabel("Name Patient");
		lblNewLabel.setBounds(10, 23, 79, 13);
		panel_2.add(lblNewLabel);
	}

	//getters and setters
	public String getidConsultSearch() {
		return textField_idConsultSearch.getText();
	}

	public String idConsultClinical() {
		return textField_idConsultClinical.getText();
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	public JTable getTableResult() {
		return this.tableResults;
	}

	public void setTableResult(JTable tableResult) {
		this.tableResults = tableResult;
	}
	
	public String getName() {
		return textFieldName.getText();
	}
}
