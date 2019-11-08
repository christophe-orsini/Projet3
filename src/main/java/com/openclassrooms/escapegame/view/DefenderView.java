package com.openclassrooms.escapegame.view;

import java.util.Scanner;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;

/**
 * Vue console du mode défenseur qui affiche les infos et demande les entrées
 * 
 * @author C.ORSINI
 *
 */
public class DefenderView extends View
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * 
	 * @param controller Controller : le controleur qui gère la vue
	 * @param model Model : Le modèle correspondant
	 * @param console IConsole : Console d'affichage des messages
	 */
	public DefenderView(Controller controller, Model model, IConsole console)
	{
		super(controller, model, console);
	}
	
	// ******************************************************* methods
	@Override
	public void displayInstructions() {
		// consignes
		_console.displayLine("Pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) et je vais la deviner en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
	}
	@Override
	public String queryEntry(EntryMode entryMode, int tryNumber)
	{
		String message = String.format("%46s%d : %s","Voici ma proposition N°", tryNumber, _modelState.getProposed());
		_console.displayLine(message);
		message = String.format("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
		_console.display(message);
		
		_entry = new Scanner(System.in);
		String proposition = _entry.nextLine();
		return proposition;
	}
	@Override
	public void displayWin(int nbTries)
	{
		_console.displayLine("YOUPI ! J'ai gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getProposed());
	}
	@Override
	public void displayLost()
	{
		_console.displayLine("Dommage, je n'ai pas trouvé la combinaison.");
	}
	
	@Override
	public void displayResult()
	{
		// rien a faire
	}
}
