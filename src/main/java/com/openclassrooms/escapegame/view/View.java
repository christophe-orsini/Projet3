package com.openclassrooms.escapegame.view;

import java.util.Observer;
import com.openclassrooms.escapegame.controller.Controller;
import com.openclassrooms.escapegame.model.Model;

/**
 * Classe abstraite de gestion des vues
 * @author C.ORSINI
 *
 */
public abstract class View implements Observer
{
	protected Model _model;
	protected Controller _controller;
	
	public View(Controller controller, Model model)
	{
		_model = model;
		_controller = controller;
		_model.addObserver(this);
	}
	
	public abstract void displayInstructions();
	public abstract String queryEntry(int tryNumber);
	public abstract void displayError(String message);
	public abstract void displayResult();
	public abstract void displayWin(int nbTries);
	public abstract void displayLost();
}
