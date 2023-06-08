package ViewSuperAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;

public class ViewSuperAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTable tableResults;
	private ControllerViewSuperAdmin controller;
	private JTextField textField_name;
	private JTextField textField_ID_delete;
	private JTextField textField_idUpdate;
	private JComboBox comboBox;

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
		setBounds(100, 100, 910, 393);
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
		btnSearchID.addActionListener(controller.new searchEmployeeByID());
		btnSearchID.setBounds(50, 44, 85, 21);
		panel.add(btnSearchID);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 62, 463, 176);
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
		btnRefresh.setBounds(801, 255, 85, 21);
		contentPane.add(btnRefresh);

		JButton btnNewEmployee = new JButton("New Employee");
		btnNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewNewEmployee.main(null);
			}
		});
		btnNewEmployee.setBounds(458, 310, 118, 21);
		contentPane.add(btnNewEmployee);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search By Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(27, 155, 204, 89);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 31, 45, 13);
		panel_1.add(lblNewLabel);

		textField_name = new JTextField();
		textField_name.setBounds(46, 28, 129, 19);
		panel_1.add(textField_name);
		textField_name.setColumns(10);

		JButton btnSearch_name = new JButton("Search");
		btnSearch_name.addActionListener(controller.new SearchEmployeeByName());
		btnSearch_name.setBounds(56, 57, 85, 21);
		panel_1.add(btnSearch_name);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Delete Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(27, 254, 204, 84);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(9, 26, 45, 13);
		panel_2.add(lblNewLabel_1);

		textField_ID_delete = new JTextField();
		textField_ID_delete.setBounds(47, 23, 126, 19);
		panel_2.add(textField_ID_delete);
		textField_ID_delete.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(controller.new deleteEmployee());
		btnDelete.setBounds(61, 50, 85, 21);
		panel_2.add(btnDelete);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Update Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(241, 62, 172, 89);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = controller.checkId(textField_idUpdate.getText());
				if(result == true) {
					ViewUpdateEmployee.main(textField_idUpdate.getText());
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "ID not found.", "Search results Employee", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(40, 54, 106, 21);
		panel_3.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(10, 28, 45, 13);
		panel_3.add(lblNewLabel_2);

		textField_idUpdate = new JTextField();
		textField_idUpdate.setBounds(30, 25, 123, 19);
		panel_3.add(textField_idUpdate);
		textField_idUpdate.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "List Employees", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(241, 161, 172, 106);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Profession");
		lblNewLabel_3.setBounds(45, 24, 69, 13);
		panel_4.add(lblNewLabel_3);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 41, 140, 21);
		panel_4.add(comboBox);
		comboBox.addItem("medecin");
		comboBox.addItem("technicien");
		comboBox.addItem("admin");
		comboBox.addItem("superAdmin");
		
		JButton btnSearchProfession = new JButton("Search");
		btnSearchProfession.addActionListener(controller.new searchEmployeeByProfession());
		btnSearchProfession.setBounds(45, 75, 85, 21);
		panel_4.add(btnSearchProfession);
	}

	public String getIdEmployee() {
		return textField_ID.getText();
	}

	public JTable getTableResults() {
		return this.tableResults;
	}

	public String getName() {
		return textField_name.getText();
	}

	public String getIdDelete() {
		return textField_ID_delete.getText();
	}
	
	public String getProfession() {
	    Object selectedItem = comboBox.getSelectedItem();
	    if (selectedItem != null) {
	    	System.out.println(selectedItem);
	        if(selectedItem.equals("superAdmin")) {
	        	selectedItem = "admin";
	        }
	        return selectedItem.toString();
	    } else {
	        return null;
	    }
	}
}
