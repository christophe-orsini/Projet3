package com.openclassrooms.escapegame.view;

import java.util.Scanner;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;

/**
 * Vue console du mode duel qui affiche les infos et demande les entrées
 * 
 * @author C.ORSINI
 *
 */
public class FightView extends View
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * 
	 * @param controller Controller : le controleur qui gère la vue
	 * @param model Model : Le modèle correspondant
	 */
	public FightView(Controller controller, Model model, IConsole console)
	{
		super(controller, model, console);
	}
	
	// ******************************************************* methods
	@Override
	public void displayInstructions() {
		// consignes
		_console.displayLine("Nous allons jouer chacun à notre tour pour deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " +
				AppConfig.getInstance().getNbTries() + " tentative(s).");
		_console.displayLine("Le premier qui arrive à deviner la combinaison de l'autre a gagné.");
		_console.displayLine("Avant de commmencer, pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s).");
		_console.displayLine("D'abord vous corrigez ma proposition et ensuite vous essayez de deviner ma combinaison. Allons-y !");
		
		// mode developpement
		if (AppConfig.getInstance().isDebug())
		{
			_console.displayLine("Développement : solution = " + _modelState.getSearched());
		}
	}
	@Override
	public String queryEntry(EntryMode entryMode, int tryNumber)
	{
		if (entryMode == EntryMode.CHALLENGER)
		{
			String message = String.format("%65s %d : ", "Veuillez faire votre proposition N°", tryNumber);
			_console.displayLine(message);
		}
		if (entryMode == EntryMode.DEFENDER)
		{
			String message = String.format("%46s%d : %s%n","Voici ma proposition N°", tryNumber, _modelState.getProposed());
			_console.displayLine(message);
			message = String.format("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
			_console.displayLine(message);
		}
		_entry = new Scanner(System.in);
		String proposition = _entry.nextLine();
		
		return proposition;
	}
	@Override
	public void displayResult()
	{
		String message = String.format("%67s : %s%n", "Resultat", _modelState.getResult());
		_console.displayLine(message);
	}
	@Override
	public void displayWin(int nbTries)
	{
		if (_modelState.isYouWin())
		{
			_console.displayLine("BRAVO ! Vous avez gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getSearched());
		}
		if (_modelState.isIWin())
		{
			_console.displayLine("YOUPI ! J'ai gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getProposed());
		}
	}
	@Override
	public void displayLost()
	{
		if (!_modelState.isYouWin())
		{
			_console.displayLine("Dommage, vous n'avez pas trouvé la combinaison. La réponse était : " + _modelState.getSearched());
		}
		if (!_modelState.isIWin())
		{
			_console.displayLine("Dommage, je n'ai pas trouvé la combinaison.");
		}
	}
}
