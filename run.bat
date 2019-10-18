@echo off
cls
if not exist target\classes\log4j.xml (
	mvn clean package
)
java -jar target\gameplaystudio-1.1.1.jar