package com.openclassrooms.escapegame.model;

import java.util.Observable;

/**
 * Classe abstraite de gestion des modèles
 * @author C.ORSINI
 *
 */
public abstract class Model extends Observable
{
	protected Combinaison _searched; // Combinaison rechechee
	protected Combinaison _proposed; // Combinaison proposee
	protected String _result; // Symboles du resultat + - =
	protected boolean _win; // flag de victoire
	
	public abstract void manageEntry(String entry);
	
	public Combinaison getSearched() {
		return _searched;
	}
	public Combinaison getProposed() {
		return _proposed;
	}
	public String getResult() {
		return _result;
	}
	public boolean isWin() {
		return _win;
	}
	
	
}
