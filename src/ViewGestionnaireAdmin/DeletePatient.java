package ViewGestionnaireAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.GestionnaireAdministratif;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeletePatient extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private GestionnaireAdministratif gestionnaire = new GestionnaireAdministratif("root", "T1t4n1c0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePatient frame = new DeletePatient();
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
	public DeletePatient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Delete Patient");
		Title.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		Title.setBounds(89, 10, 169, 24);
		contentPane.add(Title);
		
		JLabel lblID = new JLabel("ID ");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblID.setBounds(58, 68, 45, 13);
		contentPane.add(lblID);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(86, 66, 152, 19);
		contentPane.add(textField_ID);
		textField_ID.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the input values
		        String id = textField_ID.getText();

		        // Delete the patient
		        boolean deletionSuccessful = gestionnaire.deletePatient(id);
		        // Show a message to the user depending on the result of the deletionb
		        if (deletionSuccessful) {
		            JOptionPane.showMessageDialog(contentPane, "Patient eliminated with success!", "Patient Deletion", JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "An error occurred while removing the patient.", "Patient Deletion", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnDelete.setBounds(58, 119, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(153, 119, 85, 21);
		contentPane.add(btnClose);
	}
}
