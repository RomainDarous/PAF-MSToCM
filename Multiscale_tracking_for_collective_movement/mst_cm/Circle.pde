
class Circle implements IDrawable {
    float x;
    float y;
    float r;


    Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    /* Vérifie si un point en particulier appartient à ce sous-arbre, en fonction de sa position par rapport au rectangle */
    boolean contains (Boid b){
        return dist(b.position.x, b.position.y, this.x, this.y) < r;
    }


    void show() {
        noFill();
        strokeWeight(2);
        stroke(95, 211, 189);
        circle(x, y, r*2);
    }
    
}