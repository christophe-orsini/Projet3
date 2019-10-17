package com.openclassrooms.escapegame.view;

import java.util.Observable;
import java.util.Observer;
import com.openclassrooms.escapegame.controller.Controller;
import com.openclassrooms.escapegame.model.Model;
import com.openclassrooms.escapegame.model.ModelState;

/**
 * Classe abstraite de gestion des vues
 * 
 * @author C.ORSINI
 *
 */
public abstract class View implements Observer
{
	protected Model _model;
	protected Controller _controller;
	protected ModelState _modelState;
	
	// ********************************************************************* constructors
	/**
	 * Constructeur s'inscrivant aurpès du modèle comme observateur
	 * 
	 * @param controller Controller : Le controller de la vue
	 * @param model Model : Le modèle
	 */
	public View(Controller controller, Model model)
	{
		_model = model;
		_controller = controller;
		_model.addObserver(this);
	}
	
	// ******************************************************************** methods
	/**
	 * Affiche un message à l'écran
	 * 
	 * @param message String : le message à afficher
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
	/**
	 * Permet l'affichage des instructions
	 */
	public abstract void displayInstructions();
	/**
	 * Demande une entrée en affichant le numéro de la tentative
	 * 
	 * @param tryNumber int : Numéro de la tentative pour affichage
	 * @return String : l'entrée
	 */
	public abstract String queryEntry(int tryNumber);
	/**
	 * Affiche à l'écran le résultat d'un traitement par le modèle
	 */
	public abstract void displayResult();
	/**
	 * Affiche le message gagné
	 * 
	 * @param nbTries int : le nombre d'essais pour gagner
	 */
	public abstract void displayWin(int nbTries);
	/**
	 * Affiche le message perdu
	 */
	public abstract void displayLost();
}
