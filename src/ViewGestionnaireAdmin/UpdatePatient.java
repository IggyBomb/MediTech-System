package ViewGestionnaireAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Acteurs.Patient;
import formulaires.GestionnaireAdministratif;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

public class UpdatePatient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_LastName;
	private JTextField textField_FirstName;
	private JTextField textField_Address;
	private GestionnaireAdministratif gestionnaire = new GestionnaireAdministratif("root", "T1t4n1c0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePatient frame = new UpdatePatient();
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
	public UpdatePatient() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ID.setBounds(107, 71, 45, 13);
		contentPane.add(lbl_ID);
		
		JLabel lblTitle = new JLabel("Update Patient Information");
		lblTitle.setBounds(124, 10, 223, 18);
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(174, 65, 156, 19);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
		
		JLabel lbl_LastName = new JLabel("Last Name");
		lbl_LastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_LastName.setBounds(73, 111, 91, 13);
		contentPane.add(lbl_LastName);
		
		textField_LastName = new JTextField();
		textField_LastName.setBounds(174, 105, 156, 19);
		contentPane.add(textField_LastName);
		textField_LastName.setColumns(10);
		
		JLabel lbl_FistName = new JLabel("First Name");
		lbl_FistName.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_FistName.setBounds(60, 154, 104, 13);
		contentPane.add(lbl_FistName);
		
		textField_FirstName = new JTextField();
		textField_FirstName.setBounds(174, 148, 156, 19);
		contentPane.add(textField_FirstName);
		textField_FirstName.setColumns(10);
		
		JLabel lbl_Address = new JLabel("Address");
		lbl_Address.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Address.setBounds(119, 196, 45, 13);
		contentPane.add(lbl_Address);
		
		textField_Address = new JTextField();
		textField_Address.setBounds(175, 190, 156, 19);
		contentPane.add(textField_Address);
		textField_Address.setColumns(10);
		
		JDateChooser dateChooser_BirthDay = new JDateChooser();
		dateChooser_BirthDay.setBounds(174, 233, 156, 19);
		contentPane.add(dateChooser_BirthDay);
		
		JLabel lbl_BirthDate = new JLabel("Date of birth");
		lbl_BirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_BirthDate.setBounds(93, 239, 70, 13);
		contentPane.add(lbl_BirthDate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the input values
		        String id = textField_ID.getText();
		        String nom = textField_LastName.getText();
		        String prenom = textField_FirstName.getText();
		        Date date = dateChooser_BirthDay.getDate();
		        LocalDateTime birthDate = null;
		        try {
		            Instant instant = date.toInstant();
		            birthDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).withHour(0).withMinute(0).withSecond(0).withNano(0);
		        } catch (NullPointerException npe) {
		            JOptionPane.showMessageDialog(contentPane, "An error occurred when updating the informations.", "Update Patient", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        String adresse = textField_Address.getText();
		        // Create the new patient object
		        Patient patient = new Patient(nom, prenom, adresse, birthDate, id);
		        try {
					boolean updateSuccessful = gestionnaire.updatePatient(patient);
			        if (updateSuccessful) {
			        	JOptionPane.showMessageDialog(contentPane, "Patient informations updated.", "Update Patient", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			            JOptionPane.showMessageDialog(contentPane, "An error occurred when updating the informations.", "Update Patient", JOptionPane.ERROR_MESSAGE);
			        }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnUpdate.setBounds(107, 293, 85, 21);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(214, 293, 85, 21);
		contentPane.add(btnClose);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_ID.setText("");
				textField_LastName.setText("");
				textField_FirstName.setText("");
				textField_Address.setText("");
			}
		});
		btnClear.setBounds(323, 293, 85, 21);
		contentPane.add(btnClear);
	}
}
