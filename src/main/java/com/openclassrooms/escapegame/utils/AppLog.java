package com.openclassrooms.escapegame.utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

/**
 * Singleton pour les logs
 * 
 * @author C.ORSINI
 *
 */
public final class AppLog {
	private static AppLog _instance;
	private static final Logger _logger = Logger.getLogger(AppLog.class);

	// ************************************************************************ constructors
	private AppLog() 
	{
		ConsoleAppender appender = (ConsoleAppender)  _logger.getAppender("DailyRollingFile"); 
		_logger.addAppender(appender);
	}
	 
	// ************************************************** methods
	/**
	 * Obtenir l'unique instance de AppLog
	 * @return AppLog : l'instance
	 */
	public final static AppLog getInstance()
	{
		if (AppLog._instance == null)
		{
			AppLog._instance = new AppLog();
		}
		return AppLog._instance;
	}
	/**
	 * Obtenir une instance du logger
	 * 
	 * @return Logger : Le logger
	 */
	public final static Logger getLogger()
	{
		if (AppLog._instance == null)
		{
			AppLog._instance = new AppLog();
		}
		return AppLog._logger;
	}
}
