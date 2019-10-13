package com.openclassrooms.escapegame.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;
import com.openclassrooms.escapegame.model.DualModel;
import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;
import com.openclassrooms.escapegame.view.DualView;

/**
 * Contrôleur du mode défenseur qui vérifie les entrées de la vue et transmet au modèle 
 * @author C.ORSINI
 *
 */
public class DualController implements Observer
{
	protected DualModel _model;
	protected DualView _view;
	private boolean _win;
	
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé. Le constructeur crée la vue
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public DualController(DualModel model)
	{
		_model = model;
		_view = new DualView(this, _model);
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
			String entry = _view.readProposed(nbTours); // affiche la demande de proposition et attend la proposition
			if (!checkEntry(entry)) // verifie que la proposition comprend des chiffres et est de la bonne longueur
			{
				_view.printMessage("Veuillez entrer une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) SVP !");
				continue;
			}
			_model.verify(entry); // passe la main au modele pour verifier la combinaison
			AppLog.getLogger().info("Essai N°" + nbTours + " combinaison proposée : " + _model.getSearchedCombinaison() +" -> réponse : " + _model.getResponse());
			_view.printResponse();
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
	 * Appelé par la vue pour vérifier qu'une entrée au clavier est bien numérique et de la bonne longueur
	 * @param entry String : l'entrée au clavier
	 * @return boolean : true si l'entrée est valide sinon false
	 */
	private boolean checkEntry(String entry)
	{
		if (entry.length() != AppConfig.getInstance().getNbDigits())
		{
			return false;
		}
		
		return Pattern.matches("^[0-9]*$", entry);
	}
}
