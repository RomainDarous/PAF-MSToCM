/*
Classe de prédiction des trajectoires des barycentres des groupes (calculé avec tous les membres du groupe)
*/

class TrajectoryPredictor {
    Groupe group;
    int previousDataNumber; // Nombre de données passées qu'on utilise pour prédire la trajectoire
    int predictedDataNumber; // Nombre de points qu'on prédit
    int passedPredictedCount; // Compte le nbe de données prédites passées dans le temps
    int fullCount; // Compte qu'on remplit bien une fois les tableaux de valeurs précédentes avant de prédire

    PVector[] previousPosition; // Tableau des vecteurs position précédents du groupe
    PVector[] previousVelocity; // Tableau des vecteurs vitesse précédents du groupe. Première valeur inutilisable - différence de position, la première étant éronée
    PVector[] previousAcceleration; // Tableau des vecteurs accélération précédents du groupe. Deux première valeurs inutilisables - différence de vitesse, la première étant erronée
    PVector[] predictedTrajectory; // Tableau des positiosn prédites
    PVector[] predictedVelocity; // Tableau des vitesses prédites

    PVector currentBarycenterPosition; //Récupère la position courant du barycentre pour la comparée avec la position calculée

    PVector avgAcceleration; // Vecteur accélération moyen calculé sur un certain nombre de position passées - previsousDataNumber

    boolean errorTooHigh; // Permet de déterminer si l'erreur entre la mesure et la courbe est trop importante. Dans ce cas, on recalcule
    float maxError; // Erreur maximale tolérée

    boolean isFull; // Permet de s'assurer qu'on a rempli une première fois les tableaux de prévision de trajectoire

    TrajectoryPredictor(Groupe group) {
        this.group = group;

        this.previousDataNumber = 60;
        this.predictedDataNumber = 200;

        this.previousPosition = new PVector[previousDataNumber];
        this.previousVelocity = new PVector[previousDataNumber];
        this.previousAcceleration = new PVector[previousDataNumber];
        this.predictedVelocity = new PVector[predictedDataNumber];
        this.predictedTrajectory = new PVector[predictedDataNumber];
        this.avgAcceleration = new PVector(0, 0);

        this.isFull = false;
        this.errorTooHigh = false;
        this.maxError = 20;

        this.passedPredictedCount = this.predictedDataNumber - 1;
        this.fullCount = 0;

        initializeVectorArrays();
    }

    // Initialise tous les tableaux avec le vecteur nul
    void initializeVectorArrays() {
        for (int i = 0 ; i < previousDataNumber ; i ++) {
            this.previousPosition[i] = new PVector(0, 0);
            this.previousVelocity[i] = new PVector(0, 0);
            this.previousAcceleration[i] = new PVector(0, 0);
        }

        for (int i = 0; i < predictedDataNumber ; i ++) {
            this.predictedTrajectory[i] = new PVector(0, 0);
            this.predictedVelocity[i] = new PVector(0, 0);
        }
    }

    // Retourne la valeur moyenne du vecteur accélération sur les dernières données
    PVector getAvgAcceleration(PVector[] previousAccelerationValues) {
        PVector average = new PVector(0, 0);

        for (int i = 2 ; i < previousDataNumber ; i ++) {
            average.add(previousAccelerationValues[i]);
        }

        return average.div(previousDataNumber - 2);
    }

    // Retourne la valeur moyenne du vecteur vitesse sur les dernières données
    PVector getAvgVelocity(PVector[] previousVelocityValues) {
        PVector average = new PVector(0, 0);

        for (int i = 1 ; i < previousDataNumber ; i ++) {
            average.add(previousVelocityValues[i]);
        }

        return average.div(previousDataNumber - 2);
    }

    // Calcule l'erreur entre la position calculée du boid et sa position réelle
    float errorValue () {
        int delta = predictedDataNumber;
        float error = dist(predictedTrajectory[this.passedPredictedCount].x, predictedTrajectory[this.passedPredictedCount].y, currentBarycenterPosition.x, currentBarycenterPosition.y);

        for (int i = -1 * delta ; i < delta ; i ++) {
            if(i > 0 && i < predictedDataNumber) {
                float temp_error = dist(predictedTrajectory[i].x, predictedTrajectory[i].y, currentBarycenterPosition.x, currentBarycenterPosition.y);
                if( temp_error < error) {
                    error =  temp_error;
                
                }
            }
        }
        return error;
    }

    /* Calcul de la trajectoire étant donné un vecteur accélération moyen*/
    void calculateTrajectory() {
        // Une fois la prédiction de trajectoire terminée, on met à jour les nouvelles valeurs
        this.currentBarycenterPosition = group.findBarycentre();
        updatePreviousValues();
        if(isFull) {

            if (this.passedPredictedCount == this.predictedDataNumber - 1 || errorTooHigh) {
                this.errorTooHigh = false;
                this.passedPredictedCount = 0;

                this.avgAcceleration = getAvgAcceleration(this.previousAcceleration);
                // Si le tableau a été complété au moins une fois, on peut prédire les trajectoires
                PVector v0 = getAvgVelocity(this.previousVelocity); // Vitesse initiale
                PVector p0 = this.previousPosition[this.previousDataNumber - 1].copy(); // Position initiale

                for (int i = 0; i < this.predictedDataNumber ; i ++) {
                    this.predictedVelocity[i] = v0.add( this.avgAcceleration.copy().mult(i));
                    v0 = this.previousVelocity[this.previousDataNumber - 1].copy();
                    this.predictedTrajectory[i] = this.avgAcceleration.copy().mult( i*i/2 ).add( v0.mult(i)).add( p0 ); // On ajoute le vecteur vitesse
                }
            }   
            else {this.passedPredictedCount += 1;}
            if (this.errorValue() > this.maxError) {
                this.errorTooHigh = true;
            }
        }
    }

    // Mise à jour des valeurs précédentes de coordonnées de trajectoires La valeur la plus récente est la dernière.
    void updatePreviousValues() {
        for (int i = 1 ; i < this.previousDataNumber ; i ++) {
            this.previousPosition[i - 1] = this.previousPosition[i].copy();
            this.previousVelocity[i - 1] = this.previousVelocity[i].copy();
            this.previousAcceleration[i - 1] = this.previousAcceleration[i].copy();
        }

        // Calcul des vecteurs par dérivations successives
        this.previousPosition[this.previousDataNumber - 1] = currentBarycenterPosition;
        this.previousVelocity[this.previousDataNumber - 1] = this.previousPosition[this.previousDataNumber - 1].copy().sub( this.previousPosition[this.previousDataNumber - 2].copy() );
        this.previousAcceleration[this.previousDataNumber - 1] = this.previousVelocity[this.previousDataNumber - 1].copy().sub( this.previousVelocity[this.previousDataNumber - 2].copy() );
        if (fullCount < this.previousDataNumber) fullCount ++ ;
        else isFull = true;
    }

    // Affichage de la trajectoire
    void show() {
        if (isFull) {
            stroke(group.colorRed, group.colorGreen, group.colorBlue);
            strokeWeight(2);
            for (int i = 0 ; i < this.predictedDataNumber ; i ++) {
                    point(predictedTrajectory[i].x, predictedTrajectory[i].y);
            }        
        }
    }
}


