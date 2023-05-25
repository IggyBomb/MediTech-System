package viewMedecin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Consultation;
import Models.GestionnaireConsultation;
import Models.Ordonnance;
import controllerViewMedecin.ControllerViewOrdonnance;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewOrdonnance extends JFrame {

	private JPanel contentPane;
	private  String idOrd;
	ControllerViewOrdonnance controller;
	JEditorPane editorPane_medicaments;
	JEditorPane editorPane_propSoins;
	JEditorPane editorPane_appareilMed;

	/**
	 * Launch the application.
	 */
	public static void main(String idConsult) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOrdonnance frame = new ViewOrdonnance(idConsult);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 * @throws SQLIntegrityConstraintViolationException 
	 */
	public ViewOrdonnance(String idConsult) throws SQLIntegrityConstraintViolationException {
		controller = new ControllerViewOrdonnance(this);
		this.idOrd = idConsult;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrdonnance = new JLabel("Ordonnance");
		lblOrdonnance.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblOrdonnance.setBounds(186, 10, 171, 13);
		contentPane.add(lblOrdonnance);
		
		JLabel lblmedicaments = new JLabel("Medicaments:");
		lblmedicaments.setBounds(60, 42, 84, 13);
		contentPane.add(lblmedicaments);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 43, 189, 44);
		contentPane.add(scrollPane);
		
		editorPane_medicaments = new JEditorPane();
		scrollPane.setViewportView(editorPane_medicaments);
		
		JLabel lblNewLabel = new JLabel("PropositionsSoins:");
		lblNewLabel.setBounds(60, 123, 112, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_PropSoins = new JScrollPane();
		scrollPane_PropSoins.setBounds(192, 123, 187, 42);
		contentPane.add(scrollPane_PropSoins);
		
		editorPane_propSoins = new JEditorPane();
		scrollPane_PropSoins.setViewportView(editorPane_propSoins);
		
		JLabel lblNewLabel_appareilMedical = new JLabel("Appareil Medical:");
		lblNewLabel_appareilMedical.setBounds(60, 211, 84, 13);
		contentPane.add(lblNewLabel_appareilMedical);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(197, 211, 185, 60);
		contentPane.add(scrollPane_1);
		
		editorPane_appareilMed = new JEditorPane();
		scrollPane_1.setViewportView(editorPane_appareilMed);
		
		JButton btncreate = new JButton("Create");
		btncreate.addActionListener(controller.new setOrdonnace());

		btncreate.setBounds(121, 308, 85, 21);
		contentPane.add(btncreate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPane_medicaments.setText("");
				editorPane_propSoins.setText("");
				editorPane_appareilMed.setText("");
			}
		});
		btnClear.setBounds(227, 308, 85, 21);
		contentPane.add(btnClear);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(328, 308, 85, 21);
		contentPane.add(btnClose);
	}
	
	public String getEditorPane_medicaments() {
		return editorPane_medicaments.getText();
	}


	public void setEditorPane_medicaments(JEditorPane editorPane_medicaments) {
		this.editorPane_medicaments = editorPane_medicaments;
	}


	public String getEditorPane_propSoins() {
		return editorPane_propSoins.getText();
	}


	public void setEditorPane_propSoins(JEditorPane editorPane_propSoins) {
		this.editorPane_propSoins = editorPane_propSoins;
	}


	public String getEditorPane_appareilMed() {
		return editorPane_appareilMed.getText();
	}


	public void setEditorPane_appareilMed(JEditorPane editorPane_appareilMed) {
		this.editorPane_appareilMed = editorPane_appareilMed;
	}


	public String getIdConsult() {
		return this.idOrd;
	}
}
