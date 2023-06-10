package com.smilesnake.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.smilesnake.model.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Damitha_Nuwan
 */
public class GameUi extends JFrame implements ActionListener {

	/**
	 * Game UI
	 */
	private static final long serialVersionUID = 1465843131870514498L;
	private JPanel SmileAPIGamePanel , TimeCownDownPanel , ShowResultPanel;
	static JLabel questArea = null;
	static GameEngine myGame = null;
	static BufferedImage currentGame = null;
	static JLabel TimeLabel;
	static ImageIcon SmileImage;
	private JLabel CorrectOrIncorrectLabel;
	
	/**
	 * Create the frame.
	 * Default player is null. 
	 */
	public GameUi() {
		super();
		initGame(null);
	}
	/**
	 * Use this to start GUI, e.g., after login.
	 * @param player
	 */
	public GameUi(String player) {
		super();
		initGame(player);
	}
	/**
	 * Initializes the game. 
	 * @param player
	 */
	private void initGame(String player) {
		setTitle("SmileSnake");
		setSize(710, 560);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		TimeCownDownPanel = new JPanel();
		TimeCownDownPanel.setBounds(0, 0, 700, 40);
		TimeCownDownPanel.setBackground(new Color(40, 39, 39));
		getContentPane().add(TimeCownDownPanel);
		
		SmileAPIGamePanel = new JPanel();
		SmileAPIGamePanel.setBounds(0, 40, 700, 450);
		SmileAPIGamePanel.setBackground(new Color(40, 39, 39));
		getContentPane().add(SmileAPIGamePanel);
		
		TimeLabel = new JLabel("Time Left : 100");
		TimeLabel.setForeground(Color.WHITE);
		TimeLabel.setFont( new Font("Ink Free",Font.BOLD, 29));
		TimeCountDownMethod(10);
		TimeCownDownPanel.add(TimeLabel);
		
		ShowResultPanel = new JPanel();
		ShowResultPanel.setBounds(0, 490, 700, 40);
		ShowResultPanel.setBackground(new Color(40, 39, 39));
		getContentPane().add(ShowResultPanel);
		
		CorrectOrIncorrectLabel= new JLabel("");
		CorrectOrIncorrectLabel.setFont( new Font("Ink Free",Font.BOLD, 17));
		CorrectOrIncorrectLabel.setForeground(Color.RED);
		ShowResultPanel.add(CorrectOrIncorrectLabel);
		
		//call smile game running function
		SmileGame(player);
	}
	//Smile API game show in 'SmileAPIGamePanel'
	private void SmileGame(String player) {
		myGame = new GameEngine(player);
		currentGame = myGame.nextGame();
		SmileImage = new ImageIcon(currentGame);
		
		questArea = new JLabel(SmileImage);
		questArea.setLocation(12, 33);
		SmileAPIGamePanel.add(questArea);
		questArea.setSize(698, 498);
				
		for (int i = 0; i < 10; i++) {
			JButton AnswerButton = new JButton(String.valueOf(i));
			SmileAPIGamePanel.add(AnswerButton);
			AnswerButton.addActionListener(this);
		}
		SmileAPIGamePanel.repaint();
	}
	//time count down method
	static void TimeCountDownMethod(int counter1){
		
		//A facility for threads to schedule tasks for future execution in a background thread				
		Timer timer = new Timer();
		//A task that can be scheduled for one-time or repeated execution by a Timer
		TimerTask task = new TimerTask() {
			int counter = 100;
			@Override
			public void run() {
				if(counter>0) {
					TimeLabel.setText("Time Left : " + Integer.toString(counter));	
					counter--;
				} 
				else {
					currentGame = myGame.nextGame(); 			
					SmileImage = new ImageIcon(currentGame);
					questArea.setIcon(SmileImage);
					TimeCountDownMethod(10);
					timer.cancel();
				}
			}		
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	/**
	 * Method that is called when a button has been pressed.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int solution = Integer.parseInt(e.getActionCommand());
		boolean correct = myGame.checkSolution(solution);
		if (correct) {
			CorrectOrIncorrectLabel.setText("Correct Answer");
			SnakeGame gameFrame = new SnakeGame();
			gameFrame.setVisible(true);
			dispose();
			
		} else { 
			CorrectOrIncorrectLabel.setText("Incorrect Answer");
		}
	}
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			GameUi frame = new GameUi();
//			frame.setVisible(true);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//	}
}
