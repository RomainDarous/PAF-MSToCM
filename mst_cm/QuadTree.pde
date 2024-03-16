/* Un QuadTree contient une référence à ses 4 sous-arbres s'ils sont définis et une référence à sa frontière (rectangle) */
class Quadtree implements IDrawable {
    Rectangle boundary;         
    ArrayList <Boid> boids ;
    boolean divided = false;
    Quadtree northeast; 
    Quadtree northwest;
    Quadtree southeast;
    Quadtree southwest;
    int capacity;
    
    Quadtree (Rectangle rect , int cap){
        this.boundary = rect;
        this.capacity = cap;
        boids = new ArrayList<Boid>();
    }
    
    
    /* Insert un agent dans l'arbre */
    boolean insert (Boid b){
        if (!this.boundary.contains(b)) return false;  // Vérifie que le boid soit dans la bonne zone de l'espace

        if (this.boids.size() <= this.capacity ){ // On ajoute le boid à l'arbre si sa capacité n'a pas été dépassée
            this.boids.add(b); 
            return true;

        } else {

            /* Si la capacité est dépassée l'arbre est subdivisé en 4 fils */

            if (!this.divided) {
                subdivide();
            }

            // On tente d'insérer le boid dans l'un des sous arbres
            return(
                this.northeast.insert(b) ||
                this.northwest.insert(b) ||
                this.southeast.insert(b) ||
                this.southwest.insert(b)
            );
        }
    }

    /* Subdivision de l'arbre en 4 sous arbres */
    void  subdivide (){
        float x = this.boundary.x;
        float y = this.boundary.y;
        float w = this.boundary.width;
        float h = this.boundary.height;
        int newCapacity = capacity > 2 ? capacity - 1 : capacity;
        this.northeast = new Quadtree(new Rectangle (x+w/2 , y-h/2 , w/2 , h/2),newCapacity);
        this.northwest = new Quadtree(new Rectangle (x-w/2 , y-h/2 , w/2 , h/2),newCapacity);
        this.southeast = new Quadtree(new Rectangle (x+w/2 , y+h/2 , w/2 , h/2),newCapacity);
        this.southwest = new Quadtree(new Rectangle (x-w/2 , y+h/2 , w/2 , h/2),newCapacity);
        this.divided = true;  
    }


    /*
    ArrayList<Boid> query(Rectangle range) {

        ArrayList<Boid> found = new ArrayList<Boid>();

        if(!boundary.intersects(range)) {
            return found; // Liste vide
        } else {
            for(Boid b : boids) {
                if(range.contains(b)) {
                    found.add(b);
                }
            }
        }



        // Si l'arbre est divisé, il ne contient aucun point et il faut chercher dans ses sous aarbres
        if(divided) {
            found.addAll(northwest.query(range));
            found.addAll(northeast.query(range));
            found.addAll(southwest.query(range));
            found.addAll(southeast.query(range));
        }

        return found;
    }*/



    /* Renvoie la liste des boids qui se trouvent dans le cercle */
    ArrayList<Boid> query(Circle range, ArrayList<Boid> found) {


        if(found == null) {
            found = new ArrayList<Boid>();
        }
        

        if(!boundary.intersects(range)) {
            return found; // Liste vide
        }

        for(Boid b : this.boids) {
            if(range.contains(b)) {
                found.add(b);
            }
        }
        

        // Si l'arbre est divisé on ajoute a la liste deja existante les boids issus des sous arbres
        if(divided) {
            northwest.query(range, found);
            northeast.query(range, found);
            southwest.query(range, found);
            southeast.query(range, found);
        }

        return found;
    }


    void show (){

        // Affichage du quadTree si la case des paramètres est cochée
        if(showQuadTreeCheckBox.getArrayValue()[0] == 1) {
            this.boundary.show();

            // Affiche les sous arbres s'ils existent
            if (this.divided){
                this.northeast.show();
                this.northwest.show();
                this.southeast.show();
                this.southwest.show();
            }
        }
        
        
    }
    
    
    
}