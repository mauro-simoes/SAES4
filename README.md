# Projet SAES4

Ce projet a été réalisé dans le cadre du quatrième semestre de la Licence professionnelle BUT. Il regroupe une API, un site Frontend en React et une application mobile en React Native.
Le gitHub de l'application mobile est à retrouver juste ici : [Application Mobile GitHub](https://github.com/Nabal22/SAES4-Mobile)

## Présentation

Le projet SAES4 est une application web et mobile qui permet de gérer des sondages en ligne. Il comprend une API développée en Node.js, un site Frontend réalisé en React et une application mobile développée en React Native.

L'API est responsable de la gestion des utilisateurs, des sondages, des questions et des réponses. Le site Frontend offre une interface utilisateur pour créer, modifier et participer aux sondages. L'application mobile permet aux utilisateurs de répondre aux sondages depuis leurs appareils mobiles.


## Installation et exécution

Assurez-vous d'avoir Docker installé sur votre machine avant de procéder à l'installation.

1. Clonez ce dépôt : `git clone https://github.com/mauro-simoes/SAES4.git`.
2. Accédez au répertoire du projet : `cd SAES4`.
3. Lancez l'application en utilisant Docker : 

```
docker compose build
docker compose up -d
docker compose down
```

Le site sera accessible à l'adresse suivante : `http://localhost:3000`.

## Contribuer

Les contributions à ce projet sont les bienvenues. Vous pouvez ouvrir une issue pour signaler des problèmes ou proposer des améliorations. Vous pouvez également soumettre des pull requests pour contribuer au développement de l'application.
