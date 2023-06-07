package Main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ViewGestionnaireAdmin.ViewHomePageAdmin;
import ViewSuperAdmin.ViewSuperAdmin;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JButton btnSuperAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                HomePage frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHomeP = new JLabel("HomePage");
		lblHomeP.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeP.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblHomeP.setBounds(149, 30, 113, 35);
		contentPane.add(lblHomeP);
		
		JButton rdbtnAdmin = new JButton("Admin");
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewLogin login = new ViewLogin(HomePage.this, "admin");
                login.setVisible(true); 	
			}
		});
		rdbtnAdmin.setBounds(159, 85, 103, 21);
		contentPane.add(rdbtnAdmin);
		
		JButton rdbtnConsult = new JButton("Medecin");
		rdbtnConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewLogin login = new ViewLogin(HomePage.this, "medecin");
                login.setVisible(true); 	
			}
		});
		rdbtnConsult.setBounds(159, 127, 103, 21);
		contentPane.add(rdbtnConsult);
		
		JButton rdbtntechnicien = new JButton("Technicien");
		rdbtntechnicien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewLogin login = new ViewLogin(HomePage.this, "technicien");
                login.setVisible(true); 	
			}
		});
		rdbtntechnicien.setBounds(159, 168, 103, 21);
		contentPane.add(rdbtntechnicien);
		
		btnSuperAdmin = new JButton("Super Admin");
		btnSuperAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSuperAdmin.main(null);
			}
		});
		btnSuperAdmin.setBounds(159, 211, 103, 21);
		contentPane.add(btnSuperAdmin);
	}
}