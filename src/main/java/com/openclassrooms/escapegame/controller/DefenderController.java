package com.openclassrooms.escapegame.controller;

import java.util.Observable;
import java.util.regex.Pattern;
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
	private boolean _win;
	
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé. Le constructeur crée la vue
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public DefenderController(Model model)
	{
		super(model);
		_view = new DefenderView(this, _model);
	}
	// *********************************************** methods
	/**
	 * execution de la boucle principale du jeu
	 */
	public void run()
	{
		// consignes
		_view.displayInstructions();
		
		// jeu
		int nbTours = 1; // nombres de tours de jeu
		while (!_win && nbTours <= AppConfig.getInstance().getNbTries())
		{
			String entry = _view.queryEntry(nbTours); // affiche la demande de proposition et attend la proposition
			if (!checkEntry(entry)) // verifie que la proposition comprend des chiffres et est de la bonne longueur
			{
				_view.displayError("Veuillez entrer une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) SVP !");
				continue;
			}
			_model.manageEntry(entry); // passe la main au modele pour verifier la combinaison
			AppLog.getLogger().info("Essai N°" + nbTours + " combinaison proposée : " + _model.getSearched() +" -> réponse : " + _model.getResult());
			_view.displayResult();
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
		if (o instanceof DefenderModel)
		{
		_win = ((ModelState)arg).isWin();
		}
	}
	/**
	 * Appelé par la vue pour vérifier qu'une entrée au clavier est bien numérique et de la bonne longueur
	 * @param entry String : l'entrée au clavier
	 * @return boolean : true si l'entrée est valide sinon false
	 */
	@Override
	protected boolean checkEntry(String entry) {
		if (entry.length() != AppConfig.getInstance().getNbDigits())
		{
			return false;
		}
		
		return Pattern.matches("^[0-9]*$", entry);
	}
}
