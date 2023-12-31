package viewTechnicien;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllerViewTechnicien.ControllerHomePageTechnician;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewHomePageTechnicien extends JFrame {

	private JPanel contentPane;
	private JTextField textField_idConsult;
	private JTextField textField_idPatient;
	private JTable tableResults;
	ControllerHomePageTechnician controller;
	private JTextField textField_patientName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHomePageTechnicien frame = new ViewHomePageTechnicien();
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
	public ViewHomePageTechnicien() {
		controller = new ControllerHomePageTechnician(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 916, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Gestionnaire Consultation Technicien");
		lblTitle.setBounds(267, 25, 365, 13);
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 81, 271, 85);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Technical View", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIDConsultation = new JLabel("ID Consultation");
		lblIDConsultation.setBounds(10, 27, 99, 13);
		panel.add(lblIDConsultation);
		
		textField_idConsult = new JTextField();
		textField_idConsult.setBounds(114, 24, 147, 19);
		panel.add(textField_idConsult);
		textField_idConsult.setColumns(10);
		
		JButton btnSearchConsultationID_clinical = new JButton("Search");
		btnSearchConsultationID_clinical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean result = controller.searchOrdonnanceById(getIdConsultTech());
				if(result) {
				ViewConsultationTech.main(ViewHomePageTechnicien.this, getIdConsultTech());
				}
			}
		});
		btnSearchConsultationID_clinical.setBounds(114, 53, 85, 21);
		panel.add(btnSearchConsultationID_clinical);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(320, 87, 572, 264);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 176, 271, 85);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search Consultations", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblIDConsultation_1 = new JLabel("ID patient");
		lblIDConsultation_1.setBounds(10, 26, 99, 13);
		panel_1.add(lblIDConsultation_1);
		
		textField_idPatient = new JTextField();
		textField_idPatient.setColumns(10);
		textField_idPatient.setBounds(114, 23, 147, 19);
		panel_1.add(textField_idPatient);
		
		tableResults = new JTable();
		scrollPane.setViewportView(tableResults);
		
		JButton btnSearchConsultationID_admin = new JButton("Search");
		btnSearchConsultationID_admin.addActionListener(controller.new TableResultsSearch());
		btnSearchConsultationID_admin.setBounds(114, 52, 85, 21);
		panel_1.add(btnSearchConsultationID_admin);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search Consultation By Patient Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(39, 278, 271, 98);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient Name");
		lblNewLabel.setBounds(10, 39, 77, 13);
		panel_2.add(lblNewLabel);
		
		textField_patientName = new JTextField();
		textField_patientName.setBounds(115, 36, 146, 19);
		panel_2.add(textField_patientName);
		textField_patientName.setColumns(10);
		
		JButton btnSearchName = new JButton("Search");
		btnSearchName.addActionListener(controller.new ShowResultsTableSearchByName());
		btnSearchName.setBounds(115, 67, 85, 21);
		panel_2.add(btnSearchName);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				tableResults.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnRefresh.setBounds(807, 367, 85, 21);
		contentPane.add(btnRefresh);
	}
	
	//getters and setters
	public String getIdPatient() {
		return textField_idPatient.getText();
	}
	
	public String getIdConsultTech() {
		return textField_idConsult.getText();
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
		return textField_patientName.getText();
	}
}
