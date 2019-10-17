@echo off
cls
if not exist target\classes\log4j.xml (
	mvn clean compile
)
java -jar target\gameplaystudio-1.0.4-SNAPSHOT.jar