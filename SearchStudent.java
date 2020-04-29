package com.pack;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class SearchStudent {

	private JFrame frame;
	private JTextField textFieldIname;
	private JTextField textFieldIsurname;
	private JTextField textFieldIid;
	private JTable table;
	
	Connection connection;
	PreparedStatement pst;
	DefaultTableModel defaultTableModel;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SearchStudent window = new SearchStudent();
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
			
			pst = connection.prepareStatement("SELECT student_name, student_surname, student_id FROM students");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numOfRows = rsmd.getColumnCount();
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			
			while (rs.next()) {
				Vector v2 = new Vector();
	        	
	        	for (int a = 1; a <= numOfRows; a++) {
	        		v2.add(rs.getString("student_name"));
	        		v2.add(rs.getString("student_surname"));
	        		v2.add(rs.getLong("student_id"));
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
	public SearchStudent() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 598);
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
		lblBackLabel.setForeground(Color.RED);
		lblBackLabel.setBounds(10, 11, 48, 14);
		frame.getContentPane().add(lblBackLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Information");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(20, 45, 229, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 101, 316, 192);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Search by");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 11, 95, 22);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(10, 42, 82, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 76, 82, 14);
		panel.add(lblSurname);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 114, 82, 14);
		panel.add(lblId);
		
		textFieldIname = new JTextField();
		textFieldIname.setText("");
		textFieldIname.setBounds(112, 39, 157, 20);
		panel.add(textFieldIname);
		textFieldIname.setColumns(10);
		
		textFieldIsurname = new JTextField();
		textFieldIsurname.setText("");
		textFieldIsurname.setColumns(10);
		textFieldIsurname.setBounds(112, 73, 157, 20);
		panel.add(textFieldIsurname);
		
		textFieldIid = new JTextField();
		textFieldIid.setText("");
		textFieldIid.setColumns(10);
		textFieldIid.setBounds(112, 111, 157, 20);
		panel.add(textFieldIid);
		
		JButton btnSeacrh = new JButton("Search");
		btnSeacrh.setBounds(180, 158, 89, 23);
		panel.add(btnSeacrh);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.setBounds(81, 158, 89, 23);
		panel.add(btnShowAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 316, 317, 183);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"NAME", "SURNAME", "ID"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBounds(358, 25, 308, 504);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setBounds(10, 11, 92, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblSurname_1 = new JLabel("Surname");
		lblSurname_1.setBounds(10, 49, 92, 14);
		panel_1.add(lblSurname_1);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setBounds(10, 86, 92, 14);
		panel_1.add(lblId_1);
		
		JLabel lblFrom = new JLabel("Gender");
		lblFrom.setBounds(10, 129, 92, 14);
		panel_1.add(lblFrom);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(10, 175, 92, 14);
		panel_1.add(lblBirthDate);
		
		JLabel lblFrom_1 = new JLabel("From");
		lblFrom_1.setBounds(10, 227, 92, 14);
		panel_1.add(lblFrom_1);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(10, 280, 92, 14);
		panel_1.add(lblFaculty);
		
		JLabel lblSpeciality = new JLabel("Speciality");
		lblSpeciality.setBounds(10, 328, 92, 14);
		panel_1.add(lblSpeciality);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(10, 377, 92, 14);
		panel_1.add(lblCourses);
		
		JLabel lblName = new JLabel("");
		lblName.setForeground(new Color(165, 42, 42));
		lblName.setBounds(112, 11, 165, 14);
		panel_1.add(lblName);
		
		JLabel lblSur = new JLabel("");
		lblSur.setForeground(new Color(165, 42, 42));
		lblSur.setBounds(112, 49, 165, 14);
		panel_1.add(lblSur);
		
		JLabel lblid = new JLabel("");
		lblid.setForeground(new Color(165, 42, 42));
		lblid.setBounds(112, 86, 165, 14);
		panel_1.add(lblid);
		
		JLabel lblGender = new JLabel("");
		lblGender.setForeground(new Color(165, 42, 42));
		lblGender.setBounds(112, 129, 165, 14);
		panel_1.add(lblGender);
		
		JLabel lblBorn = new JLabel("");
		lblBorn.setForeground(new Color(165, 42, 42));
		lblBorn.setBounds(112, 175, 165, 14);
		panel_1.add(lblBorn);
		
		JLabel lblRegion = new JLabel("");
		lblRegion.setForeground(new Color(165, 42, 42));
		lblRegion.setBounds(112, 227, 165, 14);
		panel_1.add(lblRegion);
		
		JLabel lblFac = new JLabel("");
		lblFac.setForeground(new Color(165, 42, 42));
		lblFac.setBounds(112, 280, 165, 14);
		panel_1.add(lblFac);
		
		JLabel lblSpec = new JLabel("");
		lblSpec.setForeground(new Color(165, 42, 42));
		lblSpec.setBounds(112, 328, 165, 14);
		panel_1.add(lblSpec);
		
		JLabel lblCou = new JLabel("");
		lblCou.setForeground(new Color(165, 42, 42));
		lblCou.setBounds(112, 377, 165, 14);
		panel_1.add(lblCou);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(112, 370, 186, 116);
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel labelForCourse1 = new JLabel("");
		labelForCourse1.setForeground(new Color(165, 42, 42));
		labelForCourse1.setBorder(new EmptyBorder(5,5,5,5));
		panel_2.add(labelForCourse1);
		JLabel labelForCourse2 = new JLabel("");
		labelForCourse2.setForeground(new Color(165, 42, 42));
		labelForCourse2.setBorder(new EmptyBorder(5,5,5,5));
		panel_2.add(labelForCourse2);
		JLabel labelForCourse3 = new JLabel("");
		labelForCourse3.setForeground(new Color(165, 42, 42));
		labelForCourse3.setBorder(new EmptyBorder(5,5,5,5));
		panel_2.add(labelForCourse3);
		JLabel labelForCourse4 = new JLabel("");
		labelForCourse4.setForeground(new Color(165, 42, 42));
		labelForCourse4.setBorder(new EmptyBorder(5,5,5,5));
		panel_2.add(labelForCourse4);
		
		textFieldIname.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		textFieldIsurname.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				try {
					
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				defaultTableModel = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				String idInDB = defaultTableModel.getValueAt(selectedRow, 2).toString();
				
				try {
					
					pst = connection.prepareStatement("SELECT * FROM students WHERE student_id = ?");
					pst.setString(1, idInDB);
					rs = pst.executeQuery();
					while (rs.next()) {
						try {
							
							lblName.setText(rs.getString("student_name"));
							lblSur.setText(rs.getString("student_surname"));
							lblid.setText(rs.getString("student_id"));
							lblBorn.setText(rs.getDate("born").toString());
							lblGender.setText(rs.getString("gender"));
							//
							String regionId = String.valueOf(rs.getInt("region_id"));
							Connection connection2 = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "db_final", "dbfinal");
							PreparedStatement pst2 = connection2.prepareStatement("SELECT region_name FROM regions WHERE region_id = ?");
							pst2.setString(1, regionId);
							ResultSet rs2 = pst2.executeQuery();
							rs2.next();
							lblRegion.setText(rs2.getString("region_name"));
							//
							String facId = rs.getString("faculty_id").toString();
							pst2 = connection2.prepareStatement("SELECT faculty_name FROM faculties WHERE faculty_id = ?");
							pst2.setString(1, facId);
							rs2 = pst2.executeQuery();
							rs2.next();
							lblFac.setText(rs2.getString("faculty_name"));
							//
							String specId = rs.getString("speciality_id").toString();
							pst2 = connection2.prepareStatement("SELECT speciality_title FROM specialities WHERE speciality_id = ?");
							pst2.setString(1, specId);
							rs2 = pst2.executeQuery();
							rs2.next();
							lblSpec.setText(rs2.getString("speciality_title"));
							//
							pst2 = connection2.prepareStatement("SELECT course_id FROM students_courses WHERE student_id = ?");
							pst2.setString(1, idInDB);
							rs2 = pst2.executeQuery();
							
							labelForCourse1.setText("");
							labelForCourse2.setText("");
							labelForCourse3.setText("");
							labelForCourse4.setText("");
							int i = 0;
							while (rs2.next()) {
								if (i == 0)
										labelForCourse1.setText(rs2.getString("course_id"));
								else if (i == 1)
										labelForCourse2.setText(rs2.getString("course_id"));
								else if (i == 2)
										labelForCourse3.setText(rs2.getString("course_id"));
								else if (i == 3)
										labelForCourse4.setText(rs2.getString("course_id"));
								i++;
								}
								
												
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSeacrh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String stuName = textFieldIname.getText();
				String stuSurname = textFieldIsurname.getText();
				String stuId = textFieldIid.getText();
				
				try {
					
					pst = connection.prepareStatement("SELECT student_name, student_surname, student_id FROM students "
							+ "WHERE student_name LIKE ?"
							+ "AND student_surname LIKE ?"
							+ "AND TO_CHAR(student_id) LIKE ?");
					pst.setString(1, stuName + "%");
					pst.setString(2, stuSurname + "%");
					pst.setString(3, stuId + "%");
					rs = pst.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int numOfRows = rsmd.getColumnCount();
					
					defaultTableModel = (DefaultTableModel) table.getModel();
					defaultTableModel.setRowCount(0);
					
					while (rs.next()) {
						Vector v2 = new Vector();
			        	
			        	for (int a = 1; a <= numOfRows; a++) {
			        		
			        		v2.add(rs.getString("student_name"));
			        		v2.add(rs.getString("student_surname"));
			        		v2.add(rs.getLong("student_id"));
			        	}
			        	
			        	defaultTableModel.addRow(v2);
					}
					textFieldIid.setText("");
					textFieldIname.setText("");
					textFieldIsurname.setText("");
					
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		
		btnShowAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateTable();
				
			}
		});
		
	}
}
