package com.smilesnake.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.smilesnake.controller.Database;
import com.smilesnake.model.UserClass;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Damitha_Nuwan
 *
 */
public class RegisterUi extends JFrame {
	
	/**
	 * Register UI
	 * 
	 */
	private static final long serialVersionUID = -1115260145024963420L;
	private JPanel contentPane;
	public RegisterUi() {
		super("SmileSnake-Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBackground(new Color(39, 39, 39));
		setBounds(100, 100, 700, 400);
		setLocationRelativeTo(null);	//open window on center
		
		//Panel Properties
		contentPane = new JPanel();
		contentPane.setLayout(null);	//unlock layout grid
		contentPane.setBackground(new Color(39, 39, 39));
		setContentPane(contentPane);
			
		//Register Topic Properties
		JLabel RegisterTopic = new JLabel("Register");
		RegisterTopic.setHorizontalAlignment(SwingConstants.CENTER);
		RegisterTopic.setHorizontalTextPosition(SwingConstants.CENTER);
		RegisterTopic.setBounds(260, 11, 151, 70);
		RegisterTopic.setFont(new Font("Tahoma", Font.PLAIN, 29));
		RegisterTopic.setForeground(new Color(255, 255, 128));
		contentPane.add(RegisterTopic);
		
		//User name label
		JLabel Username = new JLabel(" Username ");
		labelsProperties(Username);
		Username.setBounds(91, 92, 151, 31);
		contentPane.add(Username);
		
		//get User name from user enter text field
		JTextField textGetUsername = new JTextField(40);
		textGetUsername.setBounds(322, 134, 230, 31);
		contentPane.add(textGetUsername);
		
		//Email label
		JLabel Email = new JLabel(" Email ");
		labelsProperties(Email);
		Email.setBounds(91, 134, 151, 31);
		contentPane.add(Email);
		
		//get Email from user enter text field
		JTextField textGetUserEmail = new JTextField(40);
		textGetUserEmail.setBounds(322, 92, 230, 31);
		contentPane.add(textGetUserEmail);
		
		//Password label
		JLabel Password = new JLabel(" Password ");
		labelsProperties(Password);
		Password.setBounds(91, 176, 151, 31);
		contentPane.add(Password);
		
		//get Password from user enter Password field
		JPasswordField textGetUserPassword = new JPasswordField(40);
		textGetUserPassword.setBounds(322, 176, 230, 31);
		contentPane.add(textGetUserPassword);
		
		//Confirm Password label Properties
		JLabel confirmPassword = new JLabel(" Confirm Password ");
		labelsProperties(confirmPassword);
		
		confirmPassword.setBounds(91, 218, 160, 31);
		contentPane.add(confirmPassword);
		
		//get Confirm Password from user enter Password field
		JPasswordField textGetUserConfirmPassword = new JPasswordField(40);
		textGetUserConfirmPassword.setBounds(322, 218, 230, 31);
		contentPane.add(textGetUserConfirmPassword);
		
		//Register Button
		JButton LoginButton = new JButton("Register");
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LoginButton.setForeground(new Color(0, 0, 0));
		LoginButton.setBackground(new Color(192, 192, 192));
		LoginButton.setBounds(229, 276, 182, 40);
		LoginButton.addActionListener(new ActionListener() {
		
		//add user's detail to database
		public void actionPerformed(ActionEvent e) {
			String username = textGetUsername.getText();
			String email = textGetUserEmail.getText();
			String password = String.valueOf(textGetUserPassword.getPassword());
			String confirmpsaaword = String.valueOf(textGetUserConfirmPassword.getPassword());
			if(password.equals(confirmpsaaword)) {
				UserClass newUsers = new UserClass(username, email, password);
				try{
					java.sql.Statement rege = Database.myConnection().createStatement();
					if ( newUsers.getUsername().equals(username) && newUsers.getEmail().equals(email) && newUsers.getPassword().equals(password) ) {
						rege.executeUpdate("INSERT INTO `users`( username, email, password ) VALUES "
								+ "('"+username+"','"+email+"','"+password+"')");
						JOptionPane.showMessageDialog(rootPane, "You Account Created Successful");
						System.out.println("You Account Created Successful");
						new LoginUi().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Invalid Username or Email or Password.");
					}
					}catch (Exception e1) {
						System.out.println(e);
					}
				} else {
					JOptionPane.showMessageDialog(rootPane, "Password and Confirm Password Not Equal");
				}
			}
		});
		contentPane.add(LoginButton);
		
		//LoginLable
		JLabel LoginLable = new JLabel("I have A Account. Login");
		LoginLable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginUi().setVisible(true);
				dispose();
			}
		});
		LoginLable.setForeground(new Color(255, 255, 255));
		LoginLable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LoginLable.setBounds(500, 313, 145, 22);
		contentPane.add(LoginLable);
		}
	
	//Label Properties
	private void labelsProperties(JLabel lableName) {
		lableName.setHorizontalTextPosition(SwingConstants.CENTER);
		lableName.setHorizontalAlignment(SwingConstants.CENTER);
		lableName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lableName.setForeground(new Color(255, 255, 255));
	}
}