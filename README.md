# Systèmes Répartis1 - TP2 -  «Serveur FTP»

### Binome :

- Hatim  Mrabet el khomssi 

- Nouria Aitkheddache
 
<br>

## Introduction

>L'objectif du projet est désormais de **mettre en œuvre un serveur conforme au protocole applicatif File Transfer Protocol (FTP)**. Ce serveur doit donc utiliser l'API Socket TCP pour échanger avec un client FTP (e.g., Filezilla) pour stocker et envoyer des fichiers en respectant le standard FTP.

* *Documentation FTP* : <http://abcdrfc.free.fr/rfc-vf/pdf/rfc959.pdf>


### Architecture du rendu

* Un dossier **src** qui contient le projet Maven.
* Un dossier **doc** qui contient les vidéos de démonstration du projet et le diagramme UML.
* Un fichier **serveur** qui contient les dossier des client.
* Un fichier **passwd.txt** qui contient les noms clients et leur mots de passe
* Un fichier **README.md** (celui-ci) qui décrit les caractéristiques du projet.
## Installation et utilisation du projet Maven

### Lancer le server avec Maven 
Ouvrir ce dépôt dans un terminal de contrôle et se placer dans le répertoire serveurFTP/ avec la commande `cd serveurFTP /`.

* Générer le projet et l'archive exécutable : 
`mvn package`

* Exécution de l'archive : 
`java -jar target/serveurFTP-1.0.jar


### Classes

#### 1. Serveur:
- La classe Main reponsable d'accepter les demandes de connexions des clients FTP.
- La classe ServeurHandle crée le client et l'enregistrer avec l'ID du thread courant et invoque les commande. 
### 2. les classe Command.java :
- la classe abstract Commande java héritée par toutes les classes commande.Contient une méthode execut() qui traite la requête envoyée par le client FTP et envoie une réponse.
- la classe CommandeExecuteur pour un utilisateur connecté, fait appel à la classe commande spécifié pour exécuter la commande de client.
<br>



