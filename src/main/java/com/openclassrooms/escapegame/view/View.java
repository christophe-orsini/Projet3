package com.openclassrooms.escapegame.view;

import java.util.Observable;
import java.util.Observer;
import com.openclassrooms.escapegame.controller.Controller;
import com.openclassrooms.escapegame.model.ChallengerModel;
import com.openclassrooms.escapegame.model.Model;
import com.openclassrooms.escapegame.model.ModelState;

/**
 * Classe abstraite de gestion des vues
 * @author C.ORSINI
 *
 */
public abstract class View implements Observer
{
	protected Model _model;
	protected Controller _controller;
	protected ModelState _modelState;
	
	// ********************************************************************* constructors
	public View(Controller controller, Model model)
	{
		_model = model;
		_controller = controller;
		_model.addObserver(this);
	}
	
	// ******************************************************************** methods
	/**
	 * Affiche un message a l'ecran
	 * @param message String : le message
	 */
	public void displayError(String message)
	{
		System.out.println(message);
	}
	public void update(Observable o, Object arg)
	{
		if (o instanceof Model)
		{
		_modelState = (ModelState)arg;
		}
	}
	public abstract void displayInstructions();
	public abstract String queryEntry(int tryNumber);
	public abstract void displayResult();
	public abstract void displayWin(int nbTries);
	public abstract void displayLost();
}
