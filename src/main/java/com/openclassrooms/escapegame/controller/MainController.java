package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.ChallengerModel;
import com.openclassrooms.escapegame.model.DefenderModel;
import com.openclassrooms.escapegame.view.ErrorView;
import com.openclassrooms.escapegame.view.MainMenuView;

/**
 * Controleur principal du pattern MVC
 */
public class MainController
{
	/**
	 * Method principale d'execution du jeu permettant de choisir un mode de jeu
	 */
	public void run()
	{
		// affichage du menu principal
		MainMenuView view = new MainMenuView();
		int choice = view.display(true); // true pour le premier appel sans affichage de l'option rejouer
		
		// boucle principale du jeu
		while (choice != 0)
		{
			switch (choice)
			{
			case 1:
				DefenderModel defenderModel	= new DefenderModel(); // choix 1 creation du modele defenseur...
				DefenderController defenderController = new DefenderController(defenderModel); // ... et du controleur correspondant
				defenderController.run();
				break;
			case 2:
				ChallengerModel challengerModel	= new ChallengerModel();
				ChallengerController challengerController = new ChallengerController(challengerModel);
				challengerController.run();
				break;
			case 3:
				break;
			case 4:
			case 0:
				break;
			default:
				ErrorView message = new ErrorView("Choix invalide !"); // vue en cas d'erreur de choix
				message.display();
			}
			choice = view.display(false); // false pour afficher l'option rejouer
		}
	}
}
