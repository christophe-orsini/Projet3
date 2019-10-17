package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;
import com.openclassrooms.escapegame.view.*;

/**
 * Contrôleur du mode duel qui vérifie les entrées de la vue et transmet au modèle 
 * @author C.ORSINI
 *
 */
public class DualController extends Controller
{
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé.<br />
	 * Le constructeur crée la vue et demande au modèle de notifier ses changements
	 * 
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public DualController(Model model)
	{
		super(model);
		_view = new DualView(this, _model);
		_model.notifyState();
	}
	// *********************************************** methods
	@Override
	public void run()
	{
		// consignes
		_view.displayInstructions();
		
		// jeu
		int nbTours = 1; // nombres de tours de jeu
		while (!_win && nbTours <= AppConfig.getInstance().getNbTries())
		{
			String entry = _view.queryEntry(nbTours); // affiche la proposition et attend la reponse
			if (!checkEntry("^(-|\\+|=)*$", entry)) // verifie que la reponse comprend des symboles + - =  et est de la bonne longueur
			{
				_view.displayError("Veuillez entrer une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) SVP !");
				continue;
			}
			AppLog.getLogger().info("Proposition N°" + nbTours + " : " + _model.getProposed()+ " -> Reponse : " + entry);
			_model.manageEntry(entry); // passe la main au modele pour controler la reponse
			
			entry = _view.queryEntry(nbTours); // affiche la demande de proposition et attend la proposition
			if (!checkEntry("^[0-9]*$", entry)) // verifie que la proposition comprend des chiffres et est de la bonne longueur
			{
				_view.displayError("Veuillez entrer une réponse à " + AppConfig.getInstance().getNbDigits() + " symbole(s) SVP !");
				continue;
			}
			_model.manageEntry(entry); // passe la main au modele pour verifier la combinaison
			AppLog.getLogger().info("Essai N°" + nbTours + " combinaison proposée : " + _model.getSearched() +" -> réponse : " + _model.getResult());
			_view.displayResult();
			
			nbTours++;
		}
		nbTours--;
		
		// resultat ordinateur
		if (_modelState.isIWin()) // l'ordinateur a gagne
		{
			AppLog.getLogger().info("J'ai gagné en " + nbTours + " tentative(s)");
		}
		else
		{
			AppLog.getLogger().info("J'ai perdu aprés " + nbTours + " tentative(s)");
		}
		// resultat joueur
		if (_modelState.isYouWin()) // le joueur a gagne
		{
			AppLog.getLogger().info("Vous avez gagné en " + nbTours + " tentative(s)");
		}
		else
		{
			AppLog.getLogger().info("Vous avez perdu aprés " + nbTours + " tentative(s)");
		}
		
		// affiche message
		_view.displayWin(nbTours);
		_view.displayLost();
	}
}
