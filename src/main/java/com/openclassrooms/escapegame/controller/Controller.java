package com.openclassrooms.escapegame.controller;

import java.util.Observer;
import com.openclassrooms.escapegame.model.Model;
import com.openclassrooms.escapegame.view.View;

/**
 * Ckasse abstraite de gestion des controlleurs
 * @author C.ORSINI
 *
 */
public abstract class Controller implements Observer
{
	protected Model _model;
	protected View _view;
	
	public Controller(Model model)
	{
		_model = model;
		_model.addObserver(this); // abonnement aupres du modele
		// create view in sub-class
	}
	public abstract void run();
	protected abstract boolean checkEntry(String entry);
}
