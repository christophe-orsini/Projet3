package com.openclassrooms.escapegame.controller;

import java.util.regex.Pattern;
import com.openclassrooms.escapegame.model.DefenderModel;
import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.view.DefenderView;

/**
 * Contrôleur du mode défenseur qui vérifie les entrées de la vue et transmet au modèle 
 * @author C.ORSINI
 *
 */
public class DefenderController
{
	protected DefenderModel _model;
	protected DefenderView _view;
	
	// ***************************************************************** constructors
	/**
	 * Constructeur appelé avec un modèle déjà créé. Le constructeur crée la vue
	 * @param model Model : le modèle auquel le controleur passe les commandes
	 */
	public DefenderController(DefenderModel model)
	{
		_model = model;
		_view = new DefenderView(this, _model);
	}
	// *********************************************** methods
	/**
	 * Affiche la vue correspondante à ce contrôleur
	 */
	public void displayView()
	{
		_view.display();
	}
	/**
	 * Appelé par la vue pour vérifier qu'une entrée au clavier est bien numérique et de la bonne longueur
	 * @param entry String : l'entrée au clavier
	 * @return boolean : true si l'entrée est valide sinon false
	 */
	public boolean checkEntry(String entry)
	{
		if (entry.length() != AppConfig.getInstance().getNbDigits())
		{
			return false;
		}
		
		return Pattern.matches("^[0-9]*$", entry);
	}
	/**
	 * Gère la proposition faite dans la vue
	 * @param proposition String : la proposition de combinaison
	 */
	public void manageEvent(String proposition)
	{
		_model.verify(proposition);
	}
}
