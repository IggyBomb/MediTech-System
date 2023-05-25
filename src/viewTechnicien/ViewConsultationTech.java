package viewTechnicien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllerViewTechnicien.ControllerConsultationTech;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewConsultationTech extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_idConsult;
	private String idConsult;
	JTextArea textArea_prescription;
	private ControllerConsultationTech controller;
	

	/**
	 * Launch the application.
	 */
	public static void main(Frame owner, String idConsult) {
		try {
			ViewConsultationTech dialog = new ViewConsultationTech(owner, idConsult);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewConsultationTech(Frame owner, String idConsult) {
		super(owner, "Consultation", true);
		controller = new ControllerConsultationTech(this);
		this.idConsult = idConsult;
		setBounds(100, 100, 404, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Consultation");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setBounds(34, 44, 130, 13);
		contentPanel.add(lblTitle);
		
		textField_idConsult = new JTextField();
		textField_idConsult.setText(idConsult);
		textField_idConsult.setBounds(152, 43, 189, 19);
		contentPanel.add(textField_idConsult);
		textField_idConsult.setColumns(10);
		
		JLabel lblPrescription = new JLabel("Prescription");
		lblPrescription.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblPrescription.setBounds(34, 98, 99, 13);
		contentPanel.add(lblPrescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(152, 94, 189, 67);
		contentPanel.add(scrollPane);
		
		JTextArea textArea_prescription = new JTextArea();
		textArea_prescription.setText(controller.getPrescription());
		scrollPane.setViewportView(textArea_prescription);
		{
			JButton buttonOctroyer = new JButton("octroyer");
			buttonOctroyer.addActionListener(controller.new octroyerMedicalDevice());
			buttonOctroyer.setBounds(262, 171, 80, 21);
			contentPanel.add(buttonOctroyer);
			buttonOctroyer.setActionCommand("OK");
			getRootPane().setDefaultButton(buttonOctroyer);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public String getIdConsultation() {
		return this.idConsult;
	}
	
	public JTextField getTextFieldIdConsult() {
		return this.textField_idConsult;
	}
	
	public JTextArea getTextAreaPrescription() {
		return this.textArea_prescription;
	}
	
	public JPanel getJPanel() {
		return this.contentPanel;
	}
}