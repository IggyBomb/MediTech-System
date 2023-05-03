package GestionnaireAdminSwing;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Acteurs.Patient;
import formulaires.GestionnaireAdministratif;

public class InsertPatient implements ActionListener {

	private GestionnaireAdministratif gestionnaire;
	private JFrame frame;

	public InsertPatient(GestionnaireAdministratif gestionnaire) {
		this.gestionnaire = gestionnaire;
		this.frame =  new JFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Create a new dialog box for adding a patient
		final JDialog dialog = new JDialog(frame, "Ajouter un patient", true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// Create the input fields
		final JTextField idField = new JTextField();
		final JTextField nomField = new JTextField();
		final JTextField prenomField = new JTextField();
		final JTextField dateNaissanceField = new JTextField();
		final JTextField adresseField = new JTextField();

		// Create the label and panel for the input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);
		inputPanel.add(new JLabel("Nom:"));
		inputPanel.add(nomField);
		inputPanel.add(new JLabel("Prénom:"));
		inputPanel.add(prenomField);
		inputPanel.add(new JLabel("Date de naissance (YYYY-MM-DD):"));
		inputPanel.add(dateNaissanceField);
		inputPanel.add(new JLabel("Adresse:"));
		inputPanel.add(adresseField);

		// Create the button panel with the "Ajouter" and "Annuler" buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton ajouterButton = new JButton("Ajouter");
		ajouterButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Get the input values
		        String id = idField.getText();
		        String nom = nomField.getText();
		        String prenom = prenomField.getText();
		        String dateNaissanceStr = dateNaissanceField.getText();
		        String adresse = adresseField.getText();

		        // Create the new patient object
		        Patient patient = new Patient(nom, prenom, adresse, null, id);

		        // Insert the patient into the database
		        boolean insertionSuccessful = gestionnaire.insertPatient(patient);
		        // Show a message to the user depending on the result of the insertion
		        if (insertionSuccessful) {
		            JOptionPane.showMessageDialog(dialog, "Patient ajouté avec succès!", "Ajout de patient", JOptionPane.INFORMATION_MESSAGE);
		            dialog.dispose();
		        } else {
		            JOptionPane.showMessageDialog(dialog, "Une erreur est survenue lors de l'ajout du patient.", "Ajout de patient", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		JButton annulerButton = new JButton("Annuler");
		annulerButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        dialog.dispose();
		    }
		});
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(ajouterButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(annulerButton);
	    // Add the input and button panels to the dialog
	    dialog.add(inputPanel, BorderLayout.CENTER);
	    dialog.add(buttonPanel, BorderLayout.SOUTH); 
	    // Set the size of the dialog and show it
	    dialog.pack();
	    dialog.setVisible(true);
	}
	
	
}
