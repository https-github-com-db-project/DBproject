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
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
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
import javax.swing.JRadioButton;

public class Students {

	private JFrame frame;
	Connection connection;
	PreparedStatement pst;
	private JTextField textFieldSid;
	private JTextField textFieldSname;
	private JTextField textFieldSsurn;
	private JTextField textFieldDay;
	private JTextField textFieldYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Students window = new Students();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
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
			
			pst = connection.prepareStatement("SELECT * FROM students");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getLong("student_id"));
	        		v2.add(rs.getString("student_name"));
	        		v2.add(rs.getString("student_surname"));
	        		v2.add(rs.getDate("born"));
	        		v2.add(rs.getString("gender"));
	        		v2.add(rs.getString("region_id"));
	        		v2.add(rs.getString("faculty_id"));
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
	public Students() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1100, 620);
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
	JComboBox comboBoxFacId;
	private JTable table;
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
		
		JLabel lblNewLabel = new JLabel("Students");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(26, 46, 292, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 108, 423, 452);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCId = new JLabel("STUDENT_ID");
		lblCId.setBounds(10, 11, 99, 14);
		panel.add(lblCId);
		
		JLabel lblCname = new JLabel("STUDENT_NAME");
		lblCname.setBounds(10, 59, 128, 14);
		panel.add(lblCname);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(111, 404, 89, 23);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(210, 404, 89, 23);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(309, 404, 89, 23);
		panel.add(btnDelete);
		
		JLabel lblStudentsurname = new JLabel("STUDENT_SURNAME");
		lblStudentsurname.setBounds(10, 106, 128, 14);
		panel.add(lblStudentsurname);
		
		JLabel lblBorn = new JLabel("BORN");
		lblBorn.setBounds(10, 155, 99, 14);
		panel.add(lblBorn);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setBounds(10, 201, 99, 14);
		panel.add(lblGender);
		
		JLabel lblRegionid = new JLabel("REGION_NAME");
		lblRegionid.setBounds(10, 248, 128, 14);
		panel.add(lblRegionid);
		
		JLabel lblFacultyName = new JLabel("FACULTY_NAME");
		lblFacultyName.setBounds(10, 295, 128, 14);
		panel.add(lblFacultyName);
		
		JLabel lblSpecialityTitle = new JLabel("SPECIALITY_TITLE");
		lblSpecialityTitle.setBounds(10, 342, 128, 14);
		panel.add(lblSpecialityTitle);
		
		textFieldSid = new JTextField();
		textFieldSid.setBounds(183, 8, 192, 20);
		panel.add(textFieldSid);
		textFieldSid.setColumns(10);
		
		textFieldSname = new JTextField();
		textFieldSname.setColumns(10);
		textFieldSname.setBounds(183, 56, 192, 20);
		panel.add(textFieldSname);
		
		textFieldSsurn = new JTextField();
		textFieldSsurn.setColumns(10);
		textFieldSsurn.setBounds(183, 103, 192, 20);
		panel.add(textFieldSsurn);
		
		JComboBox comboBox = new JComboBox();
		String[] months = new String[] {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
		comboBox.setModel(new DefaultComboBoxModel(months));
		comboBox.setBounds(183, 151, 56, 22);
		comboBox.setSelectedItem(months[0]);
		panel.add(comboBox);
		
		textFieldDay = new JTextField();
		textFieldDay.setText("Day");
		textFieldDay.setBounds(254, 152, 48, 20);
		panel.add(textFieldDay);
		textFieldDay.setColumns(10);
		
		textFieldYear = new JTextField();
		textFieldYear.setText("Year");
		textFieldYear.setColumns(10);
		textFieldYear.setBounds(312, 152, 64, 20);
		panel.add(textFieldYear);
		
		JRadioButton rdButtonMale = new JRadioButton("Male");
		rdButtonMale.setBounds(183, 197, 72, 23);
		panel.add(rdButtonMale);
		
		JRadioButton radioButtonFemale = new JRadioButton("Female");
		radioButtonFemale.setBounds(259, 197, 72, 23);
		panel.add(radioButtonFemale);
		
		JComboBox comboBoxRegName = new JComboBox();
		String[] regionIds = regionNameArray();
		comboBoxRegName.setModel(new DefaultComboBoxModel(regionIds));
		comboBoxRegName.setBounds(183, 244, 215, 22);
		panel.add(comboBoxRegName);
		
		comboBoxFacId = new JComboBox();
		String[] facultyIds = facultyNameArray();
		comboBoxFacId.setModel(new DefaultComboBoxModel(facultyIds));
		comboBoxFacId.setBounds(183, 291, 215, 22);
		panel.add(comboBoxFacId);
		
		JComboBox comboBoxSpecId = new JComboBox();
		String[] specTitles = specTitleArray();
		comboBoxSpecId.setModel(new DefaultComboBoxModel(specTitles));
		comboBoxSpecId.setBounds(183, 338, 215, 22);
		panel.add(comboBoxSpecId);
		
		JLabel lblNewLabel_1 = new JLabel("Day");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1.setBounds(254, 134, 31, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Year");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_2.setBounds(312, 134, 48, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Month");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_3.setBounds(183, 134, 48, 14);
		panel.add(lblNewLabel_3);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(474, 108, 587, 452);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"STUDENT_ID", "STUDENT_NAME", "STUDENT_SURNAME", "BORN", "GENDER", "REGION_ID", "FACULTY_ID", "SPECIALITY_ID"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		textFieldDay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldDay.getText().toString().equals("Day"))
					textFieldDay.setText("");
			}
		});
		textFieldYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldYear.getText().toString().equals("Year"))
					textFieldYear.setText("");
			}
		});
		rdButtonMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdButtonMale.setSelected(true);
				radioButtonFemale.setSelected(false);
			}
		});
		radioButtonFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdButtonMale.setSelected(false);
				radioButtonFemale.setSelected(true);
			}
		});
		
		comboBoxFacId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] specTitles = specTitleArray();
				comboBoxSpecId.setModel(new DefaultComboBoxModel(specTitles));
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				textFieldSid.setText(defaultTableModel.getValueAt(selectedRow, 0).toString());
		        textFieldSname.setText(defaultTableModel.getValueAt(selectedRow, 1).toString());
		        textFieldSsurn.setText(defaultTableModel.getValueAt(selectedRow, 2).toString());
		        if (defaultTableModel.getValueAt(selectedRow, 3) instanceof Date) {
		        	Date date = (Date) defaultTableModel.getValueAt(selectedRow, 3);
		        	Calendar calendar = Calendar.getInstance();
		        	calendar.setTime(date);
		        	int year = calendar.get(Calendar.YEAR);
		        	int day = calendar.get(Calendar.DAY_OF_MONTH);
		        	int month = calendar.get(Calendar.MONTH);
		        	comboBox.setSelectedIndex(month);
		        	textFieldDay.setText(day + "");
		        	textFieldYear.setText(year + "");
		        }
		        if (defaultTableModel.getValueAt(selectedRow, 4).toString().equals("Male")) {
		        	rdButtonMale.setSelected(true);
		        	radioButtonFemale.setSelected(false);
		        } else {
		        	rdButtonMale.setSelected(false);
		        	radioButtonFemale.setSelected(true);
		        }
		        try {
		        	
		        	String regionId = (String) defaultTableModel.getValueAt(selectedRow, 5);
		        	pst = connection.prepareStatement("SELECT region_name FROM regions WHERE region_id = ?");
		        	pst.setString(1, regionId);
		        	ResultSet rs = pst.executeQuery();
		        	if (rs.next())
		        		comboBoxRegName.setSelectedItem(rs.getString("region_name"));
		        	
		        	String facultyId = defaultTableModel.getValueAt(selectedRow, 6).toString();
		        	pst = connection.prepareStatement("SELECT faculty_name FROM faculties WHERE faculty_id = ?");
		        	pst.setString(1, facultyId);
		        	rs = pst.executeQuery();
		        	if (rs.next())
		        		comboBoxFacId.setSelectedItem(rs.getString("faculty_name"));
		        	
		        	String specId = defaultTableModel.getValueAt(selectedRow, 7).toString();
		        	pst = connection.prepareStatement("SELECT speciality_title FROM specialities WHERE speciality_id = ?");
		        	pst.setString(1, specId);
		        	rs = pst.executeQuery();
		        	if (rs.next())	
		        		comboBoxSpecId.setSelectedItem(rs.getString("speciality_title"));
		        	
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        }
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String stuId = textFieldSid.getText();
					String stuName = textFieldSname.getText();
					String stuSurname = textFieldSsurn.getText();
					///
					int month = comboBox.getSelectedIndex();
					int day = Integer.parseInt(textFieldDay.getText());
					int year= Integer.parseInt(textFieldYear.getText());
					Date born = new GregorianCalendar(year, month, day).getTime();
					///
					String gender = rdButtonMale.isSelected() ? "Male" : "Female";
					///
					String regionName = (String) comboBoxRegName.getSelectedItem();
					pst  = connection.prepareStatement("SELECT region_id FROM regions WHERE region_name = ?");
					pst.setString(1, regionName);
					ResultSet rs = pst.executeQuery();
					rs.next();
					int regionId = rs.getInt("region_id");
					///
					String facultyName = (String) comboBoxFacId.getSelectedItem();
					pst = connection.prepareStatement("SELECT faculty_id FROM faculties WHERE faculty_name = ?");
					pst.setString(1, facultyName);
					rs = pst.executeQuery();
					rs.next();
					String facultyId = rs.getString("faculty_id");
					///
					String specTitle = (String) comboBoxSpecId.getSelectedItem();
					pst = connection.prepareStatement("SELECT speciality_id FROM specialities WHERE speciality_title = ?");
					pst.setString(1, specTitle);
					rs = pst.executeQuery();
					rs.next();
					String specId = rs.getString("speciality_id");
					
					
					pst = connection.prepareStatement("INSERT INTO students "
							+ "VALUES(?,?,?,?,?,?,?,?)");
					pst.setLong(1, Integer.parseInt(stuId));
			        pst.setString(2, stuName);
			        pst.setString(3, stuSurname);
			        pst.setDate(4, new java.sql.Date(born.getTime()));
			        pst.setString(5, gender);
			        pst.setInt(6, regionId);
			        pst.setString(7, facultyId);
			        pst.setString(8, specId);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Added");
					
					textFieldSid.setText("");
					textFieldSname.setText("");
					textFieldSsurn.setText("");
					comboBox.setSelectedIndex(0);
					textFieldDay.setText("Day");
					textFieldYear.setText("Year");
					rdButtonMale.setSelected(false);
					radioButtonFemale.setSelected(false);
					comboBoxRegName.setSelectedIndex(0);
					comboBoxFacId.setModel(new DefaultComboBoxModel(facultyIds));
					comboBoxSpecId.setModel(new DefaultComboBoxModel(specTitles));
//					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String stuIdDB = defaultTableModel.getValueAt(selectedRow, 0).toString();
				
				try {
					String stuId = textFieldSid.getText();
					String stuName = textFieldSname.getText();
					String stuSurname = textFieldSsurn.getText();
					///
					int month = comboBox.getSelectedIndex();
					int day = Integer.parseInt(textFieldDay.getText());
					int year= Integer.parseInt(textFieldYear.getText());
					Date born = new GregorianCalendar(year, month, day).getTime();
					///
					String gender = rdButtonMale.isSelected() ? "Male" : "Female";
					///
					String regionName = (String) comboBoxRegName.getSelectedItem();
					pst  = connection.prepareStatement("SELECT region_id FROM regions WHERE region_name = ?");
					pst.setString(1, regionName);
					ResultSet rs = pst.executeQuery();
					rs.next();
					int regionId = rs.getInt("region_id");
					///
					String facultyName = (String) comboBoxFacId.getSelectedItem();
					pst = connection.prepareStatement("SELECT faculty_id FROM faculties WHERE faculty_name = ?");
					pst.setString(1, facultyName);
					rs = pst.executeQuery();
					rs.next();
					String facultyId = rs.getString("faculty_id");
					///
					String specTitle = (String) comboBoxSpecId.getSelectedItem();
					pst = connection.prepareStatement("SELECT speciality_id FROM specialities WHERE speciality_title = ?");
					pst.setString(1, specTitle);
					rs = pst.executeQuery();
					rs.next();
					String specId = rs.getString("speciality_id");
					
					
					pst = connection.prepareStatement("UPDATE students SET "
			        		+ "student_id = ?,"
			        		+ "student_name = ?,"
			        		+ "student_surname = ?,"
			        		+ "born = ?,"
			        		+ "gender = ?,"
			        		+ "region_id = ?,"
			        		+ "faculty_id = ?,"
			        		+ "speciality_id = ?"
			        		+ " WHERE student_id = ?");
					pst.setString(1, stuId);
			        pst.setString(2, stuName);
			        pst.setString(3, stuSurname);
			        pst.setDate(4, new java.sql.Date(born.getTime()));
			        pst.setString(5, gender);
			        pst.setInt(6, regionId);
			        pst.setString(7, facultyId);
			        pst.setString(8, specId);
			        pst.setString(9, stuIdDB);
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Record Edited");

					textFieldSid.setText("");
					textFieldSname.setText("");
					textFieldSsurn.setText("");
					comboBox.setSelectedIndex(0);
					textFieldDay.setText("Day");
					textFieldYear.setText("Year");
					rdButtonMale.setSelected(false);
					radioButtonFemale.setSelected(false);
					comboBoxRegName.setSelectedIndex(0);
					comboBoxFacId.setModel(new DefaultComboBoxModel(facultyIds));
					comboBoxSpecId.setModel(new DefaultComboBoxModel(specTitles));
					
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
				String stuId = textFieldSid.getText();
				
				try {
					pst = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
			        pst.setInt(1, Integer.parseInt(stuId));
			        pst.executeUpdate();
					
					updateTable();
					JOptionPane.showMessageDialog(null, "Row Deleted");
					
					textFieldSid.setText("");
					textFieldSname.setText("");
					textFieldSsurn.setText("");
					comboBox.setSelectedIndex(0);
					textFieldDay.setText("Day");
					textFieldYear.setText("Year");
					rdButtonMale.setSelected(false);
					radioButtonFemale.setSelected(false);
					comboBoxRegName.setSelectedIndex(0);
					comboBoxFacId.setModel(new DefaultComboBoxModel(facultyIds));
					comboBoxSpecId.setModel(new DefaultComboBoxModel(specTitles));
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
	}
	
	private String[] regionNameArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT region_name FROM regions");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("region_name"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
	}
	
	private String[] facultyNameArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT faculty_name FROM faculties");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("faculty_name"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
	}
	
	private String[] specTitleArray(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT speciality_title FROM"
					+ "(SELECT speciality_title, faculty_name "
					+ "FROM faculties "
					+ "NATURAL JOIN specialities)"
					+ "WHERE faculty_name = ?");
			pst.setString(1, comboBoxFacId.getSelectedItem().toString());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("speciality_title"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] result = list.toArray(new String[list.size()]);
		return result;
		
	}
}
