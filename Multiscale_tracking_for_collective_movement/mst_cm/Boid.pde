/*
    Classe modélisant un Bird-oid (agent autonome). Modèle de Craig Reynold
*/
class Boid  implements IDrawable {

    // Paramètres permettant d'indicer les forces intuitivement
    int ALIGN = 0;
    int COHESION = 1;
    int SEPARATION = 2;
    int OBSTACLE_AVOIDANCE = 3;
    int OBSTACLE_SEPARATION = 4;
    int WALL_AVOIDANCE = 5;

    int FORCE_NUMBER = 6;

    PVector position; // Vecteur position du boid
    PVector velocity; // Vecteur vitesse du boid
    PVector acceleration; // Vecteur accélération du boid
    int size; // Parametre de taille du boid

    // intensités maximale des forces inter-boids par défaut
    float maxWanderForce;
    float maxAlignForce; 
    float maxCohesionForce;
    float maxSeparationForce;

    float maxObstacleForce; // intensité maximale des forces obstacle-boids par défaut
    float maxWallForce; // intensité maximale des forces mur-boids par défaut

    float wanderAngle; // Angle utilisé pour l'errance de l'agent
    float sightAngle; // Champ de vision des boids : angle d'ouverture symétrique - varie entre 0 et PI

    float maxSpeed; // Vitesse max des boids

    float anticipationFactor; // Facteur d'anticipation des boids leur évitant de traverser les obstacles. Ne pas modifier cette valeur

    float alignRadius; // Champ de vision par défaut de la force d'alignement
    float cohesionRadius; // Champ de vision par défaut de la force de cohésion
    float separationRadius; // Champ de vision par défaut de la force de séparation
    float obstacleRadius; // Champ de vision par défaut de détection d'obstacles
    float wallRadius ;


    // Attributs détection de groupe
    int numNeighbors_glob; //Nombre de voisins
    ArrayList<Boid> neighbors_glob = new ArrayList<Boid>(); //Liste des voisins
    Groupe group = new Groupe(0); // "Groupe" d'initialisation pour les agents qui n'ont pas encore de groupe 
    color col; // couleur de l'agent

    // Evitement des murs
    float wallOffset = 70;
    float wallAvoidanceForceLimit = 0.03;


    Boid(float x, float y) {
        this.position = new PVector(x, y);
        this.velocity = PVector.random2D();
        this.velocity.setMag(2);
        this.acceleration = new PVector(0, 0);

        this.size = 5; // Parametre de taille du triangle
        this.maxSpeed = maxSpeedSlider.getValue(); // Vitesse maximale de l'agent

        this.maxWanderForce = 0;
        this.maxAlignForce = 0.05; 
        this.maxCohesionForce = 0.05;
        this.maxSeparationForce = 0.05;
        

        this.maxObstacleForce = 15; // Presque infinie par rapport aux autres. Le plus important, c'est d'éviter l'obstacle.
        this.maxWallForce = 3;
        this.anticipationFactor = 2;

        this.wanderAngle = 0; // Angle permettant à l'agent d'avoir un mouvement aléatoire
        this.sightAngle = sightAngleSlider.getValue()* PI/100; // Angle du champ de vision de l'agent (rad)

        this.alignRadius = 50;
        this.cohesionRadius = this.alignRadius;
        this.separationRadius = this.alignRadius / 2;
        this.obstacleRadius = 30;
        this.wallRadius = 5;

    }
 
    /* Mise à jour de la couleur du boid en fonction de son groupe */
    void changeColor(int a, int b, int c){
        col = color(a,b,c);
    }

    /* Gere la collision de l'agent avec le bord */
    void edges() {
        if(this.position.x >= width) {
            this.position.x = width;
            this.velocity.rotate(1);
        } else if(this.position.x <= 0) {
            this.position.x = 0;
            this.velocity.rotate(1);
        }
        
        if(this.position.y >= height) {
            this.position.y = height;
            this.velocity.rotate(1);
        } else if(this.position.y <= 0) {
            this.position.y = 0;
            this.velocity.rotate(1);
        }
    }

    /* Applique une force à l'agent. Doit etre dans la methode flock */
    void applyForce(PVector force) {
        this.acceleration.add(force);
    }



    void update() {
        
        this.maxSpeed = maxSpeedSlider.getValue();
        this.sightAngle = sightAngleSlider.getValue()*PI/100;
        
        this.velocity.add(this.acceleration); // Vitesse obtenue en intégrant l'accélération
        this.velocity.limit(this.maxSpeed); // Limite de vitesse à laquelle peut se déplacer l'agent
        this.position.add(this.velocity); // Position obtenue en intégrant la vitesse

        if(wanderForceCheckBox.getArrayValue()[0] == 1) this.wander(); // Mouvement aléatoire de errance
    }

    /* Gère le comportement de l'agent dans un groupe. Elle applique à l'agent les forces :
        - alignement, pour que l'agent suive la direction générale du groupe
        - cohesion, pour que l'agent s'ajoute à un groupe
        - separation, pour maintenir une distance minimale entre l'agent et les autres membres du groupe */
    void flock(ArrayList<Boid> boids, ArrayList<Obstacle> obstacles) {
        this.acceleration.set(0, 0); // L'accélération est recalculée en fonction de la force à chaque actualisation
        
        PVector[] forces = calculatedForces(boids, obstacles);
        for (PVector force : forces) {
            applyForce(force);
        }
    }


    /* Permet à l'agent d'avoir un mouvement en partie aléatoire, pour ne pas se déplacer qu'en ligne droite s'il n'appartient à aucun groupe.
        Elle génère un cercle devant l'agent.
        A chaque actualisation on prend un point sur ce cercle. Il est choisi aléatoirement autour de la direction du vecteur vitesse.
        A partir de ce point, on crée une force qui force l'agent à aligner son vecteur vitesse sur ce point */
    void wander() {

        // vecteur 100 pixels devant qui correspondra à la vitesse désirée après process.
        PVector wanderPoint = this.velocity.copy(); // Initialement, wanderPoint est aligné avec la vitesse
        wanderPoint.setMag(100);
        wanderPoint.add(this.position);
        
        int wanderRadius = 25; // Rayon du cercle sur lequel on prend les valeurs d'angle

       /* if(wanderShowCheckBox.getArrayValue()[0] == 1) {
            fill(245, 106, 106);
            noStroke();
            circle(wanderPoint.x, wanderPoint.y, 3); 
            noFill();
            stroke(138, 175, 255);
            circle(wanderPoint.x, wanderPoint.y, wanderRadius); 
        }*/
        

        float theta = this.wanderAngle + this.velocity.heading(); // L'angle correspondant au point sélectionné sur le cercle est calculé à partir de la direction du vecteur vitesse

        // Conversion de l'angle sélection en coordonnées cartésiennes
        float x = wanderRadius*cos(theta);
        float y = wanderRadius*sin(theta);

        wanderPoint.add(x,y); // Fait dévier le vecteur wanderPoint initialement aligné avec le vecteur vitesse

        /*if(wanderShowCheckBox.getArrayValue()[0] == 1) {
            fill(95,211,189);
            noStroke();
            circle(wanderPoint.x, wanderPoint.y, 3);
        }*/
            

        PVector steering = wanderPoint.sub(this.position); // Force à appliquer a l'agent pour le faire dévier vers le point désiré
        steering.limit(this.maxWanderForce); // Norme de la force limitée pour que l'agent ne s'aligne pas instantanément sur la position calculée.

        //steering.mult(wanderSlider.getValue());

        applyForce(steering);

        float displaceRange = 2;
        this.wanderAngle = random(-displaceRange, displaceRange);
    }


    PVector[] calculatedForces(ArrayList<Boid> boids, ArrayList<Obstacle> obstacles) {

        // Variable de retour
        PVector[] forces = new PVector[FORCE_NUMBER];

        // Paramètres globaux

        NeighbourDesiredVector ndv; // variable pour récupérer les résultats de calcul intermédiaires
        float d_boid; // distance  inter-boids

        int[] neighborCountList = new int[FORCE_NUMBER]; // Liste des nombres de boids / obstacles dans le champ de vision pour une force donnée
        PVector[] steeringForceList = new PVector[FORCE_NUMBER]; // Liste dse forces à appliquer au boid
        PVector[] desiredVectorList = new PVector[FORCE_NUMBER]; // Liste des vecteurs (poisition, vitesse) vers lesquels ont veut faire tendre les vecteurs du boid.

        // Initialisation des listes
        for (int i = 0 ; i < FORCE_NUMBER; i++) {
            neighborCountList[i] = 0;
            steeringForceList[i] = new PVector(0, 0);
            desiredVectorList[i] = new PVector(0, 0);
            forces[i] = new PVector(0, 0);
           
        }

        // Application des forces d'interaction boid - boid
        for (Boid other : boids) {

            // Calcul de la position entre deux boids
            d_boid = dist(this.position.x, this.position.y, other.position.x, other.position.y);

            // Force d'alignement
            ndv = alignSpeed(other, d_boid);
            desiredVectorList[ALIGN].add( ndv.desiredVector );
            neighborCountList[ALIGN] += ndv.neighborCount;

            // Force de cohésion
            ndv = cohesionPosition(other, d_boid);
            desiredVectorList[COHESION].add( ndv.desiredVector );
            neighborCountList[COHESION] += ndv.neighborCount;

            // Force de séparation
            ndv = separationSpeed(other, d_boid);
            desiredVectorList[SEPARATION].add( ndv.desiredVector );
            neighborCountList[SEPARATION] += ndv.neighborCount;
        }

    
        forces[ALIGN] = alignForce(desiredVectorList[ALIGN], neighborCountList[ALIGN]);
        forces[ALIGN].mult(alignForceSlider.getValue());

        forces[COHESION] = cohesionForce(desiredVectorList[COHESION], neighborCountList[COHESION]);
        forces[COHESION].mult(cohesionForceSlider.getValue());
        
        forces[SEPARATION] = separationForce(desiredVectorList[SEPARATION], neighborCountList[SEPARATION]);
        forces[SEPARATION].mult(separationForceSlider.getValue());
        
        forces[OBSTACLE_AVOIDANCE] = obstacleAvoidanceForce(obstacles);
        //forces[OBSTACLE_AVOIDANCE].mult(obstacleAvoidanceSlider.getValue());

        forces[WALL_AVOIDANCE] = wallAvoidance2();

        return forces;
    }

    // Calcule la vitesse à appliquer au boid étant donné le vecteur vitesse d'alignement désiré
    NeighbourDesiredVector alignSpeed(Boid other, float d) {

        PVector desiredSpeed = new PVector(0, 0);
        this.alignRadius = this.alignRadius * alignPerceptionRadiusSlider.getValue(); // Distance maximale à laquelle doit se trouver un autre agent pour être considéré comme son voisin
        int neighborCount = 0; // Compte le nombre de voisins de l'agent à une distance plus petite de 100 

        PVector comparisonVector = PVector.sub(other.position, this.position);
        // Calcule la distance du boid avec tous les autres
        float diffAngle = PVector.angleBetween(comparisonVector, this.velocity);
        
        if((d < alignRadius) && (other != this) && (diffAngle < this.sightAngle)) {
            desiredSpeed.add(other.velocity); // Prend en compte la vitesse des voisins les plus proches du boid
            neighborCount++;

        }

        return new NeighbourDesiredVector(desiredSpeed, neighborCount);
    }

    // Calcule la force à appliquer à l'accélération du boid étant donné le vecteur vitesse d'alignement désiré
    PVector alignForce(PVector desiredSpeed, int neighborCount) {

        PVector steering = new PVector(0,0);
        if(neighborCount >  0) {
            desiredSpeed.div(neighborCount); // Valeur moyenne des vecteurs vitesse de tous les voisins de l'agent
            desiredSpeed.setMag(this.maxSpeed);
            steering = desiredSpeed.sub(this.velocity); // Force à appliquer au boid pour qu'il change de direction
            steering.limit(this.maxAlignForce); // Limite la force pour qu'il ne tourne pas instantanément
        }

        return steering;
    }

    // Calcule une position voulue du boid comme la moyenne des positions de ses voisins
    NeighbourDesiredVector cohesionPosition(Boid other, float d) {

        PVector desiredPosition = new PVector(0, 0);
        this.cohesionRadius = this.cohesionRadius * alignPerceptionRadiusSlider.getValue(); // Distance maximale à laquelle doit se trouver un autre agent pour être considéré comme son voisin
        int neighborCount = 0; // Compte le nombre de voisins de l'agent à une distance plus petite de 100 

        PVector comparisonVector = PVector.sub(other.position, this.position);
        // Calcule la distance du boid avec tous les autres
        float diffAngle = abs(PVector.angleBetween(comparisonVector, this.velocity));
        
        if( (other != this) && (d < cohesionRadius) && (diffAngle < this.sightAngle)) {
            desiredPosition.add(other.position); // Prend en compte la position des voisins les plus proches du boid
            neighborCount++;
        }

        return new NeighbourDesiredVector(desiredPosition, neighborCount);
    }

    // Calcule la force à appliquer au vecteur accélération étant donné la position désirée du boid
    PVector cohesionForce(PVector desiredPosition, int neighborCount) {

        PVector steering = new PVector(0,0);

        if(neighborCount >  0) {
            desiredPosition.div(neighborCount); // Position moyenne de tous les voisins de l'agent (equivalent à une force)
            desiredPosition.sub(this.position); // Force à appliquer au boid pour qu'il change de direction
            desiredPosition.setMag(this.maxSpeed); // Fixe la nome du vecteur pointant vers la position désirée à maxSpeed
            steering = desiredPosition.sub(this.velocity);
            steering.limit(this.maxCohesionForce); // Limite la force pour qu'il ne tourne pas instantanément
        }
        
        return steering;
    }

    // Calcule le vecteur vitesse désiré pour éviter la collision entre boids
    NeighbourDesiredVector separationSpeed(Boid other, float d) {

        int neighborCount = 0; // Compte le nombre de voisins de l'agent à une distance plus petite de 100 
        this.separationRadius = this.separationRadius * separationPerceptionRadiusSlider.getValue(); // Distance maximale à laquelle doit se trouver un autre agent pour être considéré comme son voisin
        PVector desiredSpeed = new PVector(0, 0);
        PVector mergeSpeed = new PVector(0, 0); // La vitesse de rapprochement de bois et d'un voisin

        PVector comparisonVector = PVector.sub(other.position, this.position);
        // Calcule la distance du boid avec tous les autres
        float diffAngle = PVector.angleBetween(comparisonVector, this.velocity);

        if((d < separationRadius) && (other != this) && (diffAngle < this.sightAngle)) { 
            mergeSpeed = PVector.sub(this.position, other.position);
            mergeSpeed.div(d*d);
            desiredSpeed.add(mergeSpeed); // Prend en compte la vitesse des voisins les plus proches du boid
            neighborCount++;
        }
        return new NeighbourDesiredVector(desiredSpeed, neighborCount);
    }

    // Calcule le vecteur force à appliquer à l'accélération du boid pour éviter la collision entre boids
    PVector separationForce(PVector desiredSpeed, int neighborCount) {

        PVector steering = new PVector(0,0);

        if(neighborCount >  0) {
            desiredSpeed.div(neighborCount); // Position moyenne de tous les voisins de l'agent (equivalent à une force)
            desiredSpeed.setMag(this.maxSpeed); // Fixe la nome du vecteur pointant vers la position désirée à maxSpeed
            steering = desiredSpeed.sub(this.velocity);
            steering.limit(this.maxSeparationForce); // Limite la force pour qu'il ne tourne pas instantanément
        }

        return steering;
    }

    /*
    Calcul de la vitesse désirée pour éviter l'obstacle en le longeant.
    */
    PVector obstacleAvoidanceForce(ArrayList<Obstacle> obstacles) {
        
        float d;

        PVector obstacleAvoidanceSpeed = new PVector(0, 0);
        PVector orientedFieldOfView = new PVector(0, 0);
        PVector steeringForce = new PVector(0, 0);


        // Paramètres angulaires
        float numPoints = 170f;
        float step = 1/numPoints;
        float theta = 0;
        float i = 0;

        PVector positionCopy = this.position.copy();
        PVector velocityCopy = this.velocity.copy();

        for (Obstacle obstacle : obstacles) {

            /*
            Tant que le boid regarde dans la direction de l'obstacle et que la distance le séparant de ce dernier est inférieur à son champ de vision, 
            on cherche à faire tourner le vecteur vitesse de sorte que le boid puisse longer l'obstacle
            */
            do {
                positionCopy = this.position.copy();
                velocityCopy = this.velocity.copy();

                theta = 2* PI * step * i;

                // Point du champ de vision orienté par le vecteur vitesse du boid, puis calcul de la distance avec le centre de l'obstacle
                orientedFieldOfView = positionCopy.add(velocityCopy.rotate(theta).setMag(obstacleRadius));
                d = dist(orientedFieldOfView.x, orientedFieldOfView.y, obstacle.position.x, obstacle.position.y);

                // On balaye les angles de rotations possibles du vecteur position à la manière d'un essui-glace, pour trouver le plus petit angle convenant (en valeur absolue)
                if( i <= 0) i = abs(i) + 1;
                else i = -1*i;

                if (i == numPoints || i == -1 * numPoints) {
                    println("Excès de boucle");
                    break;
                }

            } while(d < anticipationFactor*obstacle.radius); // On augmente de valeur de d requise pour améliorer l'anticipation des boids.
        
            if (theta != 0) {
                obstacleAvoidanceSpeed.add(velocityCopy.rotate(theta));
                // Calcul de la force de Steering à appliquer, en la limitant à une valeur max. La vitesse a une norme définie
                obstacleAvoidanceSpeed.setMag(maxSpeed);
                steeringForce = obstacleAvoidanceSpeed.sub(this.velocity);
                steeringForce.limit(maxObstacleForce);
                return steeringForce;
            }
            i = 0;
            theta = 0;  
        }      
        return steeringForce;
    }

    // Calcul de la force à appliquer au boid pour éviter les murs
    /*PVector wallAvoidance() {

        float d_left = dist(this.position.x, this.position.y, 0, this.position.y);
        float d_right = dist(this.position.x, this.position.y, width, this.position.y);
        float d_bottom = dist(this.position.x, this.position.y, this.position.x, 0);
        float d_top = dist(this.position.x, this.position.y, this.position.x, height);

        PVector leftPosition = new PVector(0, this.position.y);
        PVector rightPosition = new PVector(width, this.position.y);
        PVector bottomPosition = new PVector(this.position.x, 0);
        PVector topPosition = new PVector(this.position.x, height);


        PVector steeringForce = new PVector(0, 0);
        PVector wallAvoidanceSpeed = new PVector(0, 0);


        int neighborCount = 0; // Compte le nombre de voisins de l'agent à une distance plus petite de 100 
        PVector desiredSpeed = new PVector(0, 0);
        PVector mergeSpeed = new PVector(0, 0); // La vitesse de rapprochement de bois et d'un voisin


        if(d_left < wallRadius) { 
            mergeSpeed = PVector.sub(this.position, leftPosition);
            mergeSpeed.div(d_left);
            desiredSpeed.add(mergeSpeed); // Prend en compte la vitesse des voisins les plus proches du boid
            neighborCount++;
        }
        if(d_right < wallRadius) { 
            mergeSpeed = PVector.sub(this.position, rightPosition);
            mergeSpeed.div(d_right);
            desiredSpeed.add(mergeSpeed); // Prend en compte la vitesse des voisins les plus proches du boid
            neighborCount++;
        }
        if(d_top < wallRadius) { 
            mergeSpeed = PVector.sub(this.position, topPosition);
            mergeSpeed.div(d_top);
            desiredSpeed.add(mergeSpeed); // Prend en compte la vitesse des voisins les plus proches du boid
            neighborCount++;
        }
        if(d_bottom < wallRadius) { 
            mergeSpeed = PVector.sub(this.position, bottomPosition);
            mergeSpeed.div(d_bottom);
            desiredSpeed.add(mergeSpeed); // Prend en compte la vitesse des voisins les plus proches du boid
            neighborCount++;
        }

        PVector steering = new PVector(0,0);

        if(neighborCount >  0) {
            desiredSpeed.div(neighborCount); // Position moyenne de tous les voisins de l'agent (equivalent à une force)
            desiredSpeed.setMag(this.maxSpeed); // Fixe la nome du vecteur pointant vers la position désirée à maxSpeed
            steering = desiredSpeed.sub(this.velocity);
            steering.limit(this.maxWallForce); // Limite la force pour qu'il ne tourne pas instantanément
        }

        return steering;
    }*/

    /* Evitement des murs */
    PVector wallAvoidance() {
        float defaultMaxAlignForce = this.maxAlignForce;
        float defaultMaxCohesionForce = this.maxCohesionForce;
        float defaultMaxSeparationForce = this.maxSeparationForce; 

        PVector steering = new PVector(0, 0);
        
        if(calculateDistToWall() != 0) {
            this.maxAlignForce = 0.01; 
            this.maxCohesionForce = 0.01;
            this.maxSeparationForce = 0.01;
            steering = new PVector().sub(center, this.position).setMag(this.maxSpeed).sub(this.velocity);
        } else {
            this.maxAlignForce = defaultMaxAlignForce; 
            this.maxCohesionForce = defaultMaxCohesionForce;
            this.maxSeparationForce = defaultMaxSeparationForce;
        }

        steering.limit(this.wallAvoidanceForceLimit);
        return steering;
    }

    /* Calcule la distance entre l'agent et le mur le plus proche */
    float calculateDistToWall() {
        if(this.position.x > width - wallOffset) return  width - this.position.x;
        if(this.position.x < wallOffset)  return this.position.x;
        if(this.position.y > height - wallOffset) return height - this.position.y;
        if(this.position.y < wallOffset) return this.position.y;
        return 0;
    }

    PVector wallAvoidance2() {
        PVector steering = new PVector(0, 0);
       
        
        if(calculateDistToWall() != 0) {
             //PVector desiredVel = new PVector(0, 0);
            PVector centerVect = new PVector(center.x - this.position.x, center.y - this.position.y);
            float ang2 = this.velocity.heading() - centerVect.heading();
            
            if(ang2 > 0 && ang2 < PI) {
                steering = this.velocity.copy().rotate(-ang2/2).setMag(this.maxSpeed).sub(this.velocity);
            } 
            if(ang2 > PI) {
                steering = this.velocity.copy().rotate(+ang2/2).setMag(this.maxSpeed).sub(this.velocity);
            }

            if(ang2 < 0 && ang2 > -PI) {
                steering = this.velocity.copy().rotate(-ang2/2).setMag(this.maxSpeed).sub(this.velocity);
            }
            if(ang2 < -PI) {
                steering = this.velocity.copy().rotate(-ang2/2).setMag(this.maxSpeed).sub(this.velocity);
            }

            //steering = desiredVel.sub(this.velocity);
        
        }
        steering.limit(0.1);
        
        return steering;
    }



    // ========================= AFFICHAGE BOID + DEBUG ============================

    /* Gère l'affichage de l'agent */
    void show() {
        noStroke();
        fill(22, 34, 108);
        
        push();
        translate(this.position.x, this.position.y); // Translation à la position de l'agent
        rotate(this.velocity.heading()); // L'agent pointe vers la cible
        triangle(-this.size, -this.size/2, -this.size, this.size/2, this.size, 0); // Coordonnées des points du triangle
        pop();

        if(showSightAngleCheckBox.getArrayValue()[0] == 1) {
            showFov();
        }
        

    }


    /*void showFovIfChecked(CheckBox controller, float perceptionRadius) {
        if(controller.getArrayValue()[0] == 1) {
            float currentHeading = this.velocity.heading();
            pushMatrix();
            translate(this.position.x, this.position.y);
            rotate(currentHeading);
            fill(95, 211, 189, 90);
            arc(0, 0, perceptionRadius*2, perceptionRadius*2, -this.sightAngle, this.sightAngle);
            popMatrix();
        }
    }*/

    void showFov() {
            float currentHeading = this.velocity.heading();
            pushMatrix();
            translate(this.position.x, this.position.y);
            rotate(currentHeading);
            fill(95, 211, 189, 90);
            arc(0, 0, 15*2, 15*2, -this.sightAngle, this.sightAngle);
            popMatrix();
    }

}
