@echo off
chcp 850
cls
if not exist target\gameplaystudio-${project.version}.jar (
	echo **************************************************************************************
	echo * L'application n'est pas installée !                                                *
	echo * Exécuter la commande 'install' en mode console ou double-cliquer sur 'install.bat' *
	echo * pour installer l'application                                                       *
	echo **************************************************************************************
) else (
	java -jar target\gameplaystudio-${project.version}.jar
)