package ViewGestionnaireAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.GestionnaireConsultation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteConsultation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private GestionnaireConsultation gestionnaireConsult = new GestionnaireConsultation("root", "T1t4n1c0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteConsultation frame = new DeleteConsultation();
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
	public DeleteConsultation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteConsult = new JLabel("Delete Consultation");
		lblDeleteConsult.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteConsult.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblDeleteConsult.setBounds(119, 10, 208, 27);
		contentPane.add(lblDeleteConsult);
		
		JLabel lblID = new JLabel("ID Consultation");
		lblID.setBounds(72, 68, 100, 13);
		contentPane.add(lblID);
		
		textField = new JTextField();
		textField.setBounds(159, 65, 156, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get the input values
		        String id = textField.getText();

		        // Delete the consultation
		        boolean deletionSuccessful = gestionnaireConsult.deleteConsultation(id);
		        // Show a message to the user depending on the result of the deletion
		        if (deletionSuccessful) {
		            JOptionPane.showMessageDialog(contentPane, "Consultation eliminated with success!", "Consultation Deletion", JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "An error occurred while removing the Consultation.", "Consultation Deletion", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnDelete.setBounds(111, 115, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(230, 115, 85, 21);
		contentPane.add(btnClose);
	}
}
