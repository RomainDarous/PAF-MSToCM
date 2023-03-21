# Manuel d'utilisation

<!-- Affiche du projet -->
<div align="center">
    <img src="readme_images/poster-16-1.png" alt="Logo">
</div>
<br/>

<!-- TABLE DES MATIERES -->
<details>
  <summary>Table des matières</summary>
  <ol>
      <li>
      <a href="#getting-started">Démarrage</a>
      <ul>
        <li><a href="#prerequisites">Prérequis</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Utilisation</a>
        <ul>
            <li><a href="#agent-handle">Gestion des agents</a></li>
            <li><a href="#terrain-handle">Gestion du terrain</a></li>
        </ul>
    </li>
    <li><a href="#display-results">Affichage des résultats</a></li>
  </ol>
</details>

## Démarrage
<div id="getting-started"></div>

### Prérequis
<div id="prerequisites"></div>

* [Processing](https://processing.org/) : Il faut d'abord installer l'environnement de développement et l'IDE Processing pour pouvoir exécuter les fichier PDE
  
* [ControlP5](https://github.com/sojamo/controlp5)
: Cette librairie permet l'affichage de l'interface graphique du programme. Se référer au repo de la librairie pour l'installer sur Processing

* [Python3](https://www.python.org/downloads/)

<br/>

### Installation
<div id="installation"></div>

1. Cloner le dépôt

2. Ouvrir le projet avec l'IDE processing ou vscode (Dans ce cas le chemin d'accès à l'IDE Processing doit être renseigné dans les variables d'environnement)

**ATTENTION ! Il faut que le dossier dans lequel se trouve le projet soit renommé "mst_cm" (nom de la classe d'exécution) et que le chemin d'accès ne comporte pas de dossier dont le nom est composé d'esapces.**

3. S'assurer que les librairies Python `matplotlib, sympy, numpy, csv` sont installées. Exécuter les commandes suivantes dans un invite de commande si ce n'est pas le cas.
```sh
   pip install numpy
   pip install sympy
   pip install csv
   ```



## Utilisation
<div id="usage"></div>

Une fois le programme lancé, l'interface de contrôle permet de modifier la plupart des paramètres du comportement des agents (champ de vision, intensité des forces qui lui sont appliquées...). Elle permet aussi l'affichage de certains paramètres associés à l'environnement dans lequel les agents évoluent.

Les options sont accessibles en cliquant sur le bouton `Paramètres` en haut à gauche.

![Bouton paramètres][param-button]

---
## Gestion des agents
<br/>
<div id="agent-handle"></div>

<!-- Interface de gestion des agents -->
<div align="center">
    <img src="readme_images/boids-manager.png" alt="Logo">
</div>
<br/>

### Vitesse maximale

![Réglage de la vitesse maximale][speed-img]

Tous les agents ont la même vitesse maximale. Elle a cinq valeurs possibles, réglables avec le slider `VITESSE MAXIMALE`.

<br/>

### Champ de vision

![Réglage du champ de vision][visibility-img]

Le champ de vision des agents est modifiable avec le slider `VISIBILITÉ`. Il est fixé par défaut à 100% (vision à 360°).

<div align="center">
    <img src="readme_images/sight-displayed.png" alt="Logo">
    <br/>
    <em>Champ de vision des agents</em>
</div>

<br/>

### Gestion des forces

![Réglage des forces][forces-img]

Pour chacune des forces appliquées aux agents (`alignement`, `séparation`, `cohésion`), il est possible de modifier son intensité et le rayon de perception des agents. Plus le rayon de perception est élevé, plus il y aura d'agents impliqués dans le calcul de ces forces (Cela a un impact sur le comportement des agents puisque ces forces sont déterminées à partir du comportement de leurs voisins).

<br/>

### Errance

![Réglage de l'errance][wander-img]

Ei elle est activée, l'errance (`wander`) ajoute aux agents un léger mouvement aléatoire. Dans le cas où un agent n'appartiendrait à aucun groupe, sa trajectoire ne serait pas parfaitement rectiligne si l'errance était active. 

---

## Gestion du terrain

<br/>

<div id="terrain-handle"></div>

<!-- Interface de gestion du terrain -->
<div align="center">
    <img src="./readme_images/terrain-manage.png" />
</div>

<br/>

### Arbre quaternaire

![Réglage de l'errance][quadtree-img]

Il est possible d'afficher l'arbre quaternaire dans lequel évoluent les agents, ainsi que le rayon de perception qui permet de connître leur voisinnage.
Le slider `RAYON DE PERCEPTION` permet de modifier la taille du voisinnage de chaque agent. Plus il est faible, plus les performances du programme sont bonnes. En revanche, le calcul des forces de cohésion, de séparation et d'alignement est moins précis. 

<!-- Interface de gestion du terrain -->
<div align="center">
    <img src="readme_images/quadtree-displayed.png" alt="Logo">
    <br/>
    <em>Terrain avec arbre quaternaire</em>
</div>
<br/>
<div align="center">
    <img src="readme_images/quadtree-radius-displayed.png" alt="Logo">
    <br/>
    <em>Terrain avec arbre quaternaire et rayon de perception pour la calcul des voisins</em>
</div>

<br/>

### Ajout d'éléments au terrain

![Ajout d'un élément][add-elmt-img]

Pour ajouter des éléments (obstacles ou agents), il faut sélectionner dans la partie `Ajouter un élément` le type à ajouter, puis faire un clique gauche sur le terrain.

Il n'est possible de placer qu'un obstacle à la fois. Le rayon est réglable avec le slider `RAYON DE L'OBSTACLE`.

Pour créer plusieur agents en même temps, il faut choisir, grâce au slider `NOMBRE D'AGENTS A AJOUTER` le nombre souhaité. 

<br/>

### Enveloppe convexe

Cliquer sur la checkBox `ENVELOPPE CONVEXE` permet d'afficher l'enveloppe convexe de chaque groupe.

<div align="center">
    <img src="readme_images/display-convex.png" alt="Logo">
    <br/>
    <em>Affichage de l'enveloppe convexe de chaque groupe</em>
</div>


<br/>

### Prédiciton de trajectoire

Pour chaque groupe, il est possible d'afficher la trajectoire prévue. Elle est recalculée si le groupe s'éloigne trop de la prédiciton.

<div align="center">
    <img src="readme_images/path-predicting.png" alt="Logo">
    <br/>
    <em>Affcichage de la trajectoire des groupes</em>
</div>


<br/>

## Affichage des résultats
<div id="display-results"></div>
<br/>

### Depuis la simulation

La simulation permet d'afficher certaines statistiques concernant les groupes :
* Trajectoire et historique du groupe avec le plus d'agents
* Interpolation de la trajectoire
* Nombre d'agents dans le groupe au cours du temps
* Nombre de groupes sur le terrain au cours du temps

Il faut cliquer sur le bouton `ARRETER LA SIMULATION` pour afficher la liste des courbes disponibles.
Pendant ce temps, la simulation d'arrête. Il suffit de cliquer sur`RELANCER LA SIMULATION` ou `PARAMETRES` pour la relancer.

Attention, il se peut qu'après une simulation trop longue, les courbes mettent trop de temps à charger. On peut alors relancer la simulation et observer d'autres courbes plus rapidement ou exécuter directement le script python depuis la console. 

<br/>

### Depuis la console

Il est possible, après avoir lancé puis arrêter la simulation d'exécuter séparément les scripts python. Il faut ouvrir une console dans le dossier contenant tous les fichiers du programme et écrire :
```sh
python ./<nom_fichier>.py --groupid <groupe_a_afficher>
``` 

#### Nom du fichier à ouvrir en fonction de la courbe désirée

| Courbe désirée | Nom du fichier |
| -------------- | -------------- |
| Trajectoire | AnalyseData.py |
| Interpolation | Interpole.py |
| Nombre d'agents | BoidInGroup.py |
| Nombre de groupes | NombreGroup.py |

<br/>

Exemple : Commande pour afficher la trajectoire interpolée du groupe 5 :

```sh
python ./Interpole.py --groupid 5
```


<!-- SOURCES -->
<!-- Boutons principaux -->
[param-button]: readme_images/param.png

<!-- Gestion des agents -->
[speed-img]: readme_images/speed.png
[wander-img]: readme_images/wander.png
[visibility-img]: readme_images/visibility.png
[forces-img]: readme_images/forces.png

<!-- Gestino du terrain -->
[convex-img]: readme_images/convex.png
[add-elmt-img]: readme_images/add-elmt.png
[quadtree-img]: readme_images/quadtree.png







