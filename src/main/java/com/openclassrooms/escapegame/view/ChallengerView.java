package com.openclassrooms.escapegame.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import com.openclassrooms.escapegame.controller.ChallengerController;
import com.openclassrooms.escapegame.model.ChallengerModel;
import com.openclassrooms.escapegame.utils.AppConfig;

/**
 * Vue console du mode attaquant qui affiche les infos et demande les entrées
 * @author C.ORSINI
 *
 */
public class ChallengerView implements Observer
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	private String _proposedCombinaison; // combinaison proposee
	protected ChallengerController _controller;
	protected ChallengerModel _model;
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * @param controller ChallengerController : le controleur qui gère la vue
	 * @param model ChallengerModel : Le modèle correspondant
	 */
	public ChallengerView(ChallengerController controller, ChallengerModel model)
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
		System.out.println("Pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) et je vais la deviner en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
	}
	/**
	 * Affiche une proposition et demande les corrections a apporter sous forme de symboles +, - ou =
	 * @param tryNumber int : le numero de la tentative en cours
	 * @return String : la reponse sous forme de symboles +, - ou =
	 */
	public String readProposed(int tryNumber)
	{
		_proposedCombinaison = _model.getProposedCombinaison();
		System.out.printf("%46s%d : %s%n","Voici ma proposition N°", tryNumber, _proposedCombinaison);
		System.out.printf("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
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
	 * Affiche le message de victoire
	 * @param nbTries int : nombre d'essais pour ganger
	 */
	public void displayWin(int nbTries)
	{
		System.out.println("YOUPI ! J'ai gangé en " + nbTries + " tentative(s). La réponse est : " + _proposedCombinaison);
	}
	/**
	 * Affiche le message de defaite
	 */
	public void displayLost()
	{
		System.out.println("Dommage, je n'ai pas trouvé la combinaison.");
	}
	public void update(Observable o, Object arg)
	{
		_proposedCombinaison = _model.getProposedCombinaison();
	}
}
