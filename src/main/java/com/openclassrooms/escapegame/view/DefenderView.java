package com.openclassrooms.escapegame.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import com.openclassrooms.escapegame.controller.DefenderController;
import com.openclassrooms.escapegame.model.DefenderModel;
import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;

/**
 * Vue console du mode défenseur qui affiche les infos et demande les entrées
 * @author C.ORSINI
 *
 */
public class DefenderView implements Observer
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	private boolean _win;  // teste si le joueur a gagne
	private String _reponse; // la reponse a la proposition
	protected DefenderController _controller;
	protected DefenderModel _model;
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrabnt la vue auprès du modèle
	 * @param controller DefenderController : le controleur qui gère la vue
	 * @param model DefenderModel : Le modèle correspondant
	 */
	public DefenderView(DefenderController controller, DefenderModel model)
	{
		_controller = controller;
		_model = model;
		_model.addObserver(this);
	}
	
	// ******************************************************* methods
	/**
	 * Affiche les informations à l'écran et demande une proposition
	 */
	public void display() {
		// consignes
		System.out.println("Vous devez deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
		// mode developpement
		if (AppConfig.getInstance().isDebug())
		{
			System.out.println("Développement : solution = " + _model.getSearchedCombinaison());
		}
		
		// jeu
		int nbTours = 1; // nombres de tours de jeu
		while (!_win && nbTours <= AppConfig.getInstance().getNbTries())
		{
			System.out.printf("%35s %d : ", "Veuillez faire la proposition N°", nbTours);
			_entry = new Scanner(System.in);
			String proposition = _entry.nextLine();
			if (!_controller.checkEntry(proposition))
			{
				System.out.println("Veuillez entrer une combianison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s)SVP !");
				continue;
			}
			_controller.manageEvent(proposition);
			System.out.printf("%37s : %s%n", "Resultat", _reponse);
			AppLog.getLogger().info("Essai N°" + nbTours + " combinaison proposée : " + proposition +" -> réponse : " + _reponse);
			nbTours++;
		}
		nbTours--;
		
		// resultat
		if (_win)
		{
			System.out.println("BRAVO ! Vous avez gangé en " + nbTours + " tentative(s). La réponse est : " + _model.getSearchedCombinaison());
			AppLog.getLogger().info("Gagné en " + nbTours + " tentative(s)");
		} 
		else
		{
			System.out.println("Dommage, vous n'avez pas trouvé la combinaison. La réponse était : " + _model.getSearchedCombinaison());
			AppLog.getLogger().info("Perdu aprés " + nbTours + " tentative(s)");
		}
		
	}
	public void update(Observable o, Object arg)
	{
		_win = _model.isWin();
		_reponse = _model.getReponse();
	}
}
