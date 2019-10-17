@echo off
cls
if not exist target\classes\log4j.xml (
	mvn clean package
)
java -jar target\gameplaystudio-${project.version}.jar