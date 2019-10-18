package com.openclassrooms.escapegame.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;
import com.openclassrooms.escapegame.model.Model;
import com.openclassrooms.escapegame.model.ModelState;
import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.view.View;

/**
 * Classe abstraite de gestion des controleurs
 * @author C.ORSINI
 *
 */
public abstract class Controller implements Observer
{
	protected Model _model;
	protected View _view;
	protected ModelState _modelState; // etat du modele
	protected boolean _win; // flag de victoire
	
	// ********************************************************************* constructors
	/**
	 * Constructeur s'inscrivant comme observateur du modèle passé en paramètre
	 * 
	 * @param model Model : une instance de classe Modele ou dérivée
	 */
	public Controller(Model model)
	{
		_model = model;
		_model.addObserver(this); // abonnement aupres du modele
		// creer la vue dans la sous-classe
	}
	
	// ******************************************************************** methods
	public void update(Observable o, Object arg)
	{
		if (o instanceof Model)
		{
			_modelState = (ModelState)arg;
			_win = ((ModelState)arg).isIWin() || ((ModelState)arg).isYouWin();
		}
	}
	/**
	 * Méthode appelée par la vue pour vérifier qu'une entrée au clavier correspond au pattern et est de la bonne longueur
	 * 
	 * @param pattern String : Le pattern sous forme de regex
	 * @param entry String : l'entrée au clavier
	 * @return boolean : true si l'entrée est valide sinon false
	 */
	protected boolean checkEntry(String pattern, String entry)
	{
		if (entry.length() != AppConfig.getInstance().getNbDigits())
		{
			return false;
		}
		
		return Pattern.matches(pattern, entry);
	}
	/**
	 * Méthode qui contient le code à exécuter
	 */
	public abstract void run();
}
