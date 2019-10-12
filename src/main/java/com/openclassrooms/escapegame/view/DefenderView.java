package com.openclassrooms.escapegame.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import com.openclassrooms.escapegame.controller.DefenderController;
import com.openclassrooms.escapegame.model.DefenderModel;
import com.openclassrooms.escapegame.utils.AppConfig;

/**
 * Vue console du mode défenseur qui affiche les infos et demande les entrées
 * @author C.ORSINI
 *
 */
public class DefenderView implements Observer
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	private String _searchedCombinaison; // combinaison recherchee
	private String _response; // la reponse a la proposition
	protected DefenderController _controller;
	protected DefenderModel _model;
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
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
	 * Affiche les consignes à l'écran
	 */
	public void display() {
		// consignes
		System.out.println("Vous devez deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
		// mode developpement
		if (AppConfig.getInstance().isDebug())
		{
			System.out.println("Développement : solution = " + _model.getSearchedCombinaison());
		}
	}
	/**
	 * Demande la proposition
	 * @param tryNumber
	 * @return
	 */
	public String readProposed(int tryNumber)
	{
		System.out.printf("%35s %d : ", "Veuillez faire la proposition N°", tryNumber);
		_entry = new Scanner(System.in);
		String proposition = _entry.nextLine();
		return proposition;
	}
	/**
	 * Affiche un message a l'ecran
	 * @param message String : le message
	 */
	public void printMessage(String message)
	{
		System.out.println(message);
	}
	/**
	 * Affiche la reponse de l'ordinateur
	 */
	public void printResponse()
	{
		System.out.printf("%37s : %s%n", "Resultat", _response);
	}
	/**
	 * Affiche le message de victoire
	 * @param nbTries int : nombre d'essais pour ganger
	 */
	public void displayWin(int nbTries)
	{
		System.out.println("BRAVO ! Vous avez gangé en " + nbTries + " tentative(s). La réponse est : " + _searchedCombinaison);
	}
	/**
	 * Affiche le message de defaite
	 */
	public void displayLost()
	{
		System.out.println("Dommage, vous n'avez pas trouvé la combinaison. La réponse était : " + _searchedCombinaison);
	}
	public void update(Observable o, Object arg)
	{
		_searchedCombinaison = _model.getSearchedCombinaison();
		_response = _model.getResponse();
	}
}
