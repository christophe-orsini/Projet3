# GAMEPLAY STUDIO

## Project N°3 Escape Game ONLINE

* Développeur : Christophe ORSINI
* Version : 1.0.4-SNAPSHOT
* Date : 2019-10-13T16:57:41Z

---
### Chargement
Cloner le dépôt à cette adresse [https://github.com/christophe-orsini/Projet3.git](https://github.com/christophe-orsini/Projet3.git)

### Prérequis
> Vous devez avoir **java 8 (1.8.0.222)** ou supérieur et **maven 3.6.2** ou supérieur sur votre ordinateur pour pouvoir exécuter cette application

### Exécution
Pour la première utilisation, après avoir cloné le dépôt, il faut :  
1. Se placer dans le dossier du projet dans lequel vous venez de le cloner
2. A la première utilisation, lancer la commande `mvn package`. Cette commande fabrique l'exécutable et démarre l'application
3. Pour les autres utilisations, lancer la commande `java -jar target\gameplaystudio-1.0.4-SNAPSHOT` cela évitera de reconstruire l'application à chaque fois

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
    - Affichage de la solution en cas de perte aou de gain  
    