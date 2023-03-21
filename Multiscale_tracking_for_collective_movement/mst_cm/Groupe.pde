
class Groupe {

  /* ============== Couleur du groupe ============== */
  int colorRed;
  int colorBlue;
  int colorGreen;
  
  int[][] possibleColors; //Tableau de toutes les couleurs possibles (Aurait pu être une variable de classe):
  
  /* ============== Identifiant ============== */
  int id;
  
  /* ============== Membre =================== */
 ArrayList<Boid> members;
 
 ArrayList<Boid> members_limit; //Seulement ceux de l'enveloppe convexe

 int pastNumber; //Nombre de boid au temps précédent
 
 /* ============== Trajectoire =================== */
 boolean trajectoryPrediction;
 
 
  /* ============ Intialisation d'un groue vide d'identifiant id ============ */
 Groupe(int id){
  
  this.id = id;
  members = new ArrayList<Boid>();
  members_limit = convexFonction();
  pastNumber = 0;

/* Initialisation des couleurs */
  possibleColors = initColor();
  int rand = int(random(20));
  colorRed = possibleColors[rand][0];
  colorGreen = possibleColors[rand][1];
  colorBlue = possibleColors[rand][2];
  
  
  this.trajectoryPrediction = false;
  
  }
  
  
  /* ============= Gestion des agents ============= */
  
  void removeBoid(Boid b){
    
    members.remove(b);
  }
  
  void addBoid(Boid b){
  
    members.add(b);
  
  }

  /* ============= Mise à jour de la couleur de chaque agent du groupe ============= */
  void  updateColor(){

  for (Boid b : members){
            b.changeColor(colorRed,colorGreen,colorBlue);
          }
  }
  
  //Getteur id
  int getID(){
  
    return id;  
    
  }
  
  /* ============= Affichage de l'enveloppe convexe ============= */ 

  void displayConvex(){
  
    members_limit = convexFonction();
    stroke(colorRed,colorGreen, colorBlue);
    
    //On relie les agents de l'enveloppe 2 à 2
    for (int i = 0; i < members_limit.size()-1; i++){
      
      //Agent 1
      float x1 = members_limit.get(i).position.x;
      float y1 = members_limit.get(i).position.y;
       
      //Agent 2
      float x2 = members_limit.get(i+1).position.x;
      float y2 = members_limit.get(i+1).position.y;
      strokeWeight(3);
      line(x1,y1,x2,y2);
      
      } 
      
      if (members_limit.size()!= 0){
      
      //On relie le dernier et le premier
        
      line(members_limit.get(members_limit.size()-1).position.x, members_limit.get(members_limit.size()-1).position.y, members_limit.get(0).position.x, members_limit.get(0).position.y);
    
      }

    strokeWeight(10);
    point(findBarycentre().x, findBarycentre().y);
  }
 
 
  /* ============= Deux façons alternatives de choisir le leader ============= */
  //Plus proche du barycentre
  Boid getLeader(){
    PVector bary = findBarycentre();
    float res = 10000000;
    
    Boid leader = members.get(0);
    
    for (Boid b : members){
      if (PVector.dist(bary, b.position) < res ){
        
        res = PVector.dist(bary, b.position);
        leader = b;
      }
    }
    
    return leader;
}

// Celui avec le plus de voisins
Boid getLeader2(){
    
    Boid leader = members.get(0);
    
    for (Boid b : members){
      if (b.numNeighbors_glob > leader.numNeighbors_glob ){
       
        leader = b;
      }
    }
    
    return leader;
}

  /* ============= Informations générales sur le groupe ============= */
  
  //Barycentre
  PVector findBarycentre(){

    int nb = members.size();
    float bary_x = 0;
    float bary_y = 0;
    
    for (Boid b : members){
      
      bary_x += (b.position.x)/nb;
      bary_y += (b.position.y)/nb;
    }
    
    return new PVector(bary_x,bary_y);
    
  }
  
  //Vitesse moyenne ou vitesse du barycentre
  PVector findVelocityBarycentre(){
  
  
    PVector res = new PVector(0,0);
    
    for (Boid b : members){
      
      res.x += (b.velocity.x)/members.size();
      res.y += (b.velocity.y)/members.size();
      
    }
    
    return res;
  
  }



  // Barycentre des agents de l'enveloppe convexe seulement
  PVector findBarycentreConvex(){
  
    ArrayList<Boid> liste = convexFonction();
    int nb = liste.size();
    float bary_x = 0;
    float bary_y = 0;
    
    for (Boid b : liste){
      
      bary_x += (b.position.x)/nb;
      bary_y += (b.position.y)/nb;
    }
    
    return new PVector(bary_x,bary_y);
    
  }

  /* =========== Autres affichages  =========== */
  //Aucune de ces fonctions ne sont utilisées pour l'instant mais elles ont été utiles au cours du projet et peuvent le redevenir
  
  //Affichage barycentre
  void displayBarycentres(){
   
    strokeWeight(10);
    stroke(colorRed,colorGreen, colorBlue,50);
    
    
    float bary_x = findBarycentre().x;
    float bary_y = findBarycentre().y;
    point( bary_x +1000, bary_y);
    
    
  }

  //Affichage du barycentre de l'envellope convexe
  void displayBarycentresConvex(){
    
    
    strokeWeight(10);
    stroke(colorRed+20,colorGreen+20, colorBlue+20,50);
    
   
    
    float bary_x = findBarycentre().x;
    float bary_y = findBarycentre().y;
    float baryConvex_x = findBarycentreConvex().x;
    float baryConvex_y = findBarycentreConvex().y;
    
   
    
    point( baryConvex_x +1000, baryConvex_y);
    
    strokeWeight(1);
    stroke(0);
    
    double distanceBary= Math.sqrt(Math.pow(baryConvex_x-bary_x,2)+Math.pow(baryConvex_y-bary_y,2));
    
    text((String.valueOf(distanceBary)),800,800+9*id,20);
  
    
   
  }
  
  //Affichage du graphe comparant les deux barycentres
  void displayGraph(int time){
    
    line(1050,550,1050,900);
    line(1050,900,1850,900);
    float maxvalue=30;
    float ymax=900;
    float ymin = 550;
    float xmin = 1050;
    float xmax = 1850;
    
    float bary_x = findBarycentre().x;
    float bary_y = findBarycentre().y;
    float baryConvex_x = findBarycentreConvex().x;
    float baryConvex_y = findBarycentreConvex().y;
    
    if (time%(xmax-xmin)==0){
      fill(250);
      noStroke();
      rect(xmin-10,ymin,xmax,ymax-10);
    }
  
    double distanceBary= Math.sqrt(Math.pow(baryConvex_x-bary_x,2)+Math.pow(baryConvex_y-bary_y,2));
    //distanceBary=(float)distanceBary;
    
    fill(colorRed,colorGreen, colorBlue);
    noStroke();
    circle(xmin+(time%(xmax-xmin)),(float)(ymax-((ymax-ymin)/maxvalue)*distanceBary),10);
    fill(0);
  
  
  
  
  }

  /* =========== Calcul de l'enveloppe convexe =========== */
  //On utilise ici l'algorithme de Jarvis
  
  
  ArrayList<Boid> convexFonction(){
    
    ArrayList<Float> listeX = this.positionsX();
    ArrayList<Float> listeY = this.positionsY();
    int n = listeX.size();
       
    ArrayList<Boid> res = new ArrayList();
    
    
    // There must be at least 3 points
    if (n < 3) return(members);
  
    
  
    // Find the leftmost point
    int l = 0;
    for (int i = 1; i < n; i++){
        if (listeX.get(i) < listeX.get(l))
            l = i;
    }
    
    
    // Start from leftmost point, keep moving
    // counterclockwise until reach the start point
    // again. This loop runs O(h) times where h is
    // number of points in result or output.
    int p = l;
    int q = l;
    do
    {
        // Add current point to result
        res.add(members.get(p));
  
        // Search for a point 'q' such that
        // orientation(p, q, x) is counterclockwise
        // for all points 'x'. The idea is to keep
        // track of last visited most counterclock-
        // wise point in q. If any point 'i' is more
        // counterclock-wise than q, then update q.
        q = (p + 1) % n;
         
        for (int iter = 0; iter < n; iter++)
        {
           // If i is more counterclockwise than
           // current q, then update q
           if (orientation(listeX.get(p),listeY.get(p),listeX.get(iter),listeY.get(iter),listeX.get(q),listeY.get(q))== 2)
               q = iter;  
        }
        
        // Now q is the most counterclockwise with
        // respect to p. Set p as q for next iteration,
        // so that q is added to result 'hull'
        p = q;
  
    } while (p != l);  // While we don't come to first
                       // point       

  return res;  
}

  int orientation(float px, float py, float qx, float qy, float rx, float ry){
      float val = (qy - py) * (rx - qx) -(qx - px) * (ry - qy);
    
      if (val == 0) return 0;  // collinear
      return (val > 0)? 1: 2; // clock or counterclock wise
  }
  
      

  ArrayList<Float> positionsX (){
          
    ArrayList<Float> listeX = new ArrayList();
   
    for ( Boid b : members){
        listeX.add(b.position.x);
    }
    
    return(listeX);
  }

  ArrayList<Float> positionsY (){
      
    ArrayList<Float> listeY = new ArrayList();
  
    for ( Boid b : members){
        listeY.add(b.position.y);
    }
    
    return(listeY);
  }


// =========== Couleurs possibles que peut prendre un groupe =========== */ 
  int[][] initColor(){
    
     int[][] res = new int[21][3];
     
     int[] c1 = new int[3];
     c1[0] = 140; c1[1] = 148; c1[2] = 155;
     res[0]=c1;
   
     c1 = new int[3];
     c1[0] = 230; c1[1] = 230; c1[2] = 250;
     res[1]=c1;
     
     c1 = new int[3];
     c1[0] = 176; c1[1] = 224; c1[2] = 230;
     res[2]=c1;
     
     c1 = new int[3];
     c1[0] = 173; c1[1] = 216; c1[2] = 230;
     res[3]=c1;
     
     c1 = new int[3];
     c1[0] = 135; c1[1] = 206; c1[2] = 250;
     res[4]=c1;
     
     c1 = new int[3];
     c1[0] = 0; c1[1] = 191; c1[2] = 255;
     res[5]=c1;
     
     c1 = new int[3];
     c1[0] = 176; c1[1] = 196; c1[2] = 222;
     res[6]=c1;
     
     c1 = new int[3];
     c1[0] = 30; c1[1] = 144; c1[2] = 255;
     res[7]=c1;
     
     c1 = new int[3];
     c1[0] = 70; c1[1] = 130; c1[2] = 180;
     res[8]=c1;
     
     c1 = new int[3];
     c1[0] = 100; c1[1] = 149; c1[2] = 237;
     res[9]=c1;
     
     c1 = new int[3];
     c1[0] = 123; c1[1] = 104; c1[2] = 238;
     res[10]=c1;
     
     c1 = new int[3];
     c1[0] = 95; c1[1] = 158; c1[2] = 160;
     res[11]=c1;
     
     c1 = new int[3];
     c1[0] = 106; c1[1] = 90; c1[2] = 205;
     res[12]=c1;
     
     c1 = new int[3];
     c1[0] = 72; c1[1] = 61; c1[2] = 139;
     res[13]=c1;
     
     c1 = new int[3];
     c1[0] = 65; c1[1] = 105; c1[2] = 225;
     res[14]=c1;
     
     c1 = new int[3];
     c1[0] = 0; c1[1] = 0; c1[2] = 255;
     res[15]=c1;
     
     c1 = new int[3];
     c1[0] = 0 ; c1[1] = 0; c1[2] = 139;
     res[16]=c1;
     
     c1 = new int[3];
     c1[0] = 0; c1[1] = 0; c1[2] = 128;
     res[17]=c1;
     
     c1 = new int[3];
     c1[0] = 25 ; c1[1] = 25; c1[2] = 112;
     res[18]=c1;
     
     c1 = new int[3];
     c1[0] = 138; c1[1] = 43; c1[2] = 226;
     res[19]=c1;
     
     c1 = new int[3];
     c1[0] = 75; c1[1] = 0; c1[2] = 130;
     res[20] = c1;
    
  
      return res;
  } 


} // Fin de la classe Groupe
