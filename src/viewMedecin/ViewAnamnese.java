package viewMedecin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DossierMedicale.Dossier;
import controllerViewMedecin.ControllerViewAnamnese;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ViewAnamnese extends JFrame {

	private JPanel contentPane;
	JTextPane textPane_anamnese;
	private String idPatient;
	private ControllerViewAnamnese controller;
	private Dossier dossier;
	private JTextField textField_PatientData;

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
		try {
			this.dossier = controller.getDossier(idPatient);
			if(this.dossier == null) {
				JOptionPane.showMessageDialog(contentPane, "No Dossier For this Patient", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
				controller.createDossier(idPatient);
				this.dossier = controller.getDossier(idPatient);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "No Dossier For this Patient", "Prescription results", JOptionPane.INFORMATION_MESSAGE);
			controller.createDossier(idPatient);
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.idPatient = idPatient;
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Anamn\u00E8se");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblNewLabel.setBounds(33, 21, 111, 24);
		contentPane.add(lblNewLabel);
		
		textField_PatientData = new JTextField();
		textField_PatientData.setBounds(33, 55, 140, 19);
		contentPane.add(textField_PatientData);
		textField_PatientData.setColumns(10);
		textField_PatientData.setText(controller.retrivePatientData());

		JButton btnNewButton_save = new JButton("Save");
		btnNewButton_save.addActionListener(controller.new UpdateDossier());
		btnNewButton_save.setBounds(156, 313, 85, 21);
		contentPane.add(btnNewButton_save);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 93, 359, 202);
		contentPane.add(scrollPane);

		textPane_anamnese = new JTextPane();
		scrollPane.setViewportView(textPane_anamnese);
		textPane_anamnese.setText(controller.retriveAnamnse());

	}

	public JTextPane getPanelText() {
		return this.textPane_anamnese;
	}

	public String getIdPatient() {
		return this.idPatient;
	}

	public void setDossier(Dossier d) {
		this.dossier = d;
	}

	public Dossier getDossier() {
		return this.dossier;
	}
}
