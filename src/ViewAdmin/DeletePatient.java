package ViewAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.GestionnaireAdministratif;
import constrollersViewAdmin.ControllerViewDeletePatient;

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
	private ControllerViewDeletePatient controller;

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
		controller = new ControllerViewDeletePatient(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		btnDelete.addActionListener(controller.new deletePatient());
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
	
	public String getId() {
		return textField_ID.getText();
	}
}
