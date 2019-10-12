package com.openclassrooms.escapegame.model;

import java.util.Observable;

import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;

/**
 * Modèle du mode attaquant qui gère le jeu en collaboration avec Combinaison
 * @author C.ORSINI
 *
 */
public class ChallengerModel extends Observable
{
	private Combinaison _proposedCombinaison; // la proposition de l'ordinateur
	private boolean _win; // flag pour indiquer la victoire
	
	// ******************************************************* constructors
	/**
	 * Constructeur créant une proposition de combinaison
	 */
	public ChallengerModel()
	{
		AppLog.getLogger().info("Mode attaquant");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		_proposedCombinaison = new Combinaison(AppConfig.getInstance().getNbDigits());
	}
	// ******************************************************* getters/setters
	/**
	 * Retourne la combinaison sous forme de String
	 * @return String : la combinaison
	 */
	public String getProposedCombinaison() {
		return _proposedCombinaison.toString();
	}
	@SuppressWarnings("javadoc")
	public boolean isWin()
	{
		return _win;
	}
	// ******************************************************* methods
	/**
	 * Vérifie si la reponse par rapport a une combinaison proposee
	 * @param response String : La reponse sous forme de symboles + - =
	 */
	public void verify(String response)
	{
		// Verification de la reponse
		if (_proposedCombinaison.isFound(response))
		{
			_win = true;
		} 
		_proposedCombinaison = _proposedCombinaison.search(response); // elabore une nouvelle combinaison proposee
		setChanged();
		notifyObservers();
	}
}
