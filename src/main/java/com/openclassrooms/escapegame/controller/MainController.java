package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.view.*;

/**
 * Controleur principal de l'application qui propose un choix de jeu
 */
public class MainController
{
	// ************************************************************************** methods
	/**
	 * Methode principale d'execution du jeu permettant de choisir un mode de jeu
	 */
	public void run()
	{
		Model model = null;
		Controller controller = null;
		boolean flag = true; // flag true pour afficher l'option rejouer
		
		// affichage du menu principal
		MainMenuView view = new MainMenuView();
		int choice = -1;
		
		// boucle principale du jeu
		while (choice != 0)
		{
			choice = view.display(flag); // true pour le premier appel sans affichage de l'option rejouer
			switch (choice)
			{
			case 1:
				model	= new ChallengerModel(); // choix 1 creation du modele attaquant...
				controller = new ChallengerController(model); // ... et du controleur correspondant
				controller.run(); // execution de la methode principale du controleur
				break;
			case 2:
				model	= new DefenderModel();
				controller = new DefenderController(model);
				controller.run();
				break;
			case 3:
				model	= new FightModel();
				controller = new FightController(model);
				controller.run();
				break;
			case 4:
				if (controller != null) {
					model.changeCombinaison();
					controller.run();
				}
			case 0:
				break;
			default:
				view.displayMessage("Choix invalide !"); // vue en cas d'erreur de choix
				continue;
			}
			flag = false; // pour les autres appels a la vue avec l'option rejouer
		}
	}
}
