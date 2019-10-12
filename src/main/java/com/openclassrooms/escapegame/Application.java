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
	public static void main(String[] args)
	{
		MainController mainController = new MainController();
		
		mainController.run();
	}
}
