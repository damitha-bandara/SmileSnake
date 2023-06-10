package com.smilesnake.view;

import javax.swing.JFrame;

import com.smilesnake.model.SnakeGamePanelModel;

/**
 * @author Damitha_Nuwan
 *
 */
public class SnakeGame extends JFrame{
	
	/**
	 * Snake Game JFram UI
	 */
	private static final long serialVersionUID = 7287486741587627906L;

	public SnakeGame(){
		this.add(new SnakeGamePanelModel());
		this.setTitle("SmileSnake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		SnakeGame newSankeGame = new SnakeGame();
		newSankeGame.setVisible(true);
	}
}