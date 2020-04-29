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

import javax.swing.DefaultComboBoxModel;
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

public class StudentsCourses {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	Connection connection;
	PreparedStatement pst;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentsCourses window = new StudentsCourses();
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
			
			pst = connection.prepareStatement("SELECT * FROM students_courses");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getString("student_id"));
	        		v2.add(rs.getString("course_id"));
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
	public StudentsCourses() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 780, 450);
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
	
	JComboBox comboBox_1;
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
		
		JLabel lblNewLabel = new JLabel("Student's Courses");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(45, 59, 292, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 128, 328, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCId = new JLabel("STUDENT_ID");
		lblCId.setBounds(10, 85, 99, 14);
		panel.add(lblCId);
		
		JLabel lblCname = new JLabel("COURSE_ID");
		lblCname.setBounds(10, 129, 99, 14);
		panel.add(lblCname);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 175, 89, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(109, 175, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(208, 175, 89, 23);
		panel.add(btnDelete);
		
		comboBox_1 = new JComboBox();
		String[] specIds = specIdArray();
		comboBox_1.setModel(new DefaultComboBoxModel(specIds));
		comboBox_1.setBounds(115, 35, 182, 22);
		panel.add(comboBox_1);
		
		JComboBox comboBox = new JComboBox();
		String[] courseIds = courseIdArray();
		comboBox.setModel(new DefaultComboBoxModel(courseIds));
		comboBox.setBounds(115, 125, 182, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("SPECIALITY_ID");
		lblNewLabel_1.setBounds(10, 39, 89, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox_2 = new JComboBox();
		String[] stuIds = studIdArray();
		comboBox_2.setModel(new DefaultComboBoxModel(stuIds));
		comboBox_2.setBounds(115, 81, 183, 22);
		panel.add(comboBox_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(403, 128, 300, 266);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"STUDENT_ID", "COURSE_ID"
			}
		));
		scrollPane.setViewportView(table);
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] courseIds = courseIdArray();
				comboBox.setModel(new DefaultComboBoxModel(courseIds));
				
				String[] stuIds = studIdArray();
				comboBox_2.setModel(new DefaultComboBoxModel(stuIds));
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				comboBox_2.setSelectedItem(defaultTableModel.getValueAt(selectedRow, 0).toString());
		        comboBox.setSelectedItem(defaultTableModel.getValueAt(selectedRow, 1).toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String stuId = comboBox_2.getSelectedItem().toString();
				String couId = comboBox.getSelectedItem().toString();
				
				try {
					pst = connection.prepareStatement("INSERT INTO students_courses "
							+ "VALUES(?,?)");
					pst.setString(1, stuId);
			        pst.setString(2, couId);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Added");
					
					comboBox_2.setModel(new DefaultComboBoxModel(stuIds));
					comboBox.setModel(new DefaultComboBoxModel(courseIds));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String stuId = comboBox_2.getSelectedItem().toString();
				String couId = comboBox.getSelectedItem().toString();
				
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String stuIdDB = defaultTableModel.getValueAt(selectedRow, 0).toString();
				String couIdDB = defaultTableModel.getValueAt(selectedRow, 1).toString();
				
				try {
					pst = connection.prepareStatement("UPDATE students_courses SET "
			        		+ "student_id = ?,"
			        		+ "course_id = ?"
			        		+ " WHERE student_id = ? AND course_id = ?");
					pst.setString(1, stuId);
			        pst.setString(2, couId);
			        pst.setString(3, stuIdDB);
			        pst.setString(4, couIdDB);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Edited");

					
					comboBox_2.setModel(new DefaultComboBoxModel(stuIds));
					comboBox.setModel(new DefaultComboBoxModel(courseIds));
					
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
				String stuIdDB = defaultTableModel.getValueAt(selectedRow, 0).toString();
				String couIdDB = defaultTableModel.getValueAt(selectedRow, 1).toString();
				
				try {
					pst = connection.prepareStatement("DELETE FROM students_courses WHERE student_id = ? AND course_id = ?");
			        pst.setString(1, stuIdDB);
			        pst.setString(2, couIdDB);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					comboBox_2.setModel(new DefaultComboBoxModel(stuIds));
					comboBox.setModel(new DefaultComboBoxModel(courseIds));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
	}
	
	private String[] courseIdArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			
			pst = connection.prepareStatement("SELECT course_id FROM courses WHERE speciality_id = ?");
			pst.setString(1, comboBox_1.getSelectedItem().toString());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("course_id"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
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
	
	private String[] studIdArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT student_id FROM students WHERE speciality_id = ?");
			pst.setString(1, comboBox_1.getSelectedItem().toString());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("student_id"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
	}
}
