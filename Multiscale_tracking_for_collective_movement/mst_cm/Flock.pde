import java.util.Collections;

class Flock {

    ArrayList<Boid> boids; //Liste de tous les agents
    ArrayList<Groupe> groups; //Liste de tous les groupes
    ArrayList<Obstacle> obstacles; //Liste de tous les obstacles
    int time = 0; // current time step

    Flock() {
        boids  = new ArrayList<Boid>();
        obstacles = new ArrayList<Obstacle>();
        groups = new ArrayList<Groupe>();
    }

    /* ============= Gestion des agents et des obstacles ============= */
    void addBoid(Boid boid) {
        boids.add(boid);
    }

    void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /* ============= Ce que lance draw() à chaque passage de boucles ============= */
    void run() {
     
        ++time;
        
        /* ============= Détection des groupes ============= */
        
        initSituation(); // On ne conserve plus qu'un agent par groupe
        changeNeighbors(); // Calcul des voisins pour chaque boid
        createGroup(); // Formation des groupes
        reajust(); // Cas des groupes formés seulement d'anciens agents leaders
        
        
        /* ============= Actualisation ============= */
        biggestGroupId = getBiggestGroup();
        
        
        for ( Groupe group : groups){
          
          group.updateColor(); //colorisation
          group.pastNumber = group.members.size(); //Nombre de boid par groupe
          
        // Affichage ou non de l'enveloppe convexe
        if(showConvex.getArrayValue()[0] == 1) {
            group.displayConvex();
        }
        
        // Enregistrement dans le csv
        outputStream.write(group.getID() + "," + group.findBarycentre().x + "," + group.findBarycentre().y + "," + group.findBarycentreConvex().x + "," + group.findBarycentreConvex().y + "," +  group.findVelocityBarycentre().x + "," + group.findVelocityBarycentre().y + "," + group.members.size() + "," + groups.size() + "," + plusProcheGroupe(group, groups) + "," + time + "\n");

        }
        
    } // fin de flock.run()
    
    
    // On ne conserve plus qu'un agent (le leader) par groupe 
   void initSituation(){
        Groupe group0 = new Groupe(0);
        
        for (Groupe group : groups){
            int iter = group.members.size();
        
            for(int i = 1; i < iter;  i++){
                
                changeGroup(group.members.get(1), group0);
            } 
        }
   }
    
    
    
    //Formation des groupes
    void createGroup(){

        int res0 = 0;
        for (Groupe group : groups){
            res0 = max(res0,group.getID());
        }
        
        int id_group = res0+1;
        
        for ( Boid b : boids){
        
            if(b.group.id == 0){
            
            
                if (b.numNeighbors_glob == 0) {
                
                    Groupe group = new Groupe(id_group);
                    groups.add(group);
                    group.addBoid(b);
                b.group = group;
                id_group++;
                }
                
                
                else{
                
                    int midID = 0;
                    ArrayList<Integer> res = new ArrayList<Integer>();
                    res.add(midID);
                
                    for (Boid other : b.neighbors_glob){
                            int other_id = other.group.getID();
                            midID = max(midID,other_id);
                            if (!(res.contains(midID))){res.add(midID);}
                    }
                    
                    
                    if (midID == 0){
                        
                        Groupe group = new Groupe(id_group);
                        
                        groups.add(group);
                        b.group = group;
                        group.addBoid(b);
                    
                        for (Boid other : b.neighbors_glob){
                            other.group = group;
                            group.addBoid(other);
                        }

                        id_group++;
                    }
                    
                    else{
                        res.remove(res.indexOf(0));
                        //Avec le plus grand groupe qui reste : 
                        int finalID = biggestGroup(res);
                        
                        Groupe group = getGroupById(finalID);
                        b.group = group;
                        group.addBoid(b);
                        
                    
                        for (int i : res){
                            if (i != finalID){
                                boolean isRemoved = groups.remove(getGroupById(i));
                            }
                        }
                            
                            
                        for (Boid other : boids){
                            if (res.contains(other.group.getID())&&(other.group.getID()!= finalID)){
                                other.group = group;
                                group.addBoid(other);
                            }
                        }
                    }
                }
            }
        }
    } // fin de la formation des groupes
    
    
    // Cas des groupes formés seulement d'anciens agents leaders : plusieurs visins mais ne passera jamais dans la boucle de creategroup()
    void reajust(){
    
        for (int iter = 0; iter < groups.size(); iter++){
        
            Groupe group = groups.get(iter);
            if (group.members.size() == 1){
                
                Boid b = (group.members.get(0));

                if (b.numNeighbors_glob != 0){
                    groups.remove(getGroupById(b.group.getID()));
                    b.group = b.neighbors_glob.get(0).group;
                    b.neighbors_glob.get(0).group.addBoid(b);
                
                }

            }
        }          
    }
    
    
    //Getting neigbors
    void changeNeighbors(){
        for (Boid b0 : boids){
            b0.numNeighbors_glob = 0;
            b0.neighbors_glob = new ArrayList<Boid>();
            for (Boid b : boids){
                boolean isNotEqual = (0 < PVector.dist(b0.position, b.position)); // Les deux agents ne sont pas les mêmes
                
                //Ce qu'on considere comme un bord de la simulation
                int lowThreshold = 30;
                int highThreshold = 30;
                
                /* ============== Comportement au bord de la simulation ============== */
                if( (b0.position.x <= lowThreshold) || (b0.position.y <= highThreshold) || (b0.position.x >= width - highThreshold) || (b0.position.y >= height - highThreshold)) {
                    boolean isInSight = (PVector.dist(b0.position, b.position) < 50); //Petite distance 
                    if (isNotEqual && isInSight){

                        b0.neighbors_glob.add(b);
                        b0.numNeighbors_glob++;
                    }
                }
                
                /* ============== Comportement normal ============== */
                else {
                    boolean isInSight = (PVector.dist(b0.position, b.position) < 20) && (PVector.angleBetween(b0.velocity, b.velocity) < PI/3); //Petite distance 
                    boolean follow = (PVector.dist(b0.position, b.position) < 70) && (PVector.angleBetween(b0.velocity, b.velocity) < PI/4); // Petite différence d'angle des vitesses
                    if (isNotEqual &&(follow||isInSight)){

                        b0.neighbors_glob.add(b);
                        b0.numNeighbors_glob++;
                    }
                }
            }
        }
    }
 
    
   /* ============== Fonctions Auxiliaires ============== */
   
   // Groupe avec le plus d'élément
   int getBiggestGroup() {
      int biggestGroupSize = 0;
      int biggestGroupId = 0;
      for(Groupe groupe : groups) {
          if(groupe.members.size() > biggestGroupSize) {
              biggestGroupId = groupe.id;
              biggestGroupSize = groupe.members.size();
          }
      }
      return biggestGroupId;        
    }
  
   // Pour une liste de groupe donnée, retourne le groupe avec le plus d'agents
   int biggestGroup(ArrayList<Integer> res){
      int id = 0;
      int sol = 0;
      Groupe group;
      for (int i : res){
        group = getGroupById(i); 
        if (sol <= group.pastNumber){
            sol = group.pastNumber;
            id = group.getID();
            
        }
      }
      
      return id;
    
    }
 
 
 
   // Pour les fusions et les séparations : groupe dont le barycentre est le plus proche (celui avec lequel il y a eu fusion ou séparation
   int plusProcheGroupe(Groupe group0, ArrayList<Groupe> groups ){
      PVector bary = group0.findBarycentre();
      float d = 1000000;
      int res = 0;
  
      for (Groupe group : groups){
          group.findBarycentre();
          float new_d = PVector.dist(bary, group.findBarycentre());
          if ((new_d < d)&& (group0.getID() != group.getID())){
              d = new_d;
              res = group.getID();
          }
      }
      return res;
    }
  
   // Groupe d'identifiant dans la liste des groupes
   boolean containsGroup(int id){
        for (Groupe group : groups){
        
        if(id == group.id){return true;}
        
        }
        return false;
    }

   

   // Groupe d'identifiant id ou groupe 0
   Groupe getGroupById(int id){
        for (Groupe group : groups){
        if (group.id == id){return group;}
        }
        return new Groupe(0);
    }
   
   // Pour qu'un agent change de groupe
   void changeGroup(Boid b, Groupe group){
        b.group.removeBoid(b);
        b.group = group;
        group.addBoid(b);
    }
   
     
    
} 
