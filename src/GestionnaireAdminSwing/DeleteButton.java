package GestionnaireAdminSwing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Acteurs.Patient;
import formulaires.GestionnaireAdministratif;

public class DeleteButton implements ActionListener {

    private GestionnaireAdministratif gestionnaire;
	private JFrame frame;

    public DeleteButton(GestionnaireAdministratif gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.frame =  new JFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		// Create a new dialog box for adding a patient
		final JDialog dialog = new JDialog(frame, "Eliminer un patient", true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// Create the input fields
		final JTextField idField = new JTextField();

		// Create the label and panel for the input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);


		// Create the button panel with the "éliminer" and "Annuler" buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton ajouterButton = new JButton("Suppression");
		ajouterButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Get the input values
		        String id = idField.getText();

		        // Delete the patient
		        boolean deletionSuccessful = gestionnaire.deletePatient(id);
		        // Show a message to the user depending on the result of the deletionb
		        if (deletionSuccessful) {
		            JOptionPane.showMessageDialog(dialog, "Patient éliminé avec succès!", "Suppression du patient", JOptionPane.INFORMATION_MESSAGE);
		            dialog.dispose();
		        } else {
		            JOptionPane.showMessageDialog(dialog, "Une erreur est survenue lors de l'élimination du patient.", "Suppression du patient", JOptionPane.ERROR_MESSAGE);
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