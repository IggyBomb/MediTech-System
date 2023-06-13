package ViewAdmin;

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
import constrollersViewAdmin.ControllerViewUpdatePatient;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewUpdatePatient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_LastName;
	private JTextField textField_FirstName;
	private JTextField textField_Address;
	private JDateChooser dateChooser_BirthDay;
	private ControllerViewUpdatePatient controller;

	/**
	 * Launch the application.
	 */
	public static void main(String idPatient) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUpdatePatient frame = new ViewUpdatePatient(idPatient);
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
	public ViewUpdatePatient(String idPatient) throws SQLException {
		controller = new ControllerViewUpdatePatient(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		textField_ID.setEditable(false);

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

		dateChooser_BirthDay = new JDateChooser();
		dateChooser_BirthDay.setBounds(174, 233, 156, 19);
		contentPane.add(dateChooser_BirthDay);

		JLabel lbl_BirthDate = new JLabel("Date of birth");
		lbl_BirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_BirthDate.setBounds(93, 239, 70, 13);
		contentPane.add(lbl_BirthDate);
		
		//initialize all the patient data
		controller.FillData(idPatient);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(controller.new updateEmployee());
		btnUpdate.setBounds(198, 292, 85, 21);
		contentPane.add(btnUpdate);

	}

	public void setLastName(String lastN) {
		textField_LastName.setText(lastN);
	}

	public String getLastName() {
		return textField_LastName.getText();
	}

	public void setFirstName(String firstN) {
		textField_FirstName.setText(firstN);
	}

	public String getFirstName() {
		return textField_FirstName.getText();
	}

	public void SetAddress(String address) {
		textField_Address.setText(address);
	}

	public String getAddress() {
		return textField_Address.getText();
	}

	public void setBirthDay(LocalDateTime birthDay) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = birthDay.atZone(defaultZoneId).toInstant();
		Date date = Date.from(instant);
		dateChooser_BirthDay.setDate(date);
	}
	
	public LocalDateTime getBirthDay() {
		Date date = dateChooser_BirthDay.getDate();
		LocalDateTime birthDate = null;
		try {
			Instant instant = date.toInstant();
			birthDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).withHour(0).withMinute(0).withSecond(0).withNano(0);
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(contentPane, "An error occurred when updating the informations.", "Update Patient", JOptionPane.WARNING_MESSAGE);
		}
		return birthDate;
	}
	
	public String getID() {
		return textField_ID.getText();
	}
	
	public void setId(String id) {
		textField_ID.setText(id);
	}
}



