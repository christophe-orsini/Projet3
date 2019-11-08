# GAMEPLAY STUDIO

## Project N°3 Escape Game ONLINE

* Développeur : Christophe ORSINI
* Version     : 1.3.0
* Javadoc     : [ici](https://projet3.orsini-perso.fr) 

---
### Prérequis
Vous devez avoir **java** version **1.8.0_222** ou supérieur et **maven** version **3.6.2** ou supérieur sur votre ordinateur pour pouvoir exécuter cette application

### Chargement
Clonez le dépôt à cette adresse [https://github.com/christophe-orsini/Projet3.git](https://github.com/christophe-orsini/Projet3.git)

### Installation
1. Placez vous dans le dossier où vous avez cloné le dépôt  
2. Tapez la commande `install` si vous êtes en mode console ou cliquez sur `install.bat`  

### Exécution
Tapez la commande `run` si vous êtes en mode console ou cliquez sur `run.bat`  

---
#### Notes de versions
- Version 0.0.0  
    - Début du projet  
    - Mise en place du menu principal, de la classe `Combinaison` et du pattern stratégie pour les différents modes du jeu  
    - Création des 3 algorithmes de jeu  
- Verion 1.0.0  
     - Ajout pattern singleton pour logs et config  
     - organisation des packages  
- Version 1.0.1  
     - Ajout du Pattern MVC  
- Version 1.0.2  
      - Correction des bugs N°1 et N°2  
- Version 1.0.3  
    - Ajout de la version et ajout de constantes dans `AppConfig`  
    - Correction affichage message developpeur dans `DefenderMode`  
    - Nouvelle configuration du `README.md`  
    - Affichage de la solution en cas de perte ou de gain  
- Version 1.0.4  
     - Amélioration du pattern MVC  
     - Ajout de run.bat dans les ressources  
- Version 1.0.5  
     - Correction du run.bat
     - Correction des commentaires javadoc  
- Version 1.0.6  
    - Correction bug boucle en mode dual  
    - Correction ecriture des logs  
- Version 1.1.0 - Version stable
    - Correction inversion mode defender et challenger  
- Version 1.1.1  
     - Refactoring de MainController et MainMenuView
     - Suppression de ErrorView
     - Changement de nom Dual en Fight  
- Version 1.1.2
    - Incorporation des dépendances dans le jar
    - Refonte du `README.md`
    - Correction du `run.bat`  
- Version 1.1.3
     - Correction bug log combinaison ne se fait pas lorsque l'on rejoue
- Version 1.1.4
     - Création du `install.bat`
     - Nouveau `run.bat`
     - Modification du `config.properties` et du `README.md`
- Version 1.2.0
    - Utilisation de Factory pour l'application et les vues
    - Création de l'interface `IConsole` et de la classe `Console` pour l'affichage et la saisie
    - Refonte de `Application`
    - Correction bug saisie menu  
- Version 1.3.0
    - Ajout d'un controle d'erreur de saisie du joueur en mode défenseur et duel
    - Correction des tests