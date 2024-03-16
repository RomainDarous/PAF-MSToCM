/* Rectangle défini avec un son centre, sa demi-longueur et sa demi-largeur */
class Rectangle implements IDrawable {
    float x;
    float y;
    float height ; 
    float width;
 
    Rectangle (float x , float y , float width , float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    /* Vérifie si un point en particulier appartient à ce sous-arbre, en fonction de sa position par rapport au rectangle */
    boolean contains (Boid b){
        return  b.position.x <= this.x+this.width &&
                b.position.x >= this.x-this.width &&
                b.position.y <= this.y+this.height &&
                b.position.y >= this.y-this.height ;
    }

    /* Vérifie si un sous arbre intersecte la zone dans laquelle on veut récupérer les boids */
    boolean intersects(Rectangle range) {

        if((range.x-range.width > this.x+this.width) || (range.x+range.width < this.x-this.width) || (range.y+range.height < this.y-this.height) || (range.y-range.height > this.y+this.height)) {
            return false;
        } else return true;
    }


     /* Vérifie le rectangle intersecte un cercle */
    boolean intersects(Circle range) {

        // Vecteur entre le centre du rectangle et le centre du cercle
        PVector circleDistance = new PVector(abs(range.x - this.x), abs(range.y - this.y));

        // Traite le cas ou le cercle ne peut pas intersecter le rectangle parce que leur distance est plus grande que la longueur ou la largeur du rectangle
        if((circleDistance.x > this.width + range.r) || (circleDistance.y > this.height + range.r)) return false;

        if((circleDistance.x <= this.width + range.r) || (circleDistance.y <= this.height + range.r)) return true;

        // Traite le cas ou le cercle est proche d'un bord du rectangle

        // Pythagore
        float distance = pow((circleDistance.x-this.width), 2) + pow((circleDistance.y-this.height),2);

        return distance <= pow(range.r,2);
    }

    void show() {
        stroke(138, 175, 255);
        strokeWeight(1);
        noFill();
        rectMode(CENTER);
        rect (x , y , width*2 ,height*2);
    }

}