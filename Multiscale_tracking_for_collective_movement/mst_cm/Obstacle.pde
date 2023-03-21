/*
Classe permettant de créer des obstacles. Ils ont un radius et une position dans l'espace et un certain design.
*/
class Obstacle {
    int radius;
    PVector position;

    Obstacle(int x, int y, int radius) {
        this.radius = radius;
        this.position = new PVector(x, y);
    }

    void show() {
        noStroke();
        fill(245, 106, 106);
        ellipseMode(RADIUS);
        circle(position.x, position.y, radius);
    }
}