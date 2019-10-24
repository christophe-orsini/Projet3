@echo off
cls
if not exist target\classes\log4j.xml (
	mvn clean package
	
	echo Installation terminee. Relancer la commande 'run.bat' pour jouer.
	pause
)
java -jar target\gameplaystudio-1.1.3.jar