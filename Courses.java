package com.pack;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Courses {

	private JFrame frame;
	private JTextField textFieldCid;
	private JTextField textFieldCname;
	private JTable table;
	
	
	Connection connection;
	PreparedStatement pst;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Courses window = new Courses();
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
			
			pst = connection.prepareStatement("SELECT * FROM courses");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getString("course_id"));
	        		v2.add(rs.getString("course_name"));
	        		v2.add(rs.getString("speciality_id"));
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
	public Courses() {
		
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
		
		JLabel lblNewLabel = new JLabel("Courses");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(45, 59, 153, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 122, 429, 327);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCId = new JLabel("COURSE_ID");
		lblCId.setBounds(10, 46, 115, 14);
		panel.add(lblCId);
		
		JLabel lblCname = new JLabel("COURSE_NAME");
		lblCname.setBounds(10, 81, 115, 14);
		panel.add(lblCname);
		
		JLabel lblNewLabel_2 = new JLabel("SPECIALITY_ID");
		lblNewLabel_2.setBounds(10, 117, 115, 14);
		panel.add(lblNewLabel_2);
		
		textFieldCid = new JTextField();
		textFieldCid.setBounds(185, 43, 213, 20);
		panel.add(textFieldCid);
		textFieldCid.setColumns(10);
		
		textFieldCname = new JTextField();
		textFieldCname.setColumns(10);
		textFieldCname.setBounds(185, 78, 213, 20);
		panel.add(textFieldCname);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(111, 195, 89, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(210, 195, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(309, 195, 89, 23);
		panel.add(btnDelete);
		
		JComboBox comboBox = new JComboBox();
		String[] specIds = specIdArray();
		comboBox.setModel(new DefaultComboBoxModel(specIds));
		comboBox.setBounds(185, 113, 213, 22);
		panel.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(496, 122, 460, 327);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"COURSE_ID", "COURSE_NAME", "SPECIALITY_ID"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(255);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				textFieldCid.setText(defaultTableModel.getValueAt(selectedRow, 0).toString());
		        textFieldCname.setText(defaultTableModel.getValueAt(selectedRow, 1).toString());
		        comboBox.setSelectedItem(defaultTableModel.getValueAt(selectedRow, 2).toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String courseId = textFieldCid.getText();
				String courseName = textFieldCname.getText();
				String courseSid = (String) comboBox.getSelectedItem();
				
				try {
					pst = connection.prepareStatement("INSERT INTO courses "
							+ "VALUES(?,?,?)");
					pst.setString(1, courseId);
			        pst.setString(2, courseName);
			        pst.setString(3, courseSid);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Added");
					
					textFieldCid.setText("");
					textFieldCname.setText("");
					comboBox.setModel(new DefaultComboBoxModel(specIds));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String courseId = textFieldCid.getText();
				String courseName = textFieldCname.getText();
				String courseSid = (String) comboBox.getSelectedItem();
				
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String idInDb = defaultTableModel.getValueAt(selectedRow, 0).toString();
				
				try {
					pst = connection.prepareStatement("UPDATE courses SET "
			        		+ "course_id = ?,"
			        		+ "course_name = ?,"
			        		+ "speciality_id = ?"
			        		+ " WHERE course_id = ?");
					pst.setString(1, courseId);
			        pst.setString(2, courseName);
			        pst.setString(3, courseSid);
			        pst.setString(4, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Edited");

					
					textFieldCid.setText("");
					textFieldCname.setText("");
					comboBox.setModel(new DefaultComboBoxModel(specIds));
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
					pst = connection.prepareStatement("DELETE FROM courses WHERE course_id = ?");
			        pst.setString(1, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					textFieldCid.setText("");
					textFieldCname.setText("");
					comboBox.setModel(new DefaultComboBoxModel(specIds));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
	}
	
	private String[] specIdArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT speciality_id FROM specialities");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("speciality_id"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
	}
}
