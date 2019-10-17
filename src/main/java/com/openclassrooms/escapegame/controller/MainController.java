package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.view.*;

/**
 * Controleur principal du pattern MVC
 */
public class MainController
{
	private Model _model;
	private Controller _controller;
	
	// ************************************************************************** methods
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
				_model	= new DefenderModel(); // choix 1 creation du modele defenseur...
				_controller = new DefenderController(_model); // ... et du controleur correspondant
				_controller.run(); // execution de la methode principale du controleur
				break;
			case 2:
				_model	= new ChallengerModel();
				_controller = new ChallengerController(_model);
				_controller.run();
				break;
			case 3:
				_model	= new DualModel();
				_controller = new DualController(_model);
				_controller.run();
				break;
			case 4:
				if (_controller != null) {
					_model.changeCombinaison();
					_controller.run();
				}
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
