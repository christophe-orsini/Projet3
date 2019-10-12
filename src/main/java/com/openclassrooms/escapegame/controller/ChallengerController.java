package com.openclassrooms.escapegame.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;
import com.openclassrooms.escapegame.model.ChallengerModel;
import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;
import com.openclassrooms.escapegame.view.ChallengerView;

/**
 * Contrôleur du mode attaquant qui vérifie les entrées de la vue et transmet au modèle 
 * @author C.ORSINI
 *
 */
public class ChallengerController implements Observer
{
	protected ChallengerModel _model;
	protected ChallengerView _view;
	private boolean _win;
	
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé. Le constructeur crée la vue et enregistre le controleur auprès du modèle
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public ChallengerController(ChallengerModel model)
	{
		_model = model;
		_view = new ChallengerView(this, _model);
		_model.addObserver(this);
	}
	// *********************************************** methods
	/**
	 * execution de la boucle principale du jeu
	 */
	public void run()
	{
		// consignes
		_view.display();
		
		// jeu
		int nbTours = 1; // nombres de tours de jeu
		while (!_win && nbTours <= AppConfig.getInstance().getNbTries())
		{
			String entry = _view.readProposed(nbTours); // affiche la proposition et attend la reponse
			if (!checkEntry(entry)) // verifie que la reponse comprend des symboles + - =  et est de la bonne longueur
			{
				_view.printMessage("Veuillez entrer une réponse à " + AppConfig.getInstance().getNbDigits() + " symbole(s) SVP !");
				continue;
			}
			AppLog.getLogger().info("Proposition N°" + nbTours + " : " + _model.getProposedCombinaison()+ " -> Reponse : " + entry);
			_model.verify(entry); // passe la main au modele pour controler la reponse
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
	public void update(Observable o, Object arg) {
		_win = _model.isWin();
	}
	/**
	 * Appelé par la vue pour vérifier qu'une entrée au clavier ne comprend que les symboles +, - ou = et est de la bonne longueur
	 * @param entry String : l'entrée au clavier
	 * @return boolean : true si l'entrée est valide sinon false
	 */
	private boolean checkEntry(String entry)
	{
		if (entry.length() != AppConfig.getInstance().getNbDigits())
		{
			return false;
		}
		
		return Pattern.matches("^(-|\\+|=)*$", entry);
	}
}
