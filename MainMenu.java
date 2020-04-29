package com.pack;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    
	    frame.setVisible(true);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JButton btnCourses = new JButton("COURSES");
		btnCourses.setBounds(70, 65, 174, 40);
		frame.getContentPane().add(btnCourses);
		
		JButton btnFaculties = new JButton("FACULTIES");
		btnFaculties.setBounds(70, 127, 174, 40);
		frame.getContentPane().add(btnFaculties);
		
		JButton btnRegions = new JButton("REGIONS");
		btnRegions.setBounds(70, 192, 174, 40);
		frame.getContentPane().add(btnRegions);
		
		JButton btnSpeacialties = new JButton("SPEACIALITIES");
		btnSpeacialties.setBounds(70, 251, 174, 40);
		frame.getContentPane().add(btnSpeacialties);
		
		JButton btnStudents = new JButton("STUDENTS");
		btnStudents.setBounds(357, 65, 174, 40);
		frame.getContentPane().add(btnStudents);
		
		JButton btnStudentsCourses = new JButton("STUDENTS COURSES");
		btnStudentsCourses.setBounds(357, 127, 174, 40);
		frame.getContentPane().add(btnStudentsCourses);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBounds(357, 251, 174, 40);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(88, 11, 441, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnStudentsInformation = new JButton("STUDENTS INFORMATION");
		btnStudentsInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SearchStudent searchStudent = new SearchStudent();
			}
		});
		btnStudentsInformation.setBounds(357, 192, 174, 40);
		frame.getContentPane().add(btnStudentsInformation);
		
		btnCourses.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Courses courses = new Courses();
				
			}
		});
		btnFaculties.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Faculties faculties = new Faculties();
				
			}
		});
		btnRegions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Regions regions = new Regions();
				
			}
		});
		btnSpeacialties.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Speciality speciality = new Speciality();
				
			}
		});
		btnStudents.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Students students = new Students();
				
			}
		});
		btnStudentsCourses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				StudentsCourses studentsCourses = new StudentsCourses();
			}
		});
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				
			}
		});
	}
}
