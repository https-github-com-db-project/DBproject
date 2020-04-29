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

public class Faculties {

	private JFrame frame;

	Connection connection;
	PreparedStatement pst;
	private JTextField textFieldFid;
	private JTextField textFieldFname;
	private JTextField textFieldFoffice;
	private JTextField textFieldFdepar;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Faculties window = new Faculties();
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
			
			pst = connection.prepareStatement("SELECT * FROM faculties");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getString("faculty_id"));
	        		v2.add(rs.getString("faculty_name"));
	        		v2.add(rs.getString("faculty_office"));
	        		v2.add(rs.getString("faculty_department"));
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
	public Faculties() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1100, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    
	    frame.setVisible(true);
		
		initialize();
		prepareConnection();
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
		
		JLabel lblNewLabel = new JLabel("Faculties");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(31, 46, 153, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 108, 429, 327);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCId = new JLabel("FACULTY_ID");
		lblCId.setBounds(10, 46, 137, 14);
		panel.add(lblCId);
		
		JLabel lblCname = new JLabel("FACULTY_NAME");
		lblCname.setBounds(10, 81, 137, 14);
		panel.add(lblCname);
		
		JLabel lblNewLabel_2 = new JLabel("FACULTY_OFFICE");
		lblNewLabel_2.setBounds(10, 117, 137, 14);
		panel.add(lblNewLabel_2);
	
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(110, 231, 89, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(209, 231, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(308, 231, 89, 23);
		panel.add(btnDelete);
		
		JLabel lblFacultydepartment = new JLabel("FACULTY_DEPARTMENT");
		lblFacultydepartment.setBounds(10, 153, 137, 14);
		panel.add(lblFacultydepartment);
		
		textFieldFid = new JTextField();
		textFieldFid.setBounds(185, 43, 212, 20);
		panel.add(textFieldFid);
		textFieldFid.setColumns(10);
		
		textFieldFname = new JTextField();
		textFieldFname.setColumns(10);
		textFieldFname.setBounds(185, 78, 212, 20);
		panel.add(textFieldFname);
		
		textFieldFoffice = new JTextField();
		textFieldFoffice.setColumns(10);
		textFieldFoffice.setBounds(185, 114, 212, 20);
		panel.add(textFieldFoffice);
		
		textFieldFdepar = new JTextField();
		textFieldFdepar.setColumns(10);
		textFieldFdepar.setBounds(185, 150, 212, 20);
		panel.add(textFieldFdepar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 108, 585, 327);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"FACULTY_ID", "FACULTY_NAME", "FACULTY_OFFICE", "FACULTY_DEPARMENT"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				textFieldFid.setText(defaultTableModel.getValueAt(selectedRow, 0).toString());
		        textFieldFname.setText(defaultTableModel.getValueAt(selectedRow, 1).toString());
		        textFieldFoffice.setText(defaultTableModel.getValueAt(selectedRow, 2).toString());
		        textFieldFdepar.setText(defaultTableModel.getValueAt(selectedRow, 3).toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String facultyId = textFieldFid.getText();
				String facultyName = textFieldFname.getText();
				String facultyOffice = textFieldFoffice.getText();
				String facultyDepar = textFieldFdepar.getText();
				
				try {
					pst = connection.prepareStatement("INSERT INTO faculties "
							+ "VALUES(?,?,?,?)");
					pst.setString(1, facultyId);
			        pst.setString(2, facultyName);
			        pst.setString(3, facultyOffice);
			        pst.setString(4, facultyDepar);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Added");
					
					textFieldFid.setText("");
					textFieldFname.setText("");
					textFieldFoffice.setText("");
					textFieldFdepar.setText("");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String facultyId = textFieldFid.getText();
				String facultyName = textFieldFname.getText();
				String facultyOffice = textFieldFoffice.getText();
				String facultyDepar = textFieldFdepar.getText();
				
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String idInDb = defaultTableModel.getValueAt(selectedRow, 0).toString();
				
				try {
					pst = connection.prepareStatement("UPDATE faculties SET "
			        		+ "faculty_id = ?,"
			        		+ "faculty_name = ?,"
			        		+ "faculty_office = ?,"
			        		+ "faculty_department = ?"
			        		+ " WHERE faculty_id = ?");
					pst.setString(1, facultyId);
			        pst.setString(2, facultyName);
			        pst.setString(3, facultyOffice);
			        pst.setString(4, facultyDepar);
			        pst.setString(5, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Edited");

					textFieldFid.setText("");
					textFieldFname.setText("");
					textFieldFoffice.setText("");
					textFieldFdepar.setText("");
					
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
					pst = connection.prepareStatement("DELETE FROM faculties WHERE faculty_id = ?");
			        pst.setString(1, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					textFieldFid.setText("");
					textFieldFname.setText("");
					textFieldFoffice.setText("");
					textFieldFdepar.setText("");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
	}

}
