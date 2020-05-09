

# Autodoc: Gérez votre garage



## OBJECTIF GENERAL

	En tant que nouveau développeur, il y a un manque de projets disponibles sur le net reprenant une architecture simple et complète d’un environnement de webservice.

	Or, il est souvent fastidieux lorsque l’on débute de mettre en place ce type d’environnement qui est est une base classique a partir de laquelle on peut développer bon nombre de projets.


## DESCRIPTION


	La solution fournie consiste en un environnement de développement de webservice de type CI/CD qui sera fonctionnel, documenté et dont la flexibilité permettra de le réadapter en fonction des besoins du développeur qui aura à l’utiliser

	La présente solution est fournie sous license "Apache License 2.0"

	Le thème de base utilisé pour illustrer le projet sera une application de garagiste qui se connecte à un web-service.

	Il s'agit d'une solution pour une entreprise possédant un stock de marchandise, produisant des services pour un client et ayant un système de facturation.

	Le développement a été effectué ici pour une entreprise de garagiste sous forme de web-service de type REST (autodoc-webservice).

	Ce service intéragit avec la base de données et propose différentes opérations de gestion de personnel, gestion de clients, gestion de stocks, gestion de tâches et de facturation.

	Pour tester le webservice, un jeu de tests d'intégration .json est fourni (Autodoc.postman collection.json) ainsi qu'une application web (autodoc-webapp) communiquant avec ce webservice


	Les differents containers sont:
	    - serveurs de base de données PostgreSql pour chaque environnement (dev, QA, pré-production, production)
	    - serveur Jenkins
	    - serveur sonarQube
	    - serveur Tomcat du Web-Service
	    - serveur Tomcat de l'application web


## PRE-REQUIS ( installation )

	- Docker et docker-compose
	- Intellij Idea
	- Postman
	- Logiciel de gestion de bases de données (option) type Dbeaver


## INSTALLATION DE L'ENVIRONNEMENT
### ( Jenkins, SonarQube, PostgreSql )

	Récupérer le code source

	Télécharger le zip, cloner ou forker le repository  >https://github.com/xbreizh/Autodoc.git

	Création des containers de base de données, Jenkins et SonarQube

	Depuis un terminal, lancer la commande “docker-compose up”

>Noter que pour utiliser pleinement la chaine d'intégration, il vous faudra utiliser votre propre repository ainsi que vos crédentiels Github, je vous conseille donc de "fork" pour pouvoir pleinement utiliser le projet

Cette commande va télécharger les images des containers de base de données, jenkins et sonarqube, puis créer un container pour chacun et les démarrer.

Une fois la commande exécutée, depuis un nouveau terminal, 	lancer la commande “docker ps” et vérifier que les conteneurs suivants soient présents:

		Jenkins
		Sonarqube
		Autodoc-db-production
		autodoc-db-QA
		Autodoc-db-preprod
		Autodoc-db-dev

Une configuration initiale est fournir pour jenkins, contenant notamment les plugins qui seront nécessaires à la création d’un projet (jacoco, ...)

Un projet basique permettant notamment de transmettre les données de jacoco à sonarqube est également fourni


### Connexion à jenkins

<pre>

	<b>Url</b> <i>localhost:8083</i>

	<b>Login</b> <i>admin</i>

	<b>Password</b> <i>admin</i>

</pre>

### Connexion à sonarQube

<pre>

	<b>Url</b> <i>localhost:9000</i>

	<b>Login</b> <i>admin</i>

	<b>Password</b> <i>admin</i>

</pre>

**Note:** il est possible d’envoyer les information de jacoco générées en local directement à SonarQube et sans passer par Jenkins en utilisant la commande suivante depuis le terminal de Intellij Idea ( projet web service ): 

``./gradlew clean build test jacocoTestReport sonarqube  -Dsonar.java.coveragePlugin=jacoco -Dsonar -Dsonar.host.url=http://localhost:9000/``


### Connexion aux bases de données

<pre>

	<b>Host</b> <i>localhost</i>

	<b>Database</b> <i>autodoc</i>

	<b>Port</b> <i>5132 (dev), 5232 (QA), 5332 (Preprod), 5432 (Prod)</i>

	<b>Login</b> <i>ocp</i>

	<b>Password</b> <i>123</i>
	
</pre>

## LANCEMENT DU WEBSERVICE EN LOCAL

	Depuis Intellij Idea, ouvrir le dossier “autodoc-webservice” 

	Lancer la commande “gradle combo”. Cette commande permettra de construire l’application et de créer le fichier “autodoc-webservice.war” au chemin “web/build/libs”

	Depuis le menu de configuration, ajouter une nouvelle configuration pour Tomcat

### *Onglet server* 

<pre>

	<b>Port</b> <i>8087</i>

	<b>JMX port</b> <i>1097</i>
	
</pre>

### *Onglet deployment*

	Ajouter le war qui vient d'être créé

	Context: /autodoc

	Démarrer le serveur Tomcat

	Depuis un navigateur, se rendre a la page http://localhost:8087/autodoc/

	Depuis cette page, vous pouvez accéder à la documentation du webservice

## AJOUT DU JEU DE BASE DE DONNEES

	Ouvrir Postman

	Depuis “File/Import” sélectionner le fichier “Autodoc.postman_collection.json”

	Cela permet d’importer le jeu de requêtes

	Lancer la requête “filler” permettra de remplir la base de données

	Lancer la requête “authentification/authenticate” permet de récupérer le token qui sera mis en cache dans l’application et utilisé dans les autres requêtes.

## LANCEMENT DE LA WEBAPP EN LOCAL


	Depuis Intellij Idea, ouvrir le dossier “autodoc-webapp” 

	Lancer la commande “gradle combo”. Cette commande permettra de construire l’application et de créer le fichier “autodoc-webapp.war” au chemin “web/build/libs”

	Depuis le menu de configuration, ajouter une nouvelle configuration pour Tomcat

### Onglet server


<pre>

	<b>Port</b> <i>8084</i>

	<b>JMX port</b> <i>1097</i>
</pre>

### Onglet deployment

	Ajouter le war qui vient d'être créé
	
	Context: /autodoctor

	Démarrer le serveur Tomcat

	Depuis un navigateur, se rendre a la page http://localhost:8084/autodoctor/

	Depuis cette page, vous pouvez accéder la page de login de l’application.

	Si la base de données a été remplie (voir section Ajout du jeu de données initial), 
	Vous pouvez vous connecter à l’application en utilisant les crédentiels suivants:
<pre>

	<b>Login</b> lmolo
	<b>Password</b> password
</pre>


## TELECHARGEMENT DES IMAGES DE DOCKERHUB

	Depuis la racine du dossier Autodoc, vérifier que le fichier checkDockerHub.txt soit présent.

	Ce fichier permet de mettre à jour le container souhaité.

	Il scanne docker Hub pour une nouvelle version de l’image pour le webservice ou la webapp dans l’environnement souhaité (dev, qa, preprod, production)

	Il télécharge la nouvelle image, puis stop le conteneur concerné, remonte puis redémarre le container a partir de cette nouvelle image.

	Il convient donc de bien faire attention avant d’utiliser cette commande, raison pour laquelle le fichier est couramment avec une extension .txt

	Pour l’utiliser, il faut donc renommer son extension .txt en .sh, puis lancer la commande:

	./checkDockerHub.sh web service-dev (environnement de développement)

	A noter que le projet sur Docker Hub est configuré pour générer une nouvelle image chaque fois qu’un nouveau commit apparaît sur github dans la branche dev


## Documents fournis

<pre>

	-	<b>script docker-compose.yml</b> -> ce script permet de générer et démarrer les containers de base de données, jenkins et sonarqube, mais aussi les containers de webservice et webapp.

		Pour ce faire, il convient tout d'abord de générer les .war du webservice et de la webapp, puis de de-commenter dans le docker-compose les lignes 12 à 23 et 26 à 35

<i>Noter que le docker-compose fait appel a différents fichiers Dockerfile  qui précisent la configuration des différentes images. Leur chemin d'accès est précisé dans le docker-compose</i>

	-	<b>documentation/mecano_diagrams</b> -> diagrammes de conception (Type DRAW.io)

	-	<b>documentation/autodoc-web service.web.main.uml</b> -> diagramme d'entites

	-	<b>documentation/Autodoc.postman collection.json</b> -> contient les tests d'intégration utilisables depuis Postman

	-	<b>documentation/jenkins_jacoco.jpg</b> -> proposition de configuration du plugin jacoco dans jenkins

	-	<b>checkDockerHub.txt</b> -> permet de télécharger la nouvelle image d’un container selon son environnement

	-	<b>docker-cleaner.txt</b> -> script permettant de supprimer tous les containers, images, network et volume ( Attention!! ) à renommer en .sh pour # être utilisé

	-	<b>LICENSE</b> -> license de type Apache 2.0 expliquant les règles d'utilisation du projet
	
</pre>