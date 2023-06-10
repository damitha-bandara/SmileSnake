package com.smilesnake.controller;

import java.awt.EventQueue;

import com.smilesnake.view.*;

/**
 * @author Damitha_Nuwan
 *
 */
public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUi frame = new LoginUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
