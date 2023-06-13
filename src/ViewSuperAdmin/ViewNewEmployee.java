package ViewSuperAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllerViewSuperAdmin.ControllerViewNewEmployee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewNewEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textField_LastName;
	private JTextField textField_FirstName;
	private JTextField textField_address;
	private JTextField textField_wage;
	private JTextField textField__Id;
	private JComboBox comboBox;
	private JTextField textField_password;
	private ControllerViewNewEmployee controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewNewEmployee frame = new ViewNewEmployee();
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
	public ViewNewEmployee() {
		controller = new ControllerViewNewEmployee(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("New Employee");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setBounds(23, 10, 130, 19);
		contentPane.add(lblTitle);
		
		JLabel lblName = new JLabel("Last Name");
		lblName.setBounds(23, 54, 81, 13);
		contentPane.add(lblName);
		
		textField_LastName = new JTextField();
		textField_LastName.setBounds(114, 51, 193, 19);
		contentPane.add(textField_LastName);
		textField_LastName.setColumns(10);
		
		JLabel lblPrenom = new JLabel("First Name");
		lblPrenom.setBounds(23, 83, 65, 13);
		contentPane.add(lblPrenom);
		
		textField_FirstName = new JTextField();
		textField_FirstName.setBounds(114, 80, 193, 19);
		contentPane.add(textField_FirstName);
		textField_FirstName.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Address");
		lblAdresse.setBounds(23, 112, 65, 13);
		contentPane.add(lblAdresse);
		
		textField_address = new JTextField();
		textField_address.setBounds(114, 109, 193, 19);
		contentPane.add(textField_address);
		textField_address.setColumns(10);
		
		JLabel Wage = new JLabel("Wage");
		Wage.setBounds(23, 146, 45, 13);
		contentPane.add(Wage);
		
		textField_wage = new JTextField();
		textField_wage.setBounds(114, 143, 193, 19);
		contentPane.add(textField_wage);
		textField_wage.setColumns(10);
		textField_wage.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String text = textField_wage.getText();
		        try {
		            double wage = Double.parseDouble(text);
		        } catch (NumberFormatException nfe) {
		            JOptionPane.showMessageDialog(null, "Inserted text is not a number!");
		            textField_wage.requestFocus();
		        }
		    }
		});
		
		JLabel lblProfession = new JLabel("Profession");
		lblProfession.setBounds(24, 178, 75, 13);
		contentPane.add(lblProfession);
		
		comboBox = new JComboBox();
		comboBox.setBounds(152, 174, 153, 21);
		contentPane.add(comboBox);
		comboBox.addItem("medecin");
		comboBox.addItem("technicien");
		comboBox.addItem("admin");
		comboBox.addItem("superAdmin");
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(controller.new AddNewEmployee());
		btnCreate.setBounds(85, 336, 85, 21);
		contentPane.add(btnCreate);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(23, 255, 45, 13);
		contentPane.add(lblID);
		
		textField__Id = new JTextField();
		textField__Id.setBounds(114, 252, 193, 19);
		contentPane.add(textField__Id);
		textField__Id.setColumns(10);
		
		JButton btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_LastName.setText("");
				textField_FirstName.setText("");
				textField__Id.setText("");
				textField_address.setText("");
				textField_wage.setText(null);
			}
		});
		btnClear.setBounds(202, 336, 85, 21);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(24, 285, 81, 13);
		contentPane.add(lblNewLabel);
		
		textField_password = new JTextField();
		textField_password.setBounds(115, 282, 193, 19);
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 210, 115, 28);
		contentPane.add(lblNewLabel_1);
		FocusAdapter idGenerator = new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String firstName = textField_FirstName.getText();
		        String lastName = textField_LastName.getText();
		        // Ensure both fields have enough characters
		        if (firstName.length() >= 3 && lastName.length() >= 3) {
		            String id = lastName.substring(0, 4) + firstName.substring(0, 3);
		            textField__Id.setText(id.toLowerCase());
		        }
		    }
		};
		textField_FirstName.addFocusListener(idGenerator);
		textField_LastName.addFocusListener(idGenerator);
	}
	
	public String getLastName() {
		return textField_LastName.getText();
	}
	
	public String getFirstName() {
		return textField_FirstName.getText();
	}
	
	public String getId() {
		return textField__Id.getText();
	}
	
	public String getAddress() {
		return textField_address.getText();
	}
	
	public double getWage() {
	    String wageText = textField_wage.getText();
	    try {
	        return Double.parseDouble(wageText);
	    } catch (NumberFormatException nfe) {
	        throw new IllegalArgumentException("Wage must be a number", nfe);
	    }
	}
	
	public String getProfession() {
	    Object selectedItem = comboBox.getSelectedItem();
	    if (selectedItem != null) {
	        return selectedItem.toString();
	    } else {
	        return null;
	    }
	}
	
	public String getPassword() {
		return textField_password.getText();
	}
}

