package ViewSuperAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllerViewSuperAdmin.ControllerViewSuperAdmin;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewSuperAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTable tableResults;
	private ControllerViewSuperAdmin controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSuperAdmin frame = new ViewSuperAdmin();
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
	public ViewSuperAdmin() {
		controller = new ControllerViewSuperAdmin(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Gestionnaire Employee");
		lblTitle.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblTitle.setBounds(118, 24, 260, 23);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search Employee By ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(27, 62, 204, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(10, 21, 45, 13);
		panel.add(lblID);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(40, 18, 135, 19);
		panel.add(textField_ID);
		textField_ID.setColumns(10);
		
		JButton btnSearchID = new JButton("Search");
		btnSearchID.addActionListener(controller.new searchEmployee());
		btnSearchID.setBounds(50, 44, 85, 21);
		panel.add(btnSearchID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 61, 463, 176);
		contentPane.add(scrollPane);
		
		tableResults = new JTable();
		scrollPane.setViewportView(tableResults);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				tableResults.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnRefresh.setBounds(642, 254, 85, 21);
		contentPane.add(btnRefresh);
	}
	
	public String getIdEmployee() {
		return textField_ID.getText();
	}
	
	public JTable getTableResults() {
		return this.tableResults;
	}
}
