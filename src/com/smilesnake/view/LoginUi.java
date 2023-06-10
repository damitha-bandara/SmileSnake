package com.smilesnake.view;

import javax.swing.*;

import com.smilesnake.controller.Database;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * @author Damitha_Nuwan
 *
 */
public class LoginUi extends JFrame {
	
	/**
	 * Login UI
	 * 
	 */
	private static final long serialVersionUID = -2897864984208185931L;
	private JPanel contentPane;
	public LoginUi() {
		super("SmileSnake-Login");
		setResizable(false);
		setBackground(new Color(39, 39, 39));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);	//open window on centered
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(40, 39, 39));
		setContentPane(contentPane);
		
		//Login Topic Properties
		JLabel LoginTopic = new JLabel("Login");
		LoginTopic.setHorizontalAlignment(SwingConstants.CENTER);
		LoginTopic.setHorizontalTextPosition(SwingConstants.CENTER);
		LoginTopic.setBounds(111, 10, 151, 50);
		LoginTopic.setFont(new Font("Tahoma", Font.PLAIN, 29));
		LoginTopic.setForeground(new Color(255, 255, 128));
		contentPane.add(LoginTopic);
		
		//User name label Properties
		JLabel Username = new JLabel(" Username ");
		Username.setHorizontalTextPosition(SwingConstants.RIGHT);
		Username.setHorizontalAlignment(SwingConstants.CENTER);
		Username.setBounds(111, 85, 151, 31);
		Username.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Username.setForeground(new Color(255, 255, 255));
		contentPane.add(Username);
		
		//get User name from user enter text field Properties
		JTextField textGetUsername = new JTextField(40);
		textGetUsername.setBounds(83, 118, 215, 27);
		contentPane.add(textGetUsername);
		
		//Password label Properties
		JLabel Password = new JLabel(" Password ");
		Password.setHorizontalTextPosition(SwingConstants.RIGHT);
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setBounds(111, 156, 151, 31);
		Password.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Password.setForeground(new Color(255, 255, 255));
		contentPane.add(Password);
		
		//get Password from user enter Password field Properties
		JPasswordField textGetPassword = new JPasswordField(40);
		textGetPassword.setBounds(83, 190, 215, 27);
		contentPane.add(textGetPassword);
		
		//Login Button Properties
		JButton LoginButton = new JButton("Login");
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LoginButton.setForeground(new Color(40, 0, 0));
		LoginButton.setBackground(new Color(192, 192, 192));
		LoginButton.setBounds(111, 246, 160, 29);
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs;
				PreparedStatement ps;
				String username = textGetUsername.getText();
				String password = String.valueOf(textGetPassword.getPassword());
				String query = "SELECT * FROM `users` WHERE `username` =? AND `password`=?";
				try {
					ps = Database.myConnection().prepareStatement(query);
					ps.setString(1, username);
					ps.setString(2, password);
					rs = ps.executeQuery();
					if(rs.next()) {
						GameUi theGame = new GameUi(username);
						theGame.setVisible(true); 
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect UserName Or Password", "Login Failed", 2);
						textGetUsername.setText("");
						textGetPassword.setText("");
					}
				} catch (Exception e2) {
					//System.out.println(e2);
				}
			}
		});
		contentPane.add(LoginButton);
		
		//NoAccount label Properties
		JLabel NoAccount = new JLabel("Don't Have Account? Register");
		NoAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterUi().setVisible(true);
				dispose();
			}
		});
		NoAccount.setHorizontalTextPosition(SwingConstants.RIGHT);
		NoAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		NoAccount.setBounds(166, 311, 185, 18);
		NoAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NoAccount.setForeground(new Color(255, 255, 255));
		contentPane.add(NoAccount);
	}
}
