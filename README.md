# Autodoc: Gérez votre garage



## **OBJECTIF GENERAL**

	En tant que nouveau développeur, il y a un manque de projets disponibles sur le net reprenant une architecture simple et complète d’un environnement de webservice.

	Or, il est souvent fastidieux lorsque l’on débute de mettre en place ce type d’environnement qui est est une base classique a partir de laquelle on peut développer bon nombre de projets.


## **DESCRIPTION**


	La solution fournie consiste en un environnement de développement de webservice de type CI/CD qui sera fonctionnel, documenté et dont la flexibilité permettra de le réadapter en fonction des besoins du développeur qui aura à l’utiliser

	Le thème de base utilisé pour illustrer le projet sera une application de garagiste qui se connecte à un web-service.


	Il s'agit d'une solution pour une entreprise possédant un stock de marchandise et produisant des services pour un client.

	Le developpement a ete effectue ici pour une entreprise de garagiste sous forme de web-service de type REST (autodoc-webservice).

	Ce service interagit avec la base de données et propose différentes opérations de gestion de personnel, gestion de clients, gestion de stocks, gestion de tâches et de facturation.

	Pour tester le webservice, un jeu de tests d'intégration .json est fourni (Autodoc.postman collection.json) ainsi qu une application web (autodoc-webapp) communiquant avec ce webservice


	Les differents containers sont:
	    - serveurs de base de données PostgreSql pour chaque environnement (dev, QA, pré-production, production)
	    - serveur Jenkins
	    - serveur sonarQube
	    - serveur Tomcat du Web-Service
	    - serveur Tomcat de l'application web


## **PRE-REQUIS**

	Installer Docker get docker-compose
	Installer intellijIdea
	Installer Postman
	Installer un logiciel de gestion de bases de données (option) type Dbeaver


## **INSTALLATION DE L'ENVIRONNEMENT**
### (Jenkins, SonarQube, PostgreSql)

	Récupérer le code source

	Télecharger le zip ou cloner le repository  >https://github.com/xbreizh/Autodoc.git

	Création des containers de base de données, Jenkins et SonarQube

	Depuis un terminal, lancer la commande “docker-compose up”

Cette commande va télécharger les images des containers de base de données, jenkins et sonarqube, puis créer un container pour chacun et les démarrer.

Une fois la commande exécutée, depuis un nouveau terminal, 
	Lancer la commande “docker ps” et vérifier que les conteneurs suivants soient présents:

		Jenkins
		Sonarqube
		Autodoc-db-production
		autodoc-db-QA
		Autodoc-db-preprod
		Autodoc-db-dev

Une configuration initiale est fournir pour jenkins, contenant notamment les plugins qui seront nécessaires à la création d’un projet (jacoco, ...)

La documentation pour la création d’un nouveau projet peut être trouvée ici: 

A noter que pour que Jenkins transférer les données de jacoco a sonarQube, il faut utiliser le module Jacoco dans le projet. Un exemple de configuration peut être trouvé avec le document jenkins jacoco.jpg

### Connexion a jenkins

**Url** *localhost:8083*

**Login** *admin*

**Password** *admin*

### Connexion a sonarQube

**Url** *localhost:9000*

**Login** *admin*

**Password** *admin*

**Note:** il est possible d’envoyer les information de jacoco générées en local directement à SonarQube et sans passer par Jenkins en utilisant la commande suivante depuis le terminal de intellij (projet web service): 

>./gradlew clean build test jacocoTestReport sonarqube  -Dsonar.java.coveragePlugin=jacoco -Dsonar -Dsonar.host.url=http://localhost:9000/


### Connexion aux bases de données

**Host** *localhost*

**Database** *autodoc*

**Port** *5132 (dev), 5232 (QA), 5332 (Preprod), 5432 (Prod)*

**Login** *ocp*

**Password** *123*

## **LANCEMENT DU WEBSERVICE EN LOCAL**

	Depuis Intellij Idea, ouvrir le dossier “autodoc-webservice” 

	Lancer la commande “gradle combo”. Cette commande permettra de construire l’application et de créer le fichier “autodoc-webservice.war” au chemin “web/build/libs”

	Depuis le menu de configuration, ajouter une nouvelle configuration pour Tomcat

### *Onglet server* 

**Port** *8087*

**JMX port** *1098*

### *Onglet deployment*

*Ajouter le war qui vient d'être créé*

*Context: /autodoc*

	Démarrer le serveur Tomcat

	Depuis un navigateur, se rendre a la page http://localhost:8087/autodoc/

	Depuis cette page, vous pouvez accéder à la documentation du webservice

## **AJOUT DU JEU DE BASE DE DONNEES** 

	Ouvrir Postman

	Depuis “File/Import” selectionner le fichier “Autodoc.postman_collection.json”

	Cela permet d’importer le jeu de requêtes

	Lancer la requête “filler” permettra de remplir la base de données

	Lancer la requête “authentification/authenticate” permet de récupérer le token qui sera mis en cache dans l’application et utilisé dans les autres requêtes.

## **LANCEMENT DE LA WEBAPP EN LOCAL**


	Depuis Intellij Idea, ouvrir le dossier “autodoc-webapp” 

	Lancer la commande “gradle combo”. Cette commande permettra de construire l’application et de créer le fichier “autodoc-webapp.war” au chemin “web/build/libs”

	Depuis le menu de configuration, ajouter une nouvelle configuration pour Tomcat

### Onglet server

**Port** *8084*

**JMX port** *1097*

### Onglet deployment

	Ajouter le war qui vient d'être créé
	
	Context: /autodoctor

	Démarrer le serveur Tomcat

	Depuis un navigateur, se rendre a la page http://localhost:8084/autodoctor/

	Depuis cette page, vous pouvez accéder la page de login de l’application.

	Si la base de données a été remplie (voir section Ajout du jeu de données initial), 
	Vous pouvez vous connecter à l’application en utilisant les crédentiels suivants:
	Login: lmolo
	Password: password


## **TELECHARGEMENT DES IMAGES DE DOCKERHUB**

	Depuis la racine du dossier Autodoc, vérifier que le fichier checkDockerHub.txt soit présent.

	Ce fichier permet de mettre à jour le container souhaite.

	Il scanne docker Hub pour une nouvelle version de l’image pour le webservice ou la webapp dans l’environnement souhaite (dev, qa, preprod, production)

	Il téléchargez la nouvelle image, puis stop le conteneur concerné, et remonte puis redémarre le container a partir de cette nouvelle image.

	Il convient donc de bien faire attention avant d’utiliser cette commande, raison pour laquelle le fichier est couramment avec une extension .txt

	Pour l’utiliser, il faut donc renommer son extension .txt en .sh, puis lancer la commande:

	./checkDockerHub.sh web service-dev (environnement de développement)

	A noter que le projet sur Docker Hub est configurer pour générer une nouvelle image chaque fois qu’un nouveau commit apparaît sur github dans la branche dev


## **Documents fournis**


	- 	script docker-compose.yml ce script permet de générer et démarrer les containers de base de données, jenkins et sonarqube, mais aussi les containers de webservice et webapp.

		Pour ce faire, il convient tout d'abord de générer les .war du webservice et de la webapp, puis de de-commenter dans le docker-compose les lignes 12 à 23 et 26 à 35

    -  	mecano_diagrams -> diagrammes de conception (Type DRAW.io)

    -	autodoc-web service.web.main.uml -> diagramme d'entites

	-	fichier Autodoc.postman collection.json -> contient les tests d'intégration utilisables depuis Postman

	-	fichier jenkins_jacoco.jpg -> proposition de configuration du plugin jacoco dans jenkins

	-	fichier checkDockerHub.txt -> permet de téleécharger la nouvelle image d’un container selon son environnement