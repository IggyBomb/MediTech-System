package ConsultationSwing;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gestionnaire extends JFrame {

	private JPanel contentPane;
	private JTextField textField_idConsult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestionnaire frame = new Gestionnaire();
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
	public Gestionnaire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Gestionnaire Consultation");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(267, 25, 214, 13);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search Consultation by ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(39, 81, 271, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIDConsultation = new JLabel("ID Consultation");
		lblIDConsultation.setBounds(10, 27, 99, 13);
		panel.add(lblIDConsultation);
		
		textField_idConsult = new JTextField();
		textField_idConsult.setBounds(114, 24, 147, 19);
		panel.add(textField_idConsult);
		textField_idConsult.setColumns(10);
		
		JButton btnSearchConsultationID = new JButton("Search");
		btnSearchConsultationID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchByID.main(textField_idConsult.getText());
			}
		});
		btnSearchConsultationID.setBounds(114, 53, 85, 21);
		panel.add(btnSearchConsultationID);
		
		JButton btnNewConsult = new JButton("New Visit");
		btnNewConsult.setBounds(63, 451, 137, 34);
		contentPane.add(btnNewConsult);
	}
}
