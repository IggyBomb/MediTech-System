package viewMedecin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Acteurs.Medecin;
import Acteurs.Patient;
import Models.Consultation;
import Models.GestionnaireConsultation;
import Models.SingleConnection;
import controllerViewMedecin.ControllerNewConsultation;

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
import java.sql.SQLIntegrityConstraintViolationException;
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

public class ViewNewConsultation extends JFrame {

	private JPanel contentPane;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private JComboBox comboBox_physician;
	private JLabel lblPatient;
	private JComboBox comboBox_Patient;
	private JTextField textField_idConsultation;
	private JTextField textField_details;
	private String selectedPatientId;
	private String selectedDoctorId;
	private JSpinField hours;
	private JSpinField minutes;
	private JDateChooser dateChooser;
	private ControllerNewConsultation controller;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ViewNewConsultation frame = new ViewNewConsultation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void fillComboPhysician() throws SQLException {
		try {
			controller.fillComboPhysician(comboBox_physician);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void fillComboPatient() throws SQLException {
		try {
			controller.fillComboPatient(comboBox_Patient);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public ViewNewConsultation() {
		controller = new ControllerNewConsultation(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

		dateChooser = new JDateChooser();
		dateChooser.setBounds(191, 141, 101, 19);
		contentPane.add(dateChooser);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(112, 141, 45, 13);
		contentPane.add(lblDate);

		hours = new JSpinField();
		hours.setBounds(191, 190, 102, 19);
		contentPane.add(hours);

		minutes = new JSpinField();
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
		btnCreateNew.addActionListener(controller.new createNewConsultation());
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
	
	public String getMedecin() {
		return selectedDoctorId;
	}
	
	public String getPatient() {
		return selectedPatientId;
	}
	
	public int getHeure() {
		return  hours.getValue();
	}
	
	public int getMin() {
		return  minutes.getValue();
	}
	
	public LocalDateTime getTimeAndDate() {
		LocalDateTime ConsultTime;
		Date selectedDate = new Date(dateChooser.getDate().getTime());
		Instant instant = Instant.ofEpochMilli(selectedDate.getTime());
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		int selectedHours = getHeure();
		int selectedMinutes = getMin();
		LocalTime localTime = LocalTime.of(selectedHours, selectedMinutes);

		// Combine the date and time to create a LocalDateTime object
		return ConsultTime = LocalDateTime.of(localDate, localTime);
	}
	
	public String getDetails() {
		return textField_details.getText();
	}
	
	public String getIDConsult() {
		return textField_idConsultation.getText();
	}
}
