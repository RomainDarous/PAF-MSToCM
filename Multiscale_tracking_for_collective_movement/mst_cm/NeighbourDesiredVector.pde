/*
Objet permettant, une fois le calcul des vecteurs désirés effectués, 
de retourner le vecteur et une variable indiquant si cette valeur est 
associée à une obstacle ou oiseau voisin du boid considéré
*/

class NeighbourDesiredVector {

    PVector desiredVector; // Le vecteur désiré associé au calcul de la force 
    int neighborCount; // Vaut 1 si l'obstacle ou boid est voisin du boid considéré


    NeighbourDesiredVector(PVector desiredVector, int neighborCount) {
        this.desiredVector = desiredVector;
        this.neighborCount = neighborCount;
    }
}