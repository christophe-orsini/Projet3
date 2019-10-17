package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;
import com.openclassrooms.escapegame.view.*;

/**
 * Contrôleur du mode attaquant qui vérifie les entrées de la vue et transmet au modèle 
 * @author C.ORSINI
 *
 */
public class ChallengerController extends Controller
{
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé. Le constructeur crée la vue et enregistre le controleur auprès du modèle
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public ChallengerController(Model model)
	{
		super(model);
		_view = new ChallengerView(this, _model);
		
		// creation de la premiere proposition
		_model.notifyState();
	}
	// *********************************************** methods
	/**
	 * execution de la boucle principale du jeu
	 */
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
				_view.displayError("Veuillez entrer une réponse à " + AppConfig.getInstance().getNbDigits() + " symbole(s) SVP !");
				continue;
			}
			AppLog.getLogger().info("Proposition N°" + nbTours + " : " + _model.getProposed()+ " -> Reponse : " + entry);
			_model.manageEntry(entry); // passe la main au modele pour controler la reponse
			nbTours++;
		}
		nbTours--;
		
		// resultat
		if (_win)
		{
			// affiche le message gagne
			_view.displayWin(nbTours);
			AppLog.getLogger().info("Gagné en " + nbTours + " tentative(s)");
		} 
		else
		{
			// affiche le message perdu
			_view.displayLost();
			AppLog.getLogger().info("Perdu aprés " + nbTours + " tentative(s)");
		}
	}
}
