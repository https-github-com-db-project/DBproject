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

public class Regions {

	private JFrame frame;

	Connection connection;
	PreparedStatement pst;
	private JTextField textFieldRid;
	private JTextField textFieldRname;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Regions window = new Regions();
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
			
			pst = connection.prepareStatement("SELECT * FROM regions");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getString("region_id"));
	        		v2.add(rs.getString("region_name"));
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
	public Regions() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 740, 430);
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
		
		
		JLabel lblNewLabel = new JLabel("Regions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(45, 43, 153, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 91, 337, 244);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCId = new JLabel("REGION_ID");
		lblCId.setBounds(10, 29, 115, 14);
		panel.add(lblCId);
		
		JLabel lblCname = new JLabel("REGION_NAME");
		lblCname.setBounds(10, 64, 115, 14);
		panel.add(lblCname);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(24, 126, 89, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(123, 126, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(222, 126, 89, 23);
		panel.add(btnDelete);
		
		textFieldRid = new JTextField();
		textFieldRid.setBounds(124, 26, 187, 20);
		panel.add(textFieldRid);
		textFieldRid.setColumns(10);
		
		textFieldRname = new JTextField();
		textFieldRname.setColumns(10);
		textFieldRname.setBounds(124, 61, 187, 20);
		panel.add(textFieldRname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 91, 290, 244);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"REGION_ID", "REGION_NAME"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(177);
		scrollPane.setViewportView(table);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				textFieldRid.setText(defaultTableModel.getValueAt(selectedRow, 0).toString());
		        textFieldRname.setText(defaultTableModel.getValueAt(selectedRow, 1).toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String regionId = textFieldRid.getText();
				String regionName = textFieldRname.getText();
				
				try {
					pst = connection.prepareStatement("INSERT INTO regions "
							+ "VALUES(?,?)");
					pst.setString(1, regionId);
			        pst.setString(2, regionName);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Added");
					
					textFieldRid.setText("");
					textFieldRname.setText("");
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String regionId = textFieldRid.getText();
				String regionName = textFieldRname.getText();
				
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String idInDb = defaultTableModel.getValueAt(selectedRow, 0).toString();
				
				try {
					pst = connection.prepareStatement("UPDATE regions SET "
			        		+ "region_id = ?,"
			        		+ "region_name = ?"
			        		+ " WHERE region_id = ?");
					pst.setString(1, regionId);
			        pst.setString(2, regionName);
			        pst.setString(3, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Edited");

					
					textFieldRid.setText("");
					textFieldRname.setText("");

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
					pst = connection.prepareStatement("DELETE FROM regions WHERE region_id = ?");
			        pst.setString(1, idInDb);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					textFieldRid.setText("");
					textFieldRname.setText("");

					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
	}
}
