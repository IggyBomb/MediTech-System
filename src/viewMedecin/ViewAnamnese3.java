package viewMedecin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.GestionnaireAdministratif;
import Models.GestionnaireConsultation;
import controllerViewMedecin.ControllerViewAnamnese;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ViewAnamnese extends JFrame {

	private JPanel contentPane;
	String dossierMedicale;
	private ControllerViewAnamnese controller;
	

	/**
	 * Launch the application.
	 */
	public static void main(String idPatient) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAnamnese frame = new ViewAnamnese(idPatient);
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
	public ViewAnamnese(String idPatient) {
		controller = new ControllerViewAnamnese(this);
		controller.retrivePatientInfo();
		this.dossierMedicale = idPatient;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public String getDossierMedicale() {
		return dossierMedicale;
	}

	public void setDossierMedicale(String dossierMedicale) {
		this.dossierMedicale = dossierMedicale;
	}
	
	
}
