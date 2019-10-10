package com.openclassrooms.escapegame.model;

import java.util.Observable;

/**
 * Classe abstraite du pattern strategy pour utiliser different modes de jeu et du pattern MVC pour le modele 
 * @author C.ORSINI
 *
 */
public abstract class Model extends Observable
{
	/**
	 * Joue le mode demande
	 */
	public abstract void play();
}
