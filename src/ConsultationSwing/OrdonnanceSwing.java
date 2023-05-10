package ConsultationSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Consultation.Consultation;
import formulaires.GestionnaireConsultation;
import ordonnance.Ordonnance;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class OrdonnanceSwing extends JFrame {

	private JPanel contentPane;
	private Consultation consult;
	private static String idOrd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdonnanceSwing frame = new OrdonnanceSwing();
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
	public OrdonnanceSwing() {
		Consultation consult = SearchByConsultID.consultation;
		Ordonnance ordonnance = consult.findOrdonnance();
		System.out.println(consult.getIdConsult());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JEditorPane editorPane_medicaments = new JEditorPane();
		scrollPane.setViewportView(editorPane_medicaments);
		
		JLabel lblNewLabel = new JLabel("PropositionsSoins:");
		lblNewLabel.setBounds(60, 123, 112, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_PropSoins = new JScrollPane();
		scrollPane_PropSoins.setBounds(192, 123, 187, 42);
		contentPane.add(scrollPane_PropSoins);
		
		JEditorPane editorPane_propSoins = new JEditorPane();
		scrollPane_PropSoins.setViewportView(editorPane_propSoins);
		
		JLabel lblNewLabel_appareilMedical = new JLabel("Appareil Medical:");
		lblNewLabel_appareilMedical.setBounds(60, 211, 84, 13);
		contentPane.add(lblNewLabel_appareilMedical);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(197, 211, 185, 60);
		contentPane.add(scrollPane_1);
		
		JEditorPane editorPane_appareilMed = new JEditorPane();
		scrollPane_1.setViewportView(editorPane_appareilMed);
		
		JButton btncreate = new JButton("Create");
		btncreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordonnance.setMedicaments(editorPane_medicaments.getText());
				ordonnance.setAppareilMedical(editorPane_appareilMed.getText());
				ordonnance.setPropositionsSoins(editorPane_propSoins.getText());
				boolean result = ordonnance.updateOrdonnance();
				if(result) {
					JOptionPane.showMessageDialog(contentPane, "Prescription created", "Prescription result", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
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
}
