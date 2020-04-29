package com.pack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Speciality {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	Connection connection;
	PreparedStatement pst;
	private JTextField textFieldSid;
	private JTextField textFieldStitle;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Speciality window = new Speciality();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void prepareConnection() {
        try {
        	Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "db_final", "dbfinal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateTable(){
		try {
			
			pst = connection.prepareStatement("SELECT * FROM specialities");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getString("speciality_id"));
	        		v2.add(rs.getString("speciality_title"));
	        		v2.add(rs.getString("faculty_id"));
	        	}
	        	
	        	defaultTableModel.addRow(v2);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Speciality() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    
	    frame.setVisible(true);
		
	    prepareConnection();
		initialize();
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JLabel lblBackLabel = new JLabel("<<Back");
		lblBackLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBackLabel.setForeground(Color.PINK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBackLabel.setForeground(Color.RED);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.dispose();
				MainMenu mainMenu = new MainMenu();
				
			}
		});
		lblBackLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBackLabel.setForeground(Color.RED);
		lblBackLabel.setBounds(10, 11, 71, 24);
		frame.getContentPane().add(lblBackLabel);
		
		JLabel lblNewLabel = new JLabel("Specialities");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(45, 59, 163, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 128, 429, 327);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCId = new JLabel("SPECIALITY_ID");
		lblCId.setBounds(10, 46, 115, 14);
		panel.add(lblCId);
		
		JLabel lblCname = new JLabel("SPECIALITY_TITLE");
		lblCname.setBounds(10, 81, 115, 14);
		panel.add(lblCname);
		
		JLabel lblNewLabel_2 = new JLabel("FACULTY_ID");
		lblNewLabel_2.setBounds(10, 117, 115, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(95, 195, 89, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(194, 195, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(293, 195, 89, 23);
		panel.add(btnDelete);
		
		textFieldSid = new JTextField();
		textFieldSid.setBounds(168, 43, 214, 20);
		panel.add(textFieldSid);
		textFieldSid.setColumns(10);
		
		textFieldStitle = new JTextField();
		textFieldStitle.setColumns(10);
		textFieldStitle.setBounds(168, 78, 214, 20);
		panel.add(textFieldStitle);
		
		JComboBox comboBox = new JComboBox();
		String[] facultyIds = facultyIdArray();
		comboBox.setModel(new DefaultComboBoxModel(facultyIds));
		comboBox.setBounds(168, 113, 214, 22);
		panel.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(495, 128, 460, 327);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"SPECIALITY_ID", "SPECIALITY_TITLE", "FACULTY_ID"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		scrollPane.setViewportView(table);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				textFieldSid.setText(defaultTableModel.getValueAt(selectedRow, 0).toString());
		        textFieldStitle.setText(defaultTableModel.getValueAt(selectedRow, 1).toString());
		        comboBox.setSelectedItem(defaultTableModel.getValueAt(selectedRow, 2).toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String specId = textFieldSid.getText();
				String specTitle = textFieldStitle.getText();
				String facId = (String) comboBox.getSelectedItem();
				
				try {
					pst = connection.prepareStatement("INSERT INTO specialities "
							+ "VALUES(?,?,?)");
					pst.setString(1, specId);
			        pst.setString(2, specTitle);
			        pst.setString(3, facId);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Added");
					
					textFieldSid.setText("");
					textFieldStitle.setText("");
					comboBox.setModel(new DefaultComboBoxModel(facultyIds));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String specId = textFieldSid.getText();
				String specTitle = textFieldStitle.getText();
				String facId = (String) comboBox.getSelectedItem();
				
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String idInDb = defaultTableModel.getValueAt(selectedRow, 0).toString();
				
				try {
					pst = connection.prepareStatement("UPDATE specialities SET "
			        		+ "speciality_id = ?,"
			        		+ "speciality_title = ?,"
			        		+ "faculty_id = ?"
			        		+ " WHERE speciality_id = ?");
					pst.setString(1, specId);
			        pst.setString(2, specTitle);
			        pst.setString(3, facId);
			        pst.setString(4, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Edited");

					
					textFieldSid.setText("");
					textFieldStitle.setText("");
					comboBox.setModel(new DefaultComboBoxModel(facultyIds));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String idInDb = defaultTableModel.getValueAt(selectedRow, 0).toString();
				
				try {
					pst = connection.prepareStatement("DELETE FROM specialities WHERE speciality_id = ?");
			        pst.setString(1, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					textFieldSid.setText("");
					textFieldStitle.setText("");
					comboBox.setModel(new DefaultComboBoxModel(facultyIds));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});	
	}
	
	private String[] facultyIdArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT faculty_id FROM faculties");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("faculty_id"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
	}
}
