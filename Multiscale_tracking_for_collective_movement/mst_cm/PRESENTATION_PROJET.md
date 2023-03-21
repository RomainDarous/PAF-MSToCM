# Multiscale Tracking of Collective Movement

## Contexte

“Le QI d'une foule décroît avec le nombre, il suffit d'un impulsion pour la mettre en mouvement comme la ola dans un stade”. Avec ces quelques mots, [[Alain  Leblay]] illustre le pire cauchemar de tous les organisateurs de grands événements : les mouvements de foule. Comprendre, analyser et prédire ce phénomène est la motivation de notre projet. 

## Description

But : l’étude de mouvements de groupe et leur **analyse multi-échelle**. Nous avons donc modélisé le comportement de boids (birds - oïds), représentés par des triangles en mouvement dans un espace à deux dimensions. Le langage de programmation utilisé est le Processing Java.

### Fonctionnalités du projet

- Modélisation décentralisée du mouvement de boids
- Détection de groupes décentralisée, calcul de son enveloppe convexe et affichage des trajectoires des barycentres du groupe et de l’enveloppe convexe.
- Prédiction de trajectoire d’un groupe.
- Enregistrement des trajectoires dans un fichier .csv et affichage en trois dimensions (coordonnées x et y de l’espace en fonction du temps).
- Une interface et des paramètres modulables
-   Optimisation utilisant les arbres quaternaires

## Modélisation des boids

La modélisation des boids repose sur un modèle de Craig Reynold. Il repose sur application de forces au vecteur accélération.

On dit qu’un boid est voisin d’un autre lorsqu’il est dans son champ de vision.
  
#### Force d’alignement
Le vecteur vitesse des boids tend à prendre la valeur du vecteur vitesse moyen de ses voisins.

#### Force de cohésion
le vecteur position des boids tend à prendre la valeur du vecteur vitesse moyen de ses voisins.

#### Force de séparation
Cette force est de norme inversement proportionnelle à la distance entre deux boids voisins et permet d’éviter les collisions inter - boids.

#### Force d’évitement
Cette force s’applique lorsqu’un boid se dirige vers un obstacle. Elle modifie la direction de son vecteur vitesse et permet au boid de longer l’obstacle.


#### Ressources

-   [Boids - a Distributed Behavioral Model) (red3d.com)](https://www.red3d.com/cwr/boids/) (modèle de Craig Reynold) 

-   [Flocking Simulation - YouTube](https://www.youtube.com/watch?v=mhjuuHl6qHM&t=2032s) : implémentation du modèle

-   [Coding Adventure : Boids - YouTube](https://www.youtube.com/watch?v=bqtqltqcQhw&t=383s) : modèle d’obstacles


## Détection de groupe

La détection de groupe s’effectue de manière décentralisée, en étudiant le voisinage de chaque boid. Lorsque deux boids sont suffisamment proches l’un de l’autre et que leur vitesse vérifie certaines conditions, ils appartiennent à un même groupe.

## Calcul de l’enveloppe convexe

Pour calculer l’enveloppe convexe, nous avons utilisé l’algorithme de Jarvis.

Voir [Marche de Jarvis](https://fr.wikipedia.org/wiki/Marche_de_Jarvis).

Notre implémentation retourne une liste de boids telle que le polygone passant le centre des boids est un convexe du plan.

Nous affichons ensuite le barycentre du groupe (moyenne des positions des boids ) et de l’enveloppe convexe (moyenne des positions des boids formants l’enveloppe convexe).


## Sauvegarde des trajectoires

La sauvegarde des trajectoires s’effectue dans le fichier data.csv. Il comporte l’évolution des vecteurs vitesse et position du barycentre de chaque groupe en fonction du temps.

Un script python permet ensuite d’en afficher une représentation en 3D. 
  
## Prédiction de trajectoire d’un groupe  

La prédiction de trajectoire s’appuie sur le modèle de la chute libre. On fait une moyenne du vecteur accélération du barycentre d’un groupe sur les instants précédents, qui nous donne la direction verticale  et le sens de chute libre.

De même, on calcule le vecteur vitesse moyen du barycentre sur les instants précédents. Il donne la direction et le sens du lancer en chute libre équivalent. 

On applique ensuite les lois horaires de la chute libre avec un lancer de vitesse initiale donné.

## Optimisation du programme

Le comportement des boids étant programmé de manière décentralisée (chaque boid a des lois de mouvement qui lui sont propres ; elles ne sont pas définis globalement), cela entraîne une quantité de calcul considérable. Il devenait donc nécessaire, à partir d’un certain nombre de boids à l’écran, d’optimiser les calculs.

Nous avons donc implémenté un **arbre quaternaire**, qui permet de subdiviser la fenêtre  en carrés en fonction de la densité de présence des boids. Cela évite de parcourir, pour chaque boid, la liste de tous les boids pour vérifier s’ils sont dans son champ de vision. On cherche plutôt les subdivisions de l’espace incluses dans le champ de vision d’un boids, puis on se limite aux boids présents dans ces subdivisions.

Cette solution est particulièrement efficace lorsque les boids sont dispersés.

## L’interface utilisateur

Voir le fichier [README.md](README.md)

## INSTALLATION DU PROJET

Voir le fichier [README.md](README.md)