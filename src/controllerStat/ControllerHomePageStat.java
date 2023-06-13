package controllerStat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Models.ModelStat;
import viewStat.ViewHomePageStat;

public class ControllerHomePageStat {
	private ViewHomePageStat view;
	private ModelStat model;

	public ControllerHomePageStat(ViewHomePageStat view) {
		this.view = view;
		model = new ModelStat("root", "T1t4n1c0");
	} 

	public class ConsultationByDate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List<Map<Integer, Integer>> list = model.getMonthlyConsultationsStats(view.getConsultYear());
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No Consultations found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String[] columnNames = {"Month", "nb Visits"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				String[] months = {"January", "February", "March", "April", "May", "June",
						"July", "August", "September", "October", "November", "December" };
				for(Map<Integer, Integer> map : list) {
					Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
					String monthName = months[entry.getKey() - 1];
					Object[] rowData = {monthName, entry.getValue()};
					tableModel.addRow(rowData);
				} 
				int totConsult = model.getTotalConsultations(view.getConsultYear());
				Object[] dataTot = {"Consultations during: " + view.getConsultYear(), totConsult};
				tableModel.addRow(dataTot);
				view.getTableResult().setModel(tableModel);
			}
		}
	}

	public class DevicesGranted implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			List<Map<Integer, Integer>> list =  model.getMonthlyGrantedDeviceStatusStats(view.getYearDevices());
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(view.getContentPane(), "No devices found.", "Search results Consultation", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String[] columnNames = {"Month", "nb Visits"};
				DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
				String[] months = {"January", "February", "March", "April", "May", "June",
						"July", "August", "September", "October", "November", "December" };
				for(Map<Integer, Integer> map : list) {
					Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();
					String monthName = months[entry.getKey() - 1];
					Object[] rowData = {monthName, entry.getValue()};
					tableModel.addRow(rowData);
				}
				int totDevicePrescribed = model.getTotalDevices(view.getYearDevices());
				Object[] dataPrescribed = {"Devices prescribed during: " + view.getYearDevices(), totDevicePrescribed};
				tableModel.addRow(dataPrescribed);
				int totDevicesGranted = model.getTotalDevicesGranted(view.getYearDevices());
				Object[] dataGranted = {"Devices granted during: " + view.getYearDevices(), totDevicesGranted};
				tableModel.addRow(dataGranted);
				view.getTableResult().setModel(tableModel);
			}
		}
	}
}
