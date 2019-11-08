package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;
import com.openclassrooms.escapegame.view.*;

/**
 * Contrôleur du mode défenseur qui vérifie les entrées de la vue et transmet au modèle 
 * @author C.ORSINI
 *
 */
public class DefenderController extends Controller
{
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé.<br />
	 * Le constructeur crée la vue et demande au modèle de notifier ses changements
	 * 
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public DefenderController(Model model)
	{
		super(model);
		_view = new DefenderView(this, _model, new Console());
		
		// creation de la premiere proposition
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
			String entry = _view.queryEntry(null, nbTours); // affiche la proposition et attend la reponse
			if (!checkEntry("^(-|\\+|=)*$", entry)) // verifie que la reponse comprend des symboles + - =  et est de la bonne longueur
			{
				_view.displayError("Veuillez entrer une réponse à " + AppConfig.getInstance().getNbDigits() + " symbole(s) SVP !");
				continue;
			}
			AppLog.getLogger().info("Proposition N°" + nbTours + " : " + _modelState.getProposed()+ " -> Reponse : " + entry);
			if (!_model.checkResponse(entry))  // passe la main au modele pour controler la reponse
			{
				_view.displayError("Saisie erronée. D'après ma(mes) proposition(s) et votre(vos) saisie(s), je vous suggère : " + _modelState.getResult());
			}
			else
			{
				_model.manageEntry(entry);  // passe la main au modele pour controler la réponse et faire une proposition
				nbTours++;
			}
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
