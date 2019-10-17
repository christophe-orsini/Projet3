package com.openclassrooms.escapegame.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton qui recupère la configuration de l'application dans le fichier app.properties
 * 
 * @author C.ORSINI
 *
 */
public final class AppConfig {
	// Constantes par defaut si le fichier config.properties n'est pas trouvé
	private final boolean DEFAULT_DEBUG = true;
	private final int DEFAULT_DIGITS = 4;
	private final int DEFAULT_TRIES = 5;
	
	private static AppConfig _instance;
	private String _version;
	private boolean _debug;
	private int _nbDigits;
	private int _nbTries;

	// **************************************************************** constructors
	private AppConfig() 
	{
		Properties properties = new Properties();
		try
		{
			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
			properties.load(input);
			_version = properties.getProperty("developpeur");
			_debug = properties.getProperty("developpeur").equals("true")?true:false;
			_nbDigits = Integer.parseInt(properties.getProperty("config.nb_digits"));
			_nbTries = Integer.parseInt(properties.getProperty("config.nb_tries"));
		}
		catch (FileNotFoundException e)
		{
			// Utilisation de valeurs par defaut arbitraires
			_debug = DEFAULT_DEBUG;
			_nbDigits = DEFAULT_DIGITS;
			_nbTries = DEFAULT_TRIES;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	 
	// *********************************************** getters/setters
	@SuppressWarnings("javadoc")
	public String getVersion()
	{
		return _version;
	}
	@SuppressWarnings("javadoc")
	public boolean isDebug()
	{
		return _debug;
	}
	
	@SuppressWarnings("javadoc")
	public int getNbDigits()
	{
		return _nbDigits;
	}

	@SuppressWarnings("javadoc")
	public int getNbTries()
	{
		return _nbTries;
	}
	
	// ************************************************** methods
	/**
	 * Obtenir l'unique instance de AppConfig
	 * 
	 * @return AppConfig : l'instance
	 */
	public final static AppConfig getInstance()
	{
		if (AppConfig._instance == null)
		{
			AppConfig._instance = new AppConfig();
			
		}
		return AppConfig._instance;
	}
}
