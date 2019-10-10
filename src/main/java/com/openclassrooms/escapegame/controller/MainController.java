package com.openclassrooms.escapegame.controller;

import com.openclassrooms.escapegame.model.ChallengerMode;
import com.openclassrooms.escapegame.model.DefenderMode;
import com.openclassrooms.escapegame.model.DuelMode;
import com.openclassrooms.escapegame.view.MainMenuView;

/**
 * Controlleur proincipal du pattern MVC
 */
public class MainController extends Controller
{
	@Override
	public void run()
	{
		view = new MainMenuView();
		int choice = view.show(true);
		
		while (choice != 0)
		{
			switch (choice)
			{
			case 1:
				model = new DefenderMode();
				model.play();
				break;
			case 2:
				model = new ChallengerMode();
				model.play();
				break;
			case 3:
				model = new DuelMode();
				model.play();
				break;
			case 4:
				if (model != null)
				{
					model.play();
				}
			case 0:
				break;
			default:
				System.out.println("Choix invalide !");
			}
			choice = view.show(false);
		}
	}
}
