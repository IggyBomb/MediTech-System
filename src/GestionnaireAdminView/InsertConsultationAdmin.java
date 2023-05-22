package GestionnaireAdminView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Acteurs.Medecin;
import Acteurs.Patient;
import Consultation.Consultation;
import formulaires.GestionnaireConsultation;
import formulaires.SingleConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertConsultationAdmin extends JFrame {

	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	private JPanel contentPane;
	private PreparedStatement pstmt;
	private Connection connection = SingleConnection.getInstance("jdbc:mysql://127.0.0.1:3306/nfa019project", "root", "T1t4n1c0");
	private ResultSet rs;
	private JComboBox comboBox_physician;
	private JLabel lblPatient;
	private JComboBox comboBox_Patient;
	private JTextField textField_idConsultation;
	private JTextField textField_details;
	private String selectedPatientId;
	private String selectedDoctorId;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertConsultationAdmin frame = new InsertConsultationAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void fillComboPhysician() throws SQLException {
		try {
			String sql = "SELECT * FROM medecin";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("Id");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				double salaire = rs.getDouble("Salaire");
				Medecin medecin = new Medecin(id, nom, prenom, adresse, salaire);
				comboBox_physician.addItem(medecin);
			}
		} catch (SQLException e) {
			Logger.getLogger(InsertConsultationAdmin.class.getName());
		}/* finally {
	        pstmt.close();
	        rs.close();
	    }
		 */
	}

	@SuppressWarnings("unchecked")
	public void fillComboPatient() throws SQLException {
		try {
			String sql = "SELECT * FROM nfa019project.patient";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String idPatient = rs.getString("IdPatient");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String adresse = rs.getString("Adresse");
				LocalDateTime dateNaissance = rs.getTimestamp("DateNaissance").toLocalDateTime();
				Patient patient = new Patient(nom, prenom, adresse, dateNaissance, idPatient);
				comboBox_Patient.addItem(patient);
				System.out.println(nom +" "+prenom);
			}
		} catch (SQLException e) {
			Logger.getLogger(InsertConsultationAdmin.class.getName());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public InsertConsultationAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("New Visit");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(155, 10, 120, 33);
		contentPane.add(lblTitle);

		JLabel lblPhysician = new JLabel("Physician");
		lblPhysician.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhysician.setBounds(92, 59, 76, 13);
		contentPane.add(lblPhysician);

		comboBox_physician = new JComboBox();
		comboBox_physician.setBounds(191, 55, 101, 21);
		contentPane.add(comboBox_physician);

		lblPatient = new JLabel("Patient");
		lblPatient.setBounds(107, 101, 45, 13);
		contentPane.add(lblPatient);
		comboBox_physician.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Medecin selectedDoctor = (Medecin) comboBox_physician.getSelectedItem();
					selectedDoctorId = selectedDoctor.getId();
				}
			}
		});
		try {
			fillComboPhysician();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		comboBox_Patient = new JComboBox();
		comboBox_Patient.setBounds(191, 97, 101, 21);
		contentPane.add(comboBox_Patient);
		comboBox_Patient.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Patient selectedPatient = (Patient) comboBox_Patient.getSelectedItem();
					selectedPatientId = selectedPatient.getIdPatient();
				}
			}
		});
		try {
			fillComboPatient();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(191, 141, 101, 19);
		contentPane.add(dateChooser);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(112, 141, 45, 13);
		contentPane.add(lblDate);

		JSpinField hours = new JSpinField();
		hours.setBounds(191, 190, 102, 19);
		contentPane.add(hours);

		JSpinField minutes = new JSpinField();
		minutes.setBounds(192, 238, 102, 19);
		contentPane.add(minutes);

		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(107, 196, 45, 13);
		contentPane.add(lblHours);

		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setBounds(107, 244, 45, 13);
		contentPane.add(lblMinutes);

		JLabel lblID_consult = new JLabel("ID Consultation");
		lblID_consult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblID_consult.setBounds(44, 357, 112, 13);
		contentPane.add(lblID_consult);

		textField_idConsultation = new JTextField();
		textField_idConsultation.setBounds(191, 354, 101, 19);
		contentPane.add(textField_idConsultation);
		textField_idConsultation.setColumns(10);

		JLabel lblDetails = new JLabel("Details");
		lblDetails.setBounds(99, 288, 45, 13);
		contentPane.add(lblDetails);

		textField_details = new JTextField();
		textField_details.setBounds(192, 286, 103, 45);
		contentPane.add(textField_details);
		textField_details.setColumns(10);

		JButton btnCreateNew = new JButton("Create");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get selected date
				Date selectedDate = new Date(dateChooser.getDate().getTime());
				Instant instant = Instant.ofEpochMilli(selectedDate.getTime());
				LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

				// Convert java.util.Date to java.sql.Date
				java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

				int selectedHours = hours.getValue();
				int selectedMinutes = minutes.getValue();
				LocalTime localTime = LocalTime.of(selectedHours, selectedMinutes);

				// Combine the date and time to create a LocalDateTime object
				LocalDateTime ConsultTime = LocalDateTime.of(localDate, localTime);
				String details = textField_details.getText();
				Boolean insert = gestionnaire.addConsultation(new Consultation(textField_idConsultation.getText(), selectedPatientId, selectedDoctorId, details, ConsultTime));
				if(insert) {
					JOptionPane.showMessageDialog(contentPane, "Visit confirmed", "Create New Consultation", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(contentPane, "No!", "Create New Consultation", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			});
		btnCreateNew.setBounds(107, 430, 85, 21);
		contentPane.add(btnCreateNew);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_idConsultation.setText("");
				textField_details.setText("");
				
			}
		});
		btnClear.setBounds(309, 430, 85, 21);
		contentPane.add(btnClear);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(207, 430, 85, 21);
		contentPane.add(btnClose);
		}
	}
