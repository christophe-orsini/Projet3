@echo off
cls
if not exist target\classes\log4j.xml (
	mvn clean compile
)
java -jar target\gameplaystudio-${project.version}.jar