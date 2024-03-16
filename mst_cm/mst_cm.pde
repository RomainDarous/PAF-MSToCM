import java.io.FileOutputStream;
import java.io.FileNotFoundException;

PrintWriter outputStream=null;
float alignment = 0.25;

Flock flock; // Instance du terrain
Quadtree qtree; // Instance d'arbre quaternaire
Circle queryCircle; 
ArrayList<Boid> querriedBoids;

ArrayList<TrajectoryPredictor> trajectories; // ArrayList des trajectoires prédites pour chaque groupe

int boidNumber; // Nombre de boids initialement sur le terrain
int obstacleNumber; // Nombre d'obstacles initialement sur le terrain
int obstacleSize; // Taille initiale des obstacles

/* Paramètres de gestion de l'UI */
boolean simulationActive = true;
boolean showSettings = false;

PVector center; // Vecteur centre du terrain

int biggestGroupId = 0; // id du plus groupe possédant le plus de boids

void setup() {
    size(900, 900);
    setupUI();

    flock = new Flock(); // Initialisation du terrain
    qtree = new Quadtree (new Rectangle (width/2 , height/2 , width/2 , height/2) , 8); // Initialisation de l'arbre
    
    boidNumber =  150;
    obstacleNumber = 3;
    obstacleSize = 15;

    // Initialisation de la trajectoire
    trajectories = new ArrayList<TrajectoryPredictor>();

    center = new PVector(width/2, height/2);

    // Ajout de tous les agents au terrain
    for (int i = 0; i < boidNumber ; i++) {
        Boid b = new Boid(random(width), random(height));
        flock.addBoid(b); // width-200 pour laisser de la place pour le panneau de contrôle
    }

    // Ajout des obstacles sur le terrain
    for (int i = 0; i < obstacleNumber ; i++) {
        flock.addObstacle(new Obstacle((int) random(width/5, 4*width/5), (int) random(height/5, 4*height/5), obstacleSize));
    } 
    
    
    try{
      // Pour enregistrer dans un csv : 
      outputStream = new PrintWriter( new FileOutputStream(sketchPath()+"\\data.csv",false));
      //Informations choisies :
      outputStream.write("id_group, bary_x, bary_y,bary_convex_x,bary_convex_y, speed_bary_x, speed_bary_y, popu_group, nb_group, plus-proche, time\n");
    }
    
    catch (FileNotFoundException e){
      System.out.println("erreur lors de l'ouverture contractuelle d'un document externe portant le format csv");
      System.exit(0);
    }
}


void draw() {
    
    background(255, 255, 242);

    // La simulation se lance si on ne clique pas sur le bouton "arrêter la simulation" des paramètres
    if(simulationActive) {
        flock.run();

        // Recréation du quadTree
        qtree = new Quadtree (new Rectangle (width/2 , height/2 , width/2 , height/2) , 8); // Initialisation de l'arbre
        
        // Insertion de tous les boids dans l'arbre (n operations)
        for (Boid boid : flock.boids) {
            // Insertion des boids dans le quadtree
            qtree.insert(boid);
        }
        
        // Recuperation des voisins de chaque boid (n log(n) operations en moyenne)
        for (Boid boid : flock.boids) {
            queryCircle = new Circle(boid.position.x, boid.position.y, 60 * quadTreePerceptionRadiusSlider.getValue());

              if(showQuadTreeCheckBox.getArrayValue()[1] == 1) {
                queryCircle.show();
            }
            
            querriedBoids = qtree.query(queryCircle, null);
            boid.flock(querriedBoids, flock.obstacles); // Gestion du comportement au sein des groupes
            boid.update(); // Actualisation de la position et des forces
            boid.edges(); // Gestion des bords du terrain
            if(showBoids.getArrayValue()[0] == 1) {
                boid.show(); // Affichage des agents
            }
            
        }

        // Affichage des obstacles
        for (Obstacle obstacle : flock.obstacles) {
            obstacle.show();
        }

        qtree.show();
    
        // Affihchage de la prédiction de trajectoires si on clique sur le bouton des paramètres associé
        if(showPathPrediction.getArrayValue()[0] == 1) {

            // Création d'un objet TrajectoryPredictor pour chaque groupe non suivi
            for (Groupe group : flock.groups) {
                if (!group.trajectoryPrediction) {
                    trajectories.add(new TrajectoryPredictor(group));
                    group.trajectoryPrediction = true;
                }
            }

            for (int i = 0 ; i < trajectories.size() ; i ++) {
                // Suppression du calcul de trajectoire pour les groupes disparaissant (fusion)
                if (!flock.groups.contains(trajectories.get(i).group)) {
                    trajectories.remove(i);

                }
                else {
                    // Calcul de prédiction de trajectoire des barycentres
                    trajectories.get(i).calculateTrajectory();
                    trajectories.get(i).show();
                }
            }
        }
    
    }
}

void mousePressed() {

    if((showSettings && mouseX > 150) || (!showSettings)) { // Si la barre d'options est affichee, on fait en sorte de ne pas pouvroir placer d'élément derrière
        // Ajout de 5 boids a chaque click
        if(boidObstacleRadioButton.getArrayValue()[0] == 1) {
            for(int i = 0; i< boidNumberSlider.getValue(); i++) {
                flock.addBoid(new Boid(mouseX + random(-10, 10), mouseY + random(-10, 10)));
            }
        }
        
        // AJout d'obstacle au clic
        if(boidObstacleRadioButton.getArrayValue()[1] == 1) {
            flock.addObstacle(new Obstacle(mouseX, mouseY, floor(obstacleRadiusSlider.getValue())));   
        }
    }
    
}
