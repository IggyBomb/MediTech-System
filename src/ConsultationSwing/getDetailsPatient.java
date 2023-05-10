package ConsultationSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import formulaires.GestionnaireAdministratif;
import formulaires.GestionnaireConsultation;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class getDetailsPatient extends JFrame {

	private JPanel contentPane;
	private GestionnaireAdministratif Admin =  new GestionnaireAdministratif("root", "T1t4n1c0");
	private GestionnaireConsultation gestionnaire = new GestionnaireConsultation("root", "T1t4n1c0");
	String dossierMedicale;
	

	/**
	 * Launch the application.
	 */
	public static void main(String idPatient) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getDetailsPatient frame = new getDetailsPatient(idPatient);
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
	public getDetailsPatient(String idPatient) {
		dossierMedicale = gestionnaire.getDetailsPatient(idPatient);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblDetails = new JLabel("Dossier Medical");
		lblDetails.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblDetails.setBounds(131, 10, 163, 13);
		contentPane.add(lblDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 416, 189);
		contentPane.add(scrollPane);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setText(dossierMedicale);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(151, 236, 85, 21);
		contentPane.add(btnClose);
		
	}
}
