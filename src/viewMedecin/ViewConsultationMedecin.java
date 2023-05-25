package viewMedecin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Acteurs.Medecin;
import Acteurs.Patient;
import Models.Consultation;
import controllerViewMedecin.ControllerViewConsultationMedecin;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewConsultationMedecin extends JFrame {

	private ControllerViewConsultationMedecin controller;
	private JPanel contentPane;
	private String idConsult;
	private JTextArea textArea_namePatient;
	private Consultation consultation;
	private Patient patient;
	private Medecin medecin;

	/**
	 * Launch the application.
	 */
	public static void main(String idConsult) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultationMedecin frame = new ViewConsultationMedecin(idConsult);
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
	public ViewConsultationMedecin(String idConsult) {
		controller = new ControllerViewConsultationMedecin(this);
		controller.consultationRetrive(idConsult);
		controller.retrivePatient();
		controller.getMedecin(consultation.getMedecin());
		this.idConsult = idConsult;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Consultation");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setBounds(87, 25, 148, 13);
		contentPane.add(lblTitle);
		
		JTextArea textArea_idConsult = new JTextArea();
		textArea_idConsult.setText(idConsult);
		textArea_idConsult.setBounds(198, 21, 122, 22);
		contentPane.add(textArea_idConsult);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setFont(new Font("Verdana Pro", Font.PLAIN, 12));
		lblPatient.setBounds(87, 68, 70, 13);
		contentPane.add(lblPatient);
		
		textArea_namePatient = new JTextArea();
		String patientName = patient.getNom() + " " + patient.getPrenom();
		textArea_namePatient.setText(patientName);
		textArea_namePatient.setBounds(198, 63, 122, 22);
		contentPane.add(textArea_namePatient);
		
		JButton btnDetailsPatient = new JButton("Anamnése");
		btnDetailsPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAnamnese.main(patient.getIdPatient());
			}
		});
		btnDetailsPatient.setBounds(198, 95, 85, 21);
		contentPane.add(btnDetailsPatient);
		
		JLabel lblNewLabel = new JLabel("Medecin");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.PLAIN, 12));
		lblNewLabel.setBounds(87, 130, 70, 13);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea_DoctorName = new JTextArea();
		textArea_DoctorName.setBounds(198, 125, 122, 22);
		textArea_DoctorName.setText(medecin.getNom() + " " + medecin.getPrenom());
		contentPane.add(textArea_DoctorName);
		
		JTextArea textArea_date = new JTextArea();
		textArea_date.setBounds(198, 233, 122, 22);
		textArea_date.setText(consultation.getDate().toString());
		contentPane.add(textArea_date);
		
		JLabel lblNewLabel_1 = new JLabel("Details");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(87, 173, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea_details = new JTextArea();
		textArea_details.setBounds(198, 168, 122, 53);
		textArea_details.setText(consultation.getDetailsCliniques());
		contentPane.add(textArea_details);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(87, 239, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnCreateNewOrdo = new JButton("newOrdonnance");
		btnCreateNewOrdo.addActionListener(controller.new createNewOrdonnance());
		btnCreateNewOrdo.setBounds(87, 277, 85, 21);
		contentPane.add(btnCreateNewOrdo);
		
		JButton btnNewButton_1 = new JButton("Delete Ordonnance");
		btnNewButton_1.addActionListener(controller.new DeleteOrdonnance());
		btnNewButton_1.setBounds(182, 277, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Print Ordonnance");
		btnNewButton.addActionListener(controller.new printOrdonnance());
		btnNewButton.setBounds(277, 277, 85, 21);
		contentPane.add(btnNewButton);
	}
	
	public String getIdConsult() {
		return this.idConsult;
	}
	
	public Consultation getConsultation() {
		return this.consultation;
	}
	
	public void setConsultation(Consultation c) {
		
		this.consultation = c;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public void setPatient(Patient p) {
		this.patient = p;
	}
	
	public Medecin getMedecin() {
		return this.medecin;
	}
	
	public void setMedecin (Medecin m) {
		this.medecin = m;
	}
	
	public JPanel getContentPanel() {
		return this.contentPane;
	}
}
