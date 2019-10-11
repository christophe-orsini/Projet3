/**
 * 
 */
package com.openclassrooms.escapegame;

import com.openclassrooms.escapegame.controller.Controller;
import com.openclassrooms.escapegame.controller.MainController;

/**
 * @author C.ORSINI
 * @version 0.0.0
 */
public class Application
{
	@SuppressWarnings("javadoc")
	public static void main(String[] args)
	{
		Controller mainController = new MainController();
		mainController.run();
	}
}
