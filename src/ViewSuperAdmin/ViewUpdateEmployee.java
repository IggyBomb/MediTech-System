package ViewSuperAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllerViewSuperAdmin.ControllerViewUpdateEmployee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ViewUpdateEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_LastName;
	private JTextField textField_FirstName;
	private JTextField textField_Adresse;
	private JTextField textField_wage;
	private JTextField textField_user;
	private JTextField textField_password;
	private String idEmployee;
	private ControllerViewUpdateEmployee controller;
	private JTextField textField_profession;

	/**
	 * Launch the application.
	 */
	public static void main(String idEmployee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUpdateEmployee frame = new ViewUpdateEmployee(idEmployee);
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
	public ViewUpdateEmployee(String idEmployee) {
		controller = new ControllerViewUpdateEmployee(this);
		this.idEmployee = idEmployee;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 604, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Update Employee Informations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(35, 10, 254, 24);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(35, 69, 45, 13);
		contentPane.add(lblNewLabel_1);

		textField_id = new JTextField();
		textField_id.setBounds(110, 66, 179, 19);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		textField_id.setEditable(false);

		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(35, 106, 75, 13);
		contentPane.add(lblNewLabel_2);

		textField_LastName = new JTextField();
		textField_LastName.setBounds(110, 103, 179, 19);
		contentPane.add(textField_LastName);
		textField_LastName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("First Name");
		lblNewLabel_3.setBounds(35, 144, 63, 13);
		contentPane.add(lblNewLabel_3);

		textField_FirstName = new JTextField();
		textField_FirstName.setBounds(110, 138, 179, 19);
		contentPane.add(textField_FirstName);
		textField_FirstName.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(35, 181, 45, 13);
		contentPane.add(lblNewLabel_4);

		textField_Adresse = new JTextField();
		textField_Adresse.setBounds(110, 178, 179, 19);
		contentPane.add(textField_Adresse);
		textField_Adresse.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Wage");
		lblNewLabel_5.setBounds(35, 220, 45, 13);
		contentPane.add(lblNewLabel_5);


		textField_wage = new JTextField();
		textField_wage.setBounds(110, 217, 179, 19);
		contentPane.add(textField_wage);
		textField_wage.setColumns(10);
		textField_wage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = textField_wage.getText();
				try {
					double wage = Double.parseDouble(text);
				} catch (NumberFormatException nfe) {
					// Your exception handling here. For instance:
					JOptionPane.showMessageDialog(null, "Inserted text is not a number!");
					textField_wage.requestFocus();
				}
			}
		});

		JLabel lblNewLabel_6 = new JLabel("Login");
		lblNewLabel_6.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblNewLabel_6.setBounds(35, 255, 91, 24);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("User");
		lblNewLabel_7.setBounds(35, 292, 45, 13);
		contentPane.add(lblNewLabel_7);


		JLabel lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setBounds(35, 329, 45, 13);
		contentPane.add(lblNewLabel_8);

		textField_user = new JTextField();
		textField_user.setBounds(110, 289, 179, 19);
		contentPane.add(textField_user);
		textField_user.setColumns(10);
		FocusAdapter idGenerator = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String firstName = textField_FirstName.getText();
				String lastName = textField_LastName.getText();

				// Ensure both fields have enough characters
				if (firstName.length() >= 3 && lastName.length() >= 3) {
					String id = lastName.substring(0, 4) + firstName.substring(0, 3);
					textField_user.setText(id.toLowerCase());
				}
			}
		};
		

		textField_password = new JTextField();
		textField_password.setBounds(110, 326, 179, 19);
		contentPane.add(textField_password);
		textField_password.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(controller.new updateEmployee());
		btnUpdate.setBounds(329, 325, 85, 21);
		contentPane.add(btnUpdate);

		JLabel lblNewLabel_9 = new JLabel("Profession");
		lblNewLabel_9.setBounds(329, 69, 60, 13);
		contentPane.add(lblNewLabel_9);

		JPanel panel = new JPanel();
		panel.setBounds(10, 55, 570, 194);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField_profession = new JTextField();
		textField_profession.setBounds(377, 10, 164, 19);
		panel.add(textField_profession);
		textField_profession.setColumns(10);
		controller.FillData();
		textField_profession.setEditable(false);
	}

	public String getIdEmployee() {
		return this.idEmployee;
	}
	
	public void setIdEmployee(String id) {
		textField_id.setText(id);
	}


	// Getter and Setter for textField_lastName
	public String getLastName() {
		return this.textField_LastName.getText();
	}

	public void setLastName(String lastName) {
		this.textField_LastName.setText(lastName);
	}

	// Getter and Setter for textField_FirstName
	public String getFirstName() {
		return this.textField_FirstName.getText();
	}

	public void setFirstName(String firstName) {
		this.textField_FirstName.setText(firstName);
	}

	// Getter and Setter for textField_Adresse
	public String getAdresse() {
		return this.textField_Adresse.getText();
	}

	public void setAdresse(String adresse) {
		this.textField_Adresse.setText(adresse);
	}

	// Getter and Setter for textField_wage
	public Double getWage() {
		String wageText = textField_wage.getText();
		try {
			return Double.parseDouble(wageText);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Wage must be a number", nfe);
		}
	}

	public void setWage(String wage) {
		this.textField_wage.setText(wage);
	}

	// Getter and Setter for textField_user
	public String getUser() {
		return this.textField_user.getText();
	}

	public void setUser(String user) {
		this.textField_user.setText(user);
	}

	// Getter and Setter for textField_password
	public String getPassword() {
		return this.textField_password.getText();
	}

	public void setPassword(String password) {
		this.textField_password.setText(password);
	}
	public String getProfession() {
		return textField_profession.getText();
	}

	
	public void setProfession(String p) {
		textField_profession.setText(p);
	}
}
