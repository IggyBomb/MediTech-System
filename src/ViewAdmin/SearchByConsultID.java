package ViewAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Acteurs.Patient;
import Models.Consultation;
import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchByConsultID extends JFrame {
	

	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	private GestionnaireAdministratif Admin =  new GestionnaireAdministratif("root", "T1t4n1c0");
	private JPanel contentPane;
	private JTextField textField_Id;
	private JTextField textField_details;
	private JTextField textField_PhysicianName;
	private JTextField textField_date;
	private JTextField textField_Patient_Name;
	private Patient patient;

	/**
	 * Launch the application.
	 */
	public static void main(String idConsult) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchByConsultID frame = new SearchByConsultID(idConsult);
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
	public SearchByConsultID(String idConsult) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Consultation consultation = gestionnaire.getConsultationByID(idConsult);
		if(consultation == null) {
			JOptionPane.showMessageDialog(contentPane, "ID not found.", "Search results", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Consultation");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setBounds(169, 10, 159, 18);
		contentPane.add(lblTitle);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ID.setBounds(98, 41, 45, 13);
		contentPane.add(lbl_ID);
		
		textField_Id = new JTextField();
		textField_Id.setBounds(158, 38, 117, 19);
		contentPane.add(textField_Id);
		textField_Id.setColumns(10);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDetails.setBounds(98, 64, 45, 13);
		contentPane.add(lblDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(158, 67, 117, 58);
		contentPane.add(scrollPane);
		
		textField_details = new JTextField();
		scrollPane.setViewportView(textField_details);
		textField_details.setColumns(10);
		
		JLabel lblPhysician = new JLabel("Physician");
		lblPhysician.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhysician.setBounds(98, 148, 45, 13);
		contentPane.add(lblPhysician);
		
		textField_PhysicianName = new JTextField();
		textField_PhysicianName.setBounds(158, 145, 117, 19);
		contentPane.add(textField_PhysicianName);
		textField_PhysicianName.setColumns(10);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPatient.setBounds(98, 183, 45, 13);
		contentPane.add(lblPatient);
		
		JLabel lbDate = new JLabel("Date");
		lbDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDate.setBounds(98, 252, 45, 13);
		contentPane.add(lbDate);
		
		textField_date = new JTextField();
		textField_date.setBounds(158, 249, 117, 19);
		contentPane.add(textField_date);
		textField_date.setColumns(10);
		
		//show the details of the visit
		textField_Id.setText(consultation.getIdConsult());
		textField_details.setText(consultation.getDetailsCliniques());
		textField_PhysicianName.setText(consultation.getMedecin());
		textField_date.setText(consultation.getDate().toString());
		
		patient = Admin.findPatientByID(consultation.getPatient());
		String nomPatient = patient.getNom() + " " + patient.getPrenom();
		textField_Patient_Name = new JTextField();
		textField_Patient_Name.setBounds(158, 183, 117, 19);
		contentPane.add(textField_Patient_Name);
		textField_Patient_Name.setColumns(10);
		textField_Patient_Name.setText(nomPatient);
		
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(129, 311, 85, 21);
		contentPane.add(btnClose);
		
		JButton btnFindPatientDetails = new JButton("getDetails");
		btnFindPatientDetails.setBounds(285, 182, 117, 21);
		contentPane.add(btnFindPatientDetails);
		
		

		
	}

}
