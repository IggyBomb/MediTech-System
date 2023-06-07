package ViewGestionnaireAdmin;

import java.awt.EventQueue;
import com.toedter.calendar.JDateChooser;
import Acteurs.Patient;
import Models.GestionnaireAdministratif;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

public class InsertPatient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_LastName;
	private JTextField textField_firstName;
	private JTextField textField_address;
	private GestionnaireAdministratif gestionnaire = new GestionnaireAdministratif("root", "T1t4n1c0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertPatient frame = new InsertPatient();
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
	public InsertPatient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Insert New Patient");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setBounds(140, 10, 189, 29);
		contentPane.add(lblTitle);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_ID.setBounds(144, 54, 45, 13);
		contentPane.add(lbl_ID);
		
		textField_id = new JTextField();
		textField_id.setBounds(199, 52, 96, 19);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		textField_LastName = new JTextField();
		textField_LastName.setColumns(10);
		textField_LastName.setBounds(199, 77, 96, 19);
		contentPane.add(textField_LastName);
		
		JLabel lbl_LastName = new JLabel("Last Name");
		lbl_LastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_LastName.setBounds(122, 79, 67, 13);
		contentPane.add(lbl_LastName);
		
		textField_firstName = new JTextField();
		textField_firstName.setColumns(10);
		textField_firstName.setBounds(199, 106, 96, 19);
		contentPane.add(textField_firstName);
		
		JLabel lbl_FirstName = new JLabel("First Name");
		lbl_FirstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_FirstName.setBounds(122, 108, 67, 13);
		contentPane.add(lbl_FirstName);
		
		textField_address = new JTextField();
		textField_address.setColumns(10);
		textField_address.setBounds(199, 135, 96, 19);
		contentPane.add(textField_address);
		
		JLabel lbl_adresse = new JLabel("Address");
		lbl_adresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_adresse.setBounds(122, 137, 67, 13);
		contentPane.add(lbl_adresse);
		
		JLabel lbl_birthDate = new JLabel("Date of birth");
		lbl_birthDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_birthDate.setBounds(102, 164, 87, 13);
		contentPane.add(lbl_birthDate);
		
		JDateChooser dateBirth = new JDateChooser();
		dateBirth.setBounds(199, 164, 96, 19);
		contentPane.add(dateBirth);
		
		JButton btnInsert = new JButton("insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the input values
				String id = textField_id.getText();
		        String nom = textField_LastName.getText();
		        String prenom = textField_firstName.getText();
		        Date date = dateBirth.getDate();
		        Instant instant = date.toInstant();
		        LocalDateTime birthDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).withHour(0).withMinute(0).withSecond(0).withNano(0);
		        String adresse = textField_address.getText();
		        // Create the new patient object
		        Patient patient = new Patient(nom, prenom, adresse, birthDate, id);
		        // Insert the patient into the database
		        boolean insertionSuccessful = gestionnaire.insertPatient(patient);
		        // Show a message to the user depending on the result of the insertion
		        if (insertionSuccessful) {
		        	JOptionPane.showMessageDialog(contentPane, "Patient added to the database.", "Insert Patient", JOptionPane.INFORMATION_MESSAGE);
		        	dispose();
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "An error occurred when adding the patient.", "Insert Patient", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnInsert.setBounds(104, 222, 85, 21);
		contentPane.add(btnInsert);
		
		JButton btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_id.setText("");
				textField_LastName.setText("");
				textField_firstName.setText("");
				textField_address.setText("");
			}
		});
		btnClear.setBounds(301, 222, 85, 21);
		contentPane.add(btnClear);
		
		JButton btnCloseWindow = new JButton("close");
		btnCloseWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCloseWindow.setBounds(199, 222, 85, 21);
		contentPane.add(btnCloseWindow);
		
	}
}
