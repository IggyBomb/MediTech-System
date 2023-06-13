package viewStat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import com.toedter.calendar.JYearChooser;

import controllerStat.ControllerHomePageStat;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewHomePageStat extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JYearChooser yearChooserConsult;
	private JYearChooser yearChooser_devices;
	private ControllerHomePageStat controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHomePageStat frame = new ViewHomePageStat();
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
	public ViewHomePageStat() {
		controller = new ControllerHomePageStat(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 62, 475, 205);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consultation By Year", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 67, 147, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Year");
		lblNewLabel.setBounds(8, 36, 45, 13);
		panel.add(lblNewLabel);
		
		yearChooserConsult = new JYearChooser();
		yearChooserConsult.setBounds(43, 33, 46, 19);
		panel.add(yearChooserConsult);
		
		JButton btnSearchConsultByYear = new JButton("Search");
		btnSearchConsultByYear.addActionListener(controller.new ConsultationByDate());
		btnSearchConsultByYear.setBounds(18, 59, 85, 21);
		panel.add(btnSearchConsultByYear);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel emptyTableModel = new DefaultTableModel();
				table.setModel(emptyTableModel); // Set the empty table model for tableResults
			}
		});
		btnRefresh.setBounds(577, 280, 85, 21);
		contentPane.add(btnRefresh);
		
		JLabel lblNewLabel_1 = new JLabel("Statistic");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.BOLD, 14));
		lblNewLabel_1.setBounds(175, 10, 118, 28);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Devices Granted", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 171, 147, 96);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Year");
		lblNewLabel_2.setBounds(11, 31, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		JButton btnSearchDevice = new JButton("Search");
		btnSearchDevice.addActionListener(controller.new DevicesGranted());
		btnSearchDevice.setBounds(11, 54, 85, 21);
		panel_1.add(btnSearchDevice);
		
		yearChooser_devices = new JYearChooser();
		yearChooser_devices.setBounds(43, 28, 46, 19);
		panel_1.add(yearChooser_devices);
	}
	
	public int getConsultYear(){
	    return yearChooserConsult.getYear();
	}
	
	public JTable getTableResult() {
		return this.table;
	}

	public void setTableResult(JTable tableResult) {
		this.table = tableResult;
	}
	
	public int getYearDevices() {
		return yearChooser_devices.getYear();
	}
}
