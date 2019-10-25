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
	
	protected IConsole _console;
	
	// ********************************************************************* constructors
	/**
	 * Constructeur s'inscrivant aurpès du modèle comme observateur
	 * 
	 * @param controller Controller : Le controller de la vue
	 * @param model Model : Le modèle
	 * @param console IConsole : La console d'affichage
	 */
	public View(Controller controller, Model model, IConsole console)
	{
		_model = model;
		_controller = controller;
		_console = console;
		
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
		_console.displayLine(message);
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
	 * @param entryMode EntryMode : type d'entrée attendue pour le mode défenseur ou attaquant
	 * @param tryNumber int : Numéro de la tentative pour affichage
	 * @return String : l'entrée
	 */
	public abstract String queryEntry(EntryMode entryMode, int tryNumber);
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
