package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.Model;
import com.openclassrooms.escapegame.view.View;

/**
 * Classe abstraite du pattern MVC pour le controleur
 * @author C.ORSINI
 *
 */
public abstract class Controller
{
	protected Model model;
	protected View view;
	
	// ************************************************************ methods
	/**
	 * Methode appelle dans le constructeur pour lancer une action
	 */
	public abstract void run();
}
