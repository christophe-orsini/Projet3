/**
 * 
 */
package com.openclassrooms.escapegame;

import com.openclassrooms.escapegame.controller.*;

/**
 * Classe principale du jeu EscapeGame
 * @author C.ORSINI
 */
public class Application
{
	@SuppressWarnings("javadoc")
	public Application()
	{
		// creation du controleur principal
		MainController mainController = new MainController();
		
		// execution 
		mainController.run();
	}
	@SuppressWarnings("javadoc")
	public static void main(String[] args)
	{
		new Application();
	}
}
