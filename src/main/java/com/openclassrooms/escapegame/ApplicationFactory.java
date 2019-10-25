package com.openclassrooms.escapegame;

import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;

/**
 * @author C.ORSINI
 * Classe permettant la construction d'un jeu
 */
public class ApplicationFactory
{
	/**
	 * Permet de construire le modèle de jeu
	 * @param mode GameMode : Enumération du mode de jeu 
	 * @return Model : Le modéle construit
	 * @throws IllegalArgumentException : Lçve une exception si l'argument n'existe pas
	 */
	public static Model getModel(GameMode mode) throws IllegalArgumentException 
	{
		switch (mode)
		{
		case CHALLENGER:
			return new ChallengerModel();
		case DEFENDER:
			return new DefenderModel();
		case FIGHT:
			return new FightModel();
		default:
			throw new IllegalArgumentException("Ce mode de jeu n'existe pas");
		}
	}
	/**
	 * Permet de construire le controleur de jeu
	 * @param mode GameMode : Enumération du mode de jeu 
	 * @return Controller : Le modéle construit
	 * @throws IllegalArgumentException : Lçve une exception si l'argument n'existe pas
	 */
	public static Controller getController(GameMode mode, Model model) throws IllegalArgumentException 
	{
		switch (mode)
		{
		case CHALLENGER:
			return new ChallengerController(model);
		case DEFENDER:
			return new DefenderController(model);
		case FIGHT:
			return new FightController(model);
		default:
			throw new IllegalArgumentException("Ce mode de jeu n'existe pas");
		}
	}
}
